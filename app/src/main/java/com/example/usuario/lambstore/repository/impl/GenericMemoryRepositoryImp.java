package com.example.usuario.lambstore.repository.impl;

import android.content.ContentValues;import android.util.Log;

import com.example.usuario.lambstore.models.IdModel;
import com.example.usuario.lambstore.repository.Repository;
import com.example.usuario.lambstore.repository.listener.RepositoryListener;
import com.example.usuario.lambstore.repository.mappers.Mapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of persistence in memory of any type of Object that extends of IdModel.
 */
public class GenericMemoryRepositoryImp<T extends IdModel> implements Repository<T>{

    /**
     * Counter that defines the id of item using {@link IdModel} interface to get/set value.
     */
    private Integer counter;

    /**
     * The memory persistence
     */
    private List<T> values;

    /**
     * The listener listens if data has changed.
     */
    private  RepositoryListener listener;

    /**
     * An interface between item and persistence
     */
    private Mapper<T> mapper;

    public GenericMemoryRepositoryImp(Mapper<T>mapper){
        this.values = new ArrayList<>();
        this.mapper = mapper;
        counter=0;
    }

    @Override
    public void add(T item) {
        item.setId((long)counter++);
        values.add(item);
        listener.updateData();
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
    public List<T> getAll() {

        return values;
    }

    @Override
    public void update(T item) {
        values.remove(item);
        values.add(item);
        listener.updateData();
    }

    @Override
    public void remove(T item) {
        values.remove(item);
        listener.updateData();
    }

    @Override
    public List<T> getItemsBy(String field, Object value) {
        List<T> list = new ArrayList<>();
        for (T localValue : values){
            ContentValues contentValues = mapper.getContentValues(localValue);
            if(contentValues!=null && contentValues.get(field).equals(value)){
                list.add(localValue);
            }
        }
        return list;
    }

    @Override
    public T getItemById(Integer id) {
        return values.get(id);
    }
}
