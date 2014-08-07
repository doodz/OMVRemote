package fr.doodz.openmv.api.object;

import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
/**
 * Created by doods on 17/05/14.
 */
public class Host implements Serializable {

    private static final String TAG = "Host";

    public static final int DEFAULT_HTTP_PORT = 80;
    public static final int DEFAULT_TIMEOUT = 5000;
    public static final int DEFAULT_WOL_WAIT = 40;
    public static final int DEFAULT_WOL_PORT = 9;

    /**
     * Database ID
     */
    public int id;
    /**
     * Name (description/label) of the host
     */
    public String name;
    /**
     * IP address or host name of the host
     */
    public String addr;
    /**
     * HTTP API Port
     */
    public int port = DEFAULT_HTTP_PORT;
    /**
     * User name of in case of HTTP authentication
     */
    public String user;
    /**
     * Password of in case of HTTP authentication
     */
    public String pass;
    /**
     * TCP socket read timeout in milliseconds
     */
    public int timeout = DEFAULT_TIMEOUT;
    /**
     * If this host is only available through wifi
     */
    public boolean wifi_only = false;
    /**
     * If wifi only is true there might be an access point specified to connect to
     */
    public String access_point;
    /**
     * The MAC address of this host
     */
    public String mac_addr;
    /**
     * The time to wait after sending WOL
     */
    public int wol_wait = DEFAULT_WOL_WAIT;
    /**
     * The port to send the WOL to
     */
    public int wol_port = DEFAULT_WOL_PORT;

    /**
     * Something readable
     */
    public String toString() {
        return addr + ":" + port;
    }

    public String getSummary() {
        return toString();
    }

    public String toJson() {
        try {
            JSONObject json = new JSONObject();
            json.put("name", name);
            json.put("addr", addr);
            json.put("port", port);
            json.put("user", user);
            json.put("pass", pass);
            json.put("timeout", timeout);
            json.put("wifi_only", wifi_only);
            json.put("access_point", access_point);
            json.put("mac_addr", mac_addr);
            json.put("wol_wait", wol_wait);
            json.put("wol_port", wol_port);
            return json.toString();
        } catch (JSONException e) {
            Log.e(TAG, "Error into Json", e);
            return "";
        }
    }

    private static final long serialVersionUID = 7886482294339161092L;
}
