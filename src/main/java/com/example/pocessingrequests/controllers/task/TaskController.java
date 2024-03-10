package com.example.pocessingrequests.controllers.task;

import com.example.pocessingrequests.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/task")
@AllArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping
    public Page<TaskDTO> viewListTask(
            @PageableDefault(size = 5, page = 0, sort = "createDate") Pageable pageable
            , Filter filter) {
        return taskService.findAll(pageable, filter);
    }

    @Secured("USER_ROLE,OPERATOR_ROLE,ADMIN_ROLE")
    @GetMapping("{id}")
    public TaskDTO viewTask(@PathVariable Long id) {
        return taskService.viewTask(id);
    }

    @Secured("USER_ROLE")
    @PostMapping
    public TaskDTO createNewTask(@RequestBody NewTaskRequest payload) {
        return taskService.createTask(payload);
    }

    @Secured("USER_ROLE")
    @PostMapping("{id}/sent")
    public TaskDTO sentTask(@PathVariable Long id) {
        return taskService.sendTask(id);
    }

    @Secured("OPERATOR_ROLE")
    @PostMapping("{id}/accept")
    public TaskDTO acceptTask(@PathVariable Long id) {
        return taskService.acceptTask(id);
    }

    @Secured("OPERATOR_ROLE")
    @PostMapping("{id}/reject")
    public TaskDTO rejectTask(@PathVariable Long id) {
        return taskService.rejectTask(id);
    }
}
