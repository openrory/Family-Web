<!DOCTYPE html>
<html>
<head>
	<title>Overzicht zorgprofessionals</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="mobile-web-app-cable" content="yes">
    <meta charset="utf-8">
    <script src="/FamilyWeb/bower_components/webcomponentsjs/webcomponents.min.js"></script>
    <link rel="import" href="/FamilyWeb/elements.html">
    <link rel="import" href="/FamilyWeb/custom_elements/responsive-menu-administrator.html">
    <link rel="import" href="/FamilyWeb/custom_elements/options-menu.html">
    	<link rel="import" href="/FamilyWeb/custom_elements/ZorgproffesionalTable.html">
    <link rel="stylesheet" href="/FamilyWeb/styles/socialworker_overview.css">

</head>
<body fullbleed layout horizontal>
    <core-drawer-panel responsivewidth="1400px">
        <core-header-panel drawer>
            <core-toolbar>
                <core-icon-button core-drawer-toggle icon="close"></core-icon-button>
                <div flex>Menu</div>
            </core-toolbar>
            <responsive-menu-administrator current="2"></responsive-menu-administrator>
        </core-header-panel>
        <core-header-panel main>
            <core-toolbar>
                <core-icon-button core-drawer-toggle icon="menu"></core-icon-button>
                <div flex>Overzicht zorgprofessionals</div>
                <options-menu></options-menu>
            </core-toolbar>
            <div class="content" fit layout vertical>
            <% if(!(request.getAttribute("message") == null)){%>
        	<message-window-${messageType} message="${message}"></message-window-${messageType}>
        	<% } %>
			<p>Hieronder Ziet u een overzicht van alle gebruikers.</p>
               	<form id="zorgproffesionalform" action="/FamilyWeb/EmployeeServlet.do" method="post">
<!--                	Hier wordt de id van de familie neergezet voor de servlets -->
               	<input id="userID" name="userID" type="hidden" value="">
               	<input name="option" type="hidden" value="summary">
				 	</form>
                    <!--hieronder staat de complete tabel met alle zorgprofessionals-->
                    <Zorgproffesional-table show="contacts" id="table"></Zorgproffesional-table>
               </div>
        </core-header-panel>
    </core-drawer-panel>
        <script>
    // id van de familie wordt in zorgproffesionalID neergezet en het formulier wordt verstuurd
        function submit(ID) {
            document.getElementById("userID").value = ID;
            document.getElementById("zorgproffesionalform").submit()
        };
        document.addEventListener('polymer-ready', function () {
            var obj = document.querySelector('#table');
            console.log("Polymer Ready");
            var data = ${usersJSON};
            console.log("JSON OBJECT before table : " + obj);
            obj.loadData(data);
        });
    </script>
</body>
</html>