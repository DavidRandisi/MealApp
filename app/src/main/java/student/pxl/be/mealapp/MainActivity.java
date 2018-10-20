package student.pxl.be.mealapp;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
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
        randomMeals = new ArrayList<>();
        favoriteMeals = new ArrayList<>();
        localMeals = new ArrayList<>();

        FragmentPagerAdapter viewPagerAdapter = new ViewPagerAdapter();
        mviewPager.setAdapter(viewPagerAdapter);
        mtabLayout.setupWithViewPager(mviewPager);

    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {

        public ViewPagerAdapter() {
            super(getSupportFragmentManager());
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: return fetchExploreMealsDataAndCreateFragment();
                case 1: return fetchFavoriteMealsDataAndCreateFragment();
                case 2: return fetchLocalMealsDataAndCreateFragment();

                default: throw new IllegalArgumentException("unexpected position: " + position);
            }
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0: return "EXPLORE";
                case 1: return "FAVORITES";
                case 2: return "LOCAL";

                default: throw new IllegalArgumentException("unexpected position: " + position);
            }
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

    //TODO: replace dummy data with data from a AsyncTaskLoader API call
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
