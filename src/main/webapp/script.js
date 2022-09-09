
function goRight(anId){
	var elem = document.getElementById(anId);
	elem.scrollLeft += 164;
};


function goLeft(anId){
	 var elem = document.getElementById(anId);
	elem.scrollLeft -= 164;
};

function httpBlankGet(theUrl)
{
    var url = 'http://localhost:8080/TPFilRouge/' + theUrl;
	window.location = url;
}



function openForm() {
  document.getElementById("myForm").style.display = "flex";
  document.getElementById("id_login_name").focus();
}

function closeForm() {
  document.getElementById("myForm").style.display = "none";
}

function deleteSeance(formid, id){	
	var input = document.createElement("input");
	input.setAttribute("type", "hidden");
	input.setAttribute("name", "deleteid");
	input.setAttribute("value", id);
	var formed = document.getElementById(formid);
	formed.appendChild(input);	
	formed.submit();
	}
	
	
	function edit_on()
{
	var consulter = document.getElementById("id_profile_consult");
	var editter = document.getElementById("id_profile_edit");
	consulter.style.display = "none";
	editter.style.display = "flex";
	/*
	var nom = document.getElementById("id_nom");
	var prenom = document.getElementById("id_prenom");
	var dob = document.getElementById("id_dob");
	var telephone = document.getElementById("id_telephone");
	var poste = document.getElementById("id_poste");
	var specialite = document.getElementById("id_specialite");
	var url = document.getElementById("id_url");
	
	var nom_edit = document.getElementById("id_nom_edit");
	var prenom_edit = document.getElementById("id_prenom_edit");
	var dob_edit = document.getElementById("id_dob_edit");
	var telephone_edit = document.getElementById("id_telephone_edit");
	var poste_edit = document.getElementById("id_poste_edit");
	var specialite_edit = document.getElementById("id_specialite_edit");
	var url_edit = document.getElementById("id_url_edit");
	
	
	nom_edit.value = nom.innerText;
	prenom_edit.value = prenom.innerText;
	dob_edit.value = dob.innerText;
	telephone_edit.value = telephone.innerText;
	poste_edit.value = poste.innerText;
	specialite_edit.value = specialite.innerText;
	url_edit.value = url.innerText;
	
	document.getElementById("id_adr_num_edit").value = document.getElementById("id_adr_num").innerText;
	document.getElementById("id_adr_nom_edit").value = document.getElementById("id_adr_nom").innerText;
	document.getElementById("id_adr_cpo_edit").value = document.getElementById("id_adr_cpo").innerText;
	document.getElementById("id_adr_ville_edit").value = document.getElementById("id_adr_ville").innerText;
	*/
}

function edit_off()
{
	var consulter = document.getElementById("id_profile_consult");
	var editter = document.getElementById("id_profile_edit");
	consulter.style.display = "flex";
	editter.style.display = "none";
}