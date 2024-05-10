package org.springframework.samples.petclinic.rest.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.ArrayList;
import java.util.List;
import org.springframework.samples.petclinic.rest.dto.ItemDto;
import org.springframework.samples.petclinic.rest.dto.ProductDto;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * UserDto
 */

@JsonTypeName("User")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-05-11T01:54:42.548285900+08:00[Asia/Shanghai]")
public class UserDto {

  @JsonProperty("name")
  private String name;

  @JsonProperty("email")
  private String email;

  @JsonProperty("pass")
  private String pass;

  @JsonProperty("money")
  private Double money = null;

  @JsonProperty("address")
  private String address;

  @JsonProperty("contact")
  private String contact;

  @JsonProperty("image")
  private String image;

  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("uid")
  private Long uid = null;

  @JsonProperty("items")
  @Valid
  private List<ItemDto> items = null;

  @JsonProperty("products")
  @Valid
  private List<ProductDto> products = null;

  public UserDto name(String name) {
    this.name = name;
    return this;
  }

  /**
   * The name of the user.
   * @return name
  */
  @Pattern(regexp = "^[a-zA-Z]*$") @Size(min = 1, max = 30) 
  @Schema(name = "name", example = "George", description = "The name of the user.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public UserDto email(String email) {
    this.email = email;
    return this;
  }

  /**
   * The email of the user.
   * @return email
  */
  @Pattern(regexp = "^\\w+@\\w+\\.\\w+$") @Size(min = 1, max = 255) 
  @Schema(name = "email", example = "abc@ef.com", description = "The email of the user.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public UserDto pass(String pass) {
    this.pass = pass;
    return this;
  }

  /**
   * The password of the user.
   * @return pass
  */
  @Size(min = 1, max = 80) 
  @Schema(name = "pass", example = "123456789", description = "The password of the user.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  public String getPass() {
    return pass;
  }

  public void setPass(String pass) {
    this.pass = pass;
  }

  public UserDto money(Double money) {
    this.money = money;
    return this;
  }

  /**
   * The money of the user.
   * @return money
  */
  
  @Schema(name = "money", description = "The money of the user.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  public Double getMoney() {
    return money;
  }

  public void setMoney(Double money) {
    this.money = money;
  }

  public UserDto address(String address) {
    this.address = address;
    return this;
  }

  /**
   * Get address
   * @return address
  */
  @Pattern(regexp = "^[0-9]*$") @Size(min = 1, max = 20) 
  @Schema(name = "address", example = "10086", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public UserDto contact(String contact) {
    this.contact = contact;
    return this;
  }

  /**
   * Get contact
   * @return contact
  */
  @Pattern(regexp = "^\\d{0,20}$") 
  @Schema(name = "contact", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  public String getContact() {
    return contact;
  }

  public void setContact(String contact) {
    this.contact = contact;
  }

  public UserDto image(String image) {
    this.image = image;
    return this;
  }

  /**
   * Get image
   * @return image
  */
  
  @Schema(name = "image", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public UserDto id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  
  @Schema(name = "id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public UserDto uid(Long uid) {
    this.uid = uid;
    return this;
  }

  /**
   * Get uid
   * @return uid
  */
  @NotNull 
  @Schema(name = "uid", requiredMode = Schema.RequiredMode.REQUIRED)
  public Long getUid() {
    return uid;
  }

  public void setUid(Long uid) {
    this.uid = uid;
  }

  public UserDto items(List<ItemDto> items) {
    this.items = items;
    return this;
  }

  public UserDto addItemsItem(ItemDto itemsItem) {
    if (this.items == null) {
      this.items = new ArrayList<>();
    }
    this.items.add(itemsItem);
    return this;
  }

  /**
   * Get items
   * @return items
  */
  @Valid 
  @Schema(name = "items", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  public List<ItemDto> getItems() {
    return items;
  }

  public void setItems(List<ItemDto> items) {
    this.items = items;
  }

  public UserDto products(List<ProductDto> products) {
    this.products = products;
    return this;
  }

  public UserDto addProductsItem(ProductDto productsItem) {
    if (this.products == null) {
      this.products = new ArrayList<>();
    }
    this.products.add(productsItem);
    return this;
  }

  /**
   * Get products
   * @return products
  */
  @Valid 
  @Schema(name = "products", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  public List<ProductDto> getProducts() {
    return products;
  }

  public void setProducts(List<ProductDto> products) {
    this.products = products;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserDto user = (UserDto) o;
    return Objects.equals(this.name, user.name) &&
        Objects.equals(this.email, user.email) &&
        Objects.equals(this.pass, user.pass) &&
        Objects.equals(this.money, user.money) &&
        Objects.equals(this.address, user.address) &&
        Objects.equals(this.contact, user.contact) &&
        Objects.equals(this.image, user.image) &&
        Objects.equals(this.id, user.id) &&
        Objects.equals(this.uid, user.uid) &&
        Objects.equals(this.items, user.items) &&
        Objects.equals(this.products, user.products);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, email, pass, money, address, contact, image, id, uid, items, products);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserDto {\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    pass: ").append(toIndentedString(pass)).append("\n");
    sb.append("    money: ").append(toIndentedString(money)).append("\n");
    sb.append("    address: ").append(toIndentedString(address)).append("\n");
    sb.append("    contact: ").append(toIndentedString(contact)).append("\n");
    sb.append("    image: ").append(toIndentedString(image)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    uid: ").append(toIndentedString(uid)).append("\n");
    sb.append("    items: ").append(toIndentedString(items)).append("\n");
    sb.append("    products: ").append(toIndentedString(products)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

