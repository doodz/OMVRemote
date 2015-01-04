package fr.doodz.openmv.api.api.types;

/**
 * Created by doods on 18/05/14.
 */
public abstract class SortType {

    public static final String ORDER_ASC = "ASC";
    public static final String ORDER_DESC = "DESC";

    public static final int ALBUM = 1;
    public static final int ARTIST = 2;
    public static final int TITLE = 3;
    public static final int FILENAME = 4;
    public static final int TRACK = 5;
    public static final int RATING = 6;
    public static final int YEAR = 7;
    public static final int EPISODE_NUM = 8;
    public static final int EPISODE_TITLE = 9;
    public static final int EPISODE_RATING = 10;
    public static final int DATE_ADDED = 11;

    public static final int DONT_SORT = -1;
}
