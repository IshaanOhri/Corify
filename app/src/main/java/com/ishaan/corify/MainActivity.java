package com.ishaan.corify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.WindowManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigation;
    private boolean shouldExecuteOnResume;
    public static String date;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(API.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private ArrayList<String> source = new ArrayList<>();
    private ArrayList<String> title = new ArrayList<>();
    private ArrayList<String> description = new ArrayList<>();
    private ArrayList<String> url = new ArrayList<>();
    private ArrayList<String> imageURL = new ArrayList<>();

    private ArrayList<String> source2 = new ArrayList<>();
    private ArrayList<String> title2 = new ArrayList<>();
    private ArrayList<String> description2 = new ArrayList<>();
    private ArrayList<String> url2 = new ArrayList<>();
    private ArrayList<String> imageURL2 = new ArrayList<>();

    private ArrayList<StateData> stateData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        shouldExecuteOnResume = false;

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        bottomNavigation = findViewById(R.id.bottomNavigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new IndiaFragment()).commit();
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                Fragment selectedFragment = null;

                switch (menuItem.getItemId())
                {
                    case R.id.world:
                        selectedFragment = new WorldFragment();
                        break;

                    case R.id.india:
                        selectedFragment = new IndiaFragment();
                        break;

                    case R.id.news:
                        selectedFragment = new NewsFragment();
                        break;

                    case R.id.settings:
                        selectedFragment = new SettingsFragment();
                        break;

                }

                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,selectedFragment).commit();

                return true;
            }
        });

    }

    @Override
    public void onBackPressed() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("INFO","Resume");
        if(shouldExecuteOnResume){
            final SharedPreferences[] sharedPreferences = {getSharedPreferences("com.ishaanohri.corify", Context.MODE_PRIVATE)};
            final Gson[] gson = {new Gson()};
            final String[] json = {sharedPreferences[0].getString("indiaTitle", null)};

            API api2 = retrofit.create(API.class);
            Call<IndianStats> call2 = api2.getStats();

            call2.enqueue(new Callback<IndianStats>() {
                @Override
                public void onResponse(Call<IndianStats> call, retrofit2.Response<IndianStats> response) {
                    stateData = new ArrayList<>();

                    for(int i = 0 ; i < response.body().getStatewise().size() ; i++){
                        if(response.body().getStatewise().get(i).getActive() != null){
                            stateData.add(response.body().getStatewise().get(i));
                        }else {
                            stateData.add(new StateData());
                        }
                    }
                    Log.i("INFO","Indian Stats Successful");
                    sharedPreferences[0] = getSharedPreferences("com.ishaanohri.corify", Context.MODE_PRIVATE);
                    gson[0] = new Gson();
                    json[0] = gson[0].toJson(stateData);
                    sharedPreferences[0].edit().putString("stateData", json[0]).apply();
                }

                @Override
                public void onFailure(Call<IndianStats> call, Throwable t) {
                    Log.i("INFO","Indian Stats Failure");
                }
            });

            API api = retrofit.create(API.class);
            Call<Response> call = api.getResponse();

            call.enqueue(new Callback<Response>() {
                @Override
                public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                    String testElement = response.body().getArticles().get(0).getTitle();
                    source = new ArrayList<>();
                    title = new ArrayList<>();
                    description = new ArrayList<>();
                    url = new ArrayList<>();
                    imageURL = new ArrayList<>();

                    for(int i = 0 ; i < response.body().getArticles().size() ; i++){
                        if(response.body().getArticles().get(i).getSource().getName() != null){
                            source.add(response.body().getArticles().get(i).getSource().getName());
                        }else {
                            source.add("");
                        }
                        if(response.body().getArticles().get(i).getTitle() != null){
                            title.add(response.body().getArticles().get(i).getTitle());
                        }else {
                            source.add("");
                        }
                        if(response.body().getArticles().get(i).getDescription() != null){
                            description.add(response.body().getArticles().get(i).getDescription());
                        }else {
                            source.add("");
                        }
                        if(response.body().getArticles().get(i).getUrl() != null){
                            url.add(response.body().getArticles().get(i).getUrl());
                        }else {
                            source.add("");
                        }
                        if(response.body().getArticles().get(i).getUrlToImage() != null){
                            imageURL.add(response.body().getArticles().get(i).getUrlToImage());
                        }else {
                            source.add("");
                        }
                    }

                    Log.i("INFO","Indian News Successful");
                    sharedPreferences[0] = getSharedPreferences("com.ishaanohri.corify", Context.MODE_PRIVATE);
                    gson[0] = new Gson();
                    json[0] = gson[0].toJson(title);
                    sharedPreferences[0].edit().putString("indiaTitle", json[0]).apply();
                    json[0] = gson[0].toJson(description);
                    sharedPreferences[0].edit().putString("indiaDescription", json[0]).apply();
                    json[0] = gson[0].toJson(source);
                    sharedPreferences[0].edit().putString("indiaSource", json[0]).apply();
                    json[0] = gson[0].toJson(url);
                    sharedPreferences[0].edit().putString("indiaURL", json[0]).apply();
                    json[0] = gson[0].toJson(imageURL);
                    sharedPreferences[0].edit().putString("indiaImageURL", json[0]).apply();
                }

                @Override
                public void onFailure(Call<Response> call, Throwable t) {
                    Log.i("INFO","Indian News Failure");
                }
            });

            call = api.getWorld();

            call.enqueue(new Callback<Response>() {
                @Override
                public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                    String testElement = response.body().getArticles().get(0).getTitle();
                    source2 = new ArrayList<>();
                    title2 = new ArrayList<>();
                    description2 = new ArrayList<>();
                    url2 = new ArrayList<>();
                    imageURL2 = new ArrayList<>();

                    for(int i = 0 ; i < response.body().getArticles().size() ; i++){
                        if(response.body().getArticles().get(i).getSource().getName() != null){
                            source2.add(response.body().getArticles().get(i).getSource().getName());
                        }else {
                            source.add("");
                        }
                        if(response.body().getArticles().get(i).getTitle() != null){
                            title2.add(response.body().getArticles().get(i).getTitle());
                        }else {
                            source.add("");
                        }
                        if(response.body().getArticles().get(i).getDescription() != null){
                            description2.add(response.body().getArticles().get(i).getDescription());
                        }else {
                            source.add("");
                        }
                        if(response.body().getArticles().get(i).getUrl() != null){
                            url2.add(response.body().getArticles().get(i).getUrl());
                        }else {
                            source.add("");
                        }
                        if(response.body().getArticles().get(i).getUrlToImage() != null){
                            imageURL2.add(response.body().getArticles().get(i).getUrlToImage());
                        }else {
                            source.add("");
                        }
                    }

                    Log.i("INFO","World News Successful");
                    sharedPreferences[0] = getSharedPreferences("com.ishaanohri.corify",Context.MODE_PRIVATE);
                    gson[0] = new Gson();
                    json[0] = gson[0].toJson(title2);
                    sharedPreferences[0].edit().putString("worldTitle", json[0]).apply();
                    json[0] = gson[0].toJson(description2);
                    sharedPreferences[0].edit().putString("worldDescription", json[0]).apply();
                    json[0] = gson[0].toJson(source2);
                    sharedPreferences[0].edit().putString("worldSource", json[0]).apply();
                    json[0] = gson[0].toJson(url2);
                    sharedPreferences[0].edit().putString("worldURL", json[0]).apply();
                    json[0] = gson[0].toJson(imageURL2);
                    sharedPreferences[0].edit().putString("worldImageURL", json[0]).apply();

                    Calendar calander = Calendar.getInstance();
                    SimpleDateFormat simpledateformat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                    date = simpledateformat.format(calander.getTime());

                    json[0] = gson[0].toJson(date);
                    sharedPreferences[0].edit().putString("lastUpdate", json[0]).apply();
                }

                @Override
                public void onFailure(Call<Response> call, Throwable t) {
                    Log.i("INFO","World News Failure");
                    new AlertDialog.Builder(MainActivity.this)
                            .setIcon(R.drawable.logo)
                            .setTitle(R.string.offlineTitle)
                            .setCancelable(false)
                            .setMessage(R.string.offlineMessage)
                            .setPositiveButton("Yes", null)
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    MainActivity.this.finish();
                                    System.exit(0);
                                }
                            })
                            .show();
                }
            });
        } else{
            shouldExecuteOnResume = true;
            Calendar calander = Calendar.getInstance();
            SimpleDateFormat simpledateformat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            date = simpledateformat.format(calander.getTime());

            final SharedPreferences[] sharedPreferences = {getSharedPreferences("com.ishaanohri.corify", Context.MODE_PRIVATE)};
            final Gson[] gson = {new Gson()};
            final String[] json = {sharedPreferences[0].getString("indiaTitle", null)};
            json[0] = gson[0].toJson(date);
            sharedPreferences[0].edit().putString("lastUpdate", json[0]).apply();
        }
    }
}
