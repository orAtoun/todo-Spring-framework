package com.todos.controller;

import com.todos.service.MainService;
import com.todos.model.TodoItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MainRestController {
    @Autowired
    MainService dto;

    @RequestMapping( value = "api/todo", method = RequestMethod.GET )
    public List<TodoItem> getAllTodos() {
        return dto.getAllTodos();
    }

    @RequestMapping( value = "api/todo", method = RequestMethod.POST, consumes = "application/json" )
    public Integer addTodo( @RequestBody TodoItem todoItem ) {
        return dto.addNewTodo( todoItem );
    }

    @RequestMapping( value = "api/todo/{id}", method = RequestMethod.DELETE )
    public int removeTodo( @PathVariable( "id" ) int id ) {
        return dto.removeTodo( id );
    }

    @RequestMapping( value = "api/todo/done/{id}", method = RequestMethod.PUT )
    public int setTodoToDone( @PathVariable( "id" ) int id ) {
        return dto.toggleDone( id );
    }

    @RequestMapping( value = "api/todo", method = RequestMethod.DELETE )
    public boolean removeAllItems() {
        return dto.removeAllItems();
    }

    @RequestMapping( value = "api/todo/content/{id}", method = RequestMethod.GET )
    public String getContentById( @PathVariable( "id" ) int id ) {
        return dto.getContentByTitle( id );
    }
}