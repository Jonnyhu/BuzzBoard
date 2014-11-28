/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ggmsg.mvc.models;

import java.util.Date;

/**
 *
 * @author jonny
 */
public class Reply {
    private long id;
    private Date timestamp;
    private String content;
    private String author;
    private String topic_id;

    public long getId() {
        return id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public String getTopic_id() {
        return topic_id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTopic_id(String topic_id) {
        this.topic_id = topic_id;
    }
    
    
}
