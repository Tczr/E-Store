package tczr.projects.azstore.costumer.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class CostumerAddress {

    private Integer address_id;

    private String address_line1, address_line2;

    private String city, country, postalCode;

    private LocalDateTime insertedAt, modifiedAt;

    public CostumerAddress(){};
    public CostumerAddress(String address_line1, String city, String country, String postalCode) {
        this.address_line1 = address_line1;
        this.city = city;
        this.country = country;
        this.postalCode = postalCode;
    }


    public CostumerAddress(String address_line1,
                           String address_line2,
                           String city,
                           String country,
                           String postalCode,
                           LocalDateTime insertedAt,
                           LocalDateTime modifiedAt) {

        this.address_line1 = address_line1;
        this.address_line2 = address_line2;
        this.city = city;
        this.country = country;
        this.postalCode = postalCode;
        this.insertedAt=insertedAt;
        this.modifiedAt=modifiedAt;
    }



    public Integer getAddress_id() {
        return address_id;
    }

    public void setAddress_id(Integer address_id) {
        this.address_id = address_id;
    }

    public String getAddress_line1() {
        return address_line1;
    }

    public void setAddress_line1(String address_line1) {
        this.address_line1 = address_line1;
    }

    public String getAddress_line2() {
        return address_line2;
    }

    public void setAddress_line2(String address_line2) {
        this.address_line2 = address_line2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CostumerAddress that = (CostumerAddress) o;
        return Objects.equals(address_id, that.address_id)
                && Objects.equals(address_line1, that.address_line1)
                && Objects.equals(address_line2, that.address_line2)
                && Objects.equals(city, that.city)
                && Objects.equals(country, that.country)
                && Objects.equals(postalCode, that.postalCode)
                && Objects.equals(insertedAt, that.insertedAt)
                && Objects.equals(modifiedAt, that.modifiedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address_id, address_line1, address_line2, city, country, postalCode, insertedAt, modifiedAt);
    }

    @Override
    public String toString() {
        return "CostumerAddress{" +
                "address_id=" + address_id +
                ", address_line1='" + address_line1 + '\'' +
                ", address_line2='" + address_line2 + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", insertedAt=" + insertedAt +
                ", modifiedAt=" + modifiedAt +
                '}';
    }
}
