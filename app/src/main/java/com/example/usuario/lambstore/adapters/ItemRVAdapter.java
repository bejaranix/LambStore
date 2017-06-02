package com.example.usuario.lambstore.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.usuario.lambstore.R;
import com.example.usuario.lambstore.models.Item;

import java.util.List;

/**
 * Created by usuario on 30/05/17.
 */

public class ItemRVAdapter extends RecyclerView.Adapter<ItemRVAdapter.ViewHolder> {

    private Context context;
    private List<Item> items;

    public ItemRVAdapter(Context context, List<Item> items){
        this.items = items;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_item,parent,false));
        this.context = parent.getContext();
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Item item = items.get(position);
        holder.nameTV.setText(item.getName());
        holder.priceTV.setText(String.format("$%.2f",((float)item.getPrice())/100));
        holder.eanTV.setText(item.getEan());
        holder.itemContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
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
