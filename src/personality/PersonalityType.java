package personality;

public class PersonalityType {
    int personalityId;
    
    public PersonalityType() {
	this.personalityId = 0;
    }
    
    public void setPersonalityId(double openness, double conscientiousness,
	    double extraversion, double neuroticism) {
	int id = 0;
	if (openness >= 0.5) id += 8;
	if (conscientiousness >= 0.5) id += 4;
	if (extraversion >= 0.5) id += 2;
	if (neuroticism >= 0.5) id += 1;
	this.personalityId = id;
    }
    
    public int getPersonalityId() {
	return personalityId;
    }
}
