package com.customviews.intents;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.customviews.R;

import java.util.Objects;

public class FruitAdapter extends ListAdapter<String, FruitAdapter.FruitHolder> {

    public FruitAdapter() {
        super(FruitDiffCallBack);
    }

    @NonNull
    @Override
    public FruitHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FruitHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.contact, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FruitHolder holder, int position) {
        TextView vFruitName = holder.itemView.findViewById(R.id.vFruitName);
        vFruitName.setText(getItem(position));
    }

    class FruitHolder extends RecyclerView.ViewHolder {

        public FruitHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
    public static DiffUtil.ItemCallback FruitDiffCallBack = new DiffUtil.ItemCallback<String>() {

        @Override
        public boolean areItemsTheSame(@NonNull String oldItem, @NonNull String newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull String oldItem, @NonNull String newItem) {
            if (Objects.requireNonNull(oldItem).equals(newItem)) {
                return true;
            }
            return false;
        }
    };
}
