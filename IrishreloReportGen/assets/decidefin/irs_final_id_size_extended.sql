BEGIN TRANSACTION;
CREATE TABLE `APARTMENT_PARKING` (
  `id` INT(50)  PRIMARY KEY,
  `inspect_type` VARCHAR(255) DEFAULT NULL,
  `checkin_comm` VARCHAR(255) DEFAULT NULL,
  `checkin_qty` VARCHAR(255) DEFAULT NULL,
  `predepart_comm` VARCHAR(255) DEFAULT NULL,
  `predepart_qty` VARCHAR(255) DEFAULT NULL,
  `depart_comm` VARCHAR(255) DEFAULT NULL,
  `depart_qty` VARCHAR(255) DEFAULT NULL,
  `jobid` BIGINT(50) NOT NULL
);
CREATE TABLE `BEDROOM_2` (
  `id` INT(50)  PRIMARY KEY,
  `inspect_type` VARCHAR(255) DEFAULT NULL,
  `checkin_comm` VARCHAR(255) DEFAULT NULL,
  `checkin_img` TEXT DEFAULT NULL,
  `predepart_comm` VARCHAR(255) DEFAULT NULL,
  `predepart_img` TEXT DEFAULT NULL,
  `depart_comm` VARCHAR(255) DEFAULT NULL,
  `depart_img` TEXT DEFAULT NULL,
  `jobid` BIGINT(50) NOT NULL
);
CREATE TABLE `BEDROOM_3` (
  `id` INT(50)  PRIMARY KEY,
  `inspect_type` VARCHAR(255) DEFAULT NULL,
  `checkin_comm` VARCHAR(255) DEFAULT NULL,
  `checkin_img` TEXT DEFAULT NULL,
  `predepart_comm` VARCHAR(255) DEFAULT NULL,
  `predepart_img` TEXT DEFAULT NULL,
  `depart_comm` VARCHAR(255) DEFAULT NULL,
  `depart_img` TEXT DEFAULT NULL,
  `jobid` BIGINT(50) NOT NULL
);
CREATE TABLE `BEDROOM_4` (
  `id` INT(50)  PRIMARY KEY,
  `inspect_type` VARCHAR(255) DEFAULT NULL,
  `checkin_comm` VARCHAR(255) DEFAULT NULL,
  `checkin_img` TEXT DEFAULT NULL,
  `predepart_comm` VARCHAR(255) DEFAULT NULL,
  `predepart_img` TEXT DEFAULT NULL,
  `depart_comm` VARCHAR(255) DEFAULT NULL,
  `depart_img` TEXT DEFAULT NULL,
  `jobid` BIGINT(50) NOT NULL
);
CREATE TABLE `COMMUNICATION_N_PRECAUTIONS` (
  `id` INT(100)  PRIMARY KEY,
  `telephone_number` VARCHAR(255) DEFAULT NULL,
  `phone_handset` TINYINT(1) DEFAULT 0,
  `headset_count` INT DEFAULT 0,
  `phone_working` TINYINT(1) DEFAULT 0,
  `broadband_suplier` VARCHAR(1) DEFAULT 0,
  `is_broradband_working` TINYINT(1) DEFAULT 0,
  `alarm_code` VARCHAR(255),
  `supplier` VARCHAR(255),
  `precaut_instructor` VARCHAR(255),
  `smoke_alarm_nos` VARCHAR(255),
  `locations`  VARCHAR(255),
  `smokalrm_working` TINYINT(1) DEFAULT 0,
  `general_cp` VARCHAR(255) DEFAULT NULL,
  `general_cd` VARCHAR(255) DEFAULT NULL,
  `general_prov` VARCHAR(255) DEFAULT NULL,
  `recycle_cp` VARCHAR(255) DEFAULT NULL,
  `recycle_cd` VARCHAR(255) DEFAULT NULL,
  `recycle_prov` VARCHAR(255) DEFAULT NULL,
  `glass_cp` VARCHAR(255) DEFAULT NULL,
  `glass_cd` VARCHAR(255) DEFAULT NULL,
  `glass_prov` VARCHAR(255) DEFAULT NULL,  
  `bin_tag` TINYINT(1) DEFAULT 0,
  `tv_working` TINYINT(1) DEFAULT 0,
  `dvd_connected` TINYINT(1) DEFAULT 0,
  `skyorcbl_connected` TINYINT(1) DEFAULT 0,
  `jobid` BIGINT(50) NOT NULL
);
CREATE TABLE `DINING_ROOM` (
  `id` INT(50)  PRIMARY KEY,
  `inspect_type` VARCHAR(255) DEFAULT NULL,
  `checkin_comm` VARCHAR(255) DEFAULT NULL,
  `checkin_img` TEXT DEFAULT NULL,
  `predepart_comm` VARCHAR(255) DEFAULT NULL,
  `predepart_img` TEXT DEFAULT NULL,
  `depart_comm` VARCHAR(255) DEFAULT NULL,
  `depart_img` TEXT DEFAULT NULL,
  `jobid` BIGINT(50) NOT NULL
);
CREATE TABLE `EN_SUITE_BATHROOM` (
  `id` INT(50)  PRIMARY KEY,
  `inspect_type` VARCHAR(255) DEFAULT NULL,
  `checkin_comm` VARCHAR(255) DEFAULT NULL,
  `checkin_img` TEXT DEFAULT NULL,
  `predepart_comm` VARCHAR(255) DEFAULT NULL,
  `predepart_img` TEXT DEFAULT NULL,
  `depart_comm` VARCHAR(255) DEFAULT NULL,
  `depart_img` TEXT DEFAULT NULL,
  `jobid` BIGINT(50) NOT NULL
);
CREATE TABLE `EXTERIOR_COMMENTS` (
  `id` INT(50)  PRIMARY KEY,
  `checkin_comm` VARCHAR(255) DEFAULT NULL,
  `checkin_comp` VARCHAR(255) DEFAULT NULL,
  `predepart_comm` VARCHAR(255) DEFAULT NULL,
  `predepart_comp` VARCHAR(255) DEFAULT NULL,
  `depart_comm` VARCHAR(255) DEFAULT NULL,
  `depart_comp` VARCHAR(255) DEFAULT NULL,
  `jobid` BIGINT(50) NOT NULL
);
CREATE TABLE `EXTERIOR_GROUND` (
  `id` INT(50)  PRIMARY KEY,
  `inspect_type` varchar(255) DEFAULT NULL,
  `checkin_comm` varchar(255) DEFAULT NULL,
  `checkin_img` TEXT DEFAULT NULL,
  `checkin_img_name` varchar(255) DEFAULT NULL,
  `predepart_comm` varchar(255) DEFAULT NULL,
  `predepart_img` TEXT DEFAULT NULL,
  `predepart_img_name` varchar(255) DEFAULT NULL,
  `depart_comm` varchar(255) DEFAULT NULL,
  `depart_img` TEXT DEFAULT NULL,
  `depart_img_name` varchar(255) DEFAULT NULL,
  `jobid` BIGINT(50) NOT NULL
);
CREATE TABLE `HALL_OR_LAND` (
  `id` INT(50)  PRIMARY KEY,
 `inspect_type` varchar(255) DEFAULT NULL,
  `checkin_comm` varchar(255) DEFAULT NULL,
  `checkin_img` TEXT DEFAULT NULL,
  `checkin_img_name` varchar(255) DEFAULT NULL,
  `predepart_comm` varchar(255) DEFAULT NULL,
  `predepart_img` TEXT DEFAULT NULL,
  `predepart_img_name` varchar(255) DEFAULT NULL,
  `depart_comm` varchar(255) DEFAULT NULL,
  `depart_img` TEXT DEFAULT NULL,
  `depart_img_name` varchar(255) DEFAULT NULL,
  `jobid` BIGINT(50) NOT NULL
);
CREATE TABLE `IF_APPLICABLE` (
  `id` INT(50)  PRIMARY KEY,
  `inspect_type` VARCHAR(255) DEFAULT NULL,
  `checkin_comm` VARCHAR(255) DEFAULT NULL,
  `checkin_qty` VARCHAR(255) DEFAULT NULL,
  `predepart_comm` VARCHAR(255) DEFAULT NULL,
  `predepart_qty` VARCHAR(255) DEFAULT NULL,
  `depart_comm` VARCHAR(255) DEFAULT NULL,
  `depart_qty` VARCHAR(255) DEFAULT NULL,
  `jobid` BIGINT(50) NOT NULL
);
CREATE TABLE INSPECTION_BASICS(
   `jobid` BIGINT(50) PRIMARY KEY,
   tenant VARCHAR(255) DEFAULT NULL,
   occupier VARCHAR(255)  DEFAULT NULL,
   address TEXT  DEFAULT NULL,
   property_type VARCHAR(20)  DEFAULT NULL,
   no_of_bedrooms INT(2)  DEFAULT 0,
   isfurnished VARCHAR(20)  DEFAULT NULL,
   check_in BIGINT(50)  DEFAULT 0,
   pre_departure BIGINT(50)  DEFAULT 0,
   departure BIGINT(50)  DEFAULT 0
, added_date LONG);
CREATE TABLE JOB_LOCAL_OPERATION_PHASE_TRACK(
	jobid INT NOT NULL,
	isold TINYINT DEFAULT 0,
	phase VARCHAR(255) DEFAULT NULL,
	save_status VARCHAR(20) DEFAULT NULL,
	mode VARCHAR(20) DEFAULT NULL,
	last_updated_by  TEXT DEFAULT NULL,
	update_time INT DEFAULT NULL
);
CREATE TABLE `KITCHEN` (
  `id` INT(50)  PRIMARY KEY,
  `inspect_type` VARCHAR(255) DEFAULT NULL,
  `checkin_comm` VARCHAR(255) DEFAULT NULL,
  `checkin_img` TEXT DEFAULT NULL,
  `predepart_comm` VARCHAR(255) DEFAULT NULL,
  `predepart_img` TEXT DEFAULT NULL,
  `depart_comm` VARCHAR(255) DEFAULT NULL,
  `depart_img` TEXT DEFAULT NULL,
  `jobid` BIGINT(50) NOT NULL
);
CREATE TABLE `LIVING_ROOM` (
  `id` INT(50)  PRIMARY KEY,
  `inspect_type` VARCHAR(255) DEFAULT NULL,
  `checkin_comm` VARCHAR(255) DEFAULT NULL,
  `checkin_img` TEXT DEFAULT NULL,
  `predepart_comm` VARCHAR(255) DEFAULT NULL,
  `predepart_img` TEXT DEFAULT NULL,
  `depart_comm` VARCHAR(255) DEFAULT NULL,
  `depart_img` TEXT DEFAULT NULL,
  `jobid` BIGINT(50) NOT NULL
);
CREATE TABLE `MAIN_BATHROOM` (
  `id` INT(50)  PRIMARY KEY,
  `inspect_type` VARCHAR(255) DEFAULT NULL,
  `checkin_comm` VARCHAR(255) DEFAULT NULL,
  `checkin_img` TEXT DEFAULT NULL,
  `predepart_comm` VARCHAR(255) DEFAULT NULL,
  `predepart_img` TEXT DEFAULT NULL,
  `depart_comm` VARCHAR(255) DEFAULT NULL,
  `depart_img` TEXT DEFAULT NULL,
  `jobid` BIGINT(50) NOT NULL
);
CREATE TABLE `MASTER_BED_ROOM` (
  `id` INT(50)  PRIMARY KEY,
  `inspect_type` VARCHAR(255) DEFAULT NULL,
  `checkin_comm` VARCHAR(255) DEFAULT NULL,
  `checkin_img` TEXT DEFAULT NULL,
  `predepart_comm` VARCHAR(255) DEFAULT NULL,
  `predepart_img` TEXT DEFAULT NULL,
  `depart_comm` VARCHAR(255) DEFAULT NULL,
  `depart_img` TEXT DEFAULT NULL,
  `jobid` BIGINT(50) NOT NULL
);
CREATE TABLE `POWER_UTILITIES` (
  `id` INT(50)  PRIMARY KEY,
  `type` VARCHAR(50) DEFAULT NULL,
  `trackdate` INT(50) DEFAULT 0,
  `chkin_or_depart` TINYINT(1) DEFAULT 0,
  `morntime` INT(50) DEFAULT 0,
  `nittime` INT(50) DEFAULT 0,
  `meter_number` VARCHAR(100) DEFAULT NULL,
  `mornreading` VARCHAR(100) DEFAULT NULL,
  `nitreading` VARCHAR(100) DEFAULT NULL,
  `tank_loc` TEXT DEFAULT NULL,
  `tank_qty` INT(100) DEFAULT 0,
  `jobid` BIGINT(50) NOT NULL
);
CREATE TABLE SIGNED_BY (sign_date double, id int(50) PRIMARY KEY, conserned_type varchar(255), checkin_signimp TEXT, checkin_signimp_name varchar(255), predepart_signimp TEXT, predepart_signimp_name varchar(255), depart_signimp TEXT, depart_signimp_name varchar(255), jobid int(100));
CREATE TABLE `UTILITY_ROOM` (
  `id` INT(50)  PRIMARY KEY,
  `inspect_type` VARCHAR(255) DEFAULT NULL,
  `checkin_comm` VARCHAR(255) DEFAULT NULL,
  `checkin_img` TEXT DEFAULT NULL,
  `predepart_comm` VARCHAR(255) DEFAULT NULL,
  `predepart_img` TEXT DEFAULT NULL,
  `depart_comm` VARCHAR(255) DEFAULT NULL,
  `depart_img` TEXT DEFAULT NULL,
  `jobid` BIGINT(50) NOT NULL
);
CREATE TABLE `WC_OR_CLOAKROOM` (
  `id` INT(50)  PRIMARY KEY,
  `inspect_type` VARCHAR(255) DEFAULT NULL,
  `checkin_comm` VARCHAR(255) DEFAULT NULL,
  `checkin_img` TEXT DEFAULT NULL,
  `predepart_comm` VARCHAR(255) DEFAULT NULL,
  `predepart_img` TEXT DEFAULT NULL,
  `depart_comm` VARCHAR(255) DEFAULT NULL,
  `depart_img` TEXT DEFAULT NULL,
  `jobid` BIGINT(50) NOT NULL
);
COMMIT;
