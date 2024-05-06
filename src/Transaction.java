import java.util.Date;

public class Transaction {

	private Employee employee = null;
	private Date time = null;
	
	//Constructor that takes an employee and sets the date to the current date.
	Transaction(Employee employee){
		this.employee = employee;
		time = new Date();
	}
	
	//CHECK if this transaction is for the given name. If so return true, if not return false.
	public boolean hasName(String name) {
		if(name.equals(employee.getName())){
			return true;
		}
		else {
			return false;
		}
	}
	
	//Return Transaction as a string.
	public String toString() {
		return " Date/Time: " + time.toString();
	}
	
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
}
