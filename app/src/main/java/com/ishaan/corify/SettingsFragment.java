package com.ishaan.corify;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pusher.pushnotifications.PushNotifications;

import java.lang.reflect.Type;

public class SettingsFragment extends Fragment {

    private String lastUpdate;
    private TextView lastUpdateTextView;
    private Boolean subscribe;
    private CheckBox subscribeButton;
    private CardView privacyPolicy;
    private String privacy = "https://ishaanohri.github.io/corify-privacypolicy-app.github.io/";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        lastUpdateTextView = view.findViewById(R.id.lastUpdateTextView);
        subscribeButton = view.findViewById(R.id.subscribeButton);
        privacyPolicy = view.findViewById(R.id.privacyPolicy);

        final SharedPreferences[] sharedPreferences = {getActivity().getSharedPreferences("com.ishaanohri.corify", Context.MODE_PRIVATE)};
        final Gson[] gson = {new Gson()};
        Type type = new TypeToken<String>() {}.getType();
        String[] json = {sharedPreferences[0].getString("lastUpdate", null)};
        lastUpdate = gson[0].fromJson(json[0],type);

        lastUpdateTextView.setText(String.format("Data last updated at %s ",lastUpdate));

        json = new String[]{sharedPreferences[0].getString("subscribe", "true")};
        subscribe = Boolean.parseBoolean((String) gson[0].fromJson(json[0],type));

        Log.i("INFO",subscribe.toString());

        if(subscribe) {
            subscribeButton.setChecked(true);
        }else {
            subscribeButton.setChecked(false);
        }

        privacyPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(privacy));
                startActivity(browserIntent);
            }
        });

        final String[] finalJson = json;
        subscribeButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    subscribe = true;
                    Log.i("INFO","Subscribed");
                    finalJson[0] = gson[0].toJson(subscribe);
                    sharedPreferences[0].edit().putString("subscribe", finalJson[0]).apply();
                    PushNotifications.start(getContext(), "38c10eae-d5f0-422b-bbcd-53b6f1f33391");
                    PushNotifications.addDeviceInterest("hello");
                }else {
                    subscribe = false;
                    Log.i("INFO","Unsubscribed");
                    finalJson[0] = gson[0].toJson(subscribe);
                    sharedPreferences[0].edit().putString("subscribe", finalJson[0]).apply();
                    PushNotifications.start(getContext(), "38c10eae-d5f0-422b-bbcd-53b6f1f33391");
                    PushNotifications.removeDeviceInterest("hello");
                }
            }
        });

        return view;
    }
}
