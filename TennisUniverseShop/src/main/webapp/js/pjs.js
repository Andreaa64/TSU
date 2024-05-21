function toggleMetodoPagamento() {
    var paypalDiv = document.getElementById('paypal-info');
    var cartaDiv = document.getElementById('carta-info');
    var metodoPaypal = document.getElementById('paypal-email');
    var nomeTitolareCarta = document.getElementById('nome-titolare-carta');
    var numeroCarta = document.getElementById('numero-carta');
    var dataScadenza = document.getElementById('data-scadenza');
    var cvv = document.getElementById('cvv');

    var selectedMethod = document.querySelector('input[name="metodo-pagamento"]:checked').value;

    paypalDiv.style.display = selectedMethod === 'paypal' ? 'block' : 'none';
    cartaDiv.style.display = selectedMethod === 'carta' ? 'block' : 'none';

    metodoPaypal.required = selectedMethod === 'paypal';
    nomeTitolareCarta.required = selectedMethod === 'carta';
    numeroCarta.required = selectedMethod === 'carta';
    dataScadenza.required = selectedMethod === 'carta';
    cvv.required = selectedMethod === 'carta';

    if (selectedMethod === 'carta') {
        // Controllo della validità della data di scadenza
        var today = new Date();
        var inputDate = new Date('20' + dataScadenza.value.substring(3, 5), dataScadenza.value.substring(0, 2) - 1);

        if (dataScadenza.value.length === 5 && dataScadenza.value[2] === '/' && !isNaN(inputDate.getMonth()) && !isNaN(inputDate.getFullYear())) {
            if (inputDate.getFullYear() > today.getFullYear() || (inputDate.getFullYear() === today.getFullYear() && inputDate.getMonth() >= today.getMonth())) {
                // Data di scadenza valida
                dataScadenza.setCustomValidity('');
            } else {
                // Data di scadenza non valida
                dataScadenza.setCustomValidity('La carta è scaduta');
            }
        } else {
            // Formato della data non valido
            dataScadenza.setCustomValidity('Inserisci una data di scadenza valida (MM/AA)');
        }

        // Controllo della validità del numero di carta
        var cardNumberRegex = /^\d{16}$/;
        if (!cardNumberRegex.test(numeroCarta.value)) {
            numeroCarta.setCustomValidity('Inserisci un numero di carta valido a 16 cifre');
        } else {
            numeroCarta.setCustomValidity('');
        }

        // Controllo della validità del CVV
        var cvvRegex = /^\d{3}$/;
        if (!cvvRegex.test(cvv.value)) {
            cvv.setCustomValidity('Inserisci un CVV valido a 3 cifre');
        } else {
            cvv.setCustomValidity('');
        }
    } else if (selectedMethod === 'paypal') {
        // Controllo della validità dell'indirizzo email PayPal
        var emailRegex = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
        if (!emailRegex.test(metodoPaypal.value)) {
            metodoPaypal.setCustomValidity('Inserisci un indirizzo email PayPal valido');
        } else {
            metodoPaypal.setCustomValidity('');
        }
    }
}

