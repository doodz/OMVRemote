package fr.doodz.openmv.api.object.data;

import fr.doodz.openmv.api.object.ServiceSSH;
import fr.doodz.openmv.api.object.business.INotifiableManager;

/**
 * Created by doods on 08/09/14.
 */
public interface IServicesClient extends IClient {

    ServiceSSH getSSH(INotifiableManager manager);

    void setSSH(INotifiableManager manager, ServiceSSH service);

}
