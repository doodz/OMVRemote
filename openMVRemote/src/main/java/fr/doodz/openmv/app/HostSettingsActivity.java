package fr.doodz.openmv.app;

import android.os.Handler;
import android.preference.PreferenceActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import fr.doodz.openmv.UI.business.presentation.activity.ConfigurationManager;
import fr.doodz.openmv.app.controllers.SettingsController;


public class HostSettingsActivity extends PreferenceActivity {

    private ConfigurationManager mConfigurationManager;
    private SettingsController mSettingsController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("OMV Hosts");
        mSettingsController = new SettingsController(this, new Handler());
        mConfigurationManager = ConfigurationManager.getInstance(this);
        setPreferenceScreen(mSettingsController.createHostsPreferences(getPreferenceManager().createPreferenceScreen(this), this));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        mSettingsController.onCreateOptionsMenu(menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        mSettingsController.onMenuItemSelected(featureId, item);
        return super.onMenuItemSelected(featureId, item);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
