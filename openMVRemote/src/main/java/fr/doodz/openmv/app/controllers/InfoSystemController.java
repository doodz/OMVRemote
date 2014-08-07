package fr.doodz.openmv.app.controllers;

import android.app.Activity;
import android.os.Handler;

import fr.doodz.openmv.UI.business.ManagerFactory;
import fr.doodz.openmv.UI.business.presentation.INotifiableController;
import fr.doodz.openmv.api.object.business.IInfoManager;
import fr.doodz.openmv.jsonrpc.client.InfoSystem;

/**
 * Created by doods on 25/07/14.
 */
public class InfoSystemController  extends AbstractController  implements INotifiableController {

    private IInfoManager mInfoManager;
    private Activity mActivity;
    public InfoSystemController(Activity activity, Handler handler) {
        super.onCreate(activity, handler);
        mInfoManager = ManagerFactory.getInfoManager(this);
        this.mActivity = activity;


    }


    public InfoSystem getInfoSystem()
    {
        return mInfoManager.getSystemInfo(mActivity);
    }
}
