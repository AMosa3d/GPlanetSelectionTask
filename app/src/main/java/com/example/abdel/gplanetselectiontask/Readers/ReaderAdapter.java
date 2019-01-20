package com.example.abdel.gplanetselectiontask.Readers;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.abdel.gplanetselectiontask.R;

import java.util.ArrayList;
import java.util.List;

public class ReaderAdapter extends RecyclerView.Adapter<ReaderAdapter.ReaderViewHolder> {

    private List<Reader> readerList;

    public void setReaderList(List<Reader> readerList) {
        this.readerList = readerList;
        notifyDataSetChanged();
    }

    public void appendReader(Reader reader)
    {
        if(readerList == null)
            readerList = new ArrayList<>();
        readerList.add(reader);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ReaderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.reader_single_item,parent,false);

        return new ReaderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReaderViewHolder holder, int position) {
        Reader currentReader = readerList.get(position);
        holder.bind(currentReader.getId(),currentReader.getReaderName());
    }

    @Override
    public int getItemCount() {
        if (readerList == null)
            return 0;
        return readerList.size();
    }

    class ReaderViewHolder extends RecyclerView.ViewHolder
    {
        TextView idTextView, readerNameTextView;

        public ReaderViewHolder(View itemView) {
            super(itemView);

            //set views
            idTextView = itemView.findViewById(R.id.reader_id_textView);
            readerNameTextView = itemView.findViewById(R.id.reader_name_textView);
        }

        void bind(int id, String name)
        {
            idTextView.setText(Integer.toString(id));
            readerNameTextView.setText(name);
        }
    }
}
