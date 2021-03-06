package fr.doodz.openmv.api.api.business;

import android.content.Context;

import java.util.ArrayList;

import fr.doodz.openmv.GeneralSettings;
import fr.doodz.openmv.TimeSettings;
import fr.doodz.openmv.api.api.object.Output;
import fr.doodz.openmv.api.api.object.Plugin;
import fr.doodz.openmv.api.api.object.UpdatesSettings;
import fr.doodz.openmv.api.api.object.Upgraded;
import fr.doodz.openmv.api.api.object.WebGuiSetting;
import fr.doodz.openmv.api.api.types.Sortdir;
import fr.doodz.openmv.api.api.types.Sortfield;

/**
 * Created by doods on 09/08/14.
 */
public interface ISystemManager {

    void getSettings(final DataResponse<WebGuiSetting> response, final Context context);

    void setSettings(final DataResponse<String> response, final Context context, final WebGuiSetting Setting);

    void getTimeSettings(final DataResponse<TimeSettings> response, final Context context);

    void setDate(final DataResponse<String> response, final Context context, final int timestamp);

    void getGeneralSettings(final DataResponse<GeneralSettings> response, final Context context);

    void setGeneralSettings(final DataResponse<String> response, final Context context, final GeneralSettings settings);

    void  getListPlugin(final DataResponse<ArrayList<Plugin>> response, final Context context, final Sortdir sortdir,final Sortfield sortfield,final int start);

    void getUpgraded(final DataResponse<ArrayList<Upgraded>> response, final Context context, final Sortdir sortdir,final Sortfield sortfield,final int start);

    void upgrade(final DataResponse<String> response, final Context context, final ArrayList<Upgraded> upgrades);

    void update(final DataResponse<String> response, final Context context);

    void setUpdatesSettings(final DataResponse<String> response, final Context context, final UpdatesSettings settings);

    void getUpdatesSettings(final DataResponse<UpdatesSettings> response, final Context context);

    void getOutput(final DataResponse<Output> response, final Context context, final String fileName, final int pos);

    void reboot(final DataResponse<String> response, final Context context);

    void shutdown(final DataResponse<String> response, final Context context);
}
