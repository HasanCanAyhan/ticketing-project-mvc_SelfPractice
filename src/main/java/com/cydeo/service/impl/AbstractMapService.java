package com.cydeo.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractMapService <T,ID>{ // Spring Boot design it
    // abstract , because we don't need any object

    // for RoleDto , new map
    // for UserDto, another new map

    public Map<ID,T> map = new HashMap<>(); //DB custom database

    //we can say parent - implementation
    // those implementations are valid for child classes
    // changing is object

    T save(ID id,T object){
        map.put(id,object);
        return object;
    }

    List<T> findAll(){
        return new ArrayList<>(map.values());
    }

    T findById(ID id){ // to select UserDto.RoleDto tc from DataBase
        return map.get(id);
    }

    void deleteById(ID id){
        map.remove(id);
    }



}
