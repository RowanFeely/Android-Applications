package edu.monash.fit2081.countryinfo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Wiki_Country extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wiki__country);
    }
    // unpack country detail country_name as myWebView
    // set browser as default application myWebView.setWebViewClient (new WebViewClient())
    // get id of your web view from UI
    //.loadURL(wikipedia.org/wiki/country_name)
    // override onCreateOptionsMenu
    // override onOptionItemSelected
    // android,R.id.home
    /
}
