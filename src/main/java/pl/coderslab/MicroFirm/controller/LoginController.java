package pl.coderslab.MicroFirm.controller;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import pl.coderslab.MicroFirm.model.User;
import pl.coderslab.MicroFirm.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@SessionAttributes("loginName")
@RequestMapping(path = "/login")
public class LoginController {

    private final UserRepository userRepository;
    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //metoda hashująca hasło:
    private String hashPassword (String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    //login a user
    @GetMapping(path = "/login")
    public String initiateLoginVerification(Model model){
        model.addAttribute("headerMessage", "Zaloguj się do aplikacji");
        return "/login/formLogin";
    }
    @PostMapping(path = "/login")
    public String processLoginVerification(Model model, HttpServletRequest request){

        String loginName = request.getParameter("loginName");
        String password = request.getParameter("password");

        Optional<User> optionalUser = userRepository.findFirstByLoginName(loginName);

        if (optionalUser.isEmpty() || !BCrypt.checkpw(password, optionalUser.get().getPassword())){
            model.addAttribute("headerMessage", "Błędne logowanie, spróbuj ponownie");
            return "/login/formLogin";
        }

        HttpSession session = request.getSession();
        session.setAttribute("loggedUser", optionalUser.get());
        model.addAttribute("loginName", optionalUser.get().getLoginName());
        return "redirect:/home";
    }

    //logout a user
    @GetMapping(path = "/logout")
    public String processUserLogout(Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        model.addAttribute("loginName", null);
        session.invalidate();
        return "redirect:/home";
    }

    //edit password
    @GetMapping(path = "/editPassword")
    public String initiateEditPassword(Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        User loggedUser = (User)session.getAttribute("loggedUser");
        String loginName = loggedUser.getLoginName();
        model.addAttribute("loginName", loginName);
        model.addAttribute("headerMessage", "Zmień hasło do aplikacji");
        return "/login/formEditPassword";
    }
    @PostMapping(path = "/editPassword")
    public String processEditPassword(Model model, HttpServletRequest request){

        String loginName = request.getParameter("loginName");
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        Optional<User> optionalUser = userRepository.findFirstByLoginName(loginName);

        if (!BCrypt.checkpw(oldPassword, optionalUser.get().getPassword()) || newPassword.isEmpty()){
            model.addAttribute("headerMessage", "Błędne dane logowania, spróbuj ponownie");
            return "/login/formEditPassword";
        }

        User user = optionalUser.get();
        user.setPassword(hashPassword(newPassword));
        userRepository.save(user);
        return "redirect:/home";
    }
}
