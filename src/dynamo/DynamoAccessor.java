package dynamo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.QueryOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType;

import profile.Profile;

public class DynamoAccessor {
    private static Table table;
    
    public static void main(String[] args) {
	persistToTable(null);
	
    }
    public static void createTable(String tableName) {
	AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
		.build();

	DynamoDB dynamoDB = new DynamoDB(client);

	try {
	    System.out.println("Attempting to create table; please wait...");
	    
	    // TODO: format the schema 
	    table = dynamoDB.createTable(tableName,
		    Arrays.asList(new KeySchemaElement("year", KeyType.HASH), // Partition
			    // key
			    new KeySchemaElement("title", KeyType.RANGE)), // Sort key
		    Arrays.asList(new AttributeDefinition("year", ScalarAttributeType.N),
			    new AttributeDefinition("title", ScalarAttributeType.S)),
		    new ProvisionedThroughput(10L, 10L));
	    table.waitForActive();
	    System.out.println("Success.  Table status: " + table.getDescription().getTableStatus());

	}
	catch (Exception e) {
	    System.err.println("Unable to create table: ");
	    System.err.println(e.getMessage());
	}
    }
    
    public static boolean persistToTable(Profile profile) {
	try {
	    System.out.println("Adding a new item...");
	    // TODO: change how to put item once profile finalized
	    PutItemOutcome outcome = table
		    .putItem(new Item().withPrimaryKey("year", 2012).withString("title", "2012"));

	    System.out.println("PutItem succeeded:\n" + outcome.getPutItemResult());
	    return true;
	}
	catch (Exception e) {
	    System.err.println("Unable to add item: "/* + year + " " + title*/);
	    System.err.println(e.getMessage());
	}   
	return false;
    }
    
    // TODO: change return type into dataset format
    public static void fetchFromTable(String partitionKey, String sortKey) {
	HashMap<String, String> nameMap = new HashMap<String, String>();
	nameMap.put("#yr", "year");

	HashMap<String, Object> valueMap = new HashMap<String, Object>();
	valueMap.put(":yyyy", 1985);

	QuerySpec querySpec = new QuerySpec().withKeyConditionExpression("#yr = :yyyy").withNameMap(nameMap)
		.withValueMap(valueMap);

	ItemCollection<QueryOutcome> items = null;
	Iterator<Item> iterator = null;
	Item item = null;

	try {
	    System.out.println("Movies from 1985");
	    items = table.query(querySpec);

	    iterator = items.iterator();
	    while (iterator.hasNext()) {
		item = iterator.next();
		System.out.println(item.getNumber("year") + ": " + item.getString("title"));
	    }

	}
	catch (Exception e) {
	    System.err.println("Unable to query movies from 1985");
	    System.err.println(e.getMessage());
	}

	valueMap.put(":yyyy", 1992);
	valueMap.put(":letter1", "A");
	valueMap.put(":letter2", "L");

	querySpec.withProjectionExpression("#yr, title, info.genres, info.actors[0]")
	.withKeyConditionExpression("#yr = :yyyy and title between :letter1 and :letter2").withNameMap(nameMap)
	.withValueMap(valueMap);

	try {
	    System.out.println("Movies from 1992 - titles A-L, with genres and lead actor");
	    items = table.query(querySpec);

	    iterator = items.iterator();
	    while (iterator.hasNext()) {
		item = iterator.next();
		System.out.println(item.getNumber("year") + ": " + item.getString("title") + " " + item.getMap("info"));
	    }

	}
	catch (Exception e) {
	    System.err.println("Unable to query movies from 1992:");
	    System.err.println(e.getMessage());
	}
   }
}
