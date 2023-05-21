import java.lang.ref.PhantomReference;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class GenerateMiniStatement {


    public  void miniStatement(int accNo){


        try {

            Connection con=DbConnection.createConnection();
            PreparedStatement ps=con.prepareStatement("select * from mini_statement where accountNo=?");

            ps.setInt(1,accNo);
            ResultSet rs=ps.executeQuery();

            while (rs.next()){
                System.out.print(rs.getInt(1)+"\t");
                System.out.print(rs.getInt(2)+"\t");
                System.out.print(rs.getString(3)+"\t");
                System.out.print(rs.getString(4)+'\t');
                System.out.print(rs.getString(5)+'\t');
                System.out.println();
            }


        }catch (Exception e){

        }
    }
}
