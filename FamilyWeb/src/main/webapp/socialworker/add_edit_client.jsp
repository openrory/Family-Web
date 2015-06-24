<!DOCTYPE html>
<html>
<head>
	<title>Client toevoegen/bijwerken</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="mobile-web-app-cable" content="yes">
    <meta charset="utf-8">
    <script src="/FamilyWeb/bower_components/webcomponentsjs/webcomponents.min.js"></script>
    <link rel="import" href="/FamilyWeb/elements.html">
	<link rel="import" href="/FamilyWeb/custom_elements/responsive-menu-socialworker.html">
	<link rel="import" href="/FamilyWeb/custom_elements/options-menu.html">
	<link rel="import" href="/FamilyWeb/custom_elements/paper-form-element-decorators/paper-submit-button-decorator.html">
	<link rel="stylesheet" href="/FamilyWeb/styles/add_edit_client.css">
	<script type="text/javascript" src="/FamilyWeb/javascripts/validate_add_edit_client.js"></script>

</head>
<body fullbleed layout vertical>
    <core-drawer-panel responsivewidth="1400px">
        <core-header-panel drawer>
            <core-toolbar>
				<paper-icon-button class="toolbarButton" core-drawer-toggle icon="close"></paper-icon-button>
                <div class="menuTitle" flex>Menu</div>
            </core-toolbar>
         <responsive-menu-socialworker current="1"></responsive-menu-socialworker>
        </core-header-panel>
        <core-header-panel main>
            <core-toolbar>
			<paper-icon-button class="toolbarButton" core-drawer-toggle icon="menu"></paper-icon-button>
            <div id="title" flex>Client toevoegen/bijwerken</div>
			<options-menu></options-menu>
            </core-toolbar>
            <div id="content" >
        	<% if(!(request.getAttribute("message") == null)){%>
        	<message-window-${messageType} message="${message}"></message-window-${messageType}>
        	<% } %>
			<paper-shadow z="3" animated="true">
                <form id="add_edit_client_form" onsubmit="return validateForm();" action="/FamilyWeb/ClientServlet.do" method="post">
                    <p>
                        Lorem Ipsum is slechts een proeftekst uit het drukkerij- en zetterijwezen. 
                        Lorem Ipsum is de standaard proeftekst in deze bedrijfstak sinds de 16e eeuw, 
                        toen een onbekende drukker een zethaak met letters nam en ze door elkaar husselde om een font-catalogus te maken. 
                        Het heeft niet alleen vijf eeuwen overleefd
                    </p>
                   <% if(request.getAttribute("client") == null){ %>
                    <input type="hidden" name="option" value="create">
                   <% } else{ %>
                    <input type="hidden" name="option" value="update">
                    <input type="hidden" name="client_id" value="${client.client_id}">
                   <% }%>
					<span id="fileidWarning" class="true">Geen personeelnr ingevuld</span>
                    <div class="information">
                        <label>Dossiernummer :</label>
                            <input id="fileid" type="text" placeholder="Personeelnr" value="${client.fileNumber}"/>
                    </div>
					<span id="forenameWarning" class="true">Geen voornaam ingevuld</span>
                    <div class="information">
                        <label>Voornaam :</label>
                        <input id="forename" name="forename" type="text" placeholder="Voornaam" value="${client.forename}" />
                    </div>
					<span id="surnameWarning" class="true">Geen achternaam ingevuld</span>
                    <div class="information">
                        <label>Achternaam :</label>
                        <input id="surname" name="surname" type="text" placeholder="Achternaam" value="${client.surname}"/>
                    </div>
					<span id="dateofbirthWarning" class="true">Geen geboortedatum ingevuld</span>
					<div class="information">
                        <label>Geboortedatum :</label>
                        <input id="dateofbirth" name="dateofbirth" type="date" placeholder="DD-MM-JJJJ" value="${client.dateofBirth}" />
                    </div>
					<span id="nationalityWarning" class="true">Geen nationaliteit ingevuld</span>
					<div class="information">
                        <label>Nationaliteit :</label>
                        <input id="nationality" name="nationality" type="text" placeholder="Nationaliteit" value="${client.nationality}"/>
                    </div>
					<span id="streetWarning" class="true">Geen straatnaam ingevuld</span>
                    <div class="information">
                        <label>Straat :</label>
                        <input id="street" name="street" type="text" placeholder="Straat" value="${client.street}"/>
                    </div>
					<span id="streetnumberWarning" class="true">Geen huisnummer ingevuld</span>
                    <div class="information_short">
                        <label>Huisnummer :</label>
                        <input id="streetnumber" name="streetnumber" type="text" placeholder="Huisnr" value="${client.houseNumber}" />
                    </div>
					<span id="postcodeWarning" class="true">Geen postcode ingevuld</span>
                    <div class="information_short">
                        <label>Postcode :</label>
                        <input id="postcode" name="postcode" type="text" placeholder="Postcode" value="${client.postcode}"/>
                    </div>
					<span id="cityWarning" class="true">Geen woonplaats ingevuld</span>
                    <div class="information">
                        <label>Woonplaats :</label>
                        <input id="city" name="city" type="text" placeholder="Woonplaats" value="${client.city}"/>
                    </div>
					<span id="phonenumberWarning" class="true">Geen telefoonnummer ingevuld ingevuld</span>
                    <div class="information">
                        <label>Telefoonnummer vast :</label>
                        <input id="phonenumber" name="phonenumber" type="tel" placeholder="Telefoonnummer" value="${client.telephoneNumber}"/>
                    </div>
					<span id="mobileWarning" class="true">Geen mobiel nummer ingevuld</span>
					<div class="information">
                        <label>Mobiel nummer :</label>
                        <input id="mobile" name="mobile" type="tel" placeholder="Mobiel nummer" value="${client.mobilePhoneNumber}"/>
                    </div>
					<span id="emailWarning" class="true">Geen email ingevuld</span>
                    <div class="information">
                        <label>E-mail :</label>
                        <input id="email" name="email" type="email" placeholder="E-mail" value="${client.email}"/>
                    </div>
					<span id="email_confirmationWarning" class="true">Geen 2e email ingevuld</span>
					<div class="information">
                        <label>E-mail bevestiging :</label>
                        <input id="email_confirmation" name="email_confirmation" type="email" placeholder="E-mail bevestiging" value="${client.email}"/>
                    </div>
                    
                   <% if(request.getAttribute("client") == null){ %>
                    <div id="submit_button_container">
                    <input class="submit_button" name="op" type="submit" value="Aanmaken">
                    </div>
                   <% } else{ %>
                    <div id="submit_button_container">
                    <input class="submit_button" name="op" type="submit" value="Opslaan">
                    </div>
                   <% }%>
                </form>
				</paper-shadow>
            </div>
        </core-header-panel>
    </core-drawer-panel>
</body>
</html>
