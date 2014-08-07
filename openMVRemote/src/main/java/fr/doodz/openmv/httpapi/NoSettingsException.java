package fr.doodz.openmv.httpapi;

/**
 * Created by doods on 18/05/14.
 */
public class NoSettingsException extends Exception {

    private static final long serialVersionUID = -5024397978225112156L;

    public NoSettingsException() {
        super("Click on \"Settings\" or use the menu in order to add an OMV host or IP address to your configuration.");
    }
}