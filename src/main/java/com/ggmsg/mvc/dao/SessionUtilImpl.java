/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ggmsg.mvc.dao;

import java.sql.SQLException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

/**
 *
 * @author anton
 */
public class SessionUtilImpl implements SessionUtil {
    //@Override
    public static JdbcTemplate getSession(){
        JdbcTemplate jt = null;
        
        try{
            jt = createSession();
        }
        catch(SQLException e){ 
                                                                                                                                                                                                                                                                                                            
        }
        
        return jt;
        
    }
    
    private static JdbcTemplate createSession() throws SQLException{
        
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        
        dataSource.setDriver(new com.mysql.jdbc.Driver());
        
        dataSource.setUsername("ggAdmin");
        dataSource.setUrl("jdbc:mysql://ggmsg.cy66wlbvhfgv.us-west-2.rds.amazonaws.com/ggMSG490");
        dataSource.setPassword("ggAdmin490");

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        
        return jdbcTemplate;
    }
    
}
