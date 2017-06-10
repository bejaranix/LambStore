package com.example.usuario.lambstore.fragments.ItemManager;


import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.usuario.lambstore.R;
import com.example.usuario.lambstore.Utilities.Constants;
import com.example.usuario.lambstore.Utilities.ItemUtilities;
import com.example.usuario.lambstore.activities.BarcodeActivity;
import com.example.usuario.lambstore.activities.MainActivity;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import static android.app.Activity.RESULT_OK;

/**
 * Contains the item form to create a new one (item).
 */
public class ItemFormFragment extends Fragment implements MainActivity.DataReceivedListener {

    static final int REQUEST_IMAGE_CAPTURE = 1;

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
     * Item form listener
     */
    private ItemFormListener itemFormListener;

    /**
     * A custom ean.
     */
    private String ean="";

    /**
     * The utilities
     */
    private ItemUtilities utilities = new ItemUtilities();

    public static ItemFormFragment newInstance(ItemFormListener itemFormListener, String ean) {

        Bundle args = new Bundle();

        ItemFormFragment fragment = new ItemFormFragment();
        fragment.setArguments(args);
        fragment.itemFormListener = itemFormListener;
        if(ean!=null) {
            fragment.ean = ean;
        }
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_item_form, container, false);

        priceET = (EditText)view.findViewById(R.id.priceET);
        priceET.setTag(Constants.PRICE_TAG);
        eanET = (EditText)view.findViewById(R.id.eanET);
        eanET.setText(ean);
        eanBtn = (ImageView)view.findViewById(R.id.eanBtn);
        imageBtn = (ImageView)view.findViewById(R.id.imageBtn);
        endItemBtn = (RelativeLayout)view.findViewById(R.id.endItemBtn);
        setListenersToButtons();
        return view;
    }

    /**
     * Sets the listeners to the buttons of the fragment.
     */
    private void setListenersToButtons(){
        imageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
                MainActivity.setDataReceivedListener(ItemFormFragment.this);
            }
        });

        endItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemFormListener.sendForm();
            }
        });
        eanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getContext(), BarcodeActivity.class);
                Bundle bundle = new Bundle();
                bundle.putBoolean(Constants.RETURN_ITEM,false);
                myIntent.putExtras(bundle);
                startActivityForResult(myIntent, 100);
                MainActivity.setDataReceivedListener(ItemFormFragment.this);
            }
        });
    }

    /*
    Getters and setters
     */

    public EditText getPriceET() {
        return priceET;
    }

    public void setPriceET(EditText priceET) {
        this.priceET = priceET;
    }

    public EditText getEanET() {
        return eanET;
    }

    public void setEanET(EditText eanET) {
        this.eanET = eanET;
    }

    public ImageView getEanBtn() {
        return eanBtn;
    }

    public void setEanBtn(ImageView eanBtn) {
        this.eanBtn = eanBtn;
    }

    public ImageView getImageBtn() {
        return imageBtn;
    }

    public void setImageBtn(ImageView imageBtn) {
        this.imageBtn = imageBtn;
    }

    @Override
    public void onReceived(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageBtn.setImageBitmap(photo);
            return;
        }

        MainActivity.setDataReceivedListener(null);
        if((ean = data.getStringExtra(Constants.BARCODE_RETURN))!=null) {
            eanET.setText(ean);
        }
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getContext().getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = utilities.createImageFile(getContext());
            } catch (Exception ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(getContext(),
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }
}
