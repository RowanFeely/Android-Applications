package edu.monash.javavsxml;

import android.content.res.Resources;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //create an EditText and set its properties
        TextView javaTextView = new TextView(this);
        javaTextView.setId(R.id.javaTextView);
        javaTextView.setText("Created in Java");

        javaTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 36);

//        Resources r = getResources();
//        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, r.getDisplayMetrics());

        int paddingInPixels = (int) (20 * Resources.getSystem().getDisplayMetrics().density);
        javaTextView.setPadding(paddingInPixels, 0, paddingInPixels, 0);

        javaTextView.setTextColor(Color.rgb(30, 174, 30));
        javaTextView.setBackgroundColor(Color.rgb(212, 218, 212));

        //now create a Relative.LayoutParams for the TextView to tell its parent Relative Layout how it wants to be sized positioned and behave
        RelativeLayout.LayoutParams javaTextViewParams =
                new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT
                        );
        javaTextViewParams.addRule(RelativeLayout.ABOVE, R.id.xmlTextView);
        javaTextViewParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        javaTextViewParams.addRule(RelativeLayout.CENTER_VERTICAL);

        int marginInPixels = (int) (20 * Resources.getSystem().getDisplayMetrics().density);
        javaTextViewParams.setMargins(0, 0, 0, marginInPixels);


        RelativeLayout xmlLayout = (RelativeLayout) findViewById(R.id.activity_main);
        xmlLayout.addView(javaTextView, javaTextViewParams);


    }
}
