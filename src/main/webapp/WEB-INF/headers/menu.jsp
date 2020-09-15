
<%@taglib prefix="s" uri="/struts-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<nav id="menu" class="navbar navbar-expand-lg">
 
  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    
      <ul class="navbar-nav mr-auto">
      <s:if test="(#session.gebruikersnaam != null && !#session.gebruikersnaam.equals(\"\")) && (#session.soort != null && #session.soort.equals(\"gebruiker\"))">
  
          <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Uitlenen
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="uitleningToevoegen">Toevoegen</a>
          <a class="dropdown-item" href="uitleningenUsers">Uitlenen</a>
          <a class="dropdown-item" href="reservatiesUsers">Reservaties</a>
        </div>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          gebruikers
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="gebruikersUsers">Aanpassen/Verwijderen</a>
          <a class="dropdown-item" href="gebToevoegenUsers">Toevoegen</a>
        </div>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Productie
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="productenUsers">Aanpassen/Verwijderen</a>
          <a class="dropdown-item" href="productControle">Te Controleren</a>
          <a class="dropdown-item" href="productToevoegenUsers">Toevoegen</a>
          
          
        </div>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Inventarisatie
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="inventarisUsers">Bekijken/Verwijderen</a>
          <a class="dropdown-item" href="inventToevoegenUsers">Toevoegen</a>
          
        </div>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Overzichten
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="overzichtUitleningen">Periodiek Overzicht</a>
          <a class="dropdown-item disabled" href="javascript:void(0)">Uitleningen</a>
        </div>
      </li>
    </s:if>
      <s:elseif test="#session.gebruikersnaam != null && !#session.gebruikersnaam.equals(\"\")">
          <li class="nav-item">
        <a class="nav-link" href="uitleningenUitlener?action=uitlening" id="uitlening" role="button">
          Uitleningen
        </a>
        
      </li>
      <li class="nav-item">
        <a class="nav-link" href="uitleningenUitlener?action=reservering" id="reservaring" role="button">
          Reserveringen
        </a>
        
      </li>
      <li class="nav-item">
          <a class="nav-link" href="gebruikersGen?gebruikersnaam=<s:property value="#session.gebruikersnaam" />" id="account" role="button">
          Account
        </a>
        
      
    </s:elseif>
      
    </ul>
    <div align="right">
        <s:if test="#session.gebruikersnaam != null && !#session.gebruikersnaam.equals(\"\")">
            <ul class="navbar-nav text-right mr-auto"><li class="nav-item">Welkom, <s:property value="#session.gebruikersnaam" /></li>
                    <li class="nav-item"><a href="${pageContext.request.contextPath}/uitloggen.jsp">Uitloggen</a></li>
                </ul>
        </s:if>
        <s:else>
          <ul class="navbar-nav text-right mr-auto">
            <li class="nav-item"><a href="${pageContext.request.contextPath}/inloggen.jsp">Inloggen</a></li>
            <li class="nav-item"><a href="${pageContext.request.contextPath}/registreren.jsp">Registreren</a></li>
          </ul>
        </s:else>
        </div>
        
  </div>
</nav>
        
        <div id="dataMenu" style="padding-top: 2em; padding-bottom: 2em;">
        	<s:form cssClass="form-inline" method="post" action="zoeken">
				<input type="text" class="form-control mr-sm-2" id="txtZoeken" name="search" placeholder="Geef een zoekterm in" size="25" />
				<s:submit cssClass="btn btn-outline-success my-2 my-sm-0" value="Zoeken" />        	
        	</s:form>
        	<hr />
        		
      </div>
   
     
