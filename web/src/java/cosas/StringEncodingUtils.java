package cosas;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * Contiene utilidades para convertir Strings a UTF-8.<br/>
 * Para ver mas información sobre que es la codificación de texto,
 * mirad <a href="https://en.wikipedia.org/wiki/Character_encoding">Wikipedia</a>
 * Creado por Melchor Alejo Garau Madrigal
 */
public class StringEncodingUtils {

    /**
     * Dado el objeto {@code request}, obtiene el parámetro {@code name} en UTF-8
     * (la codificación usada por la BD y la web), o devuelve {@code null} si no
     * existe algún parámetro con ese nombre.
     * @param request Objeto request del Servlet
     * @param name Nombre del parámetro
     * @return El parámetro en UTF-8 o {@code null}
     */
    public static String getParameter(HttpServletRequest request, String name) {
        String parameter = request.getParameter(name);
        if(parameter != null) {
            return toUtf8(request.getParameter(name));
        } else {
            return null;
        }
    }

    /**
     * Dado el objeto {@code request}, obtiene la lista de parámetros
     * {@code name} en UTF-8 (la codificación usada por la BD y la web), o devuelve
     * {@code null} si no existe alguna lista de parámetros con ese nombre.
     * @param request Objeto request del Servlet
     * @param name Nombre del parámetro
     * @return La lista de valores en UTF-8 o {@code null}
     */
    public static String[] getParameterValues(HttpServletRequest request, String name) {
        String array[] = request.getParameterValues(name);
        if(array != null) {
            String converted[] = new String[array.length];
            for(int i = 0; i < array.length; i++) {
                converted[i] = toUtf8(array[i]);
            }
            return converted;
        } else {
            return null;
        }
    }

    /**
     * Convierte un {@link String} codificado en ISO-8859-1 a UTF-8.
     * @param string String en ISO-8859-1
     * @return El {@code string}  en UTF-8
     */
    public static String toUtf8(String string) {
        try {
            return new String(string.getBytes("ISO-8859-1"), "UTF-8");
        } catch(UnsupportedEncodingException e) {
            return null;
        }
    }

}
