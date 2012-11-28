package it.unisa.kids.communicationManagement.programEducationalManagement;

import java.util.ArrayList;
import java.util.Map.Entry;

/**
 * The class model the new entity ProjectAnnual
 * @author Francesco Di Lorenzo
 *
 */
public class AnnualProject 
{
	private String name;
	private String topic;
	private String description;
	private String applicationYear;
        private ArrayList<Entry> commenti;
	private int id;
        
        
        public class Comment<K,V> implements Entry<String,String>{
            private String author;
            private String comment;
            
            public Comment(String author, String comment){
                this.author=author;
                this.comment=comment;
                
            }
        /**
	 * this method return the Author of the comment about the project annual
	 * @return String Author
	 */
        public String getKey() {
            return this.author;
        }
        /**
	 * this method return the text of the Comment 
	 * @return String Comment
	 */
        public String getValue() {
            return this.comment;
        }

	/**
	 * this method set the text of the comment
	 * @param comment
         * @return String author
	 */

        public String setValue(String comment) {
            this.comment=comment;
            return this.author;
        }
        
        public String toString(){
            return this.author +" "+ this.comment;
        }
            
        }
	
	/**
	 * the constructor inizializes the container of the comments
	 */
	public AnnualProject()
	{	
            commenti=new ArrayList();
	}

	/**
	 * this method return the name of the project
	 * @return String name
	 */
	public synchronized String getName() {
		return name;
	}

	/**
	 * this method set the name of the project
	 * @param name
	 */
	public synchronized void setName(String name) {
		this.name = name;
	}

	/**
	 * this method return the tema of the project
	 * @return String tema
	 */
	public synchronized String getTopic() {
		return topic;
	}

	/**
	 * this method set the topic of the project
	 * @param topic
	 */
	public synchronized void setTopic(String topic) {
		this.topic = topic;
	}
	
	/**
	 * this method return the description of the project
	 * @return String description
	 */
	public synchronized String getDescription() {
		return description;
	}

	/**
	 * this method set the description of the project
	 * @param description
	 */
	public synchronized void setDescription(String description) {
		this.description = description;
	}

	/**
	 * this method return the applicationYear of the project
	 * @return String applicationYear
	 */
	public synchronized String getApplicationYear() {
		return applicationYear;
	}
	/**
	 * this method set the applicationYear of the project
	 * @param applicationYear
	 */
	public synchronized void setApplicationYear(String applicationYear) {
		this.applicationYear = applicationYear;
	}
	/**
	 * this method return the id of the project annual
	 * @return int id
	 */
	public int getId(){
		return id;
	}
        /**
	 * this method insert the text of the comment in the ArrayList
	 * @param comment
	 */
        public synchronized void addComment(String author, String comment){
            this.commenti.add(new Comment(author, comment));
            }
	/**
	 * this method return the text of the comment in the ArrayList at index index
         * @param index
	 * @return String text of comment
	 */
        public synchronized String getComment(int index){
            return commenti.get(index).getValue().toString();
            
        }
	/**
	 * this method return the author of the comment in the ArrayList at index index
         * @param index
	 * @return String author
	 */
        public synchronized String getAuthor(int index){
            return commenti.get(index).getKey().toString();
        }
        /**
	 * this method return the number of the comments in the ArrayList
         *
	 * @return int size
	 */
        public synchronized int getCommentNum(){
            return commenti.size();
        }

}
