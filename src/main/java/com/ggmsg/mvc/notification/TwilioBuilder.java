/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ggmsg.mvc.notification;


import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.factory.OutgoingCallerIdFactory;
import com.twilio.sdk.resource.instance.CallerIdValidation;
import com.twilio.sdk.resource.instance.Message;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author anton
 */
public class TwilioBuilder implements TwilioBuilderInterface {
    
    List<NameValuePair> params = new ArrayList<NameValuePair>();    
    
    @Override
    public void sendSMS(){        
        params.add(new BasicNameValuePair("From", FROM));
        
        TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);
        
        MessageFactory messageFactory = client.getAccount().getMessageFactory();
        
        try {        
            Message message = messageFactory.create(this.params);
        }
        catch (TwilioRestException e) {
            System.out.println(e.getErrorMessage());
        }
    }
    
    @Override
    public void AddRecepient(String number){
        params.add(new BasicNameValuePair("To", "+1" + number));
    }
    
    @Override
    public void AddMessage(String msg){
        params.add(new BasicNameValuePair("Body", msg));
    }
    
    @Override
    public String VerifyUserPhoneNumber(String phone, String name){
        /* Validate user phone number */        
        
        TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);
        

        // Build a filter for the OutgoingCallerIdList
        Map<String, String> params = new HashMap();

        if (name != null){
            params.put("FriendlyName", name);
        }
        System.out.println(phone);
        params.put("PhoneNumber", "+1" + phone);

        OutgoingCallerIdFactory callerIdFactory = client.getAccount().getOutgoingCallerIdFactory();
        CallerIdValidation validationAttempt;
        
        String validationCode = null;
        try{
            validationAttempt = callerIdFactory.create(params);
            validationCode = validationAttempt.getValidationCode();
            System.out.println(validationCode);
            //
            return validationCode;
        }
        catch (TwilioRestException te){
            //
            if(te.getErrorMessage().equals("Phone number is already verified.")){
                validationCode = "ALREADYVERIFIED";
            }
        }
        return validationCode;        
    }
    
}
