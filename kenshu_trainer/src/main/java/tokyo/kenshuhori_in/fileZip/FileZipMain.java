package tokyo.kenshuhori_in.fileZip;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import tokyo.kenshuhori_in.SubMainInterface;

public class FileZipMain implements SubMainInterface {

	/**
	 *
	 * @throws IOException
	 * @Override
	 */
	public void execute() {
		FileTransfer ft = new FileTransfer();
		ft.deleteDir(Paths.get("WebContent\\CUlogs.zip"));
		Path cLogsDir = Paths.get(".\\logs");
		
		try {
			ft.collectLogs(cLogsDir, FileTransfer.CONTROLLER);
//			Path eLogsDir = Paths.get("C:\\COMPANY_UPDATER\\executor\\logs");
//			ft.collectLogs(eLogsDir, FileTransfer.EXECUTOR);

			Path dbDir = Paths.get(".\\updater.db");
			ft.collectLogs(dbDir, FileTransfer.CONTROLLER);

			ft.createZip();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
