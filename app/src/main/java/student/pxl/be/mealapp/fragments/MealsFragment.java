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

public class MealsFragment extends Fragment
{
    View view;
    private RecyclerView recyclerView;
    private ArrayList<Meal> meals;
    private static final String MEALS__KEY = "mealkey";

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

        //If there are arguments passed and it contains the right key, retrieve and cache them for use in the adapter
        meals = new ArrayList<>();
        Bundle bundle = getArguments();
        if(bundle != null && getArguments().containsKey(MEALS__KEY)){
            meals = getArguments().getParcelableArrayList(MEALS__KEY);
        }

        //Make and set adapter with data to display, set the layoutmanager that will control the recyclerview,
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getContext(), meals);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        return view;
    }
}
