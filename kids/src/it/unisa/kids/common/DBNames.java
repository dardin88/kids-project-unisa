package it.unisa.kids.common;

public final class DBNames {
	
	private DBNames() {
		
	}

	public static final String TABLE_ACCOUNT = "account";
	public static final String TABLE_RECOURSE = "recourse";
	public static final String TABLE_RINUNCIA = "";		// da completare/definire
	public static final String TABLE_CLASSIFICATION = "Graduatoria";
	public static final String TABLE_CLASS = "class";
	public static final String TABLE_CHILD = "child_inscription";
	public static final String TABLE_ESITO = "";		// da completare/definire
	public static final String TABLE_ASSIGNATION = "assignation";
	
	public static final String TABLE_REQUEST = "request";
	public static final String TABLE_DIFF_MENU = "diff_menu";
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
	
	//Trainee' attributes
	public static final String ATT_TRAINEE_REGISTER="register";
	public static final String ATT_TRAINEE_NAME="name";
	public static final String ATT_TRAINEE_SURNAME="surname";
	public static final String ATT_TRAINEE_EMAIL="email";
	public static final String ATT_TRAINEE_BIRTHDATE="birth_date";
	public static final String ATT_TRAINEE_BIRTHCITY="birth_city";
	public static final String ATT_TRAINEE_CITYOFRESIDENCE="city_of_residence";
	public static final String ATT_TRAINEE_ADDRESS="address";
	public static final String ATT_TRAINEE_CAP="cap";
	public static final String ATT_TRAINEE_TELEPHONENUMBER="telephone_number";
	public static final String ATT_TRAINEE_DELEGATEACCOUNT="delegate_account";
	
	//Account's attributes
	public static final String ATT_ACCOUNT_CAPDOMICILIE="cap_domicilie";
	public static final String ATT_ACCOUNT_CAPRESIDENCE="cap_residence";
	public static final String ATT_ACCOUNT_CELLULARNUMBER="cellular_number";
	public static final String ATT_ACCOUNT_CITIZENSHIP="citizenship";
	public static final String ATT_ACCOUNT_CONTRACTEXPIRATIONDATE="contract_expiration_date";
	public static final String ATT_ACCOUNT_DATEOFBIRTH="date_of_birth";
	public static final String ATT_ACCOUNT_EMAIL="email";
	public static final String ATT_ACCOUNT_FACULTY="faculty";
	public static final String ATT_ACCOUNT_FAMILYSITUATION="family_situation";
	public static final String ATT_ACCOUNT_FAX="fax";
	public static final String ATT_ACCOUNT_ID="id";
	public static final String ATT_ACCOUNT_INCOME="income";
	public static final String ATT_ACCOUNT_MANUCIPALITYDOMICILIE="municipality_domicilie";
	public static final String ATT_ACCOUNT_MUNICIPALITYRESIDENCE="municipality_residence";
	public static final String ATT_ACCOUNT_NAMEUSER="name_user";
	public static final String ATT_ACCOUNT_NICKNAME="nickname";
	public static final String ATT_ACCOUNT_PASSWORD="password";
	public static final String ATT_ACCOUNT_PLACEOFBIRTH="place_of_birth";
	public static final String ATT_ACCOUNT_PROVINCERESIDENCE="province_residence";
	public static final String ATT_ACCOUNT_PROVINCEDOMICILIE="province_domicilie";
	public static final String ATT_ACCOUNT_QUALIFICATION="qualification";
	public static final String ATT_ACCOUNT_REGISTRATIONDATE="registration_date";
	public static final String ATT_ACCOUNT_STREETNUMBERDOMICILIE="street_number_domicilie";
	public static final String ATT_ACCOUNT_STREETNUMBERRESIDENCE="street_number_residence";
	public static final String ATT_ACCOUNT_SURNAMEUSER="surname_user";
	public static final String ATT_ACCOUNT_TAXCODE="tax_code";
	public static final String ATT_ACCOUNT_TELEPHONENUMBER="telephone_number";
	public static final String ATT_ACCOUNT_TYPEACCOUNT="type_account";
	public static final String ATT_ACCOUNT_TYPEPARENT="type_parent";
	public static final String ATT_ACCOUNT_VIADOMICILE="via_domicile";
	public static final String ATT_ACCOUNT_VIARESIDENCE="via_residence";
	






}
