package fr.doodz.openmv.api.object.types;

/**
 * Created by doods on 31/07/14.
 */
public enum Sortdir {

    ASC("ASC"),
    DESC("DESC");
    private final String text;

    /**
     * @param text
     */
    private Sortdir(final String text) {
        this.text = text;
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return text;
    }
}
