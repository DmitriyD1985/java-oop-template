package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.Author;
import com.epam.izh.rd.online.entity.Book;
import com.epam.izh.rd.online.entity.SchoolBook;
import com.epam.izh.rd.online.repository.BookRepository;
import com.epam.izh.rd.online.repository.SimpleAuthorRepository;
import com.epam.izh.rd.online.repository.SimpleSchoolBookRepository;

public class SimpleSchoolBookService implements BookService {
    private AuthorService authorService;
    private BookRepository<SchoolBook> schoolBookBookRepository;

    @Override
    public boolean save(Book book) {
        boolean response = false;
        SchoolBook sb = (SchoolBook) book;
        if (authorService.findByFullName(((SchoolBook) book).getAuthorName(), ((SchoolBook) book).getAuthorLastName()) != null) {
            response = schoolBookBookRepository.save(sb);
        } else {
            System.out.println("cannot be saved");
        }
        return response;
    }

    @Override
    public Book[] findByName(String name) {
        schoolBookBookRepository.findByName(name);
        return new Book[0];
    }

    @Override
    public int getNumberOfBooksByName(String name) {

        return schoolBookBookRepository.findByName(name).length;
    }

    @Override
    public boolean removeByName(String name) {
        return schoolBookBookRepository.removeByName(name);
    }

    @Override
    public int count() {
        return schoolBookBookRepository.count();
    }

    @Override
    public Author findAuthorByBookName(String name) {

        return authorService.findByFullName(schoolBookBookRepository.findByName(name)[0].getAuthorName(), schoolBookBookRepository.findByName(name)[0].getAuthorLastName());
    }

    public SimpleSchoolBookService(BookRepository<SchoolBook> schoolBookBookRepository, AuthorService authorService) {
        this.authorService = authorService;
        this.schoolBookBookRepository = schoolBookBookRepository;
    }

    public SimpleSchoolBookService() {
    }
}
