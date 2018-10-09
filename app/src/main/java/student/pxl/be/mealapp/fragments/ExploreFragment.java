package student.pxl.be.mealapp.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import student.pxl.be.mealapp.R;
import student.pxl.be.mealapp.RecyclerViewAdapter;
import student.pxl.be.mealapp.domain.Meal;

public class ExploreFragment  extends Fragment {
    View view;
    private RecyclerView recyclerView;
    private ArrayList<Meal> meals;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        meals = new ArrayList<>();
        fetchDummyData();
    }

    @Nullable
    @Override
    //Returns the view that is linked to this fragment class and initialize the recyclerview
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.explore_fragment, container, false);
        recyclerView = view.findViewById(R.id.explore_recycler_view_id);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getContext(), meals);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        return view;
    }

    //Fetch meal data and cache them
    private void fetchDummyData() {
        for(int i = 0; i<20; i++){
            Meal meal = new Meal();
            meal.thumbnail = "http://img.recipepuppy.com/11.jpg";
            meal.title = "Dummy Pork";
            meals.add(meal);
        }

    }
}
