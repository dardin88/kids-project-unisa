package it.unisa.kids.accessManagement.registrationChildManagement;

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
    private GregorianCalendar registrationDate; 
    private String userRange;
    private String registrationPhase;
    private int parentId;
    private int sectionId;
    
    private String sickness;
    private String vaccinations;
    private String privacyStatement;
    private String additionalNotes;

    private String isSicknessSet;
    private String isVaccinationsSet;
    private String isPrivacyStatementSet;
    
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
    public GregorianCalendar getRegistrationDate() {
        return registrationDate;
    }
    public void setRegistrationDate(GregorianCalendar registrationDate) {
        this.registrationDate = registrationDate;
    }
    public String getUserRange() {
            return userRange;
    }
    public void setUserRange(String userRange) {
        this.userRange = userRange;
    }
    public String getRegistrationPhase() {
        return registrationPhase;
    }
    public void setRegistrationPhase(String registrationPhase) {
        this.registrationPhase = registrationPhase;
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
    
    public String getSickness() {
        return sickness;
    }
    public void setSickness(String sickness) {
        this.sickness = sickness;
    }

    public String getVaccinations() {
        return vaccinations;
    }

    public void setVaccinations(String vaccinations) {
        this.vaccinations = vaccinations;
    }

    public String getPrivacyStatement() {
        return privacyStatement;
    }

    public void setPrivacyStatement(String privacyStatement) {
        this.privacyStatement = privacyStatement;
    }

    public String getIsSicknessSet() {
        return isSicknessSet;
    }

    public void setIsSicknessSet(String isSicknessSet) {
        this.isSicknessSet = isSicknessSet;
    }

    public String getIsVaccinationsSet() {
        return isVaccinationsSet;
    }

    public void setIsVaccinationsSet(String isVaccinationsSet) {
        this.isVaccinationsSet = isVaccinationsSet;
    }

    public String getIsPrivacyStatementSet() {
        return isPrivacyStatementSet;
    }

    public void setIsPrivacyStatementSet(String isPrivacyStatementSet) {
        this.isPrivacyStatementSet = isPrivacyStatementSet;
    }
    
    public String getAdditionalNotes() {
        return additionalNotes;
    }

    public void setAdditionalNotes(String additionalNotes) {
        this.additionalNotes = additionalNotes;
    }
    
}
