package fr.doodz.openmv.api.api.data;

import fr.doodz.openmv.api.api.object.Output;
import fr.doodz.openmv.api.api.business.INotifiableManager;

/**
 * Created by doods on 12/10/2014.
 */
public interface IOutputClient {

    Output getOutput(INotifiableManager manager,String fileName,int pos);
}
