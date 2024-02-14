package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.sql.*;
import java.util.ArrayList;

public class DashboardController {
    private final String url = "jdbc:mysql://localhost:3310/foodorderingsystem";
    public final String[] colsName = {"ID", "Food Name", "Price", "Image URL", "Description"};
    private final String user = "root";
    private final String password = "";
    private final String tableName = "menu";
    private final String orderTableName = "orderTable";
    private Connection conn;
    private PreparedStatement preparedStatement;
    private Statement statement;
    private ResultSet resultSet;
    public static JFrame frameDispose;
    public static void main(String[] args) {
        Dashboard dashboard = new Dashboard();
        dashboard.setVisible(true);
    }
    public static void start() {
        Dashboard dashboard = new Dashboard();
        dashboard.setVisible(true);
        frameDispose = dashboard;
    }

    public Object[][] getMenuData() {
        Object [][] empty = new Object[0][0];
        ArrayList<ArrayList<Object>> data = new ArrayList<ArrayList<Object>>();
        try {
            conn = DriverManager.getConnection(url, user, password);
            if (conn != null) {
                String sql = "SELECT * FROM " + tableName;
                statement = conn.createStatement();
                resultSet = statement.executeQuery(sql);
                int columnCount = 5;

                while (resultSet.next()) {
                    ArrayList<Object> row = new ArrayList<>();
                    row.add(resultSet.getInt(1));
                    row.add(resultSet.getString(2));
                    row.add(resultSet.getDouble(3));
                    row.add(resultSet.getString(4));
                    data.add(row);
                }
                if(data.size() == 0) return empty;
                Object datas[][] = new Object[data.size()][data.get(0).size()];
                for (int i = 0; i < data.size(); i++) {
                    for (int j = 0; j < data.get(0).size(); j++) {
                        datas[i][j] = data.get(i).get(j);
                    }
                }

                resultSet.close();
                statement.close();
                conn.close();

                return datas;
            }

        } catch (SQLException e) {
            System.out.println("SQLException HAPPENED");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("MENU ADDING EXCEPTION");
            e.printStackTrace();
        }

        return empty;
    }

    public void  addOrder(String userId, String foodName, int qty) {
        try {
            conn = DriverManager.getConnection(url, user, password);
            if(conn != null) {
                String sql = "INSERT  INTO " + orderTableName + " (userId, foodName, foodQty) VALUES(?, ?, ? )";
                preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, userId);
                preparedStatement.setString(2, foodName);
                preparedStatement.setInt(3, qty);

                int rowAffected = preparedStatement.executeUpdate();
                System.out.println("ROW AFFECTED: " + rowAffected);
            }

            preparedStatement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            System.out.println("MENU SINGLE ADDING EXCEPTION");
            e.printStackTrace();
        }
    }
}
