/**
 * This class is the Bicycle
 * of Parking Spot System
 * @author Bhagyasree Lavanya 
 * @version 1.8.0_101
 */
public class Bicycle extends Vehicle
{
    /**   
     * Declaring an instance of Bicycle Class  
     */
    protected double ChargeRate;
    /**
     * Constructor for objects of class Bicycle
     */
    public Bicycle(String myData,boolean value) throws VehicleClassException 
    {  
        try
        {
            String[] data = myData.split(",");
            int temp = data[0].length()+ 1;
            super.setState(myData.substring(temp,myData.length()));
        }
        catch(Exception ex)
        {
            throw new VehicleClassException("Error Occured while initializing spot attributes");
        }
    }
    
    /**
     * Constructor with one parameter namely String is implemented to assign Owner_Name.
     * @param Owner_Name String
     */
    public Bicycle(String Owner_Name) throws VehicleClassException
    {
        super(Owner_Name);
        if(Owner_Name == null || Owner_Name.trim().length()==0)
        {
           throw new VehicleClassException("Owner_Name must not be null");
        }
    }
    
    /**  
     * This is a method used 
     * to get the Fees
     */
    public double getFee()
    {
        return 0;
    }

    /**
     * This method is used to save Bicycle Data
     * @param data to be saved 
     */
    public String saveData()
    {
        String B = "Vehicle_Type-B" + "," + super.getOwner_Name() + "," + super.saveData();
        /*myData.add(B);*/
        return B;
    }

    /**  
     * This is a method used
     * to display the Vehicle_Type from the super class
     */
    public String toString()
    {
        String temp = super.toString() + " The Parked_Vehicle type is a BICYCLE" + "\n" +
            "Owner_Name:" + super.getOwner_Name();
        return temp;
    }//end of class
}