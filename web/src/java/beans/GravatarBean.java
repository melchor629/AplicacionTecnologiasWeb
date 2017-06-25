package beans;

import app.entity.Usuario;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;

/**
 * @author Antonio Ángel Cruzado Castillo
 * @author Melchor Alejo Garau Madrigal
 */
@Singleton
@ManagedBean
public class GravatarBean {

    private String imagenDefecto;

    @PostConstruct
    public void init() {
        try {
            InputStreamReader r = new InputStreamReader(FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/WEB-INF/fotoPerfil.txt"));
            StringBuilder sb = new StringBuilder();
            char cbuf[] = new char[1024];
            int readChars;
            while((readChars = r.read(cbuf)) != -1) {
                sb.append(cbuf, 0, readChars);
            }
            imagenDefecto = sb.toString();
        } catch(IOException e) {
            imagenDefecto = "";
        }
    }

    // Devuelve una URL de la imagen por defecto
    public String imagenPorDefecto(Usuario usuario) {
        if(usuario != null && usuario.getCorreo() != null) {
            // Se usa gravatar a partir del correo del usuario
            String correo = usuario.getCorreo().toLowerCase().trim();
            return "https://www.gravatar.com/avatar/" + md5Hash(correo) + "?d=retro&s=400";
        } else {
            return imagenDefecto;
        }
    }

    private String md5Hash(String str) {

        String retorno = "";

        try {
            byte[] bytesOfMessage = str.getBytes("UTF-8");
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] theDigest = md.digest(bytesOfMessage);

            for (byte b : theDigest) {
                retorno += String.format("%2x", b);
            }
        } catch (Exception ex) {
            retorno = "";
        }

        return retorno;
    }
}
