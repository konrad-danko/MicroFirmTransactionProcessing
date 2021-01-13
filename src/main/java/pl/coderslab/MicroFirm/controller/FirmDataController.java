package pl.coderslab.MicroFirm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.MicroFirm.model.FirmData;
import pl.coderslab.MicroFirm.repository.FirmDataRepository;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "/firmData")
public class FirmDataController {
    private final FirmDataRepository firmDataRepository;
    public FirmDataController(FirmDataRepository firmDataRepository) {
        this.firmDataRepository = firmDataRepository;
    }

    //    private User getLoggedUser??????????????????????
    String loginName = "JakiśTamUser";

    //show FirmData
    @GetMapping(path = "/showFirmData")
    public String showProduct(Model model) {
        model.addAttribute("loginName", loginName);
        if(firmDataRepository.findAll().size()==0){
            return "redirect:/firmData/addFirmData";
        }
        model.addAttribute("firmData", firmDataRepository.findFirstByIdGreaterThan(0));
        model.addAttribute("headerMessage", "Dane Firmy");
        model.addAttribute("disabledParam", "true");
        model.addAttribute("submitBtnVisibleParam", "invisible");
        model.addAttribute("editBtnVisibleParam", "visible");
        return "/firmData/formFirmData";
    }

    //add firmData
    @GetMapping(path = "/addFirmData")
    public String initiateAddFirmData(Model model) {
        model.addAttribute("loginName", loginName);
        model.addAttribute("firmData", new FirmData());
        model.addAttribute("headerMessage", "Wpisz dane Firmy");
        model.addAttribute("disabledParam", "false");
        model.addAttribute("submitBtnVisibleParam", "visible");
        model.addAttribute("editBtnVisibleParam", "invisible");
        return "/firmData/formFirmData";
    }
    @PostMapping(path = "/addFirmData")
    public String processAddFirmData(@ModelAttribute @Valid FirmData firmData, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("loginName", loginName);
            model.addAttribute("headerMessage", "Wpisz dane Firmy");
            model.addAttribute("disabledParam", "false");
            model.addAttribute("submitBtnVisibleParam", "visible");
            model.addAttribute("editBtnVisibleParam", "invisible");
            return "/firmData/formFirmData";
        }
        firmDataRepository.save(firmData);
        return "redirect:/firmData/showFirmData";
    }

    //edit firmData
    @GetMapping(path = "/editFirmData/{id}")
    public String initiateEditFirmData(Model model, @PathVariable long id) {
        model.addAttribute("loginName", loginName);
        model.addAttribute("firmData", firmDataRepository.findById(id).orElse(null));
        model.addAttribute("headerMessage", "Edytuj dane Firmy");
        model.addAttribute("disabledParam", "false");
        model.addAttribute("submitBtnVisibleParam", "visible");
        model.addAttribute("editBtnVisibleParam", "invisible");
        return "/firmData/formFirmData";
    }
    @PostMapping(path = "/editFirmData/{id}")
    public String processEditFirmData(@ModelAttribute @Valid FirmData firmData, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("loginName", loginName);
            model.addAttribute("headerMessage", "Edytuj dane Firmy");
            model.addAttribute("disabledParam", "false");
            model.addAttribute("submitBtnVisibleParam", "visible");
            model.addAttribute("editBtnVisibleParam", "invisible");
            return "/firmData/formFirmData";
        }
        firmDataRepository.save(firmData);
        return "redirect:/firmData/showFirmData";
    }
}
