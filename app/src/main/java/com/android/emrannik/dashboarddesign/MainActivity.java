package com.android.emrannik.dashboarddesign;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.android.emrannik.dashboarddesign.drink.DrinkFragment;
import com.android.emrannik.dashboarddesign.food.FoodFragment;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {


    TabLayout tabMenu;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabMenu     = findViewById(R.id.tabMenu);
        viewPager   = findViewById(R.id.viewPager);

        setupViewPager(viewPager);

        tabMenu.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(1);  // 0 = drink , 1=food
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new DrinkFragment(),"Drink");
        adapter.addFragment(new FoodFragment(),"Food");
        viewPager.setAdapter(adapter);
    }
}
