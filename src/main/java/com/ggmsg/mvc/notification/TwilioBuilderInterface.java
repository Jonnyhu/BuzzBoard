/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ggmsg.mvc.notification;

/**
 *
 * @author bondaran
 */
public interface TwilioBuilderInterface {
    String ACCOUNT_SID = "AC074c197fe069111df524fca591f78e5e";
    String AUTH_TOKEN = "2fca76bac0c4cf3c120d35491e72bc38";
    String FROM = "+14378002210";

    void AddMessage(String msg);

    void AddRecepient(String number);

    String VerifyUserPhoneNumber(String phone, String name);

    void sendSMS();
    
}
