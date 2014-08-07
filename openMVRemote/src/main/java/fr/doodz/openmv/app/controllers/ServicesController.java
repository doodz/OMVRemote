package fr.doodz.openmv.app.controllers;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SimpleAdapter;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

import fr.doodz.openmv.UI.business.ManagerFactory;
import fr.doodz.openmv.UI.business.presentation.INotifiableController;
import fr.doodz.openmv.api.object.Service;
import fr.doodz.openmv.api.object.SysLog;
import fr.doodz.openmv.api.object.business.IDiagnosticManager;
import fr.doodz.openmv.api.object.business.IInfoManager;
import fr.doodz.openmv.app.Adapters.ServiceAdapter;
import fr.doodz.openmv.app.R;
import fr.doodz.openmv.jsonrpc.client.InfoSystem;

/**
 * Created by doods on 27/07/14.
 */
public class ServicesController  extends AbstractController  implements INotifiableController {

    private IDiagnosticManager mDiagnosticManager;
    private Activity mActivity;

    public ServicesController(Activity activity, Handler handler) {
        super.onCreate(activity, handler);
        mDiagnosticManager = ManagerFactory.getDiagnosticManager(this);
        this.mActivity = activity;
    }



    public void  getServicesStatus(final ListView listView)
    {
        ArrayList<Service> var = mDiagnosticManager.getServicesStatus(mActivity);

        final ListAdapter adpt = new ServiceAdapter(mActivity,var);
        listView.setAdapter(adpt);

    }

    public void getLogsList(final ListView listView)
    {
        ArrayList<SysLog> var = mDiagnosticManager.getLogsList(mActivity);


    }
}

