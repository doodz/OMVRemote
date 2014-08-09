package fr.doodz.openmv.jsonrpc.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.ArrayList;
import java.util.Iterator;

import fr.doodz.openmv.GeneralSettings;
import fr.doodz.openmv.TimeSettings;
import fr.doodz.openmv.api.object.Host;
import fr.doodz.openmv.api.object.Output;
import fr.doodz.openmv.api.object.Service;
import fr.doodz.openmv.api.object.Upgraded;
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

    public ArrayList<Upgraded> getUpgraded(INotifiableManager manager) {

        final ArrayList<Upgraded> upgradeds = new ArrayList<Upgraded>();

        JsonNode result = mConnection.getJson(manager, "getUpgraded", "Apt",null);
        final JsonNode jsonMovies = result.get("data");
        if (jsonMovies != null) {
            for (Iterator<JsonNode> i = jsonMovies.elements(); i.hasNext(); ) {
                JsonNode jsonService = (JsonNode)i.next();
                upgradeds.add( new Upgraded(
                                getString2(jsonService,"Architecture"),
                                getString2(jsonService,"Description"),
                                getString2(jsonService,"Essential"),
                                getString2(jsonService,"Filename"),
                                getString2(jsonService,"Installedsize"),
                                getString2(jsonService,"Longdescription"),
                                getString2(jsonService,"Maintainer"),
                                getString2(jsonService,"Md5sum"),
                                getString2(jsonService,"Name"),
                                getString2(jsonService,"Oldversion"),
                                getString2(jsonService,"PackageName"),
                                getString2(jsonService,"Predepends"),
                                getString2(jsonService,"Priority"),
                                getString2(jsonService,"Provides"),
                                getString2(jsonService,"Replaces"),
                                getString2(jsonService,"Repository"),
                                getString2(jsonService,"Section"),
                                getString2(jsonService,"Sha1"),
                                getString2(jsonService,"Sha256"),
                                getString2(jsonService,"Size"),
                                getString2(jsonService,"Tag"),
                                getString2(jsonService,"Version")
                        )
                );

            }
        }
        return upgradeds;
    }

    public String upgrade(INotifiableManager manager,ArrayList<Upgraded> upgrades)
    {

        ArrayNode arr = arr();
        for(final Upgraded u :upgrades)
        {
            arr.add(u.Name);
        }

        JsonNode result = mConnection.getJson(manager, "upgrade", "Apt",obj().p("packages",arr));
        return getString2(result,"response");
    }

    public Output getOutput(INotifiableManager manager,String fileName,int pos){

        JsonNode result = mConnection.getJson(manager, "getOutput", "Exec",obj().p("filename",fileName).p("pos",pos));
        final JsonNode jsonRep = result.get("response");
        Output output = null;
        if (jsonRep != null) {
            output = new Output(getString2(jsonRep,"Filename"),getInt(jsonRep,"Pos"),getString2(jsonRep,"Output"),getBool(jsonRep,"Running"));
        }

        return output;
    }

    public void reboot(INotifiableManager manager)
    {
        JsonNode result = mConnection.getJson(manager, "reboot", "System",null);
    }

    public void shutdown(INotifiableManager manager)
    {
        JsonNode result = mConnection.getJson(manager, "shutdown", "System",null);
    }
}

