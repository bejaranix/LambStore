package com.example.usuario.lambstore.fragments.TicketManager;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.usuario.lambstore.R;
import com.example.usuario.lambstore.Utilities.Constants;
import com.example.usuario.lambstore.Utilities.ItemUtilities;
import com.example.usuario.lambstore.Utilities.TextUtilities;
import com.example.usuario.lambstore.activities.MainActivity;
import com.example.usuario.lambstore.adapters.ItemRVAdapter;
import com.example.usuario.lambstore.adapters.listener.ItemRVAdapterListener;
import com.example.usuario.lambstore.fragments.CartListFragment;
import com.example.usuario.lambstore.models.Purchase;
import com.example.usuario.lambstore.models.TransactionItem;
import com.example.usuario.lambstore.repository.Repository;
import com.example.usuario.lambstore.repository.impl.GenericMemoryRepositoryImp;
import com.example.usuario.lambstore.repository.mappers.impl.TransactionItemMapper;

/**
 * Fragment that prints a ticket, using a Purchase object.
 */
public class TicketFragment extends Fragment implements ItemRVAdapterListener {

    private RecyclerView rvPurchasingList;
    private ItemRVAdapter adapter;
    private RelativeLayout backBtn;
    private TextView totalTV;
    private TextView idTransactionTV;
    private TextView dateTV;
    private Repository<TransactionItem> repository;


    /**
     * The purchase to print.
     */
    private Purchase purchase;

    public static TicketFragment newInstance(Purchase purchase) {
        Bundle args = new Bundle();
        TicketFragment fragment = new TicketFragment();
        fragment.setArguments(args);
        fragment.purchase = purchase;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view  = inflater.inflate(R.layout.fragment_ticket, container, false);
        repository = new GenericMemoryRepositoryImp<>(new TransactionItemMapper());
        rvPurchasingList = (RecyclerView) view.findViewById(R.id.rvPurchasingList);
        rvPurchasingList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        adapter = new ItemRVAdapter(this.getContext(),repository, TransactionItem.class,this);
        rvPurchasingList.setAdapter(adapter);
        repository.add( purchase.getItems());
        backBtn =(RelativeLayout)view.findViewById(R.id.backBtn);
        totalTV = (TextView) view.findViewById(R.id.totalTV);
        idTransactionTV = (TextView) view.findViewById(R.id.idTransactionTV);
        dateTV = (TextView) view.findViewById(R.id.dateTV);
        setListenersToButton();
        setTextValues();
        return view;
    }

    /**
     * Sets the listeners to the buttons of the fragment.
     */
    private void setListenersToButton(){
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToCart();
            }
        });
    }

    /**
     * Set values to Text fields
     */
    private void setTextValues(){
        TextUtilities textUtilities = new TextUtilities();
        Log.d("setTextValues",""+purchase.getId());
        idTransactionTV.setText(textUtilities.getTransactionIdText(purchase.getId()));
        dateTV.setText(textUtilities.getDateText(purchase.getDate()));
        Integer total=new ItemUtilities().getTotalPricePurchase(purchase.getItems());
        totalTV.setText(textUtilities.getTotalText(total));
    }

    /**
     * Creates a new cart, and return to CartListFragment
     */
    private void backToCart(){
        FragmentManager fm = getActivity().getSupportFragmentManager();
        int count = fm.getBackStackEntryCount();
        for(int i = 0; i < count; ++i) {
            fm.popBackStackImmediate();
        }
        CartListFragment cartListFragment = CartListFragment.newInstance(this.getActivity());
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content,cartListFragment).commit();
        MainActivity.setCartListFragment(cartListFragment);
    }

    /**
     * Retrieves the item selected.
     *
     * @param item
     */
    @Override
    public void onItemClicked(Object item) {

    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().findViewById(R.id.fab).setVisibility(View.GONE);
        getActivity().findViewById(R.id.addItemItem).setVisibility(View.GONE);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.addItemItem).setEnabled(false);
        super.onPrepareOptionsMenu(menu);
        Log.d("onPrepareOptionsMenu", "invisible");

    }

}
