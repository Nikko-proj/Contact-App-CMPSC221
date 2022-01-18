// Nikko Bernadit CMPSC 221 Lab 4
package contact_app;



import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
// class for listView photo cell
public class ContactListViewCell extends ListCell<Contact> {

    @FXML
    public ImageView photoImageView;
    @FXML
    public Label nameLabel;
    @FXML
    public AnchorPane rootPane;

    private FXMLLoader loaderFXML;

    @Override
    protected void updateItem(Contact item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null){ // if  empty or item is null
            setText(null); // Text is set to null
            setGraphic(null); // Graphic is set to Null
        }else
        {
            if (loaderFXML == null) // checks FXML file
            {
                try // this block is used for the listView photos
                {
                    loaderFXML = new FXMLLoader(getClass().getResource("contact_cell.fxml"));
                    loaderFXML.setController(this);
                    loaderFXML.load();
                } catch (IOException e) 
                {
                    e.printStackTrace(); // allows to view potential error
                }
            }
            nameLabel.setText(item.getFirstName() + " " + item.getLastName());
            photoImageView.setImage(item.getPhoto());
            setText(null);
            setGraphic(rootPane);
        }
    }
}
