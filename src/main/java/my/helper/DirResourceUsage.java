package my.helper;

public class DirResourceUsage {
    public static void main(String[] args) {
        DirResource.ls(System.getProperty("java.io.tmpdir"), "*.txt", System.out::println);
    }
}
