/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ggmsg.mvc.forgot;

import com.ggmsg.mvc.notification.TwilioBuilder;
import com.ggmsg.mvc.dao.LoginDAO;
import com.ggmsg.mvc.dao.LoginDAOImpl;
import com.ggmsg.mvc.models.LoginPage;
import com.ggmsg.mvc.models.UserProfile;
import javax.servlet.http.HttpServletRequest;
import com.ggmsg.mvc.notification.NotificationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Alex
 */
@Controller
@RequestMapping("/forgot")
public class ForgotHandler {
    
    
    @RequestMapping(value="", method=RequestMethod.GET)
    public String viewforgotPasswordPage(Model model, HttpServletRequest request) {        
        model.addAttribute("login", new LoginPage());
        return "forgot/forgotPassword";
    }
    
    @RequestMapping(value="", method=RequestMethod.POST)
    public String sendRecoveryPassword(@ModelAttribute LoginPage lp, Model model, HttpServletRequest request) {
        
        LoginDAO ld = new LoginDAOImpl();
        UserProfile user = ld.retrievePassword(lp.getEmail());
        
        if(user != null){
            TwilioBuilder twilio = NotificationManager.getTwilioBuilder();
            twilio.AddRecepient(user.getPhonenumber());
            twilio.AddMessage("Hello there from BuzzBoard! Here is your password: " + user.getPassword());
            twilio.sendSMS();            
            
            return "forgot/forgotApprove";
        }
        String forgoterror = "Invalid Username";
        model.addAttribute("forgotError", forgoterror);
        return "forgot/forgotPassword";
    }
    
}
