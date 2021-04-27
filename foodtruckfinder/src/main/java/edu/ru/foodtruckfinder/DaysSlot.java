
package edu.ru.foodtruckfinder;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
public class DaysSlot{
    //private Scanner keyboardScanner = new Scanner(System.in);
    private ArrayList<String[]> timeSlots = new ArrayList<String[]>();
    private String day;
    public String getDay(){
        return day;
    }
    public void setDay(String day){
        this.day = day;
    }
    private int slotsLength;
     public int getSlotsLength(){
        return slotsLength;
    }
    public void setSlotsLength(int slotsLength){
        this.slotsLength = slotsLength;
    }
    // public void AddTimeSlot(String timeSlot){
    //     timeSlots.add(timeSlot);
    // }
    public ArrayList<String[]> getTimeSlots(){
        return timeSlots;
    }
    public DaysSlot (){}
    public DaysSlot (String day, ArrayList<String[]> timeSlots) {
        this.day = day;
		this.timeSlots = timeSlots;
		this.slotsLength = timeSlots.size();
	}
	public DaysSlot (String day) {
		this.day = day;
	}
	public DaysSlot(Scanner sc) {
	    	String value = sc.nextLine();
		 day = value;
		 String n = sc.nextLine();
		int num = Integer.parseInt(n);
		slotsLength = num;
		for(int i=0;i<num ;i++){
		    	String[] val = sc.nextLine().split(" - ");
		 timeSlots.add(val);   
		}
// 		String[] value = sc.nextLine().split(",");
// 		 day = value[0];
// 		for(int i=1;i<value.size() ;i++){
// 		 timeSlots.add(value[i]);   
// 		}
	}
	public void AddDay(){
	    Scanner keyboardScanner = new Scanner(System.in);
	    System.out.println("Enter the Day Name and press <Enter> :");
				String _day = keyboardScanner.nextLine();
			day = _day;
	}
	public void AddTimeSlots(){
	    System.out.println("Enter number of slots:");
		Scanner keyboardScanner = new Scanner(System.in);
		String num = keyboardScanner.nextLine();
		int number;
		try{
			number = Integer.parseInt(num);
		} catch(Exception e){
			System.out.println("Invalid choice");
			return;
		}
		slotsLength = number;
	    System.out.println("Enter the time slot (hh:mm - hh:mm) and press <Enter> after each slot :");
		for(int i=0;i<number;i++){
		    
		    System.out.println("TimeSlot "+(i+1));
		    String[] slot = new String[2];
		    for(int k=0; k<2; k++){
		        if(k==0)
		        System.out.println("Starting Time");
		        else
		        System.out.println("Ending Time");
			slot[k] = keyboardScanner.nextLine();
		    }
			//AddTimeSlot(slot);
		timeSlots.add(slot);
		}
	}
	//print
	public void print(){
	    System.out.println(this.day);
	    for(String[] slot:timeSlots){
             System.out.println("        "+slot[0]+" - "+slot[1]);
        }
	}
	 //Save
		  public void save (FileWriter p) {
		      try{
        p.write(day+"\n");
        
        p.write(timeSlots.size()+"\n");
        
        for(String[] slot:timeSlots){
            p.write(slot[0]+" - "+slot[1]+"\n");
        }
		      } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
		  }

}