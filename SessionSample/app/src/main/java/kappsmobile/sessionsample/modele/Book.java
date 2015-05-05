package kappsmobile.sessionsample.modele;

/**
 * Created by kevindejesusferreira on 05/05/15.
 */
public class Book {

    String name;
    int nombre;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "nameBook='" + name ;
    }

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;

        Book book = (Book) o;
        return !(getName() != null ? !getName().equals(book.getName()) : book.getName() != null);

    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + getNombre();
        return result;
    }
}