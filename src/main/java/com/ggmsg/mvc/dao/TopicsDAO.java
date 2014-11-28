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
 * @author anton
 */
public interface TopicsDAO {

    void createTopic(String title, String body, String author);
    
    public List<Topic> searchTopics(String key);

    List<Topic> listAllTopics();
    
    Topic getTopic(String id);
    
    void updateTopic(String id, String title, String body);
    
    void updateTopicState(String id, String state);
    
}
