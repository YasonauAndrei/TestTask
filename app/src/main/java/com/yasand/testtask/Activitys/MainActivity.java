package com.yasand.testtask.Activitys;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yasand.testtask.Fragments.CatFragment;
import com.yasand.testtask.Fragments.DogFragment;
import com.yasand.testtask.Models.Datum;
import com.yasand.testtask.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    FragmentTransaction fragmentTransaction;
    CatFragment catFragment;
    DogFragment dogFragment;
    static final String DOG_FRAGMENT="dogFragment";
    static final String CAT_FRAGMENT="catFragment";
    static final String DOG_FRAGMENT_DATA="dogFragmentDat";
    static final String CAT_FRAGMENT_DATA="catFragmentData";
    static final String NUM_TAB="tab";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout=(TabLayout)findViewById(R.id.tab);
        tabLayout.addTab(tabLayout.newTab().setText("tab 1"));
        tabLayout.addTab(tabLayout.newTab().setText("tab 2"));

        if(savedInstanceState==null) {
            dogFragment=new DogFragment();
            catFragment=new CatFragment();
            fragmentTransaction=getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.container, dogFragment,DOG_FRAGMENT).commit();
        }
        else
        {
            ArrayList<Datum> listDog=savedInstanceState.getParcelableArrayList(DOG_FRAGMENT_DATA);
            dogFragment=DogFragment.newInstance(savedInstanceState.getInt(DOG_FRAGMENT), listDog);

            ArrayList<Datum> listCat=savedInstanceState.getParcelableArrayList(CAT_FRAGMENT_DATA);
            catFragment=CatFragment.newInstance(savedInstanceState.getInt(CAT_FRAGMENT), listCat);

            tabLayout.getTabAt(savedInstanceState.getInt(NUM_TAB)).select();
        }

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tabLayout.getSelectedTabPosition()==0)
                {
                    fragmentTransaction=getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.container,dogFragment).commit();
                }
                else
                    if(tabLayout.getSelectedTabPosition()==1)
                    {
                        fragmentTransaction=getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.container,catFragment).commit();
                    }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(NUM_TAB,tabLayout.getSelectedTabPosition());
        outState.putInt(DOG_FRAGMENT,dogFragment.getScrollPosition());
        outState.putInt(CAT_FRAGMENT,catFragment.getScrollPosition());
        outState.putParcelableArrayList(DOG_FRAGMENT_DATA,dogFragment.getData());
        outState.putParcelableArrayList(CAT_FRAGMENT_DATA,catFragment.getData());
        super.onSaveInstanceState(outState);
    }

}
