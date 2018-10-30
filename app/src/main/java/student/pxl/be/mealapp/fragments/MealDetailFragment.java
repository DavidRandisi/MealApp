package student.pxl.be.mealapp.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import student.pxl.be.mealapp.R;
import student.pxl.be.mealapp.data.favoritemeals.AsyncFavoriteDatabaseHandler;
import student.pxl.be.mealapp.domain.Meal;

public class MealDetailFragment extends Fragment {
    private View view;
    private Meal meal;
    private AsyncFavoriteDatabaseHandler asyncFavoriteDatabaseHandler;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.meal_detail_fragment, container, false);
        ImageView thumbnailImageView = view.findViewById(R.id.details_image_id);
        ListView ingredientsListView = view.findViewById(R.id.details_ingredients_list_id);
        TextView titleTextView = view.findViewById(R.id.details_title_id);
        Button visitButton = view.findViewById(R.id.details_buttonVisit_id);
        Button buttonFavorite = view.findViewById(R.id.buttonFavorite);

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
        if(meal.thumbnail.trim().length() != 0){
            Glide.with(this)
                    .asBitmap()
                    .load(meal.thumbnail)
                    .into(thumbnailImageView);
        }
        titleTextView.setText(meal.title);

        //Create click listener on the visit button that starts a new implicit intent if there is an app installed to handle it
        visitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri webPageUri = Uri.parse(meal.href);
                Intent webPageIntent = new Intent(Intent.ACTION_VIEW, webPageUri);
                if(webPageIntent.resolveActivity(getContext().getPackageManager()) != null){
                    getContext().startActivity(webPageIntent);
                }
            }
        });

        buttonFavorite.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                asyncFavoriteDatabaseHandler.asyncInsert(meal);
                //TODO: UNFAVORITE MEALS
            }
        });

        return view;
    }

    //Creates a new instance of this class with the given meal
    public static MealDetailFragment newInstance(Meal meal){
        MealDetailFragment fragment = new MealDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("clickedMeal", meal);
        fragment.setArguments(args);
        return fragment;
    }
}
