package com.ggmsg.mvc.topics;

import com.ggmsg.mvc.dao.TopicsDAOImpl;
import com.ggmsg.mvc.dao.ReplyDAOImpl;
import com.ggmsg.mvc.dao.SubscribeDAO;
import com.ggmsg.mvc.dao.SubscribeDAOImpl;
import com.ggmsg.mvc.models.Reply;
import com.ggmsg.mvc.models.Topic;
import com.rometools.utils.Strings;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import com.ggmsg.mvc.notification.NotificationManager;
import com.ggmsg.mvc.notification.TwilioBuilder;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/topics/*")
public class TopicsController {

    @RequestMapping(value="", method=RequestMethod.GET)
    public String viewTopics(Model model) {

        TopicsDAOImpl td = new TopicsDAOImpl();
        List<Topic> topics = td.listAllTopics();

        model.addAttribute("topics", topics);

        return "topics/topics";
    }

    @RequestMapping(value="/{topicid}", method=RequestMethod.GET)
    @Scope("session")
    public String viewTopicById(Model model, @PathVariable String topicid, HttpServletRequest request) {

        TopicsDAOImpl td = new TopicsDAOImpl();
        Topic topic = td.getTopic(topicid);

        model.addAttribute("topic", topic);

        ReplyDAOImpl rd = new ReplyDAOImpl();
        List<Reply> replies = rd.getAllResponse(topicid);
        model.addAttribute("replies", replies);
        
        SubscribeDAOImpl sd = new SubscribeDAOImpl();

        try{
            String uid = request.getSession().getAttribute("id").toString();
            Boolean isSubscribed = sd.isSubscribed(uid, topicid);
            Boolean isUserSubscribed = sd.isUserSubscribed(uid, topic.getAuthor());
            model.addAttribute("isSubscribed", isSubscribed);
            model.addAttribute("isUserSubscribed", isUserSubscribed);
        }
        catch (Exception e){
            model.addAttribute("isSubscribed", "false");
            model.addAttribute("isUserSubscribed", "false");
        }
        return "topics/topic";
    }
    
    @RequestMapping(value="/edit/{topicid}", method=RequestMethod.GET)
    @Scope("session")
    public String editById(Model model, @PathVariable String topicid, HttpServletRequest request) {

        TopicsDAOImpl td = new TopicsDAOImpl();
        Topic topic = td.getTopic(topicid);
        String username = request.getSession().getAttribute("username").toString();
        model.addAttribute("topic", topic);
         if (topic.getAuthor().equals(username)) {
            return "topics/edit";
         } else {
            return "redirect:/topics/" + topicid;
         }

    }
    
    @RequestMapping(value="/edit/{topicid}", method=RequestMethod.POST)
    @Scope("session")
    public String editByIdPost(Model model, @PathVariable String topicid,  @RequestParam("title") String new_title,
            @RequestParam("content") String new_body, HttpServletRequest request) {

        String uid = request.getSession().getAttribute("id").toString();
        String username = request.getSession().getAttribute("username").toString();
        if (uid != null) {
            // if the author is the logged in user
            TopicsDAOImpl td = new TopicsDAOImpl();
            
            Topic topic = td.getTopic(topicid);
            if (topic.getAuthor().equals(username)) {
                td.updateTopic(topicid, new_title, new_body);
                
            }
            
        }
        
        return "redirect:/topics/" + topicid;
    }

    @RequestMapping(value="/reply", method=RequestMethod.POST)
    public String reply(
            @RequestParam("content") String content,
            @RequestParam("author") String author,
            @RequestParam("topicId") String topic_id,
            HttpServletRequest request
    ) {

        ReplyDAOImpl replyDAO = new ReplyDAOImpl();
        replyDAO.postResponse(author, content, topic_id);
        
        String id = request.getSession().getAttribute("id").toString();
        
        SubscribeDAO subscribeDao = new SubscribeDAOImpl();
        List <String> subscribersPhones = subscribeDao.getSubscribers(id, topic_id.toString());
        String linkToTopic = "http://buzzboard.elasticbeanstalk.com/topics/" + topic_id;
        
        if (!subscribersPhones.isEmpty()){
            for(String phonenumber : subscribersPhones){
                TwilioBuilder twilio = NotificationManager.getTwilioBuilder();
                twilio.AddRecepient(phonenumber);
                twilio.AddMessage("Hello there from BuzzBoard! There is a new post in topic " + topic_id + ": " + content + " " + linkToTopic);
                twilio.sendSMS();            
            }
        }
        
        return "redirect:/topics/" + topic_id;
    }
    
    @RequestMapping(value="/subscribe", method=RequestMethod.POST)
    public String subscribe(
            @RequestParam("topicId") String topic_id,
            @RequestParam("userId") String user_id,
            @RequestParam("action") String action
    ) {
        
        SubscribeDAOImpl rd = new SubscribeDAOImpl();
        if (action.equals("true")){
            rd.removeSub (user_id, topic_id);
        } else {
            rd.addSub (user_id, topic_id);
        }

        return "redirect:/topics/" + topic_id;
    }
    
    @RequestMapping(value="/subscribeUser", method=RequestMethod.POST)
    public String subscribeToUser(
            @RequestParam("userId") String user_id,
            @RequestParam("author") String author,
            @RequestParam("topicId") String topic_id,
            @RequestParam("action") String action
    ) {
        SubscribeDAOImpl rd = new SubscribeDAOImpl();
        if (action.equals("true")){
            rd.unsubToUser(user_id, author);
        }
        else {
            rd.subToUser(user_id, author);
        }
        
        return "redirect:/topics/" + topic_id;
    }
    
    @RequestMapping(value="/delete", method=RequestMethod.POST)
    public String deleteTopic(
            @RequestParam("topicId") String topic_id
    ) {
        TopicsDAOImpl rd = new TopicsDAOImpl();
        rd.updateTopicState(topic_id, "HIDDEN");
        
        return "redirect:/topics/";
    }
    
    @RequestMapping(value="/close", method=RequestMethod.POST)
    public String closeTopic(
            @RequestParam("topicId") String topic_id
    ) {
        TopicsDAOImpl rd = new TopicsDAOImpl();
        rd.updateTopicState(topic_id, "CLOSED");
        return "redirect:/topics/" + topic_id;
    }

    
    @RequestMapping(value="/searchresult*", method=RequestMethod.GET)
    public String search(Model model,
            @RequestParam("topickey") String topickey      
    ) {
        TopicsDAOImpl td = new TopicsDAOImpl();
        List<Topic> topics = td.searchTopics(topickey);
         

        model.addAttribute("topics", topics);

        return "topics/topics";
    }

}