package fr.doodz.openmv.api.object.data;

import java.util.ArrayList;

import fr.doodz.openmv.GeneralSettings;
import fr.doodz.openmv.TimeSettings;
import fr.doodz.openmv.api.object.Output;
import fr.doodz.openmv.api.object.Upgraded;
import fr.doodz.openmv.api.object.WebGuiSetting;
import fr.doodz.openmv.api.object.business.INotifiableManager;

/**
 * Created by doods on 07/08/14.
 */
public interface ISystemClient {

    WebGuiSetting getSettings(INotifiableManager manager);
    void setSettings(INotifiableManager manager,WebGuiSetting setting);

    TimeSettings getTimeSettings(INotifiableManager manager);
    void setDate(INotifiableManager manager, int timestamp);

    GeneralSettings getGeneralSettings(INotifiableManager manager);
    void setGeneralSettings(INotifiableManager manager,GeneralSettings settings);
    ArrayList<Upgraded> getUpgraded(INotifiableManager manager);
    String upgrade(INotifiableManager manager,ArrayList<Upgraded> upgrades);
    Output getOutput(INotifiableManager manager,String fileName,int pos);
    void reboot(INotifiableManager manager);
    void shutdown(INotifiableManager manager);
}
