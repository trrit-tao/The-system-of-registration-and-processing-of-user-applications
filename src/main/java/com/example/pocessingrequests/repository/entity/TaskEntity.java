package com.example.pocessingrequests.repository.entity;

import com.example.pocessingrequests.controllers.task.NewTaskRequest;
import com.example.pocessingrequests.controllers.task.StatusTask;
import com.example.pocessingrequests.controllers.task.TaskDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "task")
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusTask statusTask;
    @Column(name = "text")
    private String text;
    @Column(name = "user_phone")
    private String userPhone;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "create_date")
    private Instant createDate;

    public TaskDTO toModel() {
        return new TaskDTO(getId()
                , getStatusTask()
                , getText()
                , getUserPhone()
                , getUserName()
                , getCreateDate());
    }

    public static TaskEntity fromModel(NewTaskRequest model) {
        TaskEntity newTaskEntity = new TaskEntity();
        newTaskEntity.setStatusTask(model.getStatusTask());
        newTaskEntity.setText(model.getText());
        newTaskEntity.setUserPhone(model.getUserPhone());
        newTaskEntity.setUserName(model.getUserName());
        newTaskEntity.setCreateDate(Instant.now());
        return newTaskEntity;
    }
}
