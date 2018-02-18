package profile;

import java.util.ArrayList;

import com.google.gson.Gson;

public class PatientProfile implements Profile {

    private Gson gson = new Gson();
    private String username;
    private String name;
    private String password;
    private int age;
    private ArrayList<String> interests = new ArrayList<String>();
    private String gender;
    private String location;
    private int userID;
    private int personalityID;
    private int rating;

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

    public String getUserName() {
	// TODO Auto-generated method stub
	return username;
    }

    public void setPassword(String password) {
	this.password = password;
    }

}
