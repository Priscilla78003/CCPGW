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

import java.util.List;
import java.util.Map;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 * @param <E>
 */
public interface DAO<E extends GenericEntity>  {
    
    /**
     * 
     * @param entities 
     */
    public void createAll(Map<String, E> entities);
    /**
     * 
     * @param prop
     * @param clazz
     * @return 
     */
    public List<E> selectAll(String prop, Class<E> clazz);
    /**
     * 
     * @param prop
     * @param clazz
     * @param id
     * @return 
     */
    public List<E> selectBy(String prop, Class<E> clazz, long id);
    
    public List<E> selectBy(String prop, Class<E> clazz, Object[] parameters, int[]parameterTypes);
    
    public void insert(String prop, Object[] parameters, int[]parameterTypes);

    public void delete(String prop, Object[] parameters, int[]parameterTypes);
     
    public Object findById(String prop, Class<E> clazz,long id);
    
    public Object findByName(String prop, Class<E> clazz,String name);
    
    public String getPropertyalue(String prop);
    
    public Object selectImageData(String prop,Class<E> clazz, String name);
    
    
}
