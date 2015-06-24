<!DOCTYPE html>
<html>
<head>
<title>Overzicht cliënten</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="mobile-web-app-cable" content="yes">
<meta charset="utf-8">
<script src="../bower_components/webcomponentsjs/webcomponents.min.js"></script>
<link rel="import" href="../elements.html">
<link rel="import"
	href="../custom_elements/responsive-menu-administrator.html">
<link rel="import" href="../custom_elements/options-menu.html">
<link rel="import" href="../custom_elements/Client-table-administrator.html">
<link rel="stylesheet" href="../styles/client_overview.css">

</head>
<body fullbleed layout vertical>
	<core-drawer-panel responsivewidth="1400px"> <core-header-panel
		drawer> <core-toolbar> <core-icon-button
		core-drawer-toggle icon="close"></core-icon-button>
	<div flex>Menu</div>
	</core-toolbar> <responsive-menu-socialworker current="1"></responsive-menu-socialworker>
	</core-header-panel> <core-header-panel main> <core-toolbar>
	<core-icon-button core-drawer-toggle icon="menu"></core-icon-button>
	<div flex>Overzicht cliënten</div>
	<options-menu></options-menu> </core-toolbar>
	<div class="content" fit layout vertical>
		<%
			if (!(request.getAttribute("message") == null)) {
		%>
		<message-window- ${messageType} message="${message}"></message-window-${messageType}>
		<%
			}
		%>		
		<form id="familyform" action="/FamilyWeb/ClientServlet.do"
			method="post">
			<!--                	Hier wordt de id van de familie neergezet voor de servlets -->
			<input id="familyID" name="familyID"  value="" type="hidden">
			<input id="option" name="option" value="summary" type="hidden">
		</form>
		<!--                	Hieronder moet de tabel van Michiel komen te staan -->
		<Client-table-administrator show="contacts" id="table"></Client-table-administrator>
	</div>
	</core-header-panel> </core-drawer-panel>
	<script>
		document.addEventListener('polymer-ready', function() {
			var obj = document.querySelector('#table');
			console.log("Polymer Ready");
			var data = [ {
				"forename" : "Jan",
				"surname" : "De Man",
				"dateOfBirth" : "09-08-1990",
				"postcode" : "1219ZE",
				"street" : "Lunar Street",
				"houseNumber" : "5",
				"city" : "New York",
				"nationality" : "NL",
				"telephoneNumber" : "03566778800",
				"mobilePhoneNumber" : "0657722020",
				"email" : "Jan.DeMan@gmail.com",
				"fileNumber" : "1",
				"client_id"  : "1"
			}, {
				"forename" : "John",
				"surname" : "De Gooijer",
				"dateOfBirth" : "09-08-1990",
				"postcode" : "1219ZE",
				"street" : "Lunar Street",
				"houseNumber" : "5",
				"city" : "New York",
				"nationality" : "NL",
				"telephoneNumber" : "03566774800",
				"mobilePhoneNumber" : "0657722020",
				"email" : "John.DeGooijer@gmail.com",
				"fileNumber" : "2",
				"client_id"  : "2"
			}, {
				"forename" : "Joery",
				"surname" : "?",
				"dateOfBirth" : "09-08-1990",
				"postcode" : "1219ZE",
				"street" : "Lunar Street",
				"houseNumber" : "5",
				"city" : "New York",
				"nationality" : "NL",
				"telephoneNumber" : "03566778800",
				"mobilePhoneNumber" : "0657722020",
				"email" : "Joery.DeMan@gmail.com",
				"fileNumber" : "3",
			    "client_id"  : "3"
			} ]
			console.log("JSON OBJECT before table : " + obj);
			obj.loadData(data);
		});
	</script>
</body>
</html>