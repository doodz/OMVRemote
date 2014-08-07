package fr.doodz.openmv.UI.business;

import android.os.Handler;
import android.os.Looper;

import fr.doodz.openmv.UI.business.presentation.INotifiableController;

/**
 * Created by doods on 21/05/14.
 */
public class ManagerThread extends Thread {

    private final InfoManager mInfoManager;
    private final DiagnosticManager mDiagnosticManager;
    private static ManagerThread sManagerThread;
    private Handler mHandler;

    private ManagerThread() {
        super("ManagerThread");
        mInfoManager = new InfoManager();
        mDiagnosticManager = new DiagnosticManager();
    }


    public static ManagerThread get() {
        if (sManagerThread == null) {
            sManagerThread = new ManagerThread();
            sManagerThread.start();
            // thread must be entirely started
            while (sManagerThread.mHandler == null) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return sManagerThread;
    }

    public static InfoManager info(INotifiableController controller) {
        final InfoManager im = get().mInfoManager;
        im.setController(controller);
        return im;
    }

    public static DiagnosticManager  diagnostic(INotifiableController controller) {
        final DiagnosticManager dm = get().mDiagnosticManager;
        dm.setController(controller);
        return dm;
    }
    public void run() {
        Looper.prepare();
        mHandler = new Handler();
        mInfoManager.setHandler(mHandler);
        mDiagnosticManager.setHandler(mHandler);
        Looper.loop();
    }
}
