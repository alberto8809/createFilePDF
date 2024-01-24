package org.example.createfile.model;

public class ObjFile {

    private PolicyObj policyObj;
    private String nameFile;
    private String companyName;

    private String client;
    private String date;
    private String typeOf;


    public ObjFile() {
    }

    public ObjFile(PolicyObj policyObj, String nameFile, String companyName, String client, String date, String typeOf) {
        this.policyObj = policyObj;
        this.nameFile = nameFile;
        this.companyName = companyName;
        this.client = client;
        this.date = date;
        this.typeOf = typeOf;
    }

    public PolicyObj getPolicyObj() {
        return policyObj;
    }

    public void setPolicyObj(PolicyObj policyObj) {
        this.policyObj = policyObj;
    }

    public String getNameFile() {
        return nameFile;
    }

    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTypeOf() {
        return typeOf;
    }

    public void setTypeOf(String typeOf) {
        this.typeOf = typeOf;
    }


    @Override
    public String toString() {
        return "ObjFile{" +
                "policyObj=" + policyObj +
                ", nameFile='" + nameFile + '\'' +
                ", companyName='" + companyName + '\'' +
                ", client='" + client + '\'' +
                ", date='" + date + '\'' +
                ", typeOf='" + typeOf + '\'' +
                '}';
    }
}
