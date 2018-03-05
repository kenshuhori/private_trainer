package tokyo.kenshuhori_in.process;

import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;

public class ProcessEducator {

	public void detectIfProcessStopped() {
		String serviceName = "iexplore.exe";
		String command = CommandGetter.getWmicCmdBySeviceName(serviceName);
		try {
			Process process = Runtime.getRuntime().exec(command);
			process.waitFor();

			InputStream input = process.getInputStream();
	        List<String> processOutput = IOUtils.readLines(input);
	        String processId = processOutput.get(2).trim();
	        IOUtils.closeQuietly(input);
	        System.out.println("ProcessId of " + serviceName + " : " + processId);
	        if (!processId.isEmpty()) {
	        	String killCommand = CommandGetter.getCommandTaskKillByPid(processId);
	        	Runtime.getRuntime().exec(killCommand);
	        }
		} catch (Exception e) {
			System.out.println("error : " + e);
		}
	}

	public void getMyProcess() {
		String processId = java.lang.management.ManagementFactory.getRuntimeMXBean().getName().split("@")[0];
    	System.out.println("processId : " + processId);
	}

	private static class CommandGetter {
		public static String getWmicCmdBySeviceName(String serviceName) {
	    	return "wmic process where (name = '" + serviceName + "') get processid";
		}

		public static String getCommandTaskKillByPid(String pid) {
	        return String.format("taskkill /pid \"%s\" /F", pid);
		}
	}
}
