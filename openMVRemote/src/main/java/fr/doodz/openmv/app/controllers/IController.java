package fr.doodz.openmv.app.controllers;

import android.app.Activity;

/**
 * Created by doods on 24/05/14.
 */
public interface IController {
    public void onActivityPause();
    public void onActivityResume(Activity activity);
}
