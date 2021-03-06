package fr.doodz.openmv.app.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import fr.doodz.openmv.app.R;
import fr.doodz.openmv.app.controllers.UpdateController;

public class UpdateActivity extends ActionBarActivity {

    private UpdateController mUpdateController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        //this.startActionMode(new ActionBarCallBack());
        mUpdateController = new UpdateController(this, new Handler());
        ListView list = (ListView) findViewById(R.id.apt_listView);
        mUpdateController.getUpgraded(list);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.update, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            this.mUpdateController.showSettingDialog();
            return true;
        } else if (id == R.id.item_check) {
            this.mUpdateController.update();
        }
        return super.onOptionsItemSelected(item);
    }
}
