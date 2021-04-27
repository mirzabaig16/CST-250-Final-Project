package edu.ru.foodtruckfinder;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
public class FoodTruck
{
  //Properties
  // Name
  private String name;
  public String getName ()
  {
    return name;
  }
  public void setName (String name)
  {
    this.name = name;
  }
  // FoodType or cuisine
  private String cuisine;
  public String getCuisine ()
  {
    return cuisine;
  }
  public void setCuisine (String cuisine)
  {
    this.cuisine = cuisine;
  }
  //Menu
  private ArrayList < FoodMenuItem > menu = new ArrayList < FoodMenuItem > ();
  public ArrayList < FoodMenuItem > getMenu ()
  {
    return this.menu;
  }
  //Location
  private ArrayList < FoodTruckLocation > locations =
    new ArrayList < FoodTruckLocation > ();
  public ArrayList < FoodTruckLocation > getLocations ()
  {
    return this.locations;
  }
  public FoodTruck (Scanner sc)
  {
    this.name = sc.nextLine ();
    this.cuisine = sc.nextLine ();
    // day = value;
    String n = sc.nextLine ();

    int num = Integer.parseInt (n);
    for (int i = 0; i < num; i++)
      {
	//  String value = sc.nextLine();
	FoodMenuItem item = new FoodMenuItem (sc);
	menu.add (item);
      }
    n = sc.nextLine ();

    num = Integer.parseInt (n);
    for (int i = 0; i < num; i++)
      {
	//String value = sc.nextLine();
	FoodTruckLocation loc = new FoodTruckLocation (sc);
	locations.add (loc);
      }
  }
  public FoodTruck ()
  {

  }

  public void print ()
  {

    System.out.print ("---------------------\n");
    System.out.println (this.name + "\t(" + this.cuisine + ")\n");
    System.out.println ("******** MENU ********");
    int count = 0;
  for (FoodMenuItem item:this.menu)
      {
	count++;
	System.out.print ("  " + count + " ");
	item.print ();
      }
    System.out.println ("******** Location********");
    count = 0;
  for (FoodTruckLocation location:this.locations)
      {
	count++;
	System.out.print ("  " + count + " ");
	location.print ();
      }
  }
  public void printList ()
  {
    System.out.println (this.name + "\t" + this.cuisine);
  }
  public void printList (int n)
  {
    System.out.println (n + "\t" + this.name + "\t" + this.cuisine);
  }
  public void AddTruckName ()
  {
    Scanner keyboardScanner = new Scanner (System.in);
    System.out.println ("Enter the Truck Name and press <Enter> :");
    this.name = keyboardScanner.nextLine ();

  }
  public void AddTruckFoodType ()
  {
    Scanner keyboardScanner = new Scanner (System.in);
    System.out.println ("Enter the Food Truck Type and press <Enter> :");
    this.cuisine = keyboardScanner.nextLine ();

  }
  public boolean AddMenu ()
  {
    Scanner keyboardScanner = new Scanner (System.in);
    System.out.println ("Enter number of Items you want to add in menu :");

    String num = keyboardScanner.nextLine ();
    int number;
    try
    {
      number = Integer.parseInt (num);

    } catch (Exception e)
    {
      System.out.println ("Invalid Entry");
      return false;
    }

    for (int i = 0; i < number; i++)
      {
	System.out.println ("Item " + (i + 1));
	FoodMenuItem item = new FoodMenuItem ();
	item.AddItemName ();
	item.AddPrice ();
	item.AddItemType ();
	item.AddDaysSlot ();

	this.menu.add (item);

      }
    return true;
  }
  public boolean AddLocations ()
  {
    Scanner keyboardScanner = new Scanner (System.in);
    System.out.println ("Enter number of Locations :");
    //      Scanner keyboardScanner = ScannerFactory.getKeyboardScanner();
    String num = keyboardScanner.nextLine ();
    int number;
    try
    {
      number = Integer.parseInt (num);

    } catch (Exception e)
    {
      System.out.println ("Invalid Entry");
      return false;
    }

    for (int i = 0; i < number; i++)
      {
	System.out.println ("Location " + (i + 1));
	FoodTruckLocation loc = new FoodTruckLocation ();
	loc.AddAddress ();
	loc.AddLatitiude ();
	loc.AddLongitude ();
	loc.AddDaysSlot ();
	this.locations.add (loc);
      }
    return true;
  }

  //Save
  public void save (FileWriter p)
  {
    try
    {
      p.write (this.name + "\n");
      p.write (this.cuisine + "\n");

      p.write (this.menu.size () + "\n");

    for (FoodMenuItem item:this.menu)
	{
	  item.save (p);
	}
      p.write (this.locations.size () + "\n");

    for (FoodTruckLocation location:this.locations)
	{
	  location.save (p);
	}

      // p.write("\n");
    }
    catch (IOException e)
    {
      System.out.println ("An error occurred.");
      e.printStackTrace ();
    }
  }
}
