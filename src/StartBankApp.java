import java.util.Scanner;

public class StartBankApp {


    // add account , fund transfer, mini statement,exit

    Scanner sc=new Scanner(System.in);
    public void display(){

        System.out.println("1-> Add Account\n2-> Fund Transfer\n3-> Mini Statement\n4-> exit");
        operation();
    }

    public void operation(){
        int choice=sc.nextInt();

        if(choice==1){

            System.out.println("enter id");
            int id=sc.nextInt();
            System.out.println("enter name");
           sc.nextLine();
            String name=sc.nextLine();
            System.out.println("email");
           // sc.next();
            String email=sc.nextLine();
            System.out.println("phone");
           // sc.next();
            String phone=sc.nextLine();
            System.out.println("account no");
            int accNo=sc.nextInt();

            System.out.println(id+" "+name+" "+email+" "+phone+" "+accNo);
            AddAccount addAccount1=new AddAccount();
            addAccount1.addAccount(id,name,email,phone,accNo);

            display();

        }else if(choice==2){

            Transfer transfer=new Transfer();
            transfer.fundTransfer();
            display();
        }else if(choice==3){
            System.out.println("enter account number");
            int accNo=sc.nextInt();
            GenerateMiniStatement generateMiniStatement=new GenerateMiniStatement();
            generateMiniStatement.miniStatement(accNo);
            display();

        }else if(choice==4){
            System.out.println("Bank Application closed");
            System.exit(0);
        }

    }
}
