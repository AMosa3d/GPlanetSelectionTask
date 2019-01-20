package com.example.abdel.gplanetselectiontask.Readings;

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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abdel.gplanetselectiontask.R;

import java.util.List;

public class ReadingFragment extends Fragment implements ReadingContract.View {

    ReadingContract.Presenter mPresenter;
    ReadingAdapter mAdapter;

    ProgressBar mProgressBar;
    TextView issuesTextView;
    ConstraintLayout listTitlesConstraintLayout;
    RecyclerView readingRecyclerView;
    EditText readerIdEditText,pagesIntervalEditText;
    ImageView addImageView;

    private final String RECYCLER_POSITION = "position";

    public ReadingFragment() {
    }

    public static ReadingFragment createNewInstance()
    {
        return new ReadingFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new ReadingAdapter();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.readings_fragment,container,false);

        //Set views
        mProgressBar = view.findViewById(R.id.progressBar);
        issuesTextView = view.findViewById(R.id.issue_textView);
        listTitlesConstraintLayout = view.findViewById(R.id.readings_list_titles_layout);
        readingRecyclerView = view.findViewById(R.id.readings_recyclerView);
        readerIdEditText = view.findViewById(R.id.reader_id_editText);
        pagesIntervalEditText = view.findViewById(R.id.reading_pages_editText);
        addImageView = view.findViewById(R.id.add_reading_btn);

        //Set Recycler
        readingRecyclerView.setAdapter(mAdapter);
        readingRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));

        if(savedInstanceState != null)
            readingRecyclerView.setVerticalScrollbarPosition(savedInstanceState.getInt(RECYCLER_POSITION));

        //Set Listeners
        addImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.addReading(pagesIntervalEditText.getText().toString(),readerIdEditText.getText().toString());
                Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_anim);
                addImageView.startAnimation(animation);
                readerIdEditText.setText("");
                pagesIntervalEditText.setText("");
            }
        });

        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(RECYCLER_POSITION,readingRecyclerView.getVerticalScrollbarPosition());
        super.onSaveInstanceState(outState);
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
    public void showList(List<Reading> readingList) {
        mAdapter.setReadingList(readingList);

        listTitlesConstraintLayout.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.INVISIBLE);
        issuesTextView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setPresenter(ReadingContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void insertReadingCompleted(Reading reading) {
        mAdapter.appendReading(reading);
        Toast.makeText(getContext(),"Added",Toast.LENGTH_SHORT).show();

        listTitlesConstraintLayout.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.INVISIBLE);
        issuesTextView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void insertReadingFailed() {
        Toast.makeText(getContext(),"Sorry couldn't add this record",Toast.LENGTH_SHORT).show();
    }
}
