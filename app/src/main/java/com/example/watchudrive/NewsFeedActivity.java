package com.example.watchudrive;

import android.os.Bundle;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsFeedActivity extends AppCompatActivity{

     @BindView(R.id.id_tabLayout)
    TabLayout tabLayout;
     @BindView(R.id.id_viewPager)
    ViewPager viewPager;
     /*@BindView(R.id.id_search_text)
    EditText editTextSearchBar;*/
     private NewsFeedViewpagerAdapter adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_feed_activity);
        ButterKnife.bind(this);

        adapter = new NewsFeedViewpagerAdapter(getSupportFragmentManager());


        adapter.addFragment(new ProfileFragment(),"");
        adapter.addFragment(new HomeFragment(),"");
        adapter.addFragment(new NotificationFragment(),"");
        adapter.addFragment(new SettingsFragment(),"");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.icons8_user_50);
        tabLayout.getTabAt(1).setIcon(R.drawable.icons8_news_feed_64);
        tabLayout.getTabAt(2).setIcon(R.drawable.icons8_bell_50);
        tabLayout.getTabAt(3).setIcon(R.drawable.icons8_menu_50);

    }

}
