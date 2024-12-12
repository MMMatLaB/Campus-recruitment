package com.example.demo.entities;

import lombok.Data;

@Data
public class QuestionSelect {
    private int idquestionnaire;
    private String fileName;
    private String intro;
    private String title;
    private int state;
    private String creattime;
    private String userName;
}
