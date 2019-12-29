package ru.lequeston.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private static final String KEY_INPUT_FIRST_NUMBER = "input_first_number";
    private static final String KEY_INPUT_SECOND_NUMBER = "input_second_number";

    private EditText mFirstNumberEditText;
    private EditText mSecondNumberEditText;
    private Button mCalculateButton;

    private int mFirstNumber;
    private int mSecondNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirstNumberEditText = (EditText) findViewById(R.id.first_number_edit_text);
        mSecondNumberEditText = (EditText) findViewById(R.id.second_number_edit_text);

        if (savedInstanceState != null){
            String firstNumber = savedInstanceState.getString(KEY_INPUT_FIRST_NUMBER);
            String secondNumber = savedInstanceState.getString(KEY_INPUT_SECOND_NUMBER);
            mFirstNumberEditText.setText(firstNumber);
            mSecondNumberEditText.setText(secondNumber);
        }

        mCalculateButton = (Button) findViewById(R.id.calculate_button);

        mCalculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstNumber = mFirstNumberEditText.getText().toString();
                String secondNumber = mSecondNumberEditText.getText().toString();
                mFirstNumber = Integer.parseInt(firstNumber);
                mSecondNumber = Integer.parseInt(secondNumber);
                Intent intent = ResultActivity.newIntent(MainActivity.this, mFirstNumber, mSecondNumber);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_INPUT_FIRST_NUMBER, mFirstNumberEditText.getText().toString());
        outState.putString(KEY_INPUT_SECOND_NUMBER, mSecondNumberEditText.getText().toString());
    }
}