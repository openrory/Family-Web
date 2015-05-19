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
public class Question {
	private int question_id;
	private String question;
	private ArrayList<Answer> theAnswers;
	
	/**
	 * clean constructor
	 */
	public Question() {
		this.theAnswers = new ArrayList<Answer>();
	}
	
	/**
	 * Constructor with fields
	 * 
	 * @param question
	 * @param theAnswers
	 */
	public Question(int id, String question, ArrayList<Answer> theAnswers) {
		this.setQuestion_id(id);
		this.question = question;
		this.theAnswers = theAnswers;
	}	

	/**
	 * @return the question_id
	 */
	public int getQuestion_id() {
		return question_id;
	}

	/**
	 * @param question_id the question_id to set
	 */
	public void setQuestion_id(int question_id) {
		this.question_id = question_id;
	}

	/**
	 * @return the question
	 */
	public String getQuestion() {
		return question;
	}

	/**
	 * @param question the question to set
	 */
	public void setQuestion(String question) {
		this.question = question;
	}

	/**
	 * @return the theAnswers
	 */
	public ArrayList<Answer> getTheAnswers() {
		return theAnswers;
	}

	/**
	 * @param theAnswers the theAnswers to set
	 */
	public void setTheAnswers(ArrayList<Answer> theAnswers) {
		this.theAnswers = theAnswers;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Question [Question_Id = " + getQuestion_id()+ ", Question = " + getQuestion() + "]";
	}
}
