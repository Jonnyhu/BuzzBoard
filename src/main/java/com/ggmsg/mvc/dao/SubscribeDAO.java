/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ggmsg.mvc.dao;

import com.ggmsg.mvc.models.Topic;
import java.util.List;

/**
 *
 * @author jonny
 */
public interface SubscribeDAO {
    
    List<Topic> listSubscribtion(String userId);

    List<String> getSubscribers(String iduser, String idtopic);
    
    void addSub (String userId, String topicId);
    
    void removeSub (String userId, String topicId);
    
    boolean isSubscribed (String userId, String topicId);
    
    List<Topic> listTopicsForSubscribedUser(String userId);
    
    List<Topic> listUserTopics(String userName);
    
    void subToUser (String userId, String subToUserName);
    
    void unsubToUser (String userId, String subToUserName);
    
    boolean isUserSubscribed(String userId, String subToUserName);
    
    List<String> listUserForSubscribedAuthor(String author);

}
