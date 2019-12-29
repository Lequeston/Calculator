
package ru.lequeston.calculator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;


public class ResultActivity extends AppCompatActivity {
    private static final String TAG = "ResultActivity";
    private static final String EXTRA_FIRST_NUMBER = "first_number";
    private static final String EXTRA_SECOND_NUMBER = "second_number";
    private static final String KEY_RESULT = "result";
    private static final String KEY_THREAD = "thread";

    private TextView mResultTextView;

    private int mFirstNumber;
    private int mSecondNumber;
    private String mResult;
    private CalculatorThread mCalculatorThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        mResultTextView = (TextView) findViewById(R.id.result_text_view);

        if (savedInstanceState != null){
            mResult = savedInstanceState.getString(KEY_RESULT);
            mCalculatorThread = (CalculatorThread)savedInstanceState.get(KEY_THREAD);
            if (!mCalculatorThread.isRun()){
                mResultTextView.setText(mResult);
                Log.i(TAG, "Result save");
            } else {
                mCalculatorThread.mHandler = mHandler;
                Log.i(TAG, "Thread save");
            }
        } else {
            Intent packageIntent = getIntent();
            mFirstNumber = packageIntent.getIntExtra(EXTRA_FIRST_NUMBER, 0);
            mSecondNumber = packageIntent.getIntExtra(EXTRA_SECOND_NUMBER, 0);
            mCalculatorThread = new CalculatorThread(mHandler);
            mCalculatorThread.execute(new Runnable() {
                @Override
                public void run() {
                    try{
                        Thread.sleep(5000);
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    String result = ((Integer)(mFirstNumber + mSecondNumber)).toString();
                    Message message = Message.obtain();
                    message.obj = result;
                    mCalculatorThread.mHandler.sendMessage(message);
                }
            });
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_RESULT, mResult);
        outState.putSerializable(KEY_THREAD, mCalculatorThread);
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

    private final Handler mHandler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            mResult = (String)msg.obj;
            mResultTextView.setText((String)msg.obj);
        }
    };
}