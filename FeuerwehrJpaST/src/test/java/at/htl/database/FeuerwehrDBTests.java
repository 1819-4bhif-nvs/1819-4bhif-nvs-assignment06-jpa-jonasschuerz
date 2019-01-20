package at.htl.database;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class FeuerwehrDBTests {

    public static final String DRIVER_STRING = "org.apache.derby.jdbc.ClientDriver";
    private static final String CONNECTION_STRING = "jdbc:derby://localhost:1527/ffDb;create=true";
    private static final String USER = "app";
    private static final String PASSWORD = "app";
    private static Connection conn;

    @BeforeClass
    public static void initJdbc() {
        // Verbindung zur DB herstellen
        try {
            Class.forName(DRIVER_STRING);
            conn = DriverManager.getConnection(CONNECTION_STRING, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Verbindung zur Datenbank nicht möglich:\n"
                    + e.getMessage() + "\n");
            System.exit(1);
        }
    }

    @AfterClass
    public static void teardownJdbc() {
        // Connection schließen
        try {
            if (conn != null || !conn.isClosed()) {
                conn.close();
                System.out.println("Goodbye!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void t01_readMember(){
        try{
            PreparedStatement pstmt = conn.prepareStatement("SELECT id,name,rank FROM member");
            ResultSet rs = pstmt.executeQuery();

            rs.next();
            assertThat(rs.getInt("id"),is(1));
            assertThat(rs.getString("name"),is("Max Meier"));
            assertThat(rs.getString("rank"),is("Oberfeuerwehrmann"));
            rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void t02_createMembers(){
        int countInserts = 0;
        try {
            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO Member (id,name,rank) VALUES (7,'Jonas Schürz','OFM')";
            countInserts += stmt.executeUpdate(sql);
            sql = "INSERT INTO Member (id,name,rank) VALUES (8,'Alex Pichler','OFM')";
            countInserts += stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        assertThat(countInserts, is(2));

        // Daten abfragen
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT id,name,rank FROM Member");
            ResultSet rs = pstmt.executeQuery();

            rs.next();
            rs.next();
            rs.next();
            rs.next();
            rs.next();
            assertThat(rs.getInt("id"),is(5));
            assertThat(rs.getString("name"),is("Jonas Schürz"));
            assertThat(rs.getString("rank"),is("OFM"));
            rs.next();
            assertThat(rs.getInt("id"),is(6));
            assertThat(rs.getString("name"),is("Alex Pichler"));
            assertThat(rs.getString("rank"),is("OFM"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void t03_updateMember(){
        int countUpdates = 0;
        try {
            Statement stmt = conn.createStatement();
            String sql = "UPDATE Member SET rank='Hauptfeuerwehrmann' where id=1";
            countUpdates += stmt.executeUpdate(sql);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        assertThat(countUpdates,is(1));
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT id,name,rank FROM Member where id=1");
            ResultSet rs = pstmt.executeQuery();

            rs.next();
            assertThat(rs.getInt("id"),is(1));
            assertThat(rs.getString("name"),is("Max Meier"));
            assertThat(rs.getString("rank"),is("Hauptfeuerwehrmann"));
            rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void t04_deleteCreatedMember(){
        int deletedExemplars =0;
        try {
            Statement stmt = conn.createStatement();
            String sql = "DELETE FROM Commando where id>1";
            deletedExemplars= stmt.executeUpdate(sql);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        assertThat(deletedExemplars,is(2));
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT count(*) counted FROM Commando");
            ResultSet rs = pstmt.executeQuery();

            rs.next();
            assertThat(rs.getInt("counted"),is(0));
            rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
