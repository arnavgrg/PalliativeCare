package personality;

import java.io.FileNotFoundException;
import java.io.FileReader;

import com.google.gson.stream.JsonReader;
import com.ibm.watson.developer_cloud.personality_insights.v3.PersonalityInsights;
import com.ibm.watson.developer_cloud.personality_insights.v3.model.Content;
import com.ibm.watson.developer_cloud.personality_insights.v3.model.Profile;
import com.ibm.watson.developer_cloud.personality_insights.v3.model.ProfileOptions;
import com.ibm.watson.developer_cloud.util.GsonSingleton;

public class PersonalityAnalyzer {
    
    public void analyzePersonality(String jsonFile) {
	PersonalityInsights service = new PersonalityInsights("4.2.1", 
		"c734cd37-5fa5-47be-9e54-898994dc8a25", "usEXdsU28omP");
	try {
	    JsonReader jReader = new JsonReader(new FileReader(jsonFile));
	    Content content = GsonSingleton.getGson().fromJson(jReader, Content.class);
	    ProfileOptions options = new ProfileOptions.Builder()
	      .content(content).consumptionPreferences(true)
	      .rawScores(true).build();
	    Profile profile = service.profile(options).execute();
	    System.out.println(profile);
	  } catch (FileNotFoundException e) {
	    e.printStackTrace();
	  }
	
	// TODO: format the profile 
    }
}
