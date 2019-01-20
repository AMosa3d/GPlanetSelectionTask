package com.example.abdel.gplanetselectiontask.Readers;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
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

public class ReaderFragment extends android.support.v4.app.Fragment implements ReaderContract.View {

    ReaderContract.Presenter mPresenter;
    ReaderAdapter mAdapter;

    ProgressBar mProgressBar;
    TextView issuesTextView;
    ConstraintLayout listTitlesConstraintLayout;
    RecyclerView readerRecyclerView;
    EditText readerNameEditText;
    ImageView addImageView;

    private final String RECYCLER_POSITION = "position";

    public ReaderFragment() {
    }

    public static ReaderFragment createNewInstance()
    {
        return new ReaderFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new ReaderAdapter();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.reader_fragment,container,false);

        //Set views
        mProgressBar = view.findViewById(R.id.progressBar);
        issuesTextView = view.findViewById(R.id.issue_textView);
        listTitlesConstraintLayout = view.findViewById(R.id.readers_list_titles_layout);
        readerRecyclerView = view.findViewById(R.id.reader_recyclerView);
        readerNameEditText = view.findViewById(R.id.reader_name_editText);
        addImageView = view.findViewById(R.id.add_reader_btn);

        //Set Recycler
        readerRecyclerView.setAdapter(mAdapter);
        readerRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));

        if(savedInstanceState != null)
            readerRecyclerView.setVerticalScrollbarPosition(savedInstanceState.getInt(RECYCLER_POSITION));

        //Set Listeners
        addImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.addReader(readerNameEditText.getText().toString());
                Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_anim);
                addImageView.startAnimation(animation);

                readerNameEditText.setText("");
            }
        });

        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(RECYCLER_POSITION,readerRecyclerView.getVerticalScrollbarPosition());
        super.onSaveInstanceState(outState);
    }

    @Override
    public void setLoadingBar() {
        listTitlesConstraintLayout.setVisibility(View.INVISIBLE);
        mProgressBar.setVisibility(View.VISIBLE);
        issuesTextView.setVisibility(View.INVISIBLE);
        readerRecyclerView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showLoadingSuccessful() {
        Toast.makeText(getContext(), getString(R.string.loading_complete_str),Toast.LENGTH_SHORT).show();

        listTitlesConstraintLayout.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.INVISIBLE);
        issuesTextView.setVisibility(View.INVISIBLE);
        readerRecyclerView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showLoadingError() {
        issuesTextView.setText(getString(R.string.loading_error_str));

        listTitlesConstraintLayout.setVisibility(View.INVISIBLE);
        mProgressBar.setVisibility(View.INVISIBLE);
        issuesTextView.setVisibility(View.VISIBLE);
        readerRecyclerView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showEmptyList() {
        issuesTextView.setText(getString(R.string.empty_list_str));

        listTitlesConstraintLayout.setVisibility(View.INVISIBLE);
        mProgressBar.setVisibility(View.INVISIBLE);
        issuesTextView.setVisibility(View.VISIBLE);
        readerRecyclerView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showList(List<Reader> readerList) {
        mAdapter.setReaderList(readerList);

        listTitlesConstraintLayout.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.INVISIBLE);
        issuesTextView.setVisibility(View.INVISIBLE);
        readerRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void setPresenter(ReaderContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void insertReaderCompleted(Reader reader) {
        mAdapter.appendReader(reader);
        Toast.makeText(getContext(),"Added",Toast.LENGTH_SHORT).show();

        listTitlesConstraintLayout.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.INVISIBLE);
        issuesTextView.setVisibility(View.INVISIBLE);
        readerRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void insertReaderFailed() {
        Toast.makeText(getContext(),"Sorry couldn't add this record",Toast.LENGTH_SHORT).show();
    }
}
