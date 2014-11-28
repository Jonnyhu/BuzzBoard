/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ggmsg.mvc.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author anton, alex
 */


public class UserProfile {
    private long iduser;
   
    @NotNull
    @Length(min=1, max=2000)
    private String email;
    @NotNull
    @Length(min=1, max=2000)
    private String phonenumber;    
    @NotNull
    @Length(min=1, max=2000)
    private String username;
    @NotNull
    private int remember;
    @NotNull
    private int sms;
    private String password;
    
    private String cpass;
    private int phone_verified;

    /**
     * @return the iduser
     */
    public long getIduser() {
        return iduser;
    }

    /**
     * @param iduser the id to set
     */
    public void setIduser(long iduser) {
        this.iduser = iduser;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the phonenumber
     */
    public String getPhonenumber() {
        return phonenumber;
    }

    /**
     * @param phonenumber the phonenumber to set
     */
    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /*
    * @return remember me on login value (1 is yes, 0 is no)
    */
    public int getRemember() {
        return remember;
    }
    
    /*
    * @param remember set for login (1 is yes, 0 is no)
    */
    public void setRemember(int remember) {
        this.remember = remember;
    }
    
    /*
    * @return sms for phone notifications (1 is yes, 0 is no)
    */
    public int getSms() {
        return sms;
    }
    
    /*
    * @param sms set for phone notifications (1 is yes, 0 is no)
    */
    public void setSms(int sms) {
        this.sms = sms;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the phone_verified
     */
    public int getPhone_verified() {
        return phone_verified;
    }

    /**
     * @param phone_verified the phone_verified to set
     */
    public void setPhone_verified(int phone_verified) {
        this.phone_verified = phone_verified;
    }

    public String getCpass() {
        return cpass;
    }

    public void setCpass(String cpass) {
        this.cpass = cpass;
    }
    
    
}
