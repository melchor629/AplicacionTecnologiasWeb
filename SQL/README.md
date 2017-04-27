# ¿Como meter todos los SQL?

Insertar en el siguiente orden los SQL

1. Con tu usuario `root`, añadir [ModeloTecnoweb.sql][1]
2. Luego añadir [Usuario.sql][2]
3. Por ultimo, añadir [Datos.sql][3]

# ¿Como conecto la aplicación empresarial a la base de datos?

- **Base de datos**: `tecnoweb`
- **Usuario**: `tecnoweb`
- **Contraseña**: `reflexiona`

Para arreglar el problema que hay en Windows con la codificación de la base de datos, _(soon)_ ...

# ¿Que hacen los SQL?
## ModeloTecnoweb.sql

Contiene la estructura de la base de datos. Viene generada por el modelo, el archivo [ModeloTecnoweb.mwb][4].

## Usuario.sql

Contiene el usuario `tecnoweb` y los permisos correspondientes. Este es el usuario que usaremos en nuestra aplicación. Tiene los permisos restringidos por seguridad. Debería bastar para la aplicación. No usar este usuario para manipular cosas con el MySQL Workbench, usado vuestros usuarios `root`.

## Datos.sql

Contiene datos para ir probando mientras desarrollamos. Para no tener que ir creando cosas y tal.


  [1]: https://github.com/melchor629/AplicacionTecnologiasWeb/raw/master/SQL/ModeloTecnoweb.sql
  [2]: https://github.com/melchor629/AplicacionTecnologiasWeb/raw/master/SQL/Usuario.sql
  [3]: https://github.com/melchor629/AplicacionTecnologiasWeb/raw/master/SQL/Datos.sql
  [4]: https://github.com/melchor629/AplicacionTecnologiasWeb/blob/master/SQL/ModeloTecnoweb.mwb
