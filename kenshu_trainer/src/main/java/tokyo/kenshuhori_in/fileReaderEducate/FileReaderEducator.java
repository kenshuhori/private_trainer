package tokyo.kenshuhori_in.fileReaderEducate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import tokyo.kenshuhori_in.SubMainInterface;

public class FileReaderEducator implements SubMainInterface {

	private File file;

	public FileReaderEducator() {
		file = new File("./dbcopy_ini/imp_errignorelist.ini");
	}

	public static void main(String[] args) throws IOException {
		new FileReaderEducator().execute();
	}

	@Override
	public void execute() throws IOException {
		System.out.println(file.getAbsolutePath());
		BufferedReader br = null;
		try {
			// ファイルを読み込むバッファドリーダを作成します。
			System.out.println("FILE : " + file);
			System.out.println("AbsoluteFILE : " + file.getAbsoluteFile());
			System.out.println("FILEName : " + file.getName());
			br = new BufferedReader(new InputStreamReader(new FileInputStream(file.getAbsoluteFile())));
			// 読み込んだ文字列を保持するストリングバッファを用意します。
			StringBuffer sb = new StringBuffer();
			// ファイルから読み込んだ一文字を保存する変数です。
			int c;
			// ファイルから１文字ずつ読み込み、バッファへ追加します。
			while ((c = br.read()) != -1) {
				sb.append((char) c);
			}
			// バッファの内容を文字列化して返します。
			System.out.println("RESULT : " + sb.toString());
		} finally {
			// リーダを閉じます。
			br.close();
		}
	}

}
