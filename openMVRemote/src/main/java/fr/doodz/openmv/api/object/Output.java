package fr.doodz.openmv.api.object;

/**
 * Created by doods on 09/08/14.
 */
public class Output {

    public String Filename;
    public int Pos;
    public String Output;
    public Boolean Running;

    public Output (String filename,int pos,String output,Boolean running){

        this.Filename = filename;
        this.Pos = pos;
        this.Output = output;
        this.Running = running;
    }
}
