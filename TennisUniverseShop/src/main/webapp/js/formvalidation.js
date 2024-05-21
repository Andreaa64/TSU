function Validation()
{
var em = document.registration.email;
var nome = document.registration.nome;
var cognome = document.registration.cognome;
var username = document.registration.username;
var nascita = document.registration.nascita;
var psw = document.registration.password;

if( ValidateEmail(em))
{
	if(allLetter(nome))
	{
		if(allLetter(cognome))
		{
			if(user(username,4, 12))
			{	
				if(isValidAge(nascita))
				{ 
					if(psw_validation(psw,7,12))
					{
						return true;
					} 
				}
			}
		}
	}
}

return false;

}

function psw_validation(passid,mx,my)
{
var passid_len = passid.value.length;
if (passid_len == 0 ||passid_len >= my || passid_len < mx)
{
alert("Password should not be empty / length be between "+mx+" to "+my);
passid.focus();
return false;
}
return true;
}

function user(uid,mx,my)
{
var uid_len = uid.value.length;
if (uid_len == 0 || uid_len >= my || uid_len < mx)
{
alert("User Id should not be empty / length be between "+mx+" to "+my);
uid.focus();
return false;
}
return true;
}

function allLetter(uname)
{ 
var letters = /^[A-Za-z]+$/;
if(uname.value.match(letters))
{
return true;
}
else
{
alert('Username must have alphabet characters only');
uname.focus();
return false;
}
}

function ValidateEmail(uemail)
{

	var x = uemail.value;
    var atpos = x.indexOf("@");
    var dotpos = x.lastIndexOf(".");
    if (atpos<1 || dotpos<atpos+2 || dotpos+2>=x.length) {
        alert("Not a valid e-mail address");
        uemail.focus();
        return false;
    }
    else
{
return true;
}

} 

function isValidAge(nascita) {
    var birthDate = new Date(nascita.value);
    var today = new Date();
    var age = today.getFullYear() - birthDate.getFullYear();
    var monthDifference = today.getMonth() - birthDate.getMonth();

    // Se il mese di nascita è maggiore del mese attuale o è uguale ma il giorno è maggiore, sottrai un anno dall'età
    if (monthDifference < 0 || (monthDifference === 0 && today.getDate() < birthDate.getDate())) {
        age--;
    }

    if (age >= 18) {
        return true;
    } else {
        alert("Devi avere almeno 18 anni.");
        return false;
    }
}
