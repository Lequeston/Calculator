package ru.lequeston.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

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
        mCalculateButton = (Button) findViewById(R.id.calculate_button);

        mCalculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstNumber = mFirstNumberEditText.getText().toString();
                String secondNumber = mSecondNumberEditText.getText().toString();
                mFirstNumber = Integer.parseInt(firstNumber);
                mSecondNumber = Integer.parseInt(secondNumber);
                new Calculate().execute();
            }
        });
    }

    public class Calculate extends AsyncTask<Void, Integer, Void> {

        private int mResult;

        @Override
        protected Void doInBackground(Void... voids) {
            mResult = mFirstNumber + mSecondNumber;
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Intent intent = ResultActivity.newIntent(MainActivity.this, mResult);
            startActivity(intent);
        }
    }
}