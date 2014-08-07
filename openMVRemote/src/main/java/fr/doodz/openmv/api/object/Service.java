package fr.doodz.openmv.api.object;

/**
 * Created by doods on 27/07/14.
 */
public class Service {

    public String Name;
    public String Title;
    public Boolean Enabled;
    public Boolean Running;

    public Service(String name, String title, Boolean enabled, Boolean running){
        this.Name = name;
        this.Title = title;
        this.Enabled = enabled;
        this.Running = running;
    }
}
