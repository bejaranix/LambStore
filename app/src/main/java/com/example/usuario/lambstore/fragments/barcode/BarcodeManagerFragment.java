package com.example.usuario.lambstore.fragments.barcode;


import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.abhi.barcode.frag.libv2.BarcodeFragment;
import com.abhi.barcode.frag.libv2.IScanResultHandler;
import com.abhi.barcode.frag.libv2.ScanResult;
import com.example.usuario.lambstore.R;


/**
 * A barcode manager, gets the barcode using the camra and returns the text.
 */
public class BarcodeManagerFragment extends Fragment  implements IScanResultHandler {


    private BarcodeFragment brf;

    private BarcodeManagerListener listener;

    public static BarcodeManagerFragment newInstance( BarcodeManagerListener listener) {

        Bundle args = new Bundle();

        BarcodeManagerFragment fragment = new BarcodeManagerFragment();
        fragment.setArguments(args);
        fragment.listener=listener;
        return fragment;
    }
    public BarcodeManagerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_barcode, container, false);
            return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        brf = (BarcodeFragment) getChildFragmentManager().findFragmentById(R.id.barcodeFragment);
        brf.setScanResultHandler(this);
    }

    @Override
    public void scanResult(ScanResult scanResult) {
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //Toast.makeText(getActivity(), scanResult.getRawResult().getText(), Toast.LENGTH_LONG).show();
        listener.onBarcodeCaptured(scanResult.getRawResult().getText());
    }
}
