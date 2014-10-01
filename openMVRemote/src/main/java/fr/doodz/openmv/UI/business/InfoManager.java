package fr.doodz.openmv.UI.business;

import android.content.Context;

import fr.doodz.openmv.api.object.business.DataResponse;
import fr.doodz.openmv.api.object.business.IInfoManager;
import fr.doodz.openmv.api.object.business.INotifiableManager;
import fr.doodz.openmv.httpapi.WifiStateException;
import fr.doodz.openmv.jsonrpc.client.InfoSystem;

/**
 * Created by doods on 21/05/14.
 */
public class InfoManager extends AbstractManager implements IInfoManager, INotifiableManager {

    /**
     * Returns any system info variable, see {@link fr.doodz.openmv.api.object.infos}
     *
     * @param response Response object
     * @param field    Field to return
     */
    public void getSystemInfo(final DataResponse<String> response, final int field, final Context context) {
        mHandler.post(new Command<String>(response, this) {
            @Override
            public void doRun() throws Exception {
                response.value = info(context).getSystemInfo(InfoManager.this, field);
            }

        });
    }

    public InfoSystem getSystemInfo(final Context context) {

        try {
            return info(context).getFullSystemInfo(InfoManager.this);
        } catch (WifiStateException e) {
            e.printStackTrace();
        }
        return null;
    }

}
