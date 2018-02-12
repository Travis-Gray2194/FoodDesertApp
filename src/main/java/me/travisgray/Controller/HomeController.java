package me.travisgray.Controller;

import com.sun.org.apache.xpath.internal.operations.Bool;
import me.travisgray.Models.User;
import me.travisgray.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

/**
 * Created by ${TravisGray} on 11/13/2017.
 */

@Controller
public class HomeController {

    @Autowired
    UserService userService;

    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/admin")
    public String admin(){
        return "admin";
    }

    @GetMapping("/register")
    public String showRegistrationPage(Model model){
        model.addAttribute("user",new User());
        return "registration";
    }

    @PostMapping("/register")
    public String processregistration(@Valid @ModelAttribute("user") User user, BindingResult result, Model model ){

        model.addAttribute("user",user);
        if(result.hasErrors()){
            return "registration";
        }else{
            userService.saveUser(user);
            model.addAttribute("message","User Account Successfully Created");
        }
        return "index";
    }


    @RequestMapping("/secure")
    public String secure(HttpServletRequest request, Authentication authentication, Principal principal) {
        Boolean isAdmin = request.isUserInRole("ADMIN");
        Boolean isUSer = request.isUserInRole("USER");
        UserDetails userDetails = (UserDetails)
                authentication.getPrincipal();
        String username = principal.getName();
        return "secure";
    }
}
