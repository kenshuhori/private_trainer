package tokyo.kenshuhori_in.fileZip;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileTransfer {
	private String sep = FileSystems.getDefault().getSeparator();
	private Path tempDir = Paths.get("." + sep + "tempDir");
	private Path zipDir = Paths.get("WebContent\\CUlogs.zip");
	public static final String CONTROLLER = "Controller";
	public static final String EXECUTOR = "Executor";
	private byte[] buffer = new byte[1024];
    private int len;

    /**
     * targetDir以下をtempDirフォルダへまとめます。
     * ※注意※排他制御は行っていません。
     * 複数ユーザーが同時に操作する場合を考慮する場合、
     * 排他制御の機能を追加する必要があります。
     *
     * @param targetDir
     * @param serverType
     * @throws IOException
     */
    public void collectLogs(Path targetDir, String serverType) throws IOException {
    	try {
    		getTargetFile(targetDir, serverType);
    	} catch (Exception e) {
    		System.out.println("failed to collect logs to tempDir.");
    		System.out.println("targetDir : " + targetDir.toFile().getAbsolutePath());
    		throw e;
    	}
	}

    /**
     * tempDirフォルダをzipにまとめ、WebContent配下へCUlogs.zipとして配置します。
     * その後、tempDirフォルダを削除されます。
     * @throws IOException
     */
    public void createZip() throws IOException{
    	try {
			startZip(tempDir);
			deleteDir(tempDir);
		} catch (IOException e) {
			System.out.println("failed to create CUlogs.zip.");
			throw e;
		}
    }

    /**
     * pathのディレクトリ以下を全て削除します。
     * @param path
     */
	public void deleteDir(Path path) {
		File targetFile = path.toFile();
		if(targetFile.exists()) {
			if(targetFile.isFile()) {
				targetFile.delete();
			} else {
				File[] files = targetFile.listFiles();
				if(files == null) {
					System.out.println("fail does not exits under " + targetFile.getAbsolutePath());
				}
				for (File file : files) {
					if(file.exists() == false) {
						continue;
					} else {
						deleteDir(file.toPath());
						file.delete();
					}
				}
			}
		} else {
			System.out.println(targetFile.getAbsolutePath() + " does not exits");
		}
		targetFile.delete();
	}

	/**
	 * path以下のディレクトリをnewDir配下へコピーします
	 * @param files
	 * @param serverType
	 * @throws IOException
	 */
	private void getTargetFile(Path path, String serverType) throws IOException {
		if (path.toFile().isDirectory()) {
			File[] files = path.toFile().listFiles();
			Arrays.stream(files).forEach(file -> {
				try {
					copyAllToOutDir(file, serverType);
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			});
		} else {
			copyAllToOutDir(path.toFile(), serverType);
		}
	}

	private void copyAllToOutDir(File file, String serverType) throws IOException {
		if(file.isDirectory()) {
			File[] files = file.listFiles();
			if(files.length != 0) {
				getTargetFile(file.toPath(), serverType);
			}
			return;
		}
		Path outDir = convertToDir(file, serverType);
		Files.copy(file.toPath(), outDir, StandardCopyOption.REPLACE_EXISTING);
	}

	private Path convertToDir(File file, String serverType) throws IOException {
		String path = file.toPath().toString();
		Path convPath;
		convPath = tempDir.resolve(serverType).resolve(path);
		String[] str = convPath.toString().split("\\\\|\\/");
		Path basePath = Paths.get(str[0]);
		for(int i = 1; i < str.length; i++) {
			basePath = basePath.resolve(str[i]);
			try {
				Files.createDirectories(basePath);
			} catch (IOException e) {
				System.out.println("error has occured at createDirectories : " + basePath.toFile().getAbsolutePath());
				throw e;
			}
		}
		return convPath;
	}

	/**
	 * pathをout.zipとしてzip圧縮します。
	 * @param files
	 * @throws IOException
	 */
	private void startZip(Path zipPath) throws IOException {
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipDir.toString()));
		File[] files = zipPath.toFile().listFiles();
		createZip(files, out);
		out.close();
    }

	private void createZip(File[] files, ZipOutputStream out) throws IOException {
		for(File file : files) {
			if(!file.exists()) {
				continue;
			}
			if(file.isDirectory()) {
				Path subFolder = file.toPath();
				if(subFolder.toFile().listFiles().length != 0) {
					createZip(subFolder.toFile().listFiles(), out);
				}
				continue;
			}
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
			out.putNextEntry(new ZipEntry(getRelative(tempDir, file.toPath())));
			while ((len = in.read(buffer)) > 0) {
	            out.write(buffer, 0, len);
	        }
	        in.close();
		}
	}

	private String getRelative(Path par, Path child) {
		return par.relativize(child).toString();
	}
}
