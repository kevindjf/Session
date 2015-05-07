package com.github.kevindjf.session.sample.session;

import android.content.Context;

import com.github.kevindjf.session.Session;
import com.github.kevindjf.session.sample.model.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by florentchampigny on 07/05/15.
 */
public class CustomBookSession extends Session<Book> {

    private static final String NAME = "FAVORITES";

    //force set a different name that Session<Book>
    public CustomBookSession(Context context, Class<Book> type) {
        super(context, type, NAME);
    }

    public CustomBookSession(Context context, Class<Book> type, String nomSession) {
        super(context, type, nomSession);
    }

    //and add your custom methods

    public List<Book> getBooksLessThan(int number){
        List<Book> books = getAll();

        List<Book> returnList = new ArrayList<>();
        for (Book book : books){
            if(book.getNumber() < number)
                returnList.add(book);
        }

        return returnList;
    }
}
