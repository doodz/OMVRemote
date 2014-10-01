package fr.doodz.openmv.UI.business;

import android.util.Log;

import fr.doodz.openmv.api.object.business.DataResponse;
import fr.doodz.openmv.api.object.business.INotifiableManager;
import fr.doodz.openmv.httpapi.WifiStateException;

/**
 * Created by doods on 18/05/14.
 */
public abstract class Command<T> implements Runnable {

    public static final int MAX_RETRY = 5;
    public final INotifiableManager mManager;
    public final DataResponse<T> mResponse;
    // TODO Disable this when not needed anymore
    public final StackTraceElement mCaller;
    public int mRetryCount = 0;
    public long mStarted = 0;

    public Command(DataResponse<T> response, INotifiableManager manager) {
        mManager = manager;
        mResponse = response;
        mStarted = System.currentTimeMillis();
        mCaller = new Throwable().fillInStackTrace().getStackTrace()[2];
    }

    public void run() {
        try {
            mRetryCount++;
            Log.d("Command", "Running command counter: " + mRetryCount);
            if (mRetryCount > MAX_RETRY) return;
            doRun();
            Log.i(mCaller.getClassName(), "*** " + mCaller.getMethodName() + ": " + (System.currentTimeMillis() - mStarted) + "ms");

            mManager.onFinish(mResponse);
        } catch (WifiStateException e) {
            mManager.onWrongConnectionState(e.getState(), this);
        } catch (Exception e) {
            mManager.onError(e);
        }
    }

    public abstract void doRun() throws Exception;

}
