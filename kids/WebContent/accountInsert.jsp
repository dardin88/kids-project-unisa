<%-- 
    Document   : accountInsert
    Created on : 6-dic-2012
    Author     : Gianmarco Del Pozzo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--<c:if test="${sessionScope.user==null}">
    <c:redirect url="index.jsp" />
</c:if>--%>
<c:if test="${sessionScope.id >=0}">
    <c:if test="${sessionScope.user.getAccountType()=='Genitore'}"> 
        <c:redirect url="newsGenitorePage.jsp" />
    </c:if>
</c:if>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link rel="stylesheet" type="text/css" href="css/jquery.dataTables_themeroller.css" />
        <link rel="stylesheet" type="text/css" href="css/template.css">
        <link rel="stylesheet" type="text/css" href="css/overcast/jquery-ui-1.9.1.custom.min.css">

        <script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.9.1.custom.min.js"></script>
        <script type="text/javascript" src="js/jquery.validate.min.js"></script>
        <script type="text/javascript" src="js/additional-methods.min.js"></script>
        <script type="text/javascript" src="js/functions.js"></script>
        <script type="text/javascript" src="js/accountInsert.js"></script>

        <title>Kids</title>

        <script type="text/javascript">
            $(document).ready(function() {
                activePage();
                initializeRegistrationFields();
              
                $("#dateOfBirth, #contractExpirationDate, #registrationDate").datepicker({dateFormat:'yy-mm-dd'});
            });
        </script>
    </head>

    <div id="NickPassWindow" title="Visualizza dati accesso" style="display: inline">
        <form id="NickPassForm" class="cmxform" method="get" action="">
            <fieldset><br>
                <div >
                    <label id="accountLabel">  Nickname: </label>
                    <input id="accountNick" class="accountInput" type="text" name="Nick" size="50%" readonly="true" style="margin-left: 5%">
                </div>
                <div >
                    <label id="accountLabel">  Password: </label>
                    <input id="accountPass" class="accountInput" type="text" name="Nick" size="50%" readonly="true" style="margin-left: 5%">
                </div>

                <label style="margin-left: 5%">  N.B. Le verrà inviata una e-mail all'indirizzo da lei specificata </label>
                <label style="margin-left: 5%"> con i relativi dati di accesso. </label><br>
                <p class="formp">
                    <input type="button" class="confirmAddButton" id="showNickPass" value="Ok"/>
                </p>
            </fieldset>
        </form>
    </div>

    <%@include file="header.jsp"%>
    <body id="bodyRegistration">

        <form id="registrationForm" class="cmxform"  action="" method="post">

            <fieldset id="registrationFieldSet">
                <div id="artefactsManagement">
                    <div id="registration1">

                        <div >
                            <label id="accountLabel">  Nome*: </label>
                            <input id="accountName" class="accountInput" type="text" name="Nome" size="50%">
                        </div>

                        <div>
                            <label id="accountLabel"> Cognome*: </label>
                            <input id="accountSurname" class="accountInput"type="text" name="Cognome">                    
                        </div>

                        <div>
                            <label id="accountLabel"> Data di nascita*: </label>
                            <input id="dateOfBirth" class="accountInput" type="text" name="DataNascita">
                        </div>

                        <div>
                            <label id="accountLabel"> Comune di nascita*: </label>
                            <input id="placeOfBirth" class="accountInput" type="text" name="ComuneNascita">
                        </div>

                        <div>
                            <label id="accountLabel"> CodiceFiscale*: </label>
                            <input  id="taxCode" class="accountInput" type="text" name="CodiceFiscale">
                        </div>

                        <div>
                            <label id="accountLabel"> Cittadinanza*: </label>
                            <input  id="citizenship" class="accountInput" type="text" name="Cittadinanza">
                        </div>

                        <div>
                            <label id="accountLabel"> Comune di residenza*: </label>
                            <input  id="municipalityResidence" class="accountInput" type="text" name="ComuneResidenza">
                        </div>

                        <div>
                            <label id="accountLabel"> Provincia Residenza*: </label>
                            <input  id="provinceResidence" class="accountInput" type="text" name="ProvinciaResidenza">
                        </div>

                        <div>
                            <label id="accountLabel"> Indirizzo Residenza*: </label>
                            <input  id="viaResidence" class="accountInput" type="text" name="IndirizzoResidenza">
                        </div>

                        <input style="width: 200px; margin-left: 5%" type="button" name="notRegistrationButton1" id="notRegistrationButton1" value="Indietro"/>
                        <input style="width: 200px; margin-left: 2%" type="submit" name="registrationButton1" id="registrationButton1" value="Avanti"/>
                    </div>

                    <div id="registration2">

                        <div>
                            <label id="accountLabel"> C.A.P. Residenza*: </label>
                            <input id="capResidence" class="accountInput" type="text" name="CapResidenza">
                        </div>

                        <div>
                            <label id="accountLabel">  Numero di telefono: </label>
                            <input id="telephoneNumber" class="accountInput" type="text" name="Telefono">
                        </div>

                        <div>
                            <label id="accountLabel">Numero di cellulare: </label>
                            <input id="cellularNumber" class="accountInput" type="text" name="Cellulare">
                        </div>

                        <div>
                            <label id="accountLabel">  Indirizzo Fax: </label>
                            <input id="fax" class="accountInput" type="text" name="Fax">
                        </div>

                        <div>
                            <label id="accountLabel">Indirizzo email*:</label>
                            <input id="email" class="accountInput" type="text" name="Email">
                        </div>

                        <div>
                            <label id="accountLabel">  Comune di Domicilio*: </label>
                            <input id="municipalityDomicile" class="accountInput" type="text" name="ComuneDomicilio">
                        </div>

                        <div>
                            <label id="accountLabel">Provincia di Domicilio*:</label>
                            <input id="provinceDomicile" class="accountInput" type="text" name="ProvinciaDomicilio">
                        </div>

                        <div>
                            <label id="accountLabel">  Indirizzo Domicilio*: </label>
                            <input id="viaDomicile" class="accountInput" type="text" name="ViaDomicilio">
                        </div>

                        <input style="width: 200px; margin-left: 5%" type="button" name="notRegistrationButton2" id="notRegistrationButton2" value="Indietro"/>
                        <input style="width: 200px; margin-left: 2%" type="submit" name="registrationButton2" id="registrationButton2" value="Avanti"/>
                    </div>

                    <div id="registration3">

                        <div>
                            <label id="accountLabel">  C.A.P. Domicilio: </label>
                            <input id="capDomicile" class="accountInput" type="text" name="CapDomicilio">
                        </div>

                        <div>
                            <label id="accountLabel"> Titolo di Studio*: </label>
                            <input id="qualification" class="accountInput" type="text" name="TitoloStudio">                  
                        </div>

                        <div>
                            <label id="accountLabel">  Tipo di Account*: </label>
                            <%--<input id="typeAccount" class="accountInput" type="text" name="typeAccount" value="TipoAccount">--%>
                            <select id="typeAccount" name="tipo" onchange="verificaAccount()">
                                <OPTION value="Nothing" name="Scelta" selected> Scegli 
                                <OPTION value="Genitore" name="Genitore"> Genitore 
                                <OPTION value="Segreteria" name="DelegatoUfficio"> Segreteria 
                                <OPTION value="Delegato scienze della formazione" name="ScienzeFormazione"> Delegato Scienze della Formazione 
                                <OPTION value="Educatore" name="Educatore"> Educatore 
                                <OPTION value="Coordinatore Psicopedagogico" name="CoordinatorePsicopedagogico"> Coordinatore Psicopedagogico 
                                <OPTION value="Responsabile Scientifico" name="ResponsabileScientifico"> Responsabile Scentifico 
                                <OPTION value="Responsabile Asilo" name="ResponsabileAsilo"> Responsabile Asilo
                                <OPTION value="Delegato del Rettore" name="DelegatoDelRettore"> Delegato del Rettore
                                <OPTION value="Responsabile Mensa" name="ResponsabileMensa"> Responsabile Mensa
                            </select>
                        </div>

                        <div>
                            <label id="accountLabel"> Situazione Familiaria: </label>
                            <input id="familySituation" class="accountInput" type="text" name="SituazioneFamiliaria">
                        </div>

                        <div>
                            <label id="accountLabel">  Reddito*: </label>
                            <input id="income" class="accountInput" type="text" name="Reddito">
                        </div>

                        <div>
                            <label id="accountLabel2" style="display: none;"> Tipologia Genitore*: </label>
                            <%--<input id="typeParent" class="accountInput" type="text" name="TipoAccount" />--%> 
                            <select id="typeParent" name="tipo" onchange="verificaGenitore()" style="display: none">
                                <OPTION value="Nothing" name="Scelta" selected> Scegli 
                                <OPTION value="Studente" name="Studente"> Studente 
                                <OPTION value="Tecnico amministrativo" name="TecnicoAmministrativo"> Tecnico Amministrativo 
                                <OPTION value="Docente" name="Docente"> Docente 
                                <OPTION value="Contratto Tempo Determinato" name="ContrattoTempoDeterminato"> Contratto Tempo Determinato 
                                <OPTION value="Ricercatore" name="Ricercatore">Ricercatore 
                                <OPTION value="Dottorando" name="Dottorando"> Dottorando 
                            </select>
                        </div>

                        <div>
                            <label id="accountLabel3" style="display : none "> Scadenza Contratto*: </label>
                            <input id="contractExpirationDate" class="accountInput" type="text" name="ScadenzaContratto" style="display : none ">
                        </div>

                        <div>
                            <label id="accountLabel5" style="display : none ">  Facoltà*: </label>
                            <input id="faculty" class="accountInput" type="text" name="Facolta" style="display : none ">
                        </div>

                        <div>
                            <label id="accountLabel4" style="display : none "> Data di Immatricolazione*: </label>
                            <input id="registrationDate" class="accountInput" type="text" name="DataRegistrazione" style="display : none ">
                        </div>

                        <input style="width: 200px; margin-left: 5%" type="button" name="notRegistrationButton3" id="notRegistrationButton3" value="Indietro"/>
                        <input style="width: 200px; margin-left: 2%" type="submit" name="registrationButton3" id="registrationButton3" value="Completa Registrazione" />

                    </div>
                </div>
            </fieldset>


        </form>
        <%@include file="footer.jsp" %>
    </body>
</html>