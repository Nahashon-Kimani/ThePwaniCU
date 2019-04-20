package com.pk.drawerFragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pk.R;
import com.pk.adapter.ViewPagerAdapter;
import com.pk.homeFragment.Notices;
import com.pk.homeFragment.SemProgram;
import com.pk.homeFragment.Today;

public class Home extends Fragment {
    View view;
    ViewPager viewPager;
    TabLayout tabLayout;

    public Home() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_fragment, container, false);

        tabLayout = view.findViewById(R.id.tab_layout);
        viewPager = view.findViewById(R.id.view_pager);

        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getFragmentManager());
        pagerAdapter.addFragment(new Today(), "Today");
        pagerAdapter.addFragment(new Notices(), "Notices");
        pagerAdapter.addFragment(new SemProgram(), "Sem Program");

        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);


        return view;
    }
}
