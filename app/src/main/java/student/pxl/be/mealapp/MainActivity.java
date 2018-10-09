package student.pxl.be.mealapp;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

import student.pxl.be.mealapp.fragments.ExploreFragment;
import student.pxl.be.mealapp.fragments.FavoritesFragment;
import student.pxl.be.mealapp.fragments.LocalFragment;

public class MainActivity extends AppCompatActivity {
    private TabLayout mtabLayout;
    private ViewPager mviewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mtabLayout = findViewById(R.id.tabs);
        mviewPager = findViewById(R.id.vp_id);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        //add fragments to the adapter
        viewPagerAdapter.addFragment(new ExploreFragment(), "EXPLORE");
        viewPagerAdapter.addFragment(new FavoritesFragment(), "FAVORITES");
        viewPagerAdapter.addFragment(new LocalFragment(), "LOCAL");

        //set adapter to the viewpager and link it with the different tabs
        mviewPager.setAdapter(viewPagerAdapter);
        mtabLayout.setupWithViewPager(mviewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.main, menu);
        return true;
    }
}
