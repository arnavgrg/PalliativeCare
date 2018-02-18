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
import com.amazonaws.services.dynamodbv2.document.UpdateItemOutcome;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.NameMap;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ReturnValue;
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType;
import com.google.gson.Gson;

import profile.PatientProfile;
import profile.Profile;
import profile.VolunteerProfile;

public class DynamoAccessor {
    private Table table;
    private AmazonDynamoDB client;
    private Gson gson = new Gson();
    
    public DynamoAccessor(AmazonDynamoDB client) {
	this.client = client;
    }
    public void createTable(String tableName) {
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
    
    // TODO: customize
    public void updateAddNewAttribute(AmazonDynamoDB client, String tableName,
	    String userName, String attributeName, String attributeValue) {
	DynamoDB dynamoDB = new DynamoDB(client);
	table = dynamoDB.getTable(tableName);
	System.out.println(tableName);
        try {
            UpdateItemSpec updateItemSpec = new UpdateItemSpec().withPrimaryKey("username", userName)
                .withUpdateExpression("set #na =:val1").withNameMap(new NameMap().with("#na", attributeName))
                .withValueMap(new ValueMap().withString(":val1", attributeValue)).withReturnValues(ReturnValue.ALL_NEW);

            UpdateItemOutcome outcome = table.updateItem(updateItemSpec);

            // Check the response.
            System.out.println("Printing item after adding new attribute...");
            System.out.println(outcome.getItem().toJSONPretty());

        }
        catch (Exception e) {
            System.err.println("Failed to add new attribute");
            System.err.println(e.getMessage());
        }
    }
    
    public boolean persistToTable(AmazonDynamoDB client, String tableName, Profile profile) {
	DynamoDB dynamoDB = new DynamoDB(client);
	table = dynamoDB.getTable(tableName);
	
	try {
	    System.out.println("Adding a new item...");
	    // TODO: change how to put item once profile finalized
	    PutItemOutcome outcome = table
		    .putItem(new Item().withPrimaryKey("username", profile.getUserName())
			    .withString("password", profile.getPassword())
			    .withInt("personalityId", profile.getPersonalityId())
			    );

	    System.out.println("PutItem succeeded:\n" + outcome.getPutItemResult());
	    return true;
	}
	catch (Exception e) {
	    System.err.println("Unable to add item: "/* + year + " " + title*/);
	    System.err.println(e.getMessage());
	}   
	return false;
    }
    
    public VolunteerProfile fetchFromTable(AmazonDynamoDB client, String tableName, 
	    String username) {
	DynamoDB dynamoDB = new DynamoDB(client);
	table = dynamoDB.getTable(tableName);
	
	QuerySpec spec = new QuerySpec()
		.withKeyConditionExpression("username = :v_id")
		.withValueMap(new ValueMap()
			.withString(":v_id", username));

	ItemCollection<QueryOutcome> items = table.query(spec);

	Iterator<Item> iterator = items.iterator();
	Item item = null;
	while (iterator.hasNext()) {
	    item = iterator.next();
	    System.out.println(item.toJSONPretty());
	    return gson.fromJson(item.toJSON(), VolunteerProfile.class);
	}
	return null;
    }
    
    public PatientProfile fetchPatientFromTable(AmazonDynamoDB client, String tableName, 
	    String username) {
	DynamoDB dynamoDB = new DynamoDB(client);
	table = dynamoDB.getTable(tableName);
	
	QuerySpec spec = new QuerySpec()
		.withKeyConditionExpression("username = :v_id")
		.withValueMap(new ValueMap()
			.withString(":v_id", username));

	ItemCollection<QueryOutcome> items = table.query(spec);

	Iterator<Item> iterator = items.iterator();
	Item item = null;
	while (iterator.hasNext()) {
	    item = iterator.next();
	    System.out.println(item.toJSONPretty());
	    return gson.fromJson(item.toJSON(), PatientProfile.class);
	}
	return null;
    }
}
