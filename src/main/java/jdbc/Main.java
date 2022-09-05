package jdbc;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {

        //DBWork objesi olustur
        DBWork db=new DBWork();

        // Connection fonksiyonunu database'den cagir.
        Connection con=db.connect_to_db("techproed","postgres","Sa.3814641");

        //Yeni table olusturma methodunu cagir
        db.createTable(con,"employees");


    }
}
