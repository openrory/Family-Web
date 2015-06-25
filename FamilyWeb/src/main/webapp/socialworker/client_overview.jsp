<!DOCTYPE html>
<html>
<head>
	<title>Overzicht cliënten</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="mobile-web-app-cable" content="yes">
    <meta charset="utf-8">
    <script src="/FamilyWeb/bower_components/webcomponentsjs/webcomponents.min.js"></script>
    <link rel="import" href="/FamilyWeb/elements.html">
	<link rel="import" href="/FamilyWeb/custom_elements/responsive-menu-socialworker.html">
	<link rel="import" href="/FamilyWeb/custom_elements/options-menu.html">
	<link rel="import" href="../custom_elements/client-table-socialworker.html">
	<link rel="import" href="/FamilyWeb/custom_elements/Family-table.html">
	<link rel="stylesheet" href="/FamilyWeb/styles/client_overview.css">

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
            <div id="title" flex>Overzicht cliënten</div>
			<options-menu></options-menu>
            </core-toolbar>
            <div class="content" fit layout vertical>
            <% if(!(request.getAttribute("message") == null)){%>
        	<message-window-${messageType} message="${message}"></message-window-${messageType}>
        	<% } %>
			
               	<form id="familyform" action="/FamilyWeb/FamilyInformation.do" method="post">
<!--                	Hier wordt de id van de familie neergezet voor de servlets -->
               	<input id="familyID" name="familyID" type="hidden" value="">
               	</form>
<!--                	Hieronder moet de tabel van Michiel komen te staan -->
                      <Client-table show="contacts" id="table"></Client-table>
            </div>
        </core-header-panel>
    </core-drawer-panel>
    <script>
    // id van de familie wordt in familyID neergezet en het formulier wordt verstuurd
//         function submit(ID) {
//             document.getElementById("familyID").value = ID;
//             document.getElementById("familyform").submit();
//         };

        document.addEventListener('polymer-ready', function () {
            var obj = document.querySelector('#table');
            console.log("Polymer Ready");
            var data = ${clientsJSON};
            console.log("JSON OBJECT before table : " + obj);
            obj.loadData(data);
        });
    </script>
</body>
</html>

