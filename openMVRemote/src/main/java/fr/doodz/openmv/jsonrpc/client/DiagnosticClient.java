package fr.doodz.openmv.jsonrpc.client;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.Iterator;

import fr.doodz.openmv.api.object.DoagnosticSetings;
import fr.doodz.openmv.api.object.Host;
import fr.doodz.openmv.api.object.Service;
import fr.doodz.openmv.api.object.SysLog;
import fr.doodz.openmv.api.object.business.INotifiableManager;
import fr.doodz.openmv.api.object.data.IDiagnosticClient;
import fr.doodz.openmv.api.object.types.LogType;
import fr.doodz.openmv.api.object.types.Sortdir;
import fr.doodz.openmv.api.object.types.Sortfield;
import fr.doodz.openmv.jsonrpc.Connection;

/**
 * Created by doods on 29/07/14.
 */
public class DiagnosticClient extends Client implements IDiagnosticClient {

    public static final String TAG = "DiagnosticClient";
    public static final int Limitlogs = 50;

    /**
     * Class constructor needs reference to HTTP client connection
     *
     * @param connection
     */
    public DiagnosticClient(Connection connection) {
        super(connection);
    }

    /**
     * Updates host info on the connection.
     *
     * @param host
     */
    public void setHost(Host host) {
        mConnection.setHost(host);
    }


    public InfoSystem getSystemInfo(INotifiableManager manager, int field) {
        JsonNode jSonSystemInfo = mConnection.getJson(manager, "getInformation", "System", null);
        return new InfoSystem(
                getString(jSonSystemInfo, "Hostname"),
                getString(jSonSystemInfo, "Version"),
                getString(jSonSystemInfo, "Processor"),
                getString(jSonSystemInfo, "Kernel"),
                getString(jSonSystemInfo, "Uptime"));
    }


    public ArrayList<Service> getServicesStatus(INotifiableManager manager) {

        final ArrayList<Service> services = new ArrayList<Service>();
        JsonNode result = mConnection.getJson(manager, "getStatus", "Services", null);

        final JsonNode jsonMovies = result.get("data");
        if (jsonMovies != null) {
            for (Iterator<JsonNode> i = jsonMovies.elements(); i.hasNext(); ) {
                JsonNode jsonService = (JsonNode) i.next();
                services.add(new Service(
                        getString2(jsonService, "name"),
                        getString2(jsonService, "title"),
                        getBool(jsonService, "enabled"),
                        getBool(jsonService, "running")
                ));

            }
        }
        return services;
    }

    public ArrayList<SysLog> getLogsList(INotifiableManager manager, LogType logType, Sortdir sortdir, Sortfield sortfield, int start) {

        final ArrayList<SysLog> sysLogs = new ArrayList<SysLog>();
        //id: "syslog", limit: 50, sortdir: "DESC", sortfield: "rownum",start: 0
        JsonNode result = mConnection.getJson(manager, "getList", "LogFile", obj().p("id", logType.toString()).p("limit", DiagnosticClient.Limitlogs)
                .p("sortdir", sortdir.toString()).p("sortfield", sortfield.toString()).p("start", start));

        final JsonNode jsonSysLog = result.get("data");
        if (jsonSysLog != null) {
            for (Iterator<JsonNode> i = jsonSysLog.elements(); i.hasNext(); ) {
                JsonNode jsonService = (JsonNode) i.next();
                sysLogs.add(new SysLog(
                        getString2(jsonService, "date"),
                        getString2(jsonService, "hostname"),
                        getString2(jsonService, "message"),
                        getInt(jsonService, "rownum"),
                        getInt(jsonService, "ts")
                ));

            }
        }
        return sysLogs;

    }

    public void clearLogFile(INotifiableManager manager, LogType logType) {
        JsonNode result = mConnection.getJson(manager, "clear", "LogFile", obj().p("id", logType.toString()));
    }

    public DoagnosticSetings getSettings(INotifiableManager manager) {
        DoagnosticSetings settings = null;
        JsonNode result = mConnection.getJson(manager, "getSettings", "Syslog", null);
        final JsonNode jsonSettings = result.get("data");
        if (jsonSettings != null) {
            settings = new DoagnosticSetings(
                    getBool(jsonSettings, "enable"),
                    getString2(jsonSettings, "host"),
                    getInt(jsonSettings, "port"),
                    getString2(jsonSettings, "protocol")

            );
        }
        return settings;
    }

    public void setSettings(INotifiableManager manager, DoagnosticSetings setings) {
        JsonNode result = mConnection.getJson(manager, "setSettings", "Syslog", obj().p("enable", setings.Enamble).p("host", setings.Host)
                .p("port", setings.Port).p("protocol", setings.Port));
    }
}
