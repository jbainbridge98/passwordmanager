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
                System.out.println("Welcome to your password manager! Enter \"Find\" to find your password, enter \"Add\" to add a new password, or enter \"Change\" to change the information of a password");
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
                //String changeInfo = input.nextLine();
                

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
