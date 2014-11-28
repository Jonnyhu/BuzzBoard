/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ggmsg.mvc.dao;

import com.ggmsg.mvc.models.Reply;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author jonny
 */
public class ReplyDAOImpl implements ReplyDAO{

    @Override
    public void postResponse(String author, String content, String topic_id) {
         JdbcTemplate db = SessionUtilImpl.getSession();
         
         String query = "INSERT INTO reply (author, content, timestamp, topic_id)\n" +
                        "VALUE (?, ?, curtime(), ?)";
         
         db.update(query, author, content, Integer.parseInt(topic_id));
    }

    @Override
    public Reply getResponse(String response_id) {
        JdbcTemplate db = SessionUtilImpl.getSession();
        
        String query = "SELECT * FROM reply "
                     + "WHERE id = ?";
        
        Reply aReply = (Reply) db.queryForObject(query, 
                new Object[] { Integer.parseInt(response_id) }, 
                new BeanPropertyRowMapper(Reply.class));
        
        return aReply;
    }

    @Override
    public List<Reply> getAllResponse(String topic_id) {
        JdbcTemplate db = SessionUtilImpl.getSession();
        
        String query = "SELECT * FROM reply "
                     + "WHERE topic_id = ?";
                
        List<Reply> replys = db.query(query, 
                new Object[] { Integer.parseInt(topic_id) },
                new BeanPropertyRowMapper(Reply.class));
        
        return replys;
    }
    
}
