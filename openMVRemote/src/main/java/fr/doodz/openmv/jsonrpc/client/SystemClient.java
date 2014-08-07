package fr.doodz.openmv.jsonrpc.client;

import com.fasterxml.jackson.databind.JsonNode;

import fr.doodz.openmv.GeneralSettings;
import fr.doodz.openmv.TimeSettings;
import fr.doodz.openmv.api.object.Host;
import fr.doodz.openmv.api.object.WebGuiSetting;
import fr.doodz.openmv.api.object.business.INotifiableManager;
import fr.doodz.openmv.api.object.data.ISystemClient;
import fr.doodz.openmv.jsonrpc.Connection;

/**
 * Created by doods on 07/08/14.
 */
public class SystemClient extends Client implements ISystemClient {

    public static final String TAG = "SystemClient";
    /**
     * Class constructor needs reference to HTTP client connection
     * @param connection
     */
    public SystemClient(Connection connection) {
        super(connection);
    }

    /**
     * Updates host info on the connection.
     * @param host
     */
    public void setHost(Host host) {
        mConnection.setHost(host);
    }

    public WebGuiSetting getSettings(INotifiableManager manager){
        JsonNode result = mConnection.getJson(manager, "getSettings", "WebGui",null);

        WebGuiSetting setting = new WebGuiSetting(getBool(result,"dnssdenable"),
                getString2(result,"dnssdname"),
                getBool(result,"enablessl"),
                getBool(result,"forcesslonly"),
                getInt(result,"port"),
                getString2(result,"sslcertificateref"),
                getInt(result,"tsslport"),
                getInt(result,"ttimeout"));

                return setting;
    }

    public void setSettings(INotifiableManager manager,WebGuiSetting setting){
        JsonNode result = mConnection.getJson(manager, "getSettings", "WebGui",obj().p("enablessl", setting.Enablessl).p("forcesslonly", setting.Forcesslonly)
                .p("port", setting.Port) .p("sslcertificateref", setting.Sslcertificateref) .p("sslport", setting.Sslport) .p("timeout", setting.Timeout));

    }

    public TimeSettings getTimeSettings(INotifiableManager manager){
        JsonNode result = mConnection.getJson(manager, "getTimeSettings", "System",null);
        TimeSettings time = new TimeSettings(getString2(result, "Local"),getString2(result,"ISO8601"),getString2(result, "Timezone"),
        getBool(result,"forcesslonly"),getString2(result, "Ntptimeservers"));

        return time;
    }

    public void setTimeSettings(INotifiableManager manager,TimeSettings time){
        JsonNode result = mConnection.getJson(manager, "setTimeSettings", "System",obj().p("ntpenable", time.Ntpenable)
                .p("ntptimeservers", time.Ntptimeservers).p("timezone",time.Timezone));
    }
    public void setDate(INotifiableManager manager, int timestamp)
    {
        JsonNode result = mConnection.getJson(manager, "setDate", "System",obj().p("timestamp",timestamp));

    }

    public GeneralSettings getGeneralSettings(INotifiableManager manager){
        JsonNode result = mConnection.getJson(manager, "getGeneralSettings", "Network",null);

        GeneralSettings setting = new GeneralSettings(getString2(result,"Domainname"),getString2(result,"Hostname"));
        return setting;
    }

    public void setGeneralSettings(INotifiableManager manager,GeneralSettings settings){
        JsonNode result = mConnection.getJson(manager, "setGeneralSettings", "Network",obj().p("domainname", settings.Domainname)
                .p("hostname", settings.Hostname));

    }
}
