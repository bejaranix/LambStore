package com.example.usuario.lambstore.fragments.ItemManager;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.usuario.lambstore.R;
import com.example.usuario.lambstore.Utilities.Constants;
import com.example.usuario.lambstore.Utilities.TextUtilities;
import com.example.usuario.lambstore.adapters.listener.ItemRVAdapterListener;
import com.example.usuario.lambstore.database.DatabaseAdapter;
import com.example.usuario.lambstore.fragments.ItemListManager.ItemListFragment;
import com.example.usuario.lambstore.models.Item;
import com.example.usuario.lambstore.repository.Repository;
import com.example.usuario.lambstore.repository.Specification.impl.GenericModelSpecificationImpl;
import com.example.usuario.lambstore.repository.impl.SQLiteRepositoryImpl;
import com.example.usuario.lambstore.repository.mappers.impl.ItemMapper;

/**
 * Fragment of Item selection or creation.
 */
public class ItemManagerFragment extends Fragment implements ItemFormListener, ItemRVAdapterListener<Item> {

    /**
     * Edits the name of item.
     */
    private EditText nameET;

    /**
     * Button that invokes the item creation.
     */
    private ImageView createItemBtn;

    /**
     * The class needs the fragment that launches this fragment, and it needs to implement the ItemManagerListener.
     */
    private ItemManagerListener listener;

    /**
     * The current context.
     */
    private Context currentContext;

    /**
     * The fragment activity.
     */
    private FragmentActivity fragmentActivity;

    /**
     * Item form fragment.
     */
    private ItemFormFragment itemFormFragment;

    /**
     * Item list fragment.
     */
    private ItemListFragment itemListFragment;

    /**
     * The data repository
     */
    private Repository<Item> repository;

    /**
     * Listens the nameET text changed event.
     */
    private ItemManagerNameTextListener itemManagerNameTextListener;

    /**
     * Text utilities.
     */
    private TextUtilities textUtilities = new TextUtilities();

    /**
     * Shows item list?
     */
    private Boolean showItemList;

    /**
     * A custom ean.
     */
    private String ean;

    /**
     * Creates a new ItemManagerFragment instances, it needs a ItemManagerListener.
     * @param {@link ItemManagerListener} listener, the listener;
     */
    public static ItemManagerFragment newInstance(ItemManagerListener listener, FragmentActivity fragmentActivity, Boolean showItemList) {

        Bundle args = new Bundle();
        ItemManagerFragment fragment = new ItemManagerFragment();
        fragment.setArguments(args);
        fragment.listener = listener;
        fragment.fragmentActivity = fragmentActivity;
        fragment.showItemList = showItemList;
        fragment.ean="";
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_item_manager, container, false);
        nameET = (EditText)view.findViewById(R.id.nameET);
        nameET.setTag(Constants.NAME_TAG);
        createItemBtn = (ImageView)view.findViewById(R.id.createItemBtn);
        currentContext = getContext();
        this.itemFormFragment = ItemFormFragment.newInstance(this,ean);
        this.repository=new SQLiteRepositoryImpl<>(currentContext,
                new ItemMapper(), new GenericModelSpecificationImpl());
        this.itemListFragment = ItemListFragment.newInstance(fragmentActivity,repository,Item.class, this);
        this.itemManagerNameTextListener = itemListFragment;
        setEvents();
        if(showItemList) {
            setiItemListFragment();
        }else{
            setFormFragment();
        }
        return view;
    }

    /**
     * Sets the Item list
     */
    private void setiItemListFragment(){
        fragmentActivity.getSupportFragmentManager().beginTransaction().replace(R.id.itemManagerContent,itemListFragment).commit();
    }


    /**
     * Sets the form
     */
    private void setFormFragment(){
        fragmentActivity.getSupportFragmentManager().beginTransaction().replace(R.id.itemManagerContent,itemFormFragment).commit();

    }

    /**
     * Sets the listeners to elements of the fragment.
     */
    private void setEvents(){
        nameET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                itemManagerNameTextListener.onTextChanged(textUtilities.getTextValueOfEditorText(nameET));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        createItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFormFragment();
            }
        });
    }

    /**
     * Returns the item to Listener
     */
    private void returnItemToListener(){
        try {
            textUtilities.validatesFields(nameET ,itemFormFragment.getPriceET());
        }catch (IllegalArgumentException e){
            Toast.makeText(currentContext, e.getMessage(), Toast.LENGTH_SHORT).show();
            return;
        }
        String name = textUtilities.getTextValueOfEditorText(nameET);
        Log.d("returnItemToListener",textUtilities.getTextValueOfEditorText(itemFormFragment.getPriceET()));
        Integer price = (int) Float.parseFloat(textUtilities.getTextValueOfEditorText(itemFormFragment.getPriceET()))*100;
        String ean = textUtilities.getTextValueOfEditorText(itemFormFragment.getEanET());
        Item item = new Item(name,price,ean);

        repository.add(item);
        Log.d("returnItemToListener",repository.getAll().toString());
        listener.onItemCreated(item);
        fragmentActivity.onBackPressed();
//        getFragmentManager().popBackStack(Constants.CART_FRAGMENT,0);
    }

    /**
     * Callback to advice the listener that retrieves the information.
     */
    @Override
    public void sendForm() {
        returnItemToListener();
    }

    /**
     * Retrieves the item selected.
     *
     * @param item
     */
    @Override
    public void onItemClicked(Item item) {
        listener.onItemCreated(item);
//        getFragmentManager().popBackStack(Constants.CART_FRAGMENT,0);
        fragmentActivity.onBackPressed();
    }

    /**
     * Sets the ean from activity.
     * @param ean
     */
    public void setEANValue(String ean){
        if(itemFormFragment!=null && itemFormFragment.getEanET()!=null){
            itemFormFragment.getEanET().setText(ean);
        }
        this.ean=ean;

    }
}
