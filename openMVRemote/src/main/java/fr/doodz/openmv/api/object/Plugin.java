package fr.doodz.openmv.api.object;

/**
 * Created by doods on 01/10/14.
 */
public class Plugin implements IInstallObject {


    public Boolean ReadOnly;
    public String Depends;
    public String Homepage;
    public Boolean Installed;
    public String Package;
    public String Architecture;
    public String Description;
    public String Filename;
    public int Installedsize;
    public String Longdescription;
    public String Maintainer;
    public String Md5sum;
    public String Name;
    public String Priority;
    public String Section;
    public String Sha1;
    public String Sha256;
    public int Size;
    public String Version;
    String Repository = "";

    public Plugin(String architecture, String description, Boolean _readOnly, String filename, int installedsize,
                  String longdescription, String maintainer, String md5sum, String name, String depends,
                  String homepage, String _package, String priority, Boolean installed, String section, String sha1, String sha256, int size, String version) {


        this.Architecture = architecture;
        this.Description = description;
        this.ReadOnly = _readOnly;
        this.Filename = filename;
        this.Installedsize = installedsize;
        this.Longdescription = longdescription;
        this.Maintainer = maintainer;
        this.Md5sum = md5sum;
        this.Name = name;
        this.Depends = depends;
        this.Homepage = homepage;
        this.Package = _package;
        this.Priority = priority;
        this.Installed = installed;
        this.Section = section;
        this.Sha1 = sha1;
        this.Sha256 = sha256;
        this.Size = size;

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

    public Boolean getInstalled() {return this.Installed;}
}
