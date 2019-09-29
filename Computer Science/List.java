/**
 * @(#)List.java
 *
 *
 * @author 
 * @version 1.00 2017/11/20
 */
import java.util.Scanner; 
import java.util.ArrayList; 
import java.io.*; 

/* A+ November 2015 #4 */

class Item {
	private String name; 
	private int number; 
	private double price; 
		
	public Item(String name, int number, double price) {
		this.name = name; 
		this.number = number; 
		this.price = price; 
	}
	
	public String getName() {
		return name; 
	}
	
	public int getNumber() {
		return number; 
	}
	
	public double getPrice() {
		return price; 
	}
}
public class List {
	
    public static void main(String[] args) throws FileNotFoundException {
    	ArrayList<Item> items = new ArrayList<Item>(); 
        Scanner scan = new Scanner(new File("list.dat.txt"));
		double cost = 0.0;
        int numberOfDataSets = scan.nextInt(); 
        scan.nextLine(); 
        int numberOfItems = scan.nextInt(); 
        int numberOfListItems = scan.nextInt(); 
        scan.nextLine(); 
        for(int i=0; i < numberOfItems; i++) {
        	String line = scan.nextLine(); 
        	String itemName = line.substring(0, line.indexOf(",")); 	
        	int numPack = Integer.parseInt(line.substring(line.indexOf(",")+2,line.lastIndexOf(",")));
        	double price = Double.parseDouble(line.substring(line.lastIndexOf(",")+2));
        	items.add(new Item(itemName, numPack, price)); 
        }
        
        while(scan.hasNextLine()) {
        	String line = scan.nextLine(); 
        	String itemName = line.substring(0,line.indexOf(","));
        	int numberNeeded = Integer.parseInt(line.substring(line.indexOf(",")+2));

        	for(Item i : items) {
        		if(i.getName().equals(itemName)) {
					cost += (numberNeeded / i.getNumber()) * i.getPrice();
        			if(numberNeeded % i.getNumber() != 0) {
						cost += i.getPrice();
					}
				}
			}
        }
		cost = cost + cost * 0.0625;
        System.out.printf("Joe's trip to the store costs him $%.2f", cost);
    }
}