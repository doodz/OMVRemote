package fr.doodz.openmv.app.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;
import android.view.Display;

import fr.doodz.openmv.UI.business.presentation.activity.ConfigurationManager;
import fr.doodz.openmv.app.R;
import fr.doodz.openmv.app.controllers.SettingsController;

/**
 * A {@link PreferenceActivity} that presents a set of application settings. On
 * handset devices, settings are presented as a single list. On tablets,
 * settings are split by category, with category headers shown to the left of
 * the list of settings.
 * <p/>
 * See <a href="http://developer.android.com/design/patterns/settings.html">
 * Android Design: Settings</a> for design guidelines and the <a
 * href="http://developer.android.com/guide/topics/ui/settings.html">Settings
 * API Guide</a> for more information on developing a Settings UI.
 */
public class SettingsActivity extends PreferenceActivity {

    public final static String SUMMARY_VALUE_PLACEHOLDER = "%value%";
    public final static String JUMP_TO = "jump_to";
    public final static int JUMP_TO_INSTANCES = 1;
    private ConfigurationManager mConfigurationManager;
    private SettingsController mSettingsController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref_general);

        // set display size
        final Display display = getWindowManager().getDefaultDisplay();

        mSettingsController = new SettingsController(this, new Handler());
        mSettingsController.registerOnSharedPreferenceChangeListener(this);
        mConfigurationManager = ConfigurationManager.getInstance(this);
        final int jumpTo = getIntent().getIntExtra(JUMP_TO, 0);
        switch (jumpTo) {
            case JUMP_TO_INSTANCES:
                setPreferenceScreen((PreferenceScreen) getPreferenceScreen().findPreference("setting_instances"));
                break;
            default:
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSettingsController.onActivityResume(this);
        mSettingsController.updateSummaries();
        mConfigurationManager.onActivityResume(this);
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(mSettingsController);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Unregister the listener whenever a key changes
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(mSettingsController);
        mSettingsController.onActivityPause();
        mConfigurationManager.onActivityPause();
    }
}
