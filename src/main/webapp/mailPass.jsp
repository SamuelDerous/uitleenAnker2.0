<%-- 
    Document   : inloggen.jsp
    Created on : 10-mei-2017, 11:59:34
    Author     : zenodotus
--%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="headers/header.jsp" />
<jsp:include page="headers/menu.jsp" />

<section id="hoofdinhoud">
			<article id="formulier" align="center">
				<h2 align="center">Wachtwoord</h2>
                                
				<s:form action="mailPass">
                                    <table border="0" width="25%" align="center">
                                        <tr align="center">   <td colspan="2">     <div id="foutmelding" name="foutmelding">
                                                                    <s:actionerror />
                                                                    <s:actionmessage />
                                                </div></td></tr> 
                                                <tr align="center"><td colspan="2">
                                                        <div id="uitleg">Geef je e-mailadres op die je gebruikt hebt om je te registreren en je eventuele gebruikersnaam als je deze nog weet.</div>
                                                    </td></tr>
                                        <tr><td><s:textfield name="gebruikersnaam" label="gebruikersnaam" placeholder="Geef uw gebruikersnaam op"/></td></tr>
                                        <tr><td><s:textfield type="email" name="email" label="e-mail"/></tr></td>
                                    <tr><td>&nbsp;</td></tr>	<br />						
                                    <tr><td colspan="2"><s:submit value="Wachtwoord versturen" id="txtSubmit" theme="simple" /> <s:reset value="Wissen" theme="simple" /></td><tr>
                                        <tr><td>&nbsp;</td></tr>					
                                    </table>
				</s:form>			
			</article>      
      </section>
    </div>
  </body>
</html>
