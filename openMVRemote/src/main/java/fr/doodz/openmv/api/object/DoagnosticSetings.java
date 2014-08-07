package fr.doodz.openmv.api.object;

/**
 * Created by doods on 01/08/14.
 */
public class DoagnosticSetings {

    public Boolean Enamble;
    public String Host;
    public int Port;
    public String Protocol;

    public DoagnosticSetings(Boolean enable, String host,int port,String protocol)
    {

        this.Enamble = enable;
        this.Host = host;
        this.Port = port;
        this.Protocol = protocol;

    }


}
