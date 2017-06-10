package com.example.usuario.lambstore.activities;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.usuario.lambstore.R;
import com.example.usuario.lambstore.Utilities.Constants;
import com.example.usuario.lambstore.fragments.ItemManager.ItemManagerListener;
import com.example.usuario.lambstore.fragments.barcode.BarcodeManagerFragment;
import com.example.usuario.lambstore.fragments.barcode.BarcodeManagerListener;
import com.example.usuario.lambstore.models.Item;
import com.example.usuario.lambstore.repository.Repository;
import com.example.usuario.lambstore.repository.Specification.impl.GenericModelSpecificationImpl;
import com.example.usuario.lambstore.repository.impl.SQLiteRepositoryImpl;
import com.example.usuario.lambstore.repository.mappers.impl.ItemMapper;

import java.util.List;

/**
 * Manages the barcode reader.
 */
public class BarcodeActivity extends AppCompatActivity implements BarcodeManagerListener{

    private boolean getItem=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode);
        Intent intent = getIntent();
        getItem=intent.getBooleanExtra(Constants.RETURN_ITEM,true);

        if (PackageManager.PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(BarcodeActivity.this, Manifest.permission.CAMERA))
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.content, BarcodeManagerFragment.newInstance(this)).commit();
            BarcodeActivity.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        else{
            requestWritePermission(this);
        }
    }

    private static void requestWritePermission(final Context context)
    {
        if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.CAMERA))
        {
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.CAMERA}, 1);
        }
        else
        {
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.CAMERA}, 1);
        }
    }


    /**
     * When Barcodemanage finds a barcode, returns the text of it.
     *
     * @param barcode
     */
    @Override
    public void onBarcodeCaptured(String barcode) {
        Bundle conData = new Bundle();
        if(getItem) {
            Repository<Item> repository = new SQLiteRepositoryImpl<>(this,
                    new ItemMapper(), new GenericModelSpecificationImpl());
            List<Item> barcodes = repository.getItemsBy(ItemMapper.EAN, barcode);
            if (barcodes != null && barcodes.size() > 0) {
                conData.putSerializable(Constants.ITEM_RETURN, barcodes.get(0));
            }
        }
        conData.putString(Constants.BARCODE_RETURN, barcode);
        conData.putBoolean(Constants.RETURN_ITEM,getItem);
        Intent intent = new Intent();
        intent.putExtras(conData);
        setResult(RESULT_OK, intent);
        finish();
    }
}
