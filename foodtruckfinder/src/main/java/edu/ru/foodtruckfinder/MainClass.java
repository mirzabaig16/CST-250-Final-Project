
package edu.ru.foodtruckfinder;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

public class MainClass
{

  public static void clearScreen ()
  {
    System.out.print ("\033[H\033[2J");
    System.out.flush ();
  }
  public static void main (String[]args)
  {

    Scanner console = new Scanner (System.in);
    //Exam exam;
    FoodTruckMain foodTruck;
    Scanner scanner;
    File file;
    boolean flag = true;





    System.out.println
      ("Please enter Food Truck Data file name to be loaded in memory:");
    String fileName = console.nextLine ();
    file = new File ("C:\\Users\\Tayyab\\final-project\\CST-250-Final-Project\\foodtruckfinder\\src\\main\\java\\edu\\ru\\foodtruckfinder\\"+fileName);
    try
    {
      scanner = new Scanner (file);

      foodTruck = new FoodTruckMain (scanner);

      System.out.println ("Create an Data from an input data file - DONE");
      System.out.println ();


      do
	{
	  System.out.println ("Press <Enter> to continue...");
	  try
	  {
	    System.in.read ();
	  }
	  catch (Exception e)
	  {

	  }
	  clearScreen ();
	  System.out.println ("--- Welcome ---");
	  System.out.println ("--- Main Menu ---");
	  System.out.println ("Please Select From Given Menu");
	  System.out.println ("1- Create New Food Truck.");
	  System.out.println ("2- Search Food Truck");
	  // System.out.println("3- Remove questions interactively");
	  //System.out.println("4- Reorder questions");
	  //   System.out.println("5- Reorder answers");
	  System.out.println ("3- Save into file");
	  System.out.println ("4- Print All");
	  System.out.println ("5- Print List");
	  System.out.println ("6- Quite");


	  System.out.println ("Select Option");
	  int option = console.nextInt ();
	  console.nextLine ();
	  switch (option)
	    {
	    case 1:
	      foodTruck.AddFoodTruck ();
	      break;
	    case 2:
	      clearScreen ();
	      System.out.println ("--- Search Food Truck ---");
	      System.out.println ("Main Menu -> Search Food Truck\n");
	      System.out.println ("Please Select From Given Menu");
	      System.out.println ("1- Search By Name");
	      System.out.println ("2- Search By Cuisine");
	      System.out.println ("3- Search By Menu");
	      System.out.println ("4- Search Near By");
	      // System.out.println ("4- Go Back to Main Menu");

	      System.out.println ("Select Option");
	      int sub_option = console.nextInt ();
	      console.nextLine();
	      String keyword;
	      switch (sub_option)
		{
		case 1:
		  clearScreen ();
		  System.out.println ("Enter Search Word");
		  keyword = console.nextLine ();
		  foodTruck.SearchByName (keyword);
		  break;
		case 2:
		  clearScreen ();
		  System.out.println ("Enter Search Word");
		  keyword = console.nextLine ();
		  foodTruck.SearchByCuisine (keyword);

		  break;
		case 3:
		  clearScreen ();
		  System.out.println ("--- Search By Menu ---");
		  System.out.println
		    ("Main Menu -> Search Food Truck -> Search By Menu\n");
		  System.out.println ("Please Select From Given Menu");
		  System.out.println ("1- Available Now");
		  System.out.println ("2- All");
		  System.out.println ("Select Option");
		  int sub_opt = console.nextInt ();
		  console.nextLine ();
		  System.out.println ("Enter Search Word");
		  keyword = console.nextLine ();
		  switch (sub_opt)
		    {
		    case 1:
		        foodTruck.SearchByMenuNow(keyword);
		      break;
		    case 2:
		      foodTruck.SearchByMenu (keyword);
		      break;
		   
		      default:
		      break;
		    }
	      break;
	      case 4:
	           System.out.println ("Enter Latitiude : ");
		  String lat = console.nextLine ();
		  System.out.println ("Enter Langitude : ");
		  String longitude = console.nextLine ();
	          foodTruck.SearchNearBy(lat, longitude);
	          break;
	    default:
	      System.out.println ("other selected");
	      break;
	    }
	  break;
case 3:
	  //File file_ = new File (fileName);
	  FileWriter fw = new FileWriter ("C:\\Users\\Tayyab\\final-project\\CST-250-Final-Project\\foodtruckfinder\\src\\main\\java\\edu\\ru\\foodtruckfinder\\"+fileName);
	  foodTruck.save (fw);
	  fw.close ();
	  System.out.println ("Successfully wrote to the file.");
	  break;
case 4:
	  foodTruck.printAll ();
	  break;
case 5:
	  foodTruck.PrintNames ();
	  break;
case 6:
	  flag = false;
	  break;
default:
	  System.out.println ("other selected");
	  break;
	}

      }
      while (flag);
      scanner.close ();
    }
    catch (FileNotFoundException e)
    {
      System.out.println ("Input file is not found. Exiting program!");
    }
    catch (Exception e)
    {
      e.printStackTrace ();
    }

  }
}
