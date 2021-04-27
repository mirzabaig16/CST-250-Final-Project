
package edu.ru.foodtruckfinder;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
public class FoodTruckLocation{
    private String address;
    public String getAddress(){
        return this.address;
    }
    public void setAddress(String address){
        this.address = address;
    }
    private double longitude;
    public Double getLongitude(){
        return this.longitude;
    }
    public void setLongitude(){
        this.longitude = longitude;
    }
    private double latitude;
    public Double getLatitude(){
        return this.latitude;
    }
    public void setLatitude(){
        this.latitude = latitude;
    }
    private ArrayList<DaysSlot> daySlots = new ArrayList<DaysSlot>();
    public ArrayList<DaysSlot> getDaySlots() {
		return daySlots;
	}
	public void setDaySlots(ArrayList<DaysSlot> daySlots) {
		this.daySlots = daySlots;
	}
		public FoodTruckLocation(){}
	public FoodTruckLocation(Scanner sc) {
	     String value = sc.nextLine();
		  this.address = value;
// 		  	this.latitude = sc.nextLine();
// 		 this.longitude = sc.nextLine();
		String add[] = sc.nextLine().split(",");
		 this.latitude = Double.parseDouble(add[0]);
		 this.longitude = Double.parseDouble(add[1]);
		String n = sc.nextLine();
		int num = Integer.parseInt(n);
		  // finding timeslots
		  for(int i=0;i<num;i++){
			DaysSlot slot = new DaysSlot(sc);
			daySlots.add(slot);
		  }
		}
		
		  public void AddAddress(){
		      Scanner keyboardScanner = new Scanner(System.in);
		      System.out.println("Enter the Address and press <Enter> :");
				this.address = keyboardScanner.nextLine();
		  }
		  public void AddLatitiude(){
		      System.out.println("Enter Latitude:");
		Scanner keyboardScanner = new Scanner(System.in);
		String num = keyboardScanner.nextLine();
		Double number;
		try{
			number = Double.parseDouble(num);
		} catch(Exception e){
			System.out.println("Invalid Value");
			return;
		}
		this.latitude = number;
		  }
		    public void AddLongitude(){
		      System.out.println("Enter Longitude:");
		Scanner keyboardScanner = new Scanner(System.in);
		String num = keyboardScanner.nextLine();
		Double number;
		try{
			number = Double.parseDouble(num);
		} catch(Exception e){
			System.out.println("Invalid Value");
			return;
		}
		this.longitude = number;
		  }
			  public void AddDaysSlot(){
		      
		      System.out.println("Enter number of Days(1-7) :");
	Scanner keyboardScanner = new Scanner(System.in);
		String num = keyboardScanner.nextLine();
		int number;
		try{
			number = Integer.parseInt(num);
			if(number< 0 || number> 7){
			    System.out.println("You can add between 1-7");
			return;
			}
		} catch(Exception e){
			System.out.println("Invalid Entry");
			return;
		}
		
			for(int i=0;i<number;i++){
			    System.out.println("Day "+(i+1));
		       DaysSlot slot = new DaysSlot();
		     slot.AddDay();
		     slot.AddTimeSlots();
		      daySlots.add(slot);
		  }
		  }
		  
		  //print
		  public void print(){
		      System.out.print (this.address+"\t\t"+this.latitude+","+this.longitude+"\n");
		      for(DaysSlot slot:daySlots){
		          System.out.print ("      ");
            slot.print();
        }
		  }
		  
		   public void printList(){
		      System.out.print (this.address+"\t\t"+this.latitude+","+this.longitude);
		      //for(DaysSlot slot:daySlots){
		      //    System.out.print ("      ");
        //     slot.print();
        // }
		  }
     //Save
		  public void save (FileWriter p) {
		      try{
        p.write(address+"\n");
        p.write(latitude+","+longitude+"\n");
        p.write(daySlots.size()+"\n");
        for(DaysSlot slot:daySlots){
            slot.save(p);
        }
		      } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
		      
		  }
		  
		  public double getDistance( double latitude,double longitude){
        double distance = distance(this.latitude,latitude,this.longitude,longitude,"K");
        //K for kilometer and M for miles
     return distance;
    }
    
   private double distance(double lat1, double lat2, double lon1, double lon2, String unit) {
		if ((lat1 == lat2) && (lon1 == lon2)) {
			return 0;
		}
		else {
			double theta = lon1 - lon2;
			double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
			dist = Math.acos(dist);
			dist = Math.toDegrees(dist);
			dist = dist * 60 * 1.1515;
			if (unit.equals("K")) {
				dist = dist * 1.609344;
			} 
			return (dist);
		}
   }
    
}