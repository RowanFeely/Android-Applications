package edu.monash.fit2081.commonactionsimple;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View view = findViewById(R.id.mainActivityLayout);
        textView = findViewById(R.id.text);

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int x = (int) event.getX();
                int y = (int) event.getY();
                int action = event.getActionMasked();

                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        textView.setText("Down X="+x+",  Y="+y);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        textView.setText("Move X="+x+",  Y="+y);
                        break;
                    case MotionEvent.ACTION_UP:
                        textView.setText("UP X="+x+",  Y="+y);
                        break;
                }
                return true;
            }
        });
    }
}
