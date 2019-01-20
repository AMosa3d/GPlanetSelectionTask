package com.example.abdel.gplanetselectiontask.Database;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.abdel.gplanetselectiontask.Readers.ReaderFragment;
import com.example.abdel.gplanetselectiontask.Readers.ReaderPresenter;
import com.example.abdel.gplanetselectiontask.Readings.ReadingFragment;
import com.example.abdel.gplanetselectiontask.Readings.ReadingPresenter;
import com.example.abdel.gplanetselectiontask.R;

import java.util.ArrayList;
import java.util.List;

public class DatabaseActivity extends AppCompatActivity {

    private final String[] TABS_NAMES = { "Readers Table", "Readings Table" };

    private TabLayout mTabLayout;
    private ViewPager mTablesViewPager;

    private ReaderPresenter mReaderPresenter;
    private ReadingPresenter mReadingPresenter;
    private ReaderFragment readerFragment;
    private ReadingFragment readingFragment;
    private ApplicationRepo mApplicationRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.database_activity);

        //set view
        mTabLayout = findViewById(R.id.tabLayout);
        mTablesViewPager = findViewById(R.id.viewPager);

        //Create Fragments and Fragments List
        if (readerFragment == null)
            readerFragment = ReaderFragment.createNewInstance();
        if (readingFragment == null)
            readingFragment = ReadingFragment.createNewInstance();

        readingFragment.setRetainInstance(true);
        readerFragment.setRetainInstance(true);

        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(readerFragment);
        fragmentList.add(readingFragment);

        //Create and Set Presenters
        mApplicationRepo = new ApplicationRepo(getApplication());

        mReaderPresenter = new ReaderPresenter(readerFragment, mApplicationRepo);
        mReadingPresenter = new ReadingPresenter(readingFragment, mApplicationRepo);

        mApplicationRepo.setReaderPresenter(mReaderPresenter);
        mApplicationRepo.setReadingPresenter(mReadingPresenter);

        //set pager and listeners
        mTabLayout.setupWithViewPager(mTablesViewPager);
        mTablesViewPager.setAdapter(new TablesPagerAdapter(getSupportFragmentManager(),fragmentList, TABS_NAMES));
        mTablesViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mTablesViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }
}
