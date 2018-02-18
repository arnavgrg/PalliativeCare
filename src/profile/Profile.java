package profile;

import java.util.ArrayList;

public interface Profile {
    String name = "";
    int age = 0;
    String gender = "";
    ArrayList<String> interests = new ArrayList<String>();
    
    void setName(String name);
    
    void setAge(int age);
    
    void setGender(String pGender);
    
    void setLocation(String pLocation);
    
    public ArrayList<String> getInterests();
    
}
