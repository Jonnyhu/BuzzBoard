/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ggmsg.mvc.dao;

import com.ggmsg.mvc.models.UserProfile;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author jonny
 */
public class LoginDAOImpl implements LoginDAO{

    
    @Override
    public UserProfile authenticate(String email, String password) {
        JdbcTemplate db = SessionUtilImpl.getSession();
        
        String email_query = "SELECT * from user_profile" 
                + " WHERE (email = ? OR username = ?)"
                + " AND password = ?";
        try{
            UserProfile result = (UserProfile) db.queryForObject(email_query, 
                    new Object[] { email, email, password },  
                    new BeanPropertyRowMapper(UserProfile.class));
            return result;
        }
        catch (Exception e){
            return null;
        }
        
    }
    
    @Override
    public UserProfile retrievePassword(String email){
        JdbcTemplate db = SessionUtilImpl.getSession();
        
        String email_query = "SELECT * from user_profile" 
                + " WHERE email = ?";
                
        List<UserProfile> user = db.query(email_query, new Object[] {email}, new BeanPropertyRowMapper(UserProfile.class));
        if (!user.isEmpty()){
            return user.get(0);
        }
        return null;
    }
    
        
    
    @Override
    public boolean createUser(String username, String password, String phonenumber, String email) {
        JdbcTemplate db = SessionUtilImpl.getSession();
        
        String query = "INSERT INTO user_profile (email, username, phonenumber, password)"
                     + " value (?, ?, ?, ?)";
        try{
            db.update(query, email, username, phonenumber, password );
        }
        catch (DuplicateKeyException e) {
            return false;
        }
        
        return true;
    }
}
