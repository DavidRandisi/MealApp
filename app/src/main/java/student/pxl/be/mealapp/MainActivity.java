package student.pxl.be.mealapp;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

import java.util.ArrayList;

import student.pxl.be.mealapp.domain.Meal;
import student.pxl.be.mealapp.fragments.MealsFragment;

public class MainActivity extends AppCompatActivity {
    private TabLayout mtabLayout;
    private ViewPager mviewPager;
    private static final String MEALS__KEY = "mealkey";
    private ArrayList<Meal> randomMeals;
    private ArrayList<Meal> favoriteMeals;
    private ArrayList<Meal> localMeals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mtabLayout = findViewById(R.id.tabs);
        mviewPager = findViewById(R.id.vp_id);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        randomMeals = new ArrayList<>();
        favoriteMeals = new ArrayList<>();
        localMeals = new ArrayList<>();

        //if there is no saved instance, create fragments with passed data as args and add them to the viewpageradapter
        if(savedInstanceState == null){
            MealsFragment exploreListFragment = fetchExploreMealsDataAndCreateFragment();
            MealsFragment favoriteFragment = fetchFavoriteMealsDataAndCreateFragment();
            MealsFragment localFragment = fetchLocalMealsDataAndCreateFragment();

            //add fragments to the adapter
            viewPagerAdapter.addFragment(exploreListFragment, "EXPLORE");
            viewPagerAdapter.addFragment(favoriteFragment, "FAVORITES");
            viewPagerAdapter.addFragment(localFragment, "LOCAL");
        }

        //set adapter to the viewpager and link it with the different tabs
        mviewPager.setAdapter(viewPagerAdapter);
        mtabLayout.setupWithViewPager(mviewPager);
    }

    //TO-DO: replace dummy data with data from a AsyncTaskLoader API call
    private MealsFragment fetchExploreMealsDataAndCreateFragment() {
        for(int i = 0; i<20; i++){
            Meal meal = new Meal();
            meal.thumbnail = "http://img.recipepuppy.com/11.jpg";
            meal.title = "TO BE REPLACED WITH API MEALS";
            meal.ingredients = "Dummy Chicken, Dummy Rice";
            meal.href = "https://www.allrecipes.com/recipe/14746/mushroom-pork-chops/";
            randomMeals.add(meal);
        }
        //Create argument bundle that contains the meal list to display in the particular recyclerview
        Bundle args = new Bundle();
        args.putParcelableArrayList(MEALS__KEY, randomMeals);
        MealsFragment exploreListFragment = new MealsFragment();
        exploreListFragment.setArguments(args);
        return exploreListFragment;
    }

    private MealsFragment fetchFavoriteMealsDataAndCreateFragment(){
        for(int i = 0; i<20; i++){
            Meal meal = new Meal();
            meal.thumbnail = "http://img.recipepuppy.com/12.jpg";
            meal.title = "TO BE REPLACED WITH SQLITE FAVORITE MEALS";
            meal.ingredients = "Dummy Chicken, Dummy Rice";
            meal.href = "https://www.allrecipes.com/recipe/14746/mushroom-pork-chops/";
            favoriteMeals.add(meal);
        }

        //Create argument bundle that contains the meal list to display in the particular recyclerview
        Bundle args = new Bundle();
        args.putParcelableArrayList(MEALS__KEY, favoriteMeals);
        MealsFragment favoriteListFragment = new MealsFragment();
        favoriteListFragment.setArguments(args);
        return favoriteListFragment;
    }

    private MealsFragment fetchLocalMealsDataAndCreateFragment(){
        for(int i = 0; i<20; i++){
            Meal meal = new Meal();
            meal.thumbnail = "http://img.recipepuppy.com/16.jpg";
            meal.title = "TO BE REPLACED WITH SQLITE LOCAL MEALS";
            meal.ingredients = "Dummy Chicken, Dummy Rice";
            meal.href = "https://www.allrecipes.com/recipe/14746/mushroom-pork-chops/";
            localMeals.add(meal);
        }

        //Create argument bundle that contains the meal list to display in the particular recyclerview
        Bundle args = new Bundle();
        args.putParcelableArrayList(MEALS__KEY, localMeals);
        MealsFragment localListFragment = new MealsFragment();
        localListFragment.setArguments(args);
        return localListFragment;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.main, menu);
        return true;
    }
}
