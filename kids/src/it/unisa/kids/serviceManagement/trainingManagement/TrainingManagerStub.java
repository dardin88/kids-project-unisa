/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Stub;

import it.unisa.kids.accessManagement.accountManagement.Account;
import it.unisa.kids.serviceManagement.trainingManagement.ITrainingManager;
import it.unisa.kids.serviceManagement.trainingManagement.TraineeActivity;
import it.unisa.kids.serviceManagement.trainingManagement.TraineeConvocation;
import it.unisa.kids.serviceManagement.trainingManagement.TraineeRequest;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class TrainingManagerStub implements ITrainingManager {

    @Override
    public void insert(Account pTrainee) throws SQLException {
    }

    @Override
    public void insert(TraineeRequest pTraineeRequest) throws SQLException {
    }

    @Override
    public void insert(TraineeActivity pTraineeActivity) throws SQLException {
    }

    @Override
    public void insert(TraineeConvocation pTraineeConvocation) throws SQLException {
    }

    @Override
    public void update(TraineeActivity pTraineeActivity) throws SQLException {
    }

    @Override
    public void update(TraineeRequest pTraineeRequest) throws SQLException {
    }

    @Override
    public void update(Account pTrainee) throws SQLException {
    }

    @Override
    public void update(TraineeConvocation pTraineeConvocation) throws SQLException {
    }

    @Override
    public void delete(Account pTrainee) throws SQLException {
    }

    @Override
    public void delete(TraineeActivity pTraineeActivity) throws SQLException {
    }

    @Override
    public void delete(TraineeRequest pTraineeRequest) throws SQLException {
    }

    @Override
    public void delete(TraineeConvocation pTraineeConvocation) throws SQLException {
    }

    @Override
    public List<Account> search(Account pTrainee) throws SQLException {
        List<Account> toReturn = new ArrayList<Account>();
        Account toReturnAccount = new Account();
        toReturnAccount.setAccountType("Tirocinante");
        toReturnAccount.setAddressDomicile("aa");
        toReturnAccount.setCapDomicile("809");
        toReturnAccount.setCapResidence("809");
        toReturnAccount.setCellularNumber("79904");
        toReturnAccount.setCitizenship("prova");
        toReturnAccount.setContractExpirationDate(new GregorianCalendar(2010, 10, 10));
        toReturnAccount.setDataOfBirth(new GregorianCalendar(2010, 10, 10));
        toReturnAccount.setEmail("mail");
        toReturnAccount.setFaculty("ads");
        toReturnAccount.setFamilySituation("prova");
        toReturnAccount.setFax("34567");
        toReturnAccount.setId(45);
        toReturnAccount.setIncome(124);
        toReturnAccount.setMunicipalityDomicile("prova");
        toReturnAccount.setMunicipalityResidence("sdgh");
        toReturnAccount.setNameUser("nome");
        toReturnAccount.setNickName("pr");
        toReturnAccount.setPassword("asdg");
        toReturnAccount.setPlaceofBirth("asdg");
        toReturnAccount.setProvinceDomicile("asfg");
        toReturnAccount.setProvinceResidence("sga");
        toReturnAccount.setQualification("sadg");
        toReturnAccount.setRegister("setag");
        toReturnAccount.setRegistrationDate(new GregorianCalendar(2010, 10, 10));
        toReturnAccount.setState("pro");
        toReturnAccount.setSurnameUser("user");
        toReturnAccount.setTaxCode("656789");
        toReturnAccount.setTelephoneNumber("35467");
        toReturnAccount.setTypeParent("Studente");
        toReturnAccount.setViaDomicile("sdgfhs");
        toReturnAccount.setViaResidence("dsjgg");

        toReturn.add(toReturnAccount);

        return toReturn;

    }

    @Override
    public List<TraineeActivity> search(TraineeActivity pTraineeActivity) throws SQLException {
        List<TraineeActivity> toReturn = new ArrayList<TraineeActivity>();
        TraineeActivity toReturnActivity = new TraineeActivity();

        toReturnActivity.setDate(new GregorianCalendar(2010, 10, 10));
        toReturnActivity.setDelegate(32);
        toReturnActivity.setDescription("sgdafh");
        toReturnActivity.setEnd(new Time(4567));
        toReturnActivity.setId(32456);
        toReturnActivity.setName("ssdhf");
        toReturnActivity.setOpinion("sdgdfsh");
        toReturnActivity.setStart(new Time(789));
        toReturnActivity.setTrainee(345);

        toReturn.add(toReturnActivity);
        return toReturn;
    }

    @Override
    public List<TraineeRequest> search(TraineeRequest pTraineeRequest) throws SQLException {
        List<TraineeRequest> toReturn = new ArrayList<TraineeRequest>();

        TraineeRequest request = new TraineeRequest();

        request.setActivity("sdfgh");
        request.setDate(new GregorianCalendar(2010, 10, 10));
        request.setDelegate(345);
        request.setEndTime(new Time(3456));
        request.setId(345);
        request.setStartTime(new Time(43567));
        request.setTraineeNumber(3456);

        toReturn.add(request);

        return toReturn;
    }

    @Override
    public List<TraineeConvocation> search(TraineeConvocation pTraineeConvocation) throws SQLException {
        List<TraineeConvocation> toReturn = new ArrayList<TraineeConvocation>();

        TraineeConvocation convocation = new TraineeConvocation();

        convocation.setActivityName("asdfgh");
        convocation.setConfirmed(3456);
        convocation.setDate(new GregorianCalendar(2010, 10, 10));
        convocation.setDelegateId(67);
        convocation.setEndTime(new Time(3245));
        convocation.setId(3456);
        convocation.setStartTime(new Time(23456));
        convocation.setTraineeId(34565);

        toReturn.add(convocation);

        return toReturn;
    }

    @Override
    public List<TraineeRequest> getRequestsList() throws SQLException {
        List<TraineeRequest> toReturn = new ArrayList<TraineeRequest>();

        TraineeRequest request = new TraineeRequest();

        request.setActivity("sdfgh");
        request.setDate(new GregorianCalendar(2010, 10, 10));
        request.setDelegate(345);
        request.setEndTime(new Time(3456));
        request.setId(345);
        request.setStartTime(new Time(43567));
        request.setTraineeNumber(3456);

        toReturn.add(request);

        return toReturn;
    }

    @Override
    public List<TraineeActivity> getTraineeActivityList() throws SQLException {
        List<TraineeActivity> toReturn = new ArrayList<TraineeActivity>();
        TraineeActivity toReturnActivity = new TraineeActivity();

        toReturnActivity.setDate(new GregorianCalendar(2010, 10, 10));
        toReturnActivity.setDelegate(32);
        toReturnActivity.setDescription("sgdafh");
        toReturnActivity.setEnd(new Time(4567));
        toReturnActivity.setId(32456);
        toReturnActivity.setName("ssdhf");
        toReturnActivity.setOpinion("sdgdfsh");
        toReturnActivity.setStart(new Time(789));
        toReturnActivity.setTrainee(345);

        toReturn.add(toReturnActivity);
        return toReturn;
    }

    @Override
    public List<Account> getTraineeList() throws SQLException {
        List<Account> toReturn = new ArrayList<Account>();
        Account toReturnAccount = new Account();
        toReturnAccount.setAccountType("Tirocinante");
        toReturnAccount.setAddressDomicile("aa");
        toReturnAccount.setCapDomicile("809");
        toReturnAccount.setCapResidence("809");
        toReturnAccount.setCellularNumber("79904");
        toReturnAccount.setCitizenship("prova");
        toReturnAccount.setContractExpirationDate(new GregorianCalendar(2010, 10, 10));
        toReturnAccount.setDataOfBirth(new GregorianCalendar(2010, 10, 10));
        toReturnAccount.setEmail("mail");
        toReturnAccount.setFaculty("ads");
        toReturnAccount.setFamilySituation("prova");
        toReturnAccount.setFax("34567");
        toReturnAccount.setId(45);
        toReturnAccount.setIncome(124);
        toReturnAccount.setMunicipalityDomicile("prova");
        toReturnAccount.setMunicipalityResidence("sdgh");
        toReturnAccount.setNameUser("nome");
        toReturnAccount.setNickName("pr");
        toReturnAccount.setPassword("asdg");
        toReturnAccount.setPlaceofBirth("asdg");
        toReturnAccount.setProvinceDomicile("asfg");
        toReturnAccount.setProvinceResidence("sga");
        toReturnAccount.setQualification("sadg");
        toReturnAccount.setRegister("setag");
        toReturnAccount.setRegistrationDate(new GregorianCalendar(2010, 10, 10));
        toReturnAccount.setState("pro");
        toReturnAccount.setSurnameUser("user");
        toReturnAccount.setTaxCode("656789");
        toReturnAccount.setTelephoneNumber("35467");
        toReturnAccount.setTypeParent("Studente");
        toReturnAccount.setViaDomicile("sdgfhs");
        toReturnAccount.setViaResidence("dsjgg");

        toReturn.add(toReturnAccount);

        return toReturn;
    }

    @Override
    public List<TraineeConvocation> getTraineeConvocationList() throws SQLException {
        List<TraineeConvocation> toReturn = new ArrayList<TraineeConvocation>();

        TraineeConvocation convocation = new TraineeConvocation();

        convocation.setActivityName("asdfgh");
        convocation.setConfirmed(3456);
        convocation.setDate(new GregorianCalendar(2010, 10, 10));
        convocation.setDelegateId(67);
        convocation.setEndTime(new Time(3245));
        convocation.setId(3456);
        convocation.setStartTime(new Time(23456));
        convocation.setTraineeId(34565);

        toReturn.add(convocation);

        return toReturn;
    }
}
