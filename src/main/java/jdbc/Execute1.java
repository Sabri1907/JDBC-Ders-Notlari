package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Execute1 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // 1. ADIM: Driver'a kaydol
        //NOT: Driver'a kaydolmadan asagida  DriverManager class'ina baglanamayiz.
        Class.forName("org.postgresql.Driver");

        // 2. ADIM: Database'e baglan
       Connection con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed","postgres","Sa.3814641");
       // get connection methodu Connection data tipi dondurdugu icin Connection variable'i olusturup atadik.

        // 3. ADIM: Statement olustur
        Statement st=con.createStatement();
        // createstatement() methodu statement data tipi dondurdugu icin Statement variable'i olusturup atadik.

        //4.ADIM : Query Calistir.

        //1.ORNEK: "workers" adında bir table oluşturup "worker_id,worker_name, worker_salary" sütunlarını ekleyin.
        String sql1="create table workers(worker_id varchar(50), worker_name varchar(50), worker_salary int)";
        boolean result=st.execute(sql1);
        System.out.println(result); // false => Cunku data cagrilmadi sadece sql'e tablo olusturmasi icin mesaj gonderildi

        //2.Örnek: Alter table by adding worker_address column into the workers table
        String sql2="alter table workers add worker_adress varchar(80)";
        st.execute(sql2);

        //3.ORNEK: Drop workers table
        String sql3="drop table workers";
        st.execute(sql3);


        //5.ADIM : Baglanti ve statement'i kapat. (Proje icinde guvenlik amacli yapilir)
        // con.close();
        // st.close();




    }
}
