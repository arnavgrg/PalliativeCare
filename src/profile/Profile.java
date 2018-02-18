package profile;

import java.util.ArrayList;

public interface Profile {
    String username = "";
    String password = "";
    String name = "";
    int age = 0;
    String gender = "";
    ArrayList<String> interests = new ArrayList<String>();
    
    public void setPassword(String password);
    
    public void setUserName(String username);
    
    public void setName(String name);
    
    public void setAge(int age);
    
    public void setGender(String pGender);
    
    public void setLocation(String pLocation);
    
    public ArrayList<String> getInterests();
    
    public String getUserName();
    
}
