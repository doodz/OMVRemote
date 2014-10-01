package fr.doodz.openmv.api.object.types;

/**
 * Created by doods on 18/05/14.
 */
public abstract class MediaType {

    public static final int UNKNOWN = -1;
    public static final int MUSIC = 1;
    public static final int VIDEO = 2;
    public static final int VIDEO_MOVIE = 21;
    public static final int VIDEO_TVSHOW = 22;
    public static final int VIDEO_TVSEASON = 23;
    public static final int VIDEO_TVEPISODE = 24;
    public static final int PICTURES = 3;

    public static String getName(int type) {
        switch (type) {
            case MUSIC:
                return "music";
            case VIDEO:
            case VIDEO_MOVIE:
            case VIDEO_TVSHOW:
            case VIDEO_TVSEASON:
            case VIDEO_TVEPISODE:
                return "video";
            case PICTURES:
                return "pictures";
            default:
                return "";
        }
    }

    public static int getPlaylistType(int type) {
        switch (type) {
            case MUSIC:
                return 0;
            case VIDEO:
            case VIDEO_MOVIE:
            case VIDEO_TVSHOW:
            case VIDEO_TVSEASON:
            case VIDEO_TVEPISODE:
                return 1;
            case PICTURES:
                return 2;
            default:
                return 0;
        }
    }

    /**
     * Returns all media types.
     *
     * @return
     */
    public static int[] getTypes() {
        int[] types = new int[3];
        types[0] = MUSIC;
        types[1] = VIDEO;
        types[2] = PICTURES;
        return types;
    }

    public static String getArtFolder(int type) {
        switch (type) {
            case MUSIC:
                return "/Music";
            case VIDEO:
            case VIDEO_MOVIE:
            case VIDEO_TVSHOW:
            case VIDEO_TVSEASON:
            case VIDEO_TVEPISODE:
                return "/Video";
            case PICTURES:
                return "/Pictures";
            default:
                return "";
        }
    }

}