package my.helper;

public class DirUtilUsage {
    public static void main(String[] args) {
        DirUtil.ls(System.getProperty("java.io.tmpdir"), "*.txt", System.out::println);
    }
}
