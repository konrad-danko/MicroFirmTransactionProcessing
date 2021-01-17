package pl.coderslab.MicroFirm.controller;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.MicroFirm.model.User;
import pl.coderslab.MicroFirm.repository.UserRepository;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "/user")
public class UserController {

    private final UserRepository userRepository;
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //metoda hashująca hasło:
    private String hashPassword (String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    @GetMapping(path = "/showAllUsers")
    public String showAllUsers(Model model) {
        model.addAttribute("allUsers", userRepository.findAll());
        return "/user/allUsers";
    }

    //show a user
    @GetMapping(path = "/showUser/{id}")
    public String showUser(Model model, @PathVariable long id) {
        model.addAttribute("user", userRepository.findById(id).orElse(null));
        model.addAttribute("headerMessage", "Dane użytkownika");
        model.addAttribute("disabledParam", "true");
        model.addAttribute("submitBtnVisibleParam", "invisible");
        model.addAttribute("editBtnVisibleParam", "visible");
        model.addAttribute("delBtnVisibleParam", "visible");
        return "/user/formUser";
    }

    //add a user
    @GetMapping(path = "/addUser")
    public String initiateAddUser(Model model) {
        User user = new User();
        user.setPassword("password");
        model.addAttribute("user", user);
        model.addAttribute("headerMessage", "Dodaj nowego użytkownika");
        model.addAttribute("disabledParam", "false");
        model.addAttribute("submitBtnVisibleParam", "visible");
        model.addAttribute("editBtnVisibleParam", "invisible");
        model.addAttribute("delBtnVisibleParam", "invisible");
        return "/user/formUser";
    }
    @PostMapping(path = "/addUser")
    public String processAddUser(@ModelAttribute @Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("headerMessage", "Dodaj nowego użytkownika");
            model.addAttribute("disabledParam", "false");
            model.addAttribute("submitBtnVisibleParam", "visible");
            model.addAttribute("editBtnVisibleParam", "invisible");
            model.addAttribute("delBtnVisibleParam", "invisible");
            return "/user/formUser";
        }
        //przed zapisem do bazy hashujemy "password" jako hasło tymczasowe
        user.setPassword(hashPassword("password"));
        userRepository.save(user);
        return "redirect:/user/showAllUsers";
    }

    //edit a user
    @GetMapping(path = "/editUser/{id}")
    public String initiateEditUser(Model model, @PathVariable long id) {
        model.addAttribute("user", userRepository.findById(id).orElse(null));
        model.addAttribute("headerMessage", "Edytuj dane użytkownika");
        model.addAttribute("disabledParam", "false");
        model.addAttribute("submitBtnVisibleParam", "visible");
        model.addAttribute("editBtnVisibleParam", "invisible");
        model.addAttribute("delBtnVisibleParam", "invisible");
        return "/user/formUser";
    }
    @PostMapping(path = "/editUser/{id}")
    public String processEditUser(@ModelAttribute @Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("headerMessage", "Edytuj dane użytkownika");
            model.addAttribute("disabledParam", "false");
            model.addAttribute("submitBtnVisibleParam", "visible");
            model.addAttribute("editBtnVisibleParam", "invisible");
            model.addAttribute("delBtnVisibleParam", "invisible");
            return "/user/formUser";
        }
        userRepository.save(user);
        return "redirect:/user/showAllUsers";
    }

    //delete a user
    @GetMapping(path = "/deleteUser/{id}")
    public String initiateDeleteUser(Model model, @PathVariable long id) {
        model.addAttribute("user", userRepository.findById(id).orElse(null));
        model.addAttribute("headerMessage", "Potwierdź usunięcie danych użytkownika");
        model.addAttribute("disabledParam", "true");
        model.addAttribute("submitBtnVisibleParam", "visible");
        model.addAttribute("editBtnVisibleParam", "invisible");
        model.addAttribute("delBtnVisibleParam", "invisible");
        return "/user/formUser";
    }
    @PostMapping(path = "/deleteUser/{id}")
    public String processDeleteUser(@ModelAttribute User user) {
        //tu dopisać kod na sprawdzanie czy użytkownik nie ma jakiegoś podpiętego rekordu
        //userRepository.delete(user);
        return "redirect:/user/showAllUsers";
    }
}
