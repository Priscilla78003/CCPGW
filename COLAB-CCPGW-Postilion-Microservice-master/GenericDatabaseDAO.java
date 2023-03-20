/*
 * ***************************************************************
 * Truteq Internet Payment Gateway (IPGW) version 1.0.0
 * ***************************************************************
 * Copyright (c) 2020 Truteq Australia 2019
 * ***************************************************************
 * IPGW API - Microservice for Truteq IPGW project 
 * Support: Grant O'Reilly gbo@truteq.com
 * V01.00.00  20-Dec-2019 
 * ***************************************************************
 */
package com.truteq.datagenerics;

import com.truteq.ccpgw.communication.server.logging.wrapper.LogWrapper;
import com.truteq.ccpgw.transaction.manager.merchant.portal.model.ImageData;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 * @param <E>
 */

@Component
public class GenericDatabaseDAO<E extends GenericEntity> implements DAO<E>{
    
    private final LogWrapper mLogger = new LogWrapper(GenericDatabaseDAO.class);
    
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    Environment environment;
    

    private ImageData getImageData(ResultSet resultSet) throws SQLException{
        ImageData imgData = new ImageData();
        imgData.setId(resultSet.getLong("id"));
        imgData.setDescription(resultSet.getString("description"));
        imgData.setBusinessName(resultSet.getString("businessname"));
        imgData.setBusinessNumber(resultSet.getString("businessnumber"));
        InputStream contentStream = resultSet.getClob("logo")
                                           .getAsciiStream();
        
        if(resultSet.getClob("logo").length() > 0){
                String content =
                        new Scanner(contentStream, "UTF-8").useDelimiter("\\A").next();
                imgData.setLogo(content);        
        }  
      return imgData;  
    }
    
    @Override
    public Object selectImageData(String prop,Class<E> clazz, String name){
        List<ImageData> entities = jdbcTemplate.query(environment.getProperty(prop), new Object[]{name}, (resultSet, i) -> {
                  return getImageData(resultSet);
              });
        if (entities.size() == 1) {
            return entities.get(0);
        }
        return null;
    }    
    
    @Override
    public List<E> selectAll(String prop, Class<E> clazz){
        
        mLogger.debug("Calling getAll");
        
        return jdbcTemplate.query(environment.getProperty(prop),
                                  new BeanPropertyRowMapper(clazz));
    }
    
    @Override
    public List<E> selectBy(String prop, Class<E> clazz, long id){
    
        return jdbcTemplate.query(environment.getProperty(prop), new Object[]{id}, new BeanPropertyRowMapper(clazz));
     
    }
    
    @Override
    public List<E> selectBy(String prop, Class<E> clazz, Object[] parameters, int[]parameterTypes){
    
        return jdbcTemplate.query(environment.getProperty(prop), parameters, parameterTypes, new BeanPropertyRowMapper(clazz));
     
    }    
    
    @Override
    public void createAll(Map<String, E> entities) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
      
    @Override
    public void insert(String prop, Object[] parameters, int[]parameterTypes){
         jdbcTemplate.update(environment.getProperty(prop), parameters, parameterTypes);
    }
    
    @Override
    public void delete(String prop, Object[] parameters, int[]parameterTypes){
         jdbcTemplate.update(environment.getProperty(prop), parameters, parameterTypes);
    }
      
    @Override
    public Object findById(String prop, Class<E> clazz,long id){
        return jdbcTemplate.queryForObject(environment.getProperty(prop), 
                                           new Object[]{id}, 
                                           new BeanPropertyRowMapper(clazz));
    }
    
    @Override
    public Object findByName(String prop, Class<E> clazz,String name){
        try{
             Object obj = jdbcTemplate.queryForObject(environment.getProperty(prop), 
                                           new Object[]{name}, 
                                           new BeanPropertyRowMapper(clazz));
             return obj;
        }catch(EmptyResultDataAccessException e){
            return null;
        }
    }    

    @Override
    public String getPropertyalue(String prop) {
        return environment.getProperty(prop);
    }

}        

