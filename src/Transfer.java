import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Transfer {

    Scanner sc=new Scanner(System.in);
    Connection con;

    public void fundTransfer() {
        System.out.println("enter your account number");
        int your = sc.nextInt();
        System.out.println("enter other's person account number");
        int other = sc.nextInt();
        System.out.println("enter amount to be deposit");
        int deposit=sc.nextInt();

        try {
            con=DbConnection.createConnection();
            con.setAutoCommit(false);

            PreparedStatement preparedStatement1=con.prepareStatement("select balance from total_ammount where accountNo=?");
            preparedStatement1.setInt(1,your);
            ResultSet rs1=preparedStatement1.executeQuery();
            int balance1=0;
            while(rs1.next()){
                balance1=rs1.getInt(1);
            }

            PreparedStatement preparedStatement2=con.prepareStatement("select balance from total_ammount where accountNo=?");
            preparedStatement1.setInt(1,other);
            ResultSet rs2=preparedStatement1.executeQuery();
            int balance2=0;
            while(rs2.next()){
                balance2=rs2.getInt(1);
            }

            if(balance1>=deposit){

                balance1=balance1-deposit;
                balance2=balance2+deposit;

                PreparedStatement preparedStatement3=con.prepareStatement("update total_ammount set balance=? where accountNo=?");
                preparedStatement3.setInt(1,balance1);
                preparedStatement3.setInt(2,your);

                int val1=preparedStatement3.executeUpdate();



                PreparedStatement preparedStatement4=con.prepareStatement("update total_ammount set balance=? where accountNo=?");
                preparedStatement4.setInt(1,balance2);
                preparedStatement4.setInt(2,other);

                int val2=preparedStatement4.executeUpdate();


                Date date=new Date();

                SimpleDateFormat simpleDateFormat1=new SimpleDateFormat("dd/MM/yyyy");

                String date1=simpleDateFormat1.format(date);


                SimpleDateFormat simpleDateFormat2=new SimpleDateFormat("HH:mm:ss");

                String time1=simpleDateFormat2.format(date);


                PreparedStatement preparedStatement5=con.prepareStatement("insert into mini_statement values(?,?,?,?,?)");
                preparedStatement5.setInt(1,your);
                preparedStatement5.setInt(2,deposit);
                preparedStatement5.setString(3,"d");
                preparedStatement5.setString(4,date1);
                preparedStatement5.setString(5,time1);
                int val3=preparedStatement5.executeUpdate();


                PreparedStatement preparedStatement6=con.prepareStatement("insert into mini_statement values(?,?,?,?,?)");
                preparedStatement6.setInt(1,other);
                preparedStatement6.setInt(2,deposit);
                preparedStatement6.setString(3,"c");
                preparedStatement6.setString(4,date1);
                preparedStatement6.setString(5,time1);
                int val4=preparedStatement6.executeUpdate();

                if(val1>0 && val2>0 && val3>0 && val4>0){
                    con.commit();
                    System.out.println("Transaction successfully done");

                }else{
                    con.rollback();
                    System.out.println("Transaction ERROR");

                }

            }else{
                con.rollback();
                System.out.println("insufficient balance");
            }



        }catch (Exception e){

            try {
                con.rollback();
            }
           catch (Exception e1){
               e1.printStackTrace();
           }
            e.printStackTrace();


        }

    }


}
