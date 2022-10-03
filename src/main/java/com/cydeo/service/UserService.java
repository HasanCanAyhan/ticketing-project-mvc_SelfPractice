package com.cydeo.service;

import com.cydeo.dto.UserDTO;

import java.util.List;


//                                                       unique is String :username
public interface UserService extends CrudService<UserDTO,String> {

    List<UserDTO> findManagers();
    List<UserDTO> findEmployees();
}
