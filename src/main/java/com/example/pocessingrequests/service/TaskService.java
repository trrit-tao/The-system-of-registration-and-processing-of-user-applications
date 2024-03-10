package com.example.pocessingrequests.service;

import com.example.pocessingrequests.controllers.task.Filter;
import com.example.pocessingrequests.controllers.task.NewTaskRequest;
import com.example.pocessingrequests.controllers.task.StatusTask;
import com.example.pocessingrequests.controllers.task.TaskDTO;
import com.example.pocessingrequests.controllers.user.Role;
import com.example.pocessingrequests.repository.TaskRepository;
import com.example.pocessingrequests.repository.UserRepository;
import com.example.pocessingrequests.repository.entity.TaskEntity;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import static com.example.pocessingrequests.repository.entity.TaskEntity.fromModel;
import static com.example.pocessingrequests.service.AuthenticationService.getUserName;
import static com.example.pocessingrequests.service.AuthenticationService.hasRole;

@Service
@AllArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskDTO createTask(NewTaskRequest payload) {
        return taskRepository.save(fromModel(payload))
                .toModel();
    }

    public Page<TaskDTO> findAll(Pageable pageRequest, Filter filter) {
        Page<TaskEntity> entities = new PageImpl<>(new ArrayList<>());
        if (hasRole(Role.USER_ROLE)) {
            entities = taskRepository.findAll(pageRequest,
                    filter,
                    TaskRepository.withUserName(getUserName()));
        } else if (hasRole(Role.OPERATOR_ROLE)) {
            entities = taskRepository.findAll(pageRequest,
                    filter,
                    (r, q, b) -> b.equal(r.get("status").as(String.class), StatusTask.SEND));
        } else if (hasRole(Role.ADMIN_ROLE)) {
            entities = taskRepository.findAll(pageRequest,
                    filter,
                    (r, q, b) -> b.notEqual(r.get("status").as(String.class), StatusTask.DRAFT));
        }
        return entities.map(TaskEntity::toModel);
    }

    public TaskDTO sendTask(Long id) {
        TaskEntity task = taskRepository
                .findByIdAndStatusTask(id, StatusTask.DRAFT)
                .orElseThrow(NoSuchElementException::new);

        task.setStatusTask(StatusTask.SEND);
        return taskRepository.save(task).toModel();
    }

    public TaskDTO viewTask(Long id) {
        return taskRepository
                .findById(id)
                .orElseThrow(NoSuchElementException::new).toModel();
    }

    public TaskDTO acceptTask(Long id) {
        TaskEntity task = taskRepository.findById(id).orElseThrow(NoSuchElementException::new);
        task.setStatusTask(StatusTask.ACCEPTED);
        return taskRepository.save(task).toModel();
    }

    public TaskDTO rejectTask(Long id) {
        TaskEntity task = taskRepository.findById(id).orElseThrow(NoSuchElementException::new);
        task.setStatusTask(StatusTask.REJECTED);
        return taskRepository.save(task).toModel();
    }
}
