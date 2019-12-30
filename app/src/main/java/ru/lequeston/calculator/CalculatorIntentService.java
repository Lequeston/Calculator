package ru.lequeston.calculator;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import android.os.Handler;

public class CalculatorThread extends Thread implements Serializable {
    private static final String TAG = "CalculatorThread";

    private final AtomicBoolean isAlive = new AtomicBoolean(true);

    private final ConcurrentLinkedQueue<Runnable>mQueue = new ConcurrentLinkedQueue<>();
    public Handler mHandler;

    public CalculatorThread(Handler handler){
        super(TAG);
        mHandler = handler;
        start();
        Log.i(TAG, "Thread start");
    }

    @Override
    public void run() {
        while (isAlive.get()){
            Runnable task = mQueue.poll();
            if (task != null){
                task.run();
                quit();
            }
        }
    }

    public CalculatorThread execute(Runnable task){
        mQueue.add(task);
        return this;
    }

    public boolean isRun(){
        return isAlive.get();
    }

    public void quit(){
        isAlive.set(false);
        Log.i(TAG, "Thread stop");
    }
}
