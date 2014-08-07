package fr.doodz.openmv.api.object.data;

import fr.doodz.openmv.GeneralSettings;
import fr.doodz.openmv.TimeSettings;
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

}
