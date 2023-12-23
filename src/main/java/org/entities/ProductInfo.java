package org.entities;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
public class ProductInfo {
    static RandomAccessFile fromFile;
    static  final Logger logger = Logger.getLogger(ProductInfo.class.getName());
    private ProductInfo(){}
    public static List<Product> getProduct() {
        List<Product> products = new ArrayList<>();
        try {
            fromFile = new RandomAccessFile("src/main/resources/BE/Products.txt", "rw");
            fromFile.seek(0);
            String productSplit;
            Product product;
            while ((productSplit = fromFile.readLine()) != null) {
                String[] arr = productSplit.split(",");
                product = Product
                        .builder()
                        .setProductId(Integer.parseInt(arr[0]))
                        .setName(arr[1])
                        .setDescription(arr[2])
                        .setCategory(Category.valueOf(arr[3]))
                        .setPrice(Double.parseDouble(arr[4]))
                        .setAvailability(Boolean.valueOf(arr[5]))
                        .build();
                products.add(product);
            }
            fromFile.close();
        } catch (Exception e) { //ignored
        }
        return products;
      }
      public static  List<Product> searchProduct(String searchName){
        List<Product> products = new  ArrayList<>();
        try {
            fromFile = new RandomAccessFile("src/main/resources/BE/Products.txt", "rw");
            fromFile.seek(0);
            String productSplit;
            Product product;
            while ((productSplit = fromFile.readLine()) != null) {
                String[] arr = productSplit.split(",");
                if(arr[1].equalsIgnoreCase(searchName)){
                    product = Product
                            .builder()
                            .setProductId(Integer.parseInt(arr[0]))
                            .setName(arr[1])
                            .setDescription(arr[2])
                            .setCategory(Category.valueOf(arr[3]))
                            .setPrice(Double.parseDouble(arr[4]))
                            .setAvailability(Boolean.valueOf(arr[5]))
                            .build();
                    products.add(product);
                }
            }
            fromFile.close();
        }catch (Exception e) {
            logger.info(e.getMessage());
        }
         return products;
      }
    public static Product getProductByProductId(int id){
        Product findProducts=new Product();
        for (Product product1:getProduct()){
            if(product1.getProductId()==id){
                findProducts=product1;

                break;
            }
        }
        return findProducts;
    }
    public static int getProductId() {
        int id;
        id=getProduct().get(getProduct().size()-1).getProductId();
        return id+1;
    }
    }