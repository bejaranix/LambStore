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
import com.example.usuario.lambstore.models.Item;
import com.example.usuario.lambstore.models.TransactionItem;
import com.example.usuario.lambstore.repository.Repository;
import com.example.usuario.lambstore.repository.listener.RepositoryListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by usuario on 30/05/17.
 */

public class ItemRVAdapter extends RecyclerView.Adapter<ItemRVAdapter.ViewHolder> implements RepositoryListener {

    private Context context;
    private Repository<TransactionItem> itemRepository;
    private List<TransactionItem> items;

    public ItemRVAdapter(Context context, Repository<TransactionItem> itemRepository){
        Log.d("ItemRVAdapter","ItemRVAdapter");
        this.itemRepository = itemRepository;
        this.itemRepository.setListener(this);
        this.context = context;
        items = new ArrayList<>(itemRepository.getAll().values());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_item,parent,false));
        this.context = parent.getContext();
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final TransactionItem item = items.get(position);
        holder.nameTV.setText(item.getItem().getName());
        holder.priceTV.setText(new TextUtilities().getMoneyText(item.getPriceTransaction()));
        holder.eanTV.setText(item.getItem().getEan());
        holder.itemContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        Log.d("getItemCount", ""+items.size());

        return items.size();
    }

    @Override
    public void updateData() {
        Log.d("updateData", "okokoko");
        Map<Integer,TransactionItem> map = itemRepository.getAll();
        Iterator<Map.Entry<Integer,TransactionItem>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()){
            Log.d("updateData",iterator.next().getValue().toString());

        }

        items = new ArrayList<>(map.values());
        for (TransactionItem item:items){
            Log.d("updateData2",item.toString());

        }
        Log.d("updateData3", ""+items.size());
        Log.d("updateData4", ""+itemRepository.size());

        this.notifyDataSetChanged();
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
