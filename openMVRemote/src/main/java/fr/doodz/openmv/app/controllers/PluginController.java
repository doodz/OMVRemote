package fr.doodz.openmv.app.controllers;

import android.app.Activity;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import fr.doodz.openmv.UI.business.ManagerFactory;
import fr.doodz.openmv.UI.business.presentation.INotifiableController;
import fr.doodz.openmv.api.object.Plugin;
import fr.doodz.openmv.api.object.business.DataResponse;
import fr.doodz.openmv.api.object.business.ISystemManager;
import fr.doodz.openmv.api.object.types.Sortdir;
import fr.doodz.openmv.api.object.types.Sortfield;
import fr.doodz.openmv.app.Adapters.PluginAdapter;
import fr.doodz.openmv.app.Adapters.UpgradeAdapter;

/**
 * Created by doods on 01/10/14.
 */
public class PluginController extends ActionModeController implements INotifiableController {

    private ISystemManager mSystemManager;

    private Activity activity;

    private Sortdir sortdir = Sortdir.DESC;
    private Sortfield sortfield = Sortfield.Name;
    private int start = 0;

    public PluginController(Activity activity, Handler handler) {
        super.onCreate(activity, handler);
        mSystemManager = ManagerFactory.getSystemManager(this);
        this.activity = activity;
        //this.getUpdatesSettings();
    }

    public void getListPlugin(final ListView listView) {
        DataResponse<ArrayList<Plugin>> handler = new DataResponse<ArrayList<Plugin>>() {
            public void run() {
                ArrayList<Plugin> var = value;
                adapter = new PluginAdapter(mActivity, var);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent,
                                            View view, int position, long id) {
                        onListItemSelect(position);
                    }
                });
            }
        };

        this.mSystemManager.getListPlugin(handler, mActivity.getApplicationContext(),sortdir,sortfield,start);
    }

    //a mutualiser


    private void onListItemSelect(int position) {
        adapter.toggleSelection(position);
        boolean hasCheckedItems = adapter.getSelectedCount() > 0;
        if (hasCheckedItems && mActionMode == null)
            mActionMode = activity.startActionMode(new ActionModeCallback());
            // there are some selected items, start the actionMode
        else if (!hasCheckedItems && mActionMode != null)
            // there no selected items, finish the actionMode
            mActionMode.finish();
        if (mActionMode != null)
            mActionMode.setTitle(String.valueOf(adapter.getSelectedCount()) + " selected");
    }
    // a mutualiser
}
