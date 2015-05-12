/**
 * 
 */
package domain.FamilyWeb;

import java.util.ArrayList;

/**
 * @author Joery
 * @version 0.1
 * @since 2015-04-21
 */
public class Survey {
	private String name;
	private ArrayList<Question> questions;
	
	/**
	 * Constructor with fields
	 * 
	 * @param name
	 * @param questions
	 */
	public Survey(String name, ArrayList<Question> questions) {
		this.name = name;
		this.questions = questions;
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

	/**
	 * @return the questions
	 */
	public ArrayList<Question> getQuestions() {
		return questions;
	}

	/**
	 * @param questions the questions to set
	 */
	public void setQuestions(ArrayList<Question> questions) {
		this.questions = questions;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Survey [Name = " + getName() + "]";
	}	
}
