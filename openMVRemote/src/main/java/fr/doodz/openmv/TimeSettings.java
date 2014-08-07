package fr.doodz.openmv;

/**
 * Created by doods on 07/08/14.
 */
public class TimeSettings {

    public Date Date;
    public String Timezone;
        public boolean Ntpenable;
    public String Ntptimeservers;

    public TimeSettings(String local, String iSO8601, String timezone, boolean ntpenable, String ntptimeservers){
         this.Date = new Date(local,iSO8601);
         this.Timezone = timezone;
         this.Ntpenable = ntpenable;
         this.Ntptimeservers = ntptimeservers;

    }

}

  class Date{

      public String Local;
      public String ISO8601;

      public Date(String local,String iSO8601)
      {
          this.Local = local;
          this.ISO8601 = iSO8601;
      }

  }