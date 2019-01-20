package com.todos.model;

import lombok.Data;

@Data
public class TodoItem {
    int id = 0;
    String title;
    String destination;
    String content;
    boolean done = false;
}