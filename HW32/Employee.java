package telran.employeers.dto;

import java.time.LocalDate;

public class Employee {
	private long id;
	private int salary;
	private LocalDate birthDate;
	private String department;

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public long getId() {
		return id;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public Employee(long id, int salary, LocalDate birthDate, String department) {
		this.id = id;
		this.salary = salary;
		this.birthDate = birthDate;
		this.department = department;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (birthDate == null) {
			if (other.birthDate != null)
				return false;
		} else if (!birthDate.equals(other.birthDate))
			return false;
		if (department == null) {
			if (other.department != null)
				return false;
		} else if (!department.equals(other.department))
			return false;
		if (id != other.id)
			return false;
		if (salary != other.salary)
			return false;
		return true;
	}
	
	
}
