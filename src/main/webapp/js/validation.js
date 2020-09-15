 function leeg() {
            var gebruikersnaam = document.registratie.txtGebruikersnaam;
            var wachtwoord1 = document.registratie.txtWachtwoord;
            var wachtwoord2 = document.registratie.txtBevestig;
            var naam = document.registratie.txtNaam;
            var voornaam = document.registratie.txtVoornaam;
            var adres = document.registratie.txtAdres;
            var telefoon = document.registratie.txtTelefoon;
            var email = document.registratie.txtEmail;
            
            var regtest = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;

            var ingevuld = true;
            if(gebruikersnaam.value.length == 0) {
                gebruikersnaam.style.borderColor="red";
                ingevuld = false;
            }
            else {
                gebruikersnaam.style.bordercolor="#806855";
            }
            if(wachtwoord1.value.length == 0) {
                wachtwoord1.style.borderColor="red";
                ingevuld = false;
            }
            else {
                wachtwoord1.style.borderColor="#806855";
            }
            if(wachtwoord2.value.length == 0 || wachtwoord1.value !== wachtwoord2.value) {
                wachtwoord2.style.borderColor="red";
                ingevuld = false;
            }
            else {
                wachtwoord2.style.borderColor="#806855";
            }
            if(naam.value.length == 0) {
                naam.style.borderColor="red";
                ingevuld = false;
            }
            else {
                naam.style.borderColor="#806855";
            }
            if(voornaam.value.length == 0) {
                voornaam.style.borderColor="red";
                ingevuld = false;
            }
            else {
                voornaam.style.borderColor="#806855";
            }
            
            
            if(email.value.length == 0 || regtest.test(email.value) === false) {
                email.style.borderColor="red";
                ingevuld = false;

            }
            else {
                email.style.borderColor="#806855";
            }
            
            
            
            if(ingevuld == false) {
                document.getElementById("foutmelding").innerHTML = "Gelieve de roodgekleurde tekstvakken correct in te vullen";
            }
            else {
                document.getElementById("foutmelding").innerHTML = "";
            }

            return ingevuld;
            
        }

function correct(dit) {
        var func = document.getElementById(dit);
        func.style.borderColor="#806855";
       }
       
function gelijkeWachtwoorden() {
    var wachtwoord1 = document.registratie.txtWachtwoord;
    var wachtwoord2 = document.registratie.txtBevestig;
    if(wachtwoord1.value !== wachtwoord2.value) {
        wachtwoord2.style.borderColor = "red";
    } else {
        wachtwoord2.style.borderColor="#806855";
    }
}

        
