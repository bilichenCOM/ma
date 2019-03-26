package hw1;

public class ChildOfFather implements Cloneable {
	private String title;

	public ChildOfFather(String title) {
		this.title = title;
	}

	public ChildOfFather clone() {
		ChildOfFather omr = null;
		try {
			omr = (ChildOfFather) super.clone();
			omr.setTitle(this.getTitle());
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
		return omr;
	}

	public String toString() {
		return this.title;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null) {
			return false;
		}

		if (getClass() != obj.getClass()) {
			return false;
		}

		ChildOfFather other = (ChildOfFather) obj;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
