package org.example;

import java.util.ArrayList;
import java.util.List;

public class Packages {

    int totalNbrOfPackages;
    private Database db;

    private List<Package> list;

    public Packages() {
        this.db = new Database();
        this.list = db.getListOfAllPackages();
    }
    public int getTotalNumberOfPackages() {
        return this.list.size();
    }
    public int getPackageId() {
        return this.list.get(1).getPackageId();
    }
    public String getPackageType() {
        return this.list.get(2).getPackageType();
    }
    public String getPackageName() {
        return this.list.get(3).getPackageName();
    }

    public void createPackage(int packageId, String packageType, String packageName) {
        db.createNewPackage(packageId,packageType,packageName);
        this.list = db.getListOfAllPackages();
        //this.list = db.getAllPackages();

    }
    public void printAllPackages(){
        List<Package> allPackages = db.getListOfAllPackages();
        System.out.println(allPackages);
    }

    public Package fetchPackageByPackageType(String packageType){
         Package fetchedPackage1 = db.fetchedPackageByPackageType("ADVENTURE");
         //System.out.println("The fetched package is: " + fetchedPackage1);
        return fetchedPackage1;
    }
    public Package fetchPackageByPackageName(String packageName){
        Package fetchedPackage2 = db.fetchedPackageByPackageName("Explore culture Skåne");
        //System.out.println("The fetched package is: " + fetchedPackage2);
        return fetchedPackage2;
    }





}
