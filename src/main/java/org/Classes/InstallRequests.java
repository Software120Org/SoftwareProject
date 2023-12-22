        package org.Classes;

        import java.util.InputMismatchException;
        import java.util.List;
        import java.util.Scanner;
        import java.util.logging.Logger;
        import java.util.regex.Matcher;
        import java.util.regex.Pattern;


        import org.entities.Database;
        import org.entities.Installation;
        import org.entities.addInstallDate;


        public class InstallRequests {
            public static Logger logger=Logger.getLogger(InstallRequests.class.getName());

            public Installation recordInstallation(){

                Scanner in=new Scanner(System.in);
                Installation install = new Installation();
                logger.info("Enter product name");
                install.setProduct(in.nextLine());
                logger.info("Enter car make ");
                install.setMake(in.nextLine());
                logger.info("Enter car model ");
                install.setModel(in.nextLine());
                printInstallations();
                String[] dateAndTime = insatllid();

                if (dateAndTime != null && dateAndTime.length == 2) {
                    install.setDate(dateAndTime[0]);
                    install.setTime(dateAndTime[1]);
                }

                logger.info("Enter the email you want to receive notifications on ");
                String email = in.nextLine();

                while (true) {
                    if (isValidEmail(email)) {
                        install.setEmail(email);
                        break; // Exit the while loop
                    } else {
                        System.out.println("Invalid Email... Please try another email!");
                        email = in.nextLine(); // Get a new email
                    }
                }
                install.setId(Database.getReqId());
                addNewInstall(install);
                return install;
        }
            public boolean isValidEmail(String email){

                String enterEmail="^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,9}$";
                Pattern pattern=Pattern.compile(enterEmail,Pattern.CASE_INSENSITIVE);
                Matcher matcher=pattern.matcher(email);
                return matcher.find();
            }
            public boolean isValidDate(String date){

                String dateRegex = "^\\d{4}-\\d{2}-\\d{2}$";
                Pattern pattern=Pattern.compile(dateRegex,Pattern.CASE_INSENSITIVE);
                Matcher matcher=pattern.matcher(date);
                return matcher.find();
            }
            public void addNewInstall(Installation installation) {
                Database.storeObject("install",installation);
                logger.info("The request has been submitted successfully, Thank you!");
            }
            public void printInstallations() {
                List<addInstallDate> addInstallDates = Database.gitDate();
                logger.info("******************* Installation dates ********************");
                logger.info("ID            Date            Time            Status");
                for (addInstallDate installDate : addInstallDates) {
                    logger.info(installDate.getId() + "\t\t\t\t" + installDate.getDate() + "\t\t" +
                            installDate.getTime() + "\t\t" + installDate.getStatus());
                }
            }
            public String[] insatllid() {
                Scanner in = new Scanner(System.in);
                addInstallDate addInstallDate = new addInstallDate();
                boolean flag = false;
                String[] dateAndTime = null;

                while (!flag) {
                    try {
                        logger.info("Enter the ID of the ( Available ) Installations date you want to find: ");
                        int id = in.nextInt();
                        addInstallDate.setId(id);
                        flag = addInstallDate.isExitdate();
                        if (flag) {

                            dateAndTime = installBYid(addInstallDate);
                            addInstallDate = Database.getDateById(id);
                            addInstallDate.setId(id);
                            updateDate(addInstallDate);

                        } else {
                            logger.info("This date doesn't exist! Please enter a valid ID.");
                        }
                    } catch (InputMismatchException e) {
                        logger.info("Invalid Input! Please enter a valid ID.");
                        in.next(); // Consume the invalid input to avoid an infinite loop
                    }
                }

                return dateAndTime;
            }
            public void updateDate( addInstallDate addInstallDate){
                addInstallDate.setStatus("Not available");
                List<addInstallDate> datte = Database.gitDate();
                for (addInstallDate dd : datte) {
                    int ind = datte.indexOf(dd);
                    if (dd.getId()==addInstallDate.getId()) {
                        datte.remove(ind);
                        datte.add(ind,addInstallDate);
                        logger.info("Date updated successfully :)");
                        break;
                    }
                }
                Database.updateInstallation(datte);


            }


            public String[] installBYid(addInstallDate addInstallDate) {
                List<addInstallDate> addInstallDates = Database.gitDate();
                String[] dateAndTime = new String[2];

                for (addInstallDate install : addInstallDates) {
                    if (install.getId() == addInstallDate.getId()) {
                        dateAndTime[0] = install.getDate();
                        dateAndTime[1] = install.getTime();
                        break;
                    }
                }

                return dateAndTime;
            }
            //*********************************





        }
