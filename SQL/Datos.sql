INSERT INTO `Usuario` (`id`, `Nombre`, `Apellidos`, `Twitter`, `Instagram`, `Web`, `Foto`, `NombreUsuario`, `Contraseña`, `Correo`)
VALUES
    (1, 'Manolito', 'Casual', 'ManuelNotV', 'manuelnotv', NULL, 'https://scontent-mad1-1.cdninstagram.com/t51.2885-19/s320x320/14592033_1468653206484196_4673932653030801408_a.jpg', 'manuelnotv', 'd8c61debc1ce96f6cfcc809c366eb844510f976b3353913f51e0aca0d8afc833', 'veredasmanuel@gmail.com'),
    (2, 'Jose', 'Pato', NULL, NULL, NULL, NULL, 'jose', 'f06aa1a6bd55c88b4f7f0db42eccb2ca5ca62d3af9c74647b4ce9d37b6386aa4', 'jose_mj96@hotmail.com'),
    (3, 'Antonio Angel', 'Cruzado Castillo', NULL, 'a.angelcc', 'http://www.antonioangel.xyz', 'https://scontent-mad1-1.cdninstagram.com/t51.2885-19/s320x320/12519652_920506371400433_502767576_a.jpg', 'a.angelcc', '4e9fc9ab1aa53f31c312648f11a61455436897f5eb6348b1d240f9dd36fbdf07', 'a.angelcruzado@gmail.com'),
    (4, 'Eduardo', 'Guidet', 'EduardoDobleH', 'eduardodobleh', NULL, 'https://scontent-mad1-1.cdninstagram.com/t51.2885-19/s320x320/13437174_1621256951521609_1330748942_a.jpg', 'eduardodobleh', '8b932958ace3766bfb830efa7729b92e5566daf9b2a959ecdb6ad85196b26248', 'eduardogj96@gmail.com'),
    (5, 'Melchor Alejo', 'Garau Madrigal', 'Melchor629', 'melchor9000', 'http://melchor9000.me', 'https://pbs.twimg.com/profile_images/842841793842634752/mHCFmjI5.jpg', 'melchor9000', 'b810896d4fb913646c5dc6b02d3bc231972dbd899c6d1a5ccda0d347763da326', 'melchor9000@gmail.com'),
    (6, 'Rodrigo', 'Represa Represa', NULL, 'rodri_rr', NULL, 'https://scontent-mad1-1.cdninstagram.com/t51.2885-19/10809643_574618769335088_1009160558_a.jpg', 'rodri_rr', 'ee5234a9bb638d210366bbb7e8fe266fe76bb52aee8498fbfb5ba321a2788ef9', 'rodrigorepresa@gmail.com'),
    (7, 'Francis', 'Reyes Sánchez', 'francis1196', 'francis8rs', NULL, 'https://scontent-mad1-1.cdninstagram.com/t51.2885-19/s320x320/17267585_781564748686947_637854320502505472_a.jpg', 'francis1196', 'd942b481e68f77344cc4ce10e991861560f342f0c7b270fbc13d066be72c8fd5', 'francis8rs@gmail.com');

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
    (3, '2017-03-28 00:00:00', '2017-03-31 00:00:00', 'JPA', 'Cacharreando', NULL);

INSERT INTO `Mensaje` (`idEmisor`, `idReceptor`, `Texto`, `Leido`, `Titulo`)
VALUES
    (2, 3, '**Cacharreo** _intensifies_', 0, 'El'),
    (2, 5, 'Afétitate la barba que estas mu feo', 0, 'Casual'),
    (3, 7, 'icono quitado', 0, '¡Eh Francis!, mira'),
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
