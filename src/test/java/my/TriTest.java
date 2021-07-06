package my;

import static org.junit.Assert.*;
import java.util.Date;

import my.helper.Tri;
import org.junit.Test;

public class TriTest {
	@Test
	public void testTri() {
		String v1 = "Value1";
		long v2 = 2;
		Date v3 = new Date();
		
		Tri<String,Long,Date> t = new Tri();
		t.v1 = v1;
		t.v2 = v2;
		t.v3 = v3;
		assertEquals(v1, t.v1);
		assertEquals(Long.valueOf(v2), t.v2);
		assertEquals(v3, t.v3);
	}
}
