package telran.employees.services;

import telran.employees.dto.Employee;
import telran.employees.dto.EmployeesCodes;
import telran.employees.dto.SalaryRangeEmployees;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;

public class EmployeesMethodsMapsImpl implements EmployeesMethods {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	private HashMap<Long, Employee> employees = new HashMap<>();
	private HashMap<String, List<Employee>> employeesDep = new HashMap<>();
	private TreeMap<Integer, List<Employee>> employeesSalary = new TreeMap<>();
	private TreeMap<Integer, List<Employee>> employeesAge = new TreeMap<>(); // key - birth year

	static final ReentrantReadWriteLock empLock = new ReentrantReadWriteLock();
	static final Lock empLockRead = empLock.readLock();
	static final Lock empLockWrite = empLock.writeLock();

	static final ReentrantReadWriteLock depLock = new ReentrantReadWriteLock();
	static final Lock depLockRead = depLock.readLock();
	static final Lock depLockWrite = depLock.writeLock();

	static final ReentrantReadWriteLock salaryLock = new ReentrantReadWriteLock();
	static final Lock salaryLockRead = salaryLock.readLock();
	static final Lock salaryLockWrite = salaryLock.writeLock();

	static final ReentrantReadWriteLock ageLock = new ReentrantReadWriteLock();
	static final Lock ageLockRead = ageLock.readLock();
	static final Lock ageLockWrite = ageLock.writeLock();

	private EmployeesMethodsMapsImpl() {

	}

	public static EmployeesMethods getEmptyEmployees() {
		return new EmployeesMethodsMapsImpl();
	}

	@Override
	public EmployeesCodes addEmployee(Employee empl) {
		empLockWrite.lock();
		try {
			var res = employees.putIfAbsent(empl.getId(), empl);
			if (res != null) {
				return EmployeesCodes.ALREADY_EXISTS;
			}
			addEmployeeDep(empl);
			addEmployeeSalary(empl);
			addEmployeeAge(empl);
			return EmployeesCodes.OK;
		} finally {
			empLockWrite.unlock();
		}
	}

	private void addEmployeeAge(Employee empl) {
		ageLockWrite.lock();
		try {
			employeesAge.computeIfAbsent(empl.getBirthDate().getYear(), e -> new LinkedList<>()).add(empl);
		} finally {
			ageLockWrite.unlock();
		}

	}

	private void addEmployeeSalary(Employee empl) {
		salaryLockWrite.lock();
		try {
			employeesSalary.computeIfAbsent(empl.getSalary(), e -> new LinkedList<Employee>()).add(empl);
		} finally {
			salaryLockWrite.unlock();
		}

	}

	private void addEmployeeDep(Employee empl) {
		depLockWrite.lock();
		try {
			employeesDep.computeIfAbsent(empl.getDepartment(), e -> new LinkedList<Employee>()).add(empl);
		} finally {
		}
		depLockWrite.unlock();

	}

	@Override
	public EmployeesCodes removeEmployee(long id) {
		empLockWrite.lock();
		try {
		Employee empl = employees.remove(id);
		if (empl == null) {
			return EmployeesCodes.NOT_FOUND;
		}
		removeEmployeeDep(empl);
		removeEmployeeSalary(empl);
		removeEmployeeAge(empl);
		return EmployeesCodes.OK;
		} finally {
			empLockWrite.unlock();
		}
	}

	private void removeEmployeeAge(Employee employee) {
		ageLockWrite.lock();
		try {
		employeesAge.compute(employee.getBirthDate().getYear(), (k, v) -> {
			v.remove(employee);

			if (v.size() == 0) {
				return null;
			}

			return v;
		});
		} finally {
			ageLockWrite.unlock();
		}

	}

	private void removeEmployeeSalary(Employee employee) {
		salaryLockWrite.lock();
		try {
		employeesSalary.compute(employee.getSalary(), (k, v) -> {
			v.remove(employee);

			if (v.size() == 0) {
				return null;
			}

			return v;
		});
		} finally {
			salaryLockWrite.unlock();
		}

	}

	private void removeEmployeeDep(Employee employee) {
		depLockWrite.lock(); 
		try {
		employeesDep.compute(employee.getDepartment(), (k, v) -> {
			v.remove(employee);

			if (v.size() == 0) {
				return null;
			}

			return v;
		});
		} finally {
			depLockWrite.unlock();
		}

	}

	@Override
	public EmployeesCodes updateSalary(long id, int newSalary) {
		empLockWrite.lock(); 
		try {
		var replacingEmployee = getEmployee(id);
		if (replacingEmployee == null) {
			return EmployeesCodes.NOT_FOUND;
		}
		if (replacingEmployee.getSalary() == newSalary) {
			return EmployeesCodes.NO_UPDATE_REQUIRED;
		}
		Employee newEmployee = new Employee(id, newSalary, replacingEmployee.getBirthDate(),
				replacingEmployee.getDepartment());
		replaceEmployee(replacingEmployee, newEmployee);
		return EmployeesCodes.OK;
		} finally {
			empLockWrite.unlock();
		}
	}

	private void replaceEmployee(Employee replacingEmploeey, Employee newEmploeey) {
		removeEmployee(replacingEmploeey.getId());
		addEmployee(newEmploeey);
	}

	@Override
	public EmployeesCodes updateDepartment(long id, String newDepartment) {
		depLockWrite.lock();
		try {
		var replacingEmployee = getEmployee(id);
		if (replacingEmployee == null) {
			return EmployeesCodes.NOT_FOUND;
		}
		if (replacingEmployee.getDepartment().equals(newDepartment)) {
			return EmployeesCodes.NO_UPDATE_REQUIRED;
		}
		Employee newEmploeey = new Employee(id, replacingEmployee.getSalary(), replacingEmployee.getBirthDate(),
				newDepartment);
		replaceEmployee(replacingEmployee, newEmploeey);
		return EmployeesCodes.OK;
		} finally {
			depLockWrite.unlock();
		}
	}

	@Override
	public Employee getEmployee(long id) {
		empLockRead.lock(); 
		try {
		return employees.get(id); 
		} finally {
			empLockRead.unlock();
		} 
		
	}

	private static <T> Iterable<Employee> getSubMapResult(TreeMap<T, List<Employee>> treeMap, T from, T to) {

		return treeMap.subMap(from, to).values().stream().flatMap(List::stream).toList();
	}

	@Override
	public Iterable<Employee> getEmployeesBySalary(int fromInclusive, int toExclusive) {
		salaryLockRead.lock(); 
		try {
		return getSubMapResult(employeesSalary, fromInclusive, toExclusive);
		} finally {
			salaryLockRead.unlock();
		}
	}

	@Override
	public Iterable<Employee> getEmployeesByAge(int fromInclusive, int toExclusive) {
		ageLockRead.lock(); 
		try {
		var currentYear = LocalDate.now().getYear();
		var minYear = currentYear - toExclusive + 1;
		var maxYear = currentYear - fromInclusive + 1;
		return getSubMapResult(employeesAge, minYear, maxYear);
		} finally {
			ageLockRead.unlock();
		}
	}

	@Override
	public Iterable<Employee> getEmployeesByDepartment(String department) {
		depLockRead.lock(); 
		try {
		var res = employeesDep.get(department);
		return res == null ? Collections.emptyList() : res;
		} finally {
			depLockRead.unlock();
		}
	}

	@Override
	public Iterable<Employee> getAllEmployees() {
		empLockRead.lock(); 
		try {
		return employees.values();
		} finally {
			empLockRead.unlock();
		}
	}

	@Override
	public Map<String, Integer> getDepartmentsSalary() {
		depLockRead.lock(); 
		try { 
		return employeesDep.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey,
				e -> (int) e.getValue().stream().mapToInt(Employee::getSalary).average().orElse(0)));
		} finally {
			depLockRead.unlock();
		}

	}

	@Override
	public List<SalaryRangeEmployees> distributionSalary(int step) {
		salaryLockRead.lock();
		try {
		interval = step;
		return employees.values().stream()
				.collect(Collectors.groupingBy(e -> e.getSalary() / step, TreeMap::new, Collectors.toList())).entrySet()
				.stream().map(EmployeesMethodsMapsImpl::getSalaryRange).toList();
		} finally {
			salaryLockRead.unlock();	
		}
	}

	private static int interval;

	private static SalaryRangeEmployees getSalaryRange(Map.Entry<Integer, List<Employee>> e) {
		int nInterval = e.getKey();
		int minSalary = nInterval * interval;
		SalaryRangeEmployees sre = new SalaryRangeEmployees(minSalary, minSalary + interval, e.getValue());
		return sre;
	}

	@Override
	public void save(String filePath) throws Exception {
		empLockWrite.lock(); 
		try {
		try (ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(filePath))) {
			writer.writeObject(this);
		}
		} finally {
			empLockWrite.unlock();
		}

	}

}
