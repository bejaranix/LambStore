package com.example.usuario.lambstore.fragments.TicketManager;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.usuario.lambstore.R;
import com.example.usuario.lambstore.Utilities.ItemUtilities;
import com.example.usuario.lambstore.Utilities.TextUtilities;
import com.example.usuario.lambstore.adapters.ItemRVAdapter;
import com.example.usuario.lambstore.models.Purchase;
import com.example.usuario.lambstore.models.TransactionItem;
import com.example.usuario.lambstore.repository.Repository;
import com.example.usuario.lambstore.repository.impl.GenericMemoryRepositoryImp;
import com.example.usuario.lambstore.repository.mappers.impl.TransactionItemMapper;

/**
 * Fragment that prints a ticket, using a Purchase object.
 */
public class TicketFragment extends Fragment {

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
        adapter = new ItemRVAdapter(this.getContext(),repository);
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
        idTransactionTV.setText(textUtilities.getTransactionIdText(purchase.getId()));
        dateTV.setText(textUtilities.getDateText(purchase.getDate()));
        Integer total=new ItemUtilities().getTotalPricePurchase(purchase.getItems());
        totalTV.setText(textUtilities.getTotalText(total));
    }

    /**
     * Creates a new cart, and return to CartListFragment
     */
    private void backToCart(){
        getFragmentManager().popBackStack();
    }

}
