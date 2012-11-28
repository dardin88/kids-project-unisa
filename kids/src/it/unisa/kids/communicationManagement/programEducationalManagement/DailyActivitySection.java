package it.unisa.kids.communicationManagement.programEducationalManagement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * this class extends ProjectAnnualSection
 * model an activity daily for section
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
        private ChildrenList lista;
        
        public class ChildrenList{
            public class ChildrenField<String, Boolean> implements Entry<String,Boolean>{
            String name;
            Boolean presence;
            
            public ChildrenField(){
                
            }
                
         /**
	 * this method return the Name of the baby
         * @return String Author
	 */
            public String getKey() {
                return this.name;
            }
         /**
	 * this method return true if the child was present false otherwise. 
         * @return Boolean present?
	 */
            public Boolean getValue() {
                return this.presence;
            }

	 /**
	 * this method set the value of presence
	 * @param present?
         * @return boolean new value
	 */
            public Boolean setValue(Boolean value) {
                this.presence=value;
                return this.presence;
            }
            
            }
            
            private ArrayList<ChildrenField> container;
            
            public ChildrenList(){
                container=new ArrayList<ChildrenField>();
                
            }
            
            public int size(){
                return container.size();
            }
            
            public boolean insertList(int sectionId){
                
                /* metodo che prende in input l'Id della sezione e inserisce tutti i bambini della sezione nel registro
                 * 
                 */
            }
            public void setPresence(String name){
                
            }
            
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
	

	/**
	 * this method return the idEducatore of the activity section daily
	 * @return int idEducator
	 */
	public synchronized int getIdEducator() {
		return idEducator;
	}
	/**
	 * this method set the idEducator of the activity section daily
	 * @param idEducator
	 */
	public synchronized void setIdEducator(int idEducator) {
		this.idEducator = idEducator;
	}
	

	/**
	 * this method return the data of the activity section daily
	 * @return GregorianCalendar data
	 */
	public synchronized GregorianCalendar getData() {
		return data;
	}
	/**
	 * this method set the data of the activity section daily
	 * @param data
	 */
	public synchronized void setData(GregorianCalendar data) {
		this.data = data;
	}
	

	/**
	 * this method return the notes of the activity section daily
	 * @return String notes
	 */
	public synchronized String getNotes() {
		return notes;
	}
	/**
	 * this method set the notes of the activity section daily
	 * @param notes
	 */
	public synchronized void setNotes(String notes) {
		this.notes = notes;
	}
	

	/**
	 * this method return the id of the activity section daily
	 * @return int id
	 */
	public synchronized int getId() {
		return id;
	}

}
