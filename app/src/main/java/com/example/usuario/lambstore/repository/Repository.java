package com.example.usuario.lambstore.repository;


import com.example.usuario.lambstore.repository.listener.RepositoryListener;

import java.util.List;
import java.util.Map;

/**
 * Repository interface to implement the repository design pattern to data persistence
 */
public interface Repository<T> {

    /**
     * Persists the item.
     * @param T item, the item to add
     */
    void add(T item);

    /**
     * Persists the items.
     * @param {@link List} items, the items to add
     */
    void add(List<T> item);

    /**
     * Get all the items.
     * @return {@Map Set}, the list of items.
     */
    Map<Integer, T> getAll();

    /**
     * Updates the values of the item.
     * @param T item, the item to update.
     */
    void update(T item);

    /**
     * Removes the element to the persistence.
     * @param T item, the item to remove
     */
    void remove(T item);

    /**
     * Gets the items, given a field and the value of the field.
     * @param {@Link String} field, the field.
     * @param {@Link Object}value, the value.
     * @return {@Link Map}, the list of items.
     */
    Map<Integer, T> getItemsBy(String field, Object value);

    /**
     * Gets an Item, given the id.
     * @param {@Link Integer}id, the id of item
     * @return  T item, the item to get.
     */
    T getItemById(Integer id);

    /**
     * Gets the size of the data
     * @return {@Link Intege}, the size of data.
     */
    Integer size();

    /**
     * Calls to the listener when the data has changed.
     * @param {@link RepositoryListener}listener, the listener
     */
    void setListener(RepositoryListener listener);



}
