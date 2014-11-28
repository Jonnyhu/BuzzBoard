/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ggmsg.mvc.topics;

import com.ggmsg.mvc.dao.ProfileDAO;
import com.ggmsg.mvc.models.UserProfile;
import com.ggmsg.mvc.dao.ProfileDAOImpl;
import com.ggmsg.mvc.dao.SubscribeDAO;
import com.ggmsg.mvc.dao.SubscribeDAOImpl;
import com.ggmsg.mvc.models.Topic;
import com.ggmsg.mvc.notification.NotificationManager;
import com.ggmsg.mvc.notification.TwilioBuilder;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author anton, alex
 */

@Controller
@RequestMapping("/profile/*")
public class ProfileController {
    
    
    
    @RequestMapping(value="", method=RequestMethod.GET)
    public String viewUserProfilePage(Model model, HttpServletRequest request){
        // if we go to /profile then REDIRECT the user to its own username
        // if you go to /profile with no session then REDIRECT to login.
        String username = (String) request.getSession().getAttribute("username");
        if (username != null) {
            return "redirect:/profile/" + username;
        } else {
            return "redirect:/login";
        }
    }
    
    @RequestMapping(value="/{username}", method=RequestMethod.GET)
    @Scope("session")
    public String viewUserProfilePage(Model model, 
                                        @PathVariable String username, 
                                        HttpServletRequest request
                                        )
    {
        ProfileDAOImpl upd = new ProfileDAOImpl();
        UserProfile user = upd.getUserProfile(username);//.get(0);
        if (user == null){ //If requested user page doesnt exist
            return "redirect:/signup/";
        }
        model.addAttribute("User", user);
        
        Object userIdObj = request.getSession().getAttribute("id");
        
        SubscribeDAO sdao = new SubscribeDAOImpl();
        List<Topic> topics = sdao.listUserTopics(username);
        model.addAttribute("topics", topics);  
                
        if (userIdObj == null){  //User is not logged in  
            model.addAttribute("dontShowPhones", 1);
            return "user/profile";
        }
        
        //Display if this user's phone is invalid
        //on his/her page only
        long uid = Long.parseLong(userIdObj.toString());
        if (user.getPhone_verified() == 0 && uid == user.getIduser()){
            model.addAttribute("InvalidPhoneNumber", 1);
        }        
        
        //If this user has verified his/her phone
        //then display other users numbers
        String myusername = request.getSession().getAttribute("username").toString();
        UserProfile myprofile = upd.getUserProfile(myusername);
        if (myprofile.getPhone_verified() == 0 && uid != user.getIduser()){
            model.addAttribute("dontShowPhones", 1);      
            
        }
        
        return "user/profile";
    }
    
    
    //@RequestMapping(value="/{username}", method=RequestMethod.GET)
    //@Scope("session")
    public String viewUserProfilePage_Deprecated(Model model, 
                                        @PathVariable String username, 
                                        HttpServletRequest request
                                        )
    {
        ProfileDAOImpl upd = new ProfileDAOImpl();
        UserProfile user = upd.getUserProfile(username);//.get(0);
        if (user == null){ //If requested user page doesnt exist
            return "redirect:/signup/";
        }
        model.addAttribute("User", user);
        
        Object userIdObj = request.getSession().getAttribute("id");
                
        if (userIdObj == null){  //User is not logged in             
            return "user/profile";
        }
        
        
        TwilioBuilder twb = NotificationManager.getTwilioBuilder();
        String validationCode; 
        
        Object firstTime = request.getSession().getAttribute("firstTime");
        
        if (firstTime != null){
            
            request.getSession().setAttribute("firstTime", null);
            
            validationCode = twb.VerifyUserPhoneNumber(user.getPhonenumber(), user.getUsername());

            if (validationCode == null){
                model.addAttribute("InvalidPhoneNumber", 1);
            }
            else {                
                model.addAttribute("ShowValidationCodeMsg", "Use this code to verify phone number: " + validationCode);                
            }
                        
        }
        else{
            //long uid = Long.parseLong(request.getSession().getAttribute("id").toString());
            
            long uid = Long.parseLong(userIdObj.toString());
            
            if (user.getPhone_verified() != 1 && uid == user.getIduser()){
                
                model.addAttribute("ValidationButton", 1);
                
            }
            else{
                
                validationCode = twb.VerifyUserPhoneNumber(user.getPhonenumber(), user.getUsername());
                
                if (validationCode == null){
                    
                    model.addAttribute("ValidationButton", 1); 
                    //Update user profile 
                    ProfileDAO pdao = new ProfileDAOImpl();
                    pdao.verifyUserPhone(username);

                }
                else if(validationCode.equals("ALREADYVERIFIED")) {                    
                    //Do nothing
                }
                else{
                    //New validationCode
                }
            }
            
        }
        
        return "user/profile";
    }
    
    
    
    
    @RequestMapping(value="/phone/{userphone}", method=RequestMethod.GET, headers="Accept=application/json")
    @Scope("session")
    public @ResponseBody JsonValidationCode ajaxValidateNumber(Model model, 
                                        @PathVariable String userphone, 
                                        HttpServletRequest request                                        
                                        )
    {
        String username = (String) request.getSession().getAttribute("username");
        String phonenumber = (String) request.getSession().getAttribute("phonenumber");
        
        
        TwilioBuilder twb = NotificationManager.getTwilioBuilder();
        String validationCode = null;//twb.VerifyUserPhoneNumber(phonenumber, username);
        
        System.out.println(validationCode);
        
        JsonValidationCode jvc;
        if (validationCode == null){
            jvc = new JsonValidationCode("Your Number Could Not Be Verified. Change It And Try Again.");
        }        
        else{        
            jvc = new JsonValidationCode("Use this code to verify phone number: " + validationCode);
        }          
        
        return jvc;
    }
    
    @RequestMapping(value="/edit/{username}", method=RequestMethod.GET)
    public String viewEditUserProfile(Model model, @PathVariable String username, HttpServletRequest request) {
        ProfileDAOImpl upd = new ProfileDAOImpl();
        UserProfile user = upd.getUserProfile(username);
        model.addAttribute("User", user);
        
        return "user/editprofile";
    }

        
    @RequestMapping(value="/edit/{username}", method=RequestMethod.POST)
    public String editUserProfile(Model model, @ModelAttribute UserProfile up,
                                  @PathVariable String username, HttpServletRequest request) {
        ProfileDAOImpl upd = new ProfileDAOImpl();
        UserProfile oldUser = upd.getUserProfile(username);
        
        String test;
        test = "";
        int modpass;
        
        if (!up.getPassword().equals(up.getCpass())){
            String error = "Confirm Password field does not match Password field.";
            model.addAttribute("CPassError", error);
            model.addAttribute("User",oldUser);
        return "user/editprofile";
        }
        else if(up.getPassword().equals(test) && up.getCpass().equals(test)){
            up.setPassword(oldUser.getPassword());
            modpass = 0;
        }
        else{
            modpass = 1;
        }
        up.setCpass(null);
        
        if(up.getEmail().equals(test)){
            up.setEmail(oldUser.getEmail());
    }
    
        if(up.getPhonenumber().equals(test)){
            up.setPhonenumber(oldUser.getPhonenumber());
        }
        
            
        boolean isValid = false;
        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
        try {
          Phonenumber.PhoneNumber num = phoneUtil.parse(up.getPhonenumber(), "CA");
          isValid = phoneUtil.isValidNumber(num);
        } catch (NumberParseException e) {
          System.err.println("NumberParseException was thrown: " + e.toString());
        }

        if (isValid){               
           upd.verifyUserPhone(oldUser.getUsername());
        }
        else{
            upd.unVerifyUserPhone(oldUser.getUsername());
        }

    
        upd.modifyUserProfile(username, modpass, up);
        model.addAttribute("User",up);
                
        return "redirect:/profile/";
        
    }
}
