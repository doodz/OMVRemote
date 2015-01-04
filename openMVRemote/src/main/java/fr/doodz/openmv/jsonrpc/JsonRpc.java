package fr.doodz.openmv.jsonrpc;

import fr.doodz.openmv.api.api.object.Host;
import fr.doodz.openmv.jsonrpc.client.DiagnosticClient;
import fr.doodz.openmv.jsonrpc.client.InfoClient;
import fr.doodz.openmv.jsonrpc.client.OutputClient;
import fr.doodz.openmv.jsonrpc.client.SystemClient;

/**
 * Created by doods on 18/05/14.
 */
public class JsonRpc {


    /**
     * Use this client for anything system related
     */
    public final InfoClient info;


    public final DiagnosticClient Diagnostic;
    public final SystemClient System;
    public final OutputClient Output;

    /**
     * Construct with all paramaters
     *
     * @param host    Connection data of the host
     * @param timeout Read timeout
     */
    public JsonRpc(Host host, int timeout) {
        Connection connection;
        if (host != null) {
            connection = Connection.getInstance(host.addr, host.port);
            connection.setAuth(host.user, host.pass);
        } else {
            connection = Connection.getInstance(null, 0);
        }
        connection.setTimeout(timeout);
        info = new InfoClient(connection);
        Diagnostic = new DiagnosticClient(connection);
        System = new SystemClient(connection);
        this.Output = new OutputClient(connection);
    }

    /**
     * Updates host info on all clients
     *
     * @param host
     */
    public void setHost(Host host) {
        info.setHost(host);
        //music.setHost(host);
    }
}