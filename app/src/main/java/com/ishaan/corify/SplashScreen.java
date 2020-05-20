package com.ishaan.corify;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.WindowManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pusher.pushnotifications.PushNotifications;

import java.lang.reflect.Type;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SplashScreen extends AppCompatActivity {

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

    private Boolean firstTime = true;
    private Boolean copyFirstTime = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        final SharedPreferences[] sharedPreferences = {getSharedPreferences("com.ishaanohri.corify", Context.MODE_PRIVATE)};
        final Gson[] gson = {new Gson()};
        final String[] json = {sharedPreferences[0].getString("indiaTitle", null)};

        Type type = new TypeToken<String>() {}.getType();
        String[] json2 = new String[]{sharedPreferences[0].getString("firstTime", "true")};
        firstTime = Boolean.parseBoolean((String) gson[0].fromJson(json2[0],type));

        Log.i("INFO",firstTime.toString());

        if(firstTime) {
            copyFirstTime = true;
            Log.i("INFO","First Login");
            PushNotifications.start(getApplicationContext(), "38c10eae-d5f0-422b-bbcd-53b6f1f33391");
            PushNotifications.addDeviceInterest("hello");
        }

        firstTime = false;

        json[0] = gson[0].toJson(firstTime);
        sharedPreferences[0].edit().putString("firstTime", json[0]).apply();

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

                new CountDownTimer(2000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {

                    }

                    @Override
                    public void onFinish() {
                        Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                }.start();
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Log.i("INFO","World News Failure");
                new AlertDialog.Builder(SplashScreen.this)
                        .setIcon(R.drawable.logo)
                        .setTitle(R.string.offlineTitle)
                        .setCancelable(false)
                        .setMessage(R.string.offlineMessage)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(copyFirstTime){
                                    firstTime = true;

                                    json[0] = gson[0].toJson(firstTime);
                                    sharedPreferences[0].edit().putString("firstTime", json[0]).apply();
                                    new AlertDialog.Builder(SplashScreen.this)
                                            .setIcon(R.drawable.logo)
                                            .setTitle("Error!")
                                            .setCancelable(false)
                                            .setMessage(R.string.firstLogin)
                                            .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    SplashScreen.this.finish();
                                                    System.exit(0);
                                                }
                                            }).show();
                                }else {
                                    Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                }
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SplashScreen.this.finish();
                                System.exit(0);
                            }
                        })
                        .show();
            }
        });
    }
}
