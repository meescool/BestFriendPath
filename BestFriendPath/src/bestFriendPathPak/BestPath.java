package bestFriendPathPak;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * This program gets the shortest path possible from Bob to one of their friends
 * on the following map:
 * 
 * Bob-==--Alice-==-Peter | | Legend Castor-==-Marco---<|>---Falco <|> == Bridge
 * | | - + | Path +--==---Titus Atia | | | | | Paula | | | <|> | | |
 * Vita-----<|>---<|>----Barb +-Sam-+
 * 
 * Since the path is continuous, I decided to make use of a circular array
 * pattern, in order to save space in memory and also time, since the list of
 * friends won't change.
 * 
 * @author Marce
 * @version v2
 * @since 10.20.2020
 *
 */

public class BestPath {
	// Will store all the friends in an ordered fashioned
	static ArrayList<String> friends = new ArrayList<String>();
	// Keeps count of the index of the friend that the user chose to travel to
	static int friendNum = 0;

	// driver code
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String name = ""; // name of the friend the user is looking for
		Boolean loop = true;

		System.out.println("Welcome to the Best Friend Path Finder\n");
		// method that will store all the friends in one arrayList
		setFriends();
		// method that prints out visual representation of the map
		printedMap();
		while (loop == true) {
			// gets the name of the friend from the user
			name = checkName(name, scan);
			loop = contYN(scan); // gets back a boolean value
			// in order to continue or not the program
			System.out.println();
		}
		System.out.println("Thank you for participating!");

	}

	/**
	 * This method sets the friends that will be in the ArrayList
	 * 
	 * @param none
	 */
	static void setFriends() {
		friends.add(0, "Bob");
		friends.add(1, "Alice");
		friends.add(2, "Peter");
		friends.add(3, "Atia");
		friends.add(4, "Sam");
		friends.add(5, "Titus");
		friends.add(6, "Paula");
		friends.add(7, "Vita");
		friends.add(8, "Barb");
		friends.add(9, "Falco");
		friends.add(10, "Marco");
		friends.add(11, "Castor");
	}

	/**
	 * This method prints the map of the friends
	 * 
	 * @param none
	 */
	static void printedMap() { // method to print the map
		System.out.println("    Bestfriend Map\n");

		System.out.print("Bob-==--Alice-==-Peter\n" + "|                  |" + "                       Legend\n"
				+ "Castor-==-Marco---<|>---Falco" + "        <|>  ==  Bridge \n" + "                   |      |"
				+ "          -  +  |  Path\n" + "   +--==---Titus   Atia   |\n" + "   |         |     |      |\n"
				+ "Paula        |     |      |\n" + "  <|>        |     |      |\n" + "   Vita-----<|>---<|>----Barb\n"
				+ "             +-Sam-+\n\n");

	}

	/**
	 * this method checks the users input to see which friend they type in
	 * 
	 * @param name the name of the person the user wants to find
	 * @param scan for taking in the name of the person
	 * @return
	 */
	static String checkName(String name, Scanner scan) {
		System.out.println("Please enter the name of the friend you want Bob to visit.");
		String temp = scan.nextLine(); // created a temp variable to store user answer
		name = temp.substring(0, 1).toUpperCase() + temp.substring(1).toLowerCase(); // formatted user answer to fit
		// criteria
		if (friends.contains(name)) {
			friendNum = friends.indexOf(name); // sets the index of the friend we need to find if it's on the
												// linkedArray
			selectPath(name);
			return name;
		} else { // this immediately sends message that the friend isn't found
			System.out.println("\n   Friend " + name + " not found! Start with Bob");
			return null;
		}
	}
    
	/**
	 * This method chooses the direction in which Bob should travel by taking 
	 * into consideration which friend is being sought
	 * 
	 * @param name the name will determine which direction to go
	 */
	static void selectPath(String name) {
		Random r = new Random();// declaring a random for choosing which of the paths to use when they are equal

		/*
		 * since I'm only using one data structure, and the array list is indexed and
		 * already in order I can traverse the array from front to back or from back to
		 * front. therefore I'm using the index of the friend in order to determine
		 * whether it takes longer to go from "front to back" or back to front" this is
		 * faster then having to traverse both front to back and back to front and
		 * comparing the sizes
		 */
		if (friendNum < (friends.size()) - friendNum) {
			text(name);
			forward(name);
		} else if (friendNum > ((friends.size()) - friendNum)) {
			text(name);
			backward(name);
		} else {
			System.out.println("\n   Path 1 and Path 2 are equal!");
			text(name);
			int choice = r.nextInt(10) + 1;
			if (choice % 2 == 0) { // totally random choice
				forward(name);
			} else {
				backward(name);
			}

		}

	}

	/** made this method just to minimize code
	 * 
	 * @param name
	 */
	static void text(String name) {
		System.out.println("\n   Best friend path from Bob to " + name);
		System.out.print("   ");
	}
	/** 
	 * This method is for traversing the array in a forward direction
	 * and goes ahead and prints the hops of friends from Bob when choosing to hop
     * towards Alice
	 * 
	 * @param name
	 */
	static void forward(String name) {
		
		for (String k : friends) {
			System.out.print(k);
			if (k.equals(name)) // once we reach the index of the friend, the loop breaks
				// and we have the path printed out
				break;
			System.out.print(" -> ");
		}
	}

	/** 
	 * this for loop, goes through the collection of friends in backwards direction
	 * and goes ahead and prints the hops of friends from Bob when choosing to hop
	 * towards Castor
	 * 
	 * @param name
	 */
	static void backward(String name) {
		for (String k : friends) {
			/*
			 * went through how to go backwards in an array in a circular matter and found
			 * this mathematical equation. it takes the initial positions (which in this
			 * case is always Bob so 0 subtracts the index at which the counter is at and
			 * adds the total of friends. this then takes the remainder of dividing by the
			 * total number of friends
			 */
			System.out.print(friends.get(((0 - (friends.indexOf(k)) + friends.size()) % friends.size())));
			if (friends.get(((0 - (friends.indexOf(k)) + friends.size()) % friends.size())).equals(name))
				break;
			System.out.print(" -> ");
		}

	}

	/** this method is for letting the user decide whether or not to continue
	 * 
	 * @param scan
	 * @return true or false
	 */
	static boolean contYN(Scanner scan) {
		String cont = "";
		while (true) {
			System.out.println("\n\nWould you like to find another friend? (y/n):");
			cont = scan.nextLine().toLowerCase();
			if (cont.equals("y") || cont.equals("yes"))
				return true;
			else if (cont.equals("n") || cont.equals("no"))
				return false;
			else
				System.out.println("Invalid input! Please type in yes or no (y/n):");
		}

	}

}
