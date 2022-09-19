package com.myseptember.firstotushomework;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = "myLogs";
    AboutDetails aboutDetails;

    Button btnBack;
    TextView text;
    TextView title;
    ImageView image;
    CheckBox like;
    EditText comment;

    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        aboutDetails = (AboutDetails) getIntent().getParcelableExtra("Det");

        btnBack = (Button) findViewById(R.id.btnBack);
        text = (TextView) findViewById(R.id.describingText);
        title = (TextView) findViewById(R.id.title);
        image = (ImageView) findViewById(R.id.image);
        like = (CheckBox) findViewById(R.id.likeFilm);
        comment = (EditText) findViewById(R.id.commentText);

        image.setImageResource(getResources().getIdentifier(aboutDetails.getPathToPicture(), "drawable", getPackageName()));

        int strId = getResources().getIdentifier(aboutDetails.getPathToTitle(), "string", getPackageName());
        String strValue = getString(strId);
        name=strValue;
        title.setText(strValue);

        strId = getResources().getIdentifier(aboutDetails.getPathToText(), "string", getPackageName());
        strValue = getString(strId);
        text.setText(strValue);

        btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this::onClick);

        like.setChecked(getIntent().getBooleanExtra("Like",false));
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBack:
                Intent data = new Intent();
                data.putExtra(MainActivity.ACCESS_MESSAGE, "Activity Closed");
                data.putExtra("Like", like.isChecked());
                Log.println(Log.INFO,TAG,"Для фильма "+name+" наличие лайка: " + String.valueOf(like.isChecked()));
                data.putExtra("Name", name);
                data.putExtra("Comment", comment.getText().toString());
                Log.println(Log.INFO,TAG,"Для фильма "+name+" комментарий: " + comment.getText().toString());
                setResult(RESULT_OK, data);
                finish();
                break;
            default:
                break;
        }
    }

}