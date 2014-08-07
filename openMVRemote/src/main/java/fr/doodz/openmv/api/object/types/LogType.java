package fr.doodz.openmv.api.object.types;

/**
 * Created by doods on 31/07/14.
 */
public enum LogType {

    Authentication("auth"),
    Boot("boot"),
    Daemon("daemon"),
    FTP("proftpd"),
    FTPTransfer("proftpd_xferlog"),
    Messages("messages"),
    Rsync("rsync"),
    RsyncServer("rsyncd"),
    Smart("smartd"),
    Syslog("syslog")
    ;
    /**
     * @param text
     */
    private LogType(final String text) {
        this.text = text;
    }

    private final String text;

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return text;
    }
}