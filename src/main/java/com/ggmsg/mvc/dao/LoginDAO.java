/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ggmsg.mvc.dao;

import com.ggmsg.mvc.models.UserProfile;

/**
 *
 * @author anton
 */
public interface LoginDAO {

    UserProfile authenticate(String user, String pw);
    
    UserProfile retrievePassword(String email);

    boolean createUser(String username, String password, String phonenumber, String email);
    
}
