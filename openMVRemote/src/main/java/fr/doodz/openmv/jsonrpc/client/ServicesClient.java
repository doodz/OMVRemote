package fr.doodz.openmv.jsonrpc.client;

import com.fasterxml.jackson.databind.JsonNode;

import fr.doodz.openmv.api.object.Host;
import fr.doodz.openmv.api.object.ServiceSSH;
import fr.doodz.openmv.api.object.business.INotifiableManager;
import fr.doodz.openmv.api.object.data.IServicesClient;
import fr.doodz.openmv.jsonrpc.Connection;

/**
 * Created by doods on 08/09/14.
 */
public class ServicesClient extends Client implements IServicesClient {

    public static final String TAG = "ServicesClient";
    /**
     * Class constructor needs reference to HTTP client connection
     * @param connection
     */
    public ServicesClient(Connection connection) {
        super(connection);
    }
    /**
     * Updates host info on the connection.
     * @param host
     */
    public void setHost(Host host) {
        mConnection.setHost(host);
    }

    public ServiceSSH getSSH(INotifiableManager manager){
        JsonNode result = mConnection.getJson(manager, "get", "SSH",null);

            ServiceSSH ssh = new ServiceSSH(getBool(result,"compression"),
                                        getBool(result,"enable"),
                                        getString2(result,"extraoptions"),
                                        getBool(result,"passwordauthentication"),
                                        getBool(result,"permitrootlogin"),
                                        getInt(result,"port"),
                                        getBool(result,"tcpforwarding"));
        return ssh;

    }

    public void setSSH(INotifiableManager manager,ServiceSSH service){


        JsonNode result = mConnection.getJson(manager, "set", "SSH",
        obj().p("compression",service.Compression)
            .p("enable",service.Enable)
            .p("extraoptions",service.ExtraOptions)
            .p("passwordauthentication",service.PasswordAuthentication)
            .p("permitrootlogin",service.PermitRootLogin)
            .p("port",service.Port)
            .p("tcpforwarding",service.TcpForwarding));
    }

}
