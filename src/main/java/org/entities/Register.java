package org.entities;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Register {
    private int id ;
    private String name ;
    private String password;
    private String email;
    private String phone;
    private String address;
    private Register existCustomer;
    public Register(){}
    public Register(int id, String name, String email, String phone, String address, String password){
        this.id=id;
        this.name=name;
        this.password=password;
        this.email=email;
        this.phone=phone;
        this.address=address;
    }
    public Register(String name, String email, String phone, String address, String password){
       this.id = Database.getId();
        this.name=name;
        this.password=password;
        this.email=email;
        this.phone=phone;
        this.address=address;
    }
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id= id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name= name;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password= password;
    }
    public String getPhone(){
        return phone;
    }
    public void setPhone(String phone){
        this.phone= phone;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email= email;
    }
    public String getAddress(){
        return address;
    }
    public void setAddress(String address){
        this.address= address;
    }
    @Override
    public String toString() {
        return  id +
                "," + name +
                ","  +email+
                "," + phone +
                "," + address +
                "," + password +"\n";
    }
    public boolean isUnUniqueEmail() {
        List<Register>Customers;
        Customers=Database.getCustomer();
        int flag =0;
        for(Register customer: Customers){
        if(customer.getEmail().equals(this.getEmail())){
        flag=1;

        this.existCustomer=customer;
            break;
        } }
        return flag == 1;

    }
public boolean isValidEmail(){
    String enterEmail="^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,9}$";
    Pattern pattern=Pattern.compile(enterEmail,Pattern.CASE_INSENSITIVE);
    Matcher matcher=pattern.matcher(this.getEmail());
    return matcher.find();
}
    public boolean isExitCustomer(){
        boolean flag=false;
        for(Register customer: Database.getCustomer()){
            if(customer.getId()==this.id){
                flag=true;
                break;
            }
        }
        return flag;
    }
    public void sendEmail(String title,String message,String msg){
        final String user = "rubasalon5@gmail.com";
        final String emailPass = "wnjj@120";
        String to = this.getEmail();
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.starttls.enable", "true");
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected javax.mail.PasswordAuthentication getPasswordAuthentication(){
                        return new javax.mail.PasswordAuthentication(user,emailPass);
                    }

                });
        try {
            Message message1 = new MimeMessage(session);
            message1.setFrom(new InternetAddress(user));
            message1.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message1.setSubject(title);
            message1.setText(message);
            Transport.send(message1);
            logger.info(msg);
        } catch (Exception ignored) { //ignored
        }}
    static Logger logger = Logger.getLogger(Register.class.getName());
}