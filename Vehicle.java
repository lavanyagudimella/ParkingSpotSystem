/**
 * This class is the Vehicle
 * of Parking Spot System
 * @author Bhagyasree Lavanya 
 * @version 1.8.0_101
 */
/**
 * Importing classes for LocalDate
 * & DateTimeFormatter
 */
import java.text.*;
import java.time.format.*;
import java.text.DateFormat;
import java.time.*;
import java.time.format.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public abstract class Vehicle
{ 
    /**   
     * Declaring an instance of Car Class  
     */
    private LocalDateTime Intime;
    private String Owner_Name;
    public double ChargeRate;
    private int timeParked;
    private int HoursParked;
    private double Fee;
    public boolean changed_Rate=false;
    /**
     * Constructor for objects of class Car with parameters
     * @param Intime LocalDateTime - LocalDateTime contains the Intime
     * @param Owner_Name String - String contains the Owner_Name
     * @param Registration_Number String - String contains the Registration_Number
     * @param ChargeRate double - double contains the ChargeRate
     * @param timeParked int - int contains the timeParked
     */
    public Vehicle(String Owner_Name) throws VehicleClassException
    {
        try
        {
            this.Owner_Name=Owner_Name;
            this.Intime=LocalDateTime.now();
            this.ChargeRate=ChargeRate;
            this.timeParked=timeParked;
        }
        catch(Exception ex)
        {
            throw new VehicleClassException("Problem found in Class Vehicle() during initialization");
        }
    }

    /**
     * Constructor for objects of class Car without parameters
     */
    public Vehicle()
    {
    }

    /**  
     * This is a set method used 
     * to set the Intime of the Parked_Vehicle in the Parking_Spot
     */
    public void setIntime(LocalDateTime timein)
    {
        this.Intime=timein;
    }

    /**  
     * This is a get method used 
     * to calculate the Intime of the Parked_Vehicle in the Parking_Spot
     */
    public LocalDateTime getIntime()
    {
        return Intime;
    }

    /**  
     * This is a method used 
     * to calculate the time in minutes using Duration 
     */
    public int timeInMinutes()
    {
        return (int)Duration.between(Intime,LocalDateTime.now()).toMinutes();
    }

    /**  
     * This is a method used 
     * to calculate the Fees
     */
    public abstract double getFee();

    /**  
     * This is a set method used 
     * to calculate the no.of hours parked of the Parked_Vehicle in the Parking_Spot
     */
    public void setHoursParked(int hour) 
    {
        this.HoursParked=hour;
    }

    /**
     * This is a method used
     * to get the Owner_Name of the Car 
     * @return Owner_Name
     */
    public String getOwner_Name()
    {
        return Owner_Name;
    }

    /* 
     * This is a method used
     * to set the Registration_Number of the Car 
     * @param Reg_Num String - String Contains Reg_Num
     * 
    public void setRegistration_Number(String Reg_Num)
    {
    this.Registration_Number=Reg_Num;
    }*/

    /* 
     * This is a method used
     * to get the Registration_Number of the Car 
     * @return Registration_Number

    public String getRegistration_Number()
    {
    return Registration_Number;
    }*/

    /**  
     * This is a method used
     * to set the Charge_Rate of the Car 
     */
    public void setChargeRate(double newRate)
    {
        if(!changed_Rate)
        {
            this.ChargeRate=newRate;
            changed_Rate=true;
        }
        else
        {
            if(newRate<ChargeRate)
            {
                ChargeRate=newRate;
            }
        }
    }

    /**  
     * This is a method used
     * to get the Charge_Rate of the Car 
     * @return ChargeRate
     */
    public double getChargeRate()
    {
        return ChargeRate;
    }

    /**  
     * This is a method used
     * to know details of the current expectedCollectionDateTime
     * @return time_expired
     */
    public LocalDateTime expectedCollectionDateTime()
    {
        LocalDateTime time_expired = Intime.plusHours(timeParked);
        return time_expired;
    }

    /**  
     * This is a method used
     * to set the Time_Parked of the Car 
     * @param parkedtime int - int contains the parkedtime
     */
    public void settimeParked(int parkedtime)
    {
        this.timeParked = parkedtime;
    }

    /**  
     * This is a method used
     * to get the Time_Parked of the Car 
     * @return timeParked
     */
    public int gettimeParked()
    {
        return (int) Duration.between(Intime,LocalDateTime.now()).toMinutes();
    }

    /**  
     * This is a method used
     * to collect the Car 
     */
    public void collectCar() 
    {

    }

    /**
     * This is a method used to
     * set the values for the fields
     */
    protected void setState(String getfromFile)
    {
        String[] data = getfromFile.split(",");
        this.Owner_Name=data[0];
        Intime=LocalDateTime.parse(data[1]);
        ChargeRate=Double.parseDouble(data[2]);
        changed_Rate=Boolean.parseBoolean(data[3]);
        // do assignments here
    }

    /**
     * This is a method used 
     * to saveData 
     */
    protected String saveData()
    {
        return Owner_Name + "," + Intime + "," + ChargeRate + "," + changed_Rate;
    }

    /**  
     * This is a method used
     * to display the Owner_Name, Registration_Number, Intime of the Class Car
     * @return Owner_Name, Registration_Number, Intime
     */
    public String toString()
    {
        return "\n" + "Owner_Name:" + Owner_Name + "\n";
    }
}// end of class