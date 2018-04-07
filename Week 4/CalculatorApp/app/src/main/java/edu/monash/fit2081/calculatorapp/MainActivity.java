package edu.monash.fit2081.calculatorapp;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;


public class MainActivity extends AppCompatActivity {
    private double valueOne = Double.NaN;
    private double valueTwo;
    private static final char ADDITION = '+';
    private static final char SUBTRACTION = '-';
    private static final char MULTIPLICATION = '*';
    private static final char DIVISION = '/';
    private static final char NO_OPERATION = '?';

    private char CURRENT_ACTION;
    private DecimalFormat decimalFormat;
    public TextView interScreen;  // Intermediate result Screen
    private TextView resultScreen; // Result Screen


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Reference both TextViews
        interScreen = (TextView) findViewById(R.id.InterScreen);
        resultScreen = (TextView) findViewById(R.id.resultScreen);
        decimalFormat = new DecimalFormat("#.##########");
    }

    public void buttonZeroClick(View v) {
        interScreen.setText(interScreen.getText() + "0");
    }

    public void buttonOneClick(View v) {
        interScreen.setText(interScreen.getText() + "1");
    }

    public void buttonTwoClick(View v) {
        interScreen.setText(interScreen.getText() + "2");
    }

    public void buttonThreeClick(View v) {
        interScreen.setText(interScreen.getText() + "3");
    }

    public void buttonFourClick(View v) {
        interScreen.setText(interScreen.getText() + "4");
    }

    public void buttonFiveClick(View v) {
        interScreen.setText(interScreen.getText() + "5");
    }

    public void buttonSixClick(View v) {
        interScreen.setText(interScreen.getText() + "6");
    }

    public void buttonSevenClick(View v) {
        interScreen.setText(interScreen.getText() + "7");
    }

    public void buttonEightClick(View v) {
        interScreen.setText(interScreen.getText() + "8");
    }

    public void buttonNineClick(View v) {
        interScreen.setText(interScreen.getText() + "9");
    }

    public void buttonDivisionClick(View v) {
        computeCalculation();
        if (Double.isNaN(valueOne)) {
            showToast("Invalid Key");
        } else {
            CURRENT_ACTION = DIVISION;
            resultScreen.setText(decimalFormat.format(valueOne) + "/");
            interScreen.setText(null);
        }
    }

    public void buttonEqualClick(View v) {
        computeCalculation();
        CURRENT_ACTION = NO_OPERATION;
        resultScreen.setText("=" + (valueOne));
        valueOne = Double.NaN;
        interScreen.setText(null);
    }

    public void buttonClearClick(View v) {
        String textString = interScreen.getText().toString();
        if( textString.length() > 0 ) {
            interScreen.setText(textString.substring(0, textString.length() - 1 ));
        } else {
            valueOne = Double.NaN;
            valueTwo = Double.NaN;
            interScreen.setText(null);
            resultScreen.setText(null);
        }
    }

    public void buttonMultiplicationClick(View v) {
        computeCalculation();
        if (Double.isNaN(valueOne)) {
            showToast("Invalid Key");
        } else {
            CURRENT_ACTION = MULTIPLICATION;
            resultScreen.setText((valueOne) + " *");
            interScreen.setText(null);
        }
    }

    public void buttonAdditionClick(View v) {
        computeCalculation();
        if (Double.isNaN(valueOne)) {
            showToast("Invalid Key");
        } else {
        CURRENT_ACTION = ADDITION;
        resultScreen.setText((valueOne) + " +");
        interScreen.setText(null);
        }
    }

    public void buttonSubtractionClick(View v) {
        computeCalculation();
        if (Double.isNaN(valueOne)) {
            showToast("Invalid Key");
        } else {
            CURRENT_ACTION = SUBTRACTION;
            resultScreen.setText((valueOne) + " -");
            interScreen.setText(null);
        }
    }

    public void buttonDotClicked(View v) {
        interScreen.setText(interScreen.getText() + ".");
    }

    private void computeCalculation() {

        if (!Double.isNaN(valueOne)) {
            String valueTwoString = interScreen.getText().toString();
            if (!valueTwoString.equals("")) {
                valueTwo = Double.parseDouble(valueTwoString);
                interScreen.setText(null);
                if (CURRENT_ACTION == ADDITION)
                    valueOne = this.valueOne + valueTwo;
                else if (CURRENT_ACTION == SUBTRACTION)
                    valueOne = this.valueOne - valueTwo;
                else if (CURRENT_ACTION == MULTIPLICATION)
                    valueOne = this.valueOne * valueTwo;
                else if (CURRENT_ACTION == DIVISION)
                    valueOne = this.valueOne / valueTwo;
            }
        } else {
            try {
                valueOne = Double.parseDouble(interScreen.getText().toString());
            } catch (Exception e) {
            }

        }
    }

    public void openCalc(View view) {
        Intent intent = new Intent(this, ScientificCalc.class);
        startActivity(intent);
    }

    private void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }
}

