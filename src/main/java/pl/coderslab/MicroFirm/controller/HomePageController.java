package pl.coderslab.MicroFirm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/home")
public class HomePageController {

    @GetMapping(path = "")
    public String home(Model model){

        String loginName = "JakiśTamUser";

        model.addAttribute("loginName", loginName);
        return "/home";
    }



}
