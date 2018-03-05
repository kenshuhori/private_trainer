package tokyo.kenshuhori_in.parallelEducate;

import java.util.stream.IntStream;

import tokyo.kenshuhori_in.SubMainInterface;

public class ParallelEducateMain implements SubMainInterface {

	public static void main(String[] args) {
		new ParallelEducateMain().execute();
	}
	
	public ParallelEducateMain () {
		super();
	}
	
	@Override
	public void execute(){
		IntStream.range(1, 10).boxed()
        .parallel()
        .map(x -> "task" + x)
        .forEach(System.out::println);
	}

}
