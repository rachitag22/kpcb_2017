import static org.junit.Assert.*;

import org.junit.Test;

public class KPCBHashMapTest {

	@Test
	public void test() {
		KPCBHashMap<String> testMap = new KPCBHashMap<String>(10);
		
		testMap.set("Hello", "World");
		testMap.set("Rachit", "Agarwal");
		testMap.set("KPCB", "Application");
		testMap.set("Airbnb", "Belo");
		testMap.set("Pinterest", "Board");
		
		testMap.print();
		
		assertTrue(testMap.set("This is", "kinda cool"));
		assertEquals(testMap.get("Hello"), "World");
		assertTrue(testMap.set("KPCB", "Fellow"));
		assertEquals(testMap.get("KPCB"), "Fellow");
		assertEquals(testMap.delete("KPCB"), "Fellow");
		assertEquals(testMap.get("KPCB"), null);
		assertTrue(testMap.load() == 0.5);
		
	}
	
	@Test
	public void newTest() {
		// Add your own testing code here
	}

}
