import java.io.Serializable;
import java.math.BigDecimal;

public class Staff implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
    private int age;
    private BigDecimal salary;
	public Staff(String name2, int age2, BigDecimal salary) {
		this.name=name2;
		this.age=age2;
		this.salary=salary;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public BigDecimal getSalary() {
		return salary;
	}
	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}
	public void Test() {

		System.out.println("Object encription decryption working correctly"+this.name+" " +this.age+" " +this.salary);

	}
}
