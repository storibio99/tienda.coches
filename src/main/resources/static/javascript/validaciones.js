//validaciones por expresiones regulares
// en js  lo que se ponga entre / es
// expresion regular
// ^es para decir que debe empezar
// $ debe terminar
//la i al final es para case insensitive (da igual las mayusculas)

regexp_nombre = /^[a-z áéíóúñ]{2,40}$/i

// Teléfono: Admite prefijo opcional (+34, +1, etc.) y entre 7 y 15 dígitos
const regexp_telefono = /^\+?[0-9\s\-]{7,15}$/;

// Dirección: Letras, números, espacios y caracteres comunes de dirección (º, ª, slashes, guiones)
const regexp_direccion = /^[a-z0-9\s,.-ºª/]{3,100}$/i;

// Código Postal: Formato estándar de 5 dígitos (ej. España)
const regexp_codigoPostal = /^[0-9]{5}$/;

// Provincia: Solo letras, espacios y acentos (2 a 40 caracteres)
const regexp_provincia = /^[a-z áéíóúñ]{2,40}$/i;

// Ciudad: Solo letras, espacios y acentos (2 a 40 caracteres)
const regexp_ciudad = /^[a-z áéíóúñ]{2,40}$/i;

// Tipo de tarjeta: Opciones cerradas comunes (visa, mastercard, amex, etc.)
const regexp_tipo_tarjeta = /^(visa|mastercard|amex|discover)$/i;

// Número de tarjeta: 13 a 19 dígitos (admite espacios o guiones opcionales entre bloques)
const regexp_numero_tarjeta = /^(?:[0-9]{4}[-\s]?){3}[0-9]{1,4}$/;

// Titular de la tarjeta: Nombre y apellidos en mayúsculas/minúsculas (2 a 50 caracteres)
const regexp_titular_tarjeta = /^[a-z áéíóúñ]{2,50}$/i;

// Fecha de caducidad: Formato MM/AA o MM/AAAA (mes del 01 al 12)
const regexp_fechaCaducidad = /^(0[1-9]|1[0-2])\/([0-9]{2}|[0-9]{4})$/;

// CVV: 3 o 4 dígitos numéricos
const regexp_cvv = /^[0-9]{3,4}$/;


function validarNombre (nombre){
    if (regexp_nombre.test(nombre)){
        return true;
    }else {
        alert("Nombre debe ser letras y espacios de 2 a 40 caracteres")
        return false;
    }
}


function validarTelefono(telefono) {
    if (regexp_telefono.test(telefono)) {
        return true;
    } else {
        alert("Teléfono inválido. Debe incluir entre 7 y 15 dígitos (acepta prefijo con +).");
        return false;
    }
}

function validarDireccion(direccion) {
    if (regexp_direccion.test(direccion)) {
        return true;
    } else {
        alert("Dirección inválida. Debe tener entre 3 y 100 caracteres válidos.");
        return false;
    }
}

function validarCodigoPostal(codigoPostal) {
    if (regexp_codigoPostal.test(codigoPostal)) {
        return true;
    } else {
        alert("Código postal inválido. Debe contener exactamente 5 dígitos.");
        return false;
    }
}

function validarProvincia(provincia) {
    if (regexp_provincia.test(provincia)) {
        return true;
    } else {
        alert("Provincia debe ser letras y espacios de 2 a 40 caracteres.");
        return false;
    }
}

function validarCiudad(ciudad) {
    if (regexp_ciudad.test(ciudad)) {
        return true;
    } else {
        alert("Ciudad debe ser letras y espacios de 2 a 40 caracteres.");
        return false;
    }
}

function validarTipoTarjeta(tipoTarjeta) {
    if (regexp_tipo_tarjeta.test(tipoTarjeta)) {
        return true;
    } else {
        alert("Tipo de tarjeta no válido (debe ser Visa o Mastercard).");
        return false;
    }
}

function validarNumeroTarjeta(numeroTarjeta) {
    if (regexp_numero_tarjeta.test(numeroTarjeta)) {
        return true;
    } else {
        alert("Número de tarjeta inválido. Debe tener entre 13 y 19 dígitos.");
        return false;
    }
}

function validarTitularTarjeta(titularTarjeta) {
    if (regexp_titular_tarjeta.test(titularTarjeta)) {
        return true;
    } else {
        alert("Titular de la tarjeta debe ser letras y espacios de 2 a 50 caracteres.");
        return false;
    }
}

function validarFechaCaducidad(fechaCaducidad) {
    if (regexp_fechaCaducidad.test(fechaCaducidad)) {
        return true;
    } else {
        alert("Fecha de caducidad inválida. Use el formato MM/AA o MM/AAAA.");
        return false;
    }
}

function validarCvv(cvv) {
    if (regexp_cvv.test(cvv)) {
        return true;
    } else {
        alert("CVV inválido. Debe contener 3 o 4 dígitos.");
        return false;
    }
}