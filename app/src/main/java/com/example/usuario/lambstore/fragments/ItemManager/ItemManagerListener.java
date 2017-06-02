package com.example.usuario.lambstore.fragments.ItemManager;

import com.example.usuario.lambstore.models.Item;

/**
 * To use ItemManagerFragment, the Fragment that invokes it needs to implementate the listener.
 * Created by usuario on 01/06/17.
 */

public interface ItemManagerListener {

    /**
     * When the item was created, the listener gets the Item itself.
     * @param {@link Item} item, the item generate by ItemManagerFragment.
     */
    void onItemCreated(Item item);

    /**
     * If ItemManagerFragment was loaded, but it doesn´t create a Item and ItemManagerFragment is destroy,
     * invokes this method.
     */
    void onItemCancelled();

}
