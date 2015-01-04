package fr.doodz.openmv.api.api.infos;

/**
 * Created by doods on 18/05/14.
 */
public class GuiSettings {

    public static String getName(int name) {
        switch (name) {
            case MusicLibrary.LIBARY_ENABLED:
                return MusicLibrary.NAME_PREFIX + "enabled";
            case MusicLibrary.SHOW_COMPLATION_ARTISTS:
                return MusicLibrary.NAME_PREFIX + "showcompilationartists";
            case Services.EVENTSERVER_ENABLED:
                return Services.NAME_PREFIX + "esenabled";
            case Services.EVENTSERVER_ENABLED_ALL:
                return Services.NAME_PREFIX + "esallinterfaces";
            case Services.EVENTSERVER_PORT:
                return Services.NAME_PREFIX + "esport";
            case Services.EVENT_SERVER_INITIAL_DELAY:
                return Services.NAME_PREFIX + "esinitialdelay";
            case Services.EVENT_SERVER_CONTINUOUS_DELAY:
                return Services.NAME_PREFIX + "escontinuousdelay";
        }
        return null;
    }

    public static String getType(int name) {
        switch (name) {
            // boolean
            case MusicLibrary.LIBARY_ENABLED:
            case MusicLibrary.SHOW_COMPLATION_ARTISTS:
            case Services.EVENTSERVER_ENABLED:
            case Services.EVENTSERVER_ENABLED_ALL:
                return "1";

            // String
            case Services.EVENTSERVER_PORT:
                return "3";

            // int
            case Services.EVENT_SERVER_INITIAL_DELAY:
            case Services.EVENT_SERVER_CONTINUOUS_DELAY:
                return "0";
        }
        return null;
    }

    public static int getTypeInt(int name) {
        switch (name) {
            // boolean
            case MusicLibrary.LIBARY_ENABLED:
            case MusicLibrary.SHOW_COMPLATION_ARTISTS:
            case Services.EVENTSERVER_ENABLED:
            case Services.EVENTSERVER_ENABLED_ALL:
                return 1;

            // String
            case Services.EVENTSERVER_PORT:
                return 3;

            // int
            case Services.EVENT_SERVER_INITIAL_DELAY:
            case Services.EVENT_SERVER_CONTINUOUS_DELAY:
                return 0;
        }
        return -1;
    }

    public static class Services {
        public static final int EVENTSERVER_ENABLED = 1;
        public static final int EVENTSERVER_ENABLED_ALL = 2;
        public static final int EVENTSERVER_PORT = 3;

        public static final int EVENT_SERVER_INITIAL_DELAY = 795;
        public static final int EVENT_SERVER_CONTINUOUS_DELAY = 796;

        private static final String NAME_PREFIX = "services.";

    }

    public static class MusicLibrary {

        public static final int LIBARY_ENABLED = 418;
        public static final int SHOW_COMPLATION_ARTISTS = 13414;

        private static final String NAME_PREFIX = "musiclibrary.";

    }
}