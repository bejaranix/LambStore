package com.example.usuario.lambstore.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.usuario.lambstore.R;
import com.example.usuario.lambstore.models.Purchase;

/**
 * Fragment that prints a ticket, using a Purchase object.
 */
public class TicketFragment extends Fragment {

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
        return inflater.inflate(R.layout.fragment_ticket, container, false);
    }


}
