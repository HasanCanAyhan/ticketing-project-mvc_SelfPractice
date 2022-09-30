package com.cydeo.service;

import java.util.List;

public interface CrudService<T,ID> {

    // common methods for all interfaces/ services

    // changing is object like User,Role,Manager etc...

    // create, save , read, update, find, delete certain things business logic


    T save(T user);
    T findById(ID username);
    List<T> findAll();
    void deleteById(ID username);

}

