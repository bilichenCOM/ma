package hw10;

public final class ComplexNumber {
	private final double re;
	private final double im;

	public ComplexNumber(double re, double im) {
		this.re = re;
		this.im = im;
	}

	public double getRe() {
		return re;
	}

	public double getIm() {
		return im;
	}

	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}

		if (o instanceof ComplexNumber) {
			ComplexNumber other = (ComplexNumber) o;
			return other.getRe() == this.getRe() && other.getIm() == this.getIm();
		}

		return false;
	}

	public int hashCode() {
		return Double.hashCode(re) ^ Double.hashCode(im);
	}
}