package fr.doodz.openmv.app;

import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import fr.doodz.openmv.app.R;
import fr.doodz.openmv.app.controllers.HomeController;
import fr.doodz.openmv.app.controllers.InfoSystemController;
import fr.doodz.openmv.jsonrpc.client.InfoSystem;

public class InfoSystemActivity extends ActionBarActivity {

    private InfoSystemController mInfoSystemController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_system);

        mInfoSystemController = new InfoSystemController(this, new Handler());
        InfoSystem system = mInfoSystemController.getInfoSystem();

        //todo : move in controller
        final TextView hostname = (TextView)findViewById(R.id.Hostname);
        hostname.setText(system.Hostname);
        final TextView version = (TextView)findViewById(R.id.Version);
        version.setText(system.Version);
        final TextView processor = (TextView)findViewById(R.id.Processor);
        processor.setText(system.Processor);
        final TextView kernel = (TextView)findViewById(R.id.Kernel);
        kernel.setText(system.Kernel);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.info_system, menu);
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
