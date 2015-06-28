<!DOCTYPE html>
<html>
<head>
<title>Netwerken vergelijken</title>
<meta name="viewport"
	content="width=device-width, minimum-scale=1.0, initial-scale=1, user-scalable=yes">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="mobile-web-app-cable" content="yes">
<meta charset="utf-8">
<script
	src="/FamilyWeb/bower_components/webcomponentsjs/webcomponents.min.js"></script>
<link rel="import" href="/FamilyWeb/elements.html">
<link rel="import"
	href="/FamilyWeb/custom_elements/responsive-menu-socialworker.html">
<link rel="import" href="/FamilyWeb/custom_elements/options-menu.html">
<link rel="import"
	href="/FamilyWeb/bower_components/core-media-query/core-media-query.html">
<link rel="import"
	href="/FamilyWeb/bower_components/paper-tabs/paper-tabs.html">
<link rel="import"
	href="/FamilyWeb/bower_components/core-icon-button/core-icon-button.html">
<link rel="stylesheet" href="/FamilyWeb/styles/network_compare.css">
<link rel="import" href="/FamilyWeb/bower_components/paper-slider/paper-slider.html">

</head>
<body fullbleed layout vertical>
	<core-drawer-panel responsivewidth="1400px"> <core-header-panel
		drawer> <core-toolbar> <paper-icon-button class="toolbarButton" core-drawer-toggle icon="close"></paper-icon-button>
                <div class="menuTitle" flex>Menu</div>
	</core-toolbar> <responsive-menu-socialworker current="1"></responsive-menu-socialworker>
	</core-header-panel> <core-header-panel main> <core-toolbar
		class="medium-tall"> <core-icon-button
		core-drawer-toggle icon="menu"></core-icon-button>
	<div id="title" flex>Netwerken vergelijken</div>
	<options-menu></options-menu>
	<div class="bottom fit" horizontal layout>
		<paper-tabs id="scrollableTabs" selected="1" flex scrollable noink>

		<paper-tab> <a href="/FamilyWeb/socialworker/family/family_members_overview.jsp"
			horizontal center-center layout>Gezinsleden</a></paper-tab> <paper-tab>
		<a href="/FamilyWeb/socialworker/family/network_compare.html" horizontal center-center layout>Netwerken</a></paper-tab>
		<paper-tab> <a href="/FamilyWeb/socialworker/family/new_network_contacts.jsp" horizontal
			center-center layout>Nieuw Netwerk</a></paper-tab> <paper-tab> <a
			href="/FamilyWeb/socialworker/family/share_networks.jsp" horizontal center-center layout>Netwerk(en)
			delen</a></paper-tab> <paper-tab> <a href="/FamilyWeb/socialworker/family/transfer.jsp" horizontal
			center-center layout>Overdragen</a></paper-tab> </paper-tabs>
	</div>
	</core-toolbar>
	<div class="content" fit layout horizontal>
		<div class="network_container" id="container1">
            <div class="title">
            <div class="date" id="datumnetwork1"> Netwerk gemaakt op 21-02-2015</div>
            
            <form action="network_single_view.jsp" method="POST">
            	<input type="hidden" id="hidden1" name="selectedSlider" value="">
            	<input type="hidden" id="hidden2" name="selectedPerson" value="1">
            	<button type="submit">Submit</button>
<!--             	<core-icon-button class="fullscreen" icon="fullscreen" type ="submit"></core-icon-button> -->
			</form>
			</div>
			<div class="network" id = "network1">
			</div>
			<div class="sidebar">
			<div class="interviewee">
			<h1 class="title">Netwerken van:</h1>
				 <select id = "network1SelectList" onChange = "changePerson(this)">
				</select> 
			</div>
			<div class="contact_groups" >
			<h1 class="title">Contactgroepen</h1>
			<ul id = "network1contact_groups">
			</ul>
			</div>
			</div>
			<div class="timeline">
            	<h1 class="title">Tijdlijn met gemaakte netwerken</h1>
                <input type = "text" id="rangedatumnetwork1">
                <div id = "network1range">
				<input class="slider" type="range" name="points" min="0" max="10">
			</div>
         </div>
			<div class="general_comment">
				<h1 class="title">Algemeen commentaar</h1>
				<p id = "commentnetwork1">Lorem Ipsum is slechts een proeftekst uit het drukkerij- en zetterijwezen. Lorem Ipsum is de standaard proeftekst in deze bedrijfstak sinds de 16e eeuw, toen een onbekende drukker een zethaak met letters nam en ze door elkaar husselde om een font-catalogus te maken. Het heeft niet alleen vijf eeuwen overleefd maar is ook, vrijwel onveranderd, overgenomen in elektronische letterzetting</p>
			</div>
		</div>
		<div class="network_container" id="container2">
			<div class="title">
            <div class="date" id="datumnetwork2"> Netwerk gemaakt op 21-02-2015</div>
           <form action="network_single_view.jsp" method="POST">
            	<input type="hidden" id="hidden5" name="selectedSlider" value=Network2Slidervalue>
            	<input type="hidden" id="hidden6" name="selectedPerson" value=Network2ChosenPerson>
            	<button type="submit">Submit</button>
<!--             	<core-icon-button class="fullscreen" icon="fullscreen" type ="submit"></core-icon-button> -->
			</form>
			</div>
			<div class="network" id = "network2">
			</div>
			<div class="sidebar">
			<div class="interviewee">
			<h1 class="title" >Netwerken van:</h1>
                 <select id = "network2SelectList" onChange = "changePerson(this)">
				</select> 
          
			</div>
			<div class="contact_groups">
			<p>Contactgroepen</p>
			<ul id = "network2contact_groups">
			<li>Family<input type="checkbox" value="test"></li>
			<li>Collega's<input type="checkbox" value="test"></li>
			<li>Justitie<input type="checkbox" value="test"></li>
			<li>Religie<input type="checkbox" value="test"></li>
			<li>Family<input type="checkbox" value="test"></li>
			<li>Justitie<input type="checkbox" value="test"></li>
			<li>Religie<input type="checkbox" value="test"></li>
			<li>Family<input type="checkbox" value="test"></li>
			</ul>
			</div>
			</div>
			<div class="timeline">
				<h1 class="title">Tijdlijn met gemaakte netwerken</h1>
                 <input type = "text" id="rangedatumnetwork2">
                <div id = "network2range">
				<input class="slider" type="range" name="points" min="0" max="10">
                </div>
			</div>
			<div class="general_comment">
				<h1 class="title">Algemeen commentaar</h1>
				<p id = "commentnetwork2">Lorem Ipsum is slechts een proeftekst uit het drukkerij- en zetterijwezen. Lorem Ipsum is de standaard proeftekst in deze bedrijfstak sinds de 16e eeuw, toen een onbekende drukker een zethaak met letters nam en ze door elkaar husselde om een font-catalogus te maken. Het heeft niet alleen vijf eeuwen overleefd maar is ook, vrijwel onveranderd, overgenomen in elektronische letterzetting</p>
			</div>
		</div>
		<div class="resolution_warning_picture"></div>
	</div>
	</core-header-panel> </core-drawer-panel>
	<core-media-query id="mediaQuery" query="max-width: 640px"></core-media-query>

	<script>
		document.querySelector('#mediaQuery').addEventListener(
				'core-media-change',
				function(e) {
					document.body.classList.toggle('core-narrow',
							e.detail.matches);
					document.querySelector('#scrollableTabs').updateBar();
				});
	</script>
<script type="text/javascript" src="http://d3js.org/d3.v3.min.js"></script>
    <script type="text/javascript" src="/FamilyWeb/javascripts/network_compare.js">

    </script>
    </body>

    </html>