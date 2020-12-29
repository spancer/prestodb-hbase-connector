# presto-hbase-connector
[prestodb](https://prestodb.io/) connector for apache HBase, it's a lightweight HBase connector which uses zookeeper to hold the metadata. This connector is based on Hadoop 3.1, HBase 2.0 and Prestodb 0.241. 

## Overview
The Presto HBase connector allows access to HBase data from Presto. This document describe how to setup the Presto HBase connector to run SQL against HBase.
* Note:    HBase 2.0 or above is required. *

###  How to build

Presto-hbase is a Maven project. You can package the connector jar file using the maven package as below.
```
mvn clean install

```

### Plugin setup
- Deploying presto hbase plugin
  - create a directory called *presto-hbase* in the Presto plugin directory.
  - Add  jar file *prestodb-hbase-connector-0.0.1-SNAPSHOT.jar* built with above.

### Catalog config
- Add hbase config file under Presto *catalog* directory, which named as *hbase.properties* 
- Add configurations for *hbase.properties* as below:
```
connector.name=hbase
hbase.zookeepers=itserver21:2181,itserver22:2181,itserver23:2181
hbase.internal.table.drop.enabled=true

```
### Example of use
- Connect to HBase catalog. 
```
./presto-cli --server itserver22:8285 --catalog hbase

```
- Select and use the *default* schema, in which refers to HBase's default namespace *default*
```
 use default;
```
- Create an example  table *test*

```
CREATE TABLE test (
  rowkey VARCHAR,
  name VARCHAR,
  age BIGINT,
  birthday DATE
)
WITH (
  column_mapping = 'name:info:name,age:info:age,birthday:info:date'
)
```

- Produce some some data.
```
INSERT INTO test VALUES
('1', 'Jack Ma', 35, DATE '1988-11-12' ),
('2', 'Grady Chen', 33, DATE '1989-01-15' ),
('3', 'Bluce Ju', 23, DATE '1982-02-25' );
```

- Create an example table under schema *testns*
```
CREATE TABLE testns.test (
  rowkey VARCHAR,
  name VARCHAR,
  age BIGINT,
  birthday DATE
)
WITH (
  column_mapping = 'name:info:name,age:info:age,birthday:info:date'
)
```
- Drop table *test* under schema *testns*
```
USE testns; //swich to schema testns
DROP TABLE test; //drop table test
```
- Drop schema *testns*
   Note: We can only drop the schemas that have no tables.

```
DROP SCHEMA testns;

```