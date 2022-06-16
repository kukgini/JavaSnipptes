package my.helper;

public class ConsoleHelperUsage {
	public static void main(String[] args) {
		ConsoleHelper.lines(x -> {
			if ("Q".equals(x)) System.exit(0);
			System.out.printf("==>%s%n",x);
		});
	}
}
