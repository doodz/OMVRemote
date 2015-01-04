package fr.doodz.openmv.api.api.object;

/**
 * Created by doods on 31/07/14.
 */
public class SysLog {

    public String Date;
    public String Hostname;
    public String Message;
    public int Rownum;
    public int Ts;


    public SysLog(String date, String hostname, String message, int rownum, int ts) {

        this.Date = date;
        this.Hostname = hostname;
        this.Message = message;
        this.Rownum = rownum;
        this.Ts = ts;

    }
}
