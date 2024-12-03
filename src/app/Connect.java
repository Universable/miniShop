package app;
import java.sql.*;
import java.util.*;
import java.util.logging.*;


public class Connect {
    public Connection conn;
    public Statement stmt;
    public ResultSet rs;
    
    public Connect(){
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:db1.db");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } 
    }
    public String selectLatest(String column, String table){        
        String sql = "SELECT " + column + " FROM " + table + " order by " + column + " desc limit 1;";
        String alabala="";
        System.out.println(sql);
        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
                String row = "";
                 row = rs.getString(column); 
                 alabala =  row;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return alabala;
    }
    
    public ArrayList<String> select(String[] columnsArray, String table){
        ArrayList<String> data = new ArrayList<String>();
        
        String columnsString = String.join(", ", columnsArray);
        String sql = "SELECT " + columnsString + " FROM " + table;
        
        System.out.println(sql);
        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                String row = "";
                for (int i = 0; i < columnsArray.length; i++) {
                    row += rs.getString(columnsArray[i]) + "---";
                }
                data.add(row);
                
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return data;
    }
    
     public ArrayList<String> selectGroup(String[] columnsArray, String group, String table){
        ArrayList<String> data = new ArrayList<String>();
        
        String columnsString = String.join(", ", columnsArray);
        String sql = "SELECT " + columnsString + " FROM " + table + " GROUP BY " + group;
        
        System.out.println(sql);
        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                String row = "";
                for (int i = 0; i < columnsArray.length; i++) {
                    row += rs.getString(columnsArray[i]) + "---";
                }
                data.add(row);
                
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return data;
    }
    
     public ArrayList<String> selectWhere(String[] columnsArray, int[] whereColIndex, String whereValue, String table){
        ArrayList<String> data = new ArrayList<String>();
        
        String columnsString = String.join(", ", columnsArray);
        
        String sql = "SELECT " + columnsString + " FROM " + table + " WHERE ";
            for (int i = 0; i < whereColIndex.length; i++) {
                sql += columnsArray[whereColIndex[i]] + " LIKE '%" + whereValue + "%' OR ";
            }
        sql = sql.substring(0, sql.length() - 4);
        System.out.println(sql);
        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                String row = "";
                for (int i = 0; i < columnsArray.length; i++) {
                    row += rs.getString(columnsArray[i]) + "---";
                }
                data.add(row);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return data;
    }
     
    public void delete(String column, int id, String table){
        String sql = "DELETE FROM " + table + " WHERE " + column + " = " + id;
        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }    
    public void deleteall(String table){
        String sql = "DELETE FROM " + table;
        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }  
    public void insert(String[] columnsArray, String[] valuesArray, String table){
        String columnsString = String.join(", ", columnsArray);
        String valuesString = "'" + String.join("', '", valuesArray) + "'";
        
        valuesString = valuesString.replace("''", "null");
        
        String sql = "INSERT INTO " + table + " (" + columnsString + ") VALUES (" 
                + valuesString + ")";
        System.out.println(sql);
        try{
            stmt = conn.createStatement();
            stmt.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void update(String[] columnsArray, String[] valuesArray, String whereColumn, int id, String table){
        String sql =  "UPDATE " + table + " SET ";
        for (int i = 0; i < columnsArray.length; i++) {
            sql = sql + columnsArray[i] + " = " + 
                    (valuesArray[i].equals("") ? "null, " : "'" + valuesArray[i] + "', ");
        }
        sql = sql.substring(0, sql.length() - 2);

        sql = sql + " WHERE " + whereColumn + " = " + id;
        
        System.out.println(sql);
        try{
            stmt = conn.createStatement();
            stmt.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void close(){
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Throwable ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}