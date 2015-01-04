package fr.doodz.openmv.api.api.data;

import java.util.ArrayList;

import fr.doodz.openmv.api.api.object.DoagnosticSetings;
import fr.doodz.openmv.api.api.object.Service;
import fr.doodz.openmv.api.api.object.SysLog;
import fr.doodz.openmv.api.api.business.INotifiableManager;
import fr.doodz.openmv.api.api.types.LogType;
import fr.doodz.openmv.api.api.types.Sortdir;
import fr.doodz.openmv.api.api.types.Sortfield;

/**
 * Created by doods on 27/07/14.
 */
public interface IDiagnosticClient extends IClient {

    ArrayList<Service> getServicesStatus(INotifiableManager manager);

    ArrayList<SysLog> getLogsList(INotifiableManager manager, LogType logType, Sortdir sortdir, Sortfield sortfield, int start);

    DoagnosticSetings getSettings(INotifiableManager manager);

    void setSettings(INotifiableManager manager, DoagnosticSetings setings);
}
