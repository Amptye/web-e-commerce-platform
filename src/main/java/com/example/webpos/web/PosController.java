package com.example.webpos.web;

import com.example.webpos.model.Product;
import com.example.webpos.model.User;
import com.example.webpos.repo.ItemRepository;
import com.example.webpos.repo.ProductRepository;
import com.example.webpos.repo.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.example.webpos.biz.PosService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@RestController
@CrossOrigin(exposedHeaders = "errors, content-type")
@RequestMapping("/")
public class PosController {

    @Value("#{servletContext.contextPath}")
    private String servletContextPath;

    @RequestMapping(value = "/")
    public void redirectToSwagger(HttpServletResponse response) throws IOException {
//        response.sendRedirect("../../../client/dist/index.html");
        response.sendRedirect(this.servletContextPath + "/swagger-ui/index.html");
    }

}

//@RestController
//public class PosController {
//
//    private PosService posService;
//    private final UserRepository userRepository;
//    private final ProductRepository productRepository;
//    private final ItemRepository itemRepository;
//    private final CartRepository cartRepository;
//    @Autowired
//    private HttpServletRequest request;
//
//    public PosController(UserRepository userRepository, ProductRepository productRepository,
//                         CartRepository cartRepository, ItemRepository itemRepository){
//        this.userRepository = userRepository;
//        this.productRepository = productRepository;
//        this.cartRepository = cartRepository;
//        this.itemRepository = itemRepository;
//    }
//
//    @Autowired
//    public void setPosService(PosService posService) {
//        this.posService = posService;
//    }
//
//    private void add_into_Model(Model model){
//        model.addAttribute("cart", posService.getCart());
//        model.addAttribute("tax", posService.getTax());
//        model.addAttribute("discount", posService.getDiscount());
//        model.addAttribute("total",posService.total(posService.getCart()));
//    }
//
//    @GetMapping("/")
//    public String first(){
//        return "redirect:/login";
//    }
//    @GetMapping("/login")
//    public String login(){
//        return "login";
//    }
//    @GetMapping("/register")
//    public String register(){
//        return "register";
//    }
//
//    @GetMapping("/index")
//    public String pos(@RequestParam("id") long id, Model model) {
//        posService.setTax(0.12);
//        model.addAttribute("products", posService.products());
//        model.addAttribute("user_id", id);
//        User user = userRepository.findById(id).orElse(null);
//        double balance = 0;
//        if(user != null){
//            balance = user.getMoney();
//        }
//        model.addAttribute("balance",balance);
////        model.addAttribute("cart", posService.getCart());
////        model.addAttribute("tax", posService.getTax());
////        model.addAttribute("discount", posService.getDiscount());
////        model.addAttribute("total",posService.total(posService.getCart()));
//        add_into_Model(model);
//        return "index";
//    }
//
////
////    public String products() {
////        StringBuilder stringBuilder = new StringBuilder();
////        int i = 0;
////        for (Product product : posService.products()) {
////            stringBuilder.append("\t").append(++i).append("\t").append(product).append("\n");
////        }
////        return stringBuilder.toString();
////    }
//
////
////    public String newCart() {
////        return posService.newCart() + " OK";
////    }
//
//    @GetMapping("/add-item-quantity")
//    public String addQuanity(@RequestParam("index") int index){
//        posService.addItemQuanity(index, 1);
//        return "redirect:" + request.getHeader("referer");
//    }
//
//    @GetMapping("/sub-item-quantity")
//    public String subQuanity(@RequestParam("index") int index){
//        posService.subItemQuanity(index, 1);
//        return "redirect:" + request.getHeader("referer");
//    }
//
//    @GetMapping("/add-product")
//    public String addToCart(@RequestParam("id") long productId) {
//        if (posService.add(productId, 1)) {
//            return "redirect:" + request.getHeader("referer");
//        }
//        return "redirect:" + request.getHeader("referer");
//    }
//
//    @GetMapping("/combine-by-index")
//    public String combineItems(int a, int b){
//        if(posService.combineItems(a, b)) {
//            return "redirect:" + request.getHeader("referer");
//        }
//        return "redirect:" + request.getHeader("referer");
//    }
//
//    @GetMapping("/combine-by-id")
//    public String combineIdItems(long productId){
//        if(posService.combineIdItems(productId)) {
//            return "redirect:" + request.getHeader("referer");
//        }
//        return "redirect:" + request.getHeader("referer");
//    }
//
//    @GetMapping("/combine-all")
//    public String combineAllItems(){
//        posService.combineAllItems();
//        return "redirect:" + request.getHeader("referer");
//    }
//
//    @GetMapping("/charge")
//    public String checkOut(@RequestParam("id") long id){
//        User user = userRepository.findById(id).orElse(null);
//        if(user == null){
//            return "redirect:" + request.getHeader("referer");
//        }
//        double total = posService.total(posService.getCart()) * (1- posService.getDiscount()) * (1+ posService.getTax());
//        if(total <= user.getMoney()) {
//            posService.checkout(posService.getCart());
//            user.subMoney(total);
//        }
//        userRepository.save(user);
//        return "redirect:" + request.getHeader("referer");
//    }
//
//    @GetMapping("/print-cart")
//    public String printCart(){
//        return "redirect:" + request.getHeader("referer");
//    }
//
//    @GetMapping("/remove-by-index")
//    public String removeItem(@RequestParam int index){
//        if(posService.removeItem(index)){
//            return "redirect:" + request.getHeader("referer");
//        }
//        return "redirect:" + request.getHeader("referer");
//    }
//
//    @GetMapping("/remove-by-id")
//    public String removeIdItems(long productId){
//        if(posService.removeIdItems(productId)) {
//            return "redirect:" + request.getHeader("referer");
//        }
//        return "redirect:" + request.getHeader("referer");
//    }
//
//    @GetMapping("/clear-cart")
//    public String clearCart(){
//        posService.clearCart();
//        return "redirect:" + request.getHeader("referer");
//    }
//
//    @GetMapping("/search")
//    public String searchProduct(@RequestParam("id") long id, @RequestParam("search") String search, Model model){
//        model.addAttribute("products", posService.search_products(search));
//        model.addAttribute("user_id", id);
//        User user = userRepository.findById(id).orElse(null);
//        double balance = 0;
//        if(user != null){
//            balance = user.getMoney();
//        }
//        model.addAttribute("balance",balance);
////        model.addAttribute("cart", posService.getCart());
////        model.addAttribute("tax", posService.getTax());
////        model.addAttribute("discount", posService.getDiscount());
////        model.addAttribute("total",posService.total(posService.getCart()));
//        add_into_Model(model);
//        return "index";
//    }
//
//    @GetMapping("/quick-view")
//    public String quickView(@RequestParam("id") long id, Model model){
//        model.addAttribute("view_product",posService.find_product_by_id(id));
//        return "quick_view";
//    }
//
//    @GetMapping("/edit/{id}")
//    public String showUpdateForm(@PathVariable("id") long id, Model model) {
//        User user = userRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
//
//        model.addAttribute("user", user);
//        return "update-user";
//    }
//    @PostMapping("/update/{id}")
//    public String updateUser(@PathVariable("id") long id, @Valid User user,
//                             BindingResult result, Model model) {
//        if (result.hasErrors()) {
//            user.setId(id);
//            return "update-user";
//        }
//
//        userRepository.save(user);
//        return "redirect:" + request.getHeader("referer");
//    }
//
//    @GetMapping("/delete/{id}")
//    public String deleteUser(@PathVariable("id") long id, Model model) {
//        User user = userRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
//        userRepository.delete(user);
//        return "redirect:" + request.getHeader("referer");
//    }
//
//    @PostMapping("/login-user")
//    public String loginUser(@RequestParam("id") long id, @RequestParam("pass") String pass,
//                            RedirectAttributes redirectAttributes, Model model){
//        User user = userRepository.findById(id)
//                .orElse(null);
//        if(user != null) {
//            if (pass.equals(user.getPass())) {
//                redirectAttributes.addAttribute("id", user.getId());
//                return "redirect:/index";
//            }
//        }
//        return "redirect:" + request.getHeader("referer");
//    }
//    @PostMapping("/register-user")
//    public String registerUser(@RequestParam("name") String name, @RequestParam("email") String email,
//                               @RequestParam("pass") String pass, RedirectAttributes redirectAttributes) {
//        User user = new User(name, email, pass);
//
//        userRepository.save(user);
//        redirectAttributes.addAttribute("id", user.getId());
//        return "redirect:/register-ok";
//    }
//
//    @GetMapping("/register-ok")
//    public String registerOk(@RequestParam("id") long id, Model model){
//        User user = userRepository.findById(id).orElse(null);
//        if(user == null) return "redirect:" + request.getHeader("referer");
//        model.addAttribute("user_id", id);
//        return "register_ok";
//    }
//
//    @GetMapping("/message")
//    public String message(@RequestParam("id") long id, Model model){
//        User user = userRepository.findById(id).orElse(null);
//        if(user == null){
//            return "redirect:" + request.getHeader("referer");
//        }
//        model.addAttribute("now_user", user);
//        return "message";
//    }
//
//    @PostMapping("/change-name")
//    public String changeName(@RequestParam("id") long id, @RequestParam("new_name") String new_name,
//                             RedirectAttributes redirectAttributes){
//        User user = userRepository.findById(id).orElse(null);
//        if(user == null){
//            return "redirect:" + request.getHeader("referer");
//        }
//        user.setName(new_name);
//        userRepository.save(user);
//        redirectAttributes.addAttribute("id", user.getId());
//        return "redirect:/message";
//    }
//    @PostMapping("/change-email")
//    public String changeEmail(@RequestParam("id") long id, @RequestParam("new_email") String new_email,
//                              RedirectAttributes redirectAttributes){
//        User user = userRepository.findById(id).orElse(null);
//        if(user == null){
//            return "redirect:" + request.getHeader("referer");
//        }
//        user.setEmail(new_email);
//        userRepository.save(user);
//        redirectAttributes.addAttribute("id", user.getId());
//        return "redirect:/message";
//    }
//    @PostMapping("/change-password")
//    public String changePass(@RequestParam("id") long id, @RequestParam("old_pass") String old_pass,
//                             @RequestParam("new_pass") String new_pass, RedirectAttributes redirectAttributes){
//        User user = userRepository.findById(id).orElse(null);
//        if(user == null || (!old_pass.equals(user.getPass()))){
//            return "redirect:" + request.getHeader("referer");
//        }
//        user.setPass(new_pass);
//        userRepository.save(user);
//        redirectAttributes.addAttribute("id", user.getId());
//        return "redirect:/message";
//    }
//
//    @PostMapping("/add-money")
//    public String addMoney(@RequestParam("id") long id, @RequestParam("money") double money,
//                           RedirectAttributes redirectAttributes){
//        User user = userRepository.findById(id).orElse(null);
//        if(user == null){
//            return "redirect:" + request.getHeader("referer");
//        }
//        user.addMoney(money);
//        userRepository.save(user);
//        redirectAttributes.addAttribute("id", user.getId());
//        return "redirect:/message";
//    }
//}
