package fr.doodz.openmv.api.api.object;

/**
 * Created by doods on 07/08/14.
 */
public class WebGuiSetting {

    public boolean Dnssdenable;
    public String Dnssdname;
    public boolean Enablessl;
    public boolean Forcesslonly;
    public int Port;
    public String Sslcertificateref;
    public int Sslport;
    public int Timeout;

    public WebGuiSetting(boolean dnssdenable, String dnssdname, boolean enablessl, boolean forcesslonly, int port, String sslcertificateref, int sslport, int timeout) {

        this.Dnssdenable = dnssdenable;
        this.Dnssdname = dnssdname;
        this.Enablessl = enablessl;
        this.Forcesslonly = forcesslonly;
        this.Port = port;
        this.Sslcertificateref = sslcertificateref;
        this.Sslport = sslport;
        this.Timeout = timeout;
    }

}
