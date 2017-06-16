package com.example.usuario.lambstore.Utilities;

import android.content.Context;
import android.os.Environment;

import com.example.usuario.lambstore.models.TransactionItem;
import com.frosquivel.magicalcamera.MagicalCamera;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Item and TransactionItem utilities
 */
public class ItemUtilities {

    /**
     * Gets the amount of item prices of a purchase.
     * @return {@link String}, the value of purchase.
     */
    public Integer getTotalPricePurchase(List<TransactionItem> items ){
        Integer amount = 0;
        for(TransactionItem item:items){
            amount = amount+item.getPriceTransaction();
        }
        return amount;
    }

    /**
     * Gets a new file to load a photo.
     * @param context
     * @return
     * @throws IOException
     */
    public File createImageFile(Context context) throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
//        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    /**
     * Validate if magicalcamera object is null of not have photo, and shown a message if it is null.
     * @param magicalCamera
     * @return return true or false
     */
    public boolean validateMagicalCameraNull(MagicalCamera magicalCamera){
        if(magicalCamera != null) {
            if (magicalCamera.getPhoto() != null) {
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

}
