<!DOCTYPE html>
<html>
<head>
	<title>Gezinslid toevoegen/bijwerken</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="mobile-web-app-cable" content="yes">
    <meta charset="utf-8">
    <script src="/FamilyWeb/bower_components/webcomponentsjs/webcomponents.min.js"></script>
    <link rel="import" href="/FamilyWeb/elements.html">
	<link rel="import" href="/FamilyWeb/custom_elements/responsive-menu-administrator.html">
	<link rel="import" href="/FamilyWeb/custom_elements/options-menu.html">
	<link rel="import" href="/FamilyWeb/custom_elements/paper-form-element-decorators/paper-submit-button-decorator.html">
	<link rel="stylesheet" href="/FamilyWeb/styles/add_edit_family_member.css">
	<script type="text/javascript" src="/FamilyWeb/javascripts/validate_add_edit_family_member.js"></script>
</head>
<body fullbleed layout vertical>
    <core-drawer-panel responsivewidth="1400px">
        <core-header-panel drawer>
            <core-toolbar>
				<core-icon-button core-drawer-toggle icon="close"></core-icon-button>
                <div flex>Menu</div>
            </core-toolbar>
            <responsive-menu-administrator current="3"></responsive-menu-administrator>
        </core-header-panel>
        <core-header-panel main>
            <core-toolbar>
			<core-icon-button core-drawer-toggle icon="menu"></core-icon-button>
            <div flex>Gezinslid toevoegen/bijwerken</div>
			<options-menu></options-menu>
            </core-toolbar>
            <div id="content" >
        	<% if(!(request.getAttribute("message") == null)){%>
        	<message-window-${messageType} message="${message}"></message-window-${messageType}>
        	<% } %>
			<paper-shadow z="3" animated="true" id="add_edit_client_form">
                <form id="add_form" onsubmit="return validateForm();" action="/FamilyWeb/FamilyMemberServlet.do" method="post">
                    <p>
                        Lorem Ipsum is slechts een proeftekst uit het drukkerij- en zetterijwezen. 
                        Lorem Ipsum is de standaard proeftekst in deze bedrijfstak sinds de 16e eeuw, 
                        toen een onbekende drukker een zethaak met letters nam en ze door elkaar husselde om een font-catalogus te maken. 
                        Het heeft niet alleen vijf eeuwen overleefd
                    </p>
                   <% if(request.getAttribute("familymember") == null){ %>
                    <input type="hidden" name="option" value="create">
                   <% } else{ %>
                    <input type="hidden" name="option" value="update">
                    <input type="hidden" name=familymemberID value="${familymember.familymember_id}">
                   <% }%> 
					<span id="forenameWarning" class="true">Geen voornaam ingevuld</span>
                    <div class="information">
                        <label>Voornaam :</label>
                        <input id="forename" name="forename" type="text" placeholder="Voornaam" value="${familymember.forename}" />
                    </div>
					<span id="surnameWarning" class="true">Geen achternaam ingevuld</span>
                    <div class="information">
                        <label>Achternaam :</label>
                        <input id="surname" name="surname" type="text" placeholder="Achternaam" value="${familymember.surname}"/>
                    </div>
					<span id="dateofbirthWarning" class="true">Geen geboortedatum ingevuld</span>
					<div class="information">
                        <label>Geboortedatum :</label>
                        <input id="dateofbirth" name="dateofbirth" type="date" placeholder="DD-MM-JJJJ" value="${familymember.dateofbirth}" />
                    </div>
					<span id="nationalityWarning" class="true">Geen nationaliteit ingevuld</span>
					<div class="information">
                        <label>Nationaliteit :</label>
                        <input id="nationality" name="nationality" type="text" placeholder="Nationaliteit" value="${familymember.nationality}"/>
                    </div>
					<span id="streetWarning" class="true">Geen straatnaam ingevuld</span>
                    <div class="information">
                        <label>Straat :</label>
                        <input id="street" name="street" type="text" placeholder="Straat" value="${familymember.street}"/>
                    </div>
					<span id="streetnumberWarning" class="true">Geen huisnummer ingevuld</span>
                    <div class="information_short">
                        <label>Huisnummer :</label>
                        <input id="streetnumber" name="streetnumber" type="text" placeholder="Huisnr" value="${familymember.streetnumber}" />
                    </div>
					<span id="postcodeWarning" class="true">Geen postcode ingevuld</span>
                    <div class="information_short">
                        <label>Postcode :</label>
                        <input id="postcode" name="postcode" type="text" placeholder="Postcode" value="${familymember.postcode}"/>
                    </div>
					<span id="cityWarning" class="true">Geen woonplaats ingevuld</span>
                    <div class="information">
                        <label>Woonplaats :</label>
                        <input id="city" name="city" type="text" placeholder="Woonplaats" value="${familymember.city}"/>
                    </div>
					<span id="phonenumberWarning" class="true">Geen telefoonnummer ingevuld ingevuld</span>
                    <div class="information">
                        <label>Telefoonnummer vast :</label>
                        <input id="phonenumber" name="phonenumber" type="tel" placeholder="Telefoonnummer" value="${familymember.phonenumber}"/>
                    </div>
					<span id="mobileWarning" class="true">Geen mobiel nummer ingevuld</span>
					<div class="information">
                        <label>Mobiel nummer :</label>
                        <input id="mobile" name="mobile" type="tel" placeholder="Mobiel nummer" value="${familymember.mobile}"/>
                    </div>
					<span id="emailWarning" class="true">Geen email ingevuld</span>
                    <div class="information">
                        <label>E-mail :</label>
                        <input id="email" name="email" type="email" placeholder="E-mail" value="${familymember.email}"/>
                    </div>
                    
                    <paper-submit-button-decorator id="button">
                   <% if(request.getAttribute("familymember") == null){ %>
                    <button name="op" value="Aanmaken" type="submit">Aanmaken</button> Aanmaken
                   <% } else{ %>
                    <button name="op" value="Opslaan" type="submit">Opslaan</button> Opslaan
                   <% }%>
                    </paper-submit-button-decorator>
                </form>
				</paper-shadow>
            </div>
        </core-header-panel>
    </core-drawer-panel>
</body>
</html>
