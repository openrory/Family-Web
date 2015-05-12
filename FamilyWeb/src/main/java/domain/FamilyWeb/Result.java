/**
 * 
 */
package domain.FamilyWeb;

/**
 * @author Joery
 * @version 0.1
 * @since 2015-04-21
 */
public class Result {
	private Question theQuestion;
	private Answer myAnswer;
	
	/**
	 * Constructor with fields
	 * 
	 * @param theQuestion
	 * @param myAnswer
	 */
	public Result(Question theQuestion, Answer myAnswer) {
		super();
		this.theQuestion = theQuestion;
		this.myAnswer = myAnswer;
	}

	/**
	 * @return the theQuestion
	 */
	public Question getTheQuestion() {
		return theQuestion;
	}

	/**
	 * @param theQuestion the theQuestion to set
	 */
	public void setTheQuestion(Question theQuestion) {
		this.theQuestion = theQuestion;
	}

	/**
	 * @return the myAnswer
	 */
	public Answer getMyAnswer() {
		return myAnswer;
	}

	/**
	 * @param myAnswer the myAnswer to set
	 */
	public void setMyAnswer(Answer myAnswer) {
		this.myAnswer = myAnswer;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Result [TheQuestion = " + getTheQuestion()
				+ ", MyAnswer = " + getMyAnswer() + "]";
	}	
}
