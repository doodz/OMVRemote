package fr.doodz.openmv.httpapi;

/**
 * Created by doods on 18/05/14.
 */
public class NoNetworkException extends Exception {
    private static final long serialVersionUID = -300859290934884233L;
    public NoNetworkException() {
        super("This application requires network access. Enable mobile network or Wi-Fi to download data.");
    }
}
