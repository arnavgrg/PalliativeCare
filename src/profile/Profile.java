package profile;

import java.util.ArrayList;

public interface Profile {
    String username = "";
    String password = "";
    String name = "";
    String info = "";
    int age = 0;
    int personalityId = -1;
    String gender = "";
    ArrayList<String> interests = new ArrayList<String>();
    
    public String getPassword();
    public String getInfo();
    public String getName();
    public int getAge();
    public int getPersonalityId();
    public String getGender();
    
    public void setInfo(String info);
    
    public void setPersonalityID(int personalityId);

    public void setPassword(String password);
    
    public void setUserName(String username);
    
    public void setName(String name);
    
    public void setAge(int age);
    
    public void setGender(String pGender);
    
    public void setLocation(String pLocation);
    
    public ArrayList<String> getInterests();
    
    public String getUserName();
    
}
