/**
 * 
 */
package domain.FamilyWeb;

/**
 * @author Joery
 * @version 0.1
 * @since 2015-04-21
 */
public class Category {
	private String name;

	/**
	 * Constructor with fields
	 * @param name
	 */
	public Category(String name) {
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Category [Name = " + getName() + "]";
	}
}
