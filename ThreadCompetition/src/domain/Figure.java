package domain;

import java.util.*;
import javafx.scene.image.Image;

/**
 * This class is a figure that represent the object that the user can see in the panel.
 * @author Kristal Duran and Jose Camacho
 */
public class Figure {

    public Figure() {
    }

    private String directionForm; //this atribut is for the url of the figure
    private String directionImage; //this atribut is for the url of the imagen in the good direction
    private String directionImageRevers; //this atribut is for the url of the imagen that is on revert
    private Image image; //this atribut is the imagen of the avatar
    private boolean isForm; //this atribut say is the avatar is a figure or a picture
    
    public String getDirectionForm() {
        return directionForm;
    }

    public void setDirectionForm(String directionForm) {
        this.directionForm = directionForm;
    }

    public String getDirectionImage() {
        return directionImage;
    }

    public void setDirectionImage(String directionImage) {
        this.directionImage = directionImage;
    }

    public String getDirectionImageRevers() {
        return directionImageRevers;
    }

    public void setDirectionImageRevers(String directionImageRevers) {
        this.directionImageRevers = directionImageRevers;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public boolean isIsForm() {
        return isForm;
    }

    public void setIsForm(boolean isForm) {
        this.isForm = isForm;
    }
    
}