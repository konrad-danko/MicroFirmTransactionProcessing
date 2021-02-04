package pl.coderslab.MicroFirm.validator;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import pl.coderslab.MicroFirm.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UniqueUserValidator implements ConstraintValidator<UniqueUser, String> {

    @Override
    public void initialize(UniqueUser constraintAnnotation) {
    }

    @Override
    public boolean isValid(String loginName, ConstraintValidatorContext context) {

        HttpServletRequest request = getCurrentHttpRequest();
        HttpSession session = request.getSession();

        String editedLoginName = (String) session.getAttribute("editedLoginName");
        if ( loginName == null || loginName.equals("") || loginName.equals(editedLoginName)) {
            return true;
        }

        List<User> userList = new ArrayList<>();
        if (Objects.nonNull(session.getAttribute("userList"))) {
            userList = (List<User>) session.getAttribute("userList");
        }
        for (User user : userList){
            if (loginName.equals(user.getLoginName())){
                return false;
            }
        }
        return true;
    }

    private static HttpServletRequest getCurrentHttpRequest(){
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes) {
            HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();
            return request;
        }
        return null;
    }
}
