package jdbc;

import java.sql.*;

public class PreparedStatement01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed","postgres","Sa.3814641");
        Statement st =con.createStatement();

    //1. Örnek: Prepared statement kullanarak company adı IBM olan number_of_employees değerini 9999 olarak güncelleyin.

    //==> 1.ADIM: Prepared Statement Query'sini olustur
        String sql1="update companies set number_of_employees=? where company = ?";
    // Kodlarin dinamik olmasi icin (tekrar kullanilmasi icin) spesifik deger girmeyip ? koyduk.

    //==> 2.ADIM: PreparedStatement objesini olustur.
        PreparedStatement pst1=con.prepareStatement(sql1);

    //==> 3.ADIM: set() methodlari ile 1.Adimda ? koydugumuz yerlere istedigimiz degerleri atiyoruz
        pst1.setInt(1,9999);
        pst1.setString(2,"IBM");

    //==> 4.ADIM: Execute query (Sorguyu calistir)
        int updateRowSayisi= pst1.executeUpdate();
        System.out.println(updateRowSayisi+" satir guncellendi");

    // Tum tabloyu gorelim
        String sql2="select * from companies";
        ResultSet result2=st.executeQuery(sql2);
        while(result2.next()){
            System.out.println(result2.getInt(1)+" - "+result2.getString(2)
                               +" - "+result2.getInt(3));
        }

        //Google'i 15000 yapalim
        pst1.setInt(1,15000);
        pst1.setString(2,"GOOGLE");


        int updateRowSayisi2= pst1.executeUpdate();
        System.out.println(updateRowSayisi2+" satir guncellendi");

        // Tum tabloyu gorelim
        String sql3="select * from companies";
        ResultSet result3=st.executeQuery(sql3);
        while(result3.next()){
            System.out.println(result3.getInt(1)+" - "+result3.getString(2)
                    +" - "+result3.getInt(3));
        }

        System.out.println("**************************************************************");
    //2. Örnek: "SELECT * FROM <table name>" query'sini prepared statement ile kullanın.
        read_data(con,"companies");

        /*
        //1.ADIM => Query olustur
        String sql4="select * from ?";

        //2.ADIM => PreparedStatement objesini olustur.
        PreparedStatement pst2=con.prepareStatement(sql4);

        //3.ADIM => set() methoduyla guncelle
       // pst1.setString(1,"companies"); => Bo yontem calismadigi icin bunu kaldirdik.


        //4.ADIM => Query'yi calistir
        ResultSet result4=pst2.executeQuery(); // Update yapilmadigi icin executeQuery kullanildi

        //Sonucu gorelim
        while(result4.next()){
            System.out.println(result4.getInt(1)+"=>"+result4.getString(2)
                                              +"=>"+result4.getInt(3));
        }
*/
    }
    // Bir istenilen datasini Prepared Statement ile cagirmak icin kullanilan method.
    public static void read_data(Connection con,String tableName){

        try {
            String query = String.format("select * from %s", tableName);
            // String format %s method parametresini oraya otomatik atar. Dinamik string olusturur.

            Statement statement=con.createStatement();
            //=>SQL Query'yi calistir.
            ResultSet rs=statement.executeQuery(query); // Datayi cagirip ResultSet konteynirina koyduk

            while (rs.next()){ // Tum datayi cagiralim.

                System.out.println(rs.getString(1)+" - "+rs.getString(2)+" - "+rs.getInt(3));

            }
        } catch (Exception e){
            System.out.println(e);
        }
    }
}

