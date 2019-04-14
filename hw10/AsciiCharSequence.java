package hw10;

import java.util.Arrays;

public class AsciiCharSequence implements CharSequence {
	private final byte[] value;

	public AsciiCharSequence(String string) {
		this.value = string.getBytes();
	}

	public AsciiCharSequence(byte[] sequence) {
		this.value = sequence;
	}

	@Override
	public int length() {
		return value.length;
	}

	@Override
	public char charAt(int index) {
		return (char) value[index];
	}

	@Override
	public CharSequence subSequence(int start, int end) {
		return new AsciiCharSequence(Arrays.copyOfRange(value, start, end));
	}

	public String toString() {
		return new String(value);
	}
}
