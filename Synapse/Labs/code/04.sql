-- Lab - Loading data into a table - COPY Command - CSV

/* 
When it comes to loading data in a table in Synapse Dedicated SQL pool, it is not recommended to use the admin account for the load operations.

Instead, you should create a separate user for performing the load operations.

The administrator account should be used for administrative purposes and not for the load operations.

Create a separate user and then you create something known as a Work group. This ensures that right amount resources are allocated for different load operations.

In a data warehouse, you might have some users who are responsible for performing analysis on the data and then maybe you might have some users who need to perform ongoing load operations. This could be incremental loads that happen every day.

As a admin you need to segregate the CPU percentage between these two different sets of users, 
Otherwise, if suppose all of the same percentage goes towards the load operation, then there'll be no more C.P.U resources left for analysis queries.

To solve this, we create different workload groups, assign the required CPU resources to these different work groups.
*/

-- https://docs.microsoft.com/en-us/azure/synapse-analytics/sql-data-warehouse/quickstart-bulk-load-copy-tsql?context=/azure/synapse-analytics/context/context

-- Step 1: Create new User and new Login
-- This has to be run in the master database in Dedicated SQL Pool
CREATE LOGIN EltUser WITH PASSWORD = 'Azure@123456';

-- Next set of commands have to run in the context of database in Dedicated SQL Pool
CREATE USER EltUser FOR LOGIN EltUser;
GRANT ADMINISTER DATABASE BULK OPERATIONS TO EltUser;
GRANT CREATE TABLE TO EltUser;
GRANT ALTER ON SCHEMA::dbo TO EltUser;

-- https://docs.microsoft.com/en-us/azure/synapse-analytics/sql-data-warehouse/quickstart-configure-workload-isolation-tsql?context=/azure/synapse-analytics/context/context

CREATE WORKLOAD GROUP EltLoad
WITH ( 
    MIN_PERCENTAGE_RESOURCE = 100  -- 20 for 20% isolation
    ,CAP_PERCENTAGE_RESOURCE = 100
    ,REQUEST_MIN_RESOURCE_GRANT_PERCENT = 100
    );

CREATE WORKLOAD CLASSIFIER [wcEltUser]
WITH (
    WORKLOAD_GROUP = 'EltLoad'
    ,MEMBERNAME = 'EltUser'
);

-- Create a normal table
-- Here I have added more constraints when it comes to the width of the data type

CREATE TABLE [logdata_copy_cmd]
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

-- Grant the required privileges to the new user

GRANT INSERT ON logdata_copy_cmd TO EltUser;
GRANT SELECT ON logdata_copy_cmd TO EltUser;








-- Now log in as the new user and run below commands from SSMS or Azure data studio
--
--
-- 
-- The FIRSTROW option helps to ensure the first header row is not part of the COPY implementation
-- https://docs.microsoft.com/en-us/sql/t-sql/statements/copy-into-transact-sql?view=azure-sqldw-latest&preserve-view=true

SELECT * FROM [logdata_copy_cmd]

-- Here there is no authentication/authorization, 
-- so you either need to allow public access for the container or 
-- Link the storage to Azure Synapse Workspace

-- change the access level of container to Public then run below command
COPY INTO logdata_copy_cmd FROM 'https://extdatalakemallik.blob.core.windows.net/data/raw/LogData01.csv'
WITH
(
FIRSTROW=2
)



