package pl.coderslab.MicroFirm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.MicroFirm.model.Customer;
import pl.coderslab.MicroFirm.model.Transaction;
import pl.coderslab.MicroFirm.model.User;
import pl.coderslab.MicroFirm.repository.CustomerRepository;
import pl.coderslab.MicroFirm.repository.FirmDataRepository;
import pl.coderslab.MicroFirm.repository.TransItemRepository;
import pl.coderslab.MicroFirm.repository.TransactionRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping(path = "/transaction")
public class TransactionController {

    private final TransactionRepository transactionRepository;
    private final TransItemRepository transItemRepository;
    private final FirmDataRepository firmDataRepository;
    private final CustomerRepository customerRepository;
    public TransactionController(TransactionRepository transactionRepository,
                                 TransItemRepository transItemRepository,
                                 FirmDataRepository firmDataRepository,
                                 CustomerRepository customerRepository) {
        this.transactionRepository = transactionRepository;
        this.transItemRepository = transItemRepository;
        this.firmDataRepository = firmDataRepository;
        this.customerRepository = customerRepository;
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

    @ModelAttribute("allCustomers")
    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    @ModelAttribute("allPaymentTypes")
    public Transaction.PaymentType[] getAllPaymentTypes(){
        return Transaction.PaymentType.values();
    }

    @GetMapping(path = "/showAllTransactions")
    public String showAllTransactions(Model model) {
        model.addAttribute("allTransactions", transactionRepository.findAll());
        return "/transaction/allTransactions";
    }

    //show a transaction
    @GetMapping(path = "/showTransaction/{id}")
    public String showTransaction(Model model, @PathVariable long id) {
        Transaction transaction = transactionRepository.findById(id).orElse(null);
        setFormattedCreatedAndUpdatedAsModelAttributes(transaction, model);
        model.addAttribute("transaction", transaction);
        model.addAttribute("allTransItems", transItemRepository.findAllByTransaction_Id(id));
        model.addAttribute("headerMessage", "Szczegóły transakcji");
        model.addAttribute("disabledParam", "true");
        model.addAttribute("submitBtnVisibleParam", "invisible");
        model.addAttribute("editBtnVisibleParam", "visible");
        model.addAttribute("delBtnVisibleParam", "visible");
        return "/transaction/formTransaction";
    }

    //add a transaction
    @GetMapping(path = "/addTransaction")
    public String initiateAddTransaction(Model model) {
        Transaction transaction = new Transaction();
        transaction.setFirmData(firmDataRepository.findFirstByIdGreaterThan(0));
        transaction.setTransactionDate(LocalDate.now());
        transaction.setSellDate(LocalDate.now());
        transaction.setPaymentDueDate(LocalDate.now());
        transaction.setIssueInvoice(false);
        transaction.setInvoiceNo("145/01/21");    // !!!!!!!!dorobić metodę na numerację faktur !!!!!!!!!
        transaction.setPaymentType(Transaction.PaymentType.GZ);
        transaction.setTotalNetAmount(new BigDecimal("0.00"));
        transaction.setTotalVatAmount(new BigDecimal("0.00"));
        transaction.setTotalGrossAmount(new BigDecimal("0.00"));
        transaction.setPaymentAmountInWords("zero złotych, zero zero groszy");
        model.addAttribute("transaction", transaction);
        model.addAttribute("headerMessage", "Dodaj nową transakcję");
        model.addAttribute("disabledParam", "false");
        model.addAttribute("submitBtnVisibleParam", "visible");
        model.addAttribute("editBtnVisibleParam", "invisible");
        model.addAttribute("delBtnVisibleParam", "invisible");
        return "/transaction/formTransaction";
    }
    @PostMapping(path = "/addTransaction")
    public String processAddTransaction(@ModelAttribute @Valid Transaction transaction,
                                        BindingResult result,
                                        Model model,
                                        HttpServletRequest request) {
        if (result.hasErrors()) {
            model.addAttribute("headerMessage", "Dodaj nową transakcję");
            model.addAttribute("disabledParam", "false");
            model.addAttribute("submitBtnVisibleParam", "visible");
            model.addAttribute("editBtnVisibleParam", "invisible");
            model.addAttribute("delBtnVisibleParam", "invisible");
            return "/transaction/formTransaction";
        }
        transaction.setCreatedByUser(getLoggedUser(request));
        if(!transaction.isIssueInvoice()){
            transaction.setInvoiceNo(null);
        }
        transactionRepository.save(transaction);
        return "redirect:/transaction/showTransaction/"+transaction.getId();
    }

    //edit a transaction
    @GetMapping(path = "/editTransaction/{id}")
    public String initiateEditTransaction(Model model, @PathVariable long id) {
        Transaction transaction = transactionRepository.findById(id).orElse(null);
        if(transaction.getInvoiceNo()==null){
            transaction.setInvoiceNo("145/01/21");    // !!!!!!!!dorobić metodę na numerację faktur !!!!!!!!!
        }
        setFormattedCreatedAndUpdatedAsModelAttributes(transaction, model);
        model.addAttribute("transaction", transaction);
        model.addAttribute("allTransItems", transItemRepository.findAllByTransaction_Id(id));
        model.addAttribute("headerMessage", "Edytuj dane transakcji");
        model.addAttribute("disabledParam", "false");
        model.addAttribute("submitBtnVisibleParam", "visible");
        model.addAttribute("editBtnVisibleParam", "invisible");
        model.addAttribute("delBtnVisibleParam", "invisible");
        return "/transaction/formTransaction";
    }
    @PostMapping(path = "/editTransaction/{id}")
    public String processEditTransaction(@ModelAttribute @Valid Transaction transaction,
                                         BindingResult result,
                                         Model model,
                                         HttpServletRequest request,
                                         @PathVariable long id) {
        if (result.hasErrors()) {
            setFormattedCreatedAndUpdatedAsModelAttributes(transaction, model);
            model.addAttribute("allTransItems", transItemRepository.findAllByTransaction_Id(id));
            model.addAttribute("headerMessage", "Edytuj dane transakcji");
            model.addAttribute("disabledParam", "false");
            model.addAttribute("submitBtnVisibleParam", "visible");
            model.addAttribute("editBtnVisibleParam", "invisible");
            model.addAttribute("delBtnVisibleParam", "invisible");
            return "/transaction/formTransaction";
        }
        transaction.setUpdatedByUser(getLoggedUser(request));
        if(!transaction.isIssueInvoice()){
            transaction.setInvoiceNo(null);
        }
        //poniższa linijka jest dlatego, że przeglądarka obcina sekundy z daty "created"
        //i dlatego muszę pobierać tą datę z bazy i dopisać do transakcji przed ponownym zapisem do bazy
        transaction.setCreated(transactionRepository.findById(transaction.getId()).get().getCreated());
        transactionRepository.save(transaction);
        return "redirect:/transaction/showTransaction/"+transaction.getId();
    }

    //delete a transaction
    @GetMapping(path = "/deleteTransaction/{id}")
    public String initiateDeleteTransaction(Model model, @PathVariable long id) {
        Transaction transaction = transactionRepository.findById(id).orElse(null);
        setFormattedCreatedAndUpdatedAsModelAttributes(transaction, model);
        model.addAttribute("transaction", transaction);
        model.addAttribute("allTransItems", transItemRepository.findAllByTransaction_Id(id));
        model.addAttribute("headerMessage", "Potwierdź usunięcie transakcji");
        model.addAttribute("disabledParam", "true");
        model.addAttribute("submitBtnVisibleParam", "visible");
        model.addAttribute("editBtnVisibleParam", "invisible");
        model.addAttribute("delBtnVisibleParam", "invisible");
        return "/transaction/formTransaction";
    }
    @PostMapping(path = "/deleteTransaction/{id}")
    public String processDeleteTransaction(@ModelAttribute Transaction transaction, @PathVariable long id) {
        if(transItemRepository.findAllByTransaction_Id(id).size()>0){
            return "redirect:/transaction/showTransaction/"+transaction.getId();
        }
        transactionRepository.delete(transaction);
        return "redirect:/transaction/showAllTransactions";
    }
}
