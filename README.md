# testPrisioner

## Prerequisites

Es necesario tener instalado:

```
Java 8
Maven
DynamoDB
```

```javascript
/*Tabla que va a contener todos los ADN*/
var params = {TableName: 'PersonsDna', KeySchema: [ {AttributeName: 'Dna',KeyType: 'HASH',}],
AttributeDefinitions: [ { AttributeName: 'Dna', AttributeType: 'S',	}],
ProvisionedThroughput: {ReadCapacityUnits: 1, WriteCapacityUnits: 1, }};
dynamodb.createTable(params, function(err, data) { if (err) ppJson(err); else ppJson(data); });

/*Tabla que va a funcionar como contador de humanos y prisiones*/
var params = {TableName: 'DnaCounters',KeySchema: [{AttributeName: 'dna',KeyType: 'HASH',}],
AttributeDefinitions: [{AttributeName: 'dna',AttributeType: 'S',}],
ProvisionedThroughput: {ReadCapacityUnits: 1,WriteCapacityUnits: 1,}};
dynamodb.createTable(params, function(err, data) { if (err) ppJson(err); else ppJson(data);});

/*Registro base que luego se incrementara segun el tipo de ADN que se consulte*/
var params = {TableName: 'DnaCounters', Item: {dna: 'DNA',count_successful_escape: 0,count_unsuccessful_escape: 0}};
console.log("Calling PutItem"); ppJson(params);
docClient.put(params, function(err, data) { if (err) ppJson(err); else console.log("PutItem returned successfully");});
```

- Levantar la app local (Puerto 8081 por default, se puede modificar en el archivo application.properties): mvn spring-boot:run


POST → localhost:8081/prosioner/


{
"dna":["|||||||S||","|P ||   |","||  | | |","|v| | < |","| |   | |","|   |   |","|||||||||"]
}

En caso de verificar un prisionero, devuelve un HTTP 200-OK, en caso contrario un 403-Forbidden.