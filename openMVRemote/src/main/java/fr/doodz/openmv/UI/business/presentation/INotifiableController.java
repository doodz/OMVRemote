package fr.doodz.openmv.UI.business.presentation;

import fr.doodz.openmv.UI.business.Command;
import fr.doodz.openmv.api.api.business.INotifiableManager;

/**
 * Created by doods on 21/05/14.
 */

public interface INotifiableController {
    public void onWrongConnectionState(int state, INotifiableManager manager, Command<?> source);

    public void onError(Exception e);

    public void onMessage(String message);

    public void runOnUI(Runnable action);
}
