package telran.performance;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestFunctional {
	int n = 5;
	String[] arr;
	String expected = "string;string;string;string;string;";
	@BeforeEach
	void setUp(){
		arr = new String[n];
		for (int i = 0; i < n; i++) {
			arr[i] = "string";
		}
		
	}
	@Test
	void testFunctionalStringJoinString() {
		setUp();
		StringJoin stringJoinString = new StringJoinString();
		String str = stringJoinString.join(";",arr);
		assertEquals(str.length(), (arr[0].length()+1)*n );
		assertEquals(str, expected);
		
	}
	
	@Test
	void testFunctionalStringJoinBuilder() {
		setUp();
		StringJoin stringJoinBuilder = new StringJoinBuilder();
		String str = stringJoinBuilder.join(";", arr);
		assertEquals(str.length(), (arr[0].length()+1)*n);
		assertEquals(str, expected);
	}
}
