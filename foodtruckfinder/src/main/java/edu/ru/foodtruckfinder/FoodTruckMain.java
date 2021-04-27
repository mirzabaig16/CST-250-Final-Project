
package edu.ru.foodtruckfinder;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;


public class FoodTruckMain
{

//      private String title;
  private ArrayList < FoodTruck > foodTrucks = new ArrayList < FoodTruck > ();

//      public String getTitle() {
//              return title;
//      }

//      public void setTitle(String title) {
//              this.title = title;
//      }

  public FoodTruckMain (Scanner sc)
  {
//          String projTitle = "";

//              if(s.hasNextLine()){
//                      projTitle = s.nextLine();
//              }

//              title = projTitle;
    String n = sc.nextLine ();

    int num = Integer.parseInt (n);
    for (int i = 0; i < num; i++)
      {
//                      String input = sc.nextLine();

//                      if(input.isEmpty())
//                              continue;

	FoodTruck foodTruck = null;

	  foodTruck = new FoodTruck (sc);
	  foodTrucks.add (foodTruck);


      }
  }

  public void AddFoodTruck (FoodTruck foodTruck)
  {
    this.foodTrucks.add (foodTruck);
  }

  public void deleteFoodTruck (int foodTruck)
  {
    this.foodTrucks.remove (foodTruck);
  }

  //add
//      public void AddTitle(){
//     System.out.println("Enter the Project Name and press <Enter> :");
//      this.title = keyboardScanner.nextLine();
//      }
  public void AddFoodTruck ()
  {
    FoodTruck foodTruck = new FoodTruck ();
    foodTruck.AddTruckName ();
    foodTruck.AddTruckFoodType ();
    boolean result = false;
    while (!result)
      {
	result = foodTruck.AddMenu ();
      }
    result = false;
    while (!result)
      {
	result = foodTruck.AddLocations ();
      }
    foodTrucks.add (foodTruck);
  }

  //print

  //Save
  public void save (FileWriter pw)
  {

//              pw.write("Hello\n\n");
    try
    {
      pw.write (this.foodTrucks.size () + "\n");
    for (FoodTruck foodTruck:foodTrucks)
	{
	  foodTruck.save (pw);
	}

    }
    catch (IOException e)
    {
      System.out.println ("An error occurred.");
      e.printStackTrace ();
    }

  }

  public void printAll ()
  {
    int n = 0;
  for (FoodTruck foodTruck:foodTrucks)
      {
	System.out.println ("Truck No : " + ++n);
	foodTruck.print ();
      }
  }

  public void PrintNames ()
  {

    int n = 0;
    System.out.println ("No.\t" + "Name\tCuisine");
  for (FoodTruck foodTruck:foodTrucks)
      {
//      System.out.println (++n + "\t" + foodTruck.getName () + "\t" +
//                          foodTruck.getCuisine ());
	foodTruck.printList (++n);
	//foodTruck.print();
      }
  }

  public void SearchByName (String key)
  {
    int count = 0;
  for (FoodTruck foodTruck:foodTrucks)
      {
	if (foodTruck.getName ().toLowerCase ().contains (key.toLowerCase ()))
	  {
	    count++;
	    // System.out.println(element);
	    System.out.println ("No : " + count);
	    foodTruck.print ();
	  }
      }
    System.out.println ("\n\n" + count + "/" + foodTrucks.size () +
			" Records Match.");
  }

  public void SearchByCuisine (String key)
  {
    int count = 0;
  for (FoodTruck foodTruck:foodTrucks)
      {
	if (foodTruck.getCuisine ().toLowerCase ().
	    contains (key.toLowerCase ()))
	  {
	    count++;
	    // System.out.println(element);
	    System.out.println ("No : " + count);
	    foodTruck.print ();
	  }
      }
    System.out.println ("\n\n" + count + "/" + foodTrucks.size () +
			" Records Match.");
  }



  public void SearchByMenu (String key)
  {
    int count = 0;
    int menuCount = 0;
    int flag = 0;
  for (FoodTruck foodTruck:foodTrucks)
      {
	flag = 0;
      for (FoodMenuItem item:foodTruck.getMenu ())
	  {
	    
	    if (item.getName ().toLowerCase ().contains (key.toLowerCase ()))
	      {
flag++;
		menuCount++;
		// System.out.println(element);
		//      System.out.println ("No : " + count);
		if (flag == 1)
		  {
		    count++;
		    foodTruck.printList (count);
		  }
		item.print ();

	
	      }
	  }
	  if(flag>0)
	  	System.out.println ("____________________________\n\n");
      }
    System.out.println ("" + menuCount + " menu item(s) matched in " +
			count + " Food Truck(s)");
  }

  public void SearchByMenuNow (String key)
  {
    Date date = new Date ();
    SimpleDateFormat parser = new SimpleDateFormat ("HH:mm");
// System.out.println(format.format(date));


    try
    {
      Date now = parser.parse (parser.format (date));
//System.out.println(now);
      int count = 0;
      int menuCount = 0;
      int flag = 0;
    for (FoodTruck foodTruck:foodTrucks)
	{
	  flag = 0;
	for (FoodMenuItem item:foodTruck.getMenu ())
	    {
	      if (item.getName ().toLowerCase ().
		  contains (key.toLowerCase ()))
		{
		for (DaysSlot slot:item.getDaySlots ())
		    {
		      String today =
			new SimpleDateFormat ("EEEE").format (date);
		      //System.out.println (today);
		      if (today.
			  toLowerCase ().equals (slot.getDay ().
						 toLowerCase ()))
			{
			for (String[]time:slot.getTimeSlots
			       ())
			    {
			      Date start = parser.parse (time[0]);
			      Date end = parser.parse (time[1]);
			      if (now.after (start) && now.before (end))
				{

				  flag++;
				  menuCount++;
				  if (flag == 1)
				    {
				      count++;
				      foodTruck.printList (count);
				    System.out.println("******** MENU ********");
				    }
				  item.printMenuList ();
				  System.out.println ("\t" + time[0] + " - " +
						      time[1]);
				  break;
				}
			    }
			}
		    }
		}
	    }
	  if (flag > 0)
	    {
	    for (FoodTruckLocation location:foodTruck.
		   getLocations
		   ())
		{

		  //////////////////////////
		  ////////////////////////
		  flag = 0;
		for (DaysSlot slot:location.getDaySlots ())
		    {
		      String today =
			new SimpleDateFormat ("EEEE").format (date);
		      //System.out.println (today);
		      if (today.
			  toLowerCase ().equals (slot.getDay ().
						 toLowerCase ()))
			{
			for (String[]time:slot.getTimeSlots
			       ())
			    {
			      Date start = parser.parse (time[0]);
			      Date end = parser.parse (time[1]);
			      if (now.after (start) && now.before (end))
				{
				    flag++;
				    if(flag==1)
				    System.out.println("******** LOCATION ********");
				    location.printList();
				    System.out.println("\t"+time[0]+" - "+time[1]);
				    break;
				}
			    }
			}
		    }
		  ////////////////////////
		  ////////////////////////
		  
		}
	      System.out.println
		("_______________________________________\n\n");
	    }
	}
      System.out.println ("" + menuCount + " menu item(s) matched in " +
			  count + " Food Truck(s)");
      //Date userDate = parser.parse(someOtherDate);

    }
    catch (ParseException e)
    {
      // Invalid date was entered
    }
  }
  
   public void SearchNearBy (String latitude, String longitude)
  {
    Date date = new Date ();
    SimpleDateFormat parser = new SimpleDateFormat ("HH:mm");
// System.out.println(format.format(date));


    try
    {
      Date now = parser.parse (parser.format (date));
//System.out.println(now);
      int count = 0;
      int menuCount = 0;
      int flag = 0;
    for (FoodTruck foodTruck:foodTrucks)
	{
	  flag = 0;
	  for (FoodTruckLocation location:foodTruck.
		   getLocations
		   ())
		{

		  //////////////////////////
		  ////////////////////////
		  flag = 0;
		for (DaysSlot slot:location.getDaySlots ())
		    {
		      String today =
			new SimpleDateFormat ("EEEE").format (date);
		      //System.out.println (today);
		      if (today.
			  toLowerCase ().equals (slot.getDay ().
						 toLowerCase ()))
			{
			for (String[]time:slot.getTimeSlots
			       ())
			    {
			      Date start = parser.parse (time[0]);
			      Date end = parser.parse (time[1]);
			      if (now.after (start) && now.before (end))
				{
				    double lat  = Double.parseDouble(latitude);
				    double lon  = Double.parseDouble(longitude);
				    
				    double dist = location.getDistance(lat,lon);
				    if(dist <= 5){
				    flag++;
				    if (flag == 1)
				    {
				      count++;
				      foodTruck.printList (count);
				      System.out.println("******** LOCATION ********");
				    // System.out.println("******** MENU ********");
				    }
				    
				    
				    location.printList();
				    System.out.print("\t"+ dist+"KM");
				    System.out.println("\t"+time[0]+" - "+time[1]);
				    break;
				    }
				}
			    }
			}
		    }
		  ////////////////////////
		  ////////////////////////
		  
		}
	
	  if (flag > 0)
	    {
	    for (FoodMenuItem item:foodTruck.getMenu ())
	    {
	      
		for (DaysSlot slot:item.getDaySlots ())
		    {
		      String today =
			new SimpleDateFormat ("EEEE").format (date);
		      //System.out.println (today);
		      if (today.
			  toLowerCase ().equals (slot.getDay ().
						 toLowerCase ()))
			{
			for (String[]time:slot.getTimeSlots
			       ())
			    {
			      Date start = parser.parse (time[0]);
			      Date end = parser.parse (time[1]);
			      if (now.after (start) && now.before (end))
				{

				  flag++;
				  
				  if (flag == 1)
				    {
				      count++;
				      foodTruck.printList (count);
				    System.out.println("******** MENU ********");
				    }
				  item.printMenuList ();
				  System.out.println ("\t" + time[0] + " - " +
						      time[1]);
				  break;
				}
			    }
			}
		    }
		
	    }
	      System.out.println
		("_______________________________________\n\n");
	    }
	}
      System.out.println ("" + count + "/" + foodTrucks.size () +
			" Records found.");
      //Date userDate = parser.parse(someOtherDate);

    }
    catch (ParseException e)
    {
      // Invalid date was entered
    }
  }
}
