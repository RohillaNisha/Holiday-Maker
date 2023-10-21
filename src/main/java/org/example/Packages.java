package org.example;

import java.util.ArrayList;

public class Packages {

    int totalNbrOfPackages;
    private Database db;

    private ArrayList<Package> list1;

    public Packages() {
        this.db = new Database();
        this.list1 = db.listOfAllPackages();

    }
    public int getTotalNumberOfPackages() {
        return this.list1.size();

    }


    public int getPackageId() {
        return this.list1.get(1).getPackageId();
    }
    public String getPackageType() {
        return this.list1.get(2).getPackageType();
    }
    public String getPackageName() {
        return this.list1.get(3).getPackageName();
    }

    public void createPackage(int packageId, String packageType, String packageName) {
        db.createNewPackage(packageId,packageType,packageName);
        this.list1 = db.listOfAllPackages();
        //this.list1 = db.getAllPackages();

    }
    public void printAllPackages(){
        for (int i = 0; i < this.list1.size(); i++) {
            System.out.println(this.list1.get(i).toString());
        }
    }





    // Metoder vi ska ha gällande Packages


}
