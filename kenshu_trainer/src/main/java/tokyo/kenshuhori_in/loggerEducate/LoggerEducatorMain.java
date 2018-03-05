package tokyo.kenshuhori_in.loggerEducate;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;

import tokyo.kenshuhori_in.SubMainInterface;

public class LoggerEducatorMain implements SubMainInterface {

	public static void main(String[] args) {
		new LoggerEducatorMain().execute();
	}

	private static Logger logger;
	private static FileHandler fileHandler;
	private static ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    private static StreamHandler streamHandler;
    private static ConsoleHandler consoleHandler;

    public LoggerEducatorMain() {

    }

    @Override
	public void execute() {
    	setUp();
		outputLogger();
		System.out.println("⑬size : " + byteArrayOutputStream.size());
	}

	public void setUp() {
		logger = Logger.getLogger("LoggerEducator");
		try {
            fileHandler = new FileHandler("logs\\loggerEducator.log");
            fileHandler.setLevel(Level.SEVERE);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);

            streamHandler = new StreamHandler(byteArrayOutputStream, new SimpleFormatter());
            streamHandler.setLevel(Level.SEVERE);
            logger.addHandler(streamHandler);

//            consoleHandler = new ConsoleHandler();
//            consoleHandler.setLevel(Level.FINEST);
//            logger.addHandler(consoleHandler);

        } catch (IOException e) {
            logger.log(Level.SEVERE, "Fail init logger to file", e);
        }
	}

	private void outputLogger() {
		logger.log(Level.OFF, "OFFです");
		System.out.println("①size : " + byteArrayOutputStream.size());
		logger.log(Level.ALL, "ALLです");
		System.out.println("②size : " + byteArrayOutputStream.size());
		logger.log(Level.FINEST, "FINESTです");
		System.out.println("③size : " + byteArrayOutputStream.size());
		logger.log(Level.FINER, "FINERです");
		System.out.println("④size : " + byteArrayOutputStream.size());
		logger.log(Level.FINE, "FINEです");
		System.out.println("⑤size : " + byteArrayOutputStream.size());
		logger.log(Level.CONFIG, "CONFIGです");
		System.out.println("⑥size : " + byteArrayOutputStream.size());
		logger.log(Level.INFO, "INFOです");
		System.out.println("⑦size : " + byteArrayOutputStream.size());
		logger.log(Level.WARNING, "WARNINGです");
		System.out.println("⑧size : " + byteArrayOutputStream.size());
		logger.log(Level.SEVERE, "SEVEREです");
		System.out.println("⑨size : " + byteArrayOutputStream.size());
		streamHandler.flush();

		try {
			System.out.println("⑩size : " + byteArrayOutputStream.size());
			throw new IOException();
		} catch (Exception e) {
			System.out.println("⑪size : " + byteArrayOutputStream.size());
			logger.log(Level.SEVERE, "", e);
			System.out.println("⑫size : " + byteArrayOutputStream.size());
		}

	}

}
