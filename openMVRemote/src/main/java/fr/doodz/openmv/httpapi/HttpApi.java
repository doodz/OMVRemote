package fr.doodz.openmv.httpapi;

import fr.doodz.openmv.api.object.Host;
import fr.doodz.openmv.httpapi.client.InfoClient;
/**
 * Created by doods on 18/05/14.
 */
public class HttpApi {

    /**
     * Use this client for anything system related
     */
    public final InfoClient info;

    /**
     * Use this client for anything music related
     */
    //public final MusicClient music;

    /**
     * Construct with all paramaters
     * @param host    Connection data of the host
     * @param timeout Read timeout
     */
    public HttpApi(Host host, int timeout) {
        Connection connection;
        if (host != null) {
            connection = Connection.getInstance(host.addr, host.port);
            connection.setAuth(host.user, host.pass);
        } else {
            connection = Connection.getInstance(null, 0);
        }
        connection.setTimeout(timeout);
        info = new InfoClient(connection);
        //music = new MusicClient(connection);
        //video = new VideoClient(connection);
        //control = new ControlClient(connection);
        //shows = new TvShowClient(connection);
    }

    /**
     * Updates host info on all clients
     * @param host
     */
    public void setHost(Host host) {
        info.setHost(host);
        //music.setHost(host);
        //video.setHost(host);
        //control.setHost(host);
        //shows.setHost(host);
    }
}