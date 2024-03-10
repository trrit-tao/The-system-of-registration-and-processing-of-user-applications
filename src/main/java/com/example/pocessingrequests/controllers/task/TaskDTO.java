package com.example.pocessingrequests.controllers.task;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@AllArgsConstructor
@Getter
public class TaskDTO {
    private Long id;
    private StatusTask statusTask;
    private String text;
    private String userPhone;
    private String userName;
    private Instant createDate;


}
