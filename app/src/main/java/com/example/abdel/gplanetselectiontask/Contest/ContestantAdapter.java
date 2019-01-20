package com.example.abdel.gplanetselectiontask.Contest;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.abdel.gplanetselectiontask.R;

import java.util.List;

public class ContestantAdapter extends RecyclerView.Adapter<ContestantAdapter.ContestantViewHolder> {

    List<Contestant> constantsList;
    ContestantItemClickListener mContestantItemClickListener;

    public ContestantAdapter(ContestantItemClickListener mContestantItemClickListener) {
        this.mContestantItemClickListener = mContestantItemClickListener;
    }

    public void setContestantsList(List<Contestant> constantsList) {
        this.constantsList = constantsList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ContestantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.contestant_single_item, parent,false);

        return new ContestantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContestantViewHolder holder, int position) {
        Contestant currentContestant = constantsList.get(position);
        holder.bind(position+1, currentContestant.getName(), currentContestant.getNumberOfPages());
    }


    @Override
    public int getItemCount() {
        if (constantsList == null)
            return 0;
        return constantsList.size();
    }

    class ContestantViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView rankTextView, readerNameTextView, numberOfPagesTextView;

        ContestantViewHolder(View itemView) {
            super(itemView);

            //set views
            itemView.setOnClickListener(this);
            rankTextView = itemView.findViewById(R.id.contestant_rank_textView);
            readerNameTextView = itemView.findViewById(R.id.contestant_name_textView);
            numberOfPagesTextView = itemView.findViewById(R.id.contestant_pages_textView);
        }

        void bind(int rank, String name, int pages)
        {
            rankTextView.setText(Integer.toString(rank));
            readerNameTextView.setText(name);
            numberOfPagesTextView.setText(Integer.toString(pages));
        }

        @Override
        public void onClick(View view) {
            mContestantItemClickListener.onContestantItemClick(readerNameTextView.getText().toString(),numberOfPagesTextView.getText().toString());
        }
    }
}
