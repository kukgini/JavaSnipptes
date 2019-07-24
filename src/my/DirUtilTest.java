package my;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    @Test
    public void testClear() throws IOException {
    	Path clearTarget = Paths.get("clearTarget");
    	if (clearTarget.toFile().exists()) {
	    	Files.delete(clearTarget);
    	}
    	Files.copy(Paths.get("data"), clearTarget);
    	Set<Path> expected = DirUtil.walkFiles(clearTarget)
    			.collect(Collectors.toSet());    	
    	Set<Path> actual = DirUtil.walkFiles(clearTarget)
    			.collect(Collectors.toSet());
    	assertEquals(expected, actual);
    	
    	DirUtil.clear(clearTarget);
    	assertEquals(0, DirUtil.walkFiles(clearTarget).count());
    	Files.delete(clearTarget);
    }
}
