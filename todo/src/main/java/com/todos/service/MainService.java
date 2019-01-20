package com.todos.service;

import com.todos.dao.MainDao;
import com.todos.model.TodoItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainService {
    @Autowired
    MainDao dao;

    public List<TodoItem> getAllTodos() {
        return this.dao.getAllTodos();
    }

    public Integer addNewTodo( TodoItem todoItem ) {
        return dao.addNewTodo( todoItem );
    }

    public int removeTodo( int id ) {
        return dao.removeTodo( id );
    }

    public int toggleDone( int id ) {
        return dao.toggleDone( id );
    }

    public boolean removeAllItems() {
        return dao.removeAll();
    }

    public String getContentByTitle( int id ) {
        return dao.getContentByTitle( id );
    }
}