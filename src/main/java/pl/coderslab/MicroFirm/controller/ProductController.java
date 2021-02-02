package pl.coderslab.MicroFirm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.MicroFirm.model.Product;
import pl.coderslab.MicroFirm.model.User;
import pl.coderslab.MicroFirm.repository.ProductRepository;
import pl.coderslab.MicroFirm.repository.TransItemRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping(path = "/product")
public class ProductController {

    private final ProductRepository productRepository;
    private final TransItemRepository transItemRepository;
    public ProductController(ProductRepository productRepository, TransItemRepository transItemRepository) {
        this.productRepository = productRepository;
        this.transItemRepository = transItemRepository;
    }

    private User getLoggedUser(HttpServletRequest request){
        HttpSession session = request.getSession();
        return (User)session.getAttribute("loggedUser");
    }

    @GetMapping(path = "/showAllProducts")
    public String showAllProducts(Model model) {
        model.addAttribute("allProducts", productRepository.findAll());
        return "/product/allProducts";
    }

    //show a product
    @GetMapping(path = "/showProduct/{id}")
    public String showProduct(Model model, @PathVariable long id) {
        model.addAttribute("product", productRepository.findById(id).orElse(null));
        model.addAttribute("headerMessage", "Dane produktu");
        model.addAttribute("disabledParam", "true");
        model.addAttribute("submitBtnVisibleParam", "invisible");
        model.addAttribute("editBtnVisibleParam", "visible");
        model.addAttribute("delBtnVisibleParam", "visible");
        return "/product/formProduct";
    }

    //add a product
    @GetMapping(path = "/addProduct")
    public String initiateAddProduct(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("headerMessage", "Dodaj nowy produkt");
        model.addAttribute("disabledParam", "false");
        model.addAttribute("submitBtnVisibleParam", "visible");
        model.addAttribute("editBtnVisibleParam", "invisible");
        model.addAttribute("delBtnVisibleParam", "invisible");
        return "/product/formProduct";
    }
    @PostMapping(path = "/addProduct")
    public String processAddProduct(@ModelAttribute @Valid Product product, BindingResult result, Model model, HttpServletRequest request) {
        if (result.hasErrors()) {
            model.addAttribute("headerMessage", "Dodaj nowy produkt");
            model.addAttribute("disabledParam", "false");
            model.addAttribute("submitBtnVisibleParam", "visible");
            model.addAttribute("editBtnVisibleParam", "invisible");
            model.addAttribute("delBtnVisibleParam", "invisible");
            return "/product/formProduct";
        }
        product.setCreatedByUser(getLoggedUser(request));
        productRepository.save(product);
        return "redirect:/product/showProduct/"+product.getId();
    }

    //edit a product
    @GetMapping(path = "/editProduct/{id}")
    public String initiateEditProduct(Model model, @PathVariable long id) {
        model.addAttribute("product", productRepository.findById(id).orElse(null));
        model.addAttribute("headerMessage", "Edytuj dane produktu");
        model.addAttribute("disabledParam", "false");
        model.addAttribute("submitBtnVisibleParam", "visible");
        model.addAttribute("editBtnVisibleParam", "invisible");
        model.addAttribute("delBtnVisibleParam", "invisible");
        return "/product/formProduct";
    }
    @PostMapping(path = "/editProduct/{id}")
    public String processEditProduct(@ModelAttribute @Valid Product product, BindingResult result, Model model, HttpServletRequest request) {
        if (result.hasErrors()) {
            model.addAttribute("headerMessage", "Edytuj dane produktu");
            model.addAttribute("disabledParam", "false");
            model.addAttribute("submitBtnVisibleParam", "visible");
            model.addAttribute("editBtnVisibleParam", "invisible");
            model.addAttribute("delBtnVisibleParam", "invisible");
            return "/product/formProduct";
        }
        product.setUpdatedByUser(getLoggedUser(request));
        //poniższa linijka jest dlatego, że przeglądarka obcina sekundy z daty "created"
        //i dlatego muszę pobierać tą datę z bazy i dopisać do productu przed ponownym zapisem do bazy
        product.setCreated(productRepository.findById(product.getId()).get().getCreated());
        productRepository.save(product);
        return "redirect:/product/showProduct/"+product.getId();
    }

    //delete a product
    @GetMapping(path = "/deleteProduct/{id}")
    public String initiateDeleteProduct(Model model, @PathVariable long id) {
        model.addAttribute("product", productRepository.findById(id).orElse(null));
        model.addAttribute("headerMessage", "Potwierdź usunięcie produktu");
        model.addAttribute("disabledParam", "true");
        model.addAttribute("submitBtnVisibleParam", "visible");
        model.addAttribute("editBtnVisibleParam", "invisible");
        model.addAttribute("delBtnVisibleParam", "invisible");
        return "/product/formProduct";
    }
    @PostMapping(path = "/deleteProduct/{id}")
    public String processDeleteProduct(@ModelAttribute Product product, @PathVariable long id) {
        if(transItemRepository.findAllByProduct_Id(id).size()>0){
            return "redirect:/product/showProduct/"+id;
        }
        productRepository.delete(product);
        return "redirect:/product/showAllProducts";
    }
}
