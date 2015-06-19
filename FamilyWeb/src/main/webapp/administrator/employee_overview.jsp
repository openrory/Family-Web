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
                <p>Hier moet een tabel komen met het overzicht van de zorgprofessionals</p>
                <div id="form_container">
								<div id="search_container">
                    <form id="search_form">
                        <input type="search" id="zoeken_text" placeholder="Zoeken..." method="GET">
                        <input type="submit" value="Zoeken">
                    </form>
                </div>
                    <!--hieronder staat de complete tabel met alle zorgprofessionals-->
                    <div id="overview_container">
							<div class="overview">
                            <table>
                                <tr>
                                    <td>Voornaam</td>
                                    <td>Achternaam</td>
                                    <td>Regio</td>
                                    <td>Werkt sinds</td>
                                    <td>Status</td>
                                    <td>Opties</td>
                                </tr>
                                <!--de td's met f erin zijn bij elkaar 1 record van 1 zorgprofessional. Deze zijn omhuld door 1 form waarmee deze gegevens bewerkt kunnen worden.-->
                                <form>
                                    <tr>
                                        <td>Harry</td>
                                        <td>Potter</td>
                                        <td>'t Gooi</td>
                                        <td>25-03-2005</td>
                                        <td>Actief</td>
                                        <td><button type="submit">Bewerken</button></td>
                                    </tr>
                                </form>
                                <!--hieronder nogmaals 1 record van een zorgprofessional-->
                                <form>
                                    <tr>
                                        <td>Max</td>
                                        <td>Schipper</td>
                                        <td>Groningen</td>
                                        <td>13-04-2010</td>
                                        <td>Actief</td>
                                        <td><button type="submit">Bewerken</button></td>
                                    </tr>
                                </form>
                            </table>  
							</div>
                    </div>
                </div>
            </div>
        </core-header-panel>
    </core-drawer-panel>
</body>
</html>

