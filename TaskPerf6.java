package taskperf6;

import java.nio.file.*;      
import java.util.*;
import java.io.*;
import java.util.regex.*;

        
public class TaskPerf6 {
    

 public static void main(String[] args) {
     Scanner a = new Scanner(System.in);
        try{
            File createdFile = new File("records.txt");
            if(createdFile.createNewFile()){
            }
        }catch(IOException e){
            System.out.println("An errored occured.");
        }
        
        Path file = Paths.get("records.txt");
        
        try{
            Scanner scanFile = new Scanner(file);
            System.out.print("Please Type R for Register and L for Login: ");
        
            String input = a.nextLine();
            String stringPattern = "[^a-zA-Z]";
            Pattern p = Pattern.compile(stringPattern);
            Matcher m = p.matcher(input);
            
            if(input.isEmpty()){
                throw new EmptyInput();
            }
            
            if(input.equalsIgnoreCase("L")){
                login(file, a);
            }else if(input.equalsIgnoreCase("R")){
                register(file, a);
            }else{
                throw new NotInTheSelectionException();
            }
        }catch(EmptyInput e){
            System.out.println(e.getMessage());
        }catch(NotInTheSelectionException e){
            System.out.println(e.getMessage());
        }catch(IOException e){
            System.out.println(e);
        }

    }
    
    public static void register(Path file, Scanner s){
        try{
            Scanner ScanFile = new Scanner(file);
            FileWriter write = new FileWriter("records.txt", true);
            BufferedWriter writer = new BufferedWriter(write);
            System.out.println("======Registration======");
            System.out.print("Username: ");
            String username = s.nextLine();
            username = username.replace(" ", "");
            
            System.out.print("Password: ");
            String password = s.nextLine();
            password = password.replace(" ", "");
            
            String stringPattern = "[^a-zA-Z0-9]";
            Pattern userp = Pattern.compile(stringPattern);
            Matcher userm = userp.matcher(username);
            Pattern passp = Pattern.compile(stringPattern);
            Matcher passm = passp.matcher(password);
            
            if(userm.find() || passm.find()){
                throw new NotAlphaNumeric();
            }else if(username.isEmpty() || password.isEmpty()){
                throw new EmptyInput();
            }else{
                System.out.println("Successfully Registered!");
            }
            
            String input = "Username:" + username + " Password:" + password + ",";
            
            writer.write(input,0,input.length());
            writer.close();
        }catch(NotAlphaNumeric e){
            System.out.println(e.getMessage());
        }catch(EmptyInput e){
            System.out.println(e.getMessage());
        }catch(IOException e){
            System.out.println(e);
        }
        
    }
    
    public static void login(Path file, Scanner s){
        try{
            System.out.println("======Log in======");
            System.out.print("Username: ");
            String loginuser = s.nextLine();
            System.out.print("Password: ");
            String loginpass = s.nextLine();
            
            Scanner ScanFile = new Scanner(file);
            String[] data;
            String[] userandpass;
            String[] username;
            String[] password;
            if (ScanFile.hasNext()) {
                String message = "";
                data = ScanFile.nextLine().split(",");
                for(int i = 0; i < data.length; i++){
                    userandpass = data[i].split(" ");
                    username = userandpass[0].split(":");
                    password = userandpass[1].split(":");
                    if(loginuser.equals(username[1]) && loginpass.equals(password[1])){
                        message = "Succesfully logged in!";
                        break;
                    }else{
                        message = "Incorrect username or password";
                    }
                }
                System.out.println(message);
            }
            ScanFile.close();
        }catch(Exception e){
            System.out.println("Message: " + e);
        }
    }
    
}   