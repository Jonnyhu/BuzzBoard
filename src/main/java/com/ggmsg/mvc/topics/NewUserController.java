/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ggmsg.mvc.topics;

import com.ggmsg.mvc.dao.LoginDAO;
import com.ggmsg.mvc.dao.LoginDAOImpl;
import com.ggmsg.mvc.dao.ProfileDAO;
import com.ggmsg.mvc.dao.ProfileDAOImpl;
import com.ggmsg.mvc.models.UserProfile;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/signup/*")
public class NewUserController {
    
    @RequestMapping(value="", method=RequestMethod.GET)
    public String newUserPage(Model model) {
        model.addAttribute("newuser", new UserProfile());
        
        //model.addAttribute("newuserpas", new UserProfile());
        return "signup/signup";
    }
     
    @RequestMapping(value="verify", method=RequestMethod.POST)
    public String verifyUser(@RequestParam("password")String password, @Valid UserProfile newuser, BindingResult result, HttpServletRequest request) {
        
        if(result.hasErrors()){
            FieldError fe = result.getFieldError();
            System.out.println(result.getFieldError().getField());
            return "redirect:/signup/";           
        }        
        
        LoginDAO loginDAO = new LoginDAOImpl();
        
        //Create user
        boolean success = loginDAO.createUser(newuser.getUsername(), password, 
                                  newuser.getPhonenumber(), newuser.getEmail());
        if (!success){
            return "redirect:/signup/";
        }
        
        //Fetch user
        UserProfile user = loginDAO.authenticate(newuser.getEmail(), password);                
        if (user != null){
            request.getSession().setAttribute("email", user.getEmail());
            request.getSession().setAttribute("username", user.getUsername());
            request.getSession().setAttribute("firstTime", "true");
            if (user.getPhonenumber() == null){
                request.getSession().setAttribute("phonenumber", "");
            }
            else{
                request.getSession().setAttribute("phonenumber", user.getPhonenumber());
            }
            request.getSession().setAttribute("id", user.getIduser());
            
            
            boolean isValid = false;
            PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
            try {
              PhoneNumber num = phoneUtil.parse(request.getSession().getAttribute("phonenumber").toString(), "CA");
              isValid = phoneUtil.isValidNumber(num);
            } catch (NumberParseException e) {
              System.err.println("NumberParseException was thrown: " + e.toString());
            }
            
            if (isValid){
               ProfileDAO pdao = new ProfileDAOImpl ();
               pdao.verifyUserPhone(user.getUsername());
            }

            return "redirect:/profile/" + user.getUsername();
        }
        //Failed to login
        return "redirect:/login";
    }
    
}
