package pl.coderslab.MicroFirm.validator;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import pl.coderslab.MicroFirm.model.Customer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UniqueNIPValidator implements ConstraintValidator<UniqueNIP, String> {

    @Override
    public void initialize(UniqueNIP constraintAnnotation) {
    }

    @Override
    public boolean isValid(String customerNIP, ConstraintValidatorContext context) {

        HttpServletRequest request = getCurrentHttpRequest();
        HttpSession session = request.getSession();

        String editedCustomerNIP = (String) session.getAttribute("editedCustomerNIP");
        if ( customerNIP == null || customerNIP.equals("") || customerNIP.equals(editedCustomerNIP)) {
            return true;
        }

        List<Customer> customerList = new ArrayList<>();
        if (Objects.nonNull(session.getAttribute("customerList"))) {
            customerList = (List<Customer>) session.getAttribute("customerList");
        }
        for (Customer customer : customerList){
            if (customerNIP.equals(customer.getCustomerNIP())){
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
