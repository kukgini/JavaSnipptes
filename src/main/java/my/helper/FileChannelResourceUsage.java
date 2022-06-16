package my.helper;

public class FileChannelResourceUsage {
	public static void main(String[] args) {
		new FileChannelResource().use("test.txt", (it,channel) -> {
            it.writeOn(channel, "이렇게 쓰는거에요.");
		});
	}
}
