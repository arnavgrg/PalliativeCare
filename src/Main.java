import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.google.gson.Gson;

import dynamo.DynamoAccessor;
import personality.PersonalityAnalyzer;
import profile.PatientProfile;
import profile.Profile;
import profile.VolunteerProfile;
import recommender.RecommenderSystem;

public class Main {
    public static void main(String[] args) {
  	AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
  	DynamoAccessor dbAccessor = new DynamoAccessor(client);
  	PersonalityAnalyzer analyzer = new PersonalityAnalyzer();
  	Gson gson = new Gson();
  	
  	VolunteerProfile volunteer = dbAccessor.fetchFromTable(client, "TestVolunteer", "johnsmith1");
  	System.out.print(volunteer.getUserName());
  	
  	// run the personality analyzer
  	int compatiblePersonalityId = analyzer.analyzePersonality(gson.toJson(volunteer, VolunteerProfile.class));
  	volunteer.setPersonalityID(compatiblePersonalityId);
  	dbAccessor.persistToTable(client, "TestVolunteer", volunteer);
  	
  	PatientProfile patient = dbAccessor.fetchPatientFromTable(client, "TestPatient", "sameul");
  	System.out.print(patient.getUserName());
  	
  	// run the personality analyzer
  	int patientPid = analyzer.analyzePersonality(gson.toJson(patient, PatientProfile.class));
  	volunteer.setPersonalityID(patientPid);
  	dbAccessor.persistToTable(client, "TestPatient", patient);
    }
}
