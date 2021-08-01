package telran.performance;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestPerformance {

	@Test
	void testStringJoinString() {
		StringJoin stringJoinString = new StringJoinString();
		StringJoinPerformance stringJoinPerformance = new StringJoinPerformance(
					100000, stringJoinString, 
					"Test String Join String", 1);
		stringJoinPerformance.run();
	}
	
	@Test
	void testStringJoinBuilder() {
		StringJoin stringJoinBuilder = new StringJoinBuilder();
		StringJoinPerformance stringJoinPerformance = new StringJoinPerformance(
					100000, stringJoinBuilder, 
					"Test String Join Builder", 1);
		stringJoinPerformance.run();
	}
	
	


}
