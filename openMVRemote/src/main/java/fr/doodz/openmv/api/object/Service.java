package fr.doodz.openmv.api.object;

import fr.doodz.openmv.app.R;

/**
 * Created by doods on 27/07/14.
 */
public class Service {

    private String Name;
    private String Title;
    private Boolean Enabled;
    private Boolean Running;

    public Service(String name, String title, Boolean enabled, Boolean running) {
        this.Name = name;
        this.Title = title;
        this.Enabled = enabled;
        this.Running = running;
    }


    public String getName(){
        return this.Name;
    }

    public String getTitle(){
        return this.Title;
    }

    public Boolean getEnabled(){
        return this.Enabled;
    }

    public Boolean getRunning(){
        return this.Running;
    }

}
