package Classes;


import Entities.Database;

public class Login2 {
    private String email;
    private String password;
    private String rul;
    Login2 loginVar;
     public Login2(){}
    public Login2(String email, String password, String rul) {
        this.email = email;
        this.password = password;
        this.rul = rul;}
    public String getEmail(){
         return email;
    }
    public void setEmail(String email){
         this.email=email;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password=password;
    }
    public String getRul(){
        return rul;
    }
    public void setRul(String rul){
        this.rul=rul;
    }
    @Override
    public String toString(){
         return email+" "+password+" "+rul+"\r\n";
    }
    public String msg(){
         String message;
         if (rul.equals("admin")){
             message="Admin login success";}
         else if (rul.equals("customer")){
             message="Customer login success";}
         else{
             message="login fail";
         }
         return message;
    }
    public boolean isCorrectInfo() {
        int flag=0;
        for (Login2 logins: Database.users()){
            if(logins.getEmail().equals(email)&&logins.getPassword().equals(password)){
                this.loginVar =logins;
                flag=1;
                break;
            }
        }
        return flag==1;
    }
    public void login() {
        if (isCorrectInfo()) {
            if (loginVar.getRul().equals("admin")) {
                AdminDashboard admin = new AdminDashboard();
                admin.adminPage();
            } else if (loginVar.getRul().equals("customer")) {
                CustomerLogin customerLogin = new CustomerLogin();
                customerLogin.setCustomer(Database.GitCustomerbyE(email));
                customerLogin.customerPage();
            }
        }
    }
    public void adminLogin() {
        AdminDashboard admin = new AdminDashboard();
        admin.adminDashboard();
    }
    public void customerLogin() {
        CustomerLogin customerLogin = new CustomerLogin();
        customerLogin.setCustomer(Database.GitCustomerbyE(email));
        customerLogin.menu();
    }
    public void loginPage() {
        Main.menu();
    }
}



