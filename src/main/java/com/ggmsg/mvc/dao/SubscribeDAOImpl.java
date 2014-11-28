/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ggmsg.mvc.dao;

import com.ggmsg.mvc.models.Topic;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author jonny
 */
public class SubscribeDAOImpl implements SubscribeDAO {

    @Override
    public List<Topic> listSubscribtion(String userId) {
        JdbcTemplate db = SessionUtilImpl.getSession();
        
        //newest to oldest
        String query = "SELECT topic.id AS id, \n" +
                       "	   topic.title AS title, \n" +
                       "	   topic.author AS author, \n" +
                       "	   topic.content AS content, \n" +
                       "	   topic.timestamp AS timestamp\n" +
                       "FROM topic, subscription, user_profile\n" +
                       "WHERE subscription.iduser = ?\n" +
                       "AND topic.state != \"HIDDEN\"\n" +
                       "AND subscription.iduser = user_profile.iduser\n" +
                       "AND topic.id = subscription.idtopic\n" +
                       "ORDER BY timestamp DESC";
        
        List<Topic> subTopics = db.query(query, 
                                new Object[] { Integer.parseInt(userId) },
                                new BeanPropertyRowMapper(Topic.class));
        
        return subTopics;
    }

    @Override
    public void addSub(String userId, String topicId) {
        JdbcTemplate db = SessionUtilImpl.getSession();
        
        String query = "INSERT INTO subscription (iduser, idtopic)\n" +
                       "VALUE (?, ?)";
        
        db.update(query, Integer.parseInt(userId), Integer.parseInt(topicId));
    }

    @Override
    public void removeSub(String userId, String topicId) {
        JdbcTemplate db = SessionUtilImpl.getSession();
        
        String query = "DELETE FROM subscription\n" +
                       "WHERE iduser = ?\n" +
                       "AND idtopic = ?";
        
        db.update(query, Integer.parseInt(userId), Integer.parseInt(topicId));
    }

    @Override
    public boolean isSubscribed (String userId, String topicId) {
        JdbcTemplate db = SessionUtilImpl.getSession();
        
        String query = "SELECT idsubscription FROM subscription\n" +
                       "WHERE iduser = ?\n" +
                       "AND idtopic = ?";
        
       try{
            int id = db.queryForObject(query, 
                    new Object[] { Integer.parseInt(userId),
                        Integer.parseInt(topicId) },
                    Integer.class);
        }
        catch (NumberFormatException | DataAccessException e){
            return false;
        }
        
        return true;
    }
    
    @Override
    public List<String> getSubscribers(String iduser, String idtopic){
        JdbcTemplate db = SessionUtilImpl.getSession();
        
        String query = "SELECT user_profile.phonenumber  \n" +
                        " FROM subscription, user_profile \n" +
                        " WHERE subscription.iduser != ? \n" +
                        " AND subscription.idtopic = ?\n" +
                        " AND user_profile.iduser = subscription.iduser";
        
        List<String> subIds = db.queryForList(query, new Object[] {iduser, idtopic}, String.class);
                
        return subIds;
        
    }

    @Override
    public List<Topic> listTopicsForSubscribedUser(String userId) {
        JdbcTemplate db = SessionUtilImpl.getSession();
        
        String query = "SELECT topic.id AS id,\n" +
                        "		topic.title AS title,\n" +
                        "		topic.author AS author,\n" +
                        "		topic.content AS content,\n" +
                        "		topic.timestamp AS timestamp\n" +
                        "FROM topic, user_sub, user_profile\n" +
                        "WHERE user_sub.cur_user = ?\n" +
                        "AND topic.state != \"HIDDEN\"\n" +
                        "AND user_sub.sub_to = user_profile.iduser\n" +
                        "AND topic.author = user_profile.username\n" +
                        "ORDER BY timestamp DESC";
        
        List<Topic> subTopics = db.query(query, 
                                new Object[] { Integer.parseInt(userId)},
                                new BeanPropertyRowMapper(Topic.class));
        
        return subTopics;
    }

    @Override
    public void subToUser(String userId, String subToUserName) {
        JdbcTemplate db = SessionUtilImpl.getSession();
        
        String query = "INSERT INTO user_sub (cur_user, sub_to)\n" +
                       "SELECT ? as cur_user, iduser as sub_to\n" +
                       "FROM user_profile\n" +
                       "WHERE username = ?";
        
        db.update(query, Integer.parseInt(userId), subToUserName);
    }

    @Override
    public void unsubToUser(String userId, String subToUserName) {
        JdbcTemplate db = SessionUtilImpl.getSession();
        
        String query = "DELETE FROM user_sub\n" +
                       "WHERE (cur_user, sub_to) in (\n" +
                       "SELECT ? as cur_user, iduser as sub_to\n" +
                       "FROM user_profile\n" +
                       "WHERE username = ?)\n" +
                       "AND cur_user = ?";
        
        db.update(query, Integer.parseInt(userId), subToUserName, Integer.parseInt(userId));
    }

    @Override
    public boolean isUserSubscribed(String userId, String subToUserName) {
        JdbcTemplate db = SessionUtilImpl.getSession();
        
        String query = "SELECT count(*) FROM user_sub, user_profile\n" +
                       "WHERE user_sub.cur_user = ?\n" +
                       "AND user_profile.username = ?\n" +
                       "AND user_sub.sub_to = user_profile.iduser";
        
        int count = db.queryForObject(query, 
                                    new Object[] {Integer.parseInt(userId),
                                    subToUserName},
                                    Integer.class);
        
        if (count == 1) {
            return true;
        }
        else {
            return false;
        }
    }


    @Override
    public List<String> listUserForSubscribedAuthor(String author) {
        JdbcTemplate db = SessionUtilImpl.getSession();
        
        String query = "SELECT phonenumber\n" +
                       "FROM user_profile \n" +
                       "WHERE iduser\n" +
                       "IN\n" +
                       "(SELECT cur_user\n" +
                       "FROM user_sub, user_profile\n" +
                       "WHERE sub_to = user_profile.iduser\n" +
                       "AND user_profile.username = ?)";
        
        List<String> userList = db.queryForList(query, new Object[] {author}, String.class);
        
        return userList;
    }
    
    @Override
    public List<Topic> listUserTopics(String userName){
        JdbcTemplate db = SessionUtilImpl.getSession();
        
        String query = "SELECT * FROM topic WHERE author = ? AND state != \"HIDDEN\" ORDER BY timestamp DESC";
        
        List<Topic> myTopics = db.query(query, 
                                new Object[] { userName },
                                new BeanPropertyRowMapper(Topic.class));
        
        return myTopics;

    }
}
