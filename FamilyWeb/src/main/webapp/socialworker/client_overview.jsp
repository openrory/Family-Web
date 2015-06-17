<!DOCTYPE html>
<html>
<head>
	<title>Overzicht cli�nten</title>
    <meta forename="viewport" content="width=device-width, initial-scale=1.0">
    <meta forename="apple-mobile-web-app-capable" content="yes">
    <meta forename="mobile-web-app-cable" content="yes">
    <meta charset="utf-8">
    <script src="../bower_components/webcomponentsjs/webcomponents.min.js"></script>
    <link rel="import" href="../elements.html">
	<link rel="import" href="../custom_elements/responsive-menu-socialworker.html">
	<link rel="import" href="../custom_elements/options-menu.html">
	<link rel="import" href="../custom_elements/Family-table.html">
	<link rel="stylesheet" href="../styles/client_overview.css">

</head>
<body fullbleed layout vertical>
    <core-drawer-panel responsivewidth="2160px">
        <core-header-panel drawer>
            <core-toolbar>
				<core-icon-button core-drawer-toggle icon="close"></core-icon-button>
                <div flex>Menu</div>
            </core-toolbar>
            <responsive-menu-socialworker current="1"></responsive-menu-socialworker>
        </core-header-panel>
        <core-header-panel main>
            <core-toolbar>
			<core-icon-button core-drawer-toggle icon="menu"></core-icon-button>
            <div flex>Overzicht cli�nten</div>
			<options-menu></options-menu>
            </core-toolbar>
            <div class="content" fit layout vertical>
			<p>Hier moet een tabel komen met het overzicht van de cli�nten // Hieronder directe link naar zogenaamde familie</p>
               	<form id="familyform" action="FamilyInformation.do" method="post">
<!--                	Hier wordt de id van de familie neergezet voor de servlets -->
               	<input id="familyID" type="text" value="">
               	</form>
<!--                	Hieronder moet de tabel van Michiel komen te staan -->
                      <Family-table show="contacts" id="table"></Family-table>
            </div>
        </core-header-panel>
    </core-drawer-panel>
    <script>
    // id van de familie wordt in familyID neergezet en het formulier wordt verstuurd
        function submit(ID) {
            document.getElementById("familyID").value = ID;
            document.getElementById("familyform").submit()
        };

        document.addEventListener('polymer-ready', function () {
            var obj = document.querySelector('#table');
            console.log("Polymer Ready");
            var data = [
                         {
                             "forename": "Jan",
                             "surname": "De Man",
                             "dateOfBirth": "09-08-1990",
                             "postcode": "1219ZE",
                             "street": "Lunar Street",
                             "houseNumber": "5",
                             "city" : "New York",
                             "nationality" : "NL",
                             "telephoneNumber" : "03566778800",
                             "mobilePhoneNumber" : "0657722020",
                             "email" : "Jan.DeMan@gmail.com",
                             "fileNumber" : "1"
                         },
                                                  {
                                                      "forename": "John",
                                                      "surname": "De Gooijer",
                                                      "dateOfBirth": "09-08-1990",
                                                      "postcode": "1219ZE",
                                                      "street": "Lunar Street",
                                                      "houseNumber": "5",
                                                      "city": "New York",
                                                      "nationality": "NL",
                                                      "telephoneNumber": "03566774800",
                                                      "mobilePhoneNumber": "0657722020",
                                                      "email": "John.DeGooijer@gmail.com",
                                                      "fileNumber": "2"
                                                  },
                                                                           {
                                                                               "forename": "Joery",
                                                                               "surname": "?",
                                                                               "dateOfBirth": "09-08-1990",
                                                                               "postcode": "1219ZE",
                                                                               "street": "Lunar Street",
                                                                               "houseNumber": "5",
                                                                               "city": "New York",
                                                                               "nationality": "NL",
                                                                               "telephoneNumber": "03566778800",
                                                                               "mobilePhoneNumber": "0657722020",
                                                                               "email": "Joery.DeMan@gmail.com",
                                                                               "fileNumber": "3"
                                                                           }
            ]
            console.log("JSON OBJECT before table : " + obj);
            obj.loadData(data);
        });
    </script>
</body>
</html>
