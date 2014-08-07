package fr.doodz.openmv.app;

import android.content.Intent;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.os.Build;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import fr.doodz.openmv.UI.business.ManagerFactory;
import fr.doodz.openmv.UI.business.presentation.activity.ConfigurationManager;
import fr.doodz.openmv.app.controllers.HomeController;
import fr.doodz.openmv.utils.ClientFactory;


public class LoginActivity extends ActionBarActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();
    private HomeController mHomeController;
    private ConfigurationManager mConfigurationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (Build.VERSION.SDK_INT >= 9) {
            final StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        mHomeController = new HomeController(this, new Handler());
        mHomeController.setupVersionHandler(new Handler(),this.getApplicationContext());

        mConfigurationManager = ConfigurationManager.getInstance(this);

        final Button setings = (Button)findViewById(R.id.btn_settings);
        setings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, HostSettingsActivity.class));
            }
        });

        final Button connect = (Button)findViewById(R.id.btn_connect);
        connect.setOnClickListener(new View.OnClickListener(){
             public void onClick(View v){
                mHomeController.ReconnectHost();
             }
             });

        final Button info = (Button)findViewById(R.id.btn_info);
        info.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(LoginActivity.this, InfoSystemActivity.class));
            }
        });

        final Button status = (Button)findViewById(R.id.btn_Status);
        status.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(LoginActivity.this, ServicesActivity.class));
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
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
