/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ggmsg.mvc.notification;

/**
 *
 * @author anton
 */
public class NotificationManager {
       
    public static TwilioBuilder getTwilioBuilder (){
        
        return new TwilioBuilder();
    }
}
