
    //Variables globales
    nombre_login = "" //variable que indica el nombre del usuario si se ha identificado


    //PARTE DE DESCARGA DE PLANTILLAS HTML
    //para que vayan cargando cuando pulsen en el menu
    //trae el html, cuando esta hecho, esta metido en la variable llamada res de por si, que para usarla la igualamos a la variable que usaremos mas adelante
    $.get(`plantillas_html_${codigo_idioma}/inicio.html`).done(function(res){
        plantilla_inicio = res
    })
    $.get(`plantillas_html_${codigo_idioma}/listado_productos.html`).done(function(res){
        plantilla_productos = res
    })
    $.get(`plantillas_html_${codigo_idioma}/detalles_producto.html`).done(function(res){
        plantilla_detalles_producto = res
    })
    $.get(`plantillas_html_${codigo_idioma}/registro_usuario.html`).done(function(res){
        plantilla_registro_usuario = res
    })
    $.get(`plantillas_html_${codigo_idioma}/identificar_usuario.html`).done(function(res){
        plantilla_login_usuario = res
    })
    $.get(`plantillas_html_${codigo_idioma}/carrito.html`).done(function(res){
        plantilla_carrito = res
    })
    $.get(`plantillas_html_${codigo_idioma}/check_out_1.html`).done(function(res){
        plantilla_check_out_1 = res
    })
    $.get(`plantillas_html_${codigo_idioma}/check_out_2.html`).done(function(res){
        plantilla_check_out_2 = res
    })
    $.get(`plantillas_html_${codigo_idioma}/check_out_3.html`).done(function(res){
        plantilla_check_out_3 = res
    })

    $.get(`plantillas_html_${codigo_idioma}/check_out_4.html`).done(function(res){
        plantilla_check_out_4 = res
    })






    //PARTE DE ATENCION A ENLACES

    marca_a_buscar = ""; //marca en el buscador

    comienzo_resultados = 0

    //con $() se invoca a jquery
    //recogemos el elemento con id inicio, y que cuando se haga click...
    function mostrar_coches(){

    $.getJSON("restCoches/listar", { marca: marca_a_buscar, comienzo: comienzo_resultados }).done(
        function (json){
            console.log(json)
            //vamos a usar el json para listar los productos
            resultadoMustache = Mustache.render(plantilla_productos, json.coches)
            totalCoches = json.totalCoches


            $("#contenedor").html(resultadoMustache)
            $("#total_resultados").html(totalCoches)

            //vamos a ocultar el anterior o siguiente cuando no proceda
            if (comienzo_resultados <= 0){
                //en este caso ocultamos el anterior
                $("#enlace_anterior").hide()
            }else{
                $("#enlace_anterior").show()
            }

            if ( (comienzo_resultados +9) < totalCoches ){
                //en este caso si queremos que aparezca siguiente
                $("#enlace_siguiente").show()
            }else {
                $("#enlace_siguiente").hide()
            }


            //para no perder la marca:

            $("#marca_buscador").val(marca_a_buscar)
            $("#marca_buscador").focus()


            //una vez volcado el contenido voy a decir como funcionan
            //el buscador, el anterior-siguiente y el span del total del resultados

            $("#marca_buscador").keyup(
                function (){
                    //segun el usuario escriba en el buscador,
                    //lo que escriba lo guardo en la variable marca_a_buscar
                    //e invoco de nuevo el mostrar_videojuegos
                    marca_a_buscar = $(this).val()
                    mostrar_coches()
                    comienzo_resultados = 0
                }
            )// end keyup

            $("#enlace_anterior").click(
                function (){
                    comienzo_resultados -= 9
                    mostrar_coches()
                }
            )// end click enlace_anterior

            $("#enlace_siguiente").click(
                function (){
                    comienzo_resultados += 9
                    mostrar_coches()
                }
            ) //end click enlace_siguiente

        }
    )
}// END MOSTRAR_COCHES



    function mostrar_form_registro_usuario(){

    $("#contenedor").html(plantilla_registro_usuario)

    //una vez mostrado el form de registro, vamos a decir
    //que va a pasar cuanso se haga click en Registrarse
    $("#form_registro_usuario").submit(
    function (e){
    //codigo a ejecutar cuando se haga el submit/envio del form
    e.preventDefault()//para evitar que el boton del form refresque la pagina
    //validar datos del form...
    //suponemos de momento que todos los datos estan bien
    //normalmente lo suyo seria ir procesando dato a dato
    //una opcion:
    //nombre = document.getElementById("nombre").value;
    //otra opcion:
    //nombre = $("#nombre").val()
    //lo mas normal seria ir recogiendo campo a campo, pero tenemos algo llamado FormData
    //que nos va a permitir obtener lo insertado en el form de una sola vez
    formulario = document.forms[0]
    formData = new FormData(formulario)
    console.log("bien, ya tengo toda la informacion introducida:")
    //console.log(formData)//no es muy util
    console.log(Object.fromEntries(formData))//esto ya te saca toda la info
    //mandar este formData a un servicioRest
    //mandar el formdata a UsuariosREST
    $.ajax("restUsuarios/registrarUsuario",{
    type : "POST",
    data: formData,
    cache: false,
    contentType: false,
    processData:false,
    success: function (res){
    alert(res)
    confetti()
}
})

}//end function
    )//end submit
}// END MOSTRAR_FORM_REGISTRO_USUARIO



    $("#identificarme").click(
    mostrar_login
    )



    function mostrar_login(){
    $("#contenedor").html(plantilla_login_usuario)

        //vamos a comprobar si hay alguna cookie con la info
        //de identificacion y la cargamos, mucho cuidado con guardar contraseñas o informacion
        //personal sin encriptar
        if ( typeof(Cookies.get("email")) != "undefined" ){
            $("#email").val(Cookies.get("email"))
        }

        if ( typeof(Cookies.get("pass")) != "undefined" ){
            $("#pass").val(Cookies.get("pass"))
        }

    //despues de mostrar la plantilla html de identificar usuario
    //vamos a decir que hacer cuando el usuario haga click en
    //Identificarme
    $("#form_login").submit(
    function (e){

    e.preventDefault() //para que no refresque la pagina
    $.post("restUsuarios/identificarUsuario",
{
    email: $("#email").val(),
    pass: $("#pass").val()
}
    ).done(function (res){
    if (res == "email o pass incorrectos"){
    alert(res)
    return
}

    nombre_login = res.split(",")[1]
    //aprovechamos y listamos productos
    //alert("Identificado correctamente, ya puedes agregar productos al carrito")

        // Una vez identificado corractamente
        //miramos si la casilla de recordar datos esta activa

        if ( $("#recordar_pass").prop("checked") ){
            //guardar los datos de acceso en una cookie
            //cookie no es mas que un archivo que guarda el
            //navegador para cada sitio web
            Cookies.set("email", $("#email").val(), { expires : 100 } )
            Cookies.set("pass", $("#pass").val(), { expires : 100 } )

        }

    confetti()
    mostrar_coches()

    id_usuario = res.split(",")[2]
        let html_img = '<img class="avatar_usuario w-20 h-20 rounded-full object-cover mt-1 ml-auto" src="mostrar_imagen_usuario?id=' + id_usuario + '"/>';

        $("#usuarioActual")
            .addClass("flex flex-col items-end")
            .html(html_img + "<span>Bienvenido " + nombre_login + "</span>");
    //muestro los div de  enlaces para usuarios identificados
    $(".enlace-carrito").css("display", "block")
    $(".enlace-pedidos").css("display", "block")
    $(".enlace-datos").css("display", "block")
    $(".enlace-cerrar-sesion").css("display", "block")
    //ocultar los div de los enlaces para usuarios no identificados
    $(".enlace-identificarme").css("display", "none")
    $(".enlace-registrarme").css("display", "none")
    $(".enlace")

})

}// end function
    )// end submit
}//END MOSTRAR_LOGIN


    function agregarProductoCarrito(idProducto, event) {
    // Evitar que el enlace "#" recargue o mueva la página
    if (event) event.preventDefault();

    // 1. Obtener la cantidad ingresada en el input de este producto
    const inputCantidad = document.getElementById(`cantidad-${idProducto}`);
    const cantidadIngresada = inputCantidad ? parseInt(inputCantidad.value, 10) : 1;

    // 2. Validar que sea un número válido y mayor a 0
    if (isNaN(cantidadIngresada) || cantidadIngresada < 1) {
    alert("Por favor, ingresa una cantidad válida.");
    return;
}

    // 3. Enviar el ID y la cantidad dinámica al servidor Spring
    $.post("restCarrito/agregarProducto", {
    id: idProducto,
    cantidad: cantidadIngresada
}).done(function (res) {
    alert(res);
}).fail(function () {
    alert("Ocurrió un error al agregar el producto.");
});
}



    function mostrar_detalles(id){

    $.getJSON("restCoches/obtener-coche",
        {
            id : id
        }).done(
        function (json){
            console.log(json)

            resultadoHtml = Mustache.render(plantilla_detalles_producto, json)
            $("#contenedor").html(resultadoHtml)


            // CARROUSELL
            $(document).ready(function () {
                const $carrusel = $("#carrusel");
                const $btnPrev = $("#btn-prev");
                const $btnNext = $("#btn-next");
                const $indicadores = $("#indicadores");
                const intervalo = 4000; // ms entre cambios automáticos

                let $imagenes = $();   // solo las imágenes que existen
                let $puntos = $();
                let actual = 0;
                let timer = null;

                // 1) Comprobar cuáles de las 3 imágenes cargan de verdad
                const pendientes = $carrusel.find(".imagen-producto").map(function () {
                    const $img = $(this);
                    const defer = $.Deferred();

                    const test = new Image();
                    test.onload = function () { defer.resolve({ $img: $img, ok: true }); };
                    test.onerror = function () { defer.resolve({ $img: $img, ok: false }); };
                    test.src = $img.attr("src");

                    return defer.promise();
                }).get();

                // 2) Cuando todas han respondido, montar el carrusel
                $.when.apply($, pendientes).done(function () {
                    const resultados = Array.prototype.slice.call(arguments);

                    resultados.forEach(function (r) {
                        if (r.ok) {
                            $imagenes = $imagenes.add(r.$img);
                        } else {
                            r.$img.remove(); // se elimina el propio elemento, no por posición
                        }
                    });

                    iniciar();
                });

                function iniciar() {
                    const total = $imagenes.length;

                    if (total === 0) {
                        $carrusel.hide();
                        return;
                    }

                    // Estado inicial: solo se ve la primera
                    $imagenes.hide().eq(0).show();

                    // Con una sola imagen no hace falta navegación
                    if (total === 1) return;

                    crearPuntos(total);
                    $btnPrev.removeClass("hidden");
                    $btnNext.removeClass("hidden");
                    marcarPunto(0);
                    eventos();
                    iniciarAuto();
                }

                function crearPuntos(total) {
                    $indicadores.empty();
                    for (let i = 0; i < total; i++) {
                        $indicadores.append(
                            '<span class="punto h-3 w-3 cursor-pointer rounded-full bg-emerald-800/50" data-index="' + i + '"></span>'
                        );
                    }
                    $puntos = $indicadores.find(".punto");
                }

                function marcarPunto(indice) {
                    $puntos.removeClass("bg-emerald-400").addClass("bg-emerald-800/50");
                    $puntos.eq(indice).removeClass("bg-emerald-800/50").addClass("bg-emerald-400");
                }

                function mostrar(indice) {
                    if (indice === actual) return;

                    $imagenes.eq(actual).stop(true, true).fadeOut(300, function () {
                        $imagenes.eq(indice).stop(true, true).fadeIn(300);
                    });

                    actual = indice;
                    marcarPunto(actual);
                }

                function siguiente() {
                    mostrar((actual + 1) % $imagenes.length);
                }

                function anterior() {
                    mostrar((actual - 1 + $imagenes.length) % $imagenes.length);
                }

                function iniciarAuto() {
                    detenerAuto();
                    timer = setInterval(siguiente, intervalo);
                }

                function detenerAuto() {
                    if (timer) clearInterval(timer);
                }

                function eventos() {
                    $btnNext.on("click", function () {
                        siguiente();
                        iniciarAuto();
                    });

                    $btnPrev.on("click", function () {
                        anterior();
                        iniciarAuto();
                    });

                    $indicadores.on("click", ".punto", function () {
                        mostrar(parseInt($(this).data("index")));
                        iniciarAuto();
                    });

                    $carrusel.on("mouseenter", detenerAuto).on("mouseleave", iniciarAuto);
                }
            });
            // FIN CARROUSELL
        }
    )
}// END MOSTRAR_DETALLES







    //para el listado de coches
    $("#coches").click(
    mostrar_coches
    )// end coches


    $("#registrarme").click(
    mostrar_form_registro_usuario
    )// end form usuario



    $("#pedidos").click(
    function(){
    alert("pedidos ya hechos")
}
    )// end pedidos


    $("#mis_datos").click(
    function(){
    alert("datos del cliente")
}
    )// end mis_datos








    $("#carrito").click(
    mostrar_productos_carrito
    )


    function mostrar_productos_carrito(){
    $.getJSON("restCarrito/obtenerProductos").done(
        function (res){
            console.log("json recibido")
            console.log(res)
            //usar la plantilla carrito para mostrar el json recibido
            html = Mustache.render(plantilla_carrito, res)

            $("#contenedor").html(html)

            $("#realizar_pedido").click(
                mostrar_check_out_paso_1
            )
        }
    )
}




    function mostrar_check_out_paso_1(){

    $("#contenedor").html(plantilla_check_out_1)

    $("#aceptar_paso_1").click(
    enviar_datos_paso1
    )
}

    function enviar_datos_paso1(){

    //obtener los valores introducidos
    //y mandarlo a pedidosREST
    nombreCompleto = $("#campo_nombre").val()
    telefono = $("#campo_telefono").val()
    direccion = $("#campo_direccion").val()
    codigoPostal = $("#campo_codigoPostal").val()
    //lo siguiente obtendria el value del elemento seleccionado
    provincia = $("#campo_provincia").val()
    ciudad = $("#campo_ciudad").val()

    //validaciones...(por hacer muy importante)
    if (! validarNombre(nombreCompleto)){
        return
    }
    //muy importante validar el resto de campos

        if (!validarNombre(nombreCompleto)) {
            return;
        }

        if (!validarTelefono(telefono)) {
            return;
        }

        if (!validarDireccion(direccion)) {
            return;
        }

        if (!validarCodigoPostal(codigoPostal)) {
            return;
        }

        if (!validarProvincia(provincia)) {
            return;
        }

        if (!validarCiudad(ciudad)) {
            return;
        }


    //esto tiene que enviar a un servicio REST
    $.post("restPedido/procesarPaso1",
{
    nombre: nombreCompleto,
    direccion: direccion,
    provincia: provincia,
    codigoPostal: codigoPostal,
    telefono: telefono,
    ciudad:ciudad
}
    ).done(function (res){
    //alert("el servidor responde: " + res)
    if (res == "ok"){

    $("#contenedor").html(plantilla_check_out_2);
    $("#aceptar_paso_2").click(
    enviar_datos_paso2
    )

}else{
    alert(res)
}
})
}// end enviar_datos_paso1


    function enviar_datos_paso2(){

    //recogemos los datos introducidos
    tipo_tarjeta = $("#tipo_tarjeta").val()
    numero_tarjeta = $("#numero_tarjeta").val()
    titular_tarjeta = $("#titular_tarjeta").val()
    fechaCaducidad = $("#fechaCaducidad").val()
    cvv = $("#cvv").val()

    //validarlos (importante, por hacer...)

        if (!validarTipoTarjeta(tipo_tarjeta)) {
            return;
        }

        if (!validarNumeroTarjeta(numero_tarjeta)) {
            return;
        }

        if (!validarTitularTarjeta(titular_tarjeta)) {
            return;
        }

        if (!validarFechaCaducidad(fechaCaducidad)) {
            return;
        }

        if (!validarCvv(cvv)) {
            return;
        }

    //mandarlos al post al REST
    $.post("restPedido/procesarPaso2",{
    tarjeta: tipo_tarjeta,
    numero: numero_tarjeta,
    titular: titular_tarjeta,
    caducidad: fechaCaducidad,
    cvv: cvv
}).done(function (res){
    if (res == "ok"){

    $("#contenedor").html(plantilla_check_out_3);
    $("#aceptar_paso_3").click(
    enviar_datos_paso3
    )

}else{
    alert(res)
}
})
}//end enviar_datos_paso2



    function enviar_datos_paso3(){

    //recogemos los datos introducidos
    tipoEntrega = $("#tipoEntrega").val()
    concesionarioSeleccionado = $("#concesionarioSeleccionado").val()
    requiereMatriculacion = $("#requiereMatriculacion").val()
    fechaPreferidaEntrega = $("#fechaPreferidaEntrega").val()

    //validarlos (importante, por hacer...)

    //mandarlos al post al REST
    $.post("restPedido/procesarPaso3",{
    tipoEntrega: tipoEntrega,
    concesionarioSeleccionado: concesionarioSeleccionado,
    requiereMatriculacion: requiereMatriculacion,
    fechaPreferidaEntrega: fechaPreferidaEntrega
}).done(function (res){
    //alert(res)
    console.log(res)
    //usar la info recibida para mostrar lo necesario en el paso 4- resumen del pedido
    $("#contenedor").html(Mustache.render(plantilla_check_out_4, res))
    $("#boton_confirmar_pedido").click(
    enviar_datos_paso4
    )
})

}//end enviar_datos_paso3



    function enviar_datos_paso4(){

    $.post("restPedido/procesarPaso4").done(
        function (res){
            alert(res)
            if (res == "ok"){
                confetti()
                alert("pedido completado! puedes seguir comprando")
                mostrar_coches()
            }
        }
    )

}//end enviar_datos_paso4


    $("#cerrar_sesion").click(cerrar_sesion)


    function cerrar_sesion(){
        //alert("cerrar sesion")
        $.post("restUsuarios/cerrarSesion").done(
            function (res){
                if (res == "ok"){
                    location.reload()
                }
            }
        )
    }










    //para que arranque el mostrar coches de primeras
    mostrar_coches()
