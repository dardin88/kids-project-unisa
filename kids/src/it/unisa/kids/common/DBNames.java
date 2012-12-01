package it.unisa.kids.common;

public final class DBNames {

    private DBNames() {
    }
    public static final String TABLE_ACCOUNT = "account";
    public static final String TABLE_REGISTRATIONCHILD = "iscrizionebambino";
    public static final String TABLE_CLASSIFICATION = "graduatoria";
    public static final String TABLE_RESULT = "esito";  // associazione tra graduatoria ed iscrizionebambino
    public static final String TABLE_RECOURSE = "ricorso";
    public static final String TABLE_RINUNCIA = "rinunce";
    public static final String TABLE_CLASS = "classe";
    public static final String TABLE_ASSIGNATION = "assignazione";  // associazione tra classe e tirocinante
    public static final String TABLE_MEAL_REQUEST = "meal_request";
    public static final String TABLE_TIMESERV_REQUEST = "timeserv_request";
    public static final String TABLE_DIFFMENU = "menu_differenziato";
    public static final String TABLE_REFUND = "refund";
    public static final String TABLE_PAYMENT = "payment";
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
    public static final String TABLE_TRAINEE = "tirocinante";
    public static final String TABLE_TRAINEECONVOCATION = "convocazione";
    public static final String TABLE_TRAINEEREQUEST = "richiesta_tirocinanti";
    public static final String TABLE_TRAINEEACTIVITY = "attivita_tirocinante";
    
    //Account's attributes
    public static final String ATT_ACCOUNT_REGISTER = "Matricola";
    public static final String ATT_ACCOUNT_CAPDOMICILIE = "DomicilioCap";
    public static final String ATT_ACCOUNT_CAPRESIDENCE = "ResidenzaCap";
    public static final String ATT_ACCOUNT_CELLULARNUMBER = "Cellulare";
    public static final String ATT_ACCOUNT_CITIZENSHIP = "Cittadinanza";
    public static final String ATT_ACCOUNT_CONTRACTEXPIRATIONDATE = "DataScadenzaContratto";
    public static final String ATT_ACCOUNT_DATEOFBIRTH = "DataNascita";
    public static final String ATT_ACCOUNT_PROVINCEDOMICILE = "DomicilioProvincia";
    public static final String ATT_ACCOUNT_PROVINCERESIDENCE = "ResidenzaProvincia";    
    public static final String ATT_ACCOUNT_EMAIL = "Email";
    // Si devono inserire tra "" i nomi come stanno nel DB
    public static final String ATT_ACCOUNT_FACULTY = "Facolta";
    public static final String ATT_ACCOUNT_FAMILYSITUATION = "SituazioneFamiliare";
    public static final String ATT_ACCOUNT_FAX = "Fax";
    public static final String ATT_ACCOUNT_ID = "Id";
    public static final String ATT_ACCOUNT_INCOME = "Reddito";
    public static final String ATT_ACCOUNT_MUNICIPALITYDOMICILIE = "DomicilioComune";
    public static final String ATT_ACCOUNT_MUNICIPALITYRESIDENCE = "ResidenzaComune";
    public static final String ATT_ACCOUNT_NAME = "Nome";
    public static final String ATT_ACCOUNT_NICKNAME = "Nickname";
    public static final String ATT_ACCOUNT_PASSWORD = "Password";
    public static final String ATT_ACCOUNT_PLACEOFBIRTH = "ComuneNascita";
    public static final String ATT_ACCOUNT_QUALIFICATION = "TitoloStudio";
    //Si devono inserire tra "" i nomi come stanno nel DB
    public static final String ATT_ACCOUNT_REGISTRATIONDATE = "DataRegistrazione";
    public static final String ATT_ACCOUNT_SURNAMEUSER = "Cognome";
    public static final String ATT_ACCOUNT_TAXCODE = "CodiceFiscale";
    public static final String ATT_ACCOUNT_TELEPHONENUMBER = "Telefono";
    public static final String ATT_ACCOUNT_TYPEACCOUNT = "TipologiaAccount";
    public static final String ATT_ACCOUNT_TYPEPARENT = "TipologiaGenitore";
    public static final String ATT_ACCOUNT_ADDRESSDOMICILE = "DomicilioIndirizzo";
    public static final String ATT_ACCOUNT_ADDRESSRESIDENCE = "ResidenzaIndirizzo";
    public static final String ATT_ACCOUNT_STATE = "Stato";
    //AssegnationTable's attributes
    public static final String ATT_ASSEGNATION_CLASSID = "Classe";
    public static final String ATT_ASSEGNATION_EDUCATORACCOUNTID = "Educatore";
    //ClassTable's attributes
    public static final String ATT_CLASS_ID = "Id";
    public static final String ATT_CLASS_NAME = "Nome";
    //ClassificationTable's attributes
    public static final String ATT_CLASSIFICATION_ID = "Id";
    public static final String ATT_CLASSIFICATION_NAME = "Nome";
    public static final String ATT_CLASSIFICATION_DATA = "Data";
    public static final String ATT_CLASSIFICATION_STATUS = "Stato";
    //ResultTable's attributes
    public static final String ATT_RESULT_REGISTRATIONCHILDID = "Iscrizione";
    public static final String ATT_RESULT_CLASSIFICATIONID = "Graduatoria";
    public static final String ATT_RESULT_RESULT = "Esito";
    //RegistrationChildTable's attributes
    public static final String ATT_REGISTRATIONCHILD_ID = "Id";
    public static final String ATT_REGISTRATIONCHILD_SURNAME = "Cognome";
    public static final String ATT_REGISTRATIONCHILD_NAME = "Nome";
    public static final String ATT_REGISTRATIONCHILD_BORNDATE = "DataNascita";
    public static final String ATT_REGISTRATIONCHILD_COMMONBORN = "ComuneNascita";
    public static final String ATT_REGISTRATIONCHILD_FISCALCODE = "CodiceFiscale";
    public static final String ATT_REGISTRATIONCHILD_CITIZENSHIP = "Cittadinanza";
    public static final String ATT_REGISTRATIONCHILD_USERSECTION = "FasciaUtenza";
    public static final String ATT_REGISTRATIONCHILD_REGISTRATIONDATE = "DataIscrizione";
    public static final String ATT_REGISTRATIONCHILD_SICK = "Malattia";
    public static final String ATT_REGISTRATIONCHILD_REGISTRATIONPHASE = "FaseDellIscrizione";
    public static final String ATT_REGISTRATIONCHILD_PARENTACCOUNTID = "AccountGenitore";
    public static final String ATT_REGISTRATIONCHILD_CLASSID = "Classe";
    // Recourses's attributes
    public static final String ATT_RECOURSE_ID = "Id";
    public static final String ATT_RECOURSE_DATA = "Data";
    public static final String ATT_RECOURSE_REASON = "Motivo";
    public static final String ATT_RECOURSE_VALUTATION = "Valutazione";
    public static final String ATT_RECOURSE_REGISTRATIONCHILDID = "Iscrizione";
    //RinunciaTable's attributes
    public static final String ATT_RINUNCIA_ID = "Id";
    public static final String ATT_RINUNCIA_DATE = "Data";
    public static final String ATT_RINUNCIA_CONFIRM = "Conferma";
    public static final String ATT_RINUNCIA_REGISTRATIONCHILDID = "Iscrizione";
    
    //RequestTable's attributes
    public static final String ATT_REQUEST_ID = "id";
    public static final String ATT_REQUEST_DATE = "data";
    public static final String ATT_REQUEST_DESCRIPTION = "descrizione";
    public static final String ATT_REQUEST_TIME = "time";
    public static final String ATT_REQUEST_ACCOUNT_PARENT = "accountGenitore";
    //MealRequestTable's attributes
    public static final String ATT_MEALREQ_ID = "Id";
    public static final String ATT_MEALREQ_MENUTYPE = "type";
    public static final String ATT_MEALREQ_DATE = "date";
    public static final String ATT_MEALREQ_CHILDINSCID = "Iscrizione";
    //TimeServiceRequestTable's attributes
    public static final String ATT_TIMESERVREQ_ID = "Id";
    public static final String ATT_TIMESERVREQ_DAYREQ = "GiornoModifica";
    public static final String ATT_TIMESERVREQ_SERVTYPE = "TipoServizio";
    public static final String ATT_TIMESERVREQ_DATE = "Data";
    public static final String ATT_TIMESERVREQ_REQTIME = "Ora";
    public static final String ATT_TIMESERVREQ_PARENTID = "AccountGenitore";
    //DifferentiatedMenuTable's attributes
    public static final String ATT_DIFFMENU_ID = "Id";
    public static final String ATT_DIFFMENU_MOTIVATION = "Motivazione";
    public static final String ATT_DIFFMENU_FIRST = "Primo";
    public static final String ATT_DIFFMENU_SECOND = "Secondo";
    public static final String ATT_DIFFMENU_SIDEDISH = "Contorno";
    public static final String ATT_DIFFMENU_FRUIT = "Frutta";
    public static final String ATT_DIFFMENU_CHILDINSCID = "Iscrizione";
    //PaymentTable's attributes
    public static final String ATT_PAYMENT_ID = "Id";
    public static final String ATT_PAYMENT_EXPDATE = "DataScademza";
    public static final String ATT_PAYMENT_AMOUNT = "Importo";
    public static final String ATT_PAYMENT_PAID = "Effettuato";
    public static final String ATT_PAYMENT_ORIGINACCOUNT = "Conto";
    public static final String ATT_PAYMENT_PAYEE = "Beneficiario";
    public static final String ATT_PAYMENT_PARENTID = "AccountGenitore";
    public static final String ATT_PAYMENT_CHARGE = "Addebito";
    public static final String ATT_PAYMENT_DESCRIPTION = "Descrizione";
    public static final String ATT_PAYMENT_DISCOUNT = "Sconto";
    public static final String ATT_PAYMENT_DISCDESCRIPTION = "DescrizioneSconto";
    //RefundTable's attributes
    public static final String ATT_REFUND_ID = "Id";
    public static final String ATT_REFUND_DESCRIPTION = "Descrizione";
    public static final String ATT_REFUND_AMOUNT = "Importo";
    public static final String ATT_REFUND_PARENTID = "Genitore";
    
    
    //Trainee activity's attributes
    public static final String ATT_TRAINEEACTIVITY_DATE = "Data";
    public static final String ATT_TRAINEEACTIVITY_NAME = "Nome";
    public static final String ATT_TRAINEEACTIVITY_DESCRIPTION = "Descrizione";
    public static final String ATT_TRAINEEACTIVITY_STARTTIME = "OraInizio";
    public static final String ATT_TRAINEEACTIVITY_ENDTIME = "OraFine";
    public static final String ATT_TRAINEEACTIVITY_DELEGATEACCOUNT = "Delegato";
    public static final String ATT_TRAINEEACTIVITY_TRAINEE = "Tirocinante";
    public static final String ATT_TRAINEEACTIVITY_ID = "Id";
    public static final String ATT_TRAINEEACTIVITY_OPINION="Giudizio";
    // Reunion's attributes
    public static final String ATT_REUNION_ID = "Id";
    public static final String ATT_REUNION_TITLE = "Titolo";
    public static final String ATT_REUNION_DESCRIPTION = "Descrizione";
    public static final String ATT_REUNION_DATA = "Data";
    public static final String ATT_REUNION_TIME = "Ora";
    public static final String ATT_REUNION_TYPE = "Tipo";
    //News attributes
    public static final String ATT_NEWS_ID = "Id";
    public static final String ATT_NEWS_TITLE = "Titolo";
    public static final String ATT_NEWS_DESCRIPTION = "Contenuto";
    public static final String ATT_NEWS_TYPE = "Tipo";
    public static final String ATT_NEWS_DATE = "Data";
    public static final String ATT_NEWS_TIME = "Ora";
    public static final String ATT_NEWS_ATTACHED = "Allegato";
    public static final String ATT_NEWS_DELEGATEACCOUNT = "IdAutore";
    //Communication's attributes
    public static final String ATT_COMMUNICATION_ID = "id";
    public static final String ATT_COMMUNICATION_TYPE = "tipo";
    public static final String ATT_COMMUNICATION_IDEDUCATOR = "idEducatore";
    public static final String ATT_COMMUNICATION_IDCHILD = "idBambino";
    public static final String ATT_COMMUNICATION_DESCRIPTION = "descrizione";
    public static final String ATT_COMMUNICATION_DATE = "data";
    public static final String ATT_COMMUNICATION_SOLVED = "risolto";
    //Project Annual attributes
    public static final String ATT_PROJECTANNUAL_ID = "Id";
    public static final String ATT_PROJECTANNUAL_NAME = "Nome";
    public static final String ATT_PROJECTANNUAL_TOPIC = "Tema";
    public static final String ATT_PROJECTANNUAL_DESCRIPTION = "Contenuto";
    public static final String ATT_PROJECTANNUAL_APPLICATIONYEAR = "Anno applicazione";
    //ProjectAnnualSection attributes
    public static final String ATT_PROJECTANNUALSECTION_ID = "Id";
    public static final String ATT_PROJECTANNUALSECTION_NAME = "Nome";
    public static final String ATT_PROJECTANNUALSECTION_SECTION = "Sezione";
    public static final String ATT_PROJECTANNUALSECTION_DESCRIPTION = "COntenuto";
    public static final String ATT_PROJECTANNUALSECTION_IDYEAR = "IdAnnuale";
    //Comment attributes
    public static final String ATT_COMMENT_ID = "Id";
    public static final String ATT_COMMENT_DATE = "Data";
    public static final String ATT_COMMENT_TYPEMODIFY = "TipoModifica";
    public static final String ATT_COMMENT_DESCRIPTION = "Contenuto";
    public static final String ATT_COMMENT_IDYEAR = "IdAnnuale";
    public static final String ATT_COMMENT_IDSECTION = "IdSezione";
    public static final String ATT_COMMENT_IDAUTHOR = "IdAutore";
    //Activity attributes
    public static final String ATT_ACTIVITY_ID = "Id";
    public static final String ATT_ACTIVITY_PERIOD = "Periodo";
    public static final String ATT_ACTIVITY_NAME = "Nome";
    public static final String ATT_ACTIVITY_DESCRIPTION = "Contenuto";
    //Activity Section Daily
    public static final String ATT_ACTIVITYSECTTIONDAILY_ID = "Id";
    public static final String ATT_ACTIVITYSECTTIONDAILY_IDACTITIVTY = "IdAttivita";
    public static final String ATT_ACTIVITYSECTTIONDAILY_IDEDUCATORE = "IdEducatore";
    public static final String ATT_ACTIVITYSECTTIONDAILY_DATE = "Data";
    public static final String ATT_ACTIVITYSECTTIONDAILY_NOTES = "Note";
    //Child Partecipant attributes
    public static final String ATT_CHILDPARTECIPANT_IDCHILD = "IdBambino";
    public static final String ATT_CHILDPARTECIPANT_IDACTIVITYDAILY = "IdAttivitaGiornaliera";
    public static final String ATT_CHILDPARTECIPANT_STATE = "Stato";
    //Trainee Request
    public static final String ATT_TRAINEEREQUEST_ID = "Id";
    public static final String ATT_TRAINEEREQUEST_TRAINEENUMBER = "NumeroTirocinanti";
    public static final String ATT_TRAINEEREQUEST_DELEGATE = "Delegato";
    public static final String ATT_TRAINEEREQUEST_DATE = "Data";
    public static final String ATT_TRAINEEREQUEST_ACTIVITY = "Attivita";
    public static final String ATT_TRAINEEREQUEST_STARTTIME = "OraInizio";
    public static final String ATT_TRAINEEREQUEST_ENDTIME = "OraFine";
    //Trainee Convocation
    public static final String ATT_TRAINEECONVOCATION_ID = "Id";
    public static final String ATT_TRAINEECONVOCATION_DATE = "Data";
    public static final String ATT_TRAINEECONVOCATION_STARTTIME = "OraInizio";
    public static final String ATT_TRAINEECONVOCATION_ENDTIME = "OraFine";
    public static final String ATT_TRAINEECONVOCATION_ACTIVITYNAME = "NomeAttivita";
    public static final String ATT_TRAINEECONVOCATION_CONFIRMED = "Confermato";
    public static final String ATT_TRAINEECONVOCATION_DELEGATE = "IdDelegato";
    public static final String ATT_TRAINEECONVOCATION_TRAINEE = "IdTirocinante";
}
