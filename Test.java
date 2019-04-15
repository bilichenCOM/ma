import hw10.AsciiCharSequence;

public class Test {
	public static void main(String[] args) {
		byte[] example = {72,101, 108, 108, 111, 33};
		AsciiCharSequence answer = new AsciiCharSequence(example);
		String string = new String(example);
		System.out.println("Послідовінсть - " + answer.toString());// Hello!
		System.out.println("Ромір - " + answer.length());// 6
		System.out.println("Символ під № 1 - " + answer.charAt(1));// e
		System.out.println("Послідовність - " + answer.subSequence(1, 5));// ello
		System.out.println(answer.toString());// Hello!
		example[0] = 74;
		System.out.println(answer.toString());// Hello!
		System.out.println(string);
	}
}