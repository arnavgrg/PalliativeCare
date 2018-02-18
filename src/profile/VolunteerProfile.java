package profile;

import java.util.ArrayList;

import com.google.gson.Gson;

public class VolunteerProfile implements Profile  {

    private Gson gson = new Gson();
    private String name;
    private String username;
    private String password;
    private String info;
    private int age;
    private ArrayList<String> interests = new ArrayList<String>();
    private String gender;
    private String location;
    private int userID;
    private int personalityID;
    private int rating;

    public void setInfo(String info) {
	this.info = info;
    }
    
    public int getPersonalityId() { return personalityID;  }
    
    public String getInfo() {
	return info;
    }
    
    public void setName(String pName) {
	name = pName;
    }
    
    public void setUserName(String userName) {
	this.username = userName;
    }

    public String getName() {
	return name;
    }

    public void setAge(int pAge) {
	age = pAge;
    }

    public int getAge() {
	return age;
    }
    public void setGender(String pGender) {
	gender = pGender;

    }
    public void setLocation(String pLocation) {
	location = pLocation;
    }

    public ArrayList<String> getInterests() {
	return interests;
    }

    @Override
    public String getUserName() {
	return username;
    }

    public void setPassword(String password) {
	// TODO Auto-generated method stub
	this.password = password;
    }

    @Override
    public void setPersonalityID(int personalityId) {
	// TODO Auto-generated method stub
	this.personalityID = personalityId;
    }

    @Override
    public String getPassword() {
	// TODO Auto-generated method stub
	return password;
    }

    @Override
    public String getGender() {
	// TODO Auto-generated method stub
	return gender;
    }
}
