package com.cydeo.service;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.UserDTO;

import java.util.List;

//                                                              projectCode is unique : String
public interface ProjectService extends CrudService<ProjectDTO,String> {

    void complete(ProjectDTO project);
    List<ProjectDTO> getCountedListOfProjectDTO(UserDTO manager);

}
