
package com.ggmsg.mvc.models;

import java.util.Date;

public class Topic {
    private long id;
   
    private String title;
    private String content;
    private Date timestamp;
    private String author;
    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    
    public void setAuthor(String username){
        this.author = username;
    }
    
    public String getAuthor(){
        return this.author;
    }

    public void setTimestamp(Date time) {
        this.timestamp = time;
    }
    
    public Date getTimestamp() {
        return this.timestamp;
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
