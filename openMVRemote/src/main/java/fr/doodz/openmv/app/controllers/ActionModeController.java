package fr.doodz.openmv.app.controllers;

import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import fr.doodz.openmv.api.api.object.Upgraded;
import fr.doodz.openmv.app.Adapters.PluginAdapter;
import fr.doodz.openmv.app.R;

/**
 * Created by doods on 01/10/14.
 */
public class ActionModeController extends AbstractController {

    protected ActionMode mActionMode;

    protected PluginAdapter adapter;

    protected class ActionModeCallback implements ActionMode.Callback {

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

        protected void upgrade(ArrayList<Upgraded> upgrades) {

        }

    }
}
