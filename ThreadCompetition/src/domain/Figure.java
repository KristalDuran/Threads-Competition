package domain;

import java.util.*;
import javafx.scene.image.Image;

/**
 * 
 */
public class Figure {

    public Figure() {
    }

    private String directionForm;
    private String directionImage;
    private String directionImageRevers;
    private Image image;
    private boolean isForm;
    
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