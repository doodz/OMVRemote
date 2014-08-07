package fr.doodz.openmv.api.object.data;

import fr.doodz.openmv.api.object.Host;
/**
 * Created by doods on 18/05/14.
 */
public interface IClient {

    /**
     * Updates host info on the connection.
     * @param host
     */
    public void setHost(Host host);
}
