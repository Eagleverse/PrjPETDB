/*

 */

package petDB;
import java.util.Scanner;
import java.io.File;  // Import the File class
import java.io.FileWriter;
import java.io.IOException;  // Import the IOException class to handle errors
/**
 *
 * @author kaifa
 */

public class PrjPETDB {

  static Pet[] pets = new Pet[100];

  static Scanner input = new Scanner(System.in);
  static int petCount = 0;
  public static void main(String[] args) {
      //code adapted from W3schools
    int choice = 0;
    // TODO Auto-generated method stub
    System.out.println("Pet Database.");
    readPetFromFile();
    while (choice != 7) {
      System.out.println("What would you like to do?");
      System.out.println("1) View all pets\n2) Add more pets\n3) Update an existing pet\n4) Remove an existing pet\n5) Search pets by name\n6) Search pets by age\n7) Exit program");
      System.out.print("Your choice: ");
      choice = input.nextInt();
      switch (choice) {
      case 7:
         System.out.println("Yes I can reach this code.");
        break;
      case 1:
        PrjPETDB.showAllPets();
        break;
      case 2:
        PrjPETDB.addPets();
        writePetToFile();
        break;
      case 3:
        PrjPETDB.updatePet();
        break;
      case 4:
        PrjPETDB.removePet();
        break;
      case 5:
        PrjPETDB.searchPetsByName();
        break;
      case 6:
        PrjPETDB.searchPetsByAge();
        break;
      }
      
    }
    System.out.println("Goodbye!");
  }

  private static void addPets() {
    System.out.println("...");
    String a = "";
    int b = 0;

    String[] temp = new String[2];
    while (a != "done") {
      System.out.print("Add pet: ");
      a = input.next();
      if (a.equalsIgnoreCase("done")) {
        break;
      }
      b = input.nextInt();
      String CurrPet = a + " " + b;
      int age = b;
      pets[petCount] = new Pet(a, b, petCount);
      petCount++;
      System.out.println("Added: " + CurrPet + ". PetCount at: " + petCount);
    }
  }

  private static void showAllPets() {
    printTableHeader();
    int count = 0;
    for (Pet i: pets) {
      if (i != null) {
        printTableRow(pets[count].getID(), pets[count].getName(), pets[count].getAge());
        count++;
      }
    }
    printTableFooter(count);
  }

  private static void writePetToFile() {
      try {  
      FileWriter petWriter = new FileWriter("pets.txt");
      
      // Code adapted from w3schools
    int count = 0;
    for (Pet i: pets) {
      if (i != null) {
        petWriter.write(pets[count].getID()+"|"+ pets[count].getName()+"|"+ pets[count].getAge()+"\n");
        count++;
      }
    }
      petWriter.close();
      System.out.println("Successfully wrote to the file.");
    } catch (IOException e) {
      System.out.println("Cannot make file.");
      e.printStackTrace();
    } 
    
  }
  private static void readPetFromFile(){
      try{
    File petFile = new File("pets.txt");
        Scanner petReader = new Scanner(petFile);
		
		//Let's build the thing
		while (petReader.hasNextLine()) {
	        String lines = petReader.nextLine();
	        String[] petEntry = lines.split("\\|");
                pets[petCount] = new Pet(petEntry[1],Integer.valueOf(petEntry[2]),Integer.valueOf(petEntry[0]));
                petCount++;
		}
    
      }catch (IOException e) {
      System.out.println("Cannot make file.");
      e.printStackTrace();
    } 
  
  }
  private static void updatePet() {
      System.out.print("Enter the pet ID you want to update: ");
      int thisID = input.nextInt();
      String oldPet =pets[thisID].getName()+" "+pets[thisID].getAge();
      System.out.println("...");
    String a = "";
    int b = 0;

    String[] temp = new String[2];
    while (a != "cancel") {
      System.out.println("Enter new name and new age:");
      a = input.next();
      if (a.equalsIgnoreCase("cancel")) {
        break;
      }
      b = input.nextInt();
      String CurrPet = a + " " + b;
      temp = CurrPet.split(" ");
      int age = b;
      //Override with new pet object
      pets[thisID] = new Pet(temp[0], age, thisID);
      System.out.println(oldPet+" Changed to "+pets[thisID].getName()+" "+pets[thisID].getID()+".");
      break;
    }
  }
  private static void removePet(){
      Pet[] pets2 = new Pet[100];
      System.out.print("Enter the pet ID you want to remove: ");
      int thisID = input.nextInt();
      String oldPet =pets[thisID].getName()+" "+pets[thisID].getAge();
      pets[thisID]=null;
      System.out.print(oldPet+" was removed.");
      printTableHeader();
    int count = 0;
    for (Pet i: pets) {
      if (i != null) {
        pets2[count]=i;
        i.setID(count);
        printTableRow(pets2[count].getID(), pets2[count].getName(), pets2[count].getAge());
        count++;
      }
    }
    printTableFooter(count);
    //Replace with new array
    pets = pets2;
      
  }
 
  private static void searchPetsByName() {
    System.out.print("Insert name to Search: ");
    String query = input.next();
    printTableHeader();
    int count = 0;
    for (Pet i: pets) {
      if (i != null) {

        if (i.getName().equals(query)) {
          printTableRow(i.getID(), i.getName(), i.getAge());
          count++;
        }
      }
    }

    printTableFooter(count);

  }

  private static void searchPetsByAge() {
          System.out.print("Enter age to search: ");
    int query = input.nextInt();
    printTableHeader();
    int count = 0;
    for (Pet i: pets) {
      if (i != null) {

        if (i.getAge()==query) {
          printTableRow(i.getID(), i.getName(), i.getAge());
          count++;
        }
      }
    }

    printTableFooter(count);

  }
  private static void loadPetData(){
  }
  private static void savePetData(){
  }

  private static void printTableHeader() {
    System.out.printf("+%3s-%10s-%4s+\n", "---", "----------", "----");
    System.out.printf("|%-3s|%-10s|%-4s|\n", "ID", "NAME", "AGE");
    System.out.printf("+%3s-%10s-%4s+\n", "---", "----------", "----");
  }

  private static void printTableRow(int id, String name, int age) {
    System.out.printf("|%-3s|%-10s|%-4s|\n", id, name, age);

  }

  private static void printTableFooter(int rowCount) {
    System.out.printf("+%3s-%10s-%4s+\n", "---", "----------", "----");

    System.out.println(rowCount + " rows in set.");
  }
}