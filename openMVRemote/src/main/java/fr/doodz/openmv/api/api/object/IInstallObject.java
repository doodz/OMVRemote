package fr.doodz.openmv.api.api.object;

/**
 * Created by doods on 01/10/14.
 */
public interface IInstallObject {


    public String getArchitecture();

    public String getDescription();

    public String getFilename();

    public int getInstalledsize();

    public String getLongdescription();

    public String getMaintainer();

    public String getMd5sum();

    public String getName();

    public String getPriority();

    public String getSection();

    public String getSha1();

    public String getSha256();

    public int getSize();

    public String getVersion();

    public String getRepository();
    public Boolean getInstalled();
}
