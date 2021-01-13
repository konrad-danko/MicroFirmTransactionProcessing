package pl.coderslab.MicroFirm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.MicroFirm.model.Product;
import pl.coderslab.MicroFirm.repository.ProductRepository;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "/product")
public class ProductController {

    private final ProductRepository productRepository;
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    //    private User getLoggedUser??????????????????????
    String loginName = "JakiśTamUser";

    @GetMapping(path = "/showAllProducts")
    public String showAllProducts(Model model) {
        model.addAttribute("loginName", loginName);
        model.addAttribute("allProducts", productRepository.findAll());
        return "/product/allProducts";
    }

    //show a product
    @GetMapping(path = "/showProduct/{id}")
    public String showProduct(Model model, @PathVariable long id) {
        model.addAttribute("loginName", loginName);
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
        model.addAttribute("loginName", loginName);
        model.addAttribute("product", new Product());
        model.addAttribute("headerMessage", "Dodaj nowy produkt");
        model.addAttribute("disabledParam", "false");
        model.addAttribute("submitBtnVisibleParam", "visible");
        model.addAttribute("editBtnVisibleParam", "invisible");
        model.addAttribute("delBtnVisibleParam", "invisible");
        return "/product/formProduct";
    }
    @PostMapping(path = "/addProduct")
    public String processAddProduct(@ModelAttribute @Valid Product product, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("loginName", loginName);
            model.addAttribute("headerMessage", "Dodaj nowy produkt");
            model.addAttribute("disabledParam", "false");
            model.addAttribute("submitBtnVisibleParam", "visible");
            model.addAttribute("editBtnVisibleParam", "invisible");
            model.addAttribute("delBtnVisibleParam", "invisible");
            return "/product/formProduct";
        }
        productRepository.save(product);
        return "redirect:/product/showAllProducts";
    }

    //edit a product
    @GetMapping(path = "/editProduct/{id}")
    public String initiateEditProduct(Model model, @PathVariable long id) {
        model.addAttribute("loginName", loginName);
        model.addAttribute("product", productRepository.findById(id).orElse(null));
        model.addAttribute("headerMessage", "Edytuj dane produktu");
        model.addAttribute("disabledParam", "false");
        model.addAttribute("submitBtnVisibleParam", "visible");
        model.addAttribute("editBtnVisibleParam", "invisible");
        model.addAttribute("delBtnVisibleParam", "invisible");
        return "/product/formProduct";
    }
    @PostMapping(path = "/editProduct/{id}")
    public String processEditProduct(@ModelAttribute @Valid Product product, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("loginName", loginName);
            model.addAttribute("headerMessage", "Edytuj dane produktu");
            model.addAttribute("disabledParam", "false");
            model.addAttribute("submitBtnVisibleParam", "visible");
            model.addAttribute("editBtnVisibleParam", "invisible");
            model.addAttribute("delBtnVisibleParam", "invisible");
            return "/product/formProduct";
        }
        productRepository.save(product);
        return "redirect:/product/showAllProducts";
    }

    //delete a product
    @GetMapping(path = "/deleteProduct/{id}")
    public String initiateDeleteProduct(Model model, @PathVariable long id) {
        model.addAttribute("loginName", loginName);
        model.addAttribute("product", productRepository.findById(id).orElse(null));
        model.addAttribute("headerMessage", "Potwierdź usunięcie produktu");
        model.addAttribute("disabledParam", "true");
        model.addAttribute("submitBtnVisibleParam", "visible");
        model.addAttribute("editBtnVisibleParam", "invisible");
        model.addAttribute("delBtnVisibleParam", "invisible");
        return "/product/formProduct";
    }
    @PostMapping(path = "/deleteProduct/{id}")
    public String processDeleteProduct(@ModelAttribute Product product) {
        //tu dopisać kod na sprawdzanie czy produkt nie jest w jakiejś transakcji
        //productRepository.delete(product);
        return "redirect:/product/showAllProducts";
    }
}
