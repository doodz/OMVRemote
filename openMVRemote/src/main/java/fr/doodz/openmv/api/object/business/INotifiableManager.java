package fr.doodz.openmv.api.object.business;

import fr.doodz.openmv.UI.business.Command;

/**
 * Created by doods on 18/05/14.
 */
public interface INotifiableManager {

    public void onFinish(DataResponse<?> response);

    public void onWrongConnectionState(int state, Command<?> cmd);

    public void onError(Exception e);

    public void onMessage(String message);

    //public void onMessage(int code, String message);
    public void retryAll();
}