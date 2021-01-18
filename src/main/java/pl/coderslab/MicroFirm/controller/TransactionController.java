package pl.coderslab.MicroFirm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.MicroFirm.model.User;
import pl.coderslab.MicroFirm.repository.TransactionRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(path = "/transaction")
public class TransactionController {

    private final TransactionRepository transactionRepository;
    public TransactionController(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    private User getLoggedUser(HttpServletRequest request){
        HttpSession session = request.getSession();
        return (User)session.getAttribute("loggedUser");
    }








}
