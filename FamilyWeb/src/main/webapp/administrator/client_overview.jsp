<!DOCTYPE html>
<html>
	<head>
		<title>Overzicht cli�nten</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="mobile-web-app-cable" content="yes">
		<meta charset="utf-8">
		
		<script src="/FamilyWeb/bower_components/webcomponentsjs/webcomponents.min.js"> </script>
		<link rel="import" href="/FamilyWeb/elements.html">
		<link rel="import" href="/FamilyWeb/custom_elements/responsive-menu-administrator.html">
		<link rel="import" href="/FamilyWeb/custom_elements/options-menu.html">
		<link rel="import" href="/FamilyWeb/custom_elements/client-table-administrator.html">
		<link rel="stylesheet" href="/FamilyWeb/styles/client_overview.css">
	</head>
	
	<body fullbleed layout horizontal>
	<core-drawer-panel responsivewidth="1400px"> <core-header-panel drawer> <core-toolbar> 
	<paper-icon-button class="toolbarButton" core-drawer-toggle icon="close"></paper-icon-button>
    <div class="menuTitle" flex>Menu</div>
	</core-toolbar> <responsive-menu-administrator current="3"></responsive-menu-administrator>
	</core-header-panel> <core-header-panel main> <core-toolbar>
	<paper-icon-button class="toolbarButton" core-drawer-toggle icon="menu"></paper-icon-button>
            <div id="title" flex>Overzicht cli�nten</div> <options-menu></options-menu> </core-toolbar>
	
	<div class="content" fit layout vertical>
	
		<!-- INFORMATION MESSAGE -->
		<% if (!(request.getAttribute("message") == null)) { %>
		<message-window-${messageType} message="${message}"></message-window-${messageType}>
		<% } %>
		
		<p>Hieronder ziet u een overzicht van alle cli�nten.</p>
		
		<!-- THIS DATA IS SEND TO THE SERVLET -->
		<form id="tableForm" action="/FamilyWeb/ClientServlet.do" method="post">	
			<input id="currentID" name="currentID" type="hidden" value=""> 
			<input name="option" type="hidden" value="summary">
		</form>
		
		<!-- DATA TABLE -->
		<client-table-administrator show="contacts" id="table"></client-table-administrator>
		
	</div>
	</core-header-panel> </core-drawer-panel>
	<script>
		//SET DATA FOR SERVLET AND TRIGGER FORM SUBMIT
		function submit(ID) {
			document.getElementById("currentID").value = ID;
			document.getElementById("tableForm").submit();
		};
		//LOAD DATA INTO TABLE 
		document.addEventListener('polymer-ready', function() {
			var obj = document.querySelector('#table');
			var data = ${clientsJSON};
			obj.loadData(data);
		});
	</script>
</body>
</html>
