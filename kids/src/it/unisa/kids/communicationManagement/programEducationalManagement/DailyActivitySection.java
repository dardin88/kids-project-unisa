package it.unisa.kids.communicationManagement.programEducationalManagement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import it.unisa.kids.communicationManagement.programEducationalManagement.IDailyActivitySection;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * this class extends ProjectAnnualSection model an activity daily for section
 *
 * @author Francesco Di Lorenzo, Giuseppe Alfieri
 *
 */
public class DailyActivitySection extends AnnualProjectSection 
{
              
            
        
    
    
	private int id;
	private int idActivity;
	private int idEducator;
	private GregorianCalendar data;
	private String notes;
        private int idSection;
        Map<String,Value> childrenList;
        private JDBCDailyActivity db;
        

        
	public DailyActivitySection(GregorianCalendar data, int idSection) throws SQLException{
           db=new JDBCDailyActivity();
           DailyActivitySection temp=db.showDailyActivitySection(data, idSection);
           if(temp!=null){
               this.id=temp.getId();
               this.idActivity=temp.getIdActivity();
               this.idEducator=temp.getIdEducator();
               this.notes=temp.getNotes();
           }
           this.idSection=idSection;
           this.data=data;
           
           
           
            
        }
        
        
         public int getIdSection() {
            return idSection;
        }
	/**
	 * this method return the idActivity of the activity section daily
	 * @return int idActivity
	 */
	public synchronized int getIdActivity() {
		return idActivity;
	}
	
	/**
	 * this method set the idActivity of the activity section daily
	 * @param idActivity
	 */
	public synchronized void setIdActivity(int idActivity) {
		this.idActivity = idActivity;
	}
        
        public synchronized void setChildrenList() throws SQLException{
            this.childrenList=db.showRegister(this.data, this.idSection);
        }
        public synchronized Map getChildrenList(){
            return this.childrenList;
        }
	

    /**
     * this method return the idEducatore of the activity section daily
     *
     * @return int idEducator
     */
    public synchronized int getIdEducator() {
        return idEducator;
    }

    /**
     * this method set the idEducator of the activity section daily
     *
     * @param idEducator
     */
    public synchronized void setIdEducator(int idEducator) {
        this.idEducator = idEducator;
    }

    /**
     * this method return the data of the activity section daily
     *
     * @return GregorianCalendar data
     */
    public synchronized GregorianCalendar getData() {
        return data;
    }

    /**
     * this method set the data of the activity section daily
     *
     * @param data
     */
    public synchronized void setData(GregorianCalendar data) {
        this.data = data;
    }

    /**
     * this method return the notes of the activity section daily
     *
     * @return String notes
     */
    public synchronized String getNotes() {
        return notes;
    }

    /**
     * this method set the notes of the activity section daily
     *
     * @param notes
     */
    public synchronized void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * this method return the id of the activity section daily
     *
     * @return int id
     */
    public synchronized int getId() {
        return id;
    }

    public void setId(int aInt) {
        this.id=aInt;
    }
    
    
    
        }
