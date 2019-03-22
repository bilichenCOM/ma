import java.awt.Color;
import java.util.*;

public class FiveComparatorsExample {
	
	public static <T extends Pentagram> TreeSet<T> sortByName(Collection<T> pentagrams) {
		TreeSet<T> set = new TreeSet<T>(new NameComparator());
		set.addAll(pentagrams);
		return set;
	}
	
	public <T extends Pentagram> TreeSet<T> sortByRadius(Collection<T> pentagrams) {
		TreeSet<T> set = new TreeSet<T>(new RadiusComparator());
		set.addAll(pentagrams);
		return set;
	}
	
	public <T extends Pentagram> TreeSet<T> sortByColor(Collection<T> pentagrams) {
		TreeSet<T> set = new TreeSet<T>(new ColorComparator());
		set.addAll(pentagrams);
		return set;
	}
	
	public <T extends Pentagram> TreeSet<T> sortByTrueSize(Collection<T> pentagrams) {
		TreeSet<T> set = new TreeSet<T>(new TrueSizeComparator());
		set.addAll(pentagrams);
		return set;
	}
	
	public <T extends Pentagram> TreeSet<T> sortBySymbol(Collection<T> pentagrams) {
		TreeSet<T> set = new TreeSet<T>(new SymbolComparator());
		set.addAll(pentagrams);
		return set;
	}
	
	static class NameComparator implements Comparator<Pentagram>{
		public int compare(Pentagram p1,Pentagram p2) {
			return p1.getName().compareTo(p2.getName());
		}
	}
	
	static class RadiusComparator implements Comparator<Pentagram> {
		public int compare(Pentagram p1, Pentagram p2) {
			return p1.getRadius()-p2.getRadius();
		}
	}
	
	static class ColorComparator implements Comparator<Pentagram> {
		public int compare(Pentagram p1, Pentagram p2) {
			return p1.getColor().getRGB()-p2.getColor().getRGB();
		}
	}
	
	static class TrueSizeComparator implements Comparator<Pentagram> {
		public int compare(Pentagram p1, Pentagram p2) {
			return (p1.isTrueSize()&&!p2.isTrueSize())?1:
				(p1.isTrueSize()&&p2.isTrueSize())?0:
					-1;
		}
	}
	
	static class SymbolComparator implements Comparator<Pentagram> {
		public int compare(Pentagram p1, Pentagram p2) {
			return p1.getSymbol()-p2.getSymbol();
		}
	}
	
}

class Pentagram {
	private String name;
	private int radius;
	private Color color;
	private boolean trueSize;
	private char symbol;
	
	public Pentagram(String name, int radius, Color color, boolean trueSize, char symbol) {
		this.name = name;
		this.radius = radius;
		this.color = color;
		this.trueSize = trueSize;
		this.symbol = symbol;
	}

	public String getName() {
		return name;
	}

	public int getRadius() {
		return radius;
	}

	public Color getColor() {
		return color;
	}

	public boolean isTrueSize() {
		return trueSize;
	}

	public char getSymbol() {
		return symbol;
	}
	
}
