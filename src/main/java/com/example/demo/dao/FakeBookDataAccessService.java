package com.example.demo.dao;

import com.example.demo.model.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeDao") //class is served as repository.("") allows multiple implementations
public class FakeBookDataAccessService implements BookDao{

    private static List<Book> DB=new ArrayList<>();

    @Override
    public int insertBook(UUID id, Book book) {
        DB.add(new Book(id,book.getName()));

        return 1;
    }

    @Override
    public List<Book> selectAllBooks() {
        return DB;
    }

    @Override
    public Optional<Book> selectBookByID(UUID id) {
        return DB.stream().filter(book->book.getId().equals(id)).findFirst();
    }

    @Override
    public int deleteBookById(UUID id) {
        Optional<Book> bookExists=selectBookByID(id);
        if(bookExists.isEmpty()){
            return 0;
        }
        DB.remove((bookExists.get()));
        return 1;
    }

    @Override
    public int updateBookById(UUID id, Book update) {
        return selectBookByID(id).map(book->{
            int indexOfBookToUpdate=DB.indexOf(book);
            if(indexOfBookToUpdate>=0){
                DB.set(indexOfBookToUpdate,new Book(id,update.getName() ));
                return 1;
            }
            return 0;
        }).orElse(0);


    }
}
