package student.pxl.be.mealapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.Random;

import student.pxl.be.mealapp.domain.Meal;
import student.pxl.be.mealapp.domain.MealResult;
import student.pxl.be.mealapp.fragments.MealsFragment;
import student.pxl.be.mealapp.utils.NetworkUtils;

public class MainActivity extends AppCompatActivity {
    private MealsFragment exploreMealsFragment;
    private MealsFragment favoriteMealsFragment;
    private MealsFragment localMealsFragment;
    private boolean isTwoPane;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton fab = findViewById(R.id.fab_id);
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), LocalMealActivity.class);
            v.getContext().startActivity(intent);
        });

        determineTwoPane();
        setupMenuNavigation();

        if(savedInstanceState == null){
            fetchAndDisplayExploreMeals();
        } else {
            //Display the fragment that was shown before the activity got destroyed
            MealsFragment currentFragment = (MealsFragment) getSupportFragmentManager().getFragment(savedInstanceState, "CurrentFragment");
            if(currentFragment != null && currentFragment.getArguments() != null){
                Bundle args = currentFragment.getArguments();
                args.putBoolean("isTwoPane", isTwoPane);
                currentFragment.setArguments(args);
            }
            replaceMealsFragment(currentFragment);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        //Save the currently displayed fragment
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.list_frame_id);
        if(currentFragment != null){
            getSupportFragmentManager().putFragment(outState, "CurrentFragment", currentFragment);
        }
    }

    //Decides if the screen is in twoPane mode by checking if the meal detail frame is available
    private void determineTwoPane() {
        if (findViewById(R.id.land_detail_frame_id) != null) {
            isTwoPane = true;
        } else {
            isTwoPane = false;
        }
    }

    private void setupMenuNavigation() {
        drawerLayout = findViewById(R.id.drawer_layout_id);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.Open, R.string.Close);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        NavigationView navigationView = findViewById(R.id.navigation_id);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        navigationView.setNavigationItemSelectedListener((menuItem) -> {
            // set item as selected to persist highlight
            menuItem.setChecked(true);
            // close drawer when item is tapped
            drawerLayout.closeDrawers();

            // Swap MealsFragment based on clicked menu item
            int id = menuItem.getItemId();

            switch(id){
                case R.id.nav_explore_id:
                    fetchAndDisplayExploreMeals();
                    break;
                case R.id.nav_favorite_id:
                    fetchAndDisplayFavoriteMeals();
                    break;
                case R.id.nav_local_id:
                    fetchAndDisplayLocalMeals();
                    break;
            }
            return true;
        });
    }
    private void fetchAndDisplayExploreMeals(){
        if(this.exploreMealsFragment == null){
            int randomPage = new Random().nextInt(100) + 1;
            String exploreURL = NetworkUtils.buildUriString("",Integer.toString(randomPage));
            Log.d("MainActivity", "fetchAndDisplayExploreMeals: about to call " + exploreURL);
            GsonRequest<MealResult> mealRequest = new GsonRequest<>(exploreURL, MealResult.class, null, createResponseListener(), null);
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(mealRequest);
        } else {
            replaceMealsFragment(exploreMealsFragment);
        }
    }
    private Response.Listener<MealResult> createResponseListener(){
        return (response) -> {
                ArrayList<Meal> randomMeals = (ArrayList<Meal>) response.meals;
                exploreMealsFragment = MealsFragment.newInstance(randomMeals, isTwoPane);
                replaceMealsFragment(exploreMealsFragment);
        };
    }

    private void fetchAndDisplayFavoriteMeals() {
        ArrayList<Meal> favoriteMeals = new ArrayList<>();
        if (favoriteMeals.size() == 0) {
            for (int i = 0; i < 20; i++) {
                Meal meal = new Meal();
                meal.thumbnail = "http://img.recipepuppy.com/12.jpg";
                meal.title = "TO BE REPLACED WITH SQLITE FAVORITE MEALS";
                meal.ingredients = "Dummy Chicken, Dummy Rice";
                meal.href = "https://www.allrecipes.com/recipe/14746/mushroom-pork-chops/";
                favoriteMeals.add(meal);
            }
        }
        if(this.favoriteMealsFragment == null){
            favoriteMealsFragment = MealsFragment.newInstance(favoriteMeals, isTwoPane);
        }
        replaceMealsFragment(favoriteMealsFragment);
    }

    private void fetchAndDisplayLocalMeals() {
        ArrayList<Meal> localMeals = new ArrayList<>();
        if (localMeals.size() == 0) {
            for (int i = 0; i < 20; i++) {
                Meal meal = new Meal();
                meal.thumbnail = "http://img.recipepuppy.com/16.jpg";
                meal.title = "TO BE REPLACED WITH SQLITE LOCAL MEALS";
                meal.ingredients = "Dummy Chicken, Dummy Rice";
                meal.href = "https://www.allrecipes.com/recipe/14746/mushroom-pork-chops/";
                localMeals.add(meal);
            }
        }
        if(this.localMealsFragment == null){
            localMealsFragment = MealsFragment.newInstance(localMeals, isTwoPane);
        }
        replaceMealsFragment(localMealsFragment);
    }
    private void replaceMealsFragment(MealsFragment mealsFragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.list_frame_id, mealsFragment);
        fragmentTransaction.commit();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return actionBarDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }
}
