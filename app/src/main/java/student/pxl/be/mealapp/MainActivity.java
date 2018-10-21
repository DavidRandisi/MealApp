package student.pxl.be.mealapp;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

import student.pxl.be.mealapp.domain.Meal;
import student.pxl.be.mealapp.fragments.MealsFragment;

public class MainActivity extends AppCompatActivity {
    private static final String MEALS__KEY = "mealkey";
    private ArrayList<Meal> randomMeals;
    private ArrayList<Meal> favoriteMeals;
    private ArrayList<Meal> localMeals;
    private boolean isTwoPane;
    private ViewPager mviewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        determineTwoPane();

        randomMeals = new ArrayList<>();
        favoriteMeals = new ArrayList<>();
        localMeals = new ArrayList<>();
        mviewPager = findViewById(R.id.vp_id);

        //TODO: add navigation in TwoPane (landscape) mode between explore/favorites/local
        if(isTwoPane){
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            //TODO: save CURRENT viewpager fragment instance to display it in landscape mode
            fragmentTransaction.replace(R.id.land_list_frame_id, getExploreMealsFragment());
            fragmentTransaction.commit();
        } else {
            TabLayout mtabLayout = findViewById(R.id.tabs);
            ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter();
            mviewPager.setAdapter(viewPagerAdapter);
            mtabLayout.setupWithViewPager(mviewPager);
        }
    }

    //Decides if the screen is in twoPane mode by checking if the meal detail frame is available
    private void determineTwoPane(){
        if(findViewById(R.id.land_detail_frame_id) != null){
            isTwoPane = true;
        } else {
            isTwoPane = false;
        }
    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {

        public ViewPagerAdapter() {
            super(getSupportFragmentManager());
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: return getExploreMealsFragment();
                case 1: return getFavoriteMealsFragment();
                case 2: return getLocalMealsFragment();

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
    //TODO: save fragment state so the API call doesn't get executed on every screen rotation
    private MealsFragment getExploreMealsFragment() {
        if(randomMeals.size() == 0){
            for(int i = 0; i<20; i++){
                Meal meal = new Meal();
                meal.thumbnail = "http://img.recipepuppy.com/11.jpg";
                meal.title = "TO BE REPLACED WITH API MEALS";
                meal.ingredients = "Dummy Chicken, Dummy Rice";
                meal.href = "https://www.allrecipes.com/recipe/14746/mushroom-pork-chops/";
                randomMeals.add(meal);
            }
            Meal meal = new Meal();
            meal.thumbnail = "http://img.recipepuppy.com/10.jpg";
            meal.title = "Different meal to test navigation";
            meal.ingredients = "Dummy Crisps, Dummy Ketchup";
            meal.href = "https://www.allrecipes.com/recipe/14746/mushroom-pork-chops/";
            randomMeals.add(meal);
        }

        //Create new MealsFragment instance with random meals and information about the current mode
        return MealsFragment.newInstance(randomMeals, isTwoPane);
    }

    private MealsFragment getFavoriteMealsFragment(){
        if(favoriteMeals.size() == 0){
            for(int i = 0; i<20; i++){
                Meal meal = new Meal();
                meal.thumbnail = "http://img.recipepuppy.com/12.jpg";
                meal.title = "TO BE REPLACED WITH SQLITE FAVORITE MEALS";
                meal.ingredients = "Dummy Chicken, Dummy Rice";
                meal.href = "https://www.allrecipes.com/recipe/14746/mushroom-pork-chops/";
                favoriteMeals.add(meal);
            }
        }
        ////Create new MealsFragment instance with favorite meals and information about the current mode
        return MealsFragment.newInstance(favoriteMeals, isTwoPane);
    }

    private MealsFragment getLocalMealsFragment(){
        if(localMeals.size() == 0){
            for(int i = 0; i<20; i++){
                Meal meal = new Meal();
                meal.thumbnail = "http://img.recipepuppy.com/16.jpg";
                meal.title = "TO BE REPLACED WITH SQLITE LOCAL MEALS";
                meal.ingredients = "Dummy Chicken, Dummy Rice";
                meal.href = "https://www.allrecipes.com/recipe/14746/mushroom-pork-chops/";
                localMeals.add(meal);
            }
        }
        //Create new MealsFragment instance with local meals and information about the current mode
        return MealsFragment.newInstance(localMeals, isTwoPane);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.main, menu);
        return true;
    }
}
