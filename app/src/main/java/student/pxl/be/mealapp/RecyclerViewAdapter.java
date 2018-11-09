package student.pxl.be.mealapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import student.pxl.be.mealapp.domain.Meal;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    private static final String TAG = "RecyclerViewAdapter";
    private ArrayList<Meal> meals;
    private Context context;
    private OnItemClickListener itemClickListener;

    public RecyclerViewAdapter(Context context, ArrayList<Meal> meals, OnItemClickListener listener) {
        this.meals = meals;
        this.context = context;
        this.itemClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_list_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int position) {
        Log.d(TAG, "onBindViewHolder: called");
        viewHolder.bind(meals.get(position), itemClickListener);
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;
        RelativeLayout relativeLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_image_id);
            textView = itemView.findViewById(R.id.item_text_id);
            relativeLayout = itemView.findViewById(R.id.parent_layout_id);
        }
        //Bind current viewholder with the data of the given meal and add a click listener
        public void bind(Meal meal, OnItemClickListener listener) {
            if(meal.thumbnail.trim().length() != 0){
                Glide.with(context)
                        .asBitmap()
                        .load(meal.thumbnail)
                        .into(imageView);
            }
            textView.setText(meal.title);

            itemView.setOnClickListener( (view) -> {
                listener.onItemClick(meal);
            });
        }
    }
}
