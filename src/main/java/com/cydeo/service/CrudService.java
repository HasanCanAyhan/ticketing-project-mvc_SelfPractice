package com.cydeo.service;

import java.util.List;

public interface CrudService<T,ID> {

    // common methods for all interfaces


    T save(T user);
    T findById(ID username);
    List<T> findAll();
    void deleteById(ID username);

}

