package it.unisa.kids.accessManagement;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ManagerAccount {

	private static ManagerAccount manager;
	
	private ManagerAccount(){
	}
	
	public ManagerAccount getInstance(){
		if (manager!=null)
			return manager;
		else
			return (manager=new ManagerAccount());
	}
	
	public Account Crea(Account account){
		String nomeAccount=null;
		String query=null;
		int i=2;
		/*generazione nome account */
		while(true){
			nomeAccount= account.getNomeUtente()+account.getCognome();
			query="Select nomeAccount From name_table Where nomeAccount='"+nomeAccount+"'";
			if(query!=null)
				nomeAccount=nomeAccount+i;
			else 
				break;
			i++;
		}
		/*generazione password*/
		String password=getPWD();
		
		 query="Insert into 'table_name'(annoIscrizione,capDomicilio,capResidenza,cellulare,cittadinanza,codiceFiscale,cognome,comuneDomicilio,dataDiNascita,dataScadenzaContratto,email(),facoltà,fax,luogoDiNascita,nomeAccount,nomeUtente,numCivicoDomicilio,numCivicoResidenza,password,provincia,provinciaResidenza,reddito,residenza,situazioneFamiliare,telefono,tipologiaAccount,tipologiaGenitore,titoloDiStudio,viaDomicilio,viaResidenza) values("+account.getAnnoIscrizione()+","+account.getCapDomicilio()+","+account.getCapResidenza()+","+account.getCellulare()+","+account.getCittadinanza()+","+account.getCodiceFiscale()+","+account.getCognome()+","+account.getComuneDomicilio()+","+account.getDataDiNascita()+","+account.getDataScadenzaContratto()+","+account.getEmail()+","+account.getFacoltà()+","+account.getFax()+","+account.getLuogoDiNascita()+","+nomeAccount+","+account.getNomeUtente()+","+account.getNumCivicoDomicilio()+","+account.getNumCivicoResidenza()+","+password+","+account.getProvinciaDomicilio()+","+account.getProvinciaResidenza()+","+account.getReddito()+","+account.getResidenza()+","+account.getSituazioneFamiliare()+","+account.getTelefono()+","+account.getTipologiaAccount()+","+account.getTipologiaGenitore()+","+account.getTitoloDiStudio()+","+account.getViaDomicilio()+","+account.getViaResidenza()+")";
		
		 //creazione connessione DB
		//esecuzione della query
		
		return account;
	}
	
	public Account Modifica(Account accountModificato){
		
		String query="Update table_name " +
				"SET annoIscrizione="+accountModificato.getAnnoIscrizione()+",capDomicilio="+accountModificato.getCapDomicilio()+",capResidenza="+accountModificato.getCapResidenza()+",cellulare="+accountModificato.getCellulare()+",cittadinanza="+accountModificato.getCittadinanza()+",codiceFiscale="+accountModificato.getCodiceFiscale()+",cognome="+accountModificato.getCognome()+",comuneDomicilio="+accountModificato.getComuneDomicilio()+",dataDiNascita="+accountModificato.getDataDiNascita()+",dataScadenzaContratto="+accountModificato.getDataScadenzaContratto()+",email="+accountModificato.getEmail()+",facoltà="+accountModificato.getFacoltà()+",fax="+accountModificato.getFax()+"luogoDiNascita="+accountModificato.getLuogoDiNascita()+",nomeAccount="+accountModificato.getNomeAccount()+",nomeUtente="+accountModificato.getNomeUtente()+",numCivicoDomicilio="+accountModificato.getNumCivicoDomicilio()+",numCivicoResidenza="+accountModificato.getNumCivicoResidenza()+",password="+accountModificato.getPassword()+",provincia="+accountModificato.getProvinciaDomicilio()+",provinciaResidenza="+accountModificato.getProvinciaResidenza()+",reddito="+accountModificato.getReddito()+",residenza="+accountModificato.getResidenza()+",situazioneFamiliare="+accountModificato.getSituazioneFamiliare()+",telefono="+accountModificato.getTelefono()+",tipologiaAccount="+accountModificato.getTipologiaAccount()+",tipologiaGenitore="+accountModificato.getTipologiaGenitore()+",titoloDiStudio="+accountModificato.getTitoloDiStudio()+",viaDomicilio="+accountModificato.getViaDomicilio()+",viaResidenza="+accountModificato.getViaResidenza()+
				"WHERE codiceFiscale="+accountModificato.getCodiceFiscale(); //dubbi sulla query
		
		//creazione connessione DB
		//esecuzione della query 
		
		return accountModificato;
	}
	
	public Account Elimina(Account accountEliminato){
		//dubbi sulla query
		String query="Select Id from name_table Where codiceFiscale='"+accountEliminato.getCodiceFiscale()+"'";
		String query2="Delete From name_table Where id='"+query+"'";
		return accountEliminato;
	}
	
	public ArrayList<Account> ricerca(Account account){
		ArrayList<Account> elencoAccount=new ArrayList<Account>();		//deve essere riempito con il risultato della query
		  String query="SELECT * FROM name_table WHERE ";				//cambiare i nome del db
		  
		  if (account.getNomeAccount()!=null)
			  query=query+"nomeAccount='"+account.getNomeAccount()+"'";
		  if (account.getNomeUtente()!=null)
			  query=query+"nomeUtente='"+account.getNomeUtente()+"'";
		  if (account.getCognome()!=null)
			  query=query+"cognome='"+account.getCognome()+"'";
		  if (account.getTipologiaAccount()!=null)
			  query=query+"tipologiaAccount='"+account.getTipologiaAccount()+"'";
		  
		  //connessione al db
		  
		  //esecuzione della query		
		  
		  //elaborazione del risultato
		  
		  return elencoAccount;
		
	}
	
	public String getPWD() {
        
		Random rnd = new Random();
        //decido dimensione password
        int dim = 8;
        //decido la quantità di cifre numeriche
        int num = rnd.nextInt(dim-4)+1;
        //decido la quantità di cifre alfa
        int alfa = dim-num;
        int appoggio=0;
        String pass="";
        int alterna=0;
        int contnum=1;
        int contalfa=1;
        for (int i=1;i<=dim;i++) {
            alterna = rnd.nextInt(2);
            if (contalfa>alfa){alterna=0;}
            else{if (contnum>num) {alterna=1;}
            }
            if (alterna==1) {contalfa++;
            appoggio = rnd.nextInt(25);
            switch (appoggio) {
                case 0 : pass = pass+"A";break;  //potevo farlo in altra maniera
                case 1 : pass = pass+"B";break;
                case 2 : pass = pass+"C";break;
                case 3 : pass = pass+"D";break;
                case 4 : pass = pass+"E";break;
                case 5 : pass = pass+"F";break;
                case 6 : pass = pass+"G";break;
                case 7 : pass = pass+"H";break;
                case 8 : pass = pass+"I";break;
                case 9 : pass = pass+"J";break;
                case 10 : pass = pass+"K";break;
                case 11 : pass = pass+"L";break;
                case 12 : pass = pass+"M";break;
                case 13 : pass = pass+"N";break;
                case 14 : pass = pass+"O";break;
                case 15 : pass = pass+"P";break;
                case 16 : pass = pass+"Q";break;
                case 17 : pass = pass+"R";break;
                case 18 : pass = pass+"S";break;
                case 19 : pass = pass+"T";break;
                case 20 : pass = pass+"U";break;
                case 21 : pass = pass+"W";break;
                case 22 : pass = pass+"X";break;
                case 23 : pass = pass+"Y";break;
                case 24 : pass = pass+"Z";break;
            }
            }
            if (alterna==0) {contnum++;
            appoggio = rnd.nextInt(10);
            switch (appoggio) {
                case 0 : pass = pass+"0";break;
                case 1 : pass = pass+"1";break;
                case 2 : pass = pass+"2";break;
                case 3 : pass = pass+"3";break;
                case 4 : pass = pass+"4";break;
                case 5 : pass = pass+"5";break;
                case 6 : pass = pass+"6";break;
                case 7 : pass = pass+"7";break;
                case 8 : pass = pass+"8";break;
                case 9 : pass = pass+"9";break;
            }
            }
        }
        
        return pass;
    }
	
	
}
	

