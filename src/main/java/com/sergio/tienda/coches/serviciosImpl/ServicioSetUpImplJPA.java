package com.sergio.tienda.coches.serviciosImpl;


import com.sergio.tienda.coches.model.*;
import com.sergio.tienda.coches.servicios.ServicioPedidos;
import com.sergio.tienda.coches.servicios.ServicioSetUp;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

@Service
@Transactional
public class ServicioSetUpImplJPA implements ServicioSetUp {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ServicioPedidos servicioPedidos;



    @Override
    public void prepararRegistrosIniciales() {

        // preparar unos registros si la base de datos esta vacia
        Configuracion configuracion = null;
        //la idea presentada se basa en que va a haber un solo registro
        //en la tabla de Configuracion, ese registro nos indicara
        //si la base de datos ya tiene sus registros iniciales
        // como por ejemplo: productos, categorias, usuarios, pedidos, etc.

        //en sql seria como: select * from Configuracion as c
        try {
            configuracion = (Configuracion)entityManager.createQuery(
                    "select c from Configuracion c").getSingleResult();
        }catch (NoResultException e){
            System.out.println("No se encontro un unico registro en la tabla de Configuracion");
        }

        if (configuracion == null || ! configuracion.isRegistrosIniciales() ){
            //hago todos los registros iniciales
            //categorias
            Categoria catF1 = new Categoria("Formula 1", "Coches de la categoria reina");
            catF1.setImagenCategoria(leerImagenInicial("http://localhost:8080/imagenes_productos_iniciales/imagen_categoria_f1.png"));

            Categoria catFAcademy = new Categoria("Formula 1 Academy", "Categoria para mujeres con talento");
            catFAcademy.setImagenCategoria(leerImagenInicial("http://localhost:8080/imagenes_productos_iniciales/imagen_categoria_f1_academy.png"));


            Categoria catPrototipos = new Categoria("Prototipos", "Prototipos de resistencia");
            catPrototipos.setImagenCategoria(leerImagenInicial("http://localhost:8080/imagenes_productos_iniciales/imagen_categoria_prototipos.png"));


            Categoria catGT3 = new Categoria("GT3", "Coches de la categoria GT3 de resistencia");
            catGT3.setImagenCategoria(leerImagenInicial("http://localhost:8080/imagenes_productos_iniciales/imagen_categoria_gt3.png"));


            Categoria catSedan = new Categoria("Sedan", "Coches de tres volumenes con maletero independiente");
            catSedan.setImagenCategoria(leerImagenInicial("http://localhost:8080/imagenes_productos_iniciales/imagan_categoria_sedan.png"));

            Categoria catSUV = new Categoria("SUV", "Vehiculos utilitarios deportivos de carroceria elevada");
            catSUV.setImagenCategoria(leerImagenInicial("http://localhost:8080/imagenes_productos_iniciales/imagen_categoria_sub.png"));


            Categoria catConvertible = new Categoria("Convertible", "Coches con techo retractil o descapotable");
            catConvertible.setImagenCategoria(leerImagenInicial("http://localhost:8080/imagenes_productos_iniciales/imagen_categoria_convertible.png"));


            Categoria catTodoterreno = new Categoria("Todoterrenos", "Vehiculos preparados para terrenos dificiles y traccion integral");
            catTodoterreno.setImagenCategoria(leerImagenInicial("http://localhost:8080/imagenes_productos_iniciales/imagen_categoria_todoterreno.png"));


            Categoria catHatchback = new Categoria("Hatchback", "Compactos con porton trasero y maletero integrado");
            catHatchback.setImagenCategoria(leerImagenInicial("http://localhost:8080/imagenes_productos_iniciales/imagen_categoria_hatchback.png"));


            Categoria catDeportivo = new Categoria("Deportivo", "Coches de altas prestaciones y conduccion dinamica");
            catDeportivo.setImagenCategoria(leerImagenInicial("http://localhost:8080/imagenes_productos_iniciales/imagen_categoria_deportivo.png"));


            Categoria catSuperdeportivo = new Categoria("Superdeportivo", "Deportivos de potencia y rendimiento muy superiores");
            catSuperdeportivo.setImagenCategoria(leerImagenInicial("http://localhost:8080/imagenes_productos_iniciales/imagen_categoria_superdeportivo.png"));


            Categoria catHiperdeportivo = new Categoria("Hiperdeportivo", "La cumbre del rendimiento, series limitadas y tecnologia puntera");
            catHiperdeportivo.setImagenCategoria(leerImagenInicial("http://localhost:8080/imagenes_productos_iniciales/imagen_categoria_hiperdeportivo.png"));


            Categoria catRally = new Categoria("Rally", "Coches de competicion para tramos de asfalto y tierra");
            catRally.setImagenCategoria(leerImagenInicial("http://localhost:8080/imagenes_productos_iniciales/imagen_categoria_rally.png"));


            Categoria catPickUp = new Categoria("Pick-up", "Vehiculos con caja de carga abierta en la parte trasera");
            catPickUp.setImagenCategoria(leerImagenInicial("http://localhost:8080/imagenes_productos_iniciales/imagen_categoria_pickup.png"));


            Categoria catMonovolumen = new Categoria("Monovolumen", "Vehiculos familiares de gran espacio interior y varias plazas");
            catMonovolumen.setImagenCategoria(leerImagenInicial("http://localhost:8080/imagenes_productos_iniciales/imagen_categoria_monovolumen.png"));

            Categoria sinCategoria = new Categoria("Sin categoria", "Vehiculos que no tienen ninguna categoria");
            sinCategoria.setImagenCategoria(leerImagenInicial("http://localhost:8080/imagenes_productos_iniciales/imagen_sin_categoria.png"));



            entityManager.persist(sinCategoria);
            entityManager.persist(catF1);
            entityManager.persist(catFAcademy);
            entityManager.persist(catPrototipos);
            entityManager.persist(catGT3);
            entityManager.persist(catSedan);
            entityManager.persist(catSUV);
            entityManager.persist(catConvertible);
            entityManager.persist(catTodoterreno);
            entityManager.persist(catHatchback);
            entityManager.persist(catDeportivo);
            entityManager.persist(catSuperdeportivo);
            entityManager.persist(catHiperdeportivo);
            entityManager.persist(catRally);
            entityManager.persist(catPickUp);
            entityManager.persist(catMonovolumen);


            //productos
            // el primer registro tendra id 1

            // ===== FORMULA 1 =====
            Coche cocheF1AstonMartin =
                    new Coche("Aston Martin", "AMR26", "No tiene", "Hibrido", 40, 2026, 1000, true, 20000000);
            cocheF1AstonMartin.setCategoria(catF1);
            cocheF1AstonMartin.setImagenPortada(leerImagenInicial("http://localhost:8080/imagenes_productos_iniciales/1.jpg"));
            cocheF1AstonMartin.setImagen2(leerImagenInicial("http://localhost:8080/imagenes_productos_iniciales/47.jpg"));
            cocheF1AstonMartin.setImagen3(leerImagenInicial("http://localhost:8080/imagenes_productos_iniciales/48.jpg"));
            cocheF1AstonMartin.setImagen4(leerImagenInicial("http://localhost:8080/imagenes_productos_iniciales/49.jpg"));
            cocheF1AstonMartin.setImagen5(leerImagenInicial("http://localhost:8080/imagenes_productos_iniciales/50.jpg"));
            entityManager.persist(cocheF1AstonMartin);

            Coche cocheF1Ferrari =
                    new Coche("Ferrari", "SF-26", "No tiene", "Hibrido", 40, 2026, 1000, true, 20000000);
            cocheF1Ferrari.setCategoria(catF1);
            cocheF1Ferrari.setImagenPortada(leerImagenInicial("http://localhost:8080/imagenes_productos_iniciales/2.jpg"));
            entityManager.persist(cocheF1Ferrari);

            Coche cocheF1Mercedes =
                    new Coche("Mercedes", "W17", "No tiene", "Hibrido", 35, 2026, 1000, true, 19500000);
            cocheF1Mercedes.setCategoria(catF1);
            cocheF1Mercedes.setImagenPortada(leerImagenInicial("http://localhost:8080/imagenes_productos_iniciales/3.jpg"));
            entityManager.persist(cocheF1Mercedes);

            Coche cocheF1McLaren =
                    new Coche("McLaren", "MCL40", "No tiene", "Hibrido", 30, 2026, 1000, false, 20500000);
            cocheF1McLaren.setCategoria(catF1);
            cocheF1McLaren.setImagenPortada(leerImagenInicial("http://localhost:8080/imagenes_productos_iniciales/4.jpg"));
            entityManager.persist(cocheF1McLaren);

            // ===== FORMULA 1 ACADEMY =====
            Coche cocheFAcademyPrema =
                    new Coche("Tatuus", "F4-T421 Prema Racing", "No tiene", "Gasolina", 850, 2024, 165, true, 75000);
            cocheFAcademyPrema.setCategoria(catFAcademy);
            cocheFAcademyPrema.setImagenPortada(leerImagenInicial("http://localhost:8080/imagenes_productos_iniciales/5.jpg"));
            entityManager.persist(cocheFAcademyPrema);

            Coche cocheFAcademyCampos =
                    new Coche("Tatuus", "F4-T421 Campos Racing", "No tiene", "Gasolina", 1200, 2024, 165, true, 68000);
            cocheFAcademyCampos.setCategoria(catFAcademy);
            cocheFAcademyCampos.setImagenPortada(leerImagenInicial("http://localhost:8080/imagenes_productos_iniciales/6.jpg"));
            entityManager.persist(cocheFAcademyCampos);

            Coche cocheFAcademyART =
                    new Coche("Tatuus", "F4-T421 ART Grand Prix", "No tiene", "Gasolina", 600, 2025, 165, false, 79000);
            cocheFAcademyART.setCategoria(catFAcademy);
            cocheFAcademyART.setImagenPortada(leerImagenInicial("http://localhost:8080/imagenes_productos_iniciales/7.jpg"));
            entityManager.persist(cocheFAcademyART);

            // ===== PROTOTIPOS =====
            Coche cochePrototipoFerrari499P =
                    new Coche("Ferrari", "499P", "No tiene", "Hibrido", 2500, 2023, 680, true, 6500000);
            cochePrototipoFerrari499P.setCategoria(catPrototipos);
            cochePrototipoFerrari499P.setImagenPortada(leerImagenInicial("http://localhost:8080/imagenes_productos_iniciales/8.jpg"));
            entityManager.persist(cochePrototipoFerrari499P);

            Coche cochePrototipoPorsche963 =
                    new Coche("Porsche", "963", "No tiene", "Hibrido", 3200, 2023, 680, true, 5200000);
            cochePrototipoPorsche963.setCategoria(catPrototipos);
            cochePrototipoPorsche963.setImagenPortada(leerImagenInicial("http://localhost:8080/imagenes_productos_iniciales/9.jpg"));
            entityManager.persist(cochePrototipoPorsche963);

            Coche cochePrototipoToyotaGR010 =
                    new Coche("Toyota", "GR010 Hybrid", "No tiene", "Hibrido", 4800, 2022, 680, false, 5800000);
            cochePrototipoToyotaGR010.setCategoria(catPrototipos);
            cochePrototipoToyotaGR010.setImagenPortada(leerImagenInicial("http://localhost:8080/imagenes_productos_iniciales/10.jpg"));
            entityManager.persist(cochePrototipoToyotaGR010);

            // ===== GT3 =====
            Coche cocheGT3Porsche =
                    new Coche("Porsche", "911 GT3 R (992)", "No tiene", "Gasolina", 3500, 2023, 565, true, 560000);
            cocheGT3Porsche.setCategoria(catGT3);
            cocheGT3Porsche.setImagenPortada(leerImagenInicial("http://localhost:8080/imagenes_productos_iniciales/11.jpg"));
            entityManager.persist(cocheGT3Porsche);

            Coche cocheGT3Ferrari =
                    new Coche("Ferrari", "296 GT3", "No tiene", "Gasolina", 2800, 2023, 600, true, 620000);
            cocheGT3Ferrari.setCategoria(catGT3);
            cocheGT3Ferrari.setImagenPortada(leerImagenInicial("http://localhost:8080/imagenes_productos_iniciales/12.jpg"));
            entityManager.persist(cocheGT3Ferrari);

            Coche cocheGT3Mercedes =
                    new Coche("Mercedes-AMG", "GT3 Evo", "No tiene", "Gasolina", 6100, 2022, 550, false, 480000);
            cocheGT3Mercedes.setCategoria(catGT3);
            cocheGT3Mercedes.setImagenPortada(leerImagenInicial("http://localhost:8080/imagenes_productos_iniciales/13.jpg"));
            entityManager.persist(cocheGT3Mercedes);

            // ===== SEDAN =====
            Coche cocheSedanBMW =
                    new Coche("BMW", "330i", "4821KLM", "Gasolina", 42000, 2022, 258, true, 38900);
            cocheSedanBMW.setCategoria(catSedan);
            cocheSedanBMW.setImagenPortada(leerImagenInicial("http://localhost:8080/imagenes_productos_iniciales/14.jpg"));
            entityManager.persist(cocheSedanBMW);

            Coche cocheSedanMercedes =
                    new Coche("Mercedes-Benz", "C 200", "7395HNT", "Hibrido", 28500, 2023, 204, true, 44500);
            cocheSedanMercedes.setCategoria(catSedan);
            cocheSedanMercedes.setImagenPortada(leerImagenInicial("http://localhost:8080/imagenes_productos_iniciales/15.jpg"));
            entityManager.persist(cocheSedanMercedes);

            Coche cocheSedanTesla =
                    new Coche("Tesla", "Model 3 Long Range", "2610PDF", "Electrico", 15000, 2023, 441, true, 41900);
            cocheSedanTesla.setCategoria(catSedan);
            cocheSedanTesla.setImagenPortada(leerImagenInicial("http://localhost:8080/imagenes_productos_iniciales/16.jpg"));
            entityManager.persist(cocheSedanTesla);

            // ===== SUV =====
            Coche cocheSUVToyota =
                    new Coche("Toyota", "RAV4 Hybrid", "5538JBV", "Hibrido", 31000, 2022, 222, true, 33800);
            cocheSUVToyota.setCategoria(catSUV);
            cocheSUVToyota.setImagenPortada(leerImagenInicial("http://localhost:8080/imagenes_productos_iniciales/17.jpg"));
            entityManager.persist(cocheSUVToyota);

            Coche cocheSUVVolkswagen =
                    new Coche("Volkswagen", "Tiguan 2.0 TDI", "9074GKS", "Diesel", 54000, 2021, 150, true, 27500);
            cocheSUVVolkswagen.setCategoria(catSUV);
            cocheSUVVolkswagen.setImagenPortada(leerImagenInicial("http://localhost:8080/imagenes_productos_iniciales/18.jpg"));
            entityManager.persist(cocheSUVVolkswagen);

            Coche cocheSUVBMW =
                    new Coche("BMW", "X5 xDrive40i", "1186LRZ", "Gasolina", 22000, 2023, 340, false, 79900);
            cocheSUVBMW.setCategoria(catSUV);
            cocheSUVBMW.setImagenPortada(leerImagenInicial("http://localhost:8080/imagenes_productos_iniciales/19.jpg"));
            entityManager.persist(cocheSUVBMW);

            // ===== CONVERTIBLE =====
            Coche cocheConvertibleMazda =
                    new Coche("Mazda", "MX-5 RF", "3452MTC", "Gasolina", 18000, 2022, 184, true, 33500);
            cocheConvertibleMazda.setCategoria(catConvertible);
            cocheConvertibleMazda.setImagenPortada(leerImagenInicial("http://localhost:8080/imagenes_productos_iniciales/20.jpg"));
            entityManager.persist(cocheConvertibleMazda);

            Coche cocheConvertibleBMW =
                    new Coche("BMW", "Z4 M40i", "6709NBH", "Gasolina", 26000, 2021, 340, true, 56900);
            cocheConvertibleBMW.setCategoria(catConvertible);
            cocheConvertibleBMW.setImagenPortada(leerImagenInicial("http://localhost:8080/imagenes_productos_iniciales/21.jpg"));
            entityManager.persist(cocheConvertibleBMW);

            Coche cocheConvertiblePorsche =
                    new Coche("Porsche", "911 Carrera Cabriolet", "8243FWD", "Gasolina", 14500, 2022, 385, false, 139000);
            cocheConvertiblePorsche.setCategoria(catConvertible);
            cocheConvertiblePorsche.setImagenPortada(leerImagenInicial("http://localhost:8080/imagenes_productos_iniciales/22.jpg"));
            entityManager.persist(cocheConvertiblePorsche);

            // ===== TODOTERRENOS =====
            Coche cocheTodoterrenoJimny =
                    new Coche("Suzuki", "Jimny", "4967DJG", "Gasolina", 12000, 2023, 102, true, 26900);
            cocheTodoterrenoJimny.setCategoria(catTodoterreno);
            cocheTodoterrenoJimny.setImagenPortada(leerImagenInicial("http://localhost:8080/imagenes_productos_iniciales/23.jpg"));
            entityManager.persist(cocheTodoterrenoJimny);

            Coche cocheTodoterrenoWrangler =
                    new Coche("Jeep", "Wrangler Rubicon", "2381KHP", "Gasolina", 33000, 2021, 272, true, 62500);
            cocheTodoterrenoWrangler.setCategoria(catTodoterreno);
            cocheTodoterrenoWrangler.setImagenPortada(leerImagenInicial("http://localhost:8080/imagenes_productos_iniciales/24.jpg"));
            entityManager.persist(cocheTodoterrenoWrangler);

            Coche cocheTodoterrenoLandCruiser =
                    new Coche("Toyota", "Land Cruiser 2.8 D-4D", "7650BSX", "Diesel", 61000, 2020, 204, false, 48900);
            cocheTodoterrenoLandCruiser.setCategoria(catTodoterreno);
            cocheTodoterrenoLandCruiser.setImagenPortada(leerImagenInicial("http://localhost:8080/imagenes_productos_iniciales/25.jpg"));
            entityManager.persist(cocheTodoterrenoLandCruiser);

            // ===== HATCHBACK =====
            Coche cocheHatchbackGolf =
                    new Coche("Volkswagen", "Golf GTI", "1904MZN", "Gasolina", 24000, 2022, 245, true, 36900);
            cocheHatchbackGolf.setCategoria(catHatchback);
            cocheHatchbackGolf.setImagenPortada(leerImagenInicial("http://localhost:8080/imagenes_productos_iniciales/26.jpg"));
            entityManager.persist(cocheHatchbackGolf);

            Coche cocheHatchbackLeon =
                    new Coche("Seat", "Leon FR 1.5 eTSI", "5827JTK", "Hibrido", 19500, 2023, 150, true, 25400);
            cocheHatchbackLeon.setCategoria(catHatchback);
            cocheHatchbackLeon.setImagenPortada(leerImagenInicial("http://localhost:8080/imagenes_productos_iniciales/27.jpg"));
            entityManager.persist(cocheHatchbackLeon);

            Coche cocheHatchbackCivic =
                    new Coche("Honda", "Civic e:HEV", "3318GLB", "Hibrido", 9000, 2023, 184, false, 33200);
            cocheHatchbackCivic.setCategoria(catHatchback);
            cocheHatchbackCivic.setImagenPortada(leerImagenInicial("http://localhost:8080/imagenes_productos_iniciales/28.jpg"));
            entityManager.persist(cocheHatchbackCivic);

            // ===== DEPORTIVO =====
            Coche cocheDeportivoAlpine =
                    new Coche("Alpine", "A110 S", "6042HDV", "Gasolina", 11000, 2022, 300, true, 72500);
            cocheDeportivoAlpine.setCategoria(catDeportivo);
            cocheDeportivoAlpine.setImagenPortada(leerImagenInicial("http://localhost:8080/imagenes_productos_iniciales/29.jpg"));
            entityManager.persist(cocheDeportivoAlpine);

            Coche cocheDeportivoMustang =
                    new Coche("Ford", "Mustang GT 5.0 V8", "8156NKF", "Gasolina", 17000, 2022, 450, true, 61900);
            cocheDeportivoMustang.setCategoria(catDeportivo);
            cocheDeportivoMustang.setImagenPortada(leerImagenInicial("http://localhost:8080/imagenes_productos_iniciales/30.jpg"));
            entityManager.persist(cocheDeportivoMustang);

            Coche cocheDeportivoGR86 =
                    new Coche("Toyota", "GR86", "2739PJR", "Gasolina", 8000, 2023, 234, false, 38900);
            cocheDeportivoGR86.setCategoria(catDeportivo);
            cocheDeportivoGR86.setImagenPortada(leerImagenInicial("http://localhost:8080/imagenes_productos_iniciales/31.jpg"));
            entityManager.persist(cocheDeportivoGR86);

            // ===== SUPERDEPORTIVO =====
            Coche cocheSuperdeportivoHuracan =
                    new Coche("Lamborghini", "Huracan EVO", "9413FXC","Gasolina", 12000, 2021, 640, true, 235000);
            cocheSuperdeportivoHuracan.setCategoria(catSuperdeportivo);
            cocheSuperdeportivoHuracan.setImagenPortada(leerImagenInicial("http://localhost:8080/imagenes_productos_iniciales/32.jpg"));
            entityManager.persist(cocheSuperdeportivoHuracan);

            Coche cocheSuperdeportivoFerrari =
                    new Coche("Ferrari", "F8 Tributo", "1675DRT", "Gasolina", 9500, 2022, 720, true, 295000);
            cocheSuperdeportivoFerrari.setCategoria(catSuperdeportivo);
            cocheSuperdeportivoFerrari.setImagenPortada(leerImagenInicial("http://localhost:8080/imagenes_productos_iniciales/33.jpg"));
            entityManager.persist(cocheSuperdeportivoFerrari);

            Coche cocheSuperdeportivoMcLaren =
                    new Coche("McLaren", "720S", "4098HGW", "Gasolina", 16000, 2020, 720, false, 219000);
            cocheSuperdeportivoMcLaren.setCategoria(catSuperdeportivo);
            cocheSuperdeportivoMcLaren.setImagenPortada(leerImagenInicial("http://localhost:8080/imagenes_productos_iniciales/34.jpg"));
            entityManager.persist(cocheSuperdeportivoMcLaren);

            // ===== HIPERDEPORTIVO =====
            Coche cocheHiperdeportivoChiron =
                    new Coche("Bugatti", "Chiron Sport", "7284KNS", "Gasolina", 3500, 2021, 1500, true, 3200000);
            cocheHiperdeportivoChiron.setCategoria(catHiperdeportivo);
            cocheHiperdeportivoChiron.setImagenPortada(leerImagenInicial("http://localhost:8080/imagenes_productos_iniciales/35.jpg"));
            entityManager.persist(cocheHiperdeportivoChiron);

            Coche cocheHiperdeportivoNevera =
                    new Coche("Rimac", "Nevera", "5961LBM", "Electrico", 1200, 2023, 1914, true, 2400000);
            cocheHiperdeportivoNevera.setCategoria(catHiperdeportivo);
            cocheHiperdeportivoNevera.setImagenPortada(leerImagenInicial("http://localhost:8080/imagenes_productos_iniciales/36.jpg"));
            entityManager.persist(cocheHiperdeportivoNevera);

            Coche cocheHiperdeportivoJesko =
                    new Coche("Koenigsegg", "Jesko", "3827MWG", "Gasolina", 800, 2023, 1600, false, 3000000);
            cocheHiperdeportivoJesko.setCategoria(catHiperdeportivo);
            cocheHiperdeportivoJesko.setImagenPortada(leerImagenInicial("http://localhost:8080/imagenes_productos_iniciales/37.jpg"));
            entityManager.persist(cocheHiperdeportivoJesko);

            // ===== RALLY =====
            Coche cocheRallyToyota =
                    new Coche("Toyota", "GR Yaris Rally1", "No tiene", "Hibrido", 1500, 2024, 500, true, 750000);
            cocheRallyToyota.setCategoria(catRally);
            cocheRallyToyota.setImagenPortada(leerImagenInicial("http://localhost:8080/imagenes_productos_iniciales/38.jpg"));
            entityManager.persist(cocheRallyToyota);

            Coche cocheRallyHyundai =
                    new Coche("Hyundai", "i20 N Rally1", "No tiene", "Hibrido", 2200, 2023, 500, true, 720000);
            cocheRallyHyundai.setCategoria(catRally);
            cocheRallyHyundai.setImagenPortada(leerImagenInicial("http://localhost:8080/imagenes_productos_iniciales/39.jpg"));
            entityManager.persist(cocheRallyHyundai);

            Coche cocheRallyFord =
                    new Coche("Ford", "Puma Rally1", "No tiene", "Hibrido", 3100, 2022, 500, false, 700000);
            cocheRallyFord.setCategoria(catRally);
            cocheRallyFord.setImagenPortada(leerImagenInicial("http://localhost:8080/imagenes_productos_iniciales/40.jpg"));
            entityManager.persist(cocheRallyFord);

            // ===== PICK-UP =====
            Coche cochePickUpRanger =
                    new Coche("Ford", "Ranger Raptor", "6530JHC", "Diesel", 21000, 2023, 292, true, 69900);
            cochePickUpRanger.setCategoria(catPickUp);
            cochePickUpRanger.setImagenPortada(leerImagenInicial("http://localhost:8080/imagenes_productos_iniciales/41.jpg"));
            entityManager.persist(cochePickUpRanger);

            Coche cochePickUpHilux =
                    new Coche("Toyota", "Hilux 2.8 D-4D", "9847GSB", "Diesel", 38000, 2022, 204, true, 42500);
            cochePickUpHilux.setCategoria(catPickUp);
            cochePickUpHilux.setImagenPortada(leerImagenInicial("http://localhost:8080/imagenes_productos_iniciales/42.jpg"));
            entityManager.persist(cochePickUpHilux);

            Coche cochePickUpF150 =
                    new Coche("Ford", "F-150 Raptor R", "2056PLK", "Gasolina", 7500, 2023, 720, false, 129000);
            cochePickUpF150.setCategoria(catPickUp);
            cochePickUpF150.setImagenPortada(leerImagenInicial("http://localhost:8080/imagenes_productos_iniciales/43.jpg"));
            entityManager.persist(cochePickUpF150);

            // ===== MONOVOLUMEN =====
            Coche cocheMonovolumenMercedes =
                    new Coche("Mercedes-Benz", "Clase V 300 d", "4379HZT", "Diesel", 47000, 2021, 239, true, 59800);
            cocheMonovolumenMercedes.setCategoria(catMonovolumen);
            cocheMonovolumenMercedes.setImagenPortada(leerImagenInicial("http://localhost:8080/imagenes_productos_iniciales/44.jpg"));
            entityManager.persist(cocheMonovolumenMercedes);

            Coche cocheMonovolumenTouran =
                    new Coche("Volkswagen", "Touran 2.0 TDI", "8612KRD", "Diesel", 68000, 2020, 150, true, 22900);
            cocheMonovolumenTouran.setCategoria(catMonovolumen);
            cocheMonovolumenTouran.setImagenPortada(leerImagenInicial("http://localhost:8080/imagenes_productos_iniciales/45.jpg"));
            entityManager.persist(cocheMonovolumenTouran);

            Coche cocheMonovolumenSMax =
                    new Coche("Ford", "S-MAX Hybrid", "1523LGN", "Hibrido", 29000, 2022, 190, false, 35600);
            cocheMonovolumenSMax.setCategoria(catMonovolumen);
            cocheMonovolumenSMax.setImagenPortada(leerImagenInicial("http://localhost:8080/imagenes_productos_iniciales/46.jpg"));
            entityManager.persist(cocheMonovolumenSMax);


            //vamos a registrar unos usuarios y algun carrito
            Usuario u1 = new Usuario();
            u1.setNombre("Sergio");
            u1.setApellidos("Toribio González");
            u1.setEmail("sergio@gmail.com");
            u1.setPass("12345");
            u1.setFechaNacimiento("03/12/1999");
            u1.setNumTelefono("623456789");
            u1.setAvatar(leerImagenInicial("http://localhost:8080/imagenes_productos_iniciales/avatar.png"));
            entityManager.persist(u1);

            Usuario u2 = new Usuario();
            u2.setNombre("Belen");
            u2.setApellidos("Rodriguez Sanjuan");
            u2.setEmail("belen@gmail.com");
            u2.setPass("12345");
            u2.setFechaNacimiento("11/10/2000");
            u2.setNumTelefono("645974236");
            u2.setAvatar(leerImagenInicial("http://localhost:8080/imagenes_productos_iniciales/avatar.png"));
            entityManager.persist(u2);

            Usuario u3 = new Usuario();
            u3.setNombre("Vicky");
            u3.setApellidos("Marcos");
            u3.setEmail("vicky@gmail.com");
            u3.setPass("12345");
            u3.setFechaNacimiento("15/6/1996");
            u3.setNumTelefono("696258741");
            u3.setAvatar(leerImagenInicial("http://localhost:8080/imagenes_productos_iniciales/avatar.png"));
            entityManager.persist(u3);


            //vamos a preparar un carrito
            Carrito c1 = new Carrito();
            c1.setUsuario(u1);
            entityManager.persist(c1);


            Carrito c2 = new Carrito();
            c2.setUsuario(u2);
            entityManager.persist(c2);


            ProductoCarrito pc1 = new ProductoCarrito();
            pc1.setCarrito(c1);
            pc1.setCoche(cocheF1AstonMartin);
            pc1.setCantidad(1);
            entityManager.persist(pc1);


            ProductoCarrito pc2 = new ProductoCarrito();
            pc2.setCarrito(c1);
            pc2.setCoche(cocheGT3Porsche);
            pc2.setCantidad(3);
            entityManager.persist(pc2);

            ProductoCarrito pc3 = new ProductoCarrito();
            pc2.setCarrito(c2);
            pc2.setCoche(cocheSuperdeportivoFerrari);
            pc2.setCantidad(2);
            entityManager.persist(pc3);


            //vamos tambien a registrar un pedido
            //a partir del carrito
            servicioPedidos.procesarPaso1(
                    "Pedro Fernandez","Arguelles 12",
                    "Madrid", "28030", "684569325", "Madrid" , u1.getId());
            servicioPedidos.procesarPaso2(
                    "Pedro Fernandez Del Soto","12345678912",
                    "VISA", "28/10/2028", "339" , u1.getId()
            );
            servicioPedidos.procesarPaso3(
                    "Recogida en concesionario","Madrid",
                    true, "28/10/2028", u1.getId()
            );
            servicioPedidos.confirmarPedido(u1.getId());
            //ya tenemos un pedido en bd :)



            //registrar 100 productos de prueba, para probar paginacion
            String[] marcas = {"Toyota", "Ford", "Seat", "Audi", "BMW", "Renault", "Volkswagen", "Peugeot"};
            String[] tipos = {"Gasolina", "Diesel", "Hibrido", "Electrico"};

            for (int i = 0; i < 100; i++) {
                Coche vNuevo = new Coche();

                // Texto (Campos obligatorios con restricciones de tamaño)
                vNuevo.setMarca(marcas[i % marcas.length]);
                vNuevo.setModelo("Modelo " + (i + 1));
                vNuevo.setMatricula(String.format("%04dABC", (i + 1) % 9999)); // Ej: 0001ABC (7 caracteres, cumple entre 6 y 8)
                vNuevo.setTipo(tipos[i % tipos.length]); // Ej: Hibrido (cumple entre 3 y 10 caracteres)
                vNuevo.setSku("SKU-" + (1000 + i));

                // Numéricos (Cumpliendo los mínimos exigidos por @Min)
                vNuevo.setKilometros(10000 + (i * 500)); // Min: 3
                vNuevo.setAño(2000 + (i % 25));          // Min: 1900, Max: 2027
                vNuevo.setCv(90 + (i % 200));            // Min: 5
                vNuevo.setPrecio(5000 + (i * 150));      // Min: 10

                // Booleano
                vNuevo.setStock(i % 2 == 0);

                // Relación y Fotos
                vNuevo.setCategoria(sinCategoria);
                vNuevo.setImagenPortada(leerImagenInicial(
                        "http://localhost:8080/imagenes_productos_iniciales/default.jpg"));

                entityManager.persist(vNuevo);
            }



            // una vez realizados los registros iniciales
            // guardo un registro en la tabla Configuracion
            // indicando que esta ok
            configuracion = new Configuracion();
            configuracion.setRegistrosIniciales(true);
            entityManager.persist(configuracion);

        } // END IF
    } //END prepararRegistrosIniciales


    private byte[] leerImagenInicial(String ruta_imagen){

        byte[] info = null;

        try {
            URL url = new URL(ruta_imagen);
            info = IOUtils.toByteArray(url);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return info;

    }// END LEER IMAGEN INICIAL


}// END CLASS
