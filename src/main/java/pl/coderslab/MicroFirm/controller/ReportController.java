package pl.coderslab.MicroFirm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.MicroFirm.model.Transaction;
import pl.coderslab.MicroFirm.repository.TransactionRepository;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping(path = "/report")
public class ReportController {

    private final TransactionRepository transactionRepository;
    public ReportController(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @GetMapping(path = "/showInvoicedTransactions")
    public String showAllCustomers(Model model) {
        model.addAttribute("dateFrom", LocalDate.now());
        model.addAttribute("dateTo", LocalDate.now());
        return "/report/invoicedTransactions";
    }

    @PostMapping(path = "/showInvoicedTransactionsFromGivenPeriod")
    public String showInvoicedTransactionsFromGivenPeriod(Model model, HttpServletRequest request){
        String dateFromStr = request.getParameter("dateFrom");
        String dateToStr = request.getParameter("dateTo");
        LocalDate dateFrom = dateFromStr==null ? LocalDate.now() : LocalDate.parse(dateFromStr);
        LocalDate dateTo = dateFromStr==null ? LocalDate.now() : LocalDate.parse(dateToStr);

        List<Transaction> transactionList = transactionRepository
                .getInvoicedTransactionsFromGivenPeriod(dateFrom, dateTo);
        model.addAttribute("dateFrom", dateFrom);
        model.addAttribute("dateTo", dateTo);
        model.addAttribute("transactionList", transactionList);

        BigDecimal totalNetAmount = new BigDecimal("0.00");
        BigDecimal totalVatAmount = new BigDecimal("0.00");
        BigDecimal totalGrossAmount = new BigDecimal("0.00");
        for(Transaction transaction : transactionList){
            totalNetAmount = totalNetAmount.add(transaction.getTotalNetAmount());
            totalVatAmount = totalVatAmount.add(transaction.getTotalVatAmount());
            totalGrossAmount = totalGrossAmount.add(transaction.getTotalGrossAmount());
        }
        model.addAttribute("totalNetAmount", totalNetAmount);
        model.addAttribute("totalVatAmount", totalVatAmount);
        model.addAttribute("totalGrossAmount", totalGrossAmount);

        return "/report/invoicedTransactions";
    }

    @GetMapping(path = "/showUnpaidTransactions")
    public String showUnpaidTransactions(Model model){
        List<Transaction> transactionList = transactionRepository.getUnpaidTransactions();
        BigDecimal totalAmountPaid = new BigDecimal("0.00");
        BigDecimal totalGrossAmount = new BigDecimal("0.00");
        for(Transaction transaction : transactionList){
            totalAmountPaid = totalAmountPaid.add(transaction.getAmountPaid());
            totalGrossAmount = totalGrossAmount.add(transaction.getTotalGrossAmount());
        }
        model.addAttribute("totalAmountPaid", totalAmountPaid);
        model.addAttribute("totalGrossAmount", totalGrossAmount);
        model.addAttribute("transactionList", transactionList);
        model.addAttribute("today", LocalDate.now());
        return "/report/unpaidTransactions";
    }
}
