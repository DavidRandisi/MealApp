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
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import student.pxl.be.mealapp.domain.Meal;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    private static final String TAG = "RecyclerViewAdapter";
    private ArrayList<Meal> meals = new ArrayList<>();
    private Context context;

    public RecyclerViewAdapter(Context context, ArrayList<Meal> meals) {
        this.meals = meals;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_list_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    //Use Glide library to fetch a bitmap from an image url and place it into the imageview of the viewholder
    //Get the meal title and place it in the textview part of the item layout
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        Log.d(TAG, "onBindViewHolder: called");
        Glide.with(context)
                .asBitmap()
                .load(meals.get(i).thumbnail)
                .into(viewHolder.imageView);
        viewHolder.textView.setText(meals.get(i).title);
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
    }
}
