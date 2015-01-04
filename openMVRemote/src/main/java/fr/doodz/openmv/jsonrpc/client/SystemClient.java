package fr.doodz.openmv.jsonrpc.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.util.ArrayList;
import java.util.Iterator;

import fr.doodz.openmv.GeneralSettings;
import fr.doodz.openmv.TimeSettings;
import fr.doodz.openmv.api.api.object.Host;
import fr.doodz.openmv.api.api.object.Output;
import fr.doodz.openmv.api.api.object.Plugin;
import fr.doodz.openmv.api.api.object.Service;
import fr.doodz.openmv.api.api.object.UpdatesSettings;
import fr.doodz.openmv.api.api.object.Upgraded;
import fr.doodz.openmv.api.api.object.WebGuiSetting;
import fr.doodz.openmv.api.api.business.INotifiableManager;
import fr.doodz.openmv.api.api.data.ISystemClient;
import fr.doodz.openmv.api.api.types.Sortdir;
import fr.doodz.openmv.api.api.types.Sortfield;
import fr.doodz.openmv.jsonrpc.Connection;

/**
 * Created by doods on 07/08/14.
 */
public class SystemClient extends Client implements ISystemClient {

    public static final String TAG = "SystemClient";

    /**
     * Class constructor needs reference to HTTP client connection
     *
     * @param connection
     */
    public SystemClient(Connection connection) {
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

    public WebGuiSetting getSettings(INotifiableManager manager) {
        JsonNode result = mConnection.getJson(manager, "getSettings", "WebGui", null);

        WebGuiSetting setting = new WebGuiSetting(getBool(result, "dnssdenable"),
                getString2(result, "dnssdname"),
                getBool(result, "enablessl"),
                getBool(result, "forcesslonly"),
                getInt(result, "port"),
                getString2(result, "sslcertificateref"),
                getInt(result, "tsslport"),
                getInt(result, "ttimeout"));

        return setting;
    }

    public void setSettings(INotifiableManager manager, WebGuiSetting setting) {
        JsonNode result = mConnection.getJson(manager, "getSettings", "WebGui", obj().p("enablessl", setting.Enablessl).p("forcesslonly", setting.Forcesslonly)
                .p("port", setting.Port).p("sslcertificateref", setting.Sslcertificateref).p("sslport", setting.Sslport).p("timeout", setting.Timeout));

    }

    public TimeSettings getTimeSettings(INotifiableManager manager) {
        JsonNode result = mConnection.getJson(manager, "getTimeSettings", "System", null);
        TimeSettings time = new TimeSettings(getString2(result, "Local"), getString2(result, "ISO8601"), getString2(result, "Timezone"),
                getBool(result, "forcesslonly"), getString2(result, "Ntptimeservers"));

        return time;
    }

    public void setTimeSettings(INotifiableManager manager, TimeSettings time) {
        JsonNode result = mConnection.getJson(manager, "setTimeSettings", "System", obj().p("ntpenable", time.Ntpenable)
                .p("ntptimeservers", time.Ntptimeservers).p("timezone", time.Timezone));
    }

    public void setDate(INotifiableManager manager, int timestamp) {
        JsonNode result = mConnection.getJson(manager, "setDate", "System", obj().p("timestamp", timestamp));

    }

    public GeneralSettings getGeneralSettings(INotifiableManager manager) {
        JsonNode result = mConnection.getJson(manager, "getGeneralSettings", "Network", null);

        GeneralSettings setting = new GeneralSettings(getString2(result, "Domainname"), getString2(result, "Hostname"));
        return setting;
    }

    public void setGeneralSettings(INotifiableManager manager, GeneralSettings settings) {
        JsonNode result = mConnection.getJson(manager, "setGeneralSettings", "Network", obj().p("domainname", settings.Domainname)
                .p("hostname", settings.Hostname));

    }

    public ArrayList<Plugin> getListPlugin(INotifiableManager manager, Sortdir sortdir, Sortfield sortfield, int start) {

        final ArrayList<Plugin> plugins = new ArrayList<Plugin>();


        JsonNode result = mConnection.getJson(manager, "getList", "Plugin",
                obj().p("limit", 25).p("sortdir", sortdir.toString()).p("sortfield", sortfield.toString()).p("start", start));
        result = result.get("data");
        if (result != null) {
            for (Iterator<JsonNode> i = result.elements(); i.hasNext(); ) {
                JsonNode jsonPugin = i.next();
                plugins.add(
                        new Plugin(getString2(jsonPugin, "architecture"), getString2(jsonPugin, "description"), getBool(jsonPugin, "_readOnly"), getString2(jsonPugin, "filename"),
                                getInt(jsonPugin, "installedsize"), getString2(jsonPugin, "longdescription"), getString2(jsonPugin, "maintainer"), getString2(jsonPugin, "md5sum"),
                                getString2(jsonPugin, "name"), getString2(jsonPugin, "depends"), getString2(jsonPugin, "homepage"), getString2(jsonPugin, "_package"),
                                getString2(jsonPugin, " priority"), getBool(jsonPugin, "installed"), getString2(jsonPugin, " section"), getString2(jsonPugin, "sha1"),
                                getString2(jsonPugin, "sha256"), getInt(jsonPugin, "size"), getString2(jsonPugin, "version"))
                );
            }
        }
        return plugins;
    }

    public ArrayList<Upgraded> getUpgraded(INotifiableManager manager,Sortdir sortdir, Sortfield sortfield, int start) {

        final ArrayList<Upgraded> upgradeds = new ArrayList<Upgraded>();

        JsonNode result = mConnection.getJson(manager, "getUpgradedList", "Apt",
                obj().p("limit", 25).p("sortdir", sortdir.toString()).p("sortfield", sortfield.toString()).p("start", start));
        result = result.get("data");
        if (result != null) {
            for (Iterator<JsonNode> i = result.elements(); i.hasNext(); ) {
                JsonNode jsonService = i.next();
                upgradeds.add(new Upgraded(
                                getString2(jsonService, "architecture"),
                                getString2(jsonService, "description"),
                                getString2(jsonService, "essential"),
                                getString2(jsonService, "filename"),
                                getInt(jsonService, "installedsize"),
                                getString2(jsonService, "longdescription"),
                                getString2(jsonService, "maintainer"),
                                getString2(jsonService, "md5sum"),
                                getString2(jsonService, "name"),
                                getString2(jsonService, "oldversion"),
                                getString2(jsonService, "packageName"),
                                getString2(jsonService, "predepends"),
                                getString2(jsonService, "priority"),
                                getString2(jsonService, "provides"),
                                getString2(jsonService, "replaces"),
                                getString2(jsonService, "repository"),
                                getString2(jsonService, "section"),
                                getString2(jsonService, "sha1"),
                                getString2(jsonService, "sha256"),
                                getInt(jsonService, "size"),
                                getString2(jsonService, "tag"),
                                getString2(jsonService, "version")
                        )
                );

            }
        }
        return upgradeds;
    }

    public String update(INotifiableManager manager) {

        JsonNode result = mConnection.getJson(manager, "update", "Apt", null);
        return getString2(result, "response");

    }

    public String upgrade(INotifiableManager manager, ArrayList<Upgraded> upgrades) {

        ArrayNode arr = arr();
        for (final Upgraded u : upgrades) {
            arr.add(u.Name);
        }

        JsonNode result = mConnection.getJson(manager, "upgrade", "Apt", obj().p("packages", arr));
        return result.asText();
        //return getString2(result, "response");
    }

    public UpdatesSettings getUpdatesSettings(INotifiableManager manager) {

        JsonNode result = mConnection.getJson(manager, "getSettings", "Apt", null);

        UpdatesSettings updatesSettings = new UpdatesSettings(getBool(result, "partner"), getBool(result, "proposed"));

        return updatesSettings;
    }

    public void setUpdatesSettings(INotifiableManager manager, UpdatesSettings updatesSettings) {
        JsonNode result = mConnection.getJson(manager, "setSettings", "Apt", obj()
                .p("partner", updatesSettings.Partner).p("proposed", updatesSettings.Proposed));

    }


    public Output getOutput(INotifiableManager manager, String fileName, int pos) {

        JsonNode result = mConnection.getJson(manager, "getOutput", "Exec", obj().p("filename", fileName).p("pos", pos));
        final JsonNode jsonRep = result.get("response");
        Output output = null;
        if (jsonRep != null) {
            output = new Output(getString2(jsonRep, "Filename"), getInt(jsonRep, "Pos"), getString2(jsonRep, "Output"), getBool(jsonRep, "Running"));
        }

        return output;
    }

    public void reboot(INotifiableManager manager) {
        JsonNode result = mConnection.getJson(manager, "reboot", "System", null);
    }

    public void shutdown(INotifiableManager manager) {
        JsonNode result = mConnection.getJson(manager, "shutdown", "System", null);
    }

    public ArrayList<Service> getServicesStatus(INotifiableManager manager) {

        final ArrayList<Service> services = new ArrayList<Service>();
        JsonNode result = mConnection.getJson(manager, "get", "Zeroconf", null);


        if (result != null) {
            for (Iterator<JsonNode> i = result.elements(); i.hasNext(); ) {
                JsonNode jsonService = (JsonNode) i.next();
                services.add(new Service(
                        getString2(jsonService, "name"),
                        getString2(jsonService, "title"),
                        getBool(jsonService, "enable"),
                        getBool(jsonService, "running")
                ));

            }
        }
        return services;
    }


}

