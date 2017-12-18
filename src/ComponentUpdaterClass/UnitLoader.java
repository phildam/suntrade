/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ComponentUpdaterClass;

/**
 *
 * @author g33k5qu4d
 */
public class UnitLoader {
    private static String unit;
    private static String unitHeadEmail;
    private static String unitHeadName;
    private static String unitHeadPhoneNumber;

    
    public static String getUnit() {
        return unit;
    }

    public static String getUnitHeadEmail() {
        return unitHeadEmail;
    }

   
    public static String getUnitHeadName() {
        return unitHeadName;
    }

   
    public static String getUnitHeadPhoneNumber() {
        return unitHeadPhoneNumber;
    }

    public static void setUnit(String aUnit) {
        unit = aUnit;
    }

   
    public static void setUnitHeadEmail(String aUnitHeadEmail) {
        unitHeadEmail = aUnitHeadEmail;
    }

    
    public static void setUnitHeadName(String aUnitHeadName) {
        unitHeadName = aUnitHeadName;
    }

    public static void setUnitHeadPhoneNumber(String aUnitHeadPhoneNumber) {
        unitHeadPhoneNumber = aUnitHeadPhoneNumber;
    }
    
    
}
