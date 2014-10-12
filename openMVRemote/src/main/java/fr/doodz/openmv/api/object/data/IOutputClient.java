package fr.doodz.openmv.api.object.data;

import fr.doodz.openmv.api.object.Output;
import fr.doodz.openmv.api.object.business.INotifiableManager;

/**
 * Created by doods on 12/10/2014.
 */
public interface IOutputClient {

    Output getOutput(INotifiableManager manager,String fileName,int pos);
}
