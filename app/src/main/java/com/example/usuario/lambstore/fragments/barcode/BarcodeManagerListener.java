package com.example.usuario.lambstore.fragments.barcode;

/**
 * Barcode manager listener, gets the text captured by the camera.
 */

public interface BarcodeManagerListener {

    /**
     * When Barcodemanage finds a barcode, returns the text of it.
     * @param barcode
     */
    void onBarcodeCaptured(String barcode);
}
