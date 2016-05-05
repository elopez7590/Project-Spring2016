-- Database: "MetricsCollection"

-- DROP DATABASE "MetricsCollection";

--CREATE DATABASE "MetricsCollection"
  --WITH OWNER = postgres
  --     ENCODING = 'UTF8'-
  --     TABLESPACE = pg_default
  --     LC_COLLATE = 'English_United States.1252'
  --     LC_CTYPE = 'English_United States.1252'
  --     CONNECTION LIMIT = -1;

DROP TABLE IF EXISTS metrics CASCADE;

-- Metrics --
CREATE TABLE metrics (
  PID char(10) not null,
  processname char(50) not null,
  machinename char(50) not null,
  parentPID char(10),
  totalsize bigint,
  dateofcreation date,
  PRIMARY KEY(PID,processname,machinename)
);