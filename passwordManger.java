import java.util.*;


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

    public static void main(String args[]){
        
        int flag = 0;
        Scanner input = new Scanner(System.in);
        List<Info> passwords = new ArrayList<>();

        do{
            if(flag == 0){
                System.out.println("Welcome to your password manager! Enter \"Find\" to find your password, enter \"Add\" to add a new password, enter \"Change\" to change the information of a password, or enter \"Delete\" to delete a password and the information related to it.");
            }else{
                System.out.println("Enter \"Find\" to find your password, enter \"Add\" to add a new password, or enter \"Change\" to change the information of a password");
            }
            String choice = input.nextLine();


            if(choice.equalsIgnoreCase("Add")){
                System.out.println("Please enter the website the password relates to: ");
                String web = input.nextLine();
                System.out.println("Please enter the username the password relates to: ");
                String user = input.nextLine();
                System.out.println("Please enter the password: ");
                String pass = input.nextLine();
                Info insertInfo = new Info(web, user, pass);
                passwords.add(insertInfo);
                
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
            
            }else{
                System.out.println("Invalid Input!");
            }

            System.out.println("Would you like to continue? Y or N?");
            String cont = input.nextLine();
            if(cont.equalsIgnoreCase("Y")){
                flag = 1;
            }else{
                flag = 0;
            }
        }while(flag == 1);
        
        input.close();
    }
}
