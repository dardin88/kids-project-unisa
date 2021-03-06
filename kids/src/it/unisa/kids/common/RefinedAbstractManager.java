package it.unisa.kids.common;

import it.unisa.kids.communicationManagement.childrenManagement.JDBCCommunicationManager;
import it.unisa.kids.communicationManagement.newsManagement.JDBCNewsManager;
import it.unisa.kids.communicationManagement.surveyManagement.JDBCSurveyManager;
import it.unisa.kids.serviceManagement.paymentManagement.JDBCPaymentManager;
import it.unisa.kids.serviceManagement.trainingManagement.JDBCTrainingManager;
import it.unisa.kids.accessManagement.accountManagement.*;
import it.unisa.kids.accessManagement.classManagement.JDBCClassManager;
import it.unisa.kids.accessManagement.classificationManagement.JDBCClassificationManager;
import it.unisa.kids.accessManagement.recoursesManagement.JDBCRecoursesManager;
import it.unisa.kids.accessManagement.registrationChildManagement.JDBCRegistrationChildManager;
import it.unisa.kids.accessManagement.renunciationManagement.JDBCRenunciationManager;
import it.unisa.kids.communicationManagement.meeting.JDBCMeetingManager;
import it.unisa.kids.communicationManagement.programEducationalManagement.JDBCActivityManager;
import it.unisa.kids.communicationManagement.programEducationalManagement.JDBCProgramEducational;
import it.unisa.kids.serviceManagement.canteenManagement.JDBCCanteenManager;
import it.unisa.kids.serviceManagement.timeServiceManagement.JDBCTimeServiceManager;

public class RefinedAbstractManager extends AbstractManager<IManager> {

    // Singleton Design Pattern's implementation
    private static RefinedAbstractManager refinedManager;

    private RefinedAbstractManager() {
    }

    public static RefinedAbstractManager getInstance() {
        if (refinedManager == null) {
            refinedManager = new RefinedAbstractManager();
        }
        return refinedManager;
    }
    // end of Singleton Design Pattern's implementation

    public IManager getManagerImplementor(String pManagerType) {
        if (pManagerType.equals(DBNames.TABLE_PAYMENT)) {
            this.imp = JDBCPaymentManager.getInstance();
        } else if (pManagerType.equals(DBNames.TABLE_TRAINEE)) {
            this.imp = JDBCTrainingManager.getInstance();
        } else if (pManagerType.equals(DBNames.TABLE_NEWS)) {
            this.imp = JDBCNewsManager.getInstance();
        } else if (pManagerType.equals(DBNames.TABLE_SURVEY)) {
            this.imp = JDBCSurveyManager.getInstance();
        } else if (pManagerType.equals(DBNames.TABLE_COMMUNICATION)) {
            this.imp = JDBCCommunicationManager.getInstance();
        } else if (pManagerType.equals(DBNames.TABLE_ACCOUNT)) {
            this.imp = JDBCAccountManager.getInstance();
        } else if (pManagerType.equals(DBNames.TABLE_REGISTRATIONCHILD)) {
            this.imp = JDBCRegistrationChildManager.getInstance();
        } else if (pManagerType.equals(DBNames.TABLE_CLASSIFICATION)) {
            this.imp = JDBCClassificationManager.getInstance();
        } else if (pManagerType.equals(DBNames.TABLE_RENUNCIATION)) {
            this.imp = JDBCRenunciationManager.getInstance();
        } else if (pManagerType.equals(DBNames.TABLE_RECOURSE)) {
            this.imp = JDBCRecoursesManager.getInstance();
        } else if (pManagerType.equals(DBNames.TABLE_TIMESERVICE)) {
            this.imp = JDBCTimeServiceManager.getInstance();
        } else if (pManagerType.equals(DBNames.TABLE_CLASS)) {
            this.imp = JDBCClassManager.getInstance();
        } else if (pManagerType.equals(DBNames.TABLE_MENU)) {
            this.imp = JDBCCanteenManager.getInstance();
        }else if (pManagerType.equals(DBNames.TABLE_DAILY_SECTION_ACT)) {
            this.imp = JDBCActivityManager.getInstance();
        }else if (pManagerType.equals(DBNames.TABLE_REUNION)) {
            this.imp = JDBCMeetingManager.getInstance();
        }else if (pManagerType.equals(DBNames.TABLE_ANNUAL_PROJ)){
            this.imp=JDBCProgramEducational.getInstance();
        }
        return this.imp;
    }
}
