package fr.doodz.openmv.api.object.data;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import fr.doodz.openmv.api.object.FileLocation;
import fr.doodz.openmv.api.object.business.INotifiableManager;
import fr.doodz.openmv.api.object.types.DirectoryMask;
import fr.doodz.openmv.jsonrpc.client.InfoSystem;

/**
 * Created by doods on 18/05/14.
 */
public interface IInfoClient extends IClient {

    /**
     * Returns the contents of a directory
     *
     * @param path   Path to the directory
     * @param mask   Mask to filter
     * @param offset Offset (0 for none)
     * @param limit  Limit (0 for none)
     * @return
     */
    public ArrayList<FileLocation> getDirectory(INotifiableManager manager, String path, DirectoryMask mask, int offset, int limit, int mediaType);

    /**
     * Returns all the contents of a directory
     *
     * @param path Path to the directory
     * @return
     */
    public ArrayList<FileLocation> getDirectory(INotifiableManager manager, String path, int mediaType);


    /**
     * Returns all defined shares of a media type
     *
     * @param mediaType Media type
     * @return
     */
    public ArrayList<FileLocation> getShares(INotifiableManager manager, int mediaType);

    /**
     * Returns URI of the currently playing's thumbnail.
     *
     * @return
     * @throws MalformedURLException
     * @throws URISyntaxException
     */
    public String getCurrentlyPlayingThumbURI(INotifiableManager manager) throws MalformedURLException, URISyntaxException;

    /**
     * Returns any system info variable, see {@link fr.doodz.openmv.jsonrpc.client.InfoSystem}
     *
     * @param field Field to return
     * @return
     */
    public String getSystemInfo(INotifiableManager manager, int field);

    /**
     * Returns any system info variable, see {@link fr.doodz.openmv.jsonrpc.client.InfoSystem}
     *
     * @param manager
     * @return
     */

    public InfoSystem getFullSystemInfo(INotifiableManager manager);

    /**
     * Returns a boolean GUI setting
     *
     * @param field
     * @return
     */
    public boolean getGuiSettingBool(INotifiableManager manager, int field);

    /**
     * Returns an integer GUI setting
     *
     * @param field
     * @return
     */
    public int getGuiSettingInt(INotifiableManager manager, int field);

    /**
     * Returns a boolean GUI setting
     *
     * @param field
     * @param value Value
     * @return
     */
    public boolean setGuiSettingBool(INotifiableManager manager, int field, boolean value);

    /**
     * Returns an integer GUI setting
     *
     * @param field
     * @param value Value
     * @return
     */
    public boolean setGuiSettingInt(INotifiableManager manager, int field, int value);

    /**
     * Returns any music info variable see {@link org.xbmc.http.info.MusicInfo}
     *
     * @param field Field to return
     * @return
     */
    public String getMusicInfo(INotifiableManager manager, int field);

    /**
     * Returns any video info variable see {@link org.xbmc.http.info.VideoInfo}
     *
     * @param field Field to return
     * @return
     */
    public String getVideoInfo(INotifiableManager manager, int field);

}

