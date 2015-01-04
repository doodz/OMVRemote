package fr.doodz.openmv.api.api.types;

/**
 * Created by doods on 18/05/14.
 */
public enum DirectoryMask {
    Directories,
    AllFiles,
    All,
    Music,
    Video;

    public String toString() {
        switch (this) {
            case Directories:
                return "*";
            case AllFiles:
                return "*.*";
            case Music:
                return "music";
            case Video:
                return "video";
            default:
                return "/";
        }
    }
}
