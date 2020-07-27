package app.wordyourself;

import java.util.function.Function;

public class Test {

	public static void main(String[] args) {
		Function<String, Void> print = (var s) -> {
		    System.out.println(s);
		    return null;
		};
		print.apply("Hello World !");
	}
}
