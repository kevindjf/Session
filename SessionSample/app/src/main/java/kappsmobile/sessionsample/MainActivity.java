package kappsmobile.sessionsample;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import kappsmobile.session.Session;
import kappsmobile.sessionsample.modele.Book;


public class MainActivity extends AppCompatActivity {

    Session<Book> bookSession;
    TextView name_book;
    TextView number_book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();


        Book book = bookSession.getByAttribut("name", "Harry Potter");

        name_book.setText(book.getName());
        number_book.setText(book.getNombre()+"");
    }

    private void init() {

        bookSession = new Session<>(this,Book.class);

        bookSession.clear();

        Book book = new Book();
        book.setName("Harry Potter");
        book.setNombre(10);
        bookSession.add(book);

        name_book = (TextView) findViewById(R.id.name_book);
        number_book = (TextView) findViewById(R.id.number_book);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
