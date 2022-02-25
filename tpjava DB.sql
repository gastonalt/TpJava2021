-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 25-02-2022 a las 18:58:49
-- Versión del servidor: 10.4.21-MariaDB
-- Versión de PHP: 8.0.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `tpjava`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `componentes`
--

CREATE TABLE `componentes` (
  `id` int(11) NOT NULL,
  `descripcion` text NOT NULL,
  `tamano` int(11) DEFAULT NULL,
  `socket` int(11) DEFAULT NULL,
  `consumo` int(11) DEFAULT NULL,
  `precio` int(11) DEFAULT NULL,
  `marca` int(11) DEFAULT NULL,
  `tipo` int(11) DEFAULT NULL,
  `borrado` int(11) DEFAULT NULL,
  `stock` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `componentes`
--

INSERT INTO `componentes` (`id`, `descripcion`, `tamano`, `socket`, `consumo`, `precio`, `marca`, `tipo`, `borrado`, `stock`) VALUES
(10, 'Mother B85MG', 1, 1, 220, 13000, 1, 5, 0, 6),
(11, 'GT 710 2GB', 1, 1, 220, 5000, 2, 2, 0, 6),
(12, 'Intel i9 9900K', 1, 1, 231, 333, 3, 1, 0, 6),
(14, 'Memoria 8GB DDR4', 1, 1, 123, 333, 3, 4, 0, 6),
(15, 'Gabinete corsair', 1, 1, 0, 0, 1, 7, 0, 6),
(17, 'Fuente 500w 80+', 1, 1, 500, 43, 3, 6, 0, 6);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `marcas`
--

CREATE TABLE `marcas` (
  `id` int(11) NOT NULL,
  `descripcion` text NOT NULL,
  `borrado` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `marcas`
--

INSERT INTO `marcas` (`id`, `descripcion`, `borrado`) VALUES
(1, 'ASUS', 0),
(2, 'NVIDIA', 0),
(3, 'MSI', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pcarmadas`
--

CREATE TABLE `pcarmadas` (
  `id` int(11) NOT NULL,
  `cpu` int(11) NOT NULL,
  `gpu` int(11) NOT NULL,
  `memoria` int(11) NOT NULL,
  `motherboard` int(11) NOT NULL,
  `gabinete` int(11) NOT NULL,
  `fuente` int(11) NOT NULL,
  `borrado` int(11) NOT NULL,
  `entregado` int(11) NOT NULL,
  `observaciones` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `pcarmadas`
--

INSERT INTO `pcarmadas` (`id`, `cpu`, `gpu`, `memoria`, `motherboard`, `gabinete`, `fuente`, `borrado`, `entregado`, `observaciones`) VALUES
(1, 12, 11, 14, 10, 15, 17, 0, 1, 'Primera pc armada desde la pag'),
(2, 12, 11, 14, 10, 15, 17, 0, 1, 'Otra pc mas, mismos componentes xq no hay mas jaja');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sockets`
--

CREATE TABLE `sockets` (
  `id` int(11) NOT NULL,
  `descripcion` text NOT NULL,
  `borrado` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `sockets`
--

INSERT INTO `sockets` (`id`, `descripcion`, `borrado`) VALUES
(1, 'AM4', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tamanos`
--

CREATE TABLE `tamanos` (
  `id` int(11) NOT NULL,
  `descripcion` text NOT NULL,
  `borrado` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `tamanos`
--

INSERT INTO `tamanos` (`id`, `descripcion`, `borrado`) VALUES
(1, 'ATX', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipos`
--

CREATE TABLE `tipos` (
  `id` int(11) NOT NULL,
  `descripcion` text NOT NULL,
  `borrado` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `tipos`
--

INSERT INTO `tipos` (`id`, `descripcion`, `borrado`) VALUES
(1, 'CPU', 0),
(2, 'GPU', 0),
(4, 'Memoria RAM', 0),
(5, 'Motherboard', 0),
(6, 'Fuente', 0),
(7, 'Gabinete', 0),
(8, 'Monitor', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(32) NOT NULL,
  `password` varchar(32) NOT NULL,
  `isAdmin` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `isAdmin`) VALUES
(2, 'generadoDesdeCRUD', '123', 1),
(9, 'usuarioAdmin', '123', 0),
(10, 'usuarioNoAdmin', '123', 0);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `componentes`
--
ALTER TABLE `componentes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `componente_marca` (`marca`),
  ADD KEY `componente_tipo` (`tipo`),
  ADD KEY `componente_socket` (`socket`),
  ADD KEY `componente_tamano` (`tamano`);

--
-- Indices de la tabla `marcas`
--
ALTER TABLE `marcas`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `pcarmadas`
--
ALTER TABLE `pcarmadas`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `sockets`
--
ALTER TABLE `sockets`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `tamanos`
--
ALTER TABLE `tamanos`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `tipos`
--
ALTER TABLE `tipos`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `componentes`
--
ALTER TABLE `componentes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT de la tabla `marcas`
--
ALTER TABLE `marcas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `pcarmadas`
--
ALTER TABLE `pcarmadas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `sockets`
--
ALTER TABLE `sockets`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `tamanos`
--
ALTER TABLE `tamanos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `tipos`
--
ALTER TABLE `tipos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `componentes`
--
ALTER TABLE `componentes`
  ADD CONSTRAINT `componente_marca` FOREIGN KEY (`marca`) REFERENCES `marcas` (`id`),
  ADD CONSTRAINT `componente_socket` FOREIGN KEY (`socket`) REFERENCES `sockets` (`id`),
  ADD CONSTRAINT `componente_tamano` FOREIGN KEY (`tamano`) REFERENCES `tamanos` (`id`),
  ADD CONSTRAINT `componente_tipo` FOREIGN KEY (`tipo`) REFERENCES `tipos` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
