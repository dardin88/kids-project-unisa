package it.unisa.kids.serviceManagement.canteenManagement;

import java.io.Serializable;
import java.util.GregorianCalendar;

public class MenuBean implements Serializable {

    public static final String DAILY_MENU = "Giornaliero";
    public static final String DIFF_MENU = "Differenziato";
    private int id;
    private String type;
    private GregorianCalendar date;
    private String first;
    private String second;
    private String sideDish;
    private String fruit;
    private int childInscriptionId;

    public MenuBean() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int pId) {
        this.id = pId;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String pType) {
        this.type = pType;
    }

    public GregorianCalendar getDate() {
        return this.date;
    }

    public void setDate(GregorianCalendar pDate) {
        this.date = pDate;
    }

    public String getFirst() {
        return this.first;
    }

    public void setFirst(String pFirst) {
        this.first = pFirst;
    }

    public String getSecond() {
        return this.second;
    }

    public void setSecond(String pSecond) {
        this.second = pSecond;
    }

    public String getSideDish() {
        return this.sideDish;
    }

    public void setSideDish(String pSideDish) {
        this.sideDish = pSideDish;
    }

    public String getFruit() {
        return this.fruit;
    }

    public void setFruit(String pFruit) {
        this.fruit = pFruit;
    }

    public int getChildInscriptionId() {
        return this.childInscriptionId;
    }

    public void setChildInscriptionId(int pChildInscriptionId) {
        this.childInscriptionId = pChildInscriptionId;
    }
}
