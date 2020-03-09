/**
 * This class is the IO_Support
 * of Parking Spot System
 * @author Bhagyasree Lavanya 
 * @version 1.8.0_101
 */
/**
 * Imporing classes for util, IO
 * and LocalDate
 */
import java.util.*;
import java.io.*;
import java.time.LocalDate;

/**
 * This method is used to validate String,
 * int, double, date
 */
public class IO_Support
{
    //Declaring instance of Scanner Class to ask user for input
    private static Scanner in = new Scanner(System.in);

    /**
     * This method validates the String input from the user
     * and ask to enter it again if it fails to
     * validate
     * @param prompt is the argument passed from the
     * User-Interface Class
     */
    public static String getString(String prompt)
    {
        String s = "";
        while(true)
        {
            try {
                System.out.print(prompt + " ");
                //Asking for user input
                s = in.nextLine();
                s = s.replaceAll("\\s+","");
                if(s.trim().length() == 0 || s.contains(","))
                {
                    IO_Support.println("Not a valid String");
                }
                else
                    break;
            }
            catch(Exception ex)
            {
                IO_Support.println("Not a valid String");
            }
        }
        return s;
    }

    /**
     * This method validates the double input from the user
     * and ask to enter it again if it fails to
     * validate
     * @param prompt is the argument passed from the
     * User-Interface Class
     */
    public static Double getDouble(String prompt)
    {
        Double d = 0.00;
        while(true)
        {
            try
            {
                System.out.print(prompt + " ");
                //Asking for user input
                d = Double.parseDouble(in.nextLine());
                break;
            }
            catch(Exception e)
            {
                System.out.println("Not a valid Double");
            }
        }
        return d;  
    }

    /**
     * This method validates the integer input from the user
     * and ask to enter it again if it fails to
     * validate
     * @param prompt is the argument passed from the
     * User-Interface Class
     */
    public static Integer getInteger(String prompt)
    {
        Integer i = 0;
        while(true)
        {
            try
            {
                System.out.print(prompt + " ");
                //Asking for user input
                i = Integer.parseInt(in.nextLine());
                break;
            }
            catch(Exception e)
            {
                System.out.println("Not a valid Integer");
            }
        }
        return i;  
    }

    /**
     * This method is used to print the String
     * passed as an argument in the method
     * @param toPrint is the String to be printed
     * as passed from the User-Interface class
     */
    public static void println(String toPrint)
    {
        System.out.println(toPrint);
    }

    /**
     * This method is used to save data into a file 
     * specified by filename
     * @param fileName is the name of the file 
     * @param data is the actual arrayList to be
     * stored into a file
     * @throws IOException to check for any errors
     * related to file writing
     */
    public static void saveData(String fileName, ArrayList<String> data) throws IOException
    {
        PrintWriter pw = new PrintWriter(new FileWriter(fileName));
        for(String s : data)
        {
            pw.println(s);
        }
        pw.close();
    }

    /**
     * This method is used to read data from a file 
     * specified by filename
     * @param fileName is the name of the file 
     * @throws IOException to check for any errors
     * related to file reading
     */
    public static ArrayList<String> readData(String fileName) throws IOException
    {
        ArrayList<String> data = new ArrayList<String>();
        BufferedReader in = new BufferedReader(new FileReader(fileName));

        String temp = in.readLine(); 
        while (temp != null)
        {
            data.add(temp);
            temp = in.readLine();
        }
        in.close();
        return data;
    }
    
    /**
     * This method is used to 
     * Write data to file 
     * @param fileName 
     * @param data ArrayList<String>
     */
    public static void writeToLogFile(String fileName,String data)
    {
        try
        {
            PrintWriter pw = new PrintWriter(new FileWriter(fileName,true));
            pw.println(data);
            pw.close();
        }
        catch(Exception ex)
        {
            System.out.println("");
        }
    }
    
    /**
     * This method is ErrorLog is used to 
     * log messages 
     * @param ErrorMessage String
     */
    public static void ErrorLog(String ErrorMessage)
    {
        try
        {
            PrintWriter pw = new PrintWriter(new FileWriter("ErrorLog.txt"));
            pw.append(ErrorMessage);
            pw.println(ErrorMessage);
            pw.close();
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
    }
}//end of class