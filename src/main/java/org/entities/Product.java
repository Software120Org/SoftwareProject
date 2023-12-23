package org.entities;
import java.util.Objects;
public class Product {
    private String productName;
    private String description;
    private Category category;
    private Double price;
    private Boolean availability;
    private Integer productId;
    public  Product(){}
    public Product(Builder builder) {
        setProductId(builder.productId);
        setProductName(builder.productName);
        setDescription(builder.description);
        setCategory(builder.category);
        setPrice(builder.price);
        setAvailability(builder.availability);
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public String getCategory() {
        return category.getName();
    }
    public void setCategory(Category category) {
        this.category = category;
    }
    public Boolean getAvailability() {
        return availability;
    }
    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }
    public Integer getProductId() {
        return productId;
    }
    public void setProductId(Integer productId) {
        this.productId = productId;
    }
    public boolean isExitProduct(){
        boolean flag=false;
        for(Product product:ProductInfo.getProduct()){
            if(product.getProductId().equals(this.getProductId())){
                flag=true;
                break;
            }
        }
        return flag;}
    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s,%s"+"\n", productId, productName, description, category,price==null?"":price,availability==null?"":availability);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return productId.equals(product.productId) && Objects.equals(productName, product.productName) && Objects.equals(description, product.description) && category == product.category && availability == product.availability && price.equals(product.price);
    }
    @Override
    public int hashCode() {
        return Objects.hash(productName, description, category, availability, price, productId);
    }
    public static Builder builder(){
        return new Builder();
    }
    public static final class Builder {
        private String productName;
        private String description;
        private Category category;
        private Double price;
        private Boolean availability;
        private Integer productId;
        private Builder() {
            productName=null;
            description=null;
            category=null;
            availability=null;

        }
        /**
         * Sets the {@code name} and returns a reference to this Builder enabling method chaining.
         *
         * @param name the {@code name} to set
         * @return a reference to this Builder
         */
        public Builder setName(String name) {
            this.productName = name;
            return this;
        }
        public Builder setProductId(Integer productId) {
            this.productId = productId;
            return this;
        }
        /**
         * Sets the {@code description} and returns a reference to this Builder enabling method chaining.
         *
         * @param description the {@code pictureName} to set
         * @return a reference to this Builder
         */
        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }
        /**
         * Returns a {@code Product} built from the parameters previously set.
         *
         * @return a {@code Product} built with parameters of this {@code Product.Builder}
         */
        public Builder setPrice(Double price) {
            this.price = price;
            return this;
        }
        /**
         * Sets the {@code availability} and returns a reference to this Builder enabling method chaining.
         *
         * @param availability the {@code availability} to set
         * @return a reference to this Builder
         */
        public Builder setAvailability(Boolean availability) {
            this.availability = availability;
            return this;
        }

        /**
         * Sets the {@code category} and returns a reference to this Builder enabling method chaining.
         *
         * @param category the {@code category} to set
         * @return a reference to this Builder
         */
        public Builder setCategory(Category category) {
            this.category = category;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }
}
