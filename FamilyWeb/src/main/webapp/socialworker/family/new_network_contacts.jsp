<!DOCTYPE html>
<html>
<head>
    <title>Nieuw netwerk</title>
    <meta name="viewport" content="width=device-width, minimum-scale=1.0, initial-scale=1, user-scalable=yes">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="mobile-web-app-cable" content="yes">
    <meta charset="utf-8">
    <script src="../../bower_components/webcomponentsjs/webcomponents.min.js"></script>
    <link rel="import" href="../../elements.html">
    <link rel="import" href="../../custom_elements/responsive-menu-socialworker.html">
    <link rel="import" href="../../custom_elements/options-menu.html">
    <link rel="import" href="../../bower_components/core-media-query/core-media-query.html">
    <link rel="import" href="../../bower_components/paper-tabs/paper-tabs.html">
    <link rel="import" href="../../bower_components/core-icon-button/core-icon-button.html">
    <link rel="stylesheet" href="../../styles/new_network_contacts.css">
</head>
<body fullbleed layout vertical>
    <core-drawer-panel responsivewidth="1400px">
        <core-header-panel drawer>
            <core-toolbar>
                <core-icon-button core-drawer-toggle icon="close"></core-icon-button>
                <div flex>Menu</div>
            </core-toolbar>
            <responsive-menu-socialworker current="1"></responsive-menu-socialworker>
        </core-header-panel>
        <core-header-panel main>
            <core-toolbar class="medium-tall">
                <core-icon-button core-drawer-toggle icon="menu"></core-icon-button>
                <div flex>Nieuw netwerk</div>
                <options-menu></options-menu>
                <div class="bottom fit" horizontal layout>
                    <paper-tabs id="scrollableTabs" selected="2" flex scrollable noink>

                        <paper-tab><a href="family_members_overview.html" horizontal center-center layout>Gezinsleden</a></paper-tab>
                        <paper-tab><a href="network_compare.html" horizontal center-center layout>Netwerken</a></paper-tab>
                        <paper-tab><a href="new_network.html" horizontal center-center layout>Nieuw Netwerk</a></paper-tab>
                        <paper-tab><a href="share_networks.html" horizontal center-center layout>Netwerk(en) delen</a></paper-tab>
                        <paper-tab><a href="transfer.html" horizontal center-center layout>Overdragen</a></paper-tab>

                    </paper-tabs>
                </div>
            </core-toolbar>
            <div class="content" layout vertical>
                <message-window-notification message="Selecteer een gezinslid en voeg zijn of haar contactpersonen toe aan de contactgroepen."></message-window-notification>
        		<% if(!(session.getAttribute("message") == null)){%>
        		<message-window-error message="${message}"></message-window-error>
        		<% } %>
				<div id="form_container">
                    <form id="group_form" onsubmit="hoi" method="post">

                        <div id="select_interviewee">
                            <div class="information">
                                <label>Selecteer de ondervraagde:</label>
                                <select id="interviewee" name="interviewee">
                                    <option class="select_option" value="hans">Hans</option>
                                    <option class="select_option" value="billy">Billy</option>
                                    <option class="select_option" value="adriaan">Adriaan</option>
                                    <option class="select_option" value="bassie">Bassie</option>
                                </select>
                            </div>
                        </div>
                        <!-- elke groep kan ook via een jsp functie worden aangemaakt zolang alle groepen al in de sessie staan. Dus die moeten al eerder worden aangemaakt-->
                        <div id="gezin" class="group">
                            <input class="hidden" id="countergezin" type="text" value="0" />
                            <h3>Gezin</h3>
                            <core-icon-button class="add" onclick="addInput('gezin')" icon="add"></core-icon-button>
                        </div>
                        <div id="familie" class="group">
                            <input class="hidden" id="counterfamilie" type="text" value="0" />
                            <h3>Familie</h3>
                            <core-icon-button class="add" onclick="addInput('familie')" icon="add"></core-icon-button>
                        </div>
                        <div id="vrienden" class="group">
                            <input class="hidden" id="countervrienden" type="text" value="0" />
                            <h3>Vrienden</h3>
                            <core-icon-button class="add" onclick="addInput('vrienden')" icon="add"></core-icon-button>
                        </div>
                        <div id="collegas" class="group">
                            <input class="hidden" id="countercollegas" type="text" value="0" />
                            <h3>Collega's</h3>
                            <core-icon-button class="add" onclick="addInput('collegas')" icon="add"></core-icon-button>
                        </div>
                        <div id="buren" class="group">
                            <input class="hidden" id="counterburen" type="text" value="0" />
                            <h3>Buren</h3>
                            <core-icon-button class="add" onclick="addInput('buren')" icon="add"></core-icon-button>
                        </div>
                        <div id="kennissen " class="group">
                            <input class="hidden" id="counterkennissen " type="text" value="0" />
                            <h3>Kennissen </h3>
                            <core-icon-button class="add" onclick="addInput('kennissen ')" icon="add"></core-icon-button>
                        </div>
                        <div id="onderwijs" class="group">
                            <input class="hidden" id="counteronderwijs" type="text" value="0" />
                            <h3>Onderwijs</h3>
                            <core-icon-button class="add" onclick="addInput('onderwijs')" icon="add"></core-icon-button>
                        </div>
                        <div id="verenigingen" class="group">
                            <input class="hidden" id="counterverenigingen" type="text" value="0" />
                            <h3>Verenigingen</h3>
                            <core-icon-button class="add" onclick="addInput('verenigingen')" icon="add"></core-icon-button>
                        </div>
                        <div id="religie" class="group">
                            <input class="hidden" id="counterreligie" type="text" value="0" />
                            <h3>Religie</h3>
                            <core-icon-button class="add" onclick="addInput('religie')" icon="add"></core-icon-button>
                        </div>
                        <div id="zorginstellingen" class="group">
                            <input class="hidden" id="counterzorginstellingen" type="text" value="0" />
                            <h3>Zorginstellingen</h3>
                            <core-icon-button class="add" onclick="addInput('zorginstellingen')" icon="add"></core-icon-button>
                        </div>
                        <div id="jeugdzorg" class="group">
                            <input class="hidden" id="counterjeugdzorg" type="text" value="0" />
                            <h3>Jeugdzorg</h3>
                            <core-icon-button class="add" onclick="addInput('jeugdzorg')" icon="add"></core-icon-button>
                        </div>
                        <div id="bureauhalt" class="group">
                            <input class="hidden" id="counterbureauhalt" type="text" value="0" />
                            <h3>Bureau HALT</h3>
                            <core-icon-button class="add" onclick="addInput('bureauhalt')" icon="add"></core-icon-button>
                        </div>
                        <div id="justitie" class="group">
                            <input class="hidden" id="counterjustitie" type="text" value="0" />
                            <h3>Justitie</h3>
                            <core-icon-button class="add" onclick="addInput('justitie')" icon="add"></core-icon-button>
                        </div>
                        <input type="submit" value="Vragenlijst afnemen" />
                    </form>
                </div>
            </div>
        </core-header-panel>
    </core-drawer-panel>
    <core-media-query id="mediaQuery" query="max-width: 640px"></core-media-query>

    <script>
        function addInput(groupName) {
            var countBox = parseInt(document.getElementById("counter" + groupName).value);
            countBox += 1;
            var name = "name" + countBox;
            var role = "role" + countBox;
            var age = "age" + countBox;
            var validate = "validate" + countBox;
            document.getElementById(groupName).innerHTML += '<div id="' + groupName + countBox + '" class="person"> <div class="contact"> <div class="information"> <label>Naam:</label> <input type="text" id="' + groupName + name + '" name="' + groupName + name + '" placeholder="Volledige naam"/> </div> <div class="information"> <label>Rol:</label> <input type="text" id="' + groupName + role + '" name="' + groupName + role + '" placeholder="Rol" /> </div> <div class="information"> <label>Leeftijd:</label> <input type="text" id="' + groupName + age + '" name="' + groupName + age + '" placeholder="Leeftijd" /> </div> <input class="hidden" type="text" id="' + groupName + validate + '" name="' + groupName + validate + '" value="true" /> </div> <core-icon-button class="remove" icon="highlight-remove" onclick="' + " removeInput(" + countBox + "," + "'" + groupName + "'" + ")" + '"></core-icon-button> </div>';
            document.getElementById("counter" + groupName).value = countBox;
        }

        function removeInput(personNumber, groupName) {
            document.getElementById(groupName + personNumber).style.display = 'none';
            document.getElementById(groupName + "validate" + personNumber).value = "false";
        }

        document.querySelector('#mediaQuery').addEventListener('core-media-change',
          function (e) {
              document.body.classList.toggle('core-narrow', e.detail.matches);
              document.querySelector('#scrollableTabs').updateBar();
          });

    </script>
</body>

</html>
