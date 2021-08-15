package telran.employeers.services;

import telran.employeers.dto.Employee;
import telran.employeers.dto.EmployeesCodes;

import java.time.LocalDate;
import java.util.*;

public class EmployeesMethodsMapsImpl implements EmployeesMethods {

	private HashMap<Long, Employee> employees = new HashMap<>();
	private HashMap<String, List<Employee>> employeesDep = new HashMap<>();
	private TreeMap<Integer, List<Employee>> employeesSalary = new TreeMap<>();
	private TreeMap<Integer, List<Employee>> employeesAge = new TreeMap<>(); // key - birthdate

	@Override
	public EmployeesCodes addEmployee(Employee empl) {
		var res = employees.putIfAbsent(empl.getId(), empl);
		if (res != null) {
			return EmployeesCodes.ALREADY_EXISTS;
		}
		addEmployeeDep(empl);
		addEmployeeSalary(empl);
		addEmployeeAge(empl);
		return EmployeesCodes.OK;
	}

	private void addEmployeeSalary(Employee empl) {
		employeesSalary.computeIfAbsent(empl.getSalary(), n -> new LinkedList<Employee>()).add(empl);
	}

	private void addEmployeeAge(Employee empl) {
		employeesAge.computeIfAbsent(empl.getBirthDate().getYear(), 
				n -> new LinkedList<Employee>()).add(empl);
	}

	private void addEmployeeDep(Employee empl) {
		employeesDep.computeIfAbsent(empl.getDepartment(), 
				n -> new LinkedList<Employee>()).add(empl);
	}

	@Override
	public EmployeesCodes removeEmployee(long id) {
		Employee empl = employees.remove(id);
		if (empl == null) {
			return EmployeesCodes.NOT_FOUND;
		}
		removeEmployeeDep(empl);
		removeEmployeeSalary(empl);
		removeEmployeeAge(empl);
		return EmployeesCodes.OK;
	}

	private void removeEmployeeAge(Employee empl) {
		employeesAge.compute(empl.getBirthDate().getYear(), (k, v) -> {
			v.remove(empl);
			if (v.size() == 0) {
				return null;
			}
			return v;
		});
	}
 
	private void removeEmployeeSalary(Employee empl) {
		employeesSalary.compute(empl.getSalary(), (k, v) -> {
			v.remove(empl);
			if (v.size() == 0) {
				return null;
			}
			return v;
		});
	}

	private void removeEmployeeDep(Employee empl) {
		employeesDep.compute(empl.getDepartment(), (k, v) -> {
			v.remove(empl);
			if (v.size() == 0) {
				return null;
			}
			return v;
		});
	}

	@Override
	public Employee getEmployee(long id) {
		return employees.get(id);
	}

	@Override
	public Iterable<Employee> getEmployeesBySalary(int fromInclusive, int toExclusive) {
		var result = new LinkedList<Employee>();
		employeesSalary.subMap(fromInclusive, toExclusive).values().forEach(result::addAll);
		return result;
	}

	private boolean checkIfExists(long id) {
		if (getEmployee(id) == null) {
			return false;
		}
		return true;
	}

	@Override
	public EmployeesCodes updateSalary(long id, int newSalary) {
		if (checkIfExists(id)) {
			if (newSalary < 0) {
				return EmployeesCodes.UNSATISFIED_VALUE;
			}
			Employee oldEmpl = getEmployee(id);
			Employee empl = new Employee(oldEmpl.getId(), 
					newSalary, oldEmpl.getBirthDate(), oldEmpl.getDepartment());
			removeEmployee(id);
			addEmployee(empl);
			return EmployeesCodes.OK;
		} else {
			return EmployeesCodes.NOT_FOUND;
		}
	}

	@Override
	public EmployeesCodes updateDepartment(long id, String newDepartmen) {
		if (checkIfExists(id)) {
			Employee oldEmpl = getEmployee(id);
			Employee empl = new Employee(oldEmpl.getId(), 
					oldEmpl.getSalary(), oldEmpl.getBirthDate(), newDepartmen);
			removeEmployee(id);
			addEmployee(empl);
			return EmployeesCodes.OK;
		} else {
			return EmployeesCodes.NOT_FOUND;
		}
	}

	@Override
	public Iterable<Employee> getEmployeesByAge(int fromInclusive, int toExclusive) {
		var result = new LinkedList<Employee>();
		int year = LocalDate.now().getYear();
		int val1 = year - fromInclusive;
		int val2 = year - toExclusive;
		try {
			employeesAge.subMap(val2, false, val1, true).values().forEach(result::addAll);
		} catch (Exception e) {
			throw new IllegalArgumentException("Arguments not correct");
		}
		return result;
	}

	@Override
	public Iterable<Employee> getEmployeesByDepartment(String department) {
		LinkedList<Employee> emplByDep = null;
		try {
			emplByDep = new LinkedList<>(employeesDep.get(department));
		} catch (Exception e) {
			throw new NullPointerException("No such department");
		}
		return emplByDep;
	}

	@Override
	public Iterable<Employee> getAllEmployees() {
		return new LinkedList<>(employees.values());
	}

	@Override
	public int countAllEmployees() {
		return employees.size();
	}

}
