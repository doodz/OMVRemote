package fr.doodz.openmv.UI.business;

import fr.doodz.openmv.UI.business.presentation.INotifiableController;
import fr.doodz.openmv.api.object.business.IDiagnosticManager;
import fr.doodz.openmv.api.object.business.IInfoManager;

/**
 * Created by doods on 21/05/14.
 */
public class ManagerFactory {



    public static IInfoManager getInfoManager(INotifiableController controller) {
        return ManagerThread.info(controller);
    }

    public static IDiagnosticManager getDiagnosticManager(INotifiableController controller) {
        return ManagerThread.diagnostic(controller);
    }

}
