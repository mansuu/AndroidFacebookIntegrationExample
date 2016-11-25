package facebookintegrationexample.com.androidfacebookintegrationexample.user;

/**
 * Created by Himanshu on 11/22/2016.
 */

public class User {
    private static User user;
    private String name,facebookId, gender,profilePictureUrl,numberOfFriends,email,dateOfBirth,faceBookLink;

    private User(){

    }
    public static User getInstance(){
        if(user==null){
            user=new User();
        }
        return user;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    public String getNumberOfFriends() {
        return numberOfFriends;
    }

    public void setNumberOfFriends(String numberOfFriends) {
        this.numberOfFriends = numberOfFriends;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getFaceBookLink() {
        return faceBookLink;
    }

    public void setFaceBookLink(String faceBookLink) {
        this.faceBookLink = faceBookLink;
    }
}
