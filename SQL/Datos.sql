SET NAMES 'utf8mb4';

INSERT INTO `Usuario` (`id`, `Nombre`, `Apellidos`, `Twitter`, `Instagram`, `Web`, `Foto`, `NombreUsuario`, `Contraseña`, `Correo`)
VALUES
    (1, 'Manolito', 'Casual', 'ManuelNotV', 'manuelnotv', NULL, NULL, 'manuelnotv', '44b7cf02cf5b79b9a85914fbad2ff6d53bb134fdf2b8d1c93f68f88c53f0d478', 'veredasmanuel@gmail.com'),
    (2, 'Jose', 'Pato', NULL, NULL, NULL, 'assets/img/IMG-20150908-WA0011.jpg', 'jose', 'bbe3e80d04d1213630d3ccf6786f7c38b7f3aa90152ae0c0004db827b2b72b03', 'jose_mj96@hotmail.com'),
    (3, 'Antonio Angel', 'Cruzado Castillo', NULL, 'a.angelcc', 'http://www.antonioangel.xyz', 'https://scontent-mad1-1.cdninstagram.com/t51.2885-19/s320x320/12519652_920506371400433_502767576_a.jpg', 'a.angelcc', 'c7852be47c1c5e6d81456da6aed1336e1d65c1ad31429dc7b986276489c544f7', 'a.angelcruzado@gmail.com'),
    (4, 'Eduardo', 'Guidet', 'EduardoDobleH', 'eduardodobleh', NULL, 'https://scontent-mad1-1.cdninstagram.com/t51.2885-19/s320x320/13437174_1621256951521609_1330748942_a.jpg', 'eduardodobleh', 'b0034b9d5b05152488f834aef3b5607dd28d4526e3d0c9b33c851bc45151984e', 'eduardogj96@gmail.com'),
    (5, 'Melchor Alejo', 'Garau Madrigal', 'Melchor629', 'melchor9000', 'http://melchor9000.me', 'https://pbs.twimg.com/profile_images/842841793842634752/mHCFmjI5.jpg', 'melchor9000', '6bdbaaefbd6324af7a2e799f3d7a0c06c880981b08ebf9c6dec49b6ec7353df7', 'melchor9000@gmail.com'),
    (6, 'Rodrigo', 'Represa Represa', NULL, 'rodri_rr', NULL, 'https://scontent-mad1-1.cdninstagram.com/t51.2885-19/10809643_574618769335088_1009160558_a.jpg', 'rodri_rr', '9c8da44b6db95e30d04f2fefa4b336a1014e37afda8b895f3bd5b2c467ad66db', 'rodrigorepresa@gmail.com'),
    (7, 'Francis', 'Reyes Sánchez', 'francis1196', 'francis8rs', NULL, 'https://scontent-mad1-1.cdninstagram.com/t51.2885-19/s320x320/17267585_781564748686947_637854320502505472_a.jpg', 'francis1196', 'aa5d2b96985a8e1d6747e733d83bdf7bdd96a615a5b598c438d0a730ded54dad', 'francis8rs@gmail.com');

INSERT INTO `Aficiones` (`idUsuario`, `Nombre`)
VALUES
    (1, 'Casual'),
    (2, 'CS:GO'),
    (2, 'Overwatch'),
    (3, 'Adruino'),
    (3, 'Raspi'),
    (3, 'El cacharreo'),
    (4, 'Cantautor'),
    (4, 'Hearthstone'),
    (5, 'Programar en Hardcore'),
    (6, 'Muñeca en la mierda'),
    (5, 'Laif haks'),
    (6, 'Futbol'),
    (7, 'Suspender'),
    (7, 'Atletismo');

INSERT INTO `Contactos` (`idUsuario`, `Amigo`)
VALUES
    (2, 5),
    (3, 5),
    (3, 7),
    (4, 5),
    (4, 6),
    (4, 7),
    (5, 6),
    (5, 7),
    (6, 7),
    (7, 2);

INSERT INTO `Estudios` (`IdUsuario`, `Fecha_Comienzo`, `Fecha_Finalizacion`, `Descripcion`, `Ubicacion`)
VALUES
    (1, '2014-11-13 00:00:00', NULL, 'Universidad', 'Málaga'),
    (2, '2014-11-13 00:00:00', NULL, 'Universidad', 'Málaga'),
    (3, '2014-11-13 00:00:00', NULL, 'Universidad', 'Málaga'),
    (4, '2014-11-13 00:00:00', NULL, 'Universidad', 'Málaga'),
    (5, '2014-11-13 00:00:00', NULL, 'Universidad', 'Málaga'),
    (6, '2014-11-13 00:00:00', NULL, 'Universidad', 'Málaga'),
    (7, '2014-11-13 00:00:00', NULL, 'Universidad', 'Málaga');

INSERT INTO `ExperienciaLaboral` (`idUsuario`, `Fecha_Comienzo`, `Fecha_Finalizacion`, `Empresa`, `Puesto`, `Web_Empresa`)
VALUES
    (3, '2017-03-28 00:00:00', '2017-03-31 00:00:00', 'JPA', 'Cacharreando', NULL),
    (4, '2017-03-28 00:00:00', '2017-03-31 00:00:00', 'JPA', 'Encantando', NULL),
    (6, '2017-03-28 00:00:00', '2017-03-31 00:00:00', 'JPA', 'Hablando', NULL),
    (7, '2017-03-28 00:00:00', '2017-03-31 00:00:00', 'JPA', 'Carpa', NULL),
    (1, '2000-01-02 03:04:05', NULL, 'La vida', 'Casual profesional', NULL);

INSERT INTO `Mensaje` (`idEmisor`, `idReceptor`, `Texto`, `Leido`, `Titulo`)
VALUES
    (2, 3, '**Cacharreo** _intensifies_', 0, 'El'),
    (2, 5, 'Afétitate la barba que estas mu feo', 0, 'Casual'),
    (3, 7, '🖕🏼', 0, '¡Eh Francis!, mira'),
    (4, 6, 'Holis', 1, 'Holis'),
    (5, 3, 'Céntrate copón', 0, 'sos'),
    (5, 4, 'Toques molt bé sa guitarreta :)', 0, 'Sa guitarreta'),
    (5, 6, 'Que tal está usted del vraso??????', 1, 'El vraso'),
    (5, 7, 'Vas a suspender **jeje**', 0, 'Holis'),
    (7, 6, 'Illo que ase?', 1, 'Illo');

INSERT INTO `PeticionAmistad` (`idEmisor`, `idReceptor`, `Mensaje`)
VALUES
    (1, 2, 'Jeje'),
    (1, 3, NULL),
    (1, 4, NULL),
    (1, 5, NULL),
    (1, 6, NULL),
    (1, 7, 'Vas a suspender'),
    (2, 4, 'Edu no seas cabron'),
    (3, 4, NULL),
    (3, 6, NULL);
