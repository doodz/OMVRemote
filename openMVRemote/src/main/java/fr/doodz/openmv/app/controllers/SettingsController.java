package fr.doodz.openmv.app.controllers;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Hashtable;

import fr.doodz.openmv.UI.business.presentation.INotifiableController;
import fr.doodz.openmv.api.api.object.Host;
import fr.doodz.openmv.app.Activity.SettingsActivity;
import fr.doodz.openmv.app.R;
import fr.doodz.openmv.utils.HostFactory;

/**
 * Created by doods on 24/05/14.
 */
public class SettingsController extends AbstractController implements INotifiableController, IController, SharedPreferences.OnSharedPreferenceChangeListener {

    public static final int MENU_ADD_HOST = 1;
    public static final int MENU_ADD_HOST_WIZARD = 3;
    private static final String TAG = "SettingsController";
    private final Hashtable<String, String> mSummaries = new Hashtable<String, String>();
    private PreferenceActivity mPreferenceActivity;

    public SettingsController(PreferenceActivity activity, Handler handler) {
        mPreferenceActivity = activity;
        super.onCreate(activity, handler);
    }

    /**
     * Used in order to replace the %value% placeholders with real values.
     */
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Log.i("SettingsActivity", "onSharedPreferenceChanged(" + key + ")");
        Preference pref = mPreferenceActivity.getPreferenceScreen().findPreference(key);
        String origSummary = mSummaries.get(key);
        if (origSummary != null && origSummary.contains(SettingsActivity.SUMMARY_VALUE_PLACEHOLDER)) {
            pref.setSummary(origSummary.replaceAll(SettingsActivity.SUMMARY_VALUE_PLACEHOLDER, sharedPreferences.getString(key, "")));
        }
    }

    /**
     * Used in SettingsActivity in order to update the %value% placeholder in
     * the summaries.
     *
     * @param activity Reference to activity
     */
    public void registerOnSharedPreferenceChangeListener(PreferenceActivity activity) {
        activity.getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
        PreferenceScreen ps = activity.getPreferenceScreen();
        // save original summaries to variable for later update
        mSummaries.clear();
        for (String key : ps.getSharedPreferences().getAll().keySet()) {
            Preference pref = ps.findPreference(key);
            if (pref != null && pref.getSummary() != null) {
                mSummaries.put(key, pref.getSummary().toString());
            }
        }
        updateSummaries();
    }


    /**
     * Updates summaries of all known keys with the updated value.
     */
    public void updateSummaries() {
        PreferenceScreen ps = mPreferenceActivity.getPreferenceScreen();
        for (String key : ps.getSharedPreferences().getAll().keySet()) {
            Preference pref = ps.findPreference(key);
            if (pref != null && pref.getSummary() != null) {
                String summary = pref.getSummary().toString();
                if (summary.contains(SettingsActivity.SUMMARY_VALUE_PLACEHOLDER)) {
                    pref.setSummary(summary.replaceAll(SettingsActivity.SUMMARY_VALUE_PLACEHOLDER, ps.getSharedPreferences().getString(key, "<not set>")));
                }
            }
        }
    }

    /**
     * Creates the preference screen that contains all the listed hosts.
     *
     * @param root     Root node
     * @param activity Reference to activity
     * @return
     */
    public PreferenceScreen createHostsPreferences(PreferenceScreen root, Activity activity) {
        final ArrayList<Host> hosts = HostFactory.getHosts(activity.getApplicationContext());
        if (hosts.size() > 0) {
            for (Host host : hosts) {
                HostPreference pref = new HostPreference(activity);
                pref.setTitle(host.name);
                pref.setSummary(host.getSummary());
                pref.setHost(host);
                pref.setKey(HostPreference.ID_PREFIX + host.id);
                root.addPreference(pref);
            }
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            builder.setMessage(activity.getResources().getString(R.string.setting_no_hosts));
            builder.setPositiveButton("Close", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            builder.create().show();
        }
        return root;
    }

    public void onCreateOptionsMenu(Menu menu) {
        menu.addSubMenu(0, MENU_ADD_HOST, 0, "Add Host").setIcon(R.drawable.ic_add);
    }

    public void onMenuItemSelected(int featureId, MenuItem item) {
        switch (item.getItemId()) {
            case MENU_ADD_HOST:
                HostPreference pref = new HostPreference(mActivity);
                pref.setTitle("New OMV Host");
                pref.create(mPreferenceActivity.getPreferenceManager());
                mPreferenceActivity.getPreferenceScreen().addPreference(pref);
                break;
        }
    }
}
