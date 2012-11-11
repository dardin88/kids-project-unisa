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
	 * @param pDate
	 * @param pName
	 * @param pStart
	 * @param pEnd
	 * @param pEelegate
	 * @param pTrainee
	 */
	public TraineeActivity(GregorianCalendar pDate, String pName, Time pStart, Time pEnd, Account pDelegate,Trainee pTrainee) {
		super();
		this.date = pDate;
		this.name = pName;
		this.start = pStart;
		this.end = pEnd;
		this.delegate =pDelegate;
		this.trainee = pTrainee;
	}
	
	/**the method returns the date 
	 * @return Gregorian Calendar date
	 */
	public GregorianCalendar getDate() {
		return date;
	}
	
	
	/**the method sets the date
	 * @param Gregorian Calendar pDate
	 */
	public void setDate(GregorianCalendar pDate) {
		this.date = pDate;
	}
	
	
	/**the method returns the name of the activity
	 * @return String name
	 */
	public String getName() {
		return name;
	}
	
	
	/**the method sets the name of the activity
	 * @param String pName
	 */
	public void setName(String pName) {
		this.name = pName;
	}

	
	/**the method returns the description of the activity
	 * @return String description
	 */
	public String getDescription() {
		return description;
	}
	
	
	/**the method sets the description of the activity
	 * @param String pDescription
	 */
	public void setDescription(String pDescription) {
		this.description = pDescription;
	}

	
	/**the method return the start time of activity
	 * @return Time start
	 */
	public Time getStart() {
		return start;
	}
	
	
	/**the method sets the start time of activity
	 * @param Time pStart
	 */
	public void setStart(Time pStart) {
		this.start = pStart;
	}

	/**the method return the end time of activity
	 * @return Time end
	 */
	public Time getEnd() {
		return end;
	}

	/**the method sets the end time of activity
	 * @param Time pEnd
	 */
	public void setEnd(Time pEnd) {
		this.end = pEnd;
	}
	
	
	/**the method return the delegate that adds the activity
	 * @return Account delegate
	 */
	public Account getDelegate() {
		return delegate;
	}

	
	/**the method sets the delegate that adds the activity
	 * @param Account pDelegate
	 */
	public void setDelegate(Account pDelegate) {
		this.delegate = pDelegate;
	}

	
	/**the method returns the trainee that has performed the activity
	 * @return Trainee trainee
	 */
	public Trainee getTrainee() {
		return trainee;
	}

	
	/**the method sets the trainee that has performed the activity
	 * @param Trainee pTrainee
	 */
	public void setTrainee(Trainee pTrainee) {
		this.trainee = pTrainee;
	}
	
	
	
}
