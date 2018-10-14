package student.pxl.be.mealapp.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import student.pxl.be.mealapp.R;
import student.pxl.be.mealapp.domain.Meal;

public class MealDetailFragment extends Fragment {
    View view;
    private ImageView thumbnailImageView;
    private TextView ingredientTextView;
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
        ingredientTextView = view.findViewById(R.id.details_ingredients_id);

        Bundle bundle = getArguments();
        if(bundle != null && getArguments().containsKey("clickedMeal")){
            meal = getArguments().getParcelable("clickedMeal");
        }
        Glide.with(this)
                .asBitmap()
                .load(meal.thumbnail)
                .into(thumbnailImageView);
        ingredientTextView.setText(meal.ingredients);

        return view;
    }
}
