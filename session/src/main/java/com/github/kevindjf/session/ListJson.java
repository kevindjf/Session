package com.github.kevindjf.session;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by kevindejesusferreira on 05/05/15.
 */
public class ListJson <T> implements ParameterizedType
{
    private Class<?> wrapped;

    public ListJson(Class<T> wrapper)
    {
        this.wrapped = wrapper;
    }

    @Override
    public Type[] getActualTypeArguments()
    {
        return new Type[] { wrapped };
    }

    @Override
    public Type getRawType()
    {
        return List.class;
    }

    @Override
    public Type getOwnerType()
    {
        return null;
    }
}