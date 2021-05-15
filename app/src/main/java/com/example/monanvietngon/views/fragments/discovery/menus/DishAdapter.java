package com.example.monanvietngon.views.fragments.discovery.menus;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.monanvietngon.R;
import com.example.monanvietngon.models.Dish;

import java.util.ArrayList;
import java.util.List;

public class DishAdapter extends RecyclerView.Adapter<DishAdapter.DishHolder> {

    private List<Dish> dishes = new ArrayList<>();
    private Context context;
    private OnClickedItemListener listener;

    public DishAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public DishHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food, parent, false);
        return new DishHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DishHolder holder, int position) {
        Dish dish = dishes.get(position);
        holder.bind(dish);
    }

    @Override
    public int getItemCount() {
        Log.d("AppLog", "Size: " + dishes.size());
        return dishes.size();
    }

    class DishHolder extends RecyclerView.ViewHolder {

        private ImageView thumb;
        private TextView name;

        public DishHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            thumb = itemView.findViewById(R.id.thumbnail);
        }

        public void bind(final Dish dish) {

            name.setText(dish.getName());
            Glide.with(context)
                    .load(dish.getThumbnailUrl())
                    .into(thumb);

            // call back khi nhan vao item
            itemView.setOnClickListener(view -> {
                if (listener != null) listener.onClick(dish);
            });
        }
    }

    public void setOnClickItemListener(OnClickedItemListener l) {
        this.listener = l;
    }

    public void submitList(List<Dish> list) {
        Log.d("AppLog", "Update!");
        this.dishes.clear();
        this.dishes.addAll(list);
        notifyDataSetChanged();
    }
}
