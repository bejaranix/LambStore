package com.example.usuario.lambstore.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.usuario.lambstore.R;
import com.example.usuario.lambstore.Utilities.TextUtilities;
import com.example.usuario.lambstore.adapters.listener.ItemRVAdapterListener;
import com.example.usuario.lambstore.fragments.ItemManager.ItemManagerNameTextListener;
import com.example.usuario.lambstore.models.Item;
import com.example.usuario.lambstore.models.NameModel;
import com.example.usuario.lambstore.models.TransactionItem;
import com.example.usuario.lambstore.repository.Repository;
import com.example.usuario.lambstore.repository.listener.RepositoryListener;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 *
 */

public class ItemRVAdapter <T extends NameModel> extends RecyclerView.Adapter<ItemRVAdapter.ViewHolder> implements RepositoryListener, ItemManagerNameTextListener {

    private Repository<T> itemRepository;
    private List<T> items;
    private Class clazz;
    private Context context;
    private ItemRVAdapterListener<T> listener;

    public ItemRVAdapter(Context context,Repository<T> itemRepository, Class clazz, ItemRVAdapterListener listener){
        Log.d("ItemRVAdapter","ItemRVAdapter");
        this.itemRepository = itemRepository;
        this.itemRepository.setListener(this);
        this.clazz=clazz;
        this.listener=listener;
        items = itemRepository.getAll();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_item,parent,false));
        this.context = parent.getContext();
        return holder;
    }

    @Override
    public void onBindViewHolder(ItemRVAdapter.ViewHolder holder, int position) {
        Item item = null;
        if(clazz == Item.class){
            item = (Item)items.get(position);
            holder.priceTV.setText(new TextUtilities().getMoneyText(item.getPrice()));
            holder.itemContainer.setTag(item);
        }
        else if(clazz == TransactionItem.class){
            final TransactionItem transactionItem = (TransactionItem)items.get(position);
            holder.priceTV.setText(new TextUtilities().getMoneyText(transactionItem.getPriceTransaction()));
            holder.itemContainer.setTag(transactionItem);
            item = transactionItem.getItem();

        }
        else{
            throw new AssertionError("It must not happend");
        }
        holder.nameTV.setText(item.getName());
        holder.eanTV.setText(item.getEan());
        holder.itemContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClicked((T)v.getTag());
            }
        });
        if(item.getImageURL()!=null && !"".equals(item.getImageURL())) {
            Picasso.with(context).load(new File(item.getImageURL())).into(holder.itemImage);
        }
    }

    @Override
    public int getItemCount() {
        Log.d("getItemCount", ""+items.size());

        return items.size();
    }

    @Override
    public void updateData() {
        items = itemRepository.getAll();
        Log.d("updateData",items.toString());
        this.notifyDataSetChanged();
    }

    /**
     * Listens when text has been changed and gets the text itself.
     *
     * @param text
     */
    @Override
    public void onTextChanged(String text) {
        List<T>tempItems = itemRepository.getAll();
        List<T>filterItems = new ArrayList<>();
        for(T item : tempItems){
            if(item.getName().startsWith(text)){
                filterItems.add(item);
            }
        }
        this.items = filterItems;
        this.notifyDataSetChanged();
    }

    private void filter(String text){

    }

    /**
     * Represents the cardview_item elements
     */
    public class ViewHolder extends RecyclerView.ViewHolder {

        View itemContainer;

        ImageView itemImage;

        TextView nameTV;

        TextView priceTV;

        TextView eanTV;



        /**
         * Builds the cardview
         * @param itemView
         */
        public ViewHolder(View itemView) {
            super(itemView);
            itemContainer = (View) itemView.findViewById(R.id.itemContainer);
            itemImage = (ImageView) itemView.findViewById(R.id.itemImage);
            nameTV = (TextView) itemView.findViewById(R.id.nameTV);
            priceTV = (TextView) itemView.findViewById(R.id.priceTV);
            eanTV = (TextView) itemView.findViewById(R.id.eanTV);
        }
    }


}
