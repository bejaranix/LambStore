package com.example.usuario.lambstore.fragments.ItemManager;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import com.example.usuario.lambstore.database.DatabaseAdapter;
import com.example.usuario.lambstore.models.Item;
import com.example.usuario.lambstore.repository.Specification.impl.GenericModelSpecificationImpl;
import com.example.usuario.lambstore.repository.impl.SQLiteRepositoryImpl;
import com.example.usuario.lambstore.repository.mappers.impl.ItemMapper;

/**
 * Fragment of Item selection or creation.
 */
public class ItemManagerFragment extends Fragment {

    /**
     * Edits the name of item
     */
    private EditText nameET;

    /**
     * Button that invokes the item creation
     */
    private ImageView createItemBtn;

    /**
     * Edits the price of item
     */
    private EditText priceET;

    /**
     * Edits the ean of item
     */
    private EditText eanET;

    /**
     * Invokes the barcode reader to edit the ean of item
     */
    private ImageView eanBtn;

    /**
     * Invokes the image to get the image of product
     */
    private ImageView imageBtn;

    /**
     * Button that invokes the returning of th item to the fragment that invokes this fragment
     */
    private RelativeLayout endItemBtn;

    /**
     * The class needs the fragment that launches this fragment, and it needs to implement the ItemManagerListener
     */
    private ItemManagerListener listener;

    private Context currentContext;

    /**
     * Creates a new ItemManagerFragment instances, it needs a ItemManagerListener.
     * @param {@link ItemManagerListener} listener, the listener;
     */
    public static ItemManagerFragment newInstance(ItemManagerListener listener) {

        Bundle args = new Bundle();
        ItemManagerFragment fragment = new ItemManagerFragment();
        fragment.setArguments(args);
        fragment.listener = listener;
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
        priceET = (EditText)view.findViewById(R.id.priceET);
        priceET.setTag(Constants.PRICE_TAG);
        eanET = (EditText)view.findViewById(R.id.eanET);
        eanBtn = (ImageView)view.findViewById(R.id.eanBtn);
        imageBtn = (ImageView)view.findViewById(R.id.imageBtn);
        endItemBtn = (RelativeLayout)view.findViewById(R.id.endItemBtn);
        currentContext = getContext();
        setListenersToButtons();
        return view;
    }

    /**
     * Sets the listeners to the buttons of the fragment.
     */
    private void setListenersToButtons(){
        endItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnItemToListener();

            }
        });
    }

    /**
     * Returns the item to Listener
     */
    private void returnItemToListener(){
        TextUtilities textUtilities = new TextUtilities();
        try {
            textUtilities.validatesFields(nameET ,priceET);
        }catch (IllegalArgumentException e){
            Toast.makeText(currentContext, e.getMessage(), Toast.LENGTH_SHORT).show();
            return;
        }
        String name = textUtilities.getTextValueOfEditorText(nameET);
        Log.d("returnItemToListener",textUtilities.getTextValueOfEditorText( priceET));
        Integer price = (int) Float.parseFloat(textUtilities.getTextValueOfEditorText(priceET))*100;
        String ean = textUtilities.getTextValueOfEditorText(eanET);
        Item item = new Item(name,price,ean);
        SQLiteRepositoryImpl<Item> repository = new SQLiteRepositoryImpl<>(this.getContext(),
                new ItemMapper(), new GenericModelSpecificationImpl());
        repository.add(item);
        Log.d("returnItemToListener",repository.getAll().toString());
        listener.onItemCreated(item);
        getFragmentManager().popBackStack();
    }

}
