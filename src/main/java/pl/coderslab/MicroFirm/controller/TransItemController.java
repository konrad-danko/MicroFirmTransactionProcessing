package pl.coderslab.MicroFirm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.MicroFirm.model.Customer;
import pl.coderslab.MicroFirm.model.Product;
import pl.coderslab.MicroFirm.model.TransItem;
import pl.coderslab.MicroFirm.model.Transaction;
import pl.coderslab.MicroFirm.repository.CustomerRepository;
import pl.coderslab.MicroFirm.repository.ProductRepository;
import pl.coderslab.MicroFirm.repository.TransItemRepository;
import pl.coderslab.MicroFirm.repository.TransactionRepository;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping(path = "/transItem")
public class TransItemController {

    private final TransItemRepository transItemRepository;
    private final TransactionRepository transactionRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
    public TransItemController(TransItemRepository transItemRepository,
                               TransactionRepository transactionRepository,
                               ProductRepository productRepository,
                               CustomerRepository customerRepository) {
        this.transItemRepository = transItemRepository;
        this.transactionRepository = transactionRepository;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
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

    @ModelAttribute("allCustomers")
    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    @ModelAttribute("allPaymentTypes")
    public Transaction.PaymentType[] getAllPaymentTypes(){
        return Transaction.PaymentType.values();
    }

    @ModelAttribute("allProducts")
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    //add a transItem
    @GetMapping(path = "/addTransItem/{id}")
    public String initiateAddTransItem(Model model, @PathVariable long id) {
        TransItem transItem = new TransItem();
        transItem.setId(id);
        transItem.setQuantity(0);
        transItem.setNetPricePer1000(new BigDecimal("0.00"));
        transItem.setNetAmount(new BigDecimal("0.00"));
        transItem.setVatAmount(new BigDecimal("0.00"));
        transItem.setGrossAmount(new BigDecimal("0.00"));
        model.addAttribute("transItem", transItem);

        Transaction transaction = transactionRepository.findById(id).orElse(null);
        setFormattedCreatedAndUpdatedAsModelAttributes(transaction, model);
        model.addAttribute("transaction", transaction);
        model.addAttribute("allTransItemsExisting", transItemRepository.findAllByTransaction_Id(id));

        model.addAttribute("headerMessage", "Szczegóły transakcji");
        model.addAttribute("disabledParam", "true");
        model.addAttribute("submitBtnVisibleParam", "invisible");
        model.addAttribute("editBtnVisibleParam", "invisible");
        model.addAttribute("delBtnVisibleParam", "invisible");

        return "/transItem/formTransItem";
    }










}
