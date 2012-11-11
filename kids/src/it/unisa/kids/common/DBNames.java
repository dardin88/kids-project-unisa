package it.unisa.kids.common;

public final class DBNames {
	
	private DBNames() {
		
	}

	public static final String TABLE_ACCOUNT = "account";
	public static final String TABLE_RECOURSE = "recourse";
	public static final String TABLE_RINUNCIA = "";		// da completare/definire
	public static final String TABLE_CLASSIFICATION = "classification";
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
}
