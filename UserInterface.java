/**
 * This class is the actual User interface
 * of Parking Spot System
 * @author Bhagyasree Lavanya 
 * @version 1.8.0_101
 */
/**
 * Importing classes for LocalDate, ArrayList
 * & DateTimeFormatter
 */
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
public class UserInterface
{
    /**   
     * Declaring an instance of CarPark Class  
     */
    private CarPark myCarPark;  
    /**
     * Constructor for objects of class UserInterface
     */
    public UserInterface(CarPark myCarPark)
    {
        this.myCarPark = myCarPark;
    }

    /**   
     * This method calls the menu() method 
     * which asks the user for his
     * input   
     */
    public void run()
    {
        while(true)
            switch (menu())
            {
                case 1:
                addSpot();
                break;
                case 2:
                deleteSpot();
                break;
                case 3:
                listOccupiedSpots();
                break;
                case 4:
                parkVehicle();
                break;
                case 5:
                collectVehicle();
                break;
                case 6:
                findVehicle();
                break;
                case 7:
                moveVehicle();
                break;
                case 8:
                return;
                default:
                IO_Support.println("Entered an Invalid Option");
                break;
            }
    }

    /**  
     * This method asks the user for the input  
     * which is directed to the switch case above   
     */
    public int menu()
    {
        IO_Support.println("1. AddSpot");
        IO_Support.println("2. DeleteSpot");
        IO_Support.println("3. ListOccupiedSpots");
        IO_Support.println("4. ParkVehicle");
        IO_Support.println("5. CollectVehicle");
        IO_Support.println("6. FindVehicle");
        IO_Support.println("7. MoveVehicle");
        IO_Support.println("8. Exit");
        return IO_Support.getInteger("Select Option");
    }

    /**  
     * This method asks the user for the input  
     * to add the Spot by Spot_Name and Hourly_Rate  
     */
    public void addSpot() 
    {
        IO_Support.println("Add Vehicle to the Parking Spot");
        String spotID=null;
        double Hourly_Rate;
        IO_Support.println("Create new Spot");
        IO_Support.println("");
        spotID=IO_Support.getString("Enter Spot_ID:");

        if(spotID.trim().length()==0)
        {
            IO_Support.println("Spot is not Found");
            return;
        }

        if(myCarPark.findSpot(spotID) != null)
        {
            IO_Support.writeToLogFile("LogFile.txt","Spot is Exist already");
            return;
        }
        Hourly_Rate=IO_Support.getDouble("Enter Hourly_Rate:");
        if(Hourly_Rate<1)
        {
            IO_Support.println("Hourly Rate should not be less than 1");
            return;
        }

        try
        {
            myCarPark.addSpot(new Spot(spotID,Hourly_Rate));
            IO_Support.println("Spot Added Successfully");
        }
        catch(SpotClassException ex)
        {
            IO_Support.writeToLogFile("LogFile.txt",ex.getMessage());
        }
    }

    /**  
     * This method asks the user for the input  
     * to delete the Spot 
     */
    public void deleteSpot()
    {
        try
        {
            IO_Support.println("Delete Car from the Parking Spot");
            String find;
            find=IO_Support.getString("Enter the Spot you wish to delete");
            Spot s=myCarPark.findSpot(find);
            IO_Support.println(s.toString());
            if(s==null)
            {
                IO_Support.println("Spot not Found");
            }
            else if(s.isOccupied())
            {
                IO_Support.println("Selected spot contains car so cannot delete spot");
            }
            else
            {
                myCarPark.removeSpot(s);
                IO_Support.println("Spot has been Deleted Successfully");
            }
        }
        catch(SpotClassException ex)
        {
            IO_Support.writeToLogFile("LogFile.txt",ex.getMessage());
        }
    }

    /**  
     * This method asks the user for the input  
     * to list all the Occupied_Spots 
     */
    public void listOccupiedSpots()
    {
        if (myCarPark.getOccupiedSpots().isEmpty())
        {
            IO_Support.println("No Spots are Occupied");
        }
        else
        {
            int i=0;
            for(Spot s:myCarPark.getOccupiedSpots())
            {
                if(i==0)
                {
                    IO_Support.println("Spot_ID\tArrival_Time\tArrival_Date\t\tTime_IN\tRegistration\tFee\n");
                    i++;
                }
                spotReport(s);
                /*IO_Support.println(s.toString());*/
            }
        }
    }

    /**  
     * This method asks the user for the input  
     * to Park the Vehicle in the Parking_Spot
     */
    private void parkVehicle()
    {
        try
        {
            String Parking_Vehicle;
            Vehicle vehicle;
            //String OwnerName;
            //String RegistrationNumber;
            IO_Support.println("Add Vehicle to the Parking_Spot");
            IO_Support.println("1.Car");
            IO_Support.println("2.Bicycle");
            IO_Support.println("");
            Parking_Vehicle = IO_Support.getString("Select an Option you wish to Park the Vehicle in the Parking_Spot:");
            if(Parking_Vehicle.equals("1"))
            {
                String Owner_Name=IO_Support.getString("Enter Owner_Name of the Car:");
                String Registration_Number = IO_Support.getString("Enter Registration_Number of the Car:");
                try
                {
                    vehicle= new Car(Owner_Name,Registration_Number);
                }
                catch(VehicleClassException ex)
                {
                    IO_Support.writeToLogFile("LogFile.txt",ex.getMessage());
                } 
                int hour;
                Vehicle newCar= new Car(Owner_Name,Registration_Number);
                hour = IO_Support.getInteger("Enter number of hours you want to park the Car in the Parking_Spot:");
                newCar.setHoursParked(hour);
                // dipslay all spots and get selection form the user
                IO_Support.println("Available spots to Park the Car are:");
                for(Spot s : myCarPark.getAvailableSpots())
                    IO_Support.println(s.getspotID());
                String spot_ID = IO_Support.getString("Select the Spot you wish to Park the Car");
                Spot newSpot = myCarPark.findSpot(spot_ID);
                if (newSpot == null)
                {
                    IO_Support.println("Spot not found");
                    return;
                }
                else 
                {
                    newSpot.parkVehicle(newCar);
                    IO_Support.println("Car parked Successfuly in the Parking_Spot");
                }
            }

            if( Parking_Vehicle.equals("2"))
            {
                String Owner_Name = IO_Support.getString("Enter Owner_Name of the Bicycle:");
                try
                {
                    vehicle= new Bicycle(Owner_Name);
                }
                catch(VehicleClassException ex)
                {
                    IO_Support.writeToLogFile("LogFile.txt",ex.getMessage());
                }
                int hour;
                Vehicle newBicycle= new Bicycle(Owner_Name);
                hour = IO_Support.getInteger("Enter number of hours you want to park the Bicycle in the Parking_Spot:");
                newBicycle.setHoursParked(hour);
                // dipslay all spots and get selection form the user
                IO_Support.println("Available spots:");
                for(Spot s : myCarPark.getAvailableSpots())
                    IO_Support.println(s.getspotID());
                String spot_ID = IO_Support.getString("Select the Spot you wish to park the Bicycle");
                Spot newSpot = myCarPark.findSpot(spot_ID);
                if (newSpot == null)
                {
                    IO_Support.println("Spot not found");
                    return;
                }
                else 
                {
                    newSpot.parkVehicle(newBicycle);
                    IO_Support.println("Bicycle parked Successfuly in the Parking_Spot");
                }
            }
        }
        catch(VehicleClassException ex)
        {
            IO_Support.writeToLogFile("LogFile.txt",ex.getMessage());
        } 
    }

    /**  
     * This method asks the user for the input  
     * to Collect the Vehicle from the Parking_Spot
     */
    public void collectVehicle()
    {
        IO_Support.println("Collect Vehicle from the Parking Spot");
        String Owner_Name;
        Owner_Name = IO_Support.getString("Enter the Owner_Name of the Vehicle");
        ArrayList<Spot> vehicle = myCarPark.getSpotbyOwnerName(Owner_Name);
        if(vehicle==null)
        {
            IO_Support.println("Couldn't collect the Vehicle: Invalid Owner_Name");
        }
        else
        {   
            for(Spot s:vehicle)
            {
                IO_Support.println(vehicle.toString());
            }
            String spotID=IO_Support.getString("Enter the spot you wish to Remove:");
            Spot s=myCarPark.findSpot(spotID);
            Vehicle v=s.getVehicle();
            s.collectVehicle(v);
            IO_Support.println("Vehicle Collected Successfully");
        }
    }

    /**  
     * This method asks the user for the input  
     * to Find the Vehicle from the Parking_Spot
     */
    public void findVehicle()
    {
        IO_Support.println("Finding the Vehicle from the Parking Spot");
        String Owner_Name;
        Owner_Name = IO_Support.getString("Enter Owner_Name of the Vehicle:");
        ArrayList<Spot> v = myCarPark.getSpotbyOwnerName(Owner_Name);
        if(v==null)
        {
            IO_Support.println("Vehicle not Found");
        }
        else
        {   for(Spot s:v)
            {
                IO_Support.println(v.toString());
            }
        }
    }

    /**  
     * This method asks the user for the input  
     * to Move the Vehicle from one Parking_Spot to other Parking_Spot
     */
    public void moveVehicle()
    {
        IO_Support.println("Move Vehicle to the Available Parking_Spot");
        String Owner_Name;
        Owner_Name=IO_Support.getString("Enter the Owner_Name you wish to move the Vehicle from old spot to new spot:");
        ArrayList<Spot> vehicle = myCarPark.getSpotbyOwnerName(Owner_Name);
        for(Spot s: myCarPark.getOccupiedSpots())
        {
            IO_Support.println(s.toString());   
        }
        String spotID = IO_Support.getString("Select the Spot_ID to move the Vehicle:");
        Spot oldSpot= myCarPark.findSpot(spotID);
        if (vehicle == null)
        {
            IO_Support.println("Entered an Invalid Registration_Number");
        }
        else
        {
            IO_Support.println("Available_Spots are:");
            for(Spot s : myCarPark.getAvailableSpots())
            {
                IO_Support.println(s.getspotID());
            }
            String spot_ID = IO_Support.getString("Select New Spot:");
            Spot newSpot = myCarPark.findSpot(spot_ID);
            IO_Support.println("newSpot :"+newSpot);
            if (newSpot == null)
            {
                IO_Support.println("Spot not found");
                return;
            }
            myCarPark.moveVehicle(oldSpot,newSpot);
            IO_Support.println("Vehicle"+" moved to "+spotID+" sucessfully");
        }
    }

    /**  
     * This method is used  
     * to display the Spot Report of the Parking_Spot
     */
    private void spotReport(Spot s)
    {
        String spot_ID = s.getspotID();
        LocalDateTime dt = s.getIntime();
        LocalDate date=dt.toLocalDate();
        LocalTime time=dt.toLocalTime();
        DateTimeFormatter formatter1=DateTimeFormatter.ofPattern("MMMM dd, YYYY");
        String arrivaldateParked=date.format(formatter1);
        DateTimeFormatter formatter2=DateTimeFormatter.ofPattern("KK:mm a");
        String arrivaltimeParked=time.format(formatter2);
        int parkedtime = s.getVehicle().gettimeParked();
        double fee = s.getVehicle().getFee();
        Vehicle v=s.getVehicle();
        String registration_number=null;
        if(v instanceof Car)
        {
            Car x=(Car)v;
            registration_number=x.getRegistration_Number();
        }
        IO_Support.println(spot_ID+"\t"+arrivaltimeParked+"\t"+arrivaldateParked+"\t"+parkedtime+"\t"+registration_number+"\t"+"\t" + fee);
    }

    /**  
     * This method asks the user for the input  
     * to come out from the terminal
     */
    public void exit()
    {
        System.exit(0);
    }
}// end of class