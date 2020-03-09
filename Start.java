/**
 * This is the Starting point of
 * Parking Spot System where the execution begins
 * @author Bhagyasree Lavanya
 * @@version (1.8.0_101)
 */ 
import java.io.*;
public class Start
{
    public static void main(String[] args)
    {
        //Creating an instance of CarPark and User-Interface classes
        CarPark c = new CarPark();
        String fileName="";
        //Using a try-catch mechanism for indicating errors if occurred
        try
        {
            c.readAllSpots(); 
        }

        catch(CarParkException ex)
        {
            IO_Support.writeToLogFile("LogFile.txt",ex.getMessage());
            System.out.println(ex);
        }
        catch(FileNotFoundException ex)
        {
            IO_Support.writeToLogFile("LogFile.txt",ex.getMessage());
            fileName=IO_Support.getString("|| Entered an Invalid File_Name ||");
            if(fileName.equalsIgnoreCase("exit"))
            {
                System.exit(1);
            }
        } 
        catch(Exception ex)
        {
            IO_Support.writeToLogFile("LogFile.txt",ex.getMessage());
        }

        try
        {
            UserInterface UI = new UserInterface(c);
            UI.run();
            c.writeAllSpots();
        }
        catch (Exception ex)
        {
            System.out.println(ex);
        }
    }
}// end of class