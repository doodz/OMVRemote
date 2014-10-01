package fr.doodz.openmv.api.object.business;

import android.content.Context;

import fr.doodz.openmv.jsonrpc.client.InfoSystem;

/**
 * Created by doods on 21/05/14.
 */
public interface IInfoManager extends IManager {

    /**
     * Returns any system info variable, see {@link fr.doodz.openmv.api.object.infos}
     *
     * @param response Response object
     * @param field    Field to return
     */
    public void getSystemInfo(final DataResponse<String> response, final int field, final Context context);

    public InfoSystem getSystemInfo(final Context context);
}
