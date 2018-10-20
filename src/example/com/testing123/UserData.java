package example.com.testing123;

/**
 * Created by terry on 10/4/18
 */


import java.io.Serializable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import java.util.logging.*;


@ManagedBean(name = "userData", eager = true)
@SessionScoped
public class UserData implements Serializable {

    // Obtain a suitable logger.
    private static Logger logger = Logger.getLogger("com.UserData");

    private static int connectNum = 0;
    private static int getPresNum = 0;
    private List<President> records = new ArrayList<>();


    @PostConstruct
    public void init() {

        // In @PostConstruct (will be invoked immediately after construction and dependency/property injection).
        records = loadPresidents();
    }


    public List<President> loadPresidents() {
        ResultSet rs;
        PreparedStatement pst = null;
        Connection con = makeConnection();
        String stm = "Select * from Presidents ORDER BY beginTerm DESC";

        if (records.isEmpty()) {

            try {
                Statement stat = con.createStatement();

                rs = stat.executeQuery(stm);

                while (rs.next()) {

                    President president = new President();
                    president.setFirstName(rs.getString(1));
                    president.setLastName(rs.getString(2));
                    president.setBeginTerm(rs.getString(3));
                    president.setEndTerm(rs.getString(4));

                    records.add(president);
                    logger.info("presidentP " + president.getLastName());
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return records;
    }


    public Connection makeConnection() {
        Connection con = null;

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:8889/test" +
                    "?useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "", "");

            System.out.println("New President Connection " + connectNum + " completed.");
            connectNum++;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Connection Failed");
        }

        return con;
    }


    public List<President> getPresidents() {

        System.out.println("getPresidents " + getPresNum);
        getPresNum++;
        return records;
    }

}


