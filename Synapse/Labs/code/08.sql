-- Lab - Building fact and dimension tables



-- On new SQL DB where AdventureWorks is hosted

-- Lets first create a view for the fact

  CREATE VIEW [Sales_Fact_View]
  AS
  SELECT dt.[ProductID],dt.[SalesOrderID],dt.[OrderQty],dt.[UnitPrice],hd.[OrderDate],hd.[CustomerID],hd.[TaxAmt]
  FROM [Sales].[SalesOrderDetail] dt
  LEFT JOIN [Sales].[SalesOrderHeader] hd
  ON dt.[SalesOrderID]=hd.[SalesOrderID]

-- Then we will create the Sales Fact table from the view
  
SELECT [ProductID],[SalesOrderID],[CustomerID],[OrderQty],[UnitPrice],[OrderDate],[TaxAmt]
INTO SalesFact
FROM Sales_Fact_View


-- Lets build a view for the customers

CREATE VIEW Customer_view 
AS
  SELECT ct.[CustomerID],ct.[StoreID],st.[BusinessEntityID],st.[Name]  as StoreName
  FROM [Sales].[Customer] as ct
  LEFT JOIN [Sales].[Store] as st 
  ON ct.[StoreID]=st.[BusinessEntityID]
  WHERE  st.[BusinessEntityID] IS NOT NULL

-- Lets create a customer dimension table

SELECT [CustomerID],[StoreID],[BusinessEntityID],StoreName
INTO DimCustomer
FROM Customer_view 


-- Lets build a view for the products

CREATE VIEW Product_view 
AS
SELECT prod.[ProductID],prod.[Name] as ProductName,prod.[SafetyStockLevel],model.[ProductModelID],model.[Name] as ProductModelName,category.[ProductSubcategoryID],category.[Name] AS ProductSubCategoryName
FROM [Production].[Product] prod
LEFT JOIN [Production].[ProductModel] model ON prod.[ProductModelID] = model.[ProductModelID]
LEFT JOIN [Production].[ProductSubcategory] category ON prod.[ProductSubcategoryID]=category.[ProductSubcategoryID]
WHERE prod.[ProductModelID] IS NOT NULL

-- Lets create a product dimension table

SELECT [ProductID],[ProductModelID],[ProductSubcategoryID],ProductName,[SafetyStockLevel],ProductModelName,ProductSubCategoryName
INTO DimProduct
FROM Product_view 

-- If you want to drop the views and the tables

-- DROP VIEW Customer_view 

-- DROP TABLE DimCustomer

-- DROP VIEW Product_view 

-- DROP TABLE DimProduct



-- On new Dedicated SQl Pool where dataload happened

-- Query to show join between Dimension and the Fact table
SELECT dimc.StoreName,dimp.ProductName,dimp.ProductSubCategoryName,SUM(ft.OrderQty) AS Quantity
FROM SalesFact ft
INNER JOIN DimCustomer dimc ON ft.CustomerID=dimc.CustomerID
INNER JOIN DimProduct dimp ON ft.ProductID=dimp.ProductID
GROUP BY dimc.StoreName,dimp.ProductName,dimp.ProductSubCategoryName
