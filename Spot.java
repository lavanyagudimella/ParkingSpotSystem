/**
 * This class is the Spot
 * of Parking Spot System
 * @author Bhagyasree Lavanya 
 * @version 1.8.0_101
 */
/**
 * Importing classes for LocalDate
 * & DateTimeFormatter
 */
import java.time.*;
import java.text.*;
import java.util.*;
import java.time.format.*;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Spot implements Comparable<Spot>
{
    /**   
     * Declaring an instances of Spot Class  
     */
    private String Name;
    private String spotID;
    private Double Hourly_Rate;
    private LocalDateTime Intime;
    private Vehicle Parked_Vehicle;
    private double Current_Charges;
    private boolean Spot_Occupied;
    private String Vehicle_Type;
    /**
     * Constructor for objects of class Spot without parameters
     */
    public Spot()
    {
        this.spotID=null;
        this.Hourly_Rate=0.0;
    }

    /**
     * Constructor for objects of class Spot with parameters
     */
    public Spot(String savedData) throws SpotClassException,VehicleClassException
    {
        try
        {
            String[] list = savedData.split(",");
            if(list[0].trim().length() == 0 || Double.parseDouble(list[1])<1)
            {
                throw new SpotClassException(" Error in Spot Data");
            }
            else
            {
                spotID = list[0];
                Hourly_Rate= Double.parseDouble(list[1]);
                Intime=LocalDateTime.parse(list[2]);
                if (list.length > 3)
                {
                    int temp=list[0].length()+list[1].length()+list[2].length()+list[3].length()+list[4].length()+5;
                    String s=savedData.substring(temp,savedData.length());
                    if(list[4].equals("Vehicle_Type-C"))
                    {
                        Vehicle_Type=list[3];
                        Parked_Vehicle=new Car(s);
                    }
                    else if(list[4].equals("Vehicle_Type-B"))
                    {
                        Vehicle_Type=list[3];
                        Parked_Vehicle=new Bicycle(s);
                    }
                }
            }
        }
        catch(Throwable ex)
        {
            throw new SpotClassException("Problem in Spot(String data) for inappropraiate values");
        }
    }

    /**
     * Constructor for objects of class Spot with Parameters
     * @param Name String - String contains the Name
     * @param spotID String - String contains the spotID
     * @param Hourly_Rate Double - Double contains the Hourly_Rate
     * @param Intime LocalTimeDate - LocalTimeDate contains the Intime
     * @param Parked_Car Car - Car contains the Parked_Car
     * @param Current_Charges double - double contains the Current_Charges
     * @param Spot_Occupies boolean - boolean contains the Spot_Occupied
     */
    public Spot(String spotID, Double Hourly_Rate) throws SpotClassException
    {
        try
        {
            this.Name = Name;
            this.spotID = spotID;
            this.Hourly_Rate = Hourly_Rate;
            this.Intime = LocalDateTime.now();
            this.Current_Charges=Current_Charges;
            Spot_Occupied = false;
            Parked_Vehicle= null;
        }
        catch(Exception ex)
        {
            throw new SpotClassException("Problem Found in Constructor of the Class Spot with Parameters");
        }
    }

    /**  
     * This is a get() method used 
     * to get the Name of the Spot
     * @return Name
     */
    public String getName()
    {
        return Name;
    }

    /**  
     * This is a set() method used 
     * to set the Spot_ID of the Spot
     * @param String spot_ID
     */
    public void setspotID(String spot_id)
    {
        this.spotID=spot_id;
    }

    /**  
     * This is a get() method used 
     * to get the ID of the Spot
     * @return spotID
     */
    public String getspotID()
    {
        return spotID;
    }

    /**  
     * This is a get() method used 
     * to get the InTime of the Vehicle in the Spot
     * @return InTime
     */
    public LocalDateTime getIntime()
    {
        return Intime;
    }

    /**  
     * This is a boolean method used 
     * to check whether the Spot is Occupied or not
     */
    public boolean isOccupied()
    {
        if(Parked_Vehicle != null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**  
     * This is a get() method used 
     * to get the Parked_Vehicle from the Vehicle
     * @return Parked_Vehicle
     */
    public Vehicle getVehicle()
    {
        return Parked_Vehicle;
    }

    /**  
     * This is a boolean method used 
     * to check the Parked_Vehicle
     */
    public boolean parkVehicle(Vehicle temp)
    {
        if(temp==null)
        {
            return false;
        }
        else
        {
            Parked_Vehicle=temp;
            IO_Support.println("Details of the Parked_Vehicle" + Parked_Vehicle );
            Parked_Vehicle.setChargeRate(Hourly_Rate);
            return true;
        }
    }

    /**  
     * This is a method used 
     * to Collect the Vehicle from the Parking_Spot
     */
    public void collectVehicle()
    {
        Parked_Vehicle=null;
    }

    /**  
     * This is a method used 
     * to Collect the Vehicle from the Parking_Spot
     */
    public void collectVehicle(Vehicle removeVehicle)
    {
        Parked_Vehicle=null;
    }

    /**  
     * This is a method used 
     * to Move the Vehicle from the Parking_Spot
     */
    public double moveVehicle(Vehicle move_Vehicle) 
    {
        double currentRate = move_Vehicle.getChargeRate();
        LocalDateTime newtime_expired;
        if(Parked_Vehicle==null)
        {
            newtime_expired=move_Vehicle.expectedCollectionDateTime();
            if(LocalDateTime.now().isAfter(newtime_expired))
            {
                if(this.getHourly_Rate() < currentRate)
                {
                    currentRate=this.getHourly_Rate();
                    move_Vehicle.setChargeRate(currentRate);
                    Spot s=new Spot();
                    s.parkVehicle(move_Vehicle);
                    Parked_Vehicle=null;
                }
            }
            IO_Support.println("Current_Rate for the Vehicle is :" + currentRate);
        }
        return currentRate ; 
    }

    /**  
     * This is a method used 
     * to know the Time_Parked of the Parked_Vehicle
     */
    public int howlongParked()
    {
        return timeInMinutes();
    }

    /**  
     * This is a method used 
     * to know the Time_Parked in minutes of the Parked_Vehicle
     */
    public int timeInMinutes()
    {
        return (int)Duration.between(Intime,LocalDateTime.now()).toMinutes();
    }

    /**  
     * This is a method used 
     * to calculate the Hourly_Rate
     */
    public Double getHourly_Rate() 
    {
        return Hourly_Rate;
    }

    /**  
     * This is a method used 
     * to calculate the Current_Charges 
     */
    public double currentCharges() 
    {
        return howlongParked()*Hourly_Rate;
    }

    /**  
     * This is a method used 
     * to save the Spot details into the ArrayList
     */
    public void saveSpot(ArrayList<String> saveToMe) throws SpotClassException
    {
        try
        {
            saveToMe.add(spotID + "," + Hourly_Rate + ","+ Intime + "," + Parked_Vehicle.gettimeParked()+ "," + Parked_Vehicle.saveData() );
        }
        catch(Exception ex)
        {
            throw new SpotClassException("Error Found in the method saveSpot()");
        }
    }

    /**
     * This compareTo method compare One Spot to other Spot
     * @return Spot_ID
     */
    public int compareTo(Spot otherspotID)
    {
        return  this.spotID.compareTo(otherspotID.spotID);
    }    

    /**  
     * This is a toString() method used 
     * to display the details of the Spot class by Spot_ID, Hourly_Rate, Intime, Parked_Car of the Class Spot
     * @return spot_ID, Hourly_Rate, Intime
     */
    public String toString()
    {
        String Spot_Report;
        if(Parked_Vehicle==null)
        {
            Spot_Report="\n" + "\n" +
            "Spot_ID:" + spotID + "\n" + 
            "Spot is not occupied" + "\n" + 
            "Hourly_Rate:$" + Hourly_Rate;
        }
        else
        {
            Spot_Report="\n" + "\n" +
            "Spot_ID:" + spotID + "\n" + 
            "Details of the Parked_Vehicle:" + Parked_Vehicle + "\n" +
            "Hourly _Rate:$" + Hourly_Rate + "\n" +  
            "TimeIN:" + Parked_Vehicle.gettimeParked();     
        }
        return Spot_Report;
    }
} // end of class