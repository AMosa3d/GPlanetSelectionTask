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
import android.widget.ImageView;
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
    ConstraintLayout winnersConstraintLayout;
    RecyclerView contestantRecyclerView;
    ImageView firstWinnerImageView,secondWinnerImageView,thirdWinnerImageView;
    TextView firstWinnerNameTextView,secondWinnerNameTextView,thirdWinnerNameTextView;
    TextView firstWinnerPagesTextView,secondWinnerPagesTextView,thirdWinnerPagesTextView;

    private final String RECYCLER_POSITION = "position";
    private final String PAGES_STRING = " pages";

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
        winnersConstraintLayout = view.findViewById(R.id.winners_layout);
        contestantRecyclerView = view.findViewById(R.id.contestants_recyclerView);

        firstWinnerImageView = view.findViewById(R.id.first_reader_imageView);
        secondWinnerImageView = view.findViewById(R.id.second_reader_imageView);
        thirdWinnerImageView = view.findViewById(R.id.third_reader_imageView);
        firstWinnerNameTextView = view.findViewById(R.id.first_reader_name_textView);
        secondWinnerNameTextView = view.findViewById(R.id.second_reader_name_textView);
        thirdWinnerNameTextView = view.findViewById(R.id.third_reader_name_textView);
        firstWinnerPagesTextView = view.findViewById(R.id.first_reader_pages_textView);
        secondWinnerPagesTextView = view.findViewById(R.id.second_reader_pages_textView);
        thirdWinnerPagesTextView = view.findViewById(R.id.third_reader_pages_textView);

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
        winnersConstraintLayout.setVisibility(View.INVISIBLE);
        mProgressBar.setVisibility(View.VISIBLE);
        issuesTextView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showLoadingSuccessful() {
        Toast.makeText(getContext(), getString(R.string.loading_complete_str),Toast.LENGTH_SHORT).show();

        winnersConstraintLayout.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.INVISIBLE);
        issuesTextView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showLoadingError() {
        issuesTextView.setText(getString(R.string.loading_error_str));

        winnersConstraintLayout.setVisibility(View.INVISIBLE);
        mProgressBar.setVisibility(View.INVISIBLE);
        issuesTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showEmptyList() {
        issuesTextView.setText(getString(R.string.empty_list_str));

        winnersConstraintLayout.setVisibility(View.INVISIBLE);
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
        fillWinnersLayoutElements(contestantList);

        //remove the first 3 winners
        for(int i=0;i<3;i++)
            if(contestantList.size() > 0)
                contestantList.remove(0);

        mAdapter.setContestantsList(contestantList);

        winnersConstraintLayout.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.INVISIBLE);
        issuesTextView.setVisibility(View.INVISIBLE);
    }

    void fillWinnersLayoutElements(List<Contestant> contestantList)
    {
        if(contestantList.size() >= 1)
        {
            Contestant firstContestant = contestantList.get(0);
            firstWinnerNameTextView.setText(firstContestant.getName());
            firstWinnerPagesTextView.setText(Integer.toString(firstContestant.getNumberOfPages()) + PAGES_STRING);
        }

        if(contestantList.size() >= 2)
        {
            Contestant secondContestant = contestantList.get(1);
            secondWinnerNameTextView.setText(secondContestant.getName());
            secondWinnerPagesTextView.setText(Integer.toString(secondContestant.getNumberOfPages()) + PAGES_STRING);
        }

        if(contestantList.size() >= 3)
        {
            Contestant thirdContestant = contestantList.get(2);
            thirdWinnerNameTextView.setText(thirdContestant.getName());
            thirdWinnerPagesTextView.setText(Integer.toString(thirdContestant.getNumberOfPages()) + PAGES_STRING);
        }

    }
}
