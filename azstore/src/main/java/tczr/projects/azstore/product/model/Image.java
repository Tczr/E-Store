package tczr.projects.azstore.product.model;

import java.util.Objects;

public class Image {
    private Integer image_id;
    private  String image_url, image_name, image_description;


    public Image(Integer image_id,String image_url) {
        this.image_id=image_id;
        this.image_url = image_url;
    }
    public Image(String image_url) {
        this.image_url = image_url;
    }

    public Image(String image_url, String image_name) {
        this.image_url = image_url;
        this.image_name = image_name;
    }

    public Image(Integer image_id, String image_url, String image_name, String image_description) {
        this.image_id = image_id;
        this.image_url = image_url;
        this.image_name = image_name;
        this.image_description = image_description;
    }

    public Integer getImage_id() {
        return image_id;
    }

    public void setImage_id(Integer image_id) {
        this.image_id = image_id;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getImage_name() {
        return image_name;
    }

    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }

    public String getImage_description() {
        return image_description;
    }

    public void setImage_description(String image_description) {
        this.image_description = image_description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return Objects.equals(image_id, image.image_id)
                && Objects.equals(image_url, image.image_url)
                && Objects.equals(image_name, image.image_name)
                && Objects.equals(image_description, image.image_description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(image_id, image_url, image_name, image_description);
    }

    @Override
    public String toString() {
        return "Image{" +
                "image_id=" + image_id +
                ", image_url='" + image_url + '\'' +
                ", image_name='" + image_name + '\'' +
                ", image_description='" + image_description + '\'' +
                '}';
    }
}
