package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

import java.util.Arrays;

public class SimpleAuthorRepository implements AuthorRepository {

    private Author[] authors = new Author[0];

    @Override
    public boolean save(Author author) {
        boolean contain = true;
        if (!Arrays.asList(authors).contains(author)) {
            Author[] newAuthors = new Author[authors.length + 1];
            System.arraycopy(authors, 0, newAuthors, 0, authors.length);
            newAuthors[newAuthors.length - 1] = author;
            authors = newAuthors;
        } else {
            contain = false;
        }
        return contain;
    }

    @Override
    public Author findByFullName(String name, String lastname) {
        Author resp = null;
        for (Author a : authors) {
            if (a.getLastName().equals(lastname) && a.getName().equals(name)) {
                resp = a;
            }
        }
        return resp;
    }

    @Override
    public boolean remove(Author author) {
        boolean resp = false;
        int firstCount = authors.length;
        int count = 0;
        for (Author a : authors) {
            if (a.equals(author)) {
                count++;
                resp = true;
            }
        }
        if (count > 0) {
            Author[] newAuthor = new Author[firstCount - count];
            int j = 0;
            for (Author a : authors) {
                if (!(a.getName().equals(author.getName()) && a.getLastName().equals(author.getLastName()))) {
                    newAuthor[j] = a;
                    j++;
                }
            }
            authors = newAuthor;
        }
        return resp;
    }

    @Override
    public int count() {
        return authors.length;
    }
}
