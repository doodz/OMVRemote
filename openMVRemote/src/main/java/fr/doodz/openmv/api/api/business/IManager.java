package fr.doodz.openmv.api.api.business;

import fr.doodz.openmv.UI.business.presentation.INotifiableController;

/**
 * Created by doods on 21/05/14.
 */
public interface IManager {

    /**
     * Sets the current controller object. Must be set on each activity's onResume().
     *
     * @param controller Controller object
     */
    public void setController(INotifiableController controller);
}
