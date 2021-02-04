package pl.coderslab.MicroFirm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.MicroFirm.repository.FirmDataRepository;
import pl.coderslab.MicroFirm.repository.TransactionRepository;

@Controller
@RequestMapping(path = "/home")
public class HomePageController {

    private final TransactionRepository transactionRepository;
    private final FirmDataRepository firmDataRepository;

    public HomePageController(TransactionRepository transactionRepository,
                              FirmDataRepository firmDataRepository) {
        this.transactionRepository = transactionRepository;
        this.firmDataRepository = firmDataRepository;
    }

    @GetMapping(path = "")
    public String home(Model model){
        model.addAttribute("mostRecent10Transactions", transactionRepository.getMostRecent10Transactions());
        model.addAttribute("firmData", firmDataRepository.findFirstByIdGreaterThan(0));
        return "/home";
    }
}
