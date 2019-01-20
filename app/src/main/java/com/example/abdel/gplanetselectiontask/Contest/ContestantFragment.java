package com.example.abdel.gplanetselectiontask.Contest;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abdel.gplanetselectiontask.R;

import java.util.List;

public class ContestantFragment extends Fragment implements ContestantContract.View,ContestantItemClickListener {

    ContestantContract.Presenter mPresenter;
    ContestantAdapter mAdapter;

    ProgressBar mProgressBar;
    TextView issuesTextView;
    ConstraintLayout listTitlesConstraintLayout;
    RecyclerView contestantRecyclerView;

    private final String RECYCLER_POSITION = "position";

    public ContestantFragment() {
    }

    public static ContestantFragment createNewInstance() {
        return new ContestantFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new ContestantAdapter(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contestant_fragment,container,false);

        //Set views
        mProgressBar = view.findViewById(R.id.progressBar);
        issuesTextView = view.findViewById(R.id.issue_textView);
        listTitlesConstraintLayout = view.findViewById(R.id.contestant_list_titles_layout);
        contestantRecyclerView = view.findViewById(R.id.contestants_recyclerView);

        //Set Recycler
        contestantRecyclerView.setAdapter(mAdapter);
        contestantRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));

        if(savedInstanceState != null)
            contestantRecyclerView.setVerticalScrollbarPosition(savedInstanceState.getInt(RECYCLER_POSITION));

        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(RECYCLER_POSITION,contestantRecyclerView.getVerticalScrollbarPosition());
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setLoadingBar() {
        listTitlesConstraintLayout.setVisibility(View.INVISIBLE);
        mProgressBar.setVisibility(View.VISIBLE);
        issuesTextView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showLoadingSuccessful() {
        Toast.makeText(getContext(), getString(R.string.loading_complete_str),Toast.LENGTH_SHORT).show();

        listTitlesConstraintLayout.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.INVISIBLE);
        issuesTextView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showLoadingError() {
        issuesTextView.setText(getString(R.string.loading_error_str));

        listTitlesConstraintLayout.setVisibility(View.INVISIBLE);
        mProgressBar.setVisibility(View.INVISIBLE);
        issuesTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showEmptyList() {
        issuesTextView.setText(getString(R.string.empty_list_str));

        listTitlesConstraintLayout.setVisibility(View.INVISIBLE);
        mProgressBar.setVisibility(View.INVISIBLE);
        issuesTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void setPresenter(ContestantContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onContestantItemClick(String name, String numberOfPages) {
        //Might use the presenter here if there was an operation that doesn't require android package
        Toast.makeText(getContext(),mPresenter.buildContestantClickString(name, numberOfPages),Toast.LENGTH_LONG).show();
    }

    @Override
    public void showList(List<Contestant> contestantList) {
        mAdapter.setContestantsList(contestantList);

        listTitlesConstraintLayout.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.INVISIBLE);
        issuesTextView.setVisibility(View.INVISIBLE);
    }
}
