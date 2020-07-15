import java.util.*;
import java.io.*;

public class MyUtils
{
   /**
   * Example usage of MyUtils methods
   *
   * @param args unused
   */
   public static void main(String[] args)
   {  
      Scanner input = new Scanner(System.in);    
      int userInt = getNumber(input, "Enter a number between 1 and 10: ", 1, 10);
      Scanner fileScan = getInputFile(input, "Input file name: ", 
                                      "File not found. Try again: "); 
      PrintStream stream = getOutputFile(input, "Output file name: ", 
                                         "Error opening file. Try again: ");
   }
      
  /**
    * Method to get a in integer value in a range robustly from the user.
    * 
    * @param input The Scanner on the console, to get user input
    * @param prompt The prompt to display to the user
    * @param min The minimum valid response possible
    * @param max The maximum valid response possible
    * @return A valid integer within the range [min.. max] inclusive
    */
   public static int getNumber(Scanner input, String prompt, int min, int max)
   {
      while (true)
      {
         System.out.print(prompt); 
         if (!input.hasNextInt())
         {
            input.nextLine();
            System.out.println("You must enter an *integer* between "+min+" and "+max+"."); 
         }
         else
         {
            int value = input.nextInt();
            input.nextLine(); //Clear the Scanner buffer
            if ( value > max || value < min )
            {
               System.out.println("Your number needs to be between "+min+" and "+max+".");
            }
            else
            {
               return value;      
            }
         }
      }
   }
      
   /**
    *  Method to open a Scanner on on a file with the given file name without
    *  throwing an error. It is not expected to reprompt the user. 
    *
    *  @param filename The name of the file to open a Scanner on
    *  @return A valid Scanner on the given file if it exists. If not, this will return null
    */
   public static Scanner getFileScanner(String filename) 
   {
      File f = new File(filename);
      Scanner fileScan = null;
      try
      {
         fileScan = new Scanner(f); 
      }
      catch (FileNotFoundException e)
      {
         //System.out.println(error + filename);
      }
      return fileScan;
   
   }
  
   
  /**
    *  Method to open a Scanner on on a file with the given file name without
    *  throwing an error. This method reprompts the user until a valid file 
    *  name is entered
    *
    *  @param console The Scanner on the console to prompt the user for answers
    *  @param prompt The prompt to display to the user, asking for information
    *  @param error The error message to display to the user if there is a problem with 
    *               opening the file. 
    *  @return A valid Scanner on the given file if it exists. If not, this will return null
    */ 	
   public static Scanner getInputFile(Scanner console, String prompt, String error)
	{	
		Scanner fileScan = null;
      System.out.print(prompt);  
      while (fileScan == null)
      {
   		String fileName = console.nextLine(); 
			fileScan = getFileScanner(fileName); 
         if (fileScan == null)
         {
            System.out.print(error);
         }
          
		}
   	return fileScan; 	
	}

        
   /**
    *  Method to open a PrintStream on on a file with the given file name without
    *  throwing an error. It is not expected to reprompt the user. 
    *
    *  @param filename The name of the file to open a PrintStream on
    *  @param A valid PrintStream on the given file if it exists. If not, this will return null
    */
   public static PrintStream createOutputFile(String filename) 
   {
      File f = new File(filename);
      PrintStream stream = null;
      try
      {
         stream = new PrintStream(f); 
      }
      catch (FileNotFoundException e)
      {
         System.out.println("Error opening file " + filename);
      }
      return stream;
   
   }
   
   /**
    *  Method to open a PrintStream on on a file with the given file name without
    *  throwing an error. This method reprompts the user until a valid file 
    *  name is entered
    *
    *  @param console The Scanner on the console to prompt the user for answers
    *  @param prompt The prompt to display to the user, asking for information
    *  @param error The error message to display to the user if there is a problem with 
    *               opening the file. 
    *  @param A valid PrintStream on the given file if it exists. If not, this will return null
    */
 	public static PrintStream getOutputFile(Scanner console, String prompt, String error) 
	{
      PrintStream output = null;
      System.out.print(prompt);  
      while (output == null)
      {
   		String fileName = console.nextLine(); 
			output = createOutputFile(fileName); 
         if (output == null)
         {
            System.out.print(error);
         }
          
		}
   	return output; 	
	}


}
