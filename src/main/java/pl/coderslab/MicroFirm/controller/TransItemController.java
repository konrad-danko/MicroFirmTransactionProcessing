package pl.coderslab.MicroFirm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.MicroFirm.model.Product;
import pl.coderslab.MicroFirm.model.TransItem;
import pl.coderslab.MicroFirm.model.Transaction;
import pl.coderslab.MicroFirm.model.User;
import pl.coderslab.MicroFirm.repository.ProductRepository;
import pl.coderslab.MicroFirm.repository.TransItemRepository;
import pl.coderslab.MicroFirm.repository.TransactionRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping(path = "/transItem")
public class TransItemController {

    private final TransItemRepository transItemRepository;
    private final TransactionRepository transactionRepository;
    private final ProductRepository productRepository;
    public TransItemController(TransItemRepository transItemRepository,
                               TransactionRepository transactionRepository,
                               ProductRepository productRepository) {
        this.transItemRepository = transItemRepository;
        this.transactionRepository = transactionRepository;
        this.productRepository = productRepository;
    }

    private User getLoggedUser(HttpServletRequest request){
        HttpSession session = request.getSession();
        return (User)session.getAttribute("loggedUser");
    }

    private void setFormattedCreatedAndUpdatedAsModelAttributes(Transaction transaction, Model model){
        long id = transaction.getId();
        Transaction transactionFromDB = transactionRepository.findById(id).orElse(null);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy, HH:mm:ss");
        if(transactionFromDB.getCreated()!=null){
            model.addAttribute("formattedCreated", formatter.format(transactionFromDB.getCreated()));
        }
        if(transactionFromDB.getUpdated()!=null){
            model.addAttribute("formattedUpdated", formatter.format(transactionFromDB.getUpdated()));
        }
    }

    private String getAmountInWords (BigDecimal amount){
        char[] charArray = amount.toString().toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("-- ");
        String word;
        for (char c: charArray) {
            switch (c){
                case '0': word = "zero";
                    break;
                case '1': word = "jeden";
                    break;
                case '2': word = "dwa";
                    break;
                case '3': word = "trzy";
                    break;
                case '4': word = "cztery";
                    break;
                case '5': word = "pięć";
                    break;
                case '6': word = "sześć";
                    break;
                case '7': word = "siedem";
                    break;
                case '8': word = "osiem";
                    break;
                case '9': word = "dziewięć";
                    break;
                case '.': word = "zł.";
                    break;
                default: word = "brak";
                    break;
            }
            stringBuilder.append(word);
            stringBuilder.append(" ");
        }
        stringBuilder.append("gr. --");
        return stringBuilder.toString();
    }

    private void refreshTransactionalTotalAmounts(Transaction transaction){
        BigDecimal totalNetAmount = new BigDecimal("0.00");
        BigDecimal totalVatAmount = new BigDecimal("0.00");
        BigDecimal totalGrossAmount = new BigDecimal("0.00");
        List<TransItem> transItemList = transItemRepository.findAllByTransaction_Id(transaction.getId());
        for(TransItem item : transItemList){
            totalNetAmount = totalNetAmount.add(item.getNetAmount());
            totalVatAmount = totalVatAmount.add(item.getVatAmount());
            totalGrossAmount = totalGrossAmount.add(item.getGrossAmount());
        }
        transaction.setTotalNetAmount(totalNetAmount);
        transaction.setTotalVatAmount(totalVatAmount);
        transaction.setTotalGrossAmount(totalGrossAmount);
        transaction.setPaymentAmountInWords(getAmountInWords(totalGrossAmount));
        if ("GZ".equals(transaction.getPaymentType().toString())){
            transaction.setAmountPaid(totalGrossAmount);
        }
        transactionRepository.save(transaction);
    }

    @ModelAttribute("allProducts")
    public List<Product> getAllProducts(){
        return productRepository.findAllOrderedByName();
    }

    //add a transItem
    @GetMapping(path = "/addTransItem/{id}")
    public String initiateAddTransItem(Model model, 
                                       @PathVariable long id) {
        Transaction transaction = transactionRepository.findById(id).orElse(null);
        setFormattedCreatedAndUpdatedAsModelAttributes(transaction, model);
        TransItem transItem = new TransItem();
        transItem.setTransaction(transaction);
        transItem.setQuantity(0);
        transItem.setNetPricePer1000(new BigDecimal("0.00"));
        transItem.setNetAmount(new BigDecimal("0.00"));
        transItem.setVatAmount(new BigDecimal("0.00"));
        transItem.setGrossAmount(new BigDecimal("0.00"));
        model.addAttribute("transItem", transItem);
        model.addAttribute("transaction", transaction);
        model.addAttribute("allTransItemsExisting", transItemRepository.findAllByTransaction_Id(id));
        model.addAttribute("headerMessage", "Szczegóły transakcji");
        return "/transItem/formTransItem";
    }
    @PostMapping(path = "/addTransItem/{id}")
    public String processAddTransItem(@ModelAttribute @Valid TransItem transItem,
                                      BindingResult result,
                                      Model model,
                                      HttpServletRequest request,
                                      @PathVariable long id) {
        Transaction transaction = transactionRepository.findById(id).orElse(null);
        if (result.hasErrors()) {
            setFormattedCreatedAndUpdatedAsModelAttributes(transaction, model);
            model.addAttribute("transaction", transaction);
            model.addAttribute("allTransItemsExisting", transItemRepository.findAllByTransaction_Id(id));
            model.addAttribute("headerMessage", "Szczegóły transakcji");
            return "/transItem/formTransItem";
        }
        transItemRepository.save(transItem);
        transaction.setUpdatedByUser(getLoggedUser(request));
        refreshTransactionalTotalAmounts(transaction);
        return "redirect:/transaction/showTransaction/"+id;
    }

    //delete a transItem
    @GetMapping(path = "/deleteTransItem/{id}")
    public String processDeleteTransItem(@PathVariable long id,
                                         HttpServletRequest request) {
        TransItem transItem = transItemRepository.findById(id).orElse(null);
        long transactionId = transItem.getTransaction().getId();
        transItemRepository.delete(transItem);
        Transaction transaction = transactionRepository.findById(transactionId).orElse(null);
        transaction.setUpdatedByUser(getLoggedUser(request));
        refreshTransactionalTotalAmounts(transaction);
        return "redirect:/transaction/showTransaction/"+transactionId;
    }
}
