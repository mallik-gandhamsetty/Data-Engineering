-- Lab - SQL Pool - External Tables - CSV
CREATE MASTER KEY ENCRYPTION BY PASSWORD = 'P@ssw0rd@123';


-- Here we are using the Storage account key for authorization
CREATE DATABASE SCOPED CREDENTIAL AzureStorageCredential
WITH
  IDENTITY = 'extdatalakemallik',
  SECRET = 'c4vnQsGl8Wa/DcMsmqJ9J0iUY//DPEwz15oPDKAgyTqi2FWsHipcbcvdGp6dLfTD61Zak2ZwaUtzmOU1fNkgSQ==';


-- In the SQL pool, we can use Hadoop drivers to mention the source
CREATE EXTERNAL DATA SOURCE log_data
WITH (    LOCATION   = 'abfss://data@extdatalakemallik.dfs.core.windows.net',
          CREDENTIAL = AzureStorageCredential,
          TYPE = HADOOP
)

-- DROP EXTERNAL FILE FORMAT TextFileFormat;

CREATE EXTERNAL FILE FORMAT TextFileFormat WITH (  
    FORMAT_TYPE = DELIMITEDTEXT,  
    FORMAT_OPTIONS (  
        FIELD_TERMINATOR = ',',
        FIRST_ROW = 2))

-- DROP EXTERNAL TABLE [logdata];

CREATE EXTERNAL TABLE [logdata01]
(
    [Id] [INT],
	[Correlationid] [varchar](200),
	[OperationName] [varchar](200),
	[Status] [varchar](100),
	[Eventcategory] [varchar](100),
	[Level] [varchar](100),
	[Time] [DATETIME2],
	[Subscription] [varchar](200),
	[Eventinitiatedby] [varchar](1000),
	[Resourcetype] [varchar](1000),
	[Resourcegroup] [varchar](1000)
)
WITH (
 LOCATION = '/raw/LogData01.csv',
    DATA_SOURCE = log_data,  
    FILE_FORMAT = TextFileFormat
)

-- Fails  with error: HdfsBridge::recordReaderFillBuffer - Unexpected error encountered filling record reader buffer: HadoopSqlException: Error converting data type VARCHAR to DATETIME.
SELECT * FROM logdata01


-- Option 1: Recreate table with [Time] as VARCHAR field

-- Option 2: Convert the timestamp column to a compatible format using databricks or adf and then read it as datetime field 

CREATE EXTERNAL FILE FORMAT TextFileFormat WITH (  
    FORMAT_TYPE = DELIMITEDTEXT,  
    FORMAT_OPTIONS (  
        FIELD_TERMINATOR = ',',
        FIRST_ROW = 2))

-- DROP EXTERNAL TABLE [logdata];

CREATE EXTERNAL TABLE [logdata02]
(
    [Id] [INT],
	[Correlationid] [varchar](200),
	[OperationName] [varchar](200),
	[Status] [varchar](100),
	[Eventcategory] [varchar](100),
	[Level] [varchar](100),
	[Time] [DATETIME2],
	[Subscription] [varchar](200),
	[Eventinitiatedby] [varchar](1000),
	[Resourcetype] [varchar](1000),
	[Resourcegroup] [varchar](1000)
)
WITH (
 LOCATION = '/raw/LogData02.csv',
    DATA_SOURCE = log_data,  
    FILE_FORMAT = TextFileFormat
)

SELECT * FROM logdata02

