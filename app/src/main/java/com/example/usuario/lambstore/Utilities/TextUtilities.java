package com.example.usuario.lambstore.Utilities;

import android.widget.EditText;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Format, validation text utilities
 */

public class TextUtilities {

    /**
     * Validates if text is null or empty, ti is util if needs to validate the fields of a EditorText.
     * @param {@link String} text, the text to validate.
     * @return Boolean, true if text is null or empty, otherwise false
     */
    public Boolean isTextNullOrEmpty(String text){
        if(text == null || Constants.EMPTY_STRING.equals(text)){
            return true;
        }
        return false;
    }

    /**
     * Validates all the field strings of a form.
     * @param {@link String ... }fields, the fields
     * @throws IllegalArgumentException
     */
    public void validatesFields(String ... fields) throws IllegalArgumentException{
        for(String field:fields){
            if(isTextNullOrEmpty(field)){
                throw new IllegalArgumentException( String.format(Constants.EMPTY_FIELD_MESSAGE, field) );
            }
        }
    }

    /**
     * Validates all the fields of a form.
     * @param {@link EditText ... }fields, the fields
     * @throws IllegalArgumentException
     */
    public void validatesFields(EditText ... fields) throws IllegalArgumentException{
        for(EditText et: fields){
            String stringField = getTextValueOfEditorText(et);
            if(isTextNullOrEmpty(stringField)){
                throw new IllegalArgumentException( String.format(Constants.EMPTY_FIELD_MESSAGE, (String)et.getTag()) );
            }
        }
    }

    /**
     * Gets the value of an EditText;
     * @param {@link EditText} et, the EditText
     * @return {@Link String} the string into EditText
     */
    public String getTextValueOfEditorText(EditText et){
        return et.getText().toString();
    }

    /**
     * Creates the text of parcial amount to UI.
     * @param {@link Integer} value, the value of the
     * @return {@link String} the formatted value to UI.
     */
    public String getParcialText(Integer value){
        return Constants.PARCIAL_TEXT+getMoneyText(value);
    }

    /**
     * Creates the text of total amount to UI.
     * @param {@link Integer} value, the value of the
     * @return {@link String} the formatted value to UI.
     */
    public String getTotalText(Integer value){
        return Constants.TOTAL_TEXT+getMoneyText(value);
    }

    /**
     * Creates the text of money amount to UI.
     * @param {@link Integer} value, the value of the value
     * @return {@link String} the formatted value to UI.
     */
    public String getMoneyText(Integer value){
        return String.format(Constants.MONEY_TEXT, ((float)value)/100);

    }

    /**
     * Creates the text of transaction id to UI.
     * @param {@link Integer} value, the value of the transaction id
     * @return {@link String} the formatted value to UI.
     */
    public String getTransactionIdText(Integer transactionId){
        return Constants.TRANSACTION_ID_TAG+transactionId;
    }

    /**
     * Creates the text of date to UI.
     * @param {@link Date} value, the value of the date
     * @return {@link String} the formatted value to UI.
     */
    public String getDateText(Date date){
        return Constants.DATE_TAG+date.toString();
    }

}
