package fr.doodz.openmv.UI.business;

import android.content.Context;

import java.util.ArrayList;

import fr.doodz.openmv.api.object.Service;
import fr.doodz.openmv.api.object.SysLog;
import fr.doodz.openmv.api.object.business.IDiagnosticManager;
import fr.doodz.openmv.api.object.business.INotifiableManager;
import fr.doodz.openmv.api.object.types.LogType;
import fr.doodz.openmv.api.object.types.Sortdir;
import fr.doodz.openmv.api.object.types.Sortfield;
import fr.doodz.openmv.httpapi.WifiStateException;
import fr.doodz.openmv.jsonrpc.client.InfoSystem;

/**
 * Created by doods on 27/07/14.
 */
public class DiagnosticManager extends AbstractManager implements IDiagnosticManager, INotifiableManager {

    public InfoSystem getSystemInfo(final Context context) {

        try {
            return info(context).getFullSystemInfo(DiagnosticManager.this);
        } catch (WifiStateException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Service> getServicesStatus(final Context context) {

        try {
            return Diagnostic(context).getServicesStatus(DiagnosticManager.this);
        } catch (WifiStateException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<SysLog> getLogsList(final Context context) {

        try {
            return Diagnostic(context).getLogsList(DiagnosticManager.this, LogType.Syslog, Sortdir.DESC, Sortfield.Rownum, 0);
        } catch (WifiStateException e) {
            e.printStackTrace();
        }
        return null;
    }

}
