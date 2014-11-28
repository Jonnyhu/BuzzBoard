/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ggmsg.mvc.dao;

import com.ggmsg.mvc.models.UserProfile;
import java.util.List;

/**
 *
 * @author Alex
 */
public interface ProfileDAO {
    
    
    UserProfile getUserProfile(String username);
    void modifyUserProfile(String username, int modPass, UserProfile modUser);
    void verifyUserPhone(String username);
}
