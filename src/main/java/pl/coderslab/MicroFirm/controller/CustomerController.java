package pl.coderslab.MicroFirm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.MicroFirm.model.Customer;
import pl.coderslab.MicroFirm.model.User;
import pl.coderslab.MicroFirm.repository.CustomerRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Objects;

@Controller
@RequestMapping(path = "/customer")
public class CustomerController {

    private final CustomerRepository customerRepository;
    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping(path = "/showAllCustomers")
    public String showAllCustomers(Model model) {
        model.addAttribute("allCustomers", customerRepository.findAll());
        return "/customer/allCustomers";
    }

    //show a customer
    @GetMapping(path = "/showCustomer/{id}")
    public String showCustomer(Model model, @PathVariable long id) {
        model.addAttribute("customer", customerRepository.findById(id).orElse(null));
        model.addAttribute("headerMessage", "Dane klienta");

        model.addAttribute("disabledParam", "true");
        model.addAttribute("submitBtnVisibleParam", "invisible");
        model.addAttribute("editBtnVisibleParam", "visible");
        model.addAttribute("delBtnVisibleParam", "visible");
        return "/customer/formCustomer";
    }

    //add a customer
    @GetMapping(path = "/addCustomer")
    public String initiateAddCustomer(Model model) {
        model.addAttribute("customer", new Customer());
        model.addAttribute("headerMessage", "Dodaj nowego klienta");
        model.addAttribute("disabledParam", "false");
        model.addAttribute("submitBtnVisibleParam", "visible");
        model.addAttribute("editBtnVisibleParam", "invisible");
        model.addAttribute("delBtnVisibleParam", "invisible");
        return "/customer/formCustomer";
    }
    @PostMapping(path = "/addCustomer")
    public String processAddCustomer(@ModelAttribute @Valid Customer customer, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("headerMessage", "Dodaj nowego klienta");
            model.addAttribute("disabledParam", "false");
            model.addAttribute("submitBtnVisibleParam", "visible");
            model.addAttribute("editBtnVisibleParam", "invisible");
            model.addAttribute("delBtnVisibleParam", "invisible");
            return "/customer/formCustomer";
        }
        customerRepository.save(customer);
        return "redirect:/customer/showAllCustomers";
    }

    //edit a customer
    @GetMapping(path = "/editCustomer/{id}")
    public String initiateEditCustomer(Model model, @PathVariable long id) {
        model.addAttribute("customer", customerRepository.findById(id).orElse(null));
        model.addAttribute("headerMessage", "Edytuj dane klienta");
        model.addAttribute("disabledParam", "false");
        model.addAttribute("submitBtnVisibleParam", "visible");
        model.addAttribute("editBtnVisibleParam", "invisible");
        model.addAttribute("delBtnVisibleParam", "invisible");
        return "/customer/formCustomer";
    }
    @PostMapping(path = "/editCustomer/{id}")
    public String processEditCustomer(@ModelAttribute @Valid Customer customer, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("headerMessage", "Edytuj dane klienta");
            model.addAttribute("disabledParam", "false");
            model.addAttribute("submitBtnVisibleParam", "visible");
            model.addAttribute("editBtnVisibleParam", "invisible");
            model.addAttribute("delBtnVisibleParam", "invisible");
            return "/customer/formCustomer";
        }
        customerRepository.save(customer);
        return "redirect:/customer/showAllCustomers";
    }

    //delete a customer
    @GetMapping(path = "/deleteCustomer/{id}")
    public String initiateDeleteCustomer(Model model, @PathVariable long id) {
        model.addAttribute("customer", customerRepository.findById(id).orElse(null));
        model.addAttribute("headerMessage", "Potwierdź usunięcie danych klienta");
        model.addAttribute("disabledParam", "true");
        model.addAttribute("submitBtnVisibleParam", "visible");
        model.addAttribute("editBtnVisibleParam", "invisible");
        model.addAttribute("delBtnVisibleParam", "invisible");
        return "/customer/formCustomer";
    }
    @PostMapping(path = "/deleteCustomer/{id}")
    public String processDeleteCustomer(@ModelAttribute Customer customer) {
        //tu dopisać kod na sprawdzanie czy klient nie ma jakiejś transakcji
        //customerRepository.delete(customer);
        return "redirect:/customer/showAllCustomers";
    }
}
