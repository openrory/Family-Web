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
	private int survey_id;
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

	/**
	 * @return the survey_id
	 */
	public int getSurvey_id() {
		return survey_id;
	}

	/**
	 * @param survey_id the survey_id to set
	 */
	public void setSurvey_id(int survey_id) {
		this.survey_id = survey_id;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Survey [survey_id = "+this.getSurvey_id()+", Name = " + getName() + "]";
	}	
}
