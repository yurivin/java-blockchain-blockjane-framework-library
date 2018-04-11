# Development

- 1. Development of smart contracts.
+   1.1 Creation of groovy contracts runner, contract interface. And test for this.
+   1.2 Adding contract result data to the block. Contract result saves to the block with hash as JSON field.
+   1.3 Saving Contract to the block with contract code hash as id of contract. In Json object field called contract
-   1.4 Scan blocks and Create contracts pointer in the local database to run when needed. 
+       1.4.1 Serializer and Deserializer methods to handle contracts saving. with tests.
-       1.4.2 Scan blockData during block creation to create contract pointers if there are some.
-   1.5 Create Kotlin contracts runner.


Other
+ 1. Make Block class data field ObjectNode type, not String type;
