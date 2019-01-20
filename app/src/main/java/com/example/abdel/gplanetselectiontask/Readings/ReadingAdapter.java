package com.example.abdel.gplanetselectiontask.Readings;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.abdel.gplanetselectiontask.R;

import java.util.ArrayList;
import java.util.List;

public class ReadingAdapter extends RecyclerView.Adapter<ReadingAdapter.ReadingViewHolder> {

    private List<Reading> readingList;

    public void setReadingList(List<Reading> readingList) {
        this.readingList = readingList;
        notifyDataSetChanged();
    }

    public void appendReading(Reading reading)
    {
        if(readingList == null)
            readingList = new ArrayList<>();
        readingList.add(reading);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ReadingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.readings_single_item,parent,false);

        return new ReadingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReadingViewHolder holder, int position) {
        Reading currentReading = readingList.get(position);
        holder.bind(currentReading.getId(), currentReading.getPageFrom(),
                currentReading.getPageTo(), currentReading.getReaderId());
    }

    @Override
    public int getItemCount() {
        if(readingList == null)
            return 0;
        return readingList.size();
    }

    class ReadingViewHolder extends RecyclerView.ViewHolder
    {
        TextView idTextView, pagesIntervalTextView, readerIdTextView;

        public ReadingViewHolder(View itemView) {
            super(itemView);

            //set views
            idTextView = itemView.findViewById(R.id.readings_id_textView);
            pagesIntervalTextView = itemView.findViewById(R.id.readings_pages_textView);
            readerIdTextView = itemView.findViewById(R.id.reader_id_textView);
        }

        void bind(int id, int fromPage, int toPage, int readerId)
        {
            final String PAGES_INTERVAL = "From " + fromPage + " to " + toPage;

            idTextView.setText(Integer.toString(id));
            pagesIntervalTextView.setText(PAGES_INTERVAL);
            readerIdTextView.setText(Integer.toString(readerId));
        }
    }
}
