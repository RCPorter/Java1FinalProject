import java.util.*;

public class Clock {

	/*ATTRIBUTES*/
	private final String 	NOT_FOUND = "Employee Not Found",
							ABSENT = " (Absent)",
							PRESENT = " (Present)";
	
	Scanner input = new Scanner(System.in);
	
	private List<Employee> present = new ArrayList<>();
	private List<Employee> absent = new ArrayList<>();
	private List<Transaction> punch = new ArrayList<>();
	
	
	/*CONSTRUCTOR*/
	Clock(){
		System.out.println("Welcome to a new day!");
	}
		
	/*PRIVATE METHODS*/
	
	//CHECK present list for name. Return index if found. Return -1 if not.
	private int getPresent(String name) {
		for(int i = 0; i < present.size(); i++) {
			if (name.equals(present.get(i).getName())) {
				return i;
			}
		}
		return -1;
	}
	
	//CHECK absent list for name. Returns index if found. Return -1 if not.
	private int getAbsent(String name) {
		for(int i = 0; i < absent.size(); i++) {
			if (name.equals(absent.get(i).getName())) {
				return i;
			}
		}
		return -1;
	}
	
	//Replaces each punch for name with the new employee information.
	private void editPunches(String name, Employee edit) {
		int lastPunch = getLastPunch(name);
		while(lastPunch != -1) {
			punch.get(lastPunch).setEmployee(edit);
		}
	}
	
	//Removes all punches for the given name.
	//Does not need a feedback return because this method
	//is only used by the deleteEmployee method which has already confirmed
	//the existence of the named employee within one of the lists
	private void deletePunches(String name) {

		int lastPunch = getLastPunch(name);
		while(lastPunch != -1) {
			punch.remove(lastPunch);
		}
	}
	
	//Returns the index of the next punch starting after the 
	//previous punch.
	private int getNextPunch(String name, int previous) {
		for(int i = previous + 1; i < punch.size(); i++) {
			if(punch.get(i).hasName(name)) {
				return i;
			}
		}
		return -1;
	}
	
	//Outputs all punches for a given employee.
	private void listPunches(String name) {
		int nextPunch = getNextPunch(name, -1);
		System.out.println("\tPunches:");
		int count = 1;
		while(nextPunch != -1) {
			System.out.println(	"\t\t" + (count % 2 == 0 ? "Out: " : "In:  ") + 
								punch.get(nextPunch).toString());
			count++;
			nextPunch = getNextPunch(name, nextPunch);
		}
	}
	
	
	/*PUBLIC METHODS*/
	//CHECK if employee has already been entered. If so, return False.
	//If not, add employee to absent list
	public boolean setEmployee() {
		System.out.println("Please Enter Employees Name: ");
		String name = input.nextLine();
		System.out.println("Please Enter " + name + "s Address: ");
		String address = input.nextLine();
		
		Employee temp = new Employee(name, address);
		
		//If Absent list is empty.
		if (absent.isEmpty()) {
			absent.add(temp);
			return true;
		}
		//If name is found in present list.
		else if (getPresent(name) != -1) {
			return false;
		}
		//If name is not found in absent list.
		else if (getAbsent(name) == -1) {
			absent.add(temp);
			return true;
		}
		else {
			return false;
		}
	}
	
	//CHECK both lists. Return employee as string with present or absent. Return not found if not.
	public String getEmployee(String name) {
		
		if (getPresent(name) != -1) {
			return(present.get(getPresent(name)).toString() + PRESENT);
		}
		else if (getAbsent(name) != -1) {
			return(absent.get(getAbsent(name)).toString() + ABSENT);
		}
		else {
			return NOT_FOUND;
		}
		
	}
	
	//Checks if Employee with name exists. If so, deletes employee
	//and returns true. If the employee is not found, returns false.
	public boolean deleteEmployee() {
		
		System.out.println("Please enter the employees name: ");
		String name = input.nextLine();
		
		int presentEmployee = getPresent(name);
		int absentEmployee = getAbsent(name);
		
		if(presentEmployee != -1) {
			present.remove(presentEmployee);
			deletePunches(name);
			return true;
		}
		else if(absentEmployee != -1) {
			absent.remove(absentEmployee);
			deletePunches(name);
			return true;
		}
		else {
			return false;
		}
		
	}
	
	//Locates the employee by name then allows the user to input
	//the new name and address for the employee.
	//Then replaces the employee information in whichever list
	//that employee existed.
	//Then iterates through the punch list and replaces the employee
	//with the new employee
	public boolean editEmployee() {
		System.out.println("Please enter the employees name: ");
		String name = input.nextLine();
		
		int presentEmployee = getPresent(name);
		int absentEmployee = getAbsent(name);
		
		if(presentEmployee != -1) {
			System.out.println("What is the new name of " + name + ": ");
			String newName = input.nextLine();
			System.out.println("What is the new address of " + newName + ": ");
			String newAddress = input.nextLine();
			present.get(presentEmployee).setName(newName);
			present.get(presentEmployee).setAddress(newAddress);
			editPunches(name, present.get(presentEmployee));
			return true;
		}
		else if(absentEmployee != -1) {
			System.out.println("What is the new name of " + name + ": ");
			String newName = input.nextLine();
			System.out.println("What is the new address of " + newName + ": ");
			String newAddress = input.nextLine();
			absent.get(absentEmployee).setName(newName);
			absent.get(absentEmployee).setAddress(newAddress);
			editPunches(name, absent.get(absentEmployee));
			return true;
		}
		else {
			return false;
		}
	}
	
	//Outputs full list of Employees listed by Present or
	//Absent each followed by a list of their punches.
	public void listEmployees() {
		System.out.println("\nPresent:");
		for(int i = 0; i < present.size(); i++) {
			System.out.println(present.get(i).toString());
			listPunches(present.get(i).getName());
		}
		System.out.println("\nAbsent:");
		for(int i = 0; i < absent.size(); i++) {
			System.out.println(absent.get(i).toString());
			listPunches(absent.get(i).getName());
		}
	}
	
	//Adds a transaction for the employee of the given name and moves that employee from
	//Present to Absent or Absent to Present.
	//Returns true if the punch was successful.
	//Returns false if not.
	public boolean punch() {

		System.out.println("Please enter the employees name: ");
		String name = input.nextLine();
		
		int presentEmployee = getPresent(name);
		int absentEmployee = getAbsent(name);
		
		if (presentEmployee != -1) {
			punch.add(new Transaction(present.get(presentEmployee)));
			absent.add(present.get(presentEmployee));
			present.remove(presentEmployee);
			return true;
		}
		
		else if (absentEmployee != -1) {
			punch.add(new Transaction(absent.get(absentEmployee)));
			present.add(absent.get(absentEmployee));
			absent.remove(absentEmployee);
			return true;
		}
		
		else {
			return false;	
		}
	}
	
	//Returns the index of the last punch for the given name in the punch list
	//Returns -1 if no punch for the given name is found.
	public int getLastPunch(String name) {
		
		int lastPunch = -1;

		for(int i = punch.size() - 1; i >= 0; i--) {
			if(punch.get(i).hasName(name)) {
				lastPunch = i;
				return lastPunch;
			}
		}
		return lastPunch;
	}
	
	//Removes the most recent punch for the given employee.
	//Moves employee back to its previous list.
	public boolean removePunch() {
		System.out.println("Please enter the employees name: ");
		String name = input.nextLine();
		
		int lastPunch = getLastPunch(name);
		int presentEmployee = getPresent(name);
		int absentEmployee = getAbsent(name);
		
		if (lastPunch != -1) {
			if (presentEmployee != -1) {
				absent.add(present.get(presentEmployee));
				present.remove(presentEmployee);
			}
			else {
				present.add(absent.get(absentEmployee));
				absent.remove(absentEmployee);
			}
			punch.remove(lastPunch);
			return true;
		}
		else {
			return false;
		}
	}
	
	//Returns number of present employees.
	public int getNumPresent() {
		return present.size();
	}
	
	//Returns number of absent employees.
	public int getNumAbsent() {
		return absent.size();
	}
}
