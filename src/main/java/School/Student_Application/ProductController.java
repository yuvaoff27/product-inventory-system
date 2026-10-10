package School.Student_Application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("listProducts", repository.findAll());
        model.addAttribute("product", new Product()); 
        return "index";
    }

    @PostMapping("/saveProduct")
    public String saveProduct(@ModelAttribute("product") Product product) {
        repository.save(product); 
        return "redirect:/";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") int id, Model model) {
        Product product = repository.findById(id).orElse(new Product());
        model.addAttribute("product", product);
        model.addAttribute("listProducts", repository.findAll());
        return "index";
    }

    @GetMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable(value = "id") int id) {
        repository.deleteById(id);
        return "redirect:/";
    }
    // Map custom security access landing pages router endpoint views
    @GetMapping("/login")
    public String showCustomLoginPage() {
        return "login"; // Returns the clean, beautiful login.html template layout view
    }

}
