package spring.boot.week4day5ex.Controller;

import org.springframework.web.bind.annotation.*;
import spring.boot.week4day5ex.API.ApiTask;
import spring.boot.week4day5ex.Model.Task;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/task")
public class TaskController {
    //Q1

    ArrayList<Task> tasks = new ArrayList<>();

    //Get
    @GetMapping("/get")
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    //post
    @PostMapping("/add")
    public ApiTask addTask(@RequestBody Task task) {
        try {
            tasks.add(task);
            return new ApiTask("Success tasks add", "200");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return new ApiTask("Error", "500");
    }

    //Update
    @PutMapping("/update/{index}")
    public ApiTask updateTask(@PathVariable int index, @RequestBody Task task) {
        try {
            tasks.set(index, task);
            return new ApiTask("Success tasks update", "200");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return new ApiTask("Error", "404");
    }

    //Delete
    @DeleteMapping("/delete/{index}")
    public ApiTask deleteTask(@PathVariable int index) {
        try {
            tasks.remove(index);
            return new ApiTask("Success tasks delete", "200");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return new ApiTask("Error", "404");
    }

    //Change the task status as done or not done
    @PutMapping("/check/{index}")
    public ApiTask checkTaskStatus(@PathVariable Integer index) {
        try {
            tasks.get(index).setStatus("Done");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return new ApiTask( "Error,You need chose Number between 0 to " + (tasks.size() - 1),"200");
    }

    //Search for a task by given title
    @GetMapping("/title/{title}")
    public Task searchTask(@PathVariable String title) {
        for (Task task : tasks) {
            if (task.getTitle().equals(title)) {
                return task;
            }
        }
        return null;
    }
}
