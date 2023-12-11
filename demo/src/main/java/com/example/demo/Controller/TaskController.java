package com.example.demo.Controller;

import com.example.demo.Model.Task;
import com.example.demo.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/")
    public String listTasks(Model model) {
        List<Task> tasks = taskRepository.findAll();
        model.addAttribute("tasks", tasks);
        return "list";
    }

    @GetMapping("/form")
    public String displayTaskForm(Model model) {
        model.addAttribute("task", new Task());
        return "form";
    }

    @PostMapping("/save")
    public String saveTask(@ModelAttribute Task task) {
        try {
            taskRepository.save(task);
            return "redirect:/";
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception
            // Handle the exception as needed (e.g., return an error page)
            return "error"; // Create an "error.html" template
        }
    }

    @GetMapping("/edit/{id}")
    public String displayEditForm(@PathVariable Long id, Model model) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid task ID"));
        model.addAttribute("task", task);
        return "edit";
    }
    @PostMapping("/update")
    public String updateTask(@ModelAttribute Task task) {
        taskRepository.save(task);
        return "redirect:/";
    }
    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskRepository.deleteById(id);
        return "redirect:/";
    }

}
