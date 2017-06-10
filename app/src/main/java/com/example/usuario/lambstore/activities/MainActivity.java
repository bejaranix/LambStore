package com.example.usuario.lambstore.activities;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.usuario.lambstore.R;
import com.example.usuario.lambstore.Utilities.Constants;
import com.example.usuario.lambstore.fragments.CartListFragment;
import com.example.usuario.lambstore.fragments.ItemManager.ItemManagerFragment;
import com.example.usuario.lambstore.models.Item;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public static final int SCREEN_ORIENTATION_SENSOR_LANDSCAPE = 6;
    static final int REQUEST_IMAGE_CAPTURE = 1;


    private CartListFragment cartListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.bringToFront();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getBaseContext(), BarcodeActivity.class);
                Bundle bundle = new Bundle();
                bundle.putBoolean(Constants.RETURN_ITEM,true);
                myIntent.putExtras(bundle);
                startActivityForResult(myIntent, 100);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        cartListFragment = CartListFragment.newInstance(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.content,cartListFragment).addToBackStack(Constants.CART_FRAGMENT).commit();

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.addItemItem) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content, ItemManagerFragment.newInstance(cartListFragment,this,true))
                    .addToBackStack(Constants.CART_FRAGMENT).commit();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private static DataReceivedListener listener;


    public static void setDataReceivedListener(DataReceivedListener listener){
        MainActivity.listener=listener;
    }

    public interface DataReceivedListener{
        void onReceived(int requestCode, int resultCode, Intent data);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (listener!=null){
            listener.onReceived(requestCode, resultCode, data);
            return;
        }

        Log.d("onActivityResult","ok");
        String ean = null;
        if(!data.getBooleanExtra(Constants.RETURN_ITEM,true)){
            if((ean = data.getStringExtra(Constants.BARCODE_RETURN))!=null) {
                listener.onReceived(requestCode, resultCode, data);
            }
        }else{
            Item item = null;
            Log.d("onActivityResult","ok3");

            if((item = (Item)data.getSerializableExtra(Constants.ITEM_RETURN))!=null){
                cartListFragment.setItem(item);
                Log.d("onActivityResult","ok4");

            }else{
                if((ean = data.getStringExtra(Constants.BARCODE_RETURN))!=null){
                    cartListFragment.setEAN(ean);
                    Log.d("onActivityResult","ok5");

                }
                Log.d("onActivityResult","ok6");

            }
        }

    }
}
