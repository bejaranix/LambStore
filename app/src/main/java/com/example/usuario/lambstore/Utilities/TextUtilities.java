package com.example.usuario.lambstore.Utilities;

import android.widget.EditText;

import java.util.ArrayList;
import java.util.Collections;
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
        return String.format(Constants.PARCIAL_TEXT, ((float)value)/100);
    }


}
