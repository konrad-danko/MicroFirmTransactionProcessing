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
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping(path = "/product")
public class ProductController {

    private final ProductRepository productRepository;
    private final TransItemRepository transItemRepository;
    private final DisplayParams displayParams;
    public ProductController(ProductRepository productRepository,
                             TransItemRepository transItemRepository,
                             DisplayParams displayParams) {
        this.productRepository = productRepository;
        this.transItemRepository = transItemRepository;
        this.displayParams = displayParams;
    }

    private User getLoggedUser(HttpServletRequest request){
        HttpSession session = request.getSession();
        return (User)session.getAttribute("loggedUser");
    }

    private void setFormattedCreatedAndUpdatedAsModelAttributes(Product product, Model model){
        long id = product.getId();
        Product productFromDB = productRepository.findById(id).orElse(null);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy, HH:mm:ss");
        if(productFromDB.getCreated()!=null){
            model.addAttribute("formattedCreated", formatter.format(productFromDB.getCreated()));
        }
        if(productFromDB.getUpdated()!=null){
            model.addAttribute("formattedUpdated", formatter.format(productFromDB.getUpdated()));
        }
    }

    @GetMapping(path = "/showAllProducts")
    public String showAllProducts(Model model) {
        model.addAttribute("allProducts", productRepository.findAll());
        return "/product/allProducts";
    }

    //show a product
    @GetMapping(path = "/showProduct/{id}")
    public String showProduct(Model model, @PathVariable long id) {
        Product product = productRepository.findById(id).orElse(null);
        setFormattedCreatedAndUpdatedAsModelAttributes(product, model);
        model.addAttribute("product", product);
        model.addAttribute("headerMessage", "Dane produktu");
        displayParams.setShowParams(model);
        return "/product/formProduct";
    }

    //add a product
    @GetMapping(path = "/addProduct")
    public String initiateAddProduct(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("headerMessage", "Dodaj nowy produkt");
        displayParams.setAddEditParams(model);
        return "/product/formProduct";
    }
    @PostMapping(path = "/addProduct")
    public String processAddProduct(@ModelAttribute @Valid Product product, BindingResult result, Model model, HttpServletRequest request) {
        if (result.hasErrors()) {
            model.addAttribute("headerMessage", "Dodaj nowy produkt");
            displayParams.setAddEditParams(model);
            return "/product/formProduct";
        }
        product.setCreatedByUser(getLoggedUser(request));
        productRepository.save(product);
        return "redirect:/product/showProduct/"+product.getId();
    }

    //edit a product
    @GetMapping(path = "/editProduct/{id}")
    public String initiateEditProduct(Model model, @PathVariable long id) {
        Product product = productRepository.findById(id).orElse(null);
        setFormattedCreatedAndUpdatedAsModelAttributes(product, model);
        model.addAttribute("product", product);
        model.addAttribute("headerMessage", "Edytuj dane produktu");
        displayParams.setAddEditParams(model);
        return "/product/formProduct";
    }
    @PostMapping(path = "/editProduct/{id}")
    public String processEditProduct(@ModelAttribute @Valid Product product, BindingResult result, Model model, HttpServletRequest request) {
        if (result.hasErrors()) {
            setFormattedCreatedAndUpdatedAsModelAttributes(product, model);
            model.addAttribute("headerMessage", "Edytuj dane produktu");
            displayParams.setAddEditParams(model);
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
        Product product = productRepository.findById(id).orElse(null);
        setFormattedCreatedAndUpdatedAsModelAttributes(product, model);
        model.addAttribute("product", product);
        model.addAttribute("headerMessage", "Potwierdź usunięcie produktu");
        displayParams.setDelParams(model);
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
