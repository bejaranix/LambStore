package com.example.usuario.lambstore.fragments.ItemManager;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.usuario.lambstore.R;
import com.example.usuario.lambstore.Utilities.Constants;
import com.example.usuario.lambstore.Utilities.ItemUtilities;
import com.example.usuario.lambstore.activities.BarcodeActivity;
import com.example.usuario.lambstore.activities.MainActivity;
import com.example.usuario.lambstore.models.Item;
import com.frosquivel.magicalcamera.MagicalCamera;
import com.frosquivel.magicalcamera.MagicalPermissions;

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
     * The captured photo url
     */
    private String imgDir = "";

    /**
     * The utilities
     */
    private ItemUtilities utilities = new ItemUtilities();

    /**
     * The instance of camera using MagicalCamera library
     */
    private MagicalCamera magicalCamera;

    /**
     * The permissions that need MagicalCamera
     */
    private MagicalPermissions magicalPermissions;



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
        askForStoragePermissions();
        return view;
    }

    /**
     * Sets the listeners to the buttons of the fragment.
     */
    private void setListenersToButtons(){
        imageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] permissions = new String[]{
                        Manifest.permission.CAMERA,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                };

                //realized the instance of magical camera, this need the context, this need the context,
                //the percentage of quality photo and the permission granted
                magicalPermissions = new MagicalPermissions(getActivity(), permissions);
                magicalCamera = new MagicalCamera(getActivity(), 30, magicalPermissions);
                magicalCamera.takeFragmentPhoto(ItemFormFragment.this);
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

    private void askForStoragePermissions(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }

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


        if(data !=null && (ean = data.getStringExtra(Constants.BARCODE_RETURN))!=null) {
            eanET.setText(ean);
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && data == null) {
            magicalCamera.resultPhoto(requestCode, resultCode, data);
            if (utilities.validateMagicalCameraNull(magicalCamera)) {
                magicalCamera.setPhoto(magicalCamera.rotatePicture(magicalCamera.getPhoto(), MagicalCamera.ORIENTATION_ROTATE_90));
                imageBtn.setImageBitmap(magicalCamera.getPhoto());
                new AsyncTask<Void, Void, String>() {
                    protected void onPreExecute() {
                        Toast.makeText(getActivity(), "onPreExecute", Toast.LENGTH_SHORT).show();
                        getActivity().findViewById(R.id.loadingPanel).setVisibility(View.VISIBLE);
                    }

                    protected String doInBackground(Void... params) {

                        //save the picture inmemory device, and return the physical path of this photo
                        String path = magicalCamera.savePhotoInMemoryDevice(magicalCamera.getPhoto(),
                                Constants.FILE_PRODUCT_IMG,
                                Constants.DIRECTORY_PRODUCT_IMG,
                                MagicalCamera.PNG,
                                true);
                        Log.d("doInBackground", path);
                        if (path != null) {
                            Log.d("doInBackground", "ok");
                            imgDir = path;
                        } else {
                            Log.d("doInBackground", "NOT ok");
                        }
                        return null;
                    }

                    protected void onPostExecute(String msg) {
                        Toast.makeText(getActivity(), "onPostExecute", Toast.LENGTH_SHORT).show();
                        getActivity().findViewById(R.id.loadingPanel).setVisibility(View.GONE);
                    }
                }.execute();

            }

        }

    }

    public String getImgDir() {
        return imgDir;
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
