package it.unisa.kids.common;

public final class DBNames {
	
	private DBNames() {
		
	}

	public static final String TABLE_ACCOUNT = "Account";
	public static final String TABLE_RECOURSE = "Ricorso";
	public static final String TABLE_RINUNCIA = "";		// da completare/definire
	public static final String TABLE_CLASSIFICATION = "Graduatoria";
	public static final String TABLE_CLASS = "class";
	public static final String TABLE_CHILD = "child_inscription";
	public static final String TABLE_RESULT = "Esito";		// da completare/definire
	public static final String TABLE_ASSIGNATION = "assignation";
	public static final String TABLE_REGISTRATION = "IscrizioneBambino";
	
	public static final String TABLE_MEAL_REQUEST = "meal_request";
	public static final String TABLE_TIMESERV_REQUEST = "timeserv_request";
	public static final String TABLE_DIFFMENU = "menu_differenziato";
	public static final String TABLE_REFUND = "refund";
	public static final String TABLE_PAYMENT = "payment";
	public static final String TABLE_TRAINEE = "trainee";
	public static final String TABLE_TRAINEE_ACT = "trainee_activity";
	
	public static final String TABLE_PARTICIPATION = "participation";
	public static final String TABLE_COMUNICATION = "comunication";
	public static final String TABLE_REUNION = "reunion";
	public static final String TABLE_NEWS = "news";
	public static final String TABLE_ANNUAL_PROJ = "annual_proj";
	public static final String TABLE_SECTION_PROJ = "section_proj";
	public static final String TABLE_COMMENT = "comment";
	public static final String TABLE_ACT = "activity";
	public static final String TABLE_DAILY_SECTION_ACT = "daily_section_act";
	public static final String TABLE_CHILD_PARTICIPATION = "child_participation";

	//ClassTable's attributes
	public static final String ATT_CLASS_ID = "Id";
	public static final String ATT_CLASS_NAME = "Nome";
	
	//RinunciaTable's attributes
	public static final String ATT_RINUNCIA_ID = "Id";
	public static final String ATT_RINUNCIA_DATE = "Data";
	public static final String ATT_RINUNCIA_CONFIRM = "Conferma";

	//ChildTable's attributes
	public static final String ATT_CHILD_ID = "Id";
	public static final String ATT_CHILD_SURNAME = "Cognome";
	public static final String ATT_CHILD_NAME = "Nome";
	public static final String ATT_CHILD_BORNDATE = "DataNascita";
	public static final String ATT_CHILD_COMMONBORN = "ComuneNascita";
	public static final String ATT_CHILD_FISCALCODE = "CodiceFiscale";
	public static final String ATT_CHILD_CITIZENSHIP = "Cittadinanza";
	public static final String ATT_CHILD_USERSECTION = "FasciaUtenza";
	public static final String ATT_CHILD_REGISTRATIONDATE = "DataIscrizione";
	public static final String ATT_CHILD_SICK = "Malattia";
	public static final String ATT_CHILD_REGISTRATIONPHASE = "FaseDellIscrizione";
	
	//RequestTable's attributes
	public static final String ATT_REQUEST_ID= "id";
	public static final String ATT_REQUEST_DATE="data";
	public static final String ATT_REQUEST_DESCRIPTION ="descrizione";
	public static final String ATT_REQUEST_TIME= "time";
	public static final String ATT_REQUEST_ACCOUNT_PARENT ="accountGenitore";
	
	//RegistrationTable 's attributes
	public static final String ATT_REGISTRATION_ID= "Id";
	public static final String ATT_REGISTRATION_SURNAME="Cognome";
	public static final String ATT_REGISTRATION_NAME = "Nome";
	public static final String ATT_REGISTRATION_BORN ="DataNascita";
	public static final String ATT_REGISTRATION_CITY_BIRTH = "ComuneNascita";
	public static final String ATT_REGISTRATION_TAX_CODE= "CodiceFiscale";
	public static final String ATT_REGISTRATION_NATIONALITY ="Cittadinanza";
	public static final String ATT_REGISTRATION_END_USER = "FasciaUtenza";
	public static final String ATT_REGISTRATION_SIGNUP_DATE ="DataIscrizione";
	public static final String ATT_REGISTRATION_DIDEASE= "Malattia";
	public static final String ATT_REGISTRATION_ENROLLEMENT_PHASE= "FaseDellIscrizione";
	public static final String ATT_REGISTRATION_ACCOUNT_PARENT ="AccountGenitore";
	public static final String ATT_REGISTRATION_CLASS= "Classe";
	
	//MealRequestTable's attributes
	public static final String ATT_MEALREQ_ID = "Id";
	public static final String ATT_MEALREQ_MENUTYPE = "type";
	public static final String ATT_MEALREQ_DATE = "date";
	public static final String ATT_MEALREQ_CHILDINSCID = "Iscrizione";
	
	//TimeServiceRequestTable's attributes
	public static final String ATT_TIMESERVREQ_ID = "Id";
	public static final String ATT_TIMESERVREQ_DAYREQ = "day_requested";
	public static final String ATT_TIMESERVREQ_SERVTYPE = "service_type";
	public static final String ATT_TIMESERVREQ_DATE = "date";
	public static final String ATT_TIMESERVREQ_PARENTID = "parent_id";
	
	//DifferentiatedMenuTable's attributes
	public static final String ATT_DIFFMENU_ID = "Id";
	public static final String ATT_DIFFMENU_MOTIVATION = "Motivazione";
	public static final String ATT_DIFFMENU_FIRST = "Primo";
	public static final String ATT_DIFFMENU_SECOND = "Secondo";
	public static final String ATT_DIFFMENU_SIDEDISH = "Contorno";
	public static final String ATT_DIFFMENU_FRUIT = "Frutta";
	public static final String ATT_DIFFMENU_CHILDINSCID = "Iscrizione";
	
	//PaymentTable's attributes
	public static final String ATT_PAYMENT_ID = "id";
	public static final String ATT_PAYMENT_EXPDATE = "exp_date";
	public static final String ATT_PAYMENT_AMOUNT = "amount";
	public static final String ATT_PAYMENT_AMOUNTDUE = "amount_due";
	public static final String ATT_PAYMENT_PAID = "paid";
	public static final String ATT_PAYMENT_ORIGINACCOUNT = "origin_account";
	public static final String ATT_PAYMENT_PAYEE = "payee";
	public static final String ATT_PAYMENT_PARENTID = "parent_id";
	public static final String ATT_PAYMENT_CHARGE = "charge";
	public static final String ATT_PAYMENT_DESCRIPTION = "description";
	public static final String ATT_PAYMENT_DISCOUNT = "discount";
	public static final String ATT_PAYMENT_DISCDESCRIPTION = "discount_description";
	
	//RefundTable's attributes
	public static final String ATT_REFUND_ID = "Id";
	public static final String ATT_REFUND_DESCRIPTION = "Descrizione";
	public static final String ATT_REFUND_AMOUNT = "Importo";
	public static final String ATT_REFUND_PARENTID = "Genitore";
	
	//Trainee' attributes
	public static final String ATT_TRAINEE_REGISTER="Matricola";
	public static final String ATT_TRAINEE_NAME="Nome";
	public static final String ATT_TRAINEE_SURNAME="Cognome";
	public static final String ATT_TRAINEE_EMAIL="Email";
	public static final String ATT_TRAINEE_BIRTHDATE="DataNascita";
	public static final String ATT_TRAINEE_BIRTHCITY="CittaNascita";
	public static final String ATT_TRAINEE_CITYOFRESIDENCE="CittaResidenza";
	public static final String ATT_TRAINEE_ADDRESS="Indirizzo";
	public static final String ATT_TRAINEE_CAP="CAP";
	public static final String ATT_TRAINEE_TELEPHONENUMBER="NumeroTelefonico";
	public static final String ATT_TRAINEE_DELEGATEACCOUNT="AccountDelegato";
	
	//Account's attributes
	public static final String ATT_ACCOUNT_CAPDOMICILIE="DomicilioCap";
	public static final String ATT_ACCOUNT_CAPRESIDENCE="ResidenzaCap";
	public static final String ATT_ACCOUNT_CELLULARNUMBER="Cellulare";
	public static final String ATT_ACCOUNT_CITIZENSHIP="Cittadinaza";
	public static final String ATT_ACCOUNT_CONTRACTEXPIRATIONDATE="DataScadenzaContratto";
	public static final String ATT_ACCOUNT_DATEOFBIRTH="DataNascita";
	public static final String ATT_ACCOUNT_EMAIL="Email";

	// Si devono inserire tra "" i nomi come stanno nel DB
	public static final String ATT_ACCOUNT_FACULTY="faculty";
	
	public static final String ATT_ACCOUNT_FAMILYSITUATION="SituazioneFamiliare";
	public static final String ATT_ACCOUNT_FAX="Dax";
	public static final String ATT_ACCOUNT_ID="Id";
	public static final String ATT_ACCOUNT_INCOME="Reddito";
	public static final String ATT_ACCOUNT_MUNICIPALITYDOMICILIE="DomicilioComune";
	public static final String ATT_ACCOUNT_MUNICIPALITYRESIDENCE="ResidenzaComune";
	public static final String ATT_ACCOUNT_NAME="Nome";
	public static final String ATT_ACCOUNT_NICKNAME="Nickname";
	public static final String ATT_ACCOUNT_PASSWORD="Password";
	public static final String ATT_ACCOUNT_PLACEOFBIRTH="ComuneNascita";
	public static final String ATT_ACCOUNT_PROVINCERESIDENCE="ResidenzaProvincia";
	public static final String ATT_ACCOUNT_PROVINCEDOMICILIE="DomicilioProvincia";
	public static final String ATT_ACCOUNT_QUALIFICATION="TitoloStudio";

	//Si devono inserire tra "" i nomi come stanno nel DB
	public static final String ATT_ACCOUNT_REGISTRATIONDATE="registration_date";
	public static final String ATT_ACCOUNT_STREETNUMBERDOMICILIE="street_number_domicilie";
	public static final String ATT_ACCOUNT_STREETNUMBERRESIDENCE="street_number_residence";
	
	public static final String ATT_ACCOUNT_SURNAMEUSER="Cognome";
	public static final String ATT_ACCOUNT_TAXCODE="CodiceFiscale";
	public static final String ATT_ACCOUNT_TELEPHONENUMBER="Telefono";
	public static final String ATT_ACCOUNT_TYPEACCOUNT="TipologiaAccount";
	public static final String ATT_ACCOUNT_TYPEPARENT="TipologiaGenitore";
	public static final String ATT_ACCOUNT_VIADOMICILE="DomicilioIndirizzo";
	public static final String ATT_ACCOUNT_VIARESIDENCE="ResidenzaIndirizzo";
	
	//Trainee activity's attributes
	public static final String ATT_TRAINEEACTIVITY_DATE="Data";
	public static final String ATT_TRAINEEACTIVITY_NAME="Nome";
	public static final String ATT_TRAINEEACTIVITY_DESCRIPTION="Descrizione";
	public static final String ATT_TRAINEEACTIVITY_STARTTIME="OraInizio";
	public static final String ATT_TRAINEEACTIVITY_ENDTIME="OraFine";
	public static final String ATT_TRAINEEACTIVITY_DELEGATEACCOUNT="Delegato";
        public static final String ATT_TRAINEEACTIVITY_TRAINEE="Tirocinante";
	
	//Classification attributes
	public static final String ATT_CLASSIFICATION_ID="Id";
	public static final String ATT_CLASSIFICATION_DATA="data";
	public static final String ATT_CLASSIFICATION_DATA_TERM= "DataScadenza";

	//Result's attributes
	public static final String ATT_RESULT_REGISTRATION ="Iscrizione";
	public static final String ATT_RESULT_CLASSIFICATION ="Graduatoria";
	public static final String ATT_RESULT_RESULT ="Esito";
	
	// Recourses's attributes
	public static final String ATT_RECOURSE_ID="Id";
	public static final String ATT_RECOURSE_DATA="Data";
	public static final String ATT_RECOURSE_REASON="Motivo";
	public static final String ATT_RECOURSE_VALUTATION="Valutazione";
	public static final String ATT_RECOURSE_IDREGISTRATION="Iscrizione";

	
	// Reunion's attributes
	public static final String ATT_REUNION_ID="id";
	public static final String ATT_REUNION_TITLE="title";
	public static final String ATT_REUNION_DESCRIPTION="description";
	public static final String ATT_REUNION_DATA="date";
	public static final String ATT_REUNION_TIME="time";
	public static final String ATT_REUNION_TYPE="type";
	
	
	//News attributes
	public static final String ATT_NEWS_ID="Id";
	public static final String ATT_NEWS_TITLE="Titolo";
	public static final String ATT_NEWS_DESCRIPTION="Contenuto";
	public static final String ATT_NEWS_TYPE="Tipo";
	public static final String ATT_NEWS_DATE="Data";
	public static final String ATT_NEWS_TIME="Ora";
	public static final String ATT_NEWS_ATTACHED="Allegato";
	public static final String ATT_NEWS_DELEGATEACCOUNT="Delegato";

	//Communication's attributes
	public static final String ATT_COMMUNICATION_ID="id";
	public static final String ATT_COMMUNICATION_TYPE="tipo";
	public static final String ATT_COMMUNICATION_IDEDUCATOR="idEducatore";
	public static final String ATT_COMMUNICATION_IDCHILD="idBambino";
	public static final String ATT_COMMUNICATION_DESCRIPTION="descrizione";
	public static final String ATT_COMMUNICATION_DATE="data";
	public static final String ATT_COMMUNICATION_SOLVED="risolto";
	
	//Project Annual attributes
	public static final String ATT_PROJECTANNUAL_ID="Id";
	public static final String ATT_PROJECTANNUAL_NAME="Nome";
	public static final String ATT_PROJECTANNUAL_TOPIC="Tema";
	public static final String ATT_PROJECTANNUAL_DESCRIPTION="Contenuto";
	public static final String ATT_PROJECTANNUAL_APPLICATIONYEAR="Anno applicazione";
	
	//ProjectAnnualSection attributes
	public static final String ATT_PROJECTANNUALSECTION_ID="Id";
	public static final String ATT_PROJECTANNUALSECTION_NAME="Nome";
	public static final String ATT_PROJECTANNUALSECTION_SECTION="Sezione";
	public static final String ATT_PROJECTANNUALSECTION_DESCRIPTION="COntenuto";
	public static final String ATT_PROJECTANNUALSECTION_IDYEAR="IdAnnuale";

	//Comment attributes
	public static final String ATT_COMMENT_ID="Id";
	public static final String ATT_COMMENT_DATE="Data";
	public static final String ATT_COMMENT_TYPEMODIFY="TipoModifica";
	public static final String ATT_COMMENT_DESCRIPTION="Contenuto";
	public static final String ATT_COMMENT_IDYEAR="IdAnnuale";
	public static final String ATT_COMMENT_IDSECTION="IdSezione";
	public static final String ATT_COMMENT_IDAUTHOR="IdAutore";
	
	//Activity attributes
	public static final String ATT_ACTIVITY_ID="Id";
	public static final String ATT_ACTIVITY_PERIOD="Periodo";
	public static final String ATT_ACTIVITY_NAME="Nome";
	public static final String ATT_ACTIVITY_DESCRIPTION="Contenuto";
	
	//Activity Section Daily
	public static final String ATT_ACTIVITYSECTTIONDAILY_ID="Id";
	public static final String ATT_ACTIVITYSECTTIONDAILY_IDACTITIVTY="IdAttivit�";
	public static final String ATT_ACTIVITYSECTTIONDAILY_IDEDUCATORE="IdEducatore";
	public static final String ATT_ACTIVITYSECTTIONDAILY_DATE="Data";
	public static final String ATT_ACTIVITYSECTTIONDAILY_NOTES="Note";
	
	//Child Partecipant attributes
	public static final String ATT_CHILDPARTECIPANT_IDCHILD="IdBambino";
	public static final String ATT_CHILDPARTECIPANT_IDACTIVITYDAILY="IdAttivit�Giornaliera"; 
	public static final String ATT_CHILDPARTECIPANT_STATE="Stato";

	
	


}
