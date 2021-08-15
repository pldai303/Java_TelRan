package telran.employeers.services;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.employeers.dto.Employee;
import telran.employeers.dto.EmployeesCodes;

class EmployeesTest {

	private static final int EMPLOYEES_N = 5;
	private EmployeesMethodsMapsImpl obj = new EmployeesMethodsMapsImpl();
	private Employee[] arr = new Employee[EMPLOYEES_N];
	private Iterable<Employee> empList;

	private int checkSalary(int fromInclusive, int toExclusive) {
		int res = 0;
		for (Employee empl : empList) {
			int salary = empl.getSalary();
			if ((fromInclusive <= salary) && (salary < toExclusive)) {
				res++;
			}
		}
		return res;
	}

	private int checkDepartment(String department) {
		int res = 0;
		for (Employee empl : empList) {
			if (empl.getDepartment() == department) {
				res++;
			}
		}
		return res;
	}

	private int checkAge(int fromInclusive, int toExclusive) {
		int res = 0;
		for (Employee empl : empList) {
			int year = LocalDate.now().getYear() - empl.getBirthDate().getYear();
			if ((fromInclusive <= year) && (year < toExclusive)) {
				res++;
			}
		}

		return res;
	}

	@BeforeEach
	void setUp() {
		arr[0] = new Employee(10001, 5750, LocalDate.of(1990, 10, 22), "Security");
		arr[1] = new Employee(10002, 5850, LocalDate.of(1986, 8, 6), "Kitchen");
		arr[2] = new Employee(10003, 5900, LocalDate.of(1988, 6, 25), "Office");
		arr[3] = new Employee(10004, 6750, LocalDate.of(1993, 1, 5), "Security");
		arr[4] = new Employee(10005, 9750, LocalDate.of(1991, 7, 22), "Office");
		for (int i = 0; i < EMPLOYEES_N; i++) {
			obj.addEmployee(arr[i]);
		}
		empList = obj.getAllEmployees();
	}

	@Test
	void getEmploee() {
		Employee test1 = new Employee(10001, 5750, LocalDate.of(1990, 10, 22), "Security");
		Employee test2 = new Employee(10001, 5751, LocalDate.of(1990, 10, 22), "Security");
		Employee expected = obj.getEmployee(10001);
		assertTrue(expected.equals(test1));
		assertFalse(expected.equals(test2));
		expected = obj.getEmployee(00000);
		assertEquals(expected, null);
	}

	@Test
	void addEmployees() {
		setUp();
		assertEquals(5, obj.countAllEmployees());
		Employee testEployee = new Employee(10006, 10000, LocalDate.of(1987, 6, 21), "Office");
		obj.addEmployee(testEployee);
		assertEquals(EmployeesCodes.ALREADY_EXISTS, obj.addEmployee(testEployee));
		assertEquals(6, obj.countAllEmployees());
	}

	@Test
	void eployeesBySalary() {
		setUp();
		int fromInclusive = 5000;
		int toExclusive = 6000;
		empList = obj.getEmployeesBySalary(fromInclusive, toExclusive);
		assertEquals(3, checkSalary(fromInclusive, toExclusive));
		obj.addEmployee(new Employee(10006, 5000, LocalDate.of(1991, 9, 22), "Service"));
		obj.addEmployee(new Employee(10007, 6000, LocalDate.of(1991, 9, 22), "Service"));
		empList = obj.getEmployeesBySalary(fromInclusive, toExclusive);
		assertEquals(4, checkSalary(fromInclusive, toExclusive));
	}

	@Test
	void updateSalary() {
		setUp();
		assertEquals(EmployeesCodes.NOT_FOUND, obj.updateSalary(20001, 10000));
		assertEquals(EmployeesCodes.UNSATISFIED_VALUE, obj.updateSalary(10001, -5750));
		int emplId = 10002;
		int salary = 10000;
		obj.updateSalary(emplId, salary);
		assertEquals(salary, obj.getEmployee(emplId).getSalary());
	}

	@Test
	void emplooyesByDepartment() throws Exception {
		setUp();
		String expectedMessage = "No such department";
		try {
			empList = obj.getEmployeesByDepartment("TestDepartment");
		} catch (Exception e) {
			assertEquals(expectedMessage, e.getMessage());
		}
		String department = "Office";
		empList = obj.getEmployeesByDepartment(department);
		assertEquals(2, checkDepartment(department));
	}

	@Test
	void employessByAge() {
		setUp();
		int fromInclusive = 30;
		int toExclusive = 29;
		String expectedMessage = "Arguments not correct";
		try {
			empList = obj.getEmployeesByAge(fromInclusive, toExclusive);
		} catch (Exception e) {
			assertEquals(expectedMessage, e.getMessage());
		}
		fromInclusive = 30;
		toExclusive = 32;
		empList = obj.getEmployeesByAge(fromInclusive, toExclusive);
		assertEquals(2, checkAge(fromInclusive, toExclusive));

	}

	@Test
	void updateDepartment() {
		setUp();
		assertEquals(EmployeesCodes.NOT_FOUND, obj.updateDepartment(20001, "Office"));
		int emplId = 10002;
		String department = "New department";
		obj.updateDepartment(emplId, department);
		assertEquals(department, obj.getEmployee(emplId).getDepartment());
	}

	@Test
	void removeEployeesTest() {
		setUp();
		assertEquals(EmployeesCodes.NOT_FOUND, obj.removeEmployee(11111));
		obj.removeEmployee(10001);
		obj.removeEmployee(10005);
		assertEquals(3, obj.countAllEmployees());
		for (Employee empl : empList) {
			obj.removeEmployee(empl.getId());
		}
		assertEquals(0, obj.countAllEmployees());
	}

}
