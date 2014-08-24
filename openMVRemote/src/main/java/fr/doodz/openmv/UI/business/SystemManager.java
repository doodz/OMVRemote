package fr.doodz.openmv.UI.business;

import android.content.Context;

import java.util.ArrayList;

import fr.doodz.openmv.GeneralSettings;
import fr.doodz.openmv.TimeSettings;
import fr.doodz.openmv.api.object.Output;
import fr.doodz.openmv.api.object.UpdatesSettings;
import fr.doodz.openmv.api.object.Upgraded;
import fr.doodz.openmv.api.object.WebGuiSetting;
import fr.doodz.openmv.api.object.business.DataResponse;
import fr.doodz.openmv.api.object.business.IDiagnosticManager;
import fr.doodz.openmv.api.object.business.INotifiableManager;
import fr.doodz.openmv.api.object.business.ISystemManager;

/**
 * Created by doods on 09/08/14.
 */
public class SystemManager  extends AbstractManager implements ISystemManager, INotifiableManager {


    public void getSettings(final DataResponse<WebGuiSetting> response,final Context context){
        mHandler.post(new Command<WebGuiSetting>(response, this) {
            @Override
            public void doRun() throws Exception {
                response.value = System(context).getSettings(SystemManager.this);
            }
        });
    }

    public void setSettings(final DataResponse<String> response,final Context context,final WebGuiSetting Setting){
        mHandler.post(new Command<String>(response, this) {
            @Override
            public void doRun() throws Exception {
               System(context).setSettings(SystemManager.this, Setting);
            }
        });
    }

    public void getTimeSettings(final DataResponse<TimeSettings> response,final Context context){
        mHandler.post(new Command<TimeSettings>(response, this) {
            @Override
            public void doRun() throws Exception {
                response.value = System(context).getTimeSettings(SystemManager.this);
            }
        });
    }
    public void setDate(final DataResponse<String> response,final Context context,final int timestamp){
        mHandler.post(new Command<String>(response, this) {
            @Override
            public void doRun() throws Exception {
                System(context).setDate(SystemManager.this, timestamp);
            }
        });
    }

    public void getGeneralSettings(final DataResponse<GeneralSettings> response,final Context context){
        mHandler.post(new Command<GeneralSettings>(response, this) {
            @Override
            public void doRun() throws Exception {
                response.value = System(context).getGeneralSettings(SystemManager.this);
            }
        });
    }
    public void setGeneralSettings(final DataResponse<String> response,final Context context,final GeneralSettings settings){
        mHandler.post(new Command<String>(response, this) {
            @Override
            public void doRun() throws Exception {
                System(context).setGeneralSettings(SystemManager.this, settings);
            }
        });
    }

    public void setUpdatesSettings(final DataResponse<String> response,final Context context,final UpdatesSettings settings){
        mHandler.post(new Command<String>(response, this) {
            @Override
            public void doRun() throws Exception {
                System(context).setUpdatesSettings(SystemManager.this, settings);
            }
        });
    }
    public void getUpdatesSettings(final DataResponse<UpdatesSettings> response,final Context context){
        mHandler.post(new Command<UpdatesSettings>(response, this) {
            @Override
            public void doRun() throws Exception {
                response.value = System(context).getUpdatesSettings(SystemManager.this);
            }
        });
    }

    public void getUpgraded(final DataResponse<ArrayList<Upgraded>> response,final Context context){
        mHandler.post(new Command<ArrayList<Upgraded>>(response, this) {
            @Override
            public void doRun() throws Exception {
                response.value = System(context).getUpgraded(SystemManager.this);
            }
        });
    }

    public void upgrade(final DataResponse<String> response,final Context context,final ArrayList<Upgraded> upgrades){
        mHandler.post(new Command<String>(response, this) {
            @Override
            public void doRun() throws Exception {
                response.value = System(context).upgrade(SystemManager.this,upgrades);
            }
        });
    }

    public void update(final DataResponse<String> response,final Context context){
        mHandler.post(new Command<String>(response, this) {
            @Override
            public void doRun() throws Exception {
                response.value = System(context).update(SystemManager.this);
            }
        });

    }

    public void getOutput(final DataResponse<Output> response,final Context context,final String fileName,final int pos){
        mHandler.post(new Command<Output>(response, this) {
            @Override
            public void doRun() throws Exception {
                response.value = System(context).getOutput(SystemManager.this, fileName, pos);
            }
        });
    }

    public void reboot(final DataResponse<String> response,final Context context){
        mHandler.post(new Command<String>(response, this) {
            @Override
            public void doRun() throws Exception {
                System(context).reboot(SystemManager.this);
            }
        });
    }

    public void shutdown(final DataResponse<String> response,final Context context){
        mHandler.post(new Command<String>(response, this) {
            @Override
            public void doRun() throws Exception {
                System(context).shutdown(SystemManager.this);
            }
        });
    }
}
