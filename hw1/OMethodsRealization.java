
public class OMethodsRealization implements Cloneable{
	private String title;
	
	public OMethodsRealization(String title, int length) {
		super();
		this.title = title;
	}
	
	public OMethodsRealization clone() {
		OMethodsRealization omr = null;
		try {
			omr = (OMethodsRealization)super.clone();
		} catch (CloneNotSupportedException e) {
		}
		return omr;
	}
	
	public boolean equals(Object o) {
		if(o instanceof OMethodsRealization) {
			return ((OMethodsRealization)o).title == this.title;
		} else {
			return false;
		}
	}
	
	public String toString() {
		return this.title;
	}
	
	public int hashCode() {
		return this.title.hashCode();
	}
}
