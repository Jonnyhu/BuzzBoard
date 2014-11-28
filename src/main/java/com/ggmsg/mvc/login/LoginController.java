/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ggmsg.mvc.login;

import com.ggmsg.mvc.dao.LoginDAOImpl;
import com.ggmsg.mvc.models.LoginPage;
import com.ggmsg.mvc.models.UserProfile;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author alex
 */
@Controller
@RequestMapping("/login")
public class LoginController {
    
    @RequestMapping(value="", method=RequestMethod.GET)
    public String viewLogin(Model model) {
            
            model.addAttribute("login", new LoginPage());
            
            return "login";
    }
    
    @RequestMapping(value="", method=RequestMethod.POST)
    public String newLogin(@ModelAttribute LoginPage lp, Model model,  HttpServletRequest request) {
            LoginDAOImpl ld = new LoginDAOImpl();
            UserProfile user = ld.authenticate(lp.getEmail(), lp.getPassword());
            if (user != null){
                String em = user.getEmail();
                request.getSession().setAttribute("email", em);
                request.getSession().setAttribute("username", user.getUsername());
                if (user.getPhonenumber() == null){
                    request.getSession().setAttribute("phonenumber", "");
                }
                else{
                    request.getSession().setAttribute("phonenumber", user.getPhonenumber());
                }
                request.getSession().setAttribute("id", user.getIduser());
                
                return "redirect:/profile/" + user.getUsername();
            }
            else{
                String loginerror = "Invalid Email and/or Password";
                model.addAttribute("loginError", loginerror);
                return "/login";
            }
    }

}
