package com.example.abdel.gplanetselectiontask.CustomLayouts;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.StyleableRes;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.abdel.gplanetselectiontask.R;

public class TopWinnersCustomView extends ConstraintLayout {

    ImageView imageView;
    TextView rankTextView, nameTextView, pagesTextView;

    @StyleableRes
    int indexImage = 0;
    @StyleableRes
    int indexName = 1;
    @StyleableRes
    int indexRank = 2;
    @StyleableRes
    int indexPages = 3;

    public TopWinnersCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.top_winner_custom_view,this);

        initializeComponents();

        int[] attrSet = {R.attr.image, R.attr.name, R.attr.rank, R.attr.pages};

        setComponentsContent(context.obtainStyledAttributes(attrs,attrSet));
    }

    private void initializeComponents()
    {
        imageView = findViewById(R.id.image);
        rankTextView = findViewById(R.id.rank);
        nameTextView = findViewById(R.id.name);
        pagesTextView = findViewById(R.id.pages);
    }

    private void setComponentsContent(TypedArray attr)
    {
        Drawable drawable = attr.getDrawable(indexImage);
        String name = (String) attr.getText(indexName);
        String rank = (String) attr.getText(indexRank);
        String pages = (String) attr.getText(indexPages);

        if (drawable != null)
            imageView.setImageDrawable(drawable);

        if (name != null)
            nameTextView.setText(name);

        if (rank != null)
            rankTextView.setText(rank);

        if (pages != null)
            pagesTextView.setText(pages);
    }

    public void setImageView(Drawable drawable) {
        imageView.setImageDrawable(drawable);
    }

    public void setRankTextView(String rank) {
        rankTextView.setText(rank);
    }

    public void setNameTextView(String name) {
        nameTextView.setText(name);
    }

    public void setPagesTextView(String pages) {
        pagesTextView.setText(pages);
    }
}
