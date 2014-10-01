package fr.doodz.openmv.UI.business;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import fr.doodz.openmv.UI.business.presentation.INotifiableController;
import fr.doodz.openmv.api.object.business.DataResponse;
import fr.doodz.openmv.api.object.business.INotifiableManager;
import fr.doodz.openmv.api.object.data.IDiagnosticClient;
import fr.doodz.openmv.api.object.data.IInfoClient;
import fr.doodz.openmv.api.object.data.ISystemClient;
import fr.doodz.openmv.httpapi.WifiStateException;
import fr.doodz.openmv.utils.ClientFactory;

/**
 * Created by doods on 21/05/14.
 */
public class AbstractManager implements INotifiableManager {

    protected static final String TAG = AbstractManager.class.getSimpleName();
    protected INotifiableController mController = null;
    protected Handler mHandler;
    protected List<Runnable> failedRequests = new ArrayList<Runnable>();

    public void setController(INotifiableController controller) {
        mController = controller;
    }

    /**
     * Sets the handler used in the looping thread
     *
     * @param handler
     */
    public void setHandler(Handler handler) {
        mHandler = handler;
    }

    /**
     * Returns the InfoClient class
     *
     * @param response Response object
     * @return
     * @throws WifiStateException
     */
    protected IInfoClient info(Context context) throws WifiStateException {
        return ClientFactory.getInfoClient(this, context);
    }


    protected IDiagnosticClient Diagnostic(Context context) throws WifiStateException {
        return ClientFactory.getDiagnosticClient(this, context);
    }

    protected ISystemClient System(Context context) throws WifiStateException {
        return ClientFactory.getSystemClient(this, context);
    }

    public void retryAll() {
        Log.d(TAG, "Posting retries to the queue");
        mHandler.post(new Runnable() {
            public void run() {
                Log.d(TAG, "runnable started, posting retries");
                while (failedRequests.size() > 0) {
                    if (mHandler.post(failedRequests.get(0)))
                        Log.d(TAG, "Runnable posted");
                    else
                        Log.d(TAG, "Runnable coudln't be posted");
                    failedRequests.remove(0);
                }
            }
        });
    }

    /**
     * Commands failed because of wrong connection state are special. After the connection has the right state we
     * could retry the command
     */
    public void onWrongConnectionState(int state, Command<?> cmd) {
        failedRequests.add(cmd);
        if (mController != null)
            mController.onWrongConnectionState(state, this, cmd);
    }

    public void onError(Exception e) {
        if (mController != null) {
            mController.onError(e);
        }
    }

    public void onMessage(String message) {
        if (mController != null) {
            mController.onMessage(message);
        }
    }

    /**
     * Calls the UI thread's callback code.
     *
     * @param response Response object
     */
    public void onFinish(DataResponse<?> response) {
        if (mController != null) {
            //Log.i(TAG, "*** posting onFinish through controller");
            mController.runOnUI(response);
        } else {
            Log.w(TAG, "*** ignoring onFinish, controller is null.");
            //mHandler.post(response);
        }
    }
}
