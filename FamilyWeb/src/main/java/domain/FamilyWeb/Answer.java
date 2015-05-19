/**
 * 
 */
package domain.FamilyWeb;

/**
 * @author Joery
 * @version 0.1
 * @since 2015-04-21
 */
public class Answer {
	private String answer;
	private int answer_id;
	
	/**
	 * Constructor with fields
	 * 
	 * @param answer
	 * @param answer_id
	 */
	public Answer(String answer, int answer_id) {
		super();
		this.answer = answer;
		this.answer_id = answer_id;
	}

	/**
	 * clean constructor
	 */
	public Answer() {
		super();
	}

	/**
	 * @return the answer
	 */
	public String getAnswer() {
		return answer;
	}

	/**
	 * @param answer the answer to set
	 */
	public void setAnswer(String answer) {
		this.answer = answer;
	}

	/**
	 * @return the answerWeight
	 */
	public int getAnswerID() {
		return answer_id;
	}

	/**
	 * @param answerWeight the answerWeight to set
	 */
	public void setAnswerID(int answerID) {
		this.answer_id = answerID;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Answer [Answer_id = "
				+ getAnswerID() + ", Answer = " + getAnswer() + "]";
	}
}
