package com.example.usuario.lambstore.fragments.ItemListManager;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.usuario.lambstore.R;
import com.example.usuario.lambstore.adapters.ItemRVAdapter;
import com.example.usuario.lambstore.adapters.listener.ItemRVAdapterListener;
import com.example.usuario.lambstore.fragments.ItemManager.ItemManagerListener;
import com.example.usuario.lambstore.fragments.ItemManager.ItemManagerNameTextListener;
import com.example.usuario.lambstore.models.Item;
import com.example.usuario.lambstore.models.NameModel;
import com.example.usuario.lambstore.models.TransactionItem;
import com.example.usuario.lambstore.repository.Repository;

/**
 * The fragment that contains a list of Items.
 */
public class ItemListFragment<T extends NameModel> extends Fragment implements ItemManagerNameTextListener {

    /**
     * The repository with the data.
     */
    private Repository<T> repository;

    /**
     * The adapter of Item List.
     */
    private ItemRVAdapter adapter;

    /**
     * The application context.
     */
    private Context context;

    /**
     * The recycler view
     */
    private RecyclerView rvCartList;

    /**
     * The type of item to load
     */
    private Class clazz;

    /**
     * The ItemRVAdapter Listener
     */
    private ItemRVAdapterListener<T> listener;

    /**
     * Listens the nameET text changed event in ItemManagerListener.
     */
    private ItemManagerNameTextListener itemManagerNameTextListener;


    public ItemListFragment() {
        // Required empty public constructor
    }


    public static <T> ItemListFragment newInstance(
            Context context, Repository<T> repository,
            Class clazz,
            ItemRVAdapterListener<T> listener

    ) {
        Bundle args = new Bundle();
        ItemListFragment fragment = new ItemListFragment();
        fragment.setArguments(args);
        fragment.context = context;
        fragment.clazz=clazz;
        fragment.repository=repository;
        fragment.listener= listener;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        adapter = new ItemRVAdapter(context,repository, clazz,listener);
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        rvCartList = (RecyclerView) view.findViewById(R.id.rvCartList);
        rvCartList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rvCartList.setAdapter(adapter);
        this.itemManagerNameTextListener = adapter;
        return view;

    }


    /**
     * Listens when text has been changed and gets the text itself.
     *
     * @param text
     */
    @Override
    public void onTextChanged(String text) {
        if(itemManagerNameTextListener!=null) {
            itemManagerNameTextListener.onTextChanged(text);
        }
    }
}
