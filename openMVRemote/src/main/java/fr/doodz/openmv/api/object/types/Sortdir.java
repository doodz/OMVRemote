package fr.doodz.openmv.api.object.types;

/**
 * Created by doods on 31/07/14.
 */
public enum Sortdir {

    ASC("ASC"),
    DESC("DESC")

    ;
    /**
     * @param text
     */
    private Sortdir(final String text) {
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
