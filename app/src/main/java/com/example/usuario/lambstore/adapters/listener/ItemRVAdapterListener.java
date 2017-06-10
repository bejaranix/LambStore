package com.example.usuario.lambstore.adapters.listener;

/**
 * Listens the Actions over the ItemRVAdapter.
 */
public interface ItemRVAdapterListener<T>{

    /**
     * Retrieves the item selected.
     * @param T item, the item that is in the component.
     */
    void onItemClicked(T item);

}
