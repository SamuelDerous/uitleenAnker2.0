<%-- 
    Document   : overzichtPagina
    Created on : Dec 14, 2017, 9:07:13 AM
    Author     : zenodotus
--%>

<% if ((session.getAttribute("gebruikersnaam") != null && !session.getAttribute("gebruikersnaam").equals("")) && session.getAttribute("soort").equals("gebruiker")) { %>
<%@page import="databank.TblInventarisatie"%>
<%@page import="org.hibernate.Session"%>
<%@page import="org.hibernate.SessionFactory"%>
<%@page import="org.hibernate.Query"%>
<%@page import="databank.adapter.HibernateFactory"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../headers/header.jsp" />
<jsp:include page="../headers/menu.jsp" />
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<section id="hoofdinhoud">
    <article id="Overzicht" align="center">
        <h2 align="center">Overzicht <s:if test="sort.equals(\"yearly\")">${jaar}</s:if><s:elseif test="sort.equals(\"academicly\")">${jaar} - ${jaar + 1}</s:elseif></h2>

            <table id="overzichtstable" border="1" width="100%">
            <jsp:useBean id="producten" class="databank.dao.ProductDao" scope="request" />
            <jsp:useBean id="kwartaalOptelling" class="databank.dao.UitleenDao" />
            <s:if test="sort.equals(\"yearly\")">
                <thead><tr><th>Product</th><th>Jaarlijks</th><th>Jan</th><th>Feb</th><th>Maart</th><th>April</th><th>Mei</th><th>Juni</th><th>Juli</th><th>Aug</th><th>Sep</th><th>Okt</th><th>Nov</th><th>Dec</th></tr></thead>
            </s:if>
            <s:elseif test="sort.equals(\"academicly\")">
                    <thead><tr><th>Product</th><th>dit academiejaar</th><th>Sep</th><th>Okt</th><th>Nov</th><th>Dec</th><th>Jan</th><th>Feb</th><th>Maart</th><th>April</th><th>Mei</th><th>Juni</th><th>Juli</th><th>Aug</th></tr></thead>
            </s:elseif>
                <tbody>
                    <s:if test="productId == 0">
                        <c:forEach var="producties" items="${producten.alleProducten}">
                            <c:set var="productie" value="${producties.naam}"/>
                            <tr><td width="150px">${productie}</td>
                                <s:if test="sort.equals(\"yearly\")">
                                    <c:set var="optelling" value="${kwartaalOptelling.getOverzicht(producties, jaar)}" />
                            </s:if>
                            <s:elseif test="sort.equals(\"academicly\")">
                                <c:set var="optelling" value="${kwartaalOptelling.getAcademicOverzicht(producties, jaar)}" />
                            </s:elseif>

                            <td width="50px">${optelling}</td>
                            <s:if test="sort.equals(\"yearly\")">
                                <c:forEach var="teller" begin="1" end="12">
                                    <c:set var="maandelijks" value="${kwartaalOptelling.getOverzicht(producties, jaar, teller)}" />
                                    <td width="30px">${maandelijks}</td>
                                </c:forEach>
                            </s:if>
                            <s:elseif test="sort.equals(\"academicly\")">
                                <c:forEach var="teller" begin="9" end="12">
                                    <c:set var="maandelijks" value="${kwartaalOptelling.getOverzicht(producties, jaar, teller)}" />
                                    <td width="30px">${maandelijks}</td>
                                </c:forEach>
                                <c:forEach var="teller" begin="1" end="8">
                                    <c:set var="maandelijks" value="${kwartaalOptelling.getOverzicht(producties, (jaar + 1), teller)}" />
                                    <td width="30px">${maandelijks}</td>
                                </c:forEach>
                            </s:elseif>
                        </tr>
                    </c:forEach>
                </s:if>

                <s:else>
                    <c:set var="indProduct" value="${producten.getProductById(productId)}" />
                    <tr><td>${indProduct.naam}</td>
                        <s:if test="sort.equals(\"yearly\")">
                            <c:set var="productJaar" value="${kwartaalOptelling.getOverzicht(indProduct, jaar)}" />
                            <td>${productJaar}</td>

                            <c:forEach var="teller" begin="1" end="12">
                                <c:set var="productMaand" value="${kwartaalOptelling.getOverzicht(indProduct, jaar, teller)}" />
                                <td>${productMaand}</td>
                            </c:forEach>
                        </s:if>
                        <s:elseif test="sort.equals(\"academicly\")">
                            <c:set var="productJaar" value="${kwartaalOptelling.getAcademicOverzicht(indProduct, jaar)}" />
                            <td>${productJaar}</td>
                            <c:forEach var="teller" begin="9" end="12">
                                <c:set var="maandelijks" value="${kwartaalOptelling.getOverzicht(indProduct, jaar, teller)}" />
                                <td width="10px">${maandelijks}</td>
                            </c:forEach>
                            <c:forEach var="teller" begin="1" end="8">
                                <c:set var="maandelijks" value="${kwartaalOptelling.getOverzicht(intProduct, (jaar + 1), teller)}" />
                                <td width="10px">${maandelijks}</td>
                            </c:forEach>
                        </s:elseif>

                    </tr>
                </s:else>   

            </tbody>

        </table>

    </article>
</section>
</div>
<script type="text/javascript">


    $(document).ready(function () {
        $('#overzichtstable').DataTable();
    });

            $('#slctKwartaal').change(function () {
        if (this.value == "maandelijks") {
            $("#rowMonth").show();
        } else {
            $("#rowMonth").hide();
        }
    });

</script>
</body>
</html><% } else {
            response.sendRedirect("/UitleenAnker/faces/inloggen.jsp");
        }
    %>
