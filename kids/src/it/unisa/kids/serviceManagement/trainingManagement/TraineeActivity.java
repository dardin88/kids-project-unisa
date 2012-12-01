package it.unisa.kids.serviceManagement.trainingManagement;


import java.sql.Time;
import java.util.GregorianCalendar;

/**The class models trainee activities
 * @author Marco Moretti
 *
 */
public class TraineeActivity {
        private int id;
	private GregorianCalendar date;
	private String name;
	private String description;
	private Time start;
	private Time end;
	private int delegate;
	private int trainee;
        private String opinion;
	/**Empty constructor of the class
	 *
	 */
	public TraineeActivity() {
		
	}
	public String getOpinion(){
            return opinion;
        }
        
        public void setOpinion(String pOpinion){
            this.opinion=pOpinion;
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
	public int getDelegate() {
		return delegate;
	}

	
	/**the method sets the delegate that adds the activity
	 * @param Account pDelegate
	 */
	public void setDelegate(int pDelegate) {
		this.delegate = pDelegate;
	}

	
	/**the method returns the trainee that has performed the activity
	 * @return Trainee trainee
	 */
	public int getTrainee() {
		return trainee;
	}

	
	/**the method sets the trainee that has performed the activity
	 * @param Trainee pTrainee
	 */
	public void setTrainee(int pTrainee) {
		this.trainee = pTrainee;
	}
	
        public void setId(int pId){
            this.id=pId;
        }
        
        public int getId(){
            return id;
        }
	
	
}
