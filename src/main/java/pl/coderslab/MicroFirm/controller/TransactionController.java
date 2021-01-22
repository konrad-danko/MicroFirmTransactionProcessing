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
        model.addAttribute("transaction", transactionRepository.findById(id).orElse(null));
        model.addAttribute("headerMessage", "Szczegóły transakcji");
        model.addAttribute("disabledParam", "true");   // powinno być: "true"
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
        transaction.setInvoiceNo("145/01/21");    // !!!!!!!!dorobić metodę na numerację faktur
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
    public String processAddTransaction(@ModelAttribute @Valid Transaction transaction, BindingResult result, Model model, HttpServletRequest request) {
        if (result.hasErrors()) {
            model.addAttribute("headerMessage", "Dodaj nową transakcję");
            model.addAttribute("disabledParam", "false");
            model.addAttribute("submitBtnVisibleParam", "visible");
            model.addAttribute("editBtnVisibleParam", "invisible");
            model.addAttribute("delBtnVisibleParam", "invisible");
            return "/transaction/formTransaction";
        }
        transaction.setCreatedByUser(getLoggedUser(request));
        transactionRepository.save(transaction);
        return "redirect:/transaction/showAllTransactions";
    }








}
