package fr.doodz.openmv.jsonrpc.client;

/**
 * Created by doods on 25/07/14.
 */
public class InfoSystem {


    public String Hostname;
    public String Version;
    public String Processor;
    public String Kernel;
    public String Uptime;
    /**
     * Constructor
     * @param hostname
     * @param version
     * @param processor
     * @param kernel
     */
    public InfoSystem(String hostname, String version, String processor, String kernel,String uptime){
        this.Hostname = hostname;
        this.Version = version;
        this.Processor = processor;
        this.Kernel = kernel;
        this.Uptime = uptime;
    }
}
