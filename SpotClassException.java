/**
 * This is SpotClassException which
 * extends Exception class to handle the error
 * which occured in the Clinic Class with the methods 
 * that throws this exception.
 * @author Bhagyasree Lavanya 
 * @version 1.8.0_101
 */
public class SpotClassException extends Exception
{
    public SpotClassException(String errorFound) 
    {
        super(errorFound);
    }
}//endofclass