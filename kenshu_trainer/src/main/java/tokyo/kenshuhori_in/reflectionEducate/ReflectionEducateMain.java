package tokyo.kenshuhori_in.reflectionEducate;

import java.lang.reflect.Method;

import tokyo.kenshuhori_in.SubMainInterface;

public class ReflectionEducateMain implements SubMainInterface {

	String strClass = "tokyo.kenshuhori_in.reflectionEducate.ReflectionChild";
	String strMethod1 = "setMyName";
	String strMethod2 = "showMyName";
	String myName = "Kenshu Hori";

	public static void main(String[] args) {
		new ReflectionEducateMain().execute();
	}

	public ReflectionEducateMain() {
		super();
		System.out.println("コンストラクタ発動");
	}
	static {
		System.out.println("static initializer発動");
	}

	@Override
	public void execute() {
		try {
			//クラスの取得
			Class<?> c = Class.forName(strClass);
			//インスタンス生成
			Object myObj = c.newInstance();

			//メソッド(setMyName)の取得と実行
			Method method = c.getMethod(strMethod1, String.class);
			method.invoke(myObj, myName);
			//メソッド(showMyName)の取得と実行
			method = c.getMethod(strMethod2);
			method.invoke(myObj);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
