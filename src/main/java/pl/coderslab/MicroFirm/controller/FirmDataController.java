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
    private final DisplayParams displayParams;
    public FirmDataController(FirmDataRepository firmDataRepository,
                              DisplayParams displayParams) {
        this.firmDataRepository = firmDataRepository;
        this.displayParams = displayParams;
    }

    //show FirmData
    @GetMapping(path = "/showFirmData")
    public String showProduct(Model model) {
        if(firmDataRepository.findAll().size()==0){
            return "redirect:/firmData/addFirmData";
        }
        model.addAttribute("firmData", firmDataRepository.findFirstByIdGreaterThan(0));
        model.addAttribute("headerMessage", "Dane Firmy");
        displayParams.setShowParams(model);
        return "/firmData/formFirmData";
    }

    //add firmData
    @GetMapping(path = "/addFirmData")
    public String initiateAddFirmData(Model model) {
        model.addAttribute("firmData", new FirmData());
        model.addAttribute("headerMessage", "Wpisz dane Firmy");
        displayParams.setAddEditParams(model);
        return "/firmData/formFirmData";
    }
    @PostMapping(path = "/addFirmData")
    public String processAddFirmData(@ModelAttribute @Valid FirmData firmData, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("headerMessage", "Wpisz dane Firmy");
            displayParams.setAddEditParams(model);
            return "/firmData/formFirmData";
        }
        firmDataRepository.save(firmData);
        return "redirect:/firmData/showFirmData";
    }

    //edit firmData
    @GetMapping(path = "/editFirmData/{id}")
    public String initiateEditFirmData(Model model, @PathVariable long id) {
        model.addAttribute("firmData", firmDataRepository.findById(id).orElse(null));
        model.addAttribute("headerMessage", "Edytuj dane Firmy");
        displayParams.setAddEditParams(model);
        return "/firmData/formFirmData";
    }
    @PostMapping(path = "/editFirmData/{id}")
    public String processEditFirmData(@ModelAttribute @Valid FirmData firmData, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("headerMessage", "Edytuj dane Firmy");
            displayParams.setAddEditParams(model);
            return "/firmData/formFirmData";
        }
        firmDataRepository.save(firmData);
        return "redirect:/firmData/showFirmData";
    }
}
