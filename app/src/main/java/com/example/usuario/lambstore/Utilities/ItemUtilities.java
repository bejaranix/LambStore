package com.example.usuario.lambstore.Utilities;

import com.example.usuario.lambstore.models.TransactionItem;

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
}
