-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 08, 2023 at 07:38 AM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.0.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dorsu_inventory_system`
--

-- --------------------------------------------------------

--
-- Table structure for table `devices`
--

CREATE TABLE `devices` (
  `device_code` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `peripheral` varchar(50) NOT NULL,
  `assigned_to` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `devices`
--

INSERT INTO `devices` (`device_code`, `name`, `peripheral`, `assigned_to`) VALUES
('2023-0001', 'Basues Encock WM01', 'Output', 'Clarence Japinan'),
('2023-0003', 'Sample', 'Input/Output', NULL),
('2023-0004', 'Royal Kludge RK71', 'Input', 'Zen Candia');

-- --------------------------------------------------------

--
-- Table structure for table `employees`
--

CREATE TABLE `employees` (
  `emp_id` int(50) NOT NULL,
  `emp_name` varchar(100) NOT NULL,
  `contact_num` varchar(50) NOT NULL,
  `gender` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `employees`
--

INSERT INTO `employees` (`emp_id`, `emp_name`, `contact_num`, `gender`) VALUES
(2, 'Clarence Japinan', '09112239821', 'Male'),
(5, 'Kaarlo Sasiang', '098272374611', 'Male'),
(6, 'Zen Candia', '098272374611', 'Male');

-- --------------------------------------------------------

--
-- Table structure for table `peripherals`
--

CREATE TABLE `peripherals` (
  `name` varchar(50) NOT NULL,
  `description` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `peripherals`
--

INSERT INTO `peripherals` (`name`, `description`) VALUES
('Input', 'sends data or instructions to the computer, such as mouse, keyboard, graphics tablet, image scanner, barcode reader, game controller, light pen, light gun, microphone and webcam.'),
('Input/Output', 'Performs both input and output functions such as a computer data storage device (including a disk drive, solid-state drive, USB flash drive, memory card and tape drive), modem, network adapter and multi-function printer'),
('Output ', 'Provides output data from the computer such as computer monitor, projector, printer, headphones and computer speaker.');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `devices`
--
ALTER TABLE `devices`
  ADD PRIMARY KEY (`device_code`),
  ADD KEY `peripheral` (`peripheral`),
  ADD KEY `assigned_to` (`assigned_to`);

--
-- Indexes for table `employees`
--
ALTER TABLE `employees`
  ADD PRIMARY KEY (`emp_id`),
  ADD UNIQUE KEY `emp_name` (`emp_name`);

--
-- Indexes for table `peripherals`
--
ALTER TABLE `peripherals`
  ADD PRIMARY KEY (`name`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `employees`
--
ALTER TABLE `employees`
  MODIFY `emp_id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `devices`
--
ALTER TABLE `devices`
  ADD CONSTRAINT `devices_ibfk_1` FOREIGN KEY (`peripheral`) REFERENCES `peripherals` (`name`),
  ADD CONSTRAINT `devices_ibfk_2` FOREIGN KEY (`assigned_to`) REFERENCES `employees` (`emp_name`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
