//Nikko Bernadit CMPSC 221 Lab 4 Contact App
package contact_app;

import javafx.scene.image.Image;

public class Contact { // class file for contact attributes
    private String firstName;
    private String lastName;
    private String emailAddr;
    private String homeAddress;
    private String phoneNumber;
    private Image photo;


    public String getFirstName() { // returns firstName
        return firstName;
    }

    public void setFirstName(String firstName) { // sets firstName given String
        this.firstName = firstName;
    }

    public String getLastName() { // returns lastName
        return lastName;
    }

    public void setLastName(String lastName) { // sets lastName given String
        this.lastName = lastName;
    }

    public String getEmail() { // returns emaillAddr
        return emailAddr;
    }

    public void setEmail(String email) { // sets emaillAddr give String
        this.emailAddr = email;
    }

    public String getHomeAddress() { // returns homeAddr
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) { // sets homeAddr given String
        this.homeAddress = homeAddress;
    }

    public String getPhoneNumber() { // returns phoneNumber
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) { // sets phoneNumber given String
        this.phoneNumber = phoneNumber;
    }

    public Image getPhoto() { // returns photo
        return photo;
    }

    public void setPhoto(Image photo) { // sets photo give Image
        this.photo = photo;
    }
}
