package com.example.sampletodo;

import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

// responsible for displaying data from the model
public class ItemsAdapter  extends RecyclerView.Adapter<ItemsAdapter.ViewHolder>  {

    public interface OnLongClicklistener {
        void onItemLongClicked(int position);
    }
    List<String> items;
    OnLongClicklistener longClicklistener;

    public ItemsAdapter(List<String> items, OnLongClicklistener longClicklistener)  {
        this.items = items;
        this.longClicklistener = longClicklistener;
    }

    @NonNull
    @Override
    public ItemsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       // use layout inflater to inflate a view

       View todoView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
       // wrap it inside a viewHolder and return it
        return new ViewHolder(todoView);
    }
// responsible for binding data to a particular view holder
    @Override
    public void onBindViewHolder(@NonNull ItemsAdapter.ViewHolder holder, int position) {
        // grab the item at the position
        String item = items.get(position);
        // grab the item into the specified  view holder
        holder.bind(item);
    }
    // tells the RV how many items are in the list
    @Override
    public int getItemCount() {
        return items.size();
    }

    //container to provide easy access to views that present each row of the list

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(android.R.id.text1);
        }

    // update the view inside of the view holder with this data
        public void bind(String item) {
            tvItem.setText(item);
            tvItem.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    //notify adapter that an item is inserted
                    longClicklistener.onItemLongClicked(getAdapterPosition());
                    return true;
                }
            });
        }
    }
}
