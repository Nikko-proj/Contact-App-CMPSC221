// Nikko Bernadit CMPSC 221 Lab 4 
// Controller class for Contact_app
// this class is used to define the various functions for
// the buttons, listview, and imageview.
package contact_app;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;
import javafx.stage.FileChooser;


public class Contact_app implements Initializable {
    public Button btnAdd; // fxid for add button
    public Button btnSave; // fxid for save button
    public Button btnDelete; // fxid for del button
    public ImageView ivContact; // fxid for imageview
    public TextField txtPhone; // fxid for phone number
    public TextField txtLastName; // fxid for last name
    public TextField txtFirstName; // fxid for first name
    public TextField txtEmail; // fxid for email
    public TextField txtAddress; // fxid for home address
    public ListView<Contact> listContact; // listview array pf contacts

    private Contact currentContact; // current contact index 
    private Image currentImage ; // current image index

    private FileChooser fileChooser; // for choosing image files

    private ObservableList<Contact> contactObservableList;

    // adds new contact by clearing all fields and setting 
    // a new contact class and its' image to null
    public void addNewContact(ActionEvent actionEvent) {
        txtLastName.clear();
        txtFirstName.clear();
        txtEmail.clear();
        txtPhone.clear();
        txtAddress.clear();
        ivContact.setImage(null);
        currentContact = new Contact();
        currentImage = null;
    }

    // changes the contact photo of current contact
    public void changePhoto(MouseEvent mouseEvent) {
      File file  = fileChooser.showOpenDialog(ivContact.getScene().getWindow());
        try {
            currentImage = new Image(new FileInputStream(file));
            ivContact.setImage(currentImage);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // saves contact as long first name and last name fiels are entered
    public void saveContact(ActionEvent actionEvent) {
        if (txtLastName.getText().isEmpty() || txtFirstName.getText().isEmpty()){
            return;
        }

        currentContact.setLastName(txtLastName.getText());
        currentContact.setFirstName(txtFirstName.getText());
        currentContact.setEmail(txtEmail.getText());
        currentContact.setPhoneNumber(txtPhone.getText());
        currentContact.setHomeAddress(txtAddress.getText());
        currentContact.setPhoto(currentImage);

        // index for saving contacts in arraylist of Contact
        int index = contactObservableList.indexOf(currentContact);
        if (index < 0){
            contactObservableList.add(currentContact);
        }else{
            contactObservableList.set(index,null);
            contactObservableList.set(index,currentContact);
        }
        sortList(); // sorts list by first name
    }

    // sorts the contacts by comparing first name pointer
    private void sortList() {
        Comparator<Contact> comparator = (o1, o2) ->
                o1.getFirstName().compareToIgnoreCase(o2.getFirstName());
        FXCollections.sort(contactObservableList,comparator);
    }

    // if the current index is greater than zero
    // deletes and clears all fields
    public void deleteContact(ActionEvent actionEvent) {
        int index = contactObservableList.indexOf(currentContact);
        if (index >= 0){
            contactObservableList.remove(currentContact);
        }
        addNewContact(null);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        contactObservableList = FXCollections.observableArrayList();
        listContact.setItems(contactObservableList);
        listContact.setCellFactory(contactListView-> new ContactListViewCell());

        currentContact = new Contact();
        currentImage = null;


        // only allows .jpg and .png to be uploaded
        FileChooser.ExtensionFilter imageFilter = new
                FileChooser.ExtensionFilter("Images","*.jpg","*.png");
        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(imageFilter);

 
        
        listContact.setOnMouseClicked(clicked -> {
            if (listContact.getSelectionModel().getSelectedItem() != currentContact){
                currentContact = listContact.getSelectionModel().getSelectedItem();
                txtLastName.setText(currentContact.getLastName());
                txtFirstName.setText(currentContact.getFirstName());
                txtEmail.setText(currentContact.getEmail());
                txtPhone.setText(currentContact.getPhoneNumber());
                txtAddress.setText(currentContact.getHomeAddress());
                ivContact.setImage(currentContact.getPhoto());
            }
        });

    }
}

