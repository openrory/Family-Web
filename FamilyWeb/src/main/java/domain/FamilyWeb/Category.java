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
	private int group_id;

	/**
	 * Constructor with fields
	 * @param name
	 */
	public Category(String name, int id) {
		this.name = name;
		this.group_id = id;
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

	/**
	 * @return the group_id
	 */
	public int getGroup_id() {
		return group_id;
	}

	/**
	 * @param group_id the group_id to set
	 */
	public void setGroup_id(int group_id) {
		this.group_id = group_id;
	}
}
