<!DOCTYPE html>
<html>
<head>
<title>Nieuw netwerk</title>
<meta name="viewport"
	content="width=device-width, minimum-scale=1.0, initial-scale=1, user-scalable=yes">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="mobile-web-app-cable" content="yes">
<meta charset="utf-8">
<script
	src="../../bower_components/webcomponentsjs/webcomponents.min.js"></script>
<link rel="import" href="../../elements.html">
<link rel="import"
	href="../../custom_elements/responsive-menu-socialworker.html">
<link rel="import" href="../../custom_elements/options-menu.html">
<link rel="import"
	href="../../bower_components/core-media-query/core-media-query.html">
<link rel="import"
	href="../../bower_components/paper-tabs/paper-tabs.html">
<link rel="import"
	href="../../bower_components/core-icon-button/core-icon-button.html">
<link rel="stylesheet" href="../../styles/new_network_questions.css">
</head>
<body fullbleed layout vertical>
	<core-drawer-panel responsivewidth="1400px"> <core-header-panel
		drawer> <core-toolbar> <core-icon-button
		core-drawer-toggle icon="close"></core-icon-button>
	<div flex>Menu</div>
	</core-toolbar> <responsive-menu-socialworker current="1"></responsive-menu-socialworker>
	</core-header-panel> <core-header-panel main> <core-toolbar
		class="medium-tall"> <core-icon-button
		core-drawer-toggle icon="menu"></core-icon-button>
	<div flex>Nieuw netwerk</div>
	<options-menu></options-menu>
	<div class="bottom fit" horizontal layout>
		<paper-tabs id="scrollableTabs" selected="2" flex scrollable noink>

		<paper-tab>
		<a href="family_members_overview.html" horizontal center-center layout>Gezinsleden</a></paper-tab>
		<paper-tab>
		<a href="network_compare.html" horizontal center-center layout>Netwerken</a></paper-tab>
		<paper-tab>
		<a href="new_network_contacts.html" horizontal center-center layout>Nieuw
			Netwerk</a></paper-tab> <paper-tab>
		<a href="share_networks.html" horizontal center-center layout>Netwerk(en)
			delen</a></paper-tab> <paper-tab>
		<a href="transfer.html" horizontal center-center layout>Overdragen</a></paper-tab>

		</paper-tabs>
	</div>
	</core-toolbar>
	<div class="content" layout vertical>
		<message-window-notification
			message="Beantwoord per contact de vragen."></message-window-notification>
		<%@ page import="java.util.ArrayList" %>
		<%@ page import="domain.FamilyWeb.Contact" %>
		<%@ page import="domain.FamilyWeb.Question" %>
		<%@ page import="domain.FamilyWeb.Answer" %>
		<% if(!(session.getAttribute("message") == null)){%>
		<message-window-error message="${message}"></message-window-error>
		<% } %>
		<div id="form_container">
			<form id="group_form" onsubmit="hoi" method="post">
				<!-- elke contact kan ook via een jsp functie worden aangemaakt zolang alle contact al in de sessie staan. Dus die moeten al eerder worden aangemaakt-->
				<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
				<c:forEach items="${contacts}" var="contact">
					<div class="contact">
						<h3>${contact.name}</h3>
						<core-icon-button class="add" onclick="viewQuestions(${contact.ID})" icon="arrow-drop-down"></core-icon-button>
						<div id="${contact.id}" class="questions">
						<c:forEach items="${contact.questions}" var="question">
						<div id="${question.id}">
						<p>${question.name}</p>
						<c:forEach items="${question.anwsers}" var="anwser">
						<input type="radio" name="${question.id}" value="${answer.name}" />${answer.name}
						</c:forEach>
						</div>
						</c:forEach>
						<input type="button" onclick="closeQuestions(${contact.id})" value="Volgende contactpersoon" />
						</div>
					</div>
				</c:forEach>
			</form>
		</div>
	</div>
	</core-header-panel> 
	</core-drawer-panel>
	<core-media-query id="mediaQuery" query="max-width: 640px"></core-media-query>

	<script>
		function viewQuestions(questionsID) {
            document.getElementById(questionsID).style.display = 'block';
        }
		
		function closeQuestions(questionsID){
			document.getElementById(questionsID).style.display = 'none';
		}

        document.querySelector('#mediaQuery').addEventListener('core-media-change',
          function (e) {
              document.body.classList.toggle('core-narrow', e.detail.matches);
              document.querySelector('#scrollableTabs').updateBar();
          });

    </script>
</body>

</html>
