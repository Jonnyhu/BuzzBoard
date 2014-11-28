/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ggmsg.mvc.dao;

import com.ggmsg.mvc.models.UserProfile;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
/**
 *
 * @author Alex
 */
public class ProfileDAOImpl implements ProfileDAO { 
    
    /*
    * @return A list containing one entry (the User profile associated with
    * unique username
    */
    @Override
    public UserProfile getUserProfile(String username){
        
        JdbcTemplate db = SessionUtilImpl.getSession();
        
        String myquery = "SELECT * FROM user_profile"
                     + " WHERE username = ?";        
        UserProfile user;
        try{
            //List<UserProfile> user = db.query(myquery, new BeanPropertyRowMapper(UserProfile.class));
            user = (UserProfile) db.queryForObject(myquery, 
                    new Object[] { username },  
                    new BeanPropertyRowMapper(UserProfile.class));
            return user;
        }
        catch (Exception e){
            return null;
        }
                
    }
    
    public void verifyUserPhone(String username){
        JdbcTemplate db = SessionUtilImpl.getSession();
        
        String myquery = "UPDATE user_profile SET phone_verified = 1 where username = ? ";
        
        db.update(myquery, new Object[] {username});
        
    }
    
    public void unVerifyUserPhone(String username){
        JdbcTemplate db = SessionUtilImpl.getSession();
        
        String myquery = "UPDATE user_profile SET phone_verified = 0 where username = ? ";
        
        db.update(myquery, new Object[] {username});
        
    }
    
    @Override
    public void modifyUserProfile(String username, int modPass, UserProfile modUser){
        JdbcTemplate db = SessionUtilImpl.getSession();
        if(modPass == 0){
            String myquery = "UPDATE user_profile SET email = ?, phonenumber = ? WHERE username = ? ";
            db.update(myquery, new Object[] {modUser.getEmail(), modUser.getPhonenumber(), modUser.getUsername()});
        }
        else{
            String myquery = "UPDATE user_profile SET email = ?, phonenumber = ?, password = ? WHERE username = ? ";
            db.update(myquery, new Object[] {modUser.getEmail(), modUser.getPhonenumber(), modUser.getPassword(), modUser.getUsername()});
        }
        
    }
    
}
