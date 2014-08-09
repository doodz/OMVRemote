package fr.doodz.openmv.app.controllers;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import fr.doodz.openmv.UI.business.ManagerFactory;
import fr.doodz.openmv.UI.business.presentation.INotifiableController;
import fr.doodz.openmv.api.object.Output;
import fr.doodz.openmv.api.object.Upgraded;
import fr.doodz.openmv.api.object.business.DataResponse;
import fr.doodz.openmv.api.object.business.ISystemManager;
import fr.doodz.openmv.app.Adapters.ServiceAdapter;
import fr.doodz.openmv.app.Adapters.UpgradeAdapter;
import fr.doodz.openmv.app.R;

/**
 * Created by doods on 09/08/14.
 */
public class UpdateController extends AbstractController  implements INotifiableController {

    private ISystemManager mSystemManager;
    private UpgradeAdapter adapter;
    private Activity activity;
    private ActionMode mActionMode;

    public UpdateController(Activity activity, Handler handler) {
        super.onCreate(activity, handler);
        mSystemManager = ManagerFactory.getSystemManager(this);
        this.activity = activity;
    }

    public void getUpgraded(final ListView listView)
    {
        DataResponse<ArrayList<Upgraded>> handler = new DataResponse<ArrayList<Upgraded>>() {
            public void run() {
                ArrayList<Upgraded> var = value;
                adapter = new UpgradeAdapter(mActivity,var);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent,
                                            View view, int position, long id) {
                        onListItemSelect(position);
                    }
                });
            }
        };

        this.mSystemManager.getUpgraded(handler, mActivity.getApplicationContext());
    }

    private void upgrade(ArrayList<Upgraded> upgrades){
        DataResponse<String> handler = new DataResponse<String>() {
            public void run() {
                String var = value;
                getOutput(var,0);
            }
        };

        this.mSystemManager.upgrade(handler, mActivity.getApplicationContext(), upgrades);
    }

    private void getOutput(String fileName, int pos){

        DataResponse<Output> handler = new DataResponse<Output>() {
            public void run() {
                Output var = value;
                if(value != null && value.Running) {
                    getOutput(value.Filename, value.Pos);
                }
            }
        };

        this.mSystemManager.getOutput(handler, mActivity.getApplicationContext(), fileName,pos);
    }

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

    private class ActionModeCallback implements ActionMode.Callback {

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            // inflate contextual menu
            mode.getMenuInflater().inflate(R.menu.contextual_menu, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {

            switch (item.getItemId()) {
                case R.id.item_upgrade:
                    // retrieve selected items
                    SparseBooleanArray selected = adapter.getSelectedIds();
                    ArrayList<Upgraded> upgrades = new ArrayList<Upgraded>();
                    for (int i = (selected.size() - 1); i >= 0; i--) {
                        if (selected.valueAt(i)) {

                            Upgraded selectedItem = (Upgraded)adapter.getItem(selected.keyAt(i));
                            upgrades.add(selectedItem);
                            adapter.remove(selectedItem);

                        }
                    }
                    upgrade(upgrades);
                    mode.finish(); // Action picked, so close the CAB
                    return true;
                case R.id.item_select_all:
                    adapter.selectAll();
                    return true;
                default:
                    return false;
            }

        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            // remove selection
            adapter.removeSelection();
            mActionMode = null;
        }
    }
}
