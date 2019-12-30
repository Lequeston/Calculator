package ru.lequeston.calculator;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import java.util.concurrent.TimeUnit;


public class CalculatorIntentService extends IntentService {
    public static final String TAG = "CalculatorIntentService";
    private static final String EXTRA_FIRST_NUMBER = "first_number";
    private static final String EXTRA_SECOND_NUMBER = "second_number";
    private static final String EXTRA_KEY_RESULT = "result";

    private int mFirstNumber;
    private int mSecondNumber;
    private String mResult;

    public static Intent newIntent(Context packageContext, int first, int second) {
        Intent intent = new Intent(packageContext, CalculatorIntentService.class);
        intent.putExtra(EXTRA_FIRST_NUMBER, first);
        intent.putExtra(EXTRA_SECOND_NUMBER, second);
        return intent;
    }

    public CalculatorIntentService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            mFirstNumber = intent.getIntExtra(EXTRA_FIRST_NUMBER, 0);
            mSecondNumber = intent.getIntExtra(EXTRA_SECOND_NUMBER, 0);

            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mResult = Integer.toString(mFirstNumber + mSecondNumber);
            Intent responseIntent = new Intent();
            responseIntent.setAction(TAG);
            responseIntent.addCategory(Intent.CATEGORY_DEFAULT);
            responseIntent.putExtra(EXTRA_KEY_RESULT, mResult);
            sendBroadcast(responseIntent);
        }
    }

    public static String getResult(Intent intent){
        return intent.getStringExtra(EXTRA_KEY_RESULT);
    }
}
