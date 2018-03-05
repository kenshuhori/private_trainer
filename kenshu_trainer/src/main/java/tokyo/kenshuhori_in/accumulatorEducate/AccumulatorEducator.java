package tokyo.kenshuhori_in.accumulatorEducate;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.DoubleAccumulator;

import tokyo.kenshuhori_in.SubMainInterface;

public class AccumulatorEducator implements SubMainInterface {

	Map<String, Double> tableSpaceMap;

	public static void main(String[] args) {
		new AccumulatorEducator().execute();
	}

	public AccumulatorEducator() {
		tableSpaceMap = new HashMap<String, Double>();
		tableSpaceMap.put("SYSTEM", 100.0);
		tableSpaceMap.put("USER_JINJI", 200.0);
		tableSpaceMap.put("USER_KYUYO", 300.0);
		tableSpaceMap.put("USER_WORK", 400.0);
		tableSpaceMap.put("USER_INDEX", 500.0);
	}

	@Override
	public void execute() {
		double sum = sumTotalSpace(tableSpaceMap);
		System.out.println("表領域の合計は : " + sum);
	}

	private double sumTotalSpace(Map<String, Double> tableSpaceMap) {
		DoubleAccumulator acc = new DoubleAccumulator((x, y) -> x + y, 0.0);
		tableSpaceMap.entrySet().stream()
				.peek(record -> System.out.println(record.getKey() + " : " + record.getValue()))
				.map(record -> record.getValue())
				.forEach(oneUsedSize -> acc.accumulate(oneUsedSize));
		return acc.get();
	}


}
