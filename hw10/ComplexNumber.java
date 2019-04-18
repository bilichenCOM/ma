package hw10;

public final class ComplexNumber {
	private final double real;
	private final double imaginary;

	public ComplexNumber(double real, double imaginary) {
		this.real = real;
		this.imaginary = imaginary;
	}

	public double getReal() {
		return real;
	}

	public double getImaginary() {
		return imaginary;
	}

	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}

		if (o instanceof ComplexNumber) {
			ComplexNumber other = (ComplexNumber) o;
			return other.getReal() == this.getReal() && other.getImaginary() == this.getImaginary();
		}

		return false;
	}

	public int hashCode() {
		return Double.hashCode(real) ^ Double.hashCode(imaginary);
	}
}