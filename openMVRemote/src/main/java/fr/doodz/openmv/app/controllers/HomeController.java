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
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import fr.doodz.openmv.UI.business.ManagerFactory;
import fr.doodz.openmv.UI.business.presentation.INotifiableController;
import fr.doodz.openmv.api.object.Host;
import fr.doodz.openmv.api.object.business.DataResponse;
import fr.doodz.openmv.api.object.business.IInfoManager;
import fr.doodz.openmv.utils.ClientFactory;
import fr.doodz.openmv.utils.HostFactory;

/**
 * Created by doods on 18/05/14.
 */
public class HomeController extends AbstractController  implements INotifiableController {

    private DataResponse<String> mUpdateVersionHandler;
    private IInfoManager mInfoManager;

    public HomeController(Activity activity, Handler handler) {
        super.onCreate(activity, handler);
        mInfoManager = ManagerFactory.getInfoManager(this);

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
}
