/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ggmsg.mvc.dao;

import com.ggmsg.mvc.models.Reply;
import java.util.List;

/**
 *
 * @author jonny
 */
public interface ReplyDAO {
    void postResponse (String author, String content, String topic_id);
    
    Reply getResponse (String response_id);
    
    List<Reply> getAllResponse (String topic_id);
}
