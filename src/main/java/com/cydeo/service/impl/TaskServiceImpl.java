package com.cydeo.service.impl;

import com.cydeo.dto.TaskDTO;
import com.cydeo.enums.Status;
import com.cydeo.service.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class TaskServiceImpl extends AbstractMapService<TaskDTO,Long> implements TaskService {


    @Override
    public TaskDTO save(TaskDTO task) {
        if (task.getTaskStatus() == null){
            task.setTaskStatus(Status.OPEN);
        }

        if (task.getAssignedDate() == null){
            task.setAssignedDate(LocalDate.now());
        }

        return super.save(task.getId(), task);
    }

    @Override
    public TaskDTO findById(Long taskId) {
        return super.findById(taskId);
    }

    @Override
    public List<TaskDTO> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long taskId) {
         super.deleteById(taskId);
    }

    @Override
    public void update(TaskDTO object) {
        super.update(object.getId(),object);
    }
}
