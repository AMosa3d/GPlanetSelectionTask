package com.example.abdel.gplanetselectiontask.Contest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.abdel.gplanetselectiontask.ApplicationUtils;
import com.example.abdel.gplanetselectiontask.Database.ApplicationRepo;
import com.example.abdel.gplanetselectiontask.Database.DatabaseActivity;
import com.example.abdel.gplanetselectiontask.R;

public class ContestantActivity extends AppCompatActivity {

    private ContestantFragment mContestantFragment;
    private ContestantPresenter mContestantPresenter;
    private ApplicationRepo mApplicationRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contestant_activity);

        mContestantFragment = (ContestantFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (mContestantFragment == null)
        {
            mContestantFragment = ContestantFragment.createNewInstance();
            ApplicationUtils.addFragmentToActivity(getSupportFragmentManager(),mContestantFragment,R.id.fragment_container);
        }
        mContestantFragment.setRetainInstance(true);
        mApplicationRepo = new ApplicationRepo(getApplication());

        mContestantPresenter = new ContestantPresenter(mContestantFragment, mApplicationRepo);

        mApplicationRepo.setContestantPresenter(mContestantPresenter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.database_menu_item)
            startActivity(new Intent(this,DatabaseActivity.class));

        return false;
    }
}
