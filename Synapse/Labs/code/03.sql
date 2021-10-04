-- Lab - SQL Pool - External tables - Parquet - Linked ADLS Account

-- No need of CREDENTIAL when using Linked ADLS
CREATE EXTERNAL DATA SOURCE azdatalake
WITH (    LOCATION   = 'abfss://raw@azdatalakemallik.dfs.core.windows.net',
          TYPE = HADOOP
)

-- Here we are mentioning the file format as Parquet
CREATE EXTERNAL FILE FORMAT parquetfile  
WITH (  
    FORMAT_TYPE = PARQUET,  
    DATA_COMPRESSION = 'org.apache.hadoop.io.compress.SnappyCodec'  
);


CREATE EXTERNAL TABLE [logdata_parquet]
(
    [Id] [int] NULL,
	[Correlationid] [varchar](200) NULL,
	[Operationname] [varchar](200) NULL,
	[Status] [varchar](100) NULL,
	[Eventcategory] [varchar](100) NULL,
	[Level] [varchar](100) NULL,
	[Time] [datetime] NULL,
	[Subscription] [varchar](200) NULL,
	[Eventinitiatedby] [varchar](1000) NULL,
	[Resourcetype] [varchar](1000) NULL,
	[Resourcegroup] [varchar](1000) NULL
)
WITH (
 LOCATION = '/logdata/',
    DATA_SOURCE = azdatalake,  
    FILE_FORMAT = parquetfile
)

/*
A common error can come when trying to select the data, here you can get various errors such as MalformedInput

You need to ensure the column names map correctly and the data types are correct as per the parquet file definition
*/


SELECT * FROM [logdata_parquet]


-- compare the sizes of the Parquet files (~1MB) and CSV file (8 MB)