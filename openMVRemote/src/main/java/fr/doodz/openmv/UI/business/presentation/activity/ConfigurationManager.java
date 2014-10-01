package fr.doodz.openmv.UI.business.presentation.activity;

import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.preference.PreferenceManager;

/**
 * Created by doods on 22/05/14.
 */
public class ConfigurationManager implements SharedPreferences.OnSharedPreferenceChangeListener {


    public final static String PREF_KEYGUARD_DISABLED = "setting_disable_keyguard";

    public final static String KEYGUARD_STATUS_ENABLED = "0";
    public final static String KEYGUARD_STATUS_REMOTE_ONLY = "1";
    public final static String KEYGUARD_STATUS_ALL = "2";

    public final static int INT_KEYGUARD_STATUS_ENABLED = 0;
    public final static int INT_KEYGUARD_STATUS_REMOTE_ONLY = 1;
    public final static int INT_KEYGUARD_STATUS_ALL = 2;

    public final static String KEYGUARD_TAG = "OMV_remote_keyguard_lock";

    private static ConfigurationManager sInstance;

    private Activity mActivity;

    private int mKeyguardState = 0;

    private KeyguardManager.KeyguardLock mKeyguardLock = null;

    private ConfigurationManager(Activity activity) {
        mActivity = activity;
        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mActivity);
        prefs.registerOnSharedPreferenceChangeListener(this);
        mKeyguardState = Integer.parseInt(prefs.getString(PREF_KEYGUARD_DISABLED, KEYGUARD_STATUS_ENABLED));
    }

    public static ConfigurationManager getInstance(Activity activity) {
        if (sInstance == null) {
            sInstance = new ConfigurationManager(activity);
        } else {
            sInstance.mActivity = activity;
        }
        return sInstance;
    }

    //Use with extreme care! this could return null, so you need to null-check
    //in the calling code!
    public static ConfigurationManager getInstance() {
        return sInstance;
    }

    public Context getActiveContext() {
        return sInstance.mActivity;
    }

    public void disableKeyguard(Activity activity) {
        if (mKeyguardLock != null) {
            mKeyguardLock.disableKeyguard();
        } else {
            KeyguardManager keyguardManager = (KeyguardManager) activity.getSystemService(Activity.KEYGUARD_SERVICE);
            mKeyguardLock = keyguardManager.newKeyguardLock(KEYGUARD_TAG);
            mKeyguardLock.disableKeyguard();
        }
    }

    public void enableKeyguard() {
        if (mKeyguardLock != null) {
            mKeyguardLock.reenableKeyguard();
        }
        mKeyguardLock = null;
    }


    public void onSharedPreferenceChanged(SharedPreferences prefs, String key) {
        if (key.equals(PREF_KEYGUARD_DISABLED)) {
            mKeyguardState = Integer.parseInt(prefs.getString(PREF_KEYGUARD_DISABLED, KEYGUARD_STATUS_ENABLED));
            if (mKeyguardState == INT_KEYGUARD_STATUS_ALL)
                disableKeyguard(mActivity);
            else
                enableKeyguard();
        }
    }

    public void onActivityResume(Activity activity) {
        switch (mKeyguardState) {
            case INT_KEYGUARD_STATUS_REMOTE_ONLY:
                if (activity.getClass().equals(RemoteActivity.class))
                    disableKeyguard(activity);
                else
                    enableKeyguard();
                break;
            case INT_KEYGUARD_STATUS_ALL:
                disableKeyguard(activity);
                break;
            default:
                enableKeyguard();
                break;
        }

        activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        mActivity = activity;
    }

    public void onActivityPause() {
        if (mKeyguardLock != null) {
            mKeyguardLock.reenableKeyguard();
            mKeyguardLock = null;
        }
    }

}
