package fr.doodz.openmv.api.api.types;

/**
 * Created by doods on 31/07/14.
 */
public enum Sortfield {
    Name("name"),
    Message("message"),
    Rownum("rownum");
    private final String text;

    /**
     * @param text
     */
    private Sortfield(final String text) {
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
