package student.pxl.be.mealapp.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import student.pxl.be.mealapp.MealDetailActivity;
import student.pxl.be.mealapp.OnItemClickListener;
import student.pxl.be.mealapp.R;
import student.pxl.be.mealapp.RecyclerViewAdapter;
import student.pxl.be.mealapp.domain.Meal;

public class MealsFragment extends Fragment
{
    View view;
    private RecyclerView recyclerView;
    private ArrayList<Meal> meals;
    private static final String MEALS__KEY = "mealkey";
    private boolean insideTwoPane;
    private Context context;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    //Returns the view that is linked to this fragment class and initialize the recyclerview
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.meals_fragment, container, false);
        recyclerView = view.findViewById(R.id.meals_recycler_view_id);
        context = getContext();

        //If there are arguments passed and it contains the right key, retrieve and store them
        meals = new ArrayList<>();
        Bundle bundle = getArguments();
        if(bundle != null){
            if(getArguments().containsKey(MEALS__KEY)){
                meals = getArguments().getParcelableArrayList(MEALS__KEY);
            }
            if(getArguments().containsKey("isTwoPane")){
                insideTwoPane = getArguments().getBoolean("isTwoPane");
            }
        }

        //Make the adapter with data to display, and define the click logic dependant on the current mode (land/port)
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(context, meals, (meal) -> {
                if(insideTwoPane){
                        //Replace the details frame with the details of the newly clicked meal via the fragmentmanager
                        MealDetailFragment mealDetailFragment = MealDetailFragment.newInstance(meal);

                        AppCompatActivity appCompatActivityContext = ( (AppCompatActivity) context);
                        FragmentManager fragmentManager = appCompatActivityContext.getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.land_detail_frame_id, mealDetailFragment);
                        fragmentTransaction.commit();
                } else {
                        //Clicking on a recyclerview item starts a new MealDetailActivity with the clicked meal as argument
                        Intent intent = new Intent(context, MealDetailActivity.class);
                        intent.putExtra("clickedMeal", meal);
                        context.startActivity(intent);
                }
        });
        //set the layoutmanager that will control the recyclerview,
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        return view;
    }
    //Creates a new instance of this class with the given meals and mode as arguments
    public static MealsFragment newInstance(ArrayList<Meal> meals, boolean isTwoPane){
        MealsFragment fragment = new MealsFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(MEALS__KEY, meals);
        args.putBoolean("isTwoPane", isTwoPane);
        fragment.setArguments(args);
        return fragment;
    }
}
