<%-- 
    Document   : modifyRegistration
    Created on : 7-dic-2012, 23.11.51
    Author     : Pasquale
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--<c:if test="${sessionScope.user==null}">
    <c:redirect url="index.jsp" />
</c:if>--%>

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
        <script type="text/javascript" src="js/modifyAccountRegistration.js"></script>
        <title>Registrazione Account - Kids Project</title>
        <jsp:include page="/GetAccountParent" /> 
        <script type="text/javascript">
            $(document).ready(function() {
                initializeModifyRegistrationFields();
                
                update();
                $("#modifyDateOfBirth, #modifyContractExpirationDate, #modifyRegistrationDate").datepicker({dateFormat:'yy-mm-dd'});
            });
        </script>
    </head>
    <%@include file="header.jsp"%>
    <body id="bodyRegistration">


        <form id="modifyForm" class="cmxform"  action="" method="post">

            <fieldset id="modifyFieldSet">
                <div id="artefactsManagement">
                    <h1  style="font-weight: bold; font-size: 30pt"id="titleReg" align="center">Modifica Account</h1><br> <br>

                    <div id="modifyRegistration1">
                        <input id="id" class="accountInput" type="hidden" name="id" size="50%" value="${id}" readonly="true">
                        <input id="typeAccount2" class="accountInput" type="hidden" name="Nome" size="50%" value="${TipoAccount}" >
                        <input id="typeParent2" class="accountInput" type="hidden" name="Nome" size="50%" value="${TipoGenitore}" >

                        <div >
                            <label id="accountLabel">  Nome*: </label>
                            <input id="modifyAccountName" class="accountInput" type="text" name="Nome" size="50%" value="${Nome}" >
                        </div>

                        <div>
                            <label id="accountLabel"> Cognome*: </label>
                            <input id="modifyAccountSurname" class="accountInput"type="text" name="Cognome" value="${Cognome}" >                    
                        </div>

                        <div>
                            <label id="accountLabel"> Data di nascita*: </label>
                            <input id="modifyDateOfBirth" class="accountInput" type="text" name="DataNascita" value="${DataNascita}" >
                        </div>

                        <div>
                            <label id="accountLabel"> Comune di nascita*: </label>
                            <input id="modifyPlaceOfBirth" class="accountInput" type="text" name="ComuneNascita" value="${ComuneNascita}" >
                        </div>

                        <div>
                            <label id="accountLabel"> CodiceFiscale*: </label>
                            <input  id="modifyTaxCode" class="accountInput" type="text" name="CodiceFiscale"value="${CodiceFiscale}" >
                        </div>

                        <div>
                            <label id="accountLabel"> Cittadinanza*: </label>
                            <input  id="modifyCitizenship" class="accountInput" type="text" name="Cittadinanza"value="${Cittadinanza}" >
                        </div>

                        <div>
                            <label id="accountLabel"> Comune di residenza*: </label>
                            <input  id="modifyMunicipalityResidence" class="accountInput" type="text" name="ComuneResidenza" value="${ComuneResidenza}" >
                        </div>

                        <div>
                            <label id="accountLabel"> Provincia Residenza*: </label>
                            <input  id="modifyProvinceResidence" class="accountInput" type="text" name="ProvinciaResidenza" value="${ProvinciaResidenza}" >
                        </div>

                        <div>
                            <label id="accountLabel"> Indirizzo Residenza*: </label>
                            <input  id="modifyViaResidence" class="accountInput" type="text" name="IndirizzoResidenza" value="${ViaResidenza}" >
                        </div>

                        <input style="width: 200px; margin-left: 5%" type="button" name="notRegistrationButton1" id="notModifyButton1" value="Indietro"/>
                        <input style="width: 200px; margin-left: 2%" type="submit" name="registrationButton1" id="modifyButton1" value="Avanti"/>
                    </div>

                    <div id="modifyRegistration2">

                        <div>
                            <label id="accountLabel"> C.A.P. Residenza*: </label>
                            <input id="modifyCapResidence" class="accountInput" type="text" name="CapResidenza" value="${CapResidenza}" >
                        </div>

                        <div>
                            <label id="accountLabel">  Numero di telefono: </label>
                            <input id="modifyTelephoneNumber" class="accountInput" type="text" name="Telefono" value="${Telefono}" >
                        </div>

                        <div>
                            <label id="accountLabel">Numero di cellulare: </label>
                            <input id="modifyCellularNumber" class="accountInput" type="text" name="Cellulare" value="${Cellulare}" >
                        </div>

                        <div>
                            <label id="accountLabel">  Indirizzo Fax: </label>
                            <input id="modifyFax" class="accountInput" type="text" name="Fax" value="${Fax}" >
                        </div>

                        <div>
                            <label id="accountLabel">Indirizzo email*:</label>
                            <input id="modifyEmail" class="accountInput" type="text" name="Email" value="${Email}" >
                        </div>

                        <div>
                            <label id="accountLabel">  Comune di Domicilio*: </label>
                            <input id="modifyMunicipalityDomicile" class="accountInput" type="text" name="ComuneDomicilio" value="${ComuneDomicilio}" >
                        </div>

                        <div>
                            <label id="accountLabel">Provincia di Domicilio*:</label>
                            <input id="modifyProvinceDomicile" class="accountInput" type="text" name="ProvinciaDomicilio" value="${ProvinciaDomicilio}" >
                        </div>

                        <div>
                            <label id="accountLabel">  Indirizzo Domicilio*: </label>
                            <input id="modifyViaDomicile" class="accountInput" type="text" name="ViaDomicilio" value="${ViaDomicilio}" >
                        </div>

                        <input style="width: 200px; margin-left: 5%" type="button" name="notModifyButton2" id="notModifyButton2" value="Indietro"/>
                        <input style="width: 200px; margin-left: 2%" type="submit" name="modifyButton2" id="modifyButton2" value="Avanti"/>
                    </div>

                    <div id="modifyRegistration3">

                        <div>
                            <label id="accountLabel">  C.A.P. Domicilio: </label>
                            <input id="modifyCapDomicile" class="accountInput" type="text" name="CapDomicilio" value="${CapDomicilio}">
                        </div>

                        <div>
                            <label id="accountLabel"> Titolo di Studio*: </label>
                            <input id="modifyQualification" class="accountInput" type="text" name="TitoloStudio" value="${TitoloStudio}" >                  
                        </div>

                        <div>
                            <label id="accountLabel" style="display: none">  Tipo di Account*: </label>
                            <%--<input id="typeAccount" class="accountInput" type="text" name="typeAccount" value="TipoAccount">--%>
                            <select id="modifyTypeAccount" name="tipo" onchange="verificaAccount()" readonly="true"  style="visibility: hidden">
                                <OPTION value="Nothing" name="Scelta"> Scegli 
                                <OPTION value="Genitore" name="Genitore" selected> Genitore 
                                <OPTION value="Segreteria" name="DelegatoUfficio"> Segreteria 
                                <OPTION value="Delegato scienze della formazione" name="ScienzeFormazione"> Delegato Scienze della Formazione 
                                <OPTION value="Educatore" name="Educatore"> Educatore 
                                <OPTION value="Coordinatore Psicopedagogico" name="CoordinatorePsicopedagogico"> Coordinatore Psicopedagogico 
                                <OPTION value="Responsabile Scientifico" name="ResponsabileScientifico"> Responsabile Scientifico 
                                <OPTION value="Responsabile Asilo" name="ResponsabileAsilo"> Responsabile Asilo
                            </select>
                        </div>

                        <div>
                            <label id="accountLabel"> Situazione Familiaria: </label>
                            <input id="modifyFamilySituation" class="accountInput" type="text" name="SituazioneFamiliaria" value="${SituazioneFamiliare}" >
                        </div>

                        <div>
                            <label id="accountLabel">  Reddito*: </label>
                            <input id="modifyIncome" class="accountInput" type="text" name="Reddito" value="${Reddito}">
                        </div>

                        <div>

                            <label id="accountLabel2"> Tipologia Genitore*: </label> 


                            <select id="modifyTypeParent" name="tipo" onchange="verificaGenitore()">
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
                            <label id="accountLabel3" style="display: none"> Scadenza Contratto*: </label>
                            <input id="modifyContractExpirationDate" class="accountInput" type="text" name="ScadenzaContratto" value="${ScadenzaContratto}" style="display: none">
                        </div>

                        <div>
                            <label id="accountLabel5" style="display: none">  Facoltà*: </label>
                            <input id="modifyFaculty" class="accountInput" type="text" name="Facolta" value="${Facolta}"style="display: none" >
                        </div>

                        <div>
                            <label id="accountLabel4" style="display: none" > Data di Immatricolazione*: </label>
                            <input id="modifyRegistrationDate" class="accountInput" type="text" name="DataRegistrazione" value="${DataIscrizione}" style="display: none" >
                        </div>

                        <input style="width: 200px; margin-left: 5%" type="button" name="notModifyButton3" id="notModifyButton3" value="Indietro"/>
                        <input style="width: 200px; margin-left: 2%" type="submit" name="modifyButton3" id="modifyButton3" value="Conferma Modifiche" />
                    </div>
                </div>
            </fieldset>


        </form>
        <%@include file="footer.jsp" %>
    </body>
</html>
