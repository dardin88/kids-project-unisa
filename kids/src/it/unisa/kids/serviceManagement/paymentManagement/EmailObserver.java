package it.unisa.kids.serviceManagement.paymentManagement;

import java.util.Observable;
import java.util.Observer;

public class EmailObserver implements Observer {

	public void update(Observable o, Object arg) {
		Email e = (Email) arg;
		
		//sendEmail(e.getEmail(), e.getBody());
	}
}
