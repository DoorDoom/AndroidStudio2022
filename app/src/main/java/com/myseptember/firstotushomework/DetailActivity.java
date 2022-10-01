package com.myseptember.firstotushomework;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class DetailActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private static final String TAG = "myLogs";
    AboutDetails aboutDetails;

    Button btnBack;
    TextView text;
    TextView title;
    ImageView image;
    CheckBox like;
    EditText comment;

    String name;
    DrawerLayout drawer;
    NavigationView navigationView;
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

        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);;
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        ImageView view = findViewById(R.id.imageview_animation_list);
        view.setImageResource(R.drawable.frame_anim);
        ((AnimationDrawable) view.getDrawable()).start();


    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.about) {
            Toast.makeText(getApplicationContext(), "Это новое приложение", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.mainScreen) {
            drawer.closeDrawer(GravityCompat.START);
            Intent data = new Intent();
            data.putExtra(MainActivity.ACCESS_MESSAGE, "Activity Closed");
            data.putExtra("Like", like.isChecked());
            Log.println(Log.INFO,TAG,"Для фильма "+name+" наличие лайка: " + String.valueOf(like.isChecked()));
            data.putExtra("Name", name);
            data.putExtra("Comment", comment.getText().toString());
            Log.println(Log.INFO,TAG,"Для фильма "+name+" комментарий: " + comment.getText().toString());
            setResult(RESULT_OK, data);
            finish();
            return true;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_layout, menu);
        return true;
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