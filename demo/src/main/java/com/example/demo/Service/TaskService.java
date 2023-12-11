package com.example.demo.Service;


import com.example.demo.Model.Task;
import com.example.demo.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public  TaskService(TaskRepository taskRepository){
        this.taskRepository=taskRepository;
    }

    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }


    public Optional<Task> findTaskById(long id) {

        return taskRepository.findById(id);

    }

    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    public void deleteTaskById(long id) {
        taskRepository.deleteById(id);
    }
}
