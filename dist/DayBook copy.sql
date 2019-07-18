-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jul 18, 2019 at 07:25 AM
-- Server version: 10.3.15-MariaDB
-- PHP Version: 7.3.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `DayBook`
--

-- --------------------------------------------------------

--
-- Table structure for table `accountHead`
--

CREATE TABLE `accountHead` (
  `l_code` int(15) NOT NULL,
  `l_name` varchar(25) NOT NULL,
  `l_details` varchar(40) NOT NULL,
  `type` text NOT NULL,
  `username` varchar(30) NOT NULL,
  `l_limit` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `accountHead`
--

INSERT INTO `accountHead` (`l_code`, `l_name`, `l_details`, `type`, `username`, `l_limit`) VALUES
(1, 'sbi', 'account:10988', 'Income', 'adi11', 20000),
(2, 'fdi', 'account:1099', 'Expense', 'adi11', 0),
(3, 'sbi_in', 'acc:123', 'Income', 'nabhay123', 0),
(4, 'fdi_ex', 'acc:1234', 'Expense', 'nabhay123', 0),
(5, 'yes', 'acc:45', 'Expense', 'nabhay123', 0),
(6, 'punjab & sind', 'acc:099', 'Income', 'adi11', 0),
(7, 'yes bank', 'acc:45511', 'Income', 'adi11', 6000),
(8, 'paytm', '897644', 'Income', 'adi11', 500),
(10, 'paytm ', '986767', 'Expense', 'adi11', 0),
(11, 'google pay', '9867', 'Expense', 'adi11', 1000),
(12, 'bhim upi', '98699', 'Expense', 'adi11', 0),
(15, 'Electricity', 'monthly bill payment', 'Expense', 'adi11', 0),
(16, 'Fees', 'monthly school fees', 'Expense', 'adi11', 2000),
(17, 'psb', 'acc:122', 'Income', 'adi11', 0),
(18, 'Salary', 'monthly salary', 'Income', 'adi11', 0),
(19, 'EMI', 'monthly emi', 'Expense', 'adi11', 0),
(21, 'ledger2', 'qwerty', 'Income', 'new', 0),
(22, 'assssd', 'wertdd', 'Expense', 'new', 0),
(23, '12er3', 'bill', 'Expense', 'new', 0),
(24, 'yes bank', 'acc:45511', 'Income', 'adi11', 6000),
(25, 'debit accou', 'ac0099', 'Income', 'adi11', 30000);

-- --------------------------------------------------------

--
-- Table structure for table `headEntry`
--

CREATE TABLE `headEntry` (
  `entry_id` int(10) NOT NULL,
  `l_code` int(15) NOT NULL,
  `amount` int(20) NOT NULL,
  `details` varchar(40) NOT NULL,
  `type` text NOT NULL,
  `date` varchar(10) NOT NULL,
  `username` varchar(30) NOT NULL,
  `paid` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `headEntry`
--

INSERT INTO `headEntry` (`entry_id`, `l_code`, `amount`, `details`, `type`, `date`, `username`, `paid`) VALUES
(1, 1, 2000, 'debit from raj', 'Income', '07-07-2019', 'adi11', NULL),
(2, 1, 20000, 'debit salary', 'Income', '07-07-2019', 'adi11', NULL),
(3, 2, 3000, 'spent on shoes', 'Expense', '07-07-2019', 'adi11', NULL),
(4, 8, 490, 'BY RAJU', 'Income', '01-07-2019', 'adi11', NULL),
(5, 15, 2000, 'paid', 'Expense', '11-07-2019', 'adi11', NULL),
(6, 12, 200, 'paid to saki', 'Expense', '11-07-2019', 'adi11', NULL),
(7, 11, 500, 'kanisha party', 'Expense', '10-07-2019', 'adi11', NULL),
(8, 11, 200, 'paid for party', 'Expense', '12-07-2019', 'adi11', NULL),
(10, 16, 3000, 'Fees', 'Expense', '11-07-2019', 'adi11', 'YES'),
(11, 18, 40000, 'Salary', 'Income', '17-07-2019', 'adi11', 'YES'),
(12, 17, 300, 'sett', 'Income', '11-06-2019', 'adi11', NULL),
(13, 18, 6000, 'got', 'Income', '09-06-2019', 'adi11', NULL),
(14, 16, 600, 'school', 'Expense', '10-06-2019', 'adi11', NULL),
(15, 21, 200, 'paid to ravi', 'Income', '13-07-2019', 'new', NULL),
(16, 10, 5000, 'fees', 'Expense', '17-07-2019', 'adi11', NULL),
(17, 15, 300, 'Electricity', 'Expense', '18-07-2019', 'adi11', 'YES'),
(18, 19, 400, 'EMI', 'Expense', '19-07-2019', 'adi11', 'YES');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `name` text NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `phone` varchar(13) NOT NULL,
  `email` varchar(40) NOT NULL,
  `user` text NOT NULL,
  `company` varchar(30) NOT NULL,
  `ques` varchar(50) NOT NULL,
  `ans` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`name`, `username`, `password`, `phone`, `email`, `user`, `company`, `ques`, `ans`) VALUES
('aditya', 'adi11', '123er', '87658787', 'aas@yahoo.in', 'Individual', 'Individual', 'What is your school\'s best memory?', 'kindergarden'),
('nabhay', 'nabhay123', '123456', '776767667', 'yahan@yahoo.com', 'Individual', 'Individual', 'What is your favourite teacher name?', 'bharti bja'),
('real new', 'new', '12', '9878654567', 'ledger.day.book@gmail.com', 'Individual', 'Individual', 'What is your favourite animal?', 'dog'),
('real', 'real', '12345', '987678', 'team@realinfotech.in', 'Individual', 'Individual', 'What is your favourite animal?', 'dog'),
('Sugandha', 'Sugandha11', '123456', '89988989', 'sugandha@yahoo.com', 'Individual', 'Individual', 'What is your favourite teacher name?', 'bharti');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `accountHead`
--
ALTER TABLE `accountHead`
  ADD PRIMARY KEY (`l_code`),
  ADD KEY `username_fk1` (`username`);

--
-- Indexes for table `headEntry`
--
ALTER TABLE `headEntry`
  ADD PRIMARY KEY (`entry_id`),
  ADD KEY `lcode_fk_headEntry` (`l_code`),
  ADD KEY `username_fk_headEntry` (`username`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`username`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `accountHead`
--
ALTER TABLE `accountHead`
  MODIFY `l_code` int(15) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT for table `headEntry`
--
ALTER TABLE `headEntry`
  MODIFY `entry_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `accountHead`
--
ALTER TABLE `accountHead`
  ADD CONSTRAINT `username_fk1` FOREIGN KEY (`username`) REFERENCES `users` (`username`);

--
-- Constraints for table `headEntry`
--
ALTER TABLE `headEntry`
  ADD CONSTRAINT `lcode_fk_headEntry` FOREIGN KEY (`l_code`) REFERENCES `accountHead` (`l_code`),
  ADD CONSTRAINT `username_fk_headEntry` FOREIGN KEY (`username`) REFERENCES `users` (`username`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
