package com.yasand.testtask.Activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yasand.testtask.R;

public class ItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        Picasso.with(this)
                .load((getIntent().getStringExtra("uri")))
                .into((ImageView) findViewById(R.id.image_item_icon));
        ((TextView)findViewById(R.id.text_item_number)).setText(getIntent().getStringExtra("name"));
        ((TextView)findViewById(R.id.text_item_title)).setText(getIntent().getStringExtra("title"));
    }
}
