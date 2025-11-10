-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 10-11-2025 a las 17:20:19
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `inasistencias_docentes`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `administrativo`
--

CREATE TABLE `administrativo` (
  `CI` varchar(20) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `apellido` varchar(50) NOT NULL,
  `usuario` varchar(30) NOT NULL,
  `contrasena` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `administrativo`
--

INSERT INTO `administrativo` (`CI`, `nombre`, `apellido`, `usuario`, `contrasena`) VALUES
('4.321.654-8', 'Fabricio', 'Alvez', 'falvez', 'fabricio123'),
('49387120', 'Camila', 'Pérez', 'cperez', 'ca2025'),
('5.742.915-4', 'Roman', 'Rodriguez', 'rrodriguez', 'ro1234'),
('5.982.334-5', 'Matias', 'Santillan', 'msantillan', 'santillan223'),
('50223311', 'Brisa', 'Fernández', 'bfernandez', 'bf123'),
('57429154', 'Román', 'Rodríguez', 'admin', '1234');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `docente`
--

CREATE TABLE `docente` (
  `CI` varchar(20) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `apellido` varchar(50) NOT NULL,
  `grupo` varchar(30) DEFAULT NULL,
  `turno` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `docente`
--

INSERT INTO `docente` (`CI`, `nombre`, `apellido`, `grupo`, `turno`) VALUES
('4.125.698-7', 'Leandro', 'Lopez', '2MC', 'Matutino'),
('4.857.963-2', 'Sofia', 'Fernandez', '1BI', 'Vespertino'),
('456789', 'Juan', 'Pérez', '5°A', 'Matutino'),
('45678910', 'Sofía', 'Gómez', '6°B', 'Matutino'),
('47839210', 'María', 'López', '4°B', 'Vespertino'),
('5.487.213-1', 'Lucia', 'Pereyra', '4TI', 'Vespertino'),
('5.632.874-9', 'Martin', 'Cabrera', '3MC', 'Nocturno'),
('5.698.321-4', 'Marcela', 'Mederos', '2MC', 'Matutino'),
('50123941', 'Juan', 'Pérez', '5°A', 'Matutino'),
('50987654', 'Carlos', 'García', '3°A', 'Nocturno'),
('52001122', 'Lucía', 'Martínez', '2°B', 'Matutino');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `registro`
--

CREATE TABLE `registro` (
  `id` int(11) NOT NULL,
  `tipo` varchar(30) NOT NULL,
  `motivo` varchar(100) NOT NULL,
  `fecha_inicio` date NOT NULL,
  `fecha_fin` date NOT NULL,
  `CI_docente` varchar(20) NOT NULL,
  `CI_administrativo` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `registro`
--

INSERT INTO `registro` (`id`, `tipo`, `motivo`, `fecha_inicio`, `fecha_fin`, `CI_docente`, `CI_administrativo`) VALUES
(15, 'Enfermedad', 'Gripe estacional', '2025-10-15', '2025-10-18', '4.125.698-7', '4.321.654-8'),
(16, 'Concurso', 'Participación en concurso docente', '2025-09-22', '2025-09-24', '5.698.321-4', '5.982.334-5'),
(18, 'Licencia medica', 'Reposo', '2025-11-08', '2025-11-15', '47839210', '5.742.915-4'),
(20, 'licencia medica', 'reposo', '2025-11-09', '2025-11-15', '4.125.698-7', '5.742.915-4');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `administrativo`
--
ALTER TABLE `administrativo`
  ADD PRIMARY KEY (`CI`);

--
-- Indices de la tabla `docente`
--
ALTER TABLE `docente`
  ADD PRIMARY KEY (`CI`);

--
-- Indices de la tabla `registro`
--
ALTER TABLE `registro`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_REGISTRO_DOCENTE` (`CI_docente`),
  ADD KEY `FK_REGISTRO_ADMIN` (`CI_administrativo`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `registro`
--
ALTER TABLE `registro`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `registro`
--
ALTER TABLE `registro`
  ADD CONSTRAINT `FK_REGISTRO_ADMIN` FOREIGN KEY (`CI_administrativo`) REFERENCES `administrativo` (`CI`) ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_REGISTRO_DOCENTE` FOREIGN KEY (`CI_docente`) REFERENCES `docente` (`CI`) ON UPDATE CASCADE,
  ADD CONSTRAINT `registro_ibfk_1` FOREIGN KEY (`CI_docente`) REFERENCES `docente` (`CI`),
  ADD CONSTRAINT `registro_ibfk_2` FOREIGN KEY (`CI_administrativo`) REFERENCES `administrativo` (`CI`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
