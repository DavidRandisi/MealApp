package student.pxl.be.mealapp.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import student.pxl.be.mealapp.R;
import student.pxl.be.mealapp.domain.Meal;

public class MealDetailFragment extends Fragment {
    View view;
    private ImageView thumbnailImageView;
    private ListView ingredientsListView;
    private TextView titleTextView;
    private Meal meal;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.meal_detail_fragment, container, false);
        thumbnailImageView = view.findViewById(R.id.details_image_id);
        ingredientsListView = view.findViewById(R.id.details_ingredients_list_id);
        titleTextView = view.findViewById(R.id.details_title_id);

        //Retrieve the clicked meal argument if it exists
        Bundle bundle = getArguments();
        if(bundle != null && getArguments().containsKey("clickedMeal")){
            meal = getArguments().getParcelable("clickedMeal");
        }

        //Transform the ingredients string into an array of ingredient strings
        String[] ingredients = meal.ingredients.trim().split("\\s*,\\s*");

        //Create and assign an arrayadapter to the listview
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getContext(),
                R.layout.ingredient_list_item, R.id.ingredient_tv_id, ingredients);
        ingredientsListView.setAdapter(adapter);

        //Fill in the thumbnail and title for the fragment layout
        Glide.with(this)
                .asBitmap()
                .load(meal.thumbnail)
                .into(thumbnailImageView);
        titleTextView.setText(meal.title);

        return view;
    }
}
