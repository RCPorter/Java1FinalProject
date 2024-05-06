
public class Employee {

	private String name = "";
	private String address = "";
	
	Employee(String name, String address){
		this.name = name;
		this.address = address;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setAddress (String address) {
		this.address = address;
	}
	
	public String toString() {
		return "Name: " + name + " Address: " + address;
	}
	
}
