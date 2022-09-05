package jdbc;

import java.sql.*;

public class CallableStatement01 {
    /*
    => Java'da; method'lar return type'i olsa da, void olsa da method olarak adlandirilir.
    => SQL'de ise; data return ediyorsa "function", return yapmiyorsa "procedure" olarak adlandirilir.
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed","postgres","Sa.3814641");
        Statement st =con.createStatement();

     //1.ORNEK : Iki parametre ile calisip bu parametreleri toplayarak return yapan bir fonksiyon yaziniz.

     //1.ADIM: Fonksiyon kodunu yaz
     String sql1="create or replace function toplamaF(x numeric, y numeric)\n" +
             "returns numeric\n" +
             "language plpgsql\n" +
             "as\n" +
             "$$ \n" +
             "begin \n" +
             "\n" +
             "return x+y; \n" +
             "\n" +
             "end \n" +
             "$$";

     //2.ADIM: Fonksiyonu calistir.
     st.execute(sql1);

     //3.ADIM: Fonksiyonu cagir
     CallableStatement cst1=con.prepareCall("{?=call toplamaF(?,?)}");
     // {} ici ilk soru isareti return type, () icindekiler ise toplanacak datalar

     //4.ADIM:Return icin registerOutParameter() methodunu, parametreler icin ise set() methodlarindan uygun olanlari kullan.
     cst1.registerOutParameter(1, Types.NUMERIC);
     cst1.setInt(2,18);
     cst1.setInt(3,33);

     //5.ADIM: Calistirmak icin execute() methodunu kullan
     cst1.execute();

     //6.ADIM:Sonucu cagirmak icin return data tipine gore get() methodlarindan uygun olani kullan.
        System.out.println(cst1.getBigDecimal(1)); // Numeric data tipi getBigDEcimal() methodu ile cagrilir.

        System.out.println("***************************2.ORNEK***********************************");

        //2.ORNEK: Koninin hacmini hesaplayan bir function yaziniz
        String sql2="create or replace function koniHacmiF(r numeric, h numeric)\n" +
                "returns numeric\n" +
                "language plpgsql\n" +
                "as\n" +
                "$$ \n" +
                "begin \n" +
                "\n" +
                "return 3.14*r*r*h/3; \n" +
                "\n" +
                "end \n" +
                "$$";

        //2.ADIM: Fonksiyonu calistir.
        st.execute(sql2);

        //3.ADIM: Fonksiyonu cagir
        CallableStatement cst2=con.prepareCall("{?=call koniHacim(?,?)}");
        // {} ici ilk soru isareti return type, () icindekiler ise toplanacak datalar

        //4.ADIM:Return icin registerOutParameter() methodunu, parametreler icin ise set() methodlarindan uygun olanlari kullan.
        cst2.registerOutParameter(1, Types.NUMERIC);
        cst2.setInt(2,3);
        cst2.setInt(3,5);

        //5.ADIM: Calistirmak icin execute() methodunu kullan
        cst2.execute();

        //6.ADIM:Sonucu cagirmak icin return data tipine gore get() methodlarindan uygun olani kullan.
        System.out.println(cst2.getBigDecimal(1)); // Numeric data tipi getBigDEcimal() methodu ile cagrilir.



    }
}
