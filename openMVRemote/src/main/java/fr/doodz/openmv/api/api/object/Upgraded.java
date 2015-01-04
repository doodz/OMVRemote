package fr.doodz.openmv.api.api.object;


/**
 * Created by doods on 09/08/14.
 */
public class Upgraded implements IInstallObject {

    public String Architecture;
    public String Description;
    public String Essential;
    public String Filename;
    public int Installedsize;
    public String Longdescription;
    public String Maintainer;
    public String Md5sum;
    public String Name;
    public String Oldversion;
    public String PackageName;
    public String Predepends;
    public String Priority;
    public String Provides;
    public String Replaces;
    public String Repository;
    public String Section;
    public String Sha1;
    public String Sha256;
    public int Size;
    public String Tag;
    public String Version;

    public Upgraded(String architecture, String description, String essential, String filename, int installedsize,
                    String longdescription, String maintainer, String md5sum, String name, String oldversion,
                    String packageName, String predepends, String priority, String provides, String replaces,
                    String repository, String section, String sha1, String sha256, int size,
                    String tag, String version) {


        this.Architecture = architecture;
        this.Description = description;
        this.Essential = essential;
        this.Filename = filename;
        this.Installedsize = installedsize;
        this.Longdescription = longdescription;
        this.Maintainer = maintainer;
        this.Md5sum = md5sum;
        this.Name = name;
        this.Oldversion = oldversion;
        this.PackageName = packageName;
        this.Predepends = predepends;
        this.Priority = priority;
        this.Provides = provides;
        this.Replaces = replaces;
        this.Repository = repository;
        this.Section = section;
        this.Sha1 = sha1;
        this.Sha256 = sha256;
        this.Size = size;
        this.Tag = tag;
        this.Version = version;

    }


    public String getArchitecture() {
        return this.Architecture;
    }

    public String getDescription() {
        return this.Description;
    }

    public String getFilename() {
        return this.Filename;
    }

    public int getInstalledsize() {
        return this.Installedsize;
    }

    public String getLongdescription() {
        return this.Longdescription;
    }

    public String getMaintainer() {
        return this.Maintainer;
    }

    public String getMd5sum() {
        return this.Md5sum;
    }

    public String getName() {
        return this.Name;
    }

    public String getPriority() {
        return this.Priority;
    }

    public String getSection() {
        return this.Section;
    }

    public String getSha1() {
        return this.Sha1;
    }

    public String getSha256() {
        return this.Sha256;
    }

    public int getSize() {
        return this.Size;
    }

    public String getVersion() {
        return this.Version;
    }

    public String getRepository() {
        return this.Repository;
    }
    public Boolean getInstalled() {return false;}
}
