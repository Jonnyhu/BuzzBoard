package com.ggmsg.mvc.logout;

import com.ggmsg.mvc.models.UserProfile;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/logout")
public class LogoutController {
    
    
    @RequestMapping(value="", method=RequestMethod.GET)
    public String logout(Model model, HttpServletRequest request) {
        request.getSession().setAttribute("email", null);
        request.getSession().setAttribute("username", null);
        request.getSession().setAttribute("id", null);
        
        return "redirect:/";
    }
    
}




