package student.pxl.be.mealapp;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import student.pxl.be.mealapp.domain.Meal;
import student.pxl.be.mealapp.fragments.MealDetailFragment;

public class MealDetailActivity extends AppCompatActivity {
    private static final String TAG = "MealDetailActivity";
    private Meal meal;

    private FrameLayout container;
    private AnimationDrawable anim;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mealdetail);
        Log.d(TAG, "onCreate: started");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getIncomingIntent();

        container = findViewById(R.id.meal_detail_parent_layout_id);
        anim = (AnimationDrawable) container.getBackground();
        anim.setEnterFadeDuration(6000);
        anim.setExitFadeDuration(2000);

        //Create fragment with the retrieved meal from the intent
        Bundle args = new Bundle();
        args.putParcelable("clickedMeal", meal);
        Fragment mealDetailFragment = new MealDetailFragment();
        mealDetailFragment.setArguments(args);

        //Add fragment to the parent layout that corresponds to this activity
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.meal_detail_parent_layout_id, mealDetailFragment);
        fragmentTransaction.commit();
    }

    //Read arguments from the incoming intent if they exist
    private void getIncomingIntent(){
        Log.d(TAG, "getIncomingIntent: Checking for incoming Intent");
        if(getIntent().hasExtra("clickedMeal")){
            Log.d(TAG, "getIncomingIntent: found intent extras");
            meal = getIntent().getParcelableExtra("clickedMeal");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
            switch (item.getItemId()) {
                case android.R.id.home:
                    finish();
                    return true;
            }
            return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (anim != null && !anim.isRunning())
            anim.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (anim != null && anim.isRunning())
            anim.stop();
    }
}
