package fr.doodz.openmv.app;

import android.content.Intent;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.os.Build;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.ListView;
import android.widget.TextView;


import fr.doodz.openmv.UI.business.presentation.activity.ConfigurationManager;
import fr.doodz.openmv.app.controllers.HomeController;



public class LoginActivity extends ActionBarActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();
    private static final int MENU_SWITCH_OMV = 1;
    private static final int MENU_SETTINGS = 2;
    private static final int MENU_REBOOT = 3;
    private static final int MENU_POWEROFF = 4;
    private HomeController mHomeController;
    private ConfigurationManager mConfigurationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (Build.VERSION.SDK_INT >= 9) {
            final StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        mHomeController = new HomeController(this, new Handler());
        mHomeController.setupVersionHandler(new Handler(),this.getApplicationContext());

        mConfigurationManager = ConfigurationManager.getInstance(this);

        final TextView upTime = (TextView)findViewById(R.id.Main_UpTime);
        final ListView services =  (ListView)findViewById(R.id.Main_listView);

        mHomeController.getServicesStatus(services);
        mHomeController.getUpTime(upTime);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.login, menu);
        menu.add(0, MENU_SWITCH_OMV, 0, "Switch OMV").setIcon(R.drawable.ic_switch_omv_dark);
        menu.add(0, MENU_SETTINGS, 0, "Settings");
        menu.add(0, MENU_REBOOT, 0, "Reboot");
        menu.add(0, MENU_POWEROFF, 0, "PowerOff");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case MENU_SWITCH_OMV:
                mHomeController.openHostChanger();
                return true;
            case MENU_SETTINGS:
                startActivity(new Intent(this, SettingsActivity.class));
                return true;
            case MENU_REBOOT:
                mHomeController.reboot();
                return true;
            case MENU_POWEROFF:
                mHomeController.shutdown();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
