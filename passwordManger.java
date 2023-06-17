import java.util.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Paths;

class Info{

    String web;
    String user;
    String pass;

    //getters
    public String getWeb(){
        return web;
    }

    public String getUser(){
        return user;
    }
 
    public String getPass(){
        return pass;
    }

    //setter
    public Info(String web, String user, String pass ){
        this.web = web;
        this.user = user;
        this.pass = pass;
    }


}

public class passwordManager{

    public static int findWeb(List<Info> pwords, String findInfo){

        for(int i = 0; i < pwords.size(); i++){
            if(pwords.get(i).getWeb().equalsIgnoreCase(findInfo)){
                return i;
                
            }
        }
        return -1;
    }

    public static void main(String args[]) throws IOException{
        
        int flag = 0; //0 = do not continue looping, 1 = continue looping
        Scanner input = new Scanner(System.in);
        List<Info> passwords = new ArrayList<>();
        String masterPass = "";
        /*beggining ideas of the password function. Idea is if output.txt already exists then this is not
        the first time user is starting program. Set a flag for first time running, if it is indeed the
        first time running program will prompt user to make a username and password for the password manager,
        said password will be saved to the output.txt file.*/

        if(Paths.get("C:\\Users\\baind\\OneDrive\\Desktop\\Java Projects\\output.txt").toFile().exists()){
            System.out.println("Welcome back to your password manager! Please enter your password");
            BufferedReader reader = new BufferedReader(new FileReader("output.txt"));
            for(int i = 0; i < 5; i++){
                String pwordCheck = input.nextLine();
                masterPass = reader.readLine();
                if(!pwordCheck.equalsIgnoreCase(masterPass)){
                    System.out.println("WRONG PASSWORD, TRY AGAIN");
                    if(i == 4){
                        //maybe make option after 5 failed attempts to either shut down or delete password file
                        System.out.println("EXCEEDED 5 ATTEMPTS, SHUTTING DOWN NOW");
                        System.exit(0);
                    }
                }else{
                    break;
                }
            }
            String line = reader.readLine();
            while(line != null){
                String inpweb = line.substring(0, line.indexOf("!"));
                String inpuser = line.substring(line.indexOf("!") + 1, line.indexOf("@"));
                String inppass = line.substring(line.indexOf("@") + 1);
                Info oldInfo = new Info(inpweb, inpuser, inppass);
                passwords.add(oldInfo);
                line = reader.readLine();
            }
            reader.close();
        }else{
            File opt = new File("C:\\Users\\baind\\OneDrive\\Desktop\\Java Projects\\output.txt");
            opt.createNewFile();
            System.out.print("Welcome to your password manager! Please enter a password to secure your saved passwords: ");
            masterPass = input.nextLine();
            System.out.println("WARNING: DO NOT FORGET THIS PASSWORD, IT IS REQUIRED TO ACCESS YOUR SAVED PASSWORDS!");
        }
        FileWriter writer = new FileWriter("output.txt");
        do{
            if(flag == 0){
                System.out.println("Welcome to your password manager! Enter \"Find\" to find your password, enter \"Add\" to add a new password, enter \"Change\" to change the information of a password, enter \"Delete\" to delete a password and the information related to it, or enter \"Quit\" to exit.");
            }else{
                System.out.println("Enter \"Find\" to find your password, enter \"Add\" to add a new password, or enter \"Change\" to change the information of a password");
            }
            String choice = input.nextLine();

            if(choice.equalsIgnoreCase("Add")){
                System.out.println("Please enter the website the password relates to: ");
                String web = input.nextLine();
                if(findWeb(passwords, web) != -1){
                    System.out.println("ERROR: WEBSITE ALREADY ENTERED, USE CHANGE FUNCTION");
                }else{
                    System.out.println("Please enter the username the password relates to: ");
                    String user = input.nextLine();
                    System.out.println("Please enter the password: ");
                    String pass = input.nextLine();
                    Info insertInfo = new Info(web, user, pass);
                    passwords.add(insertInfo);
                }
                
            }else if(choice.equalsIgnoreCase("Find")){
                
                System.out.println("Please enter the website in which you would like to find the information for.");
                String findInfo = input.nextLine();
                int position = findWeb(passwords, findInfo);
                if(position == -1){
                    System.out.println("ERROR: WEBSITE NOT FOUND, TRY AGAIN");
                }else{
                    System.out.println("Username: " + passwords.get(position).getUser());
                    System.out.println("Password: " + passwords.get(position).getPass());
                }
                

            }else if(choice.equalsIgnoreCase("Change")){
                System.out.println("Please enter the website in which you would like to change the information for.");
                String changeInfoWeb = input.nextLine();
                int position = findWeb(passwords, changeInfoWeb);
                System.out.println("Do you want to change the username or password?");
                String userOrPass = input.nextLine();
                
                if(userOrPass.equalsIgnoreCase("username")){
                    System.out.print("Enter the new username:");
                    String newUser = input.nextLine();
                    String oldWeb = passwords.get(position).getWeb();
                    String oldPass = passwords.get(position).getPass();

                    passwords.remove(position);
                    Info updatedUser = new Info(oldWeb, newUser, oldPass);
                    passwords.add(updatedUser);

                }else if(userOrPass.equalsIgnoreCase("password")){
                    System.out.print("Enter the new password:");
                    String newPass = input.nextLine();
                    String oldWeb = passwords.get(position).getWeb();
                    String oldUser = passwords.get(position).getUser();

                    passwords.remove(position);
                    Info updatedUser = new Info(oldWeb, oldUser, newPass);
                    passwords.add(updatedUser);
                }else{
                    System.out.println("ERROR: INVALID INPUT");
                }
                System.out.println("Current information; ");
                System.out.println("Website: " + passwords.get(position).getWeb());
                System.out.println("Username: " + passwords.get(position).getUser());
                System.out.println("Password: " + passwords.get(position).getPass());
                

            }else if(choice.equalsIgnoreCase("Delete")){
                System.out.println("Please enter the website in which you would like to delete the information for.");
                String findInfo = input.nextLine();
                int position = findWeb(passwords, findInfo);
                passwords.remove(position);

            }else if(choice.equalsIgnoreCase("Quit")){
                System.exit(0);
            
            }else{
                System.out.println("Invalid Input!");
            }

            System.out.println("Would you like to continue? Y or N?");
            String cont = input.nextLine();
            if(cont.equalsIgnoreCase("Y")){
                flag = 1;
            }else{
                flag = 0;
                writer.write(masterPass + "\n");
                for(int i = 0; i < passwords.size(); i++){
                    writer.write(passwords.get(i).getWeb() + "!");
                    writer.write(passwords.get(i).getUser() + "@");
                    writer.write(passwords.get(i).getPass());
                    writer.write("\n");

                }
                
            }
        }while(flag == 1);
        
        writer.close();
        input.close();
    }
}
