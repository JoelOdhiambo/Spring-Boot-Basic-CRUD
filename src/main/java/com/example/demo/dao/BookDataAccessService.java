package com.example.demo.dao;

import com.example.demo.model.Book;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository("postgres")
public class BookDataAccessService implements BookDao{

    private final JdbcTemplate jdbcTemplate;

    public BookDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertBook(UUID id, Book book) {
        return 0;
    }

    @Override
    public List<Book> selectAllBooks() {
        final String sql = "SELECT id, name FROM book";
        List<Book>books= jdbcTemplate.query(sql,(resultSet, i)->{
            UUID id=UUID.fromString(resultSet.getString("id"));
            String name=resultSet.getString("name");
            return new Book(id,name);
        });
        return books;
    }

    @Override
    public Optional<Book> selectBookByID(UUID id) {
        return Optional.empty();
    }

    @Override
    public int deleteBookById(UUID id) {
        return 0;
    }

    @Override
    public int updateBookById(UUID id, Book book) {
        return 0;
    }

}
