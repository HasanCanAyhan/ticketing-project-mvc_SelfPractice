package com.cydeo.service;

import com.cydeo.dto.TaskDTO;
import com.cydeo.dto.UserDTO;

import java.util.List;

//                                                       TaskId
public interface TaskService extends CrudService<TaskDTO,Long>{

    List<TaskDTO> findTasksByManager(UserDTO manager);



}
