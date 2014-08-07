package fr.doodz.openmv.api.object.business;

import android.content.Context;

import java.util.ArrayList;

import fr.doodz.openmv.api.object.Service;
import fr.doodz.openmv.api.object.SysLog;
import fr.doodz.openmv.jsonrpc.client.InfoSystem;

/**
 * Created by doods on 29/07/14.
 */
public interface IDiagnosticManager extends IManager {

    public InfoSystem getSystemInfo(final Context context);
    public ArrayList<Service> getServicesStatus(final Context context);
    public ArrayList<SysLog> getLogsList(final Context context);
}
