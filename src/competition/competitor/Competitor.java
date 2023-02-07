package competition.competitor;

/** A class to represent the competitor */

public class Competitor {
	
	private String name;
	
	/** A competitor is defined by his name */
	public Competitor(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
	
	public String toString() {
		return this.name;
	}

	public boolean equals(Object o) {
		if ( !(o instanceof Competitor) ) {
			return false;
		}
		Competitor other = (Competitor) o;
		return this.name.equals(other.getName());
	}
	
	public int hashCode() {
		return ((this.name == null) ? 0 : this.name.hashCode());
	 }
	
}
