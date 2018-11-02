package student.pxl.be.mealapp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.Random;

import student.pxl.be.mealapp.data.SQLiteHelper;
import student.pxl.be.mealapp.domain.Meal;
import student.pxl.be.mealapp.domain.MealResult;
import student.pxl.be.mealapp.fragments.MealsFragment;
import student.pxl.be.mealapp.utils.NetworkUtils;

public class MainActivity extends AppCompatActivity {
    private MealsFragment exploreMealsFragment;
    private boolean isTwoPane;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private SQLiteHelper database;

    private static final String FAVORITE_TAG = "FAVORITE";
    private static final String EXPLORE_TAG = "EXPLORE";

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
            replaceMealsFragment(currentFragment, currentFragment.getTag());
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
                    if(exploreMealsFragment != null){
                        replaceMealsFragment(exploreMealsFragment, EXPLORE_TAG);
                    } else {
                        fetchAndDisplayExploreMeals();
                    }
                    break;
                case R.id.nav_favorite_id:
                    new GetFavoriteMealTask(this).execute();
                    break;
            }
            return true;
        });
    }
    private void fetchAndDisplayExploreMeals(){
            int randomPage = new Random().nextInt(100) + 1;
            String exploreURL = NetworkUtils.buildUriString("",Integer.toString(randomPage));
            Log.d("MainActivity", "fetchAndDisplayExploreMeals: about to call " + exploreURL);
            GsonRequest<MealResult> mealRequest = new GsonRequest<>(exploreURL, MealResult.class, null, createResponseListener(), null);
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(mealRequest);
    }
    private Response.Listener<MealResult> createResponseListener(){
        return (response) -> {
                ArrayList<Meal> randomMeals = (ArrayList<Meal>) response.meals;
                exploreMealsFragment = MealsFragment.newInstance(randomMeals, isTwoPane);
                replaceMealsFragment(exploreMealsFragment, EXPLORE_TAG);
        };
    }

    private class GetFavoriteMealTask extends AsyncTask<Void, Void, MealsFragment>{
        private Context context;

        public GetFavoriteMealTask(Context context){
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            database = new SQLiteHelper(context);
        }

        @Override
        protected MealsFragment doInBackground(Void... arguments) {
            ArrayList<Meal> favoriteMeals = (ArrayList<Meal>) database.getAllFavoriteMeals();
            MealsFragment favoriteMealsFragment = MealsFragment.newInstance(favoriteMeals, isTwoPane);
            return favoriteMealsFragment;
        }

        @Override
        protected void onPostExecute(MealsFragment favoriteMealsFragment) {
            database.close();
            replaceMealsFragment(favoriteMealsFragment, FAVORITE_TAG);
        }
    }

    private void replaceMealsFragment(MealsFragment mealsFragment, String tag){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.list_frame_id, mealsFragment, tag);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.refresh_button_id:
                refreshCurrentFragment();
                break;
        }
        return actionBarDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }
    private void refreshCurrentFragment(){
        MealsFragment currentFragment = (MealsFragment) getSupportFragmentManager().findFragmentById(R.id.list_frame_id);
        switch(currentFragment.getTag()){
            case EXPLORE_TAG:
                fetchAndDisplayExploreMeals();
                break;
            case FAVORITE_TAG: new GetFavoriteMealTask(this).execute(); break;
        }
    }
}
