package org.example;



public class Package {


    private int packageId;

    private String packageType;
    private String packageName;

    public Package (String packageType, String packageName) {
        this.packageType = packageType;
        this.packageName = packageName;

    }

    public void setPackageId(int packageId) {
        this.packageId = packageId;
    }

    public int getPackageId() {
        return packageId;
    }

    public String getPackageType() {
        return packageType;
    }


    public String getPackageName() {
        return packageName;
    }


    @Override
    public String toString() {
        return "Package{" +
                "packageId=" + packageId +
                ", packageType='" + packageType + '\'' +
                ", packageName='" + packageName + '\'' +
                '}';
    }
}
