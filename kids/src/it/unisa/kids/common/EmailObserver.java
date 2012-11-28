package it.unisa.kids.common;

import it.unisa.kids.common.Email;
import java.util.Observable;
import java.util.Observer;

public class EmailObserver implements Observer {

	public void update(Observable o, Object arg) {
		Email e = (Email) arg;
		
		//sendEmail(e.getEmail(), e.getBody());
	}
}
