package fr.doodz.openmv.app.controllers;

import android.app.Activity;
import android.os.Handler;
import android.widget.GridView;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.net.wifi.WifiManager.WifiLock;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Looper;
import android.os.Message;
import android.os.PowerManager;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import java.util.ArrayList;
import java.util.HashMap;

import fr.doodz.openmv.UI.business.ManagerFactory;
import fr.doodz.openmv.UI.business.presentation.INotifiableController;
import fr.doodz.openmv.api.object.Host;
import fr.doodz.openmv.api.object.Service;
import fr.doodz.openmv.api.object.business.DataResponse;
import fr.doodz.openmv.api.object.business.IDiagnosticManager;
import fr.doodz.openmv.api.object.business.IInfoManager;
import fr.doodz.openmv.api.object.business.ISystemManager;
import fr.doodz.openmv.app.Adapters.ServiceAdapter;
import fr.doodz.openmv.app.HostSettingsActivity;
import fr.doodz.openmv.app.R;
import fr.doodz.openmv.jsonrpc.client.InfoSystem;
import fr.doodz.openmv.utils.ClientFactory;
import fr.doodz.openmv.utils.HostFactory;

/**
 * Created by doods on 18/05/14.
 */
public class HomeController extends AbstractController  implements INotifiableController {

    private DataResponse<String> mUpdateVersionHandler;
    private IInfoManager mInfoManager;
    private IDiagnosticManager mDiagnosticManager;
    private ISystemManager mSystemManager;
    private Activity activity;

    public HomeController(Activity activity, Handler handler) {
        super.onCreate(activity, handler);
        mInfoManager = ManagerFactory.getInfoManager(this);
        mDiagnosticManager = ManagerFactory.getDiagnosticManager(this);
        mSystemManager = ManagerFactory.getSystemManager(this);
        this.activity = activity;
//		BroadcastListener bcl = BroadcastListener.getInstance(ConnectionManager.getHttpClient(this));
//		bcl.addObserver(this);
    }

    public void ReconnectHost()
    {
        final Host host = HostFactory.host;
        ClientFactory.resetClient(host);
        mInfoManager.getSystemInfo(mUpdateVersionHandler, 0, mActivity.getApplicationContext());

    }

    public void setupVersionHandler(final Handler handler, final android.content.Context context) {
        mUpdateVersionHandler = new DataResponse<String>() {
            public void run() {

                Toast toast = Toast.makeText(context,"Connected to : "+ value,  Toast.LENGTH_LONG);
                toast.show();
            }
        };
    }

    public void getUpTime(TextView upTime){
        InfoSystem info = this.getInfoSystem();

        upTime.setText(info.Uptime);
    }

    public void shutdown(){
        DataResponse<String> handler = new DataResponse<String>() {
            public void run() {

            }
        };
        this.mSystemManager.shutdown(handler,mActivity.getApplicationContext());
    }

    public void reboot(){
        DataResponse<String> handler = new DataResponse<String>() {
            public void run() {

            }
        };
        this.mSystemManager.reboot(handler,mActivity.getApplicationContext());
    }

    public void  getServicesStatus(final ListView listView){
        ArrayList<Service> var = mDiagnosticManager.getServicesStatus(mActivity);

        final ListAdapter adpt = new ServiceAdapter(mActivity,var);
        listView.setAdapter(adpt);
    }

    public InfoSystem getInfoSystem()
    {
        return mInfoManager.getSystemInfo(mActivity);
    }

    /**
     * Opens the host changer popup.
     */
    public void openHostChanger() {

        // granted, this is butt-ugly. better ideas, be my guest.
        final ArrayList<Host> hosts = HostFactory.getHosts(mActivity.getApplicationContext());
        final HashMap<Integer, Host> hostMap = new HashMap<Integer, Host>();
        final CharSequence[] names = new CharSequence[hosts.size()];
        int i = 0;
        for (Host host : hosts) {
            names[i] = host.name;
            hostMap.put(i, host);
            i++;
        }
        if (hosts.size() > 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
            builder.setTitle(activity.getResources().getString(R.string.dialog_pick));
            builder.setItems(names, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    final Host host = hostMap.get(which);
                    if (HostFactory.host != null && HostFactory.host.id == host.id) {
                        Toast.makeText(mActivity.getApplicationContext(),activity.getResources().getString(R.string.pick_same), Toast.LENGTH_SHORT).show();
                    } else {
                        Log.i(TAG, "Switching host to " + (host == null ? "<null>" : host.addr) + ".");
                        HostFactory.saveHost(mActivity.getApplicationContext(), host);
                        String msg = activity.getResources().getString(R.string.dialog_delete_host);
                        msg = String.format(msg, host.toString());
                        Toast.makeText(mActivity.getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                        ClientFactory.resetClient(host);
                        mInfoManager.getSystemInfo(mUpdateVersionHandler, 0, mActivity.getApplicationContext());
                    }
                }
            });
            AlertDialog dialog = builder.create();
            dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND, WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
            dialog.show();
        } else {
            Toast.makeText(mActivity.getApplicationContext(),activity.getResources().getString(R.string.no_hosts), Toast.LENGTH_LONG).show();
            Intent intent = new Intent(mActivity, HostSettingsActivity.class);
            mActivity.startActivity(intent);
        }
    }
}
