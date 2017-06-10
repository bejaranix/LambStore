package com.example.usuario.lambstore.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.usuario.lambstore.R;
import com.example.usuario.lambstore.Utilities.Constants;
import com.example.usuario.lambstore.Utilities.ItemUtilities;
import com.example.usuario.lambstore.Utilities.TextUtilities;
import com.example.usuario.lambstore.adapters.ItemRVAdapter;
import com.example.usuario.lambstore.adapters.listener.ItemRVAdapterListener;
import com.example.usuario.lambstore.fragments.ItemListManager.ItemListFragment;
import com.example.usuario.lambstore.fragments.ItemManager.ItemManagerFragment;
import com.example.usuario.lambstore.fragments.ItemManager.ItemManagerListener;
import com.example.usuario.lambstore.fragments.TicketManager.TicketFragment;
import com.example.usuario.lambstore.models.Item;
import com.example.usuario.lambstore.models.Purchase;
import com.example.usuario.lambstore.models.TransactionItem;
import com.example.usuario.lambstore.repository.Repository;
import com.example.usuario.lambstore.repository.impl.GenericMemoryRepositoryImp;
import com.example.usuario.lambstore.repository.mappers.impl.TransactionItemMapper;

import java.util.Date;

/**
 * Fragment of the cart list.
 */
public class CartListFragment extends Fragment implements ItemManagerListener, ItemRVAdapterListener<TransactionItem> {
//    private List<TransactionItem> items;
    private RelativeLayout endBuyingBtn;
    private TextView parcialTV;
    private FragmentActivity fragmentActivity;
    private Date currentDate;
    private Long transactionId = 1234567890L;
    private Repository<TransactionItem> repository;

    /**
     * Creates a new CartListFragment instances, it needs a FragmentActivity.
     * @param {@link FragmentActivity} fragmentActivity, the fragmentActivity;
     */
    public static CartListFragment newInstance(FragmentActivity fragmentActivity) {

        Bundle args = new Bundle();

        CartListFragment fragment = new CartListFragment();
        fragment.setArguments(args);
        fragment.fragmentActivity = fragmentActivity;
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createData();
        repository = new GenericMemoryRepositoryImp<>(new TransactionItemMapper());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_cart_list, container, false);

        ItemListFragment itemListFragment = ItemListFragment.newInstance(getContext(),repository, TransactionItem.class, this);
        fragmentActivity.getSupportFragmentManager().beginTransaction().replace(R.id.itemList,itemListFragment).commit();

        endBuyingBtn =(RelativeLayout)view.findViewById(R.id.endBuyingBtn);
        parcialTV = (TextView) view.findViewById(R.id.parcialTV);
      //  endBuyingBtn.setVisibility(View.GONE);
        setListenersToButtons();
        setParcialTVValue();
        startCart();
        return view;
    }

    /**
     * Sets the listeners to the buttons of the fragment.
     */
    private void setListenersToButtons() {
        endBuyingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("setListenersToButtons",""+transactionId);

                Purchase purchase = new Purchase(currentDate,transactionId,repository.getAll());
                fragmentActivity.getSupportFragmentManager().beginTransaction().replace(R.id.content, TicketFragment.newInstance(purchase)).commit();
            }
        });
    }

    /**
     * Creates dummy data, delete when repository is implementated.
     */
    private void createData(){

    }


    @Override
    public void onItemCreated(Item item) {
        addItem(item);
    }

    /**
     * Adds items to the list and UI
     * @param {@Link Item} item, the item from ItemManagerFragment
     */
    private void addItem(Item item){
        TransactionItem tItem = new TransactionItem(new Date().getTime(),item,Constants.DEFAULT_CART_ITEM_NUMBER,item.getPrice(),transactionId,currentDate);
        repository.add(tItem);
        if(repository.size()>0){
          //  endBuyingBtn.setVisibility(View.VISIBLE);
            setParcialTVValue();
        }

    }

    /**
     * Starts the cart purchasing values.
     */
    private void startCart(){
        currentDate = new Date();
    }

    /**
     * Sets the parcial value to parcialTV UI TextView.
     */
    private void setParcialTVValue(){
        Integer parcial=new ItemUtilities().getTotalPricePurchase(repository.getAll());
        parcialTV.setText(new TextUtilities().getParcialText(parcial));
    }

    @Override
    public void onItemCancelled() {

    }


    /**
     * Retrieves the item selected.
     *
     * @param item
     */
    @Override
    public void onItemClicked(TransactionItem item) {
        Log.d("onItemClicked", item.toString());
    }

    /**
     * Gets item from activity
     */
    public void setItem(Item item){
        addItem(item);
    }

    /**
     * Gets ean from activity
     * @param ean
     */
    public void setEAN(String ean){
        Toast.makeText(getActivity(), "CART "+ ean, Toast.LENGTH_LONG).show();
        ItemManagerFragment itemManagerFragment = ItemManagerFragment.newInstance(this,fragmentActivity,false);
        itemManagerFragment.setEANValue(ean);
        fragmentActivity.getSupportFragmentManager().beginTransaction()
                .replace(R.id.content,itemManagerFragment).addToBackStack(null).commit();
    }


}
