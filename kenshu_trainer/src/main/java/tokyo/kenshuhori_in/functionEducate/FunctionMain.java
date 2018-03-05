package tokyo.kenshuhori_in.functionEducate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.DoubleAccumulator;

import tokyo.kenshuhori_in.SubMainInterface;

public class FunctionMain implements SubMainInterface {
//	private Double answer = 0.0;
	private Map<String, Double> testMap = new HashMap<String, Double>();
	DoubleAccumulator accumulator = new DoubleAccumulator((x, y) -> x + y, 0.0);

	public FunctionMain() {
		testMap.put("USR_JINJI", 101.0);
		testMap.put("USR_KYUYO", 103.0);
		testMap.put("USR_WORK", 104.0);
		testMap.put("USR_SHURO", 105.0);
		testMap.put("USR_INDEX01", 102.0);
	}

	@Override
	public void execute() {
//		DoubleUnaryOperator sum = (a) -> answer += a;
//		Consumer<Double> log = (a) -> System.out.println(a);

		testMap.entrySet().stream()
		.map(record -> record.getValue())
		.forEach(value -> accumulator.accumulate(value));

//		testMap.entrySet().stream()
//		.map(record -> record.getValue())
//		.forEach(size -> log.accept(size));

		System.out.println("answer : " + accumulator.get());
	}

}
