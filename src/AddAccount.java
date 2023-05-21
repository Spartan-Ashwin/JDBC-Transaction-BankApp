import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddAccount {

    public void addAccount(int id,String name,String email,String phone,int accNo) {
        Connection con=DbConnection.createConnection();



        try {

            //FOR Creating account
            con.setAutoCommit(false);

            PreparedStatement preparedStatement=con.prepareStatement("insert into users values(?,?,?,?,?)");
            preparedStatement.setInt(1,id);
            preparedStatement.setString(2,name);
            preparedStatement.setString(3,email);
            preparedStatement.setString(4,phone);
            preparedStatement.setInt(5,accNo);

            int val1=preparedStatement.executeUpdate();


            //For Depositing minimum balance

            PreparedStatement preparedStatement2=con.prepareStatement("insert into total_ammount values(?,?,?)");

            preparedStatement2.setInt(1,id);
            preparedStatement2.setInt(2,accNo);
            preparedStatement2.setInt(3,50000);

            int val2=preparedStatement2.executeUpdate();

            if(val1>0 && val2>0){
                con.commit();
                System.out.println("Account Created Successfully");
            }else{
                con.rollback();
                System.out.println("Account can not be created");
            }


        }catch (Exception e){

            try{
                con.rollback();
            }catch (Exception e1){
                e1.printStackTrace();
            }

            e.printStackTrace();
        }

    }
}
