package my;

import static org.junit.Assert.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.junit.Test; 

public class DirUtilTest {
    @Test
    public void testFindFile() {
    	Optional<Path> found = DirUtil.findFileFirst(".", "TEXTFILE.TXT");
    	assertEquals(Paths.get("./data/TEXTFILE.TXT"), found.get());
    }
    @Test
    public void testListFles() {
    	List<Path> expected = new ArrayList<Path>();
    	expected.add(Paths.get("./data/TEXTFILE.TXT"));
    	
    	assertEquals(
			expected,
			DirUtil.listFiles("./data").collect(Collectors.toList())
		);
    }
    @Test
    public void testWalkFles() {
    	Set<Path> expected = new TreeSet<Path>();
    	expected.add(Paths.get("./data/TEXTFILE.TXT"));
    	expected.add(Paths.get("./data/subdata/SUBFILE.TXT"));
    	
    	Set<Path> actual = DirUtil.walkFiles("./data")
    			.sorted()
    			.collect(Collectors.toSet());
    	
    	assertEquals(expected, actual);
    }
}
