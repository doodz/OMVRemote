package fr.doodz.openmv.api.api.infos;

/**
 * Created by doods on 18/05/14.
 */
public class FileTypes {

    /**
     * Audio file extensions
     */
    public final static String[] AUDIO = {"ac3", "flac", "m4a", "mp3", "mid", "ogg", "wav"};

    /**
     * Playlist file extensions
     */
    public final static String[] PLAYLIST = {"m3u", "pls"};

    /**
     * Video extensions
     */
    public final static String[] VIDEO = {"avi", "flv", "mkv", "mov", "mp4", "mpg", "mpeg", "ts", "wmv", "vob"};

    /**
     * Image extensions
     */
    public final static String[] PICTURE = {"bmp", "gif", "jpeg", "jpg", "png", "tbn"};

    /**
     * Returns true if extensions is of type audio.
     *
     * @param extension Extension to check, without "."
     * @return true if audio extension, false otherwise.
     */
    public static boolean isAudio(String extension) {
        return is(AUDIO, extension);
    }

    /**
     * Returns true if extensions is of type audio.
     *
     * @param extension Extension to check, without "."
     * @return true if audio extension, false otherwise.
     */
    public static boolean isAudioOrPlaylist(String extension) {
        return is(AUDIO, extension) || is(PLAYLIST, extension);
    }

    /**
     * Returns true if extensions is of type video.
     *
     * @param extension Extension to check, without "."
     * @return true if video extension, false otherwise.
     */
    public static boolean isVideo(String extension) {
        return is(VIDEO, extension);
    }

    /**
     * Returns true if extensions is of type playlist.
     *
     * @param extension Extension to check, without "."
     * @return true if playlist extension, false otherwise.
     */
    public static boolean isPicture(String extension) {
        return is(PICTURE, extension);
    }

    /**
     * Returns true if extensions is of type picture.
     *
     * @param extension Extension to check, without "."
     * @return true if picture extension, false otherwise.
     */
    public static boolean isPlaylist(String extension) {
        return is(PLAYLIST, extension);
    }

    /**
     * Returns the file extension of a file name or path in lower case.
     *
     * @param filenameOrPath File name or path
     * @return File extension without "."
     */
    public static String getExtension(String filenameOrPath) {
        return filenameOrPath.substring(filenameOrPath.lastIndexOf(".") + 1).toLowerCase();
    }

    private static boolean is(String[] arr, String extension) {
        for (String audioExt : arr) {
            if (audioExt.equals(extension)) {
                return true;
            }
        }
        return false;
    }
}
