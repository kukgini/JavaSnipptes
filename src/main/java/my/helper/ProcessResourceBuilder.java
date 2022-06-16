package my.helper;

public class ProcessResourceBuilder {
    public ProcessResource build(String osType) {
        if ("posix".equalsIgnoreCase(osType)) {
            return new ProcessResource.PosixProcessResource();
        } else if ("windows".equalsIgnoreCase(osType)) {
            return new ProcessResource.WindowsProcessResource();
        } else {
            return null;
        }
    }
}
