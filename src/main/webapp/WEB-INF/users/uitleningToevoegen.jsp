<%-- 
    Document   : gebAanpassen
    Created on : 20-mei-2017, 17:36:35
    Author     : zenodotus
--%>
<%@page import="databank.TblPersoon"%>
<% if ((session.getAttribute("gebruikersnaam") != null && !session.getAttribute("gebruikersnaam").equals("")) && session.getAttribute("soort").equals("gebruiker")) { %>
<%@page import="databank.TblBeschrijving"%>
<%@page import="databank.TblProduct"%>
<%@page import="org.hibernate.Session"%>
<%@page import="org.hibernate.SessionFactory"%>
<%@page import="org.hibernate.Query"%>
<%@page import="databank.adapter.HibernateFactory"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../headers/header.jsp" />
<jsp:include page="../headers/menu.jsp" />
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<section id="hoofdinhoud">
    <article id="formulier" align="center">
        <h2 align="center">Toevoegen</h2>
        <s:form method="post" action="toevoegenUitlening" name="Toevoegen">
            <table id="products" border="0" align="center" width="25%">
                <tr><td colspan="2"><div id="foutmelding" name="foutmelding">
                            <s:actionerror escape="false" />

                        </div></td></tr>
                <tr><td><input type="hidden" name="action" value="toevoegen" />
                        Gebruikersnaam<span id="vereist">*</span></td>
                    <td><input type="text" class="invullen" autocomplete="off" name="gebruikersnaam" id="txtGebruikersnaam" list="personen"/>
                        <datalist id="personen">
                            <jsp:useBean id="gebruikers" class="databank.dao.PersoonDao" />
                            <c:forEach var="gebruiker" items="${gebruikers.alleGebruikers}">
                                <option value="${gebruiker.gebruikersnaam}" />
                            </c:forEach>
                        </datalist></td></tr>
                <tr><td>Product ID<span id="vereist">*</span> </td>
                    <td><input type="text" class="invullen" autocomplete="off" name="productId[0]" id="txtProductId" list="producten"/>
                        <datalist id="producten">
                            <jsp:useBean id="productie" class="databank.dao.ProductDao" />
                            <c:forEach var="product" items="${productie.alleProducten}">
                                <option value="${product.id}">${product.naam}</option>
                            </c:forEach>
                        </datalist>
                    </td>
                    <td><a href="javascript:void(0)" id="addObject"><img src="<s:url namespace="/" value="/images/add.png" />" /></a></td>
                <td><a href="javascript:void(0)" id="subtrObject"><img src="<s:url namespace="/" value="/images/minus.png" />" /></a></td></tr>
        <tr><td>Aantal: <span id="vereist">*</span> </td><td><input type="text" class="invullen" name="aantal[0]" id="txtAantal" /></td></tr></table>                                                
        <s:hidden name="productions" id="productions" /> 
    <br><br>
    <div align="center"><input type="submit" value="Toevoegen" id="txtSubmit" /><input type="reset" value="Wissen" /></div>
    <br><br>
    </table>
</s:form>
</article>
</section>
</div>
</body>
<script type="text/javascript">
    $(function () {
        var aantal = 0;
        if (aantal <= 0) {
            $("#subtrObject").hide();
        };
        $("#txtSubmit").on("click", function () {
            //aantallen = 0;
            var tmp = "";

            $('input[name^=productId]').each(function (key, value) {
                //alert(key);
                //alert(value);
                //alert($('input[name="aantal[' + key + ']"]').val());
                tmp = tmp + this.value + ":" + ($('input[name="aantal[' + key + ']"]').val() === "" ? "1" : $('input[name="aantal[' + key + ']"]').val()) + ";";
                //aantallen++;
            });
            $("#productions").val(tmp);
            //alert(tmp);
        });

        $("#addObject").on("click", function () {
            aantal++;
            
            $("#products tr:last").after('<tr><td colspan="2">&nbsp;</td><tr><tr><td>Product ID<span id="vereist">*</span> </td>' +
                    '<td><input type="text" class="invullen" autocomplete="off" name="productId[' + aantal + ']" id="txtProductId" list="producten"/>' +
                    '<datalist id="producten">' +
                    '<c:forEach var="product" items="${productie.alleProducten}">' +
                    '<option value="${product.id}">${product.naam}</option>' +
                    '</c:forEach>' +
                    '</datalist></td>' +
                    '</tr>' +
                    '<tr><td>Aantal: <span id="vereist">*</span> </td><td><input type="text" class="invullen" name="aantal[' + aantal + ']" id="txtAantal" /></td></tr>');
            //aantal++;
            if (aantal > 0) {
                $("#subtrObject").show();
            }
            //alert(aantal);
        });

        $("#subtrObject").on("click", function () {
            if (aantal > 0) {
                //alert(aantal);
                $("#products tr:last").remove();
                $("#products tr:last").remove();
                $("#products tr:last").remove();
                $("#products tr:last").remove();
                aantal--;
                if(aantal <= 0) {
                    $("#subtrObject").hide();
                }
            } else {
                $("#subtrObject").hide();
            }
        });
    });
    </script>
</html>
<%} else {
        response.sendRedirect("/UitleenAnker/faces/inloggen.jsp");
    }%>