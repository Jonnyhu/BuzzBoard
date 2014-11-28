/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ggmsg.mvc.topics;

import com.ggmsg.mvc.dao.SubscribeDAO;
import com.ggmsg.mvc.dao.SubscribeDAOImpl;
import com.ggmsg.mvc.models.Topic;
import com.ggmsg.mvc.dao.TopicsDAOImpl;
import com.ggmsg.mvc.notification.NotificationManager;
import com.ggmsg.mvc.notification.TwilioBuilder;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/topics/*")
public class NewTopicController {
       
    @RequestMapping(value="new", method=RequestMethod.GET)
    public String newTopicView(Model model) {
            model.addAttribute("topic", new Topic());
        
            return "topics/new";
    }
    
    @RequestMapping(value="new", method=RequestMethod.POST)
    public String newTopic(HttpServletRequest request,
                           @ModelAttribute Topic topic, Model model) {
            TopicsDAOImpl t = new TopicsDAOImpl();
            t.createTopic(topic.getTitle(), topic.getContent(), topic.getAuthor());        
        
            SubscribeDAO subscribeDao = new SubscribeDAOImpl();
            List <String> subscribersPhones = subscribeDao.listUserForSubscribedAuthor(topic.getAuthor());
            String linkToTopic = "http://buzzboard.elasticbeanstalk.com/profile/" + topic.getAuthor();
            if (!subscribersPhones.isEmpty()){
                for(String phonenumber : subscribersPhones){
                    TwilioBuilder twilio = NotificationManager.getTwilioBuilder();
                    twilio.AddRecepient(phonenumber);
                    twilio.AddMessage("From BuzzBoard!"
                                      + topic.getAuthor() + 
                                      " posted a new topic: " + topic.getTitle()
                                      + " " + linkToTopic);
                    twilio.sendSMS();            
                }
            }
            return "redirect:/topics/";
    }
}
