/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ggmsg.mvc.home;
import com.ggmsg.mvc.dao.SubscribeDAOImpl;
import com.ggmsg.mvc.models.Topic;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class HomeController {
    
    @RequestMapping(value="", method=RequestMethod.GET)
    public String home(Model model, HttpServletRequest request) {

        Object uid = request.getSession().getAttribute("id");
        
        if (uid != null) {
            SubscribeDAOImpl td = new SubscribeDAOImpl();
            List<Topic> topics = td.listSubscribtion((String) uid.toString());

            model.addAttribute("subscribedTopics", topics);
            
            List<Topic> subsribedUserTopics = td.listTopicsForSubscribedUser(uid.toString());

            model.addAttribute("subsribedUserTopics", subsribedUserTopics);
            
            Object userName = request.getSession().getAttribute("username");
            List<Topic> userTopics = td.listUserTopics(userName.toString());
            model.addAttribute("myTopics", userTopics);
        }
                
        return "home";
    }
    
}
