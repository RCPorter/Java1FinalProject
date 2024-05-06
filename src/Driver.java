import java.util.*;
public class Driver {

	public static void main(String[] args) {
		
		Clock clock = new Clock();
		Scanner input = new Scanner(System.in);
		
		int selection;
		
		do {
			menu();
			
			System.out.println("\nSelection: ");
			selection = input.nextInt();
			
			switch (selection) {
			case 1:
				System.out.println("\nSelected - Add Employee:");
				if (clock.setEmployee()) {
					System.out.println("Successfully Added!");
				}
				else {
					System.out.println("Employee already exists!");
				}
				System.out.println("\n");
				break;
			case 2:
				System.out.println("\nSelected - Delete Employee:");
				if (clock.deleteEmployee()) {
					System.out.println("Successfully Deleted!");
				}
				else {
					System.out.println("Employee not found!");
				}
				System.out.println("\n");
				break;
			case 3:
				System.out.println("\nSelected - Edit Employee:");
				if(clock.editEmployee()) {
					System.out.println("Succesfully Edited!");
				}
				else {
					System.out.println("Employee not found!");
				}
				System.out.println("\n");
				break;
			case 4:
				System.out.println("\nSelected - Display number of Employees Absent:");
				System.out.println(	"There are currently " + clock.getNumAbsent() +
									" absent employees!");
				break;
			case 5:
				System.out.println("\nSelected - Dipslay number of Employees Present:");
				System.out.println(	"There are currently " + clock.getNumPresent() +
						" present employees!");
				System.out.println("\n");
				break;
			case 6:
				System.out.println("\nSelected - Add Time Punch:");
				if(clock.punch()) {
					System.out.println("Time Punch Successfully added!");
				}
				else {
					System.out.println("Employee not found!");
				}
				System.out.println("\n");
				break;
			case 7:
				System.out.println("\nSelected - Remove Time Punch:");
				if(clock.removePunch()) {
					System.out.println("Time Punch Successfully removed!");
				}
				else {
					System.out.println("Employee not found!");
				}
				System.out.println("\n");
				break;
			case 8:
				System.out.println("\nSelected - List Employees:");
				clock.listEmployees();
				System.out.println("\n");
				break;
			case 9:
				System.out.println(	"\nSelected - End Day:" +
									"Thank you! Have a nice day!");
				break;
			default:
				System.out.println("\nError: Incorrect Input. Please Try Again.");
				System.out.println("\n");
				break;
			}
		}while(selection != 9);
	input.close();	
	}
	
	private static void menu() {
		System.out.print(	"Please Select an option from the following menu: " +
				"\n1:Add Employee" +
				"\n2:Delete Employee" +
				"\n3:Edit Employee Information" +
				"\n4:Display number of Employees Absent" + 
				"\n5:Display number of Employees Present" +
				"\n6:Add Time Punch" +
				"\n7:Remove Time Punch" +
				"\n8:List Employees" +
				"\n9:End Day");
	}
}
