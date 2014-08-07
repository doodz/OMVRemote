package fr.doodz.openmv.httpapi;

/**
 * Created by doods on 18/05/14.
 */
public class WrongDataFormatException extends Exception {
    private static final long serialVersionUID = 42438942451326636L;
    private String mExpected;
    private String mReceived;
    public WrongDataFormatException(String expected, String received) {
        super("Wrong data format, expected '" + expected + "', got '" + received + "'.");
        mExpected = expected;
        mReceived = received;
    }
    public String getExpected() {
        return mExpected;
    }
    public String getReceived() {
        return mReceived;
    }
}
