package student.pxl.be.mealapp.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import student.pxl.be.mealapp.R;
import student.pxl.be.mealapp.data.AsyncDatabaseHandler;
import student.pxl.be.mealapp.domain.Meal;

public class LocalMealFragment extends Fragment {
    private ImageButton imageButton;
    private EditText title;
    private EditText description;
    private EditText ingredients;
    private Button saveButton;
    private AsyncDatabaseHandler asyncDatabaseHandler;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.local_meal_fragment, container, false);
        imageButton = view.findViewById(R.id.local_imagebutton_id);
        title = view.findViewById(R.id.local_title_id);
        description = view.findViewById(R.id.local_description_id);
        ingredients = view.findViewById(R.id.local_ingredients_id);
        saveButton = view.findViewById(R.id.local_savebutton_id);
        imageButton.setOnClickListener(v -> Toast.makeText(v.getContext(), "TODO: Open camera", Toast.LENGTH_LONG).show());
        saveButton.setOnClickListener( new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Meal meal = new Meal();
                                meal.setIngredients(ingredients.getText().toString()); //TODO: Check if correct
                                meal.setTitle(title.getText().toString());
                                //TODO: Description
                                asyncDatabaseHandler.asyncInsert(meal);
                            }
                            //TODO: Go back to previous activity
                });
        return view;
    }
}
