package student.pxl.be.mealapp.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
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
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import student.pxl.be.mealapp.MainActivity;
import student.pxl.be.mealapp.R;
import student.pxl.be.mealapp.data.SQLiteHelper;
import student.pxl.be.mealapp.domain.Meal;

public class MealDetailFragment extends Fragment {
    private View view;
    private Meal meal;
    private SQLiteHelper database;

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

        if(meal != null){
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
            visitButton.setOnClickListener(v -> {
                Uri webPageUri = Uri.parse(meal.href);
                Intent webPageIntent = new Intent(Intent.ACTION_VIEW, webPageUri);
                if(webPageIntent.resolveActivity(getContext().getPackageManager()) != null){
                    getContext().startActivity(webPageIntent);
                }
            });

            buttonFavorite.setOnClickListener(v -> {
                if(meal.getId() == 0){
                    new AddFavoriteMealTask(v.getContext()).execute(meal);
                } else {
                    new DeleteFavoriteMealTask(v.getContext()).execute(meal);
                }
            });
        }
        return view;
    }

    private class AddFavoriteMealTask extends AsyncTask<Meal, Void, String>{
        private Context context;

        public AddFavoriteMealTask(Context context){
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            database = new SQLiteHelper(context);
        }

        @Override
        protected String doInBackground(Meal... meals) {
            if(meals.length > 0){
                database.addFavoriteMeal(meals[0]);
                return "Meal added to favorites!";
            } else {
                return "Error: could not add to favorites.";
            }
        }

        @Override
        protected void onPostExecute(String resultMessage) {
            Toast.makeText(context, resultMessage, Toast.LENGTH_SHORT).show();
        }
    }

    private class DeleteFavoriteMealTask extends AsyncTask<Meal, Void, String>{
        private Context context;

        public DeleteFavoriteMealTask(Context context){
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            database = new SQLiteHelper(context);
        }

        @Override
        protected String doInBackground(Meal... meals) {
            if(meals.length > 0){
                database.deleteFavoriteMeal(meals[0]);
                return "Meal succesfully deleted!";
            } else {
                return "Error: could not delete meal.";
            }
        }

        @Override
        protected void onPostExecute(String resultMessage) {
            Toast.makeText(context, resultMessage, Toast.LENGTH_SHORT).show();
        }
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
