package fr.doodz.openmv.api.object;

/**
 * Created by doods on 08/09/14.
 */
public class ServiceSSH {

    public boolean Compression;
    public boolean Enable;
    public String ExtraOptions;
    public boolean PasswordAuthentication;
    public boolean PermitRootLogin;
    public int Port;
    public boolean TcpForwarding;




    public ServiceSSH(boolean compression, boolean enable, String extraoptions, boolean passwordauthentication,
                      boolean permitrootlogin, int port, boolean tcpforwarding){
        this.Compression = compression;
        this.Enable = enable;
        this.ExtraOptions = extraoptions;
        this.PasswordAuthentication = passwordauthentication;
        this.PermitRootLogin = permitrootlogin;
        this.Port = port;
        this.TcpForwarding = tcpforwarding;
    }
}
