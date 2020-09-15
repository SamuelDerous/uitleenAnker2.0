<%-- 
    Document   : registreren
    Created on : 10-mei-2017, 11:59:46
    Author     : zenodotus
--%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="headers/header.jsp" />
<script type="text/javascript" src="js/validation.js"></script>

<section id="hoofdinhoud">
			<article id="formulier" align="center">
				<h2 align="center">Registreren</h2>
				<s:form action="registreren" name="registratie">
					<table border="0" align="center" width="25%">
						<tr><td colspan="2"><div id="foutmelding" name="foutmelding">
                                                            <s:actionerror escape="false"/>
                                    
                                                    </div></td></tr>
                                                <tr><td><s:textfield cssClass="invullen" name="gebruikersnaam" id="txtGebruikersnaam" label="gebruikersnaam" /></td></tr>
                                                <tr><s:password cssClass="invullen" name="wachtwoord" id="txtWachtwoord" label="wachtwoord" /></td></tr>
                                                <tr><td><s:password cssClass="invullen" name="bevestig" id="txtBevestig" label="Bevestig wachtwoord" /></td></tr>
						<tr><td>&nbsp;</td><td>&nbsp;</td></tr>
                                                <tr><td><s:textfield cssClass="invullen" name="voornaam" id="txtVoornaam" label="Voornaam" /></td></tr>
                                                <tr><td><s:textfield cssClass="invullen" name="naam" id="txtNaam" label="Naam" /></td></tr>
						<tr><s:textarea id="txtAdres" name="adres" cssClass="invullen" rows="3" label="Adres"  /></td></tr>
						<tr><td><s:textfield cssClass="invullen" name="telefoon" id="txtTelefoon" label="Telefoon" /></td></tr>
                                                <tr><td><s:textfield cssClass="invullen" name="email" id="txtEmail" label="E-mail"/></td></tr>
						<tr><td>&nbsp;</td><td>&nbsp;</td></tr>
						<tr><td colspan="2" align="center"><s:submit value="Registreer" id="txtSubmit" theme="simple" /><s:reset value="Wissen" theme="simple" /></td></tr>
						<tr><td>&nbsp;</td><td>&nbsp;</td></tr>						
					</table>				
				</s:form>			
			</article>      
      </section>
    </div>
  </body>
</html>
