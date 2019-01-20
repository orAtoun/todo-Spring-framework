package com.todos.dao;

import com.todos.model.TodoItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.NestedRuntimeException;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.List;

@Repository
public class MainDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<TodoItem> getAllTodos() {
        return jdbcTemplate.query( "SELECT * FROM todos", new BeanPropertyRowMapper<>( TodoItem.class ) );
    }

    public Integer addNewTodo( TodoItem todoItem ) {
        jdbcTemplate.update( "INSERT INTO todos (title, destination, content) VALUES (?, ?, ?)", new Object[]{ todoItem.getTitle(), todoItem.getDestination(), todoItem.getContent() } );

        return jdbcTemplate.queryForObject( "SELECT LAST_INSERT_ID()", Integer.class );
    }

    public int removeTodo( int id ) {
        return jdbcTemplate.update( "DELETE FROM todos WHERE id = ?", id );
    }

    public int toggleDone( int id ) {
        return jdbcTemplate.update( "UPDATE todos SET done = !done WHERE id = ?", id );
    }

    public boolean removeAll() {
        try {
            jdbcTemplate.update( "DELETE FROM todos" );
            return true;
        } catch ( DataAccessException e ) {
            return false;
        }
    }

    public String getContentByTitle( int id ) {
        return jdbcTemplate.queryForObject( "SELECT content FROM todos WHERE id = ?", new Object[]{ id }, String.class );
    }
}