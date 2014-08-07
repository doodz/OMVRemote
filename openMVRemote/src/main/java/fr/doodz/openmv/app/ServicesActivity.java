package fr.doodz.openmv.app;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import fr.doodz.openmv.UI.business.presentation.activity.ConfigurationManager;
import fr.doodz.openmv.app.R;
import fr.doodz.openmv.app.controllers.ServicesController;
import fr.doodz.openmv.app.controllers.SettingsController;

public class ServicesActivity extends ActionBarActivity {

    private ConfigurationManager mConfigurationManager;
    private ServicesController mServicesController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);
        this.mConfigurationManager = ConfigurationManager.getInstance(this);
        final ListView listView = (ListView)findViewById(R.id.listView);
        this.mServicesController = new ServicesController(this,new Handler());

        this.mServicesController.getServicesStatus(listView);

        final Button status = (Button)findViewById(R.id.ButtonTst);
        status.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                mServicesController.getLogsList(listView);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.services, menu);
        return true;
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
