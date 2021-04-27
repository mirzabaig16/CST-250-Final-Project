
package edu.ru.foodtruckfinder;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
public class FoodMenuItem
{
  // private Scanner keyboardScanner = new Scanner(System.in);
  private ArrayList < DaysSlot > daySlots = new ArrayList < DaysSlot > ();
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
  //menuType //breakfast lunch dinner
  private String menuType;
  public String getMenuType ()
  {
    return menuType;
  }
  public void setMenuType (String menuType)
  {
    this.menuType = menuType;
  }
  //AvailibilityTimeSlot
  //private  availibilityTimeSlot;
  public ArrayList < DaysSlot > getDaySlots ()
  {
    return daySlots;
  }
  public void setDaySlots (ArrayList < DaysSlot > daySlots)
  {
    this.daySlots = daySlots;
  }
  //price
  private double price;
  public double getPrice ()
  {
    return price;
  }
  public void setPrice (double price)
  {
    this.price = price;
  }

  public FoodMenuItem ()
  {
  }
  //constructor with scanner
  public FoodMenuItem (Scanner sc)
  {
    String value = sc.nextLine ();
    //      System.out.println("-----"+value);
    this.name = value;
    value = sc.nextLine ();
    this.menuType = value;
    value = sc.nextLine ();
    this.price = Double.parseDouble (value);

    String n = sc.nextLine ();
    int num = Integer.parseInt (n);
    // finding timeslots
    for (int i = 0; i < num; i++)
      {
	DaysSlot slot = new DaysSlot (sc);
	daySlots.add (slot);
      }
  }

  public void AddItemName ()
  {
    Scanner keyboardScanner = new Scanner (System.in);
    System.out.println ("Enter the Item Name and press <Enter> :");
    this.name = keyboardScanner.nextLine ();
  }
  public void AddPrice ()
  {
    System.out.println ("Enter Price:");
    Scanner keyboardScanner = new Scanner (System.in);
    String num = keyboardScanner.nextLine ();
    Double number;
    try
    {
      number = Double.parseDouble (num);
    } catch (Exception e)
    {
      System.out.println ("Invalid Price");
      return;
    }
    this.price = number;
  }
  public void AddItemType ()
  {
    Scanner keyboardScanner = new Scanner (System.in);
    System.out.
      println
      ("Enter the Menu Type (e.g. Breakfast , lunch, Dinner) and press <Enter> :");
    this.menuType = keyboardScanner.nextLine ();
  }
  public void AddDaysSlot ()
  {

    System.out.println ("Enter number of Days(1-7) :");
    Scanner keyboardScanner = new Scanner (System.in);
    String num = keyboardScanner.nextLine ();
    int number;
    try
    {
      number = Integer.parseInt (num);
      if (number < 0 || number > 7)
	{
	  System.out.println ("You can add between 1-7");
	  return;
	}
    }
    catch (Exception e)
    {
      System.out.println ("Invalid Entry");
      return;
    }

    for (int i = 0; i < number; i++)
      {
	System.out.println ("Day " + (i + 1));
	DaysSlot slot = new DaysSlot ();
	slot.AddDay ();
	slot.AddTimeSlots ();
	daySlots.add (slot);
      }
  }
  //print
  public void print ()
  {
    System.out.print (this.name + "\t\t" + this.menuType + "\t\t" +
		      this.price + "\n");
  for (DaysSlot slot:daySlots)
      {
	System.out.print ("      ");
	slot.print ();
      }
  }
  public void print (int n)
  {
    System.out.print (this.name + "\t\t" + this.menuType + "\t\t" +
		      this.price + "\n");
  for (DaysSlot slot:daySlots)
      {
	System.out.print ("      ");
	slot.print ();
      }

  }
  public void printMenuList()
  {
    System.out.print(this.name + "\t\t" + this.menuType + "\t\t" +
		      this.price);
//   for (DaysSlot slot:daySlots)
//       {
// 	System.out.print ("      ");
// 	slot.print ();
//       }

  }
  //Save
  public void save (FileWriter p)
  {
    try
    {
      p.write (name + "\n");
      p.write (menuType + "\n");
      p.write (price + "\n");

      p.write (daySlots.size () + "\n");

    for (DaysSlot slot:daySlots)
	{
	  slot.save (p);
	}
    }
    catch (IOException e)
    {
      System.out.println ("An error occurred.");
      e.printStackTrace ();
    }
  }

}
