<%-- 
    Document   : zoeken
    Created on : 24-jun-2017, 0:48:18
    Author     : zenodotus
--%>

<%@page import="java.util.ArrayList, org.apache.lucene.document.Document"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<jsp:include page="headers/header.jsp" />
<jsp:include page="headers/menu.jsp" />

<style>
    #hoofdinhoud {
        display: block;
    }
    .displayListView {
        display: inline-block;
        vertical-align: middle !important;
        margin-top:5px;
        margin-bottom:5px;
        padding-left:10px;
        float: none;
    }

    .displayRow {
        -moz-border-radius:10px; 
        -webkit-border-radius: 10px; 
        -khtml-border-radius: 10px; 
        border-radius: 10px;
        height: 50px;
        
        color: gray;
        vertical-align: middle !important;
    }
    .displayRow:hover, lvLevel:hover {
        background-color:#ebd621;

    }

</style>
<section id="hoofdinhoud"> 
    <h2>Vond <s:property value="lijst.size()" /> hits </h2>
    <s:iterator var="result" value="lijst">
        <a href="accountReserveren?id=${result.get('Nummer')}">
        <div class="displayRow">
        <!--<div class="lvLevel" width="100%" style="vertical-align:middle">-->
            

            <div class="displayListView"> <s:property value="#result.get('Naam')" /> <s:property value="#result.get('Beschrijving')" /></div>
            

            
        <!--</div>-->
        </div>
                    </a>
        <div><hr></div>
    
</s:iterator>

</section>
</div>
</body>
</html>
