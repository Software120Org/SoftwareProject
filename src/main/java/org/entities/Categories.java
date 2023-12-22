package org.entities;
public class Categories {
    private Integer id;
    private String name;
    public Categories(){

    }
    public Categories(Integer id,String name){
        this.id=id;
        this.name=name;
    }
    public Categories(String name){
        this.id=Database.getCategoryId();
        this.name=name;
    }
    public Integer getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return  id +
                "," + name  +"\r\n";
    }
    public boolean isExitCategories(){
        boolean flag=false;
        for(Categories category:Database.getCategories()){
            if(category.getId().equals(this.id)){
                flag=true;
                break;
            }
        }
        return flag;
    }
}
