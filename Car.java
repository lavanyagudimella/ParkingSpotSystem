/**
 * This class is the Car
 * of Parking Spot System
 * @author Bhagyasree Lavanya 
 * @version 1.8.0_101
 */
import java.util.*;
import java.util.ArrayList;
public class Car extends Vehicle
{
    /**   
     * Declaring an instance of Car Class  
     */
    private String Registration_Number;
    protected double ChargeRate;
    protected String myData;
    /**
     * Constructor for objects of class Car 
     */
    public Car(String myData) throws VehicleClassException
    {  
        try
        {
            String[] num = myData.split(",");
            this.Registration_Number = num[0]; 
            int temp = num[0].length()+ 1;
            super.setState(myData.substring(temp,myData.length()));
        }
        catch(Exception ex)
        {
            throw new VehicleClassException("Error occured while initializing Car");
        }
    }
    
    /**
     * Constructor with two parameter namely String and String is implemented to assign ownerName and registration number values.
     * @param Owner_Name 
     * @param Registration_Number
     */
    public Car(String Owner_Name,String Registration_Number) throws VehicleClassException
    {
       super(Owner_Name);
       if(Owner_Name == null || Owner_Name.trim().length()==0)
       {
           throw new VehicleClassException("Owner_Name must not be null or empty");
       }
  
       if(Registration_Number == null || Registration_Number.trim().length() == 0)
       {
           throw new VehicleClassException("Registration_Number must not be null or empty");
       }
       this.Registration_Number=Registration_Number;
    }
    
    /**  
     * This is a method used 
     * to get the Registration_Number of the Car
     */
    public String getRegistration_Number()
    {
        return Registration_Number;
    }

    /**  
     * This is a method used 
     * to calculate the Fees
     */
    public double getFee()
    {
        return ((super.gettimeParked()/30) + 1) * (super.getChargeRate()/2);
    }

    /**
     * This method is used to save Car Data
     * @param data to be saved 
     */  
    public String saveData()
    {
        String C = "Vehicle_Type-C" + "," + getRegistration_Number() + "," + super.saveData();
        /*myData.add(C);*/
        return C;
    }
    
    /**   
     * This is a method used
     * to display the Vehicle_Type from the super class
     */
    public String toString()
    {
        String temp = super.toString() + " The Parked_Vehicle type is a CAR" + "\n" +
            "Owner_Name:" + super.getOwner_Name() + "\n" +
            "Registration_Number:" + getRegistration_Number();             
        return temp;
    }
}//end of class