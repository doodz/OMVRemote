package fr.doodz.openmv.api.api.data;

import fr.doodz.openmv.api.api.object.ServiceSSH;
import fr.doodz.openmv.api.api.business.INotifiableManager;

/**
 * Created by doods on 08/09/14.
 */
public interface IServicesClient extends IClient {

    ServiceSSH getSSH(INotifiableManager manager);

    void setSSH(INotifiableManager manager, ServiceSSH service);

}
