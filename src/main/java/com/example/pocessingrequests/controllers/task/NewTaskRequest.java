package com.example.pocessingrequests.controllers.task;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "Данные для создание задачи")
public class NewTaskRequest {
    @Schema(description = "Статус задачи" , defaultValue = "DRAFT")
    private final StatusTask statusTask;
    @Schema(description = "Описание задачи" , defaultValue = "Помой посуду")
    private final String text;
    @Schema(description = "Телефон поьзователя задачи" , defaultValue = "8 999 999 99 99")
    private final String userPhone;
    @Schema(description = "Имя пользователя задачи" , defaultValue = "Dimon")
    private final String userName;

    public NewTaskRequest(StatusTask statusTask, String text, String userPhone, String userName) {
        this.statusTask = statusTask;
        this.text = text;
        this.userPhone = userPhone;
        this.userName = userName;
        validate();
    }

    private void validate() {
        if (!(StatusTask.DRAFT.equals(statusTask) || StatusTask.SEND.equals(statusTask)))
            throw new IllegalArgumentException();
        if (null == text || text.isEmpty())
            throw new IllegalArgumentException();
        if(null==userPhone || userPhone.isEmpty())
            throw new IllegalArgumentException();
        if (null==userName || userName.isEmpty())
            throw new IllegalArgumentException();
    }
}
