/**
 * Importing classes for LocalDate,
 * ArrayList
 */
import java.io.*;
import java.util.*;
import java.util.ArrayList;
/**
 * This class is the CarPark
 * of Parking Spot System
 * @author Bhagyasree Lavanya 
 * @version 1.8.0_101
 */
public class CarPark
{
    private ArrayList<Spot>spotCollection;
    private static int errorCount=0;
    /**
     * Constructor for objects of class CarPark
     */
    public CarPark() //throws CarParkException
    {
        spotCollection = new ArrayList<Spot>();
        //readAllSpots();
    }

    /**  
     * This booelean method used
     * to add the Spot to the ArrayList 
     */
    public boolean addSpot(Spot newSpot) throws SpotClassException
    {
        try
        {
            if(spotCollection.add(newSpot))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        catch(Exception ex)
        {
            throw new SpotClassException("Problem found in the addSpot() method");
        }
    }

    /**  
     * This booelean method used
     * to delete the Spot from the ArrayList 
     */
    public boolean removeSpot(Spot removeSpot) throws SpotClassException
    {
        try
        {
            if (spotCollection.remove(removeSpot)) 
            {
                return true;
            } 
            else 
            {
                return false;
            }
        }
        catch(Exception ex)
        {
            throw new SpotClassException("Problem found in the removeSpot() method");
        }
    }

    /**  
     * This is a method used
     * to Find the Spot from the ArrayList 
     */
    public Spot findSpot(String spotID) 
    {
        for(Spot spot : spotCollection) 
        {
            if (spot.getspotID().equals(spotID)) 
            {
                IO_Support.println("Spot Found: " + spotID);
                return spot;
            }
        }
        return null;
    }

    /**  
     * This is a method used 
     * to get the Available_Spots from the ArrayList 
     */
    public ArrayList<Spot> getAvailableSpots()
    {
        ArrayList<Spot> AvailableSpots = new ArrayList<Spot>();
        for (Spot s : spotCollection) 
        {
            if (!s.isOccupied()) 
            {
                AvailableSpots.add(s);
            }
        }
        return AvailableSpots;
    }

    /**  
     * This is a method used 
     * to get the Occupied_Spots from the ArrayList 
     * @return spotCollection
     */
    public ArrayList<Spot> getOccupiedSpots()
    {
        ArrayList<Spot> OccupiedSpots=new ArrayList<Spot>();
        for(Spot s: spotCollection)
        {
            if(s.isOccupied())
            {
                OccupiedSpots.add(s);
            }
        }
		Collections.sort(spotCollection);
        return OccupiedSpots;
    }

    /**  
     * This is a method used
     * to get all Spots whose Owner_Name are similar
     */
    public ArrayList<Spot> getSpotbyOwnerName(String owner_name) 
    {
        ArrayList<Spot> newspot=new ArrayList<Spot>();
        for (Spot s : spotCollection) 
        {
            if (s.isOccupied() && s.getVehicle().getOwner_Name().equals(owner_name)) 
            {
                newspot.add(s);
            } 
        }
        return newspot;
    }

    /**  
     * This is a boolean method used
     * to move the Vehicle from oldSpot to newSpot
     */
    public boolean moveVehicle(Spot oldSpot,Spot newSpot)
    {
        Vehicle v=oldSpot.getVehicle();
        newSpot.parkVehicle(v);
        oldSpot.collectVehicle();
        return true;
    }

    /**  
     * This is a method used
     * to Find the Vehicle based on the Owner_Name Criteria
     */
    public Vehicle findVehiclebyCriteria(String Qwner_Name) 
    {
        for (Spot spot : spotCollection) 
        {
            if (spot.isOccupied() && spot.getVehicle().getOwner_Name().equals(Qwner_Name)) 
            {
                return spot.getVehicle();
            } 
        }
        return null;
    }

    /**  
     * This is a boolean method used 
     * to Park the Vehicle using the ArrayList
     */
    public boolean parkVehicle(Vehicle addVehicle)
    {
        for (Spot spot : spotCollection) 
        {
            if (!spot.isOccupied()) 
            {
                spot.parkVehicle(addVehicle);
                return true;
            }
        }
        return false;
    }

    /**  
     * This is a boolean method used 
     * to get the Parked Spot of the Vehicle using the ArrayList
     */
    public Spot getVehicleSpot(Vehicle getVehicle)
    {
        for(Spot s:spotCollection)
        {
            if(s.isOccupied() && s.getVehicle().equals(getVehicle))
            {
                return s;
            }
        }
        return null;
    }

    /**  
     * This is a method used 
     * to Write all the Spots 
     */
    public void writeAllSpots() throws Exception
    {
        ArrayList<String> data= new ArrayList<String>();
        for(Spot s:spotCollection)
        {
            s.saveSpot(data);
        }
        IO_Support.saveData("SavedData.txt",data);
    }

    /**  
     * This is a method used 
     * to read all the Spots 
     */
    public void readAllSpots() throws CarParkException, FileNotFoundException, Exception
    {
        ArrayList<String> data = IO_Support.readData("SavedData.txt");
        for(String s : data)
        {
            try
            {
                spotCollection.add(new Spot(s));
            }
            catch(VehicleClassException ex)
            {
                errorCount++;
                IO_Support.writeToLogFile("LogFile.txt",ex.getMessage());
            }

            catch(SpotClassException ex)
            {
                errorCount++;
                IO_Support.writeToLogFile("LogFile.txt",ex.getMessage());
            }
        }
        if(errorCount>0)
        {
            throw new CarParkException("Error Occured");
        }
    }

    /**  
     * This is a toString() method used 
     * to display the Spot_Collection of the ArrayList of the Class CarPark
     * @return spotCollection
     */
    public String toString()
    {
        return "Spot_Collection:" + spotCollection;
    }
}// end of class