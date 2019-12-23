
package ru.lequeston.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    private static final String EXTRA_RESULT_CALCULATE = "result_calculate";

    private TextView mResultTextView;

    private int mResultCalculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        mResultCalculate = getIntent().getIntExtra(EXTRA_RESULT_CALCULATE, 0);

        mResultTextView = (TextView)findViewById(R.id.result_text_view);
        mResultTextView.setText(((Integer)mResultCalculate).toString());

    }

    public static Intent newIntent(Context packageContext, int result){
        Intent intent = new Intent(packageContext, ResultActivity.class);
        intent.putExtra(EXTRA_RESULT_CALCULATE, result);
        return intent;
    }
}