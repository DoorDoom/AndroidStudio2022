package com.myseptember.firstotushomework;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    static final String ACCESS_MESSAGE="ACCESS_MESSAGE";

    @BindViews({R.id.btnDetails,R.id.btnDetails2,R.id.btnDetails3,R.id.btnDetails4,R.id.btnDetails5})
    List<View> detailsButtons;

    @BindViews({R.id.title,R.id.title2,R.id.title3,R.id.title4,R.id.title5})
    List<TextView> titles;

    @BindViews({R.id.like,R.id.like2,R.id.like3,R.id.like4,R.id.like5})
    List<CheckBox> checkLikeBoxes;

    @BindViews({R.id.comment,R.id.comment2,R.id.comment3,R.id.comment4,R.id.comment5})
    List<TextView> comments;


    Button btnInvite;

    ActivityResultLauncher<Intent> mStartForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    int i=0;
                    if(result.getResultCode() == Activity.RESULT_OK){
                        Intent intent = result.getData();
                        for(TextView element: titles) {
                            if(element.getText().equals(intent.getStringExtra("Name"))){
                                checkLikeBoxes.get(i).setChecked( intent.getBooleanExtra("Like",false));
                                comments.get(i).setText( intent.getStringExtra("Comment"));
                                comments.get(i).setVisibility(View.VISIBLE);
                            break;}
                            i++;
                        }
                        String accessMessage = intent.getStringExtra(ACCESS_MESSAGE);
                        Toast.makeText(MainActivity.this, ACCESS_MESSAGE, Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Ошибка доступа", Toast.LENGTH_SHORT).show();
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        btnInvite = (Button) findViewById(R.id.btnInvite);
        btnInvite.setOnClickListener(this);
    }

    @OnClick({ R.id.btnDetails,R.id.btnDetails2,R.id.btnDetails3,R.id.btnDetails4,R.id.btnDetails5,R.id.btnInvite })
    public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        switch (v.getId()) {
            case R.id.btnDetails:
                titles.get(0).setTextColor(Color.MAGENTA);
                intent.putExtra("Det",new AboutDetails("jojo1","jojo1","jojo1"));
                intent.putExtra("Like", checkLikeBoxes.get(0).isChecked());
                mStartForResult.launch(intent);
                break;
            case R.id.btnDetails2:
                titles.get(1).setTextColor(Color.MAGENTA);;
                intent.putExtra("Det",new AboutDetails("jojo2","jojo2","jojo2"));
                intent.putExtra("Like", checkLikeBoxes.get(1).isChecked());
                mStartForResult.launch(intent);
                break;
            case R.id.btnDetails3:
                titles.get(2).setTextColor(Color.MAGENTA);
                intent.putExtra("Det",new AboutDetails("jojo3","jojo3","jojo3"));
                intent.putExtra("Like", checkLikeBoxes.get(2).isChecked());
                mStartForResult.launch(intent);
                break;
            case R.id.btnDetails4:
                titles.get(3).setTextColor(Color.MAGENTA);
                intent.putExtra("Det",new AboutDetails("jojo4","jojo4","jojo4"));
                intent.putExtra("Like", checkLikeBoxes.get(3).isChecked());
                mStartForResult.launch(intent);
                break;
            case R.id.btnDetails5:
                titles.get(4).setTextColor(Color.MAGENTA);
                intent.putExtra("Det",new AboutDetails("jojo5","jojo5","jojo5"));
                intent.putExtra("Like", checkLikeBoxes.get(4).isChecked());
                mStartForResult.launch(intent);
                break;
            case R.id.btnInvite:
                String textMessage = "Share with friends";
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, textMessage);
                sendIntent.setType("text/plain");
                String title = getResources().getString(R.string.chooser_title);
                Intent chooser = Intent.createChooser(sendIntent, title);
                if (sendIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(chooser);
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {

        for(TextView element: titles)
            outState.putInt(String.valueOf(element.getId()),element.getCurrentTextColor());
        for(CheckBox element: checkLikeBoxes)
            outState.putBoolean(String.valueOf(element.getId()),element.isChecked());
        super.onSaveInstanceState(outState);

    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        for(TextView element: titles)
            element.setTextColor(savedInstanceState.getInt(String.valueOf(element.getId())));
        for(CheckBox element: checkLikeBoxes){
            element.setChecked(savedInstanceState.getBoolean(String.valueOf(element.getId())));
        }
        super.onRestoreInstanceState(savedInstanceState);
    }
}