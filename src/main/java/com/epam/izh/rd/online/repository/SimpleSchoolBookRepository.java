package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {
    SchoolBook[] schoolBooks = new SchoolBook[0];

    @Override
    public boolean save(SchoolBook book) {
        int count = count();
        SchoolBook[] newSchoolBooks = new SchoolBook[count + 1];
        System.arraycopy(schoolBooks, 0, newSchoolBooks, 0, count);
        newSchoolBooks[count] = book;
        schoolBooks = newSchoolBooks;
        if (count + 1 == schoolBooks.length) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public SchoolBook[] findByName(String name) {
        int count = 0;
        for (SchoolBook s : this.schoolBooks) {
            if (s.getName().equals(name)) {
                count++;
            }
        }
        SchoolBook[] newSchoolBook = new SchoolBook[count];
        if (count != 0) {
            int j = 0;
            for (SchoolBook s : this.schoolBooks) {
                if (s.getName().equals(name)) {
                    count++;
                }
                newSchoolBook[j] = s;
                j++;
            }
        }
        return newSchoolBook;
    }

    @Override
    public boolean removeByName(String name) {
        int nCount = 0;

        for (SchoolBook s : schoolBooks) {
            if (s.getName().equals(name)) {
                nCount++;
            }
        }
        if (nCount > 0) {
            SchoolBook[] newSchoolBooks = new SchoolBook[schoolBooks.length - nCount];
            int j = 0;
            for (int i = 0; i < schoolBooks.length; i++) {
                if (!schoolBooks[i].getName().equals(name)) {
                    newSchoolBooks[j] = schoolBooks[i];
                    j++;
                }
            }
            schoolBooks = newSchoolBooks;
            return true;
        } else return false;
    }

    @Override
    public int count() {
        return schoolBooks.length;
    }
}
