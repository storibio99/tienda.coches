package com.sergio.tienda.coches.constantesSQL;


public class ConstantesSQL {

    public final static String SQL_OBTENER_COCHES_JSON =
            "SELECT co.id, co.marca, co.modelo, co.cv, co.precio, co.categoria_id, cat.nombre AS categoria FROM coche AS co, categoria AS cat WHERE co.categoria_id = cat.id AND LOWER(co.marca) LIKE LOWER(:marca) LIMIT :comienzo, 9";

    public final static String SQL_OBTENER_COCHE_JSON =
            "SELECT co.id, co.marca, co.modelo, co.matricula, co.tipo, co.kilometros, co.año, co.cv, co.stock, co.precio, co.categoria_id, cat.nombre AS categoria FROM coche AS co, categoria AS cat WHERE co.categoria_id = cat.id AND co.id = :id";


    public static final String SQL_OBTENER_PRODUCTOS_CARRITO_JSON =
            "SELECT co.id AS coche_id, co.precio AS precio, co.marca, co.modelo, pc.cantidad " +
                    "FROM coche AS co, producto_carrito AS pc " +
                    "WHERE co.id = pc.coche_id AND pc.carrito_id = :carrito_id ";

    public static final String SQL_BORRAR_PRODUCTOS_CARRITO =
            "delete from producto_carrito where carrito_id = :carrito_id";

    public static final String SQL_OBTENER_TOTAL_COCHES =
            "select count(id) from coche where lower(marca) like :marca";

}
