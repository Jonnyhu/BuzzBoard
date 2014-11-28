
package com.ggmsg.mvc.dao;


import com.ggmsg.mvc.models.Topic;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.ggmsg.mvc.dao.SessionUtil;

@Repository
public class TopicsDAOImpl implements TopicsDAO {
    
        
    @Override
    public List<Topic> listAllTopics() {
        
        JdbcTemplate db = SessionUtilImpl.getSession();
        
        //newest to oldest
        String query = "SELECT * FROM topic WHERE state != \"HIDDEN\" ORDER BY timestamp DESC";
        
        List<Topic> topics = db.query(query, new BeanPropertyRowMapper(Topic.class));
        
        return topics;
    }
    
    
    @Override
    public void createTopic(String title, String body, String author) {
        JdbcTemplate jdbcTemplate = SessionUtilImpl.getSession();
        
        String query = "INSERT INTO topic (title, author, content, timestamp)" +
                "VALUE (?, ?, ?, curtime())";
        
        jdbcTemplate.update(query, new Object[] {title, author, body});
    }


    
    @Override
    public List<Topic> searchTopics(String key) {
        JdbcTemplate db = SessionUtilImpl.getSession();
        
        String query = "SELECT * FROM topic WHERE (title LIKE '%"+key+"%' OR author LIKE '%"+
                key+"%') AND state != \"HIDDEN\" ORDER BY timestamp DESC";
        
        List<Topic> result = db.query(query, new BeanPropertyRowMapper(Topic.class));
        
        return result;
    }

    @Override
    public Topic getTopic(String id) {
        JdbcTemplate jdbcTemplate = SessionUtilImpl.getSession();
        
        String query = "SELECT * FROM topic"
                     + " WHERE id = ?"
                     + " AND state != \"HIDDEN\"";

        Topic aTopic = (Topic) jdbcTemplate.queryForObject(query, new Object[] 
        { Integer.parseInt(id) }, new BeanPropertyRowMapper(Topic.class));
        
        return aTopic;
    }

    @Override
    public void updateTopic(String id, String title, String body) {
        JdbcTemplate db = SessionUtilImpl.getSession();
        
        String query = "UPDATE topic SET \n" +
                       "title = ?,\n" +
                       "content = ?\n" +
                       "WHERE id = ?";
        
        db.update(query, new Object[] {title, body, id});
    }

    @Override
    public void updateTopicState(String id, String state) {
        JdbcTemplate db = SessionUtilImpl.getSession();
        
        String query = "UPDATE topic SET \n" +
                       "state = ? \n" +
                       "WHERE id = ?";
        
        db.update(query, new Object[] {state, Integer.parseInt(id)});
    }
    
    

}