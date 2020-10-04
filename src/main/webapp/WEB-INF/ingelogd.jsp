<%-- 
    Document   : inloggen.jsp
    Created on : 10-mei-2017, 11:59:34
    Author     : zenodotus
--%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../headers/header.jsp" />
<jsp:include page="../headers/menu.jsp" />
<section id="hoofdinhoud">
			<article id="formulier" align="center">
				
                                
                            <h1 align="center">Welkom <s:property value="gebruikersnaam" /></h1>
                            <s:if test="!actieveUitleningen.isEmpty()">
                            <div>
                                Uw huidige Uitleningen: 
                                <table border="0" width="100%" align="center">
                                    <thead>
                                        <tr>
                                            <th width="70%">Product</th>
                                            <th>inleverdatum</th>
                                        </tr>
                                    </thead>
        
                                    <tbody>
                                        <s:iterator value="actieveUitleningen" var="uitlening">
                                            <tr>
                                                <td><s:property value="%{#uitlening.spel.naam}" /></td>
                                                <td><s:date format="d MMMM YYYY" name="%{#uitlening.terugbrengdatum}" /></td>
                                            </tr>
                                        </s:iterator>
                                    </tbody>
                                </table>
                            </div>
                            <br>
                            </s:if>
                            <s:if test="!reserveringen.isEmpty()">
                            <div>
                                Uw reserveringen <br>
                                 <table border="0" width="100%" align="center">
                                    <thead>
                                        <tr>
                                            <th width="40%">Product</th>
                                            <th width="30%">reservatiedatum</th>
                                            <th width="40%">Binnen</th>
                                        </tr>
                                    </thead>
        
                                    <tbody>
                                        <s:iterator value="reserveringen" var="reservering">
                                            <tr>
                                                <td><s:property value="%{#reservering.product.naam}" /></td>
                                                <td><s:date format="d MMMM YYYY" name="%{#reservering.reservatieDatum}" /></td>
                                                <s:if test="#reservering.binnen != null">
                                                    <td style="color:green"><s:date format="d MMMM YYYY" name="%{#reservering.binnen}" /></td>
                                                </s:if>
                                                <s:else>
                                                    <td align="center"><img src="<s:url value="images/cross.png" />" width="40px" height="40px" /></td>
                                                </s:else>
                                            </tr>
                                            
                                        </s:iterator>
                                    </tbody>
                                </table>
                            </div>
                            </s:if>
      </article></section>
    
  </body>
</html>
