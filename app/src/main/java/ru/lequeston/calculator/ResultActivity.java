
package ru.lequeston.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;


public class ResultActivity extends AppCompatActivity {
    private static final String TAG = "ResultActivity";
    private static final String EXTRA_FIRST_NUMBER = "first_number";
    private static final String EXTRA_SECOND_NUMBER = "second_number";
    private static final String KEY_RESULT = "result";

    private TextView mResultTextView;

    private int mFirstNumber;
    private int mSecondNumber;
    private String mResult;
    private HelpBroadcastReceiver receiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        mResultTextView = (TextView) findViewById(R.id.result_text_view);

        if (savedInstanceState != null){
            mResult = savedInstanceState.getString(KEY_RESULT);
            mResultTextView.setText(mResult);
        }

        mFirstNumber = getIntent().getIntExtra(EXTRA_FIRST_NUMBER, 0);
        mSecondNumber = getIntent().getIntExtra(EXTRA_SECOND_NUMBER, 0);

        if(mResultTextView.getText().length() == 0) {

            Intent intent = CalculatorIntentService.newIntent(ResultActivity.this, mFirstNumber, mSecondNumber);
            startService(intent);

            receiver = new HelpBroadcastReceiver();
            //brsCreated = true;

            IntentFilter intentFilter = new IntentFilter(CalculatorIntentService.TAG);
            intentFilter.addCategory(Intent.CATEGORY_DEFAULT);
            registerReceiver(receiver, intentFilter);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_RESULT, mResult);
    }

    public static Intent newIntent(Context packageContext, int firstNumber, int secondNumber) {
        Intent intent = new Intent(packageContext, ResultActivity.class);
        intent.putExtra(EXTRA_FIRST_NUMBER, firstNumber);
        intent.putExtra(EXTRA_SECOND_NUMBER, secondNumber);
        return intent;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public class HelpBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            mResult = CalculatorIntentService.getResult(intent);
            mResultTextView.setText(mResult);
        }
    }
}