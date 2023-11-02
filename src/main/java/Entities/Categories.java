package Entities;

public class Categories {
    private int id;
    private String name;
    private Categories existCategories;

    public Categories(){

    }

    public Categories(int id,String name){
        this.id=id;
        this.name=name;
    }

    public Categories(String name){
        this.id=Database.getCategoryId();
        this.name=name;
    }

    public int getId() {
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

    public Categories getCategories() {
        return existCategories;
    }

    @Override
    public String toString() {
        return  id +
                "," + name  +"\r\n";
    }
    public boolean isExitCategories(){
        boolean flag=false;
        for(Categories category:Database.getCategories()){
            if(category.getId()==this.id){
                flag=true;
                break;
            }
        }
        return flag;
    }




}
