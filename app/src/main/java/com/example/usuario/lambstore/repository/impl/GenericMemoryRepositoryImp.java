package com.example.usuario.lambstore.repository.impl;

import android.content.ContentValues;
import android.util.Log;

import com.example.usuario.lambstore.Utilities.Constants;
import com.example.usuario.lambstore.models.IdModel;
import com.example.usuario.lambstore.repository.Repository;
import com.example.usuario.lambstore.repository.listener.RepositoryListener;
import com.example.usuario.lambstore.repository.mappers.Mapper;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Implementation of persistence of any type of Object that extends of IdModel.
 */

public class GenericMemoryRepositoryImp<T extends IdModel> implements Repository<T>{

    /**
     * The memory persistence
     */
    private Map<Integer,T> values;

    /**
     * The listener listens if data has changed.
     */
    private  RepositoryListener listener;

    /**
     * An interface between item and persistence
     */
    private Mapper<T> mapper;

    public GenericMemoryRepositoryImp(Mapper<T>mapper){
        this.values = new HashMap<>();
        this.mapper = mapper;
    }

    @Override
    public void add(T item) {
        values.put(item.getId(),item);
        listener.updateData();
        Iterator<Map.Entry<Integer,T>> iterator = values.entrySet().iterator();
        while (iterator.hasNext()){
            Log.d("add",iterator.next().getValue().toString());

        }
    }

    @Override
    public void add(List<T> items) {
        for (T item : items){
            add(item);
        }
    }

    @Override
    public Integer size() {
        return values.size();
    }

    @Override
    public void setListener(RepositoryListener listener) {
        this.listener=listener;
    }

    @Override
    public Map<Integer,T> getAll() {
        Iterator<Map.Entry<Integer,T>> iterator = values.entrySet().iterator();
        while (iterator.hasNext()){
            Log.d("getall",iterator.next().getValue().toString());

        }
        return values;
    }

    @Override
    public void update(T item) {
        values.remove(item);
        values.put(item.getId(),item);
        listener.updateData();
    }

    @Override
    public void remove(T item) {
        values.remove(item);
        listener.updateData();
    }

    @Override
    public Map<Integer,T> getItemsBy(String field, Object value) {
        Map<Integer,T> map = new HashMap<>();
        Iterator<Map.Entry<Integer,T>> iterator = values.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<Integer,T> valueEntry = iterator.next();
            ContentValues contentValues = mapper.getContentValues(valueEntry.getValue());
            T item =valueEntry.getValue();
            if(contentValues!=null && contentValues.get(field).equals(value)){
                map.put((Integer)contentValues.get(Constants.ID),item);
            }
        }
        return map;
    }

    @Override
    public T getItemById(Integer id) {
        return values.get(id);
    }
}
