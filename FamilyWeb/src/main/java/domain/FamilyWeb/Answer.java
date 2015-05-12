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
	private int answerWeight;
	
	/**
	 * Constructor with fields
	 * 
	 * @param answer
	 * @param answerWeight
	 */
	public Answer(String answer, int answerWeight) {
		super();
		this.answer = answer;
		this.answerWeight = answerWeight;
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
	public int getAnswerWeight() {
		return answerWeight;
	}

	/**
	 * @param answerWeight the answerWeight to set
	 */
	public void setAnswerWeight(int answerWeight) {
		this.answerWeight = answerWeight;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Answer [Answer = " + getAnswer() + ", AnswerWeight = "
				+ getAnswerWeight() + "]";
	}
}
