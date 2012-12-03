package it.unisa.kids.accessManagement.registrationChildManagement;

import it.unisa.kids.common.DBNames;
import java.util.GregorianCalendar;

/**
 * Class of rappresentation of an Application of Registration for a Child
 * 
 * @author Lauri Giuseppe Giovanni
 */
public class RegistrationChild {
    private int id;
    private String surname;
    private String name;
    private GregorianCalendar birthDate;
    private String birthPlace;
    private String fiscalCode;
    private String citizenship;
    private String sickness;
    private GregorianCalendar registrationDate; 
    private UserRange userRange;
    private RegistrationPhase registrationPhase;
    private int parentId;
    private int sectionId;
    
    private enum UserRange {
        FULL_TIME(DBNames.ATT_REGISTRATIONCHILD_ENUM_USERRANGE_FULLTIME), 
        PART_TIME_MATTUTINA(DBNames.ATT_REGISTRATIONCHILD_ENUM_USERRANGE_PARTTIMEAM),
        PART_TIME_POMERIDIANA(DBNames.ATT_REGISTRATIONCHILD_ENUM_USERRANGE_PARTTIMEPM);
        
        private final String description; // attributo

        UserRange(String desc) { // costruttore
                this.description = desc;
        }
        public String getDescription() { // metodo
                return description;
        }
    };		

    private enum RegistrationPhase {
        BOZZA(DBNames.ATT_REGISTRATIONCHILD_ENUM_REGISTRATIONPHASE_DRAFT),
        SOTTOMESSA(DBNames.ATT_REGISTRATIONCHILD_ENUM_REGISTRATIONPHASE_SUBMITTED),
        CONFERMATA(DBNames.ATT_REGISTRATIONCHILD_ENUM_REGISTRATIONPHASE_CONFIRMED),
        RIFIUTATA(DBNames.ATT_REGISTRATIONCHILD_ENUM_REGISTRATIONPHASE_UNACCEPTED),
        ACCETTATA(DBNames.ATT_REGISTRATIONCHILD_ENUM_REGISTRATIONPHASE_ACCEPTED),
        ELIMINATA(DBNames.ATT_REGISTRATIONCHILD_ENUM_REGISTRATIONPHASE_DELETED),
        RINUNCIATA(DBNames.ATT_REGISTRATIONCHILD_ENUM_REGISTRATIONPHASE_RENOUNCED),
        RICORSO(DBNames.ATT_REGISTRATIONCHILD_ENUM_REGISTRATIONPHASE_RECOURSE);

        private final String description; // attributo

        RegistrationPhase(String desc) { // costruttore
                this.description = desc;
        }
        public String getDescription() { // metodo
                return description;
        }
    };

    public RegistrationChild() {
    }

    public int getId() {
        return id;
    }
    public void setId(int registrationId) {
        this.id = registrationId;
    }
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public GregorianCalendar getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(GregorianCalendar birthDates) {
        this.birthDate = birthDates;
    }
    public String getBirthPlace() {
        return birthPlace;
    }
    public void setBirthPlace(String birthplace) {
        this.birthPlace = birthplace;
    }
    public String getFiscalCode() {
        return fiscalCode;
    }
    public void setFiscalCode(String fiscalCode) {
        this.fiscalCode = fiscalCode;
    }
    public String getCitizenship() {
        return citizenship;
    }
    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }
    public String getSickness() {
        return sickness;
    }
    public void setSickness(String sickness) {
        this.sickness = sickness;
    }

    public GregorianCalendar getRegistrationDate() {
        return registrationDate;
    }
    public void setRegistrationDate(GregorianCalendar registrationDate) {
        this.registrationDate = registrationDate;
    }
    public String getUserRange() {
            String result="";

            switch (userRange) {
            case FULL_TIME:
                    result = DBNames.ATT_REGISTRATIONCHILD_ENUM_USERRANGE_FULLTIME;
                    break;
            case PART_TIME_MATTUTINA:
                    result = DBNames.ATT_REGISTRATIONCHILD_ENUM_USERRANGE_PARTTIMEAM;
                    break;
            case PART_TIME_POMERIDIANA:
                    result = DBNames.ATT_REGISTRATIONCHILD_ENUM_USERRANGE_PARTTIMEPM;
                    break;
            }
            return result;
    }
    public void setUserRange(String userSections) {
        if(userSections.equals(UserRange.FULL_TIME.getDescription()))
            userRange = UserRange.FULL_TIME;
        if(userSections.equals(UserRange.PART_TIME_MATTUTINA.getDescription()))
            userRange = UserRange.PART_TIME_MATTUTINA;
        if(userSections.equals(UserRange.PART_TIME_POMERIDIANA.getDescription()))
            userRange = UserRange.PART_TIME_POMERIDIANA;
    }
    public String getRegistrationPhase() {
        String result="";

        switch (registrationPhase) {
        case BOZZA:
                result=RegistrationPhase.BOZZA.getDescription();
                break;
        case SOTTOMESSA:
                result=RegistrationPhase.SOTTOMESSA.getDescription();
                break;
        case CONFERMATA:
                result=RegistrationPhase.CONFERMATA.getDescription();
                break;
        case RIFIUTATA:
                result=RegistrationPhase.RIFIUTATA.getDescription();
                break;
        case ACCETTATA:
                result=RegistrationPhase.ACCETTATA.getDescription();
                break;
        case ELIMINATA:
                result=RegistrationPhase.ELIMINATA.getDescription();
                break;
        case RINUNCIATA:
                result=RegistrationPhase.RINUNCIATA.getDescription();
                break;
        case RICORSO:
                result=RegistrationPhase.RICORSO.getDescription();
                break;
        }
        return result;
    }
    public void setRegistrationPhase(String registrationPhases) {
            if(registrationPhases.equals(RegistrationPhase.BOZZA.getDescription()))
                    registrationPhase=RegistrationPhase.BOZZA;
            if(registrationPhases.equals(RegistrationPhase.SOTTOMESSA.getDescription()))
                    registrationPhase=RegistrationPhase.SOTTOMESSA;
            if(registrationPhases.equals(RegistrationPhase.CONFERMATA.getDescription()))
                    registrationPhase=RegistrationPhase.CONFERMATA;
            if(registrationPhases.equals(RegistrationPhase.RIFIUTATA.getDescription()))
                    registrationPhase=RegistrationPhase.RIFIUTATA;
            if(registrationPhases.equals(RegistrationPhase.ACCETTATA.getDescription()))
                    registrationPhase=RegistrationPhase.ACCETTATA;
            if(registrationPhases.equals(RegistrationPhase.ELIMINATA.getDescription()))
                    registrationPhase=RegistrationPhase.ELIMINATA;
            if(registrationPhases.equals(RegistrationPhase.RICORSO.getDescription()))
                    registrationPhase=RegistrationPhase.RICORSO;
            if(registrationPhases.equals(RegistrationPhase.RINUNCIATA.getDescription()))
                    registrationPhase=RegistrationPhase.RINUNCIATA;
    }

    public int getParentId() {
            return this.parentId;
    }

    public void setParentId(int parentId) {
            this.parentId = parentId;
    }
    
    public int getSectionId() {
            return this.sectionId;
    }

    public void setSectionId(int sectionId) {
            this.sectionId = sectionId;
    }
}
