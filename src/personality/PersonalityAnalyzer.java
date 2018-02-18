package personality;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import com.google.gson.stream.JsonReader;
import com.ibm.watson.developer_cloud.personality_insights.v3.PersonalityInsights;
import com.ibm.watson.developer_cloud.personality_insights.v3.model.Content;
import com.ibm.watson.developer_cloud.personality_insights.v3.model.Profile;
import com.ibm.watson.developer_cloud.personality_insights.v3.model.ProfileOptions;
import com.ibm.watson.developer_cloud.personality_insights.v3.model.Trait;
import com.ibm.watson.developer_cloud.util.GsonSingleton;

public class PersonalityAnalyzer {    
    public PersonalityAnalyzer() {}
    
    public int analyzePersonality(String jsonFile) {
	PersonalityInsights service = new PersonalityInsights("2017-10-13", 
		"c734cd37-5fa5-47be-9e54-898994dc8a25", "usEXdsU28omP");
	PersonalityType personType = new PersonalityType();
	ProfileOptions options = new ProfileOptions.Builder().text(jsonFile)
		.rawScores(true).build();
	Profile profile = service.profile(options).execute();
	System.out.println(profile);
	List<Trait> big5Traits = profile.getPersonality();

	double conscientiousness = 0, openness = 0, extraversion = 0, neuroticism = 0;
	for (Trait trait : big5Traits) {
	    switch (trait.getTraitId()) {
	    case "big5_agreeableness": 
		continue;
	    case "big5_conscientiousness":
		conscientiousness = trait.getPercentile();
		break;
	    case "big5_openness":
		openness = trait.getPercentile();
		break;
	    case "big5_extraversion":
		extraversion = trait.getPercentile();
		break;
	    case "big5_neuroticism":
		neuroticism = trait.getPercentile();
		break;
	    default:
		break;
	    }
	}
	personType.setPersonalityId(openness, conscientiousness, extraversion, neuroticism);
	System.out.println(personType.getPersonalityId());
	return personType.getPersonalityId();
	// TODO: format the profile 
    }
}