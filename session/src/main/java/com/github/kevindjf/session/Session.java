package com.github.kevindjf.session;

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

    protected final SharedPreferences sharedPreferences;
    protected final Class<T> type;
    protected final Gson gson = new Gson(); //improves performances to have only one Gson

    public Session(Context context, Class<T> type) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        this.type = type;
    }

    public Session(Context context,Class<T> type,String nomSession){
        sharedPreferences = context.getSharedPreferences(nomSession, Context.MODE_PRIVATE);
        this.type = type;
    }

    protected void save(List<T> objects){
        sharedPreferences
                .edit()
                .putString(getNameType(), new Gson().toJson(objects))
                .apply();
    }

    public Session<T> add(T object) {
        if (object == null)
            return this;

        List<T> objects = this.getAll();

        if (objects == null)
            objects = new ArrayList<>();

        objects.add(object);

        this.save(objects);

        return this;
    }

    public Session<T> add(int index,T object) {
        if (object == null)
            return this;

        List<T> objects = getAll();

        if (objects == null)
            objects = new ArrayList<>();

        objects.add(index, object);

        this.save(objects);

        return this;
    }

    public Session<T> addAll(ArrayList<T> objects) {
        if (objects == null || objects.size() == 0)
            return this;

        List<T> objectsBDD = getAll();

        objectsBDD.addAll(objects);

        this.save(objectsBDD);

        return this;
    }

    public List<T> getAll() {
        String json = sharedPreferences.getString(getNameType(), null);
        if(json == null)
            return new ArrayList<>();
        else
            return gson.fromJson(json, new ListJson<T>(type));
    }

    public T getFirst() {
        List<T> objects = getAll();

        if (objects == null || objects.size() == 0)
            return null;

        return objects.get(0);
    }

    public Session<T> clear() {
        save(new ArrayList<T>());
        return this;
    }


    public Session<T> update(T o){
        List<T> objects = getAll();

        for(int i = 0; i < objects.size();i++){
            if(objects.get(i).equals(o)) {
                removeAtIndex(i);
                add(i,o);
            }
        }

        return this;
    }

    public Session<T> update (T o,String attributeName, Object value){

        List<T> objects = getAll();

        if (objects == null || objects.size() == 0)
            return this;

        Field field = null;
        try {

            field = type.getDeclaredField(attributeName);

            field.setAccessible(true);


            Object objectReturn;

            for (int i = 0 ; i < objects.size() ;i++) {

                try {
                    objectReturn = field.get(objects.get(i));

                    if ((value).equals(objectReturn)){
                        removeAtIndex(i);
                        add(i,o);
                        return this;
                    }


                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        return this;
    }

    public T getByAttribute(String attributeName, Object value) {

        List<T> objects = getAll();

        if (objects == null || objects.size() == 0)
            return null;

        Field field = null;
        try {

            field = type.getDeclaredField(attributeName);

            field.setAccessible(true);


            Object objectReturn;

            for (T variable : objects) {

                try {
                    objectReturn = field.get(variable);

                    if ((value).equals(objectReturn))
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


    public List<T> between(int begin, int end) {
        List<T> objects = getAll();

        if (objects != null)
            return objects.subList(begin, end);

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


    public boolean containsAll(List<T> objects){
        return getAll().contains(objects);
    }

    public boolean remove(T o){
        List<T> objects = getAll();

        boolean removed = objects.remove(o);
        if(removed) {
            save(objects);
        }

        return removed;
    }


    public T removeAtIndex(int index){
        List<T> objects = getAll();

        T remove = objects.remove(index);

        save(objects);

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

        return (objects == null || objects.isEmpty());
    }
}
