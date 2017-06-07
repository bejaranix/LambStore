package com.example.usuario.lambstore.repository.impl;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.usuario.lambstore.database.DatabaseAdapter;
import com.example.usuario.lambstore.models.IdModel;
import com.example.usuario.lambstore.repository.Repository;
import com.example.usuario.lambstore.repository.Specification.ModelSpecification;
import com.example.usuario.lambstore.repository.listener.RepositoryListener;
import com.example.usuario.lambstore.repository.mappers.Mapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of persistence in database of any type of Object that extends of IdModel.
 */
public class SQLiteRepositoryImpl<T extends IdModel> implements Repository<T>{

    /**
     * The listener listens if data has changed.
     */
    private  RepositoryListener listener;

    /**
     * The application's context
     */
    private Context context;

    /**
     * The mapper of the object to use in database
     */
    private Mapper<T> mapper;

    /**
     * The database.
     */
    private SQLiteDatabase database;

    /**
     * The sql commands generator
     */
    private ModelSpecification specification;


    public SQLiteRepositoryImpl(Context context, Mapper<T> mapper, ModelSpecification specification){
        this.context = context;
        this.mapper = mapper;
        this.database = DatabaseAdapter.getDB(context);
        this.specification=specification;
    }

    /**
     * Persists the item.
     *
     * @param item
     */
    @Override
    public void add(T item) {
        Long autoincrementValue = database.insert(mapper.getTableName()
                ,null,
                mapper.getContentValues(item));
        item.setId(autoincrementValue);
    }

    /**
     * Persists the items.
     *
     * @param item
     */
    @Override
    public void add(List<T> items) {
        for(T item:items){
            add(item);
        }
    }

    /**
     * Get all the items.
     *
     * @return {@Map Set}, the list of items.
     */
    @Override
    public List<T> getAll() {
        Cursor cursor = database.query(mapper.getTableName()
                ,null,
                null, null, null, null, null);
        return getResults(cursor);
    }

    /**
     * Updates the values of the item.
     *
     * @param item
     */
    @Override
    public void update(T item) {
        database.update(mapper.getTableName()
                ,mapper.getContentValues(item)
                ,specification.getSearchingClauseIdQuery()
                , new String[]{item.getId().toString()});
    }

    /**
     * Removes the element to the persistence.
     *
     * @param item
     */
    @Override
    public void remove(T item) {
        database.delete(mapper.getTableName(),
                specification.getSearchingClauseIdQuery(),
                new String[]{item.getId().toString()});
    }

    /**
     * Gets the items, given a field and the value of the field.
     *
     * @param field
     * @param value
     * @return {@Link Map}, the list of items.
     */
    @Override
    public List<T> getItemsBy(String field, Object value) {
        Cursor cursor = database.query(mapper.getTableName(),mapper.getColumnNames(),
                specification.getSearchingClauseQuery(field),
                new String[]{value.toString()},
                null, null, null);
        return getResults(cursor);
    }

    /**
     * Gets an Item, given the id.
     *
     * @param id@return T item, the item to get.
     */
    @Override
    public T getItemById(Integer id) {
        Cursor cursor = database.query(mapper.getTableName(),
                mapper.getColumnNames(),
                specification.getSearchingClauseIdQuery(),
                new String[]{id.toString()},
                null, null, null);
        return getResult(cursor);
    }

    /**
     * Gets the size of the data
     *
     * @return {@Link Intege}, the size of data.
     */
    @Override
    public Integer size() {
        Cursor cursor = database.query(mapper.getTableName()
                ,mapper.getColumnNames(),
                null, null, null, null, null);
        return getResults(cursor).size();
    }

    /**
     * Calls to the listener when the data has changed.
     *
     * @param listener
     */
    @Override
    public void setListener(RepositoryListener listener) {
        this.listener=listener;
    }


    /**
     * Gets the results of the cursor.
     * @param {@link Cursor }cursor, the cursor of results.
     * @return {@link List}, a list of results.
     */
    private List<T> getResults(Cursor cursor){
        List<T>list = new ArrayList<>();
        if(cursor!=null){
            if(cursor.getCount()>0){
                while (cursor.moveToNext()){
                    list.add(mapper.getItem(cursor));
                }
            }
        }
        cursor.close();
        return list;
    }

    /**
     * Gets the first result of the cursor.
     * @param {@link Cursor }cursor, the cursor of results.
     * @return T, the first result.
     */
    private T getResult(Cursor cursor){
        T element = null;
        if(cursor!=null){
            if(cursor.getCount()>0){
                cursor.moveToNext();
                element = mapper.getItem(cursor);
            }
        }
        cursor.close();
        return element;
    }
}
