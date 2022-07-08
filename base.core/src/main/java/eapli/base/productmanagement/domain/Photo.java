package eapli.base.productmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.util.HashCoder;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;
import java.io.File;
import java.util.regex.Pattern;

@Embeddable
public class Photo implements ValueObject, Comparable<Photo> {

    /**
     * The image's URL
     */
    private String url;

    /**
     * The image's name
     */
    private String name;

    /**
     * The image's description
     */
    private String description;

    /**
     * Creates a photo object that stores the url of the image
     * @param url The image's URL
     * @param name The image's name
     * @param description The image's description
     */
    public Photo(String url, String name, String description){
        Preconditions.noneNull(url, name, description);

        if (!Pattern.matches(".+[.]jpg$|.+[.]png$|.+[.]jpeg$|.+[.]svg$", url))
            throw new IllegalArgumentException("This file type is not supported");

        File f = new File(url);
        if(!f.exists()) {
            throw new IllegalArgumentException("No file found in selected directory");
        }

        this.url = url;
        this.name = name;
        this.description = description;
    }

    /**
     * empty constructor for ORM
     */
    protected Photo() {
        // for ORM
    }

    /**
     * creates a new photo object
     * @param url The image's URL
     * @param name The image's name
     * @param description The image's description
     * @return a new photo object
     */
    public static Photo valueOf(String url, String name, String description){
        return new Photo(url, name, description);
    }

    /**
     * Return true if both object are equal or false otherwise
     * @param o the other object
     * @return true if both object are equal or false otherwise
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Photo)) {
            return false;
        }

        final Photo other = (Photo) o;
        return url.equals(other.url) && name.equals(other.name);
    }

    /**
     * @return transforms this object to string form
     */
    @Override
    public String toString() {
        return String.format("Image name: %s\nImage Description: %s", name, description);
    }

    /**
     * @return the object's hashcode
     */
    @Override
    public int hashCode() {
        return new HashCoder().with(url).code();
    }

    /**
     * @param o the other object
     * @return 1 if the other object is smaller, 0 if they are equal and -1 if the other object is bigger
     */
    @Override
    public int compareTo(Photo o) {
        return name.compareTo(o.name);
    }
}
