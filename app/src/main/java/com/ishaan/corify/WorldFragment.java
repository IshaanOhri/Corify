package com.ishaan.corify;

import android.app.Activity;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WorldFragment extends Fragment {

    private WebView webView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_world, container, false);

        webView = view.findViewById(R.id.webView);
        webView.setBackgroundColor(Color.parseColor("#0A141E"));

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());

        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Activity.CONNECTIVITY_SERVICE);
        try{
            if(cm.getActiveNetworkInfo().isConnected()){
                webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
                webView.loadUrl("http://www.corify.in/");
            }
            else{
                webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
                webView.loadUrl("http://www.corify.in/");
            }
        }catch (Exception e){
            webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
            webView.loadUrl("http://www.corify.in/");
        }

        return view;
    }
}
