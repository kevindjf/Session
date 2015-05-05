package kappsmobile.session;

/**
 * Created by kevindejesusferreira on 05/05/15.
 */
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

import java.lang.reflect.Field;

import java.util.ArrayList;
import java.util.List;


public class Session<T> {

    SharedPreferences sharedPreferences;
    private final Class<T> type;


    public Session(Context context, Class<T> type) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        this.type = type;
    }

    public Session(Context context,Class<T> type,String nomSession){
        sharedPreferences = context.getSharedPreferences(nomSession, Context.MODE_PRIVATE);
        this.type = type;
    }

    public void add(T object) {

        if (object == null)
            return;

        SharedPreferences.Editor editor = sharedPreferences.edit();
        List<T> objects = getAll();

        if (objects == null)
            objects = new ArrayList<>();

        objects.add(object);

        editor.putString(getNameType(), new Gson().toJson(objects));
        editor.apply();

    }

    public void add(int index,T object) {

        if (object == null)
            return;

        SharedPreferences.Editor editor = sharedPreferences.edit();
        List<T> objects = getAll();

        if (objects == null)
            objects = new ArrayList<>();

        objects.add(index,object);

        editor.putString(getNameType(), new Gson().toJson(objects));
        editor.apply();

    }

    public void addAll(ArrayList<T> objects) {

        if (objects == null || objects.size() == 0)
            return;

        SharedPreferences.Editor editor = sharedPreferences.edit();
        List<T> objectsBDD = getAll();

        if (objectsBDD == null)
            objectsBDD = new ArrayList<>();

        objectsBDD.addAll(objects);

        editor.putString(getNameType(), new Gson().toJson(objectsBDD));
        editor.apply();
    }

    public List<T> getAll() {
        return new Gson().fromJson(sharedPreferences.getString(getNameType(), null), new ListJson<T>(type));
    }

    public T getFirst() {
        List<T> objects = getAll();

        if (objects == null || objects.size() == 0)
            return null;

        return objects.get(0);
    }

    public void clear() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(getNameType(), new Gson().toJson((new ArrayList<>())));
        editor.apply();
    }


    public void update(T o){
        List<T> objects = getAll();

        for(int i = 0; i < objects.size();i++){

            if(objects.get(i).equals(o)) {
                removeAtIndex(i);
                add(i,o);

            }

        }

    }

    public void update (T o ,String attribut,Object value){

        List<T> objects = getAll();

        if (objects == null || objects.size() == 0)
            return;

        Field field = null;
        try {

            field = type.getDeclaredField(attribut);

            field.setAccessible(true);


            Object retourne;

            for (int i = 0 ; i < objects.size() ;i++) {

                try {
                    retourne = field.get(objects.get(i));

                    if ((value).equals(retourne)){
                        removeAtIndex(i);
                        add(i,o);
                        return;
                    }


                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public T getByAttribut(String attribut, Object value) {

        List<T> objects = getAll();

        if (objects == null || objects.size() == 0)
            return null;

        Field field = null;
        try {

            field = type.getDeclaredField(attribut);

            field.setAccessible(true);


            Object retourne;

            for (T variable : objects) {

                try {
                    retourne = field.get(variable);

                    if ((value).equals(retourne))
                        return variable;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        return null;
    }


    public List<T> between(int begin, int fin) {

        List<T> objects = getAll();

        if (objects != null)
            return objects.subList(begin, fin);

        return new ArrayList<>();
    }

    public int count() {

        List<T> objects = getAll();


        if (objects == null || objects.size() == 0)
            return 0;

        return objects.size();
    }

    public List<T> subList(int begin, int count) {

        List<T> objects = getAll();

        if (objects == null || objects.size() < begin + count)
            return new ArrayList<>();

        List<T> objectsInteval = new ArrayList<>();

        for (int i = begin; i < begin + count; i++)
            objectsInteval.add(objects.get(i));


        return objectsInteval;
    }

    private String getNameType() {
        return this.type.getSimpleName();
    }


    public boolean contains(T o){
        return getAll().contains(o);
    }


    public boolean containsAll(ArrayList<T> objects){
        return getAll().contains(objects);
    }

    public boolean remove(T o){

        List<T> objects = getAll();

        boolean remove = objects.remove(o);


        if(remove) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(getNameType(), new Gson().toJson(objects));
            editor.apply();
        }

        return remove;
    }


    public T removeAtIndex(int index){

        List<T> objects = getAll();

        T remove = objects.remove(index);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(getNameType(), new Gson().toJson(objects));
        editor.apply();

        return remove;
    }


    public T objectAtIndex(int index){

        List<T> objects = getAll();

        if(objects == null || objects.size() < index)
            return null;

        return objects.get(index);
    }


    public boolean isEmpty(){
        List<T> objects = getAll();

        if(objects == null)
            return true;

        return objects.isEmpty();
    }
}
