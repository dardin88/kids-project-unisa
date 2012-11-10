package it.unisa.kids.serviceManagement.trainingManagement;

import it.unisa.kids.accessManagement.Account;

import java.sql.Time;
import java.util.GregorianCalendar;

/**The class models trainee activities
 * @author Marco Moretti
 *
 */
public class TraineeActivity {
	private GregorianCalendar date;
	private String name;
	private String description;
	private Time start;
	private Time end;
	private Account delegate;
	private Trainee trainee;
	/**The constructor of the class
	 * @param date
	 * @param name
	 * @param description
	 * @param start
	 * @param end
	 * @param delegate
	 * @param trainee
	 */
	public TraineeActivity(GregorianCalendar date, String name,
			String description, Time start, Time end, Account delegate,
			Trainee trainee) {
		super();
		this.date = date;
		this.name = name;
		this.description = description;
		this.start = start;
		this.end = end;
		this.delegate = delegate;
		this.trainee = trainee;
	}
	
	/**the method returns the date 
	 * @return Gregorian Calendar date
	 */
	public GregorianCalendar getDate() {
		return date;
	}
	
	
	/**the method sets the date
	 * @param Gregorian Calendar date
	 */
	public void setDate(GregorianCalendar date) {
		this.date = date;
	}
	
	
	/**the method returns the name of the activity
	 * @return String name
	 */
	public String getName() {
		return name;
	}
	
	
	/**the method sets the name of the activity
	 * @param String name
	 */
	public void setName(String name) {
		this.name = name;
	}

	
	/**the method returns the description of the activity
	 * @return String description
	 */
	public String getDescription() {
		return description;
	}
	
	
	/**the method sets the description of the activity
	 * @param String description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	
	/**the method return the start time of activity
	 * @return Time start
	 */
	public Time getStart() {
		return start;
	}
	
	
	/**the method sets the start time of activity
	 * @param Time start
	 */
	public void setStart(Time start) {
		this.start = start;
	}

	/**the method return the end time of activity
	 * @return Time end
	 */
	public Time getEnd() {
		return end;
	}

	/**the method sets the end time of activity
	 * @param Time end
	 */
	public void setEnd(Time end) {
		this.end = end;
	}
	
	
	/**the method return the delegate that adds the activity
	 * @return Account delegate
	 */
	public Account getDelegate() {
		return delegate;
	}

	
	/**the method sets the delegate that adds the activity
	 * @param Account delegate
	 */
	public void setDelegate(Account delegate) {
		this.delegate = delegate;
	}

	
	/**the method returns the trainee that has performed the activity
	 * @return Trainee trainee
	 */
	public Trainee getTrainee() {
		return trainee;
	}

	
	/**the method sets the trainee that has performed the activity
	 * @param Trainee trainee
	 */
	public void setTrainee(Trainee trainee) {
		this.trainee = trainee;
	}
	
	
	
}
