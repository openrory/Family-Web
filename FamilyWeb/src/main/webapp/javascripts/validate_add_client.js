function validateForm() {

var isGood = true;

var fileid = document.forms["add_form"]["fileid"].value;
var forename = document.forms["add_form"]["forename"].value;
var surname = document.forms["add_form"]["surname"].value;
var dateofbirth = document.forms["add_form"]["dateofbirth"].value;
var nationality = document.forms["add_form"]["nationality"].value;
var street = document.forms["add_form"]["street"].value;
var streetnumber = document.forms["add_form"]["streetnumber"].value;
var postcode = document.forms["add_form"]["postcode"].value;
var city = document.forms["add_form"]["city"].value;
var phonenumber = document.forms["add_form"]["phonenumber"].value;
var mobile = document.forms["add_form"]["mobile"].value;
var email = document.forms["add_form"]["email"].value;
var email_confirmation = document.forms["add_form"]["email_confirmation"].value;

if (fileid == null || fileid == "") {
	document.getElementById("fileidWarning").className="false";
	isGood = false;
} else {
	document.getElementById("fileidWarning").className="true";
}

if (forename == null || forename == "") {
	document.getElementById("forenameWarning").className="false";
	isGood = false;
} else {
	document.getElementById("forenameWarning").className="true";
}

if (surname == null || surname == "") {
	document.getElementById("surnameWarning").className="false";
	isGood = false;
} else {
	document.getElementById("surnameWarning").className="true";
}

if (dateofbirth == null || dateofbirth == "") {
	document.getElementById("dateofbirthWarning").className="false";
	isGood = false;
} else {
	document.getElementById("dateofbirthWarning").className="true";
}

if (nationality == null || nationality == "") {
	document.getElementById("nationalityWarning").className="false";
	isGood = false;
} else {
	document.getElementById("nationalityWarning").className="true";
}

if (street == null || street == "") {
	document.getElementById("streetWarning").className="false";
	isGood = false;
} else {
	document.getElementById("streetWarning").className="true";
}

if (streetnumber == null || streetnumber == "") {
	document.getElementById("streetnumberWarning").className="false";
	isGood = false;
} else {
	document.getElementById("streetnumberWarning").className="true";
}

if (postcode == null || postcode == "") {
	document.getElementById("postcodeWarning").className="false";
	isGood = false;
} else {
	document.getElementById("postcodeWarning").className="true";
}

if (city == null || city == "") {
	document.getElementById("cityWarning").className="false";
	isGood = false;
} else {
	document.getElementById("cityWarning").className="true";
}

if (phonenumber == null || phonenumber == "") {
	document.getElementById("phonenumberWarning").className="false";
	isGood = false;
} else {
	document.getElementById("phonenumberWarning").className="true";
}
//
if (mobile == null || mobile == "") {
	document.getElementById("mobileWarning").className="false";
	isGood = false;
} else {
	document.getElementById("mobileWarning").className="true";
}

if (email == null || email == "") {
	document.getElementById("emailWarning").className="false";
	isGood = false;
} else {
	document.getElementById("emailWarning").className="true";
}

if (email_confirmation == null || email_confirmation == "") {
	document.getElementById("email_confirmationWarning").className="false";
	isGood = false;
} else {
	document.getElementById("email_confirmationWarning").className="true";
}
 return isGood;
}
