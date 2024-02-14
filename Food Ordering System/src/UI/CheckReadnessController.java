package UI;

import java.sql.*;
import java.util.ArrayList;

public class CheckReadnessController {
    private final String url = "jdbc:mysql://localhost:3310/foodorderingsystem";
    private final String user = "root";
    private final String password = "";
    private final String tableName = "orderTable";
    private Connection conn;
    private PreparedStatement preparedStatement;
    private Statement statement;
    private ResultSet resultSet;
    public static void main(String[] args) {
        CheckReadness checkReadness = new CheckReadness();
        checkReadness.setVisible(true);
    }

    public void start() {
        CheckReadness checkReadness = new CheckReadness();
        checkReadness.setVisible(true);
    }
    public ArrayList getOrderData() {
        ArrayList<ArrayList<ArrayList<Object>>> data = new ArrayList<>();
        try {
            conn = DriverManager.getConnection(url, user, password);
            if (conn != null) {
                String sql = "SELECT * FROM " + tableName;
                statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                resultSet = statement.executeQuery(sql);

                int index = 0;
                ArrayList<String> uniques = new ArrayList<>();
                boolean newOrder = false;
                int flag = 0;
                ArrayList<ArrayList<Object>> order = new ArrayList<>();

                resultSet.last();
                int rowCount = resultSet.getRow();
                resultSet.beforeFirst();

                while (resultSet.next()) {
                    ++index;
                    ArrayList<Object> row = new ArrayList<>();
                    row.add(resultSet.getInt(1));// id
                    row.add(resultSet.getString(2));// user id
                    if(uniques.isEmpty()) {
                        uniques.add(resultSet.getString(2));
                    } else {
                        if(uniques.contains(resultSet.getString(2))) {
                            uniques.add(resultSet.getString(2));
                            newOrder = false;
                        } else {
                            newOrder = true;
                            uniques = new ArrayList<>();
                            uniques.add(resultSet.getString(2));
                        }
                    }

                    row.add(resultSet.getString(3));// food name
                    row.add(resultSet.getInt(4)); // qty
                    row.add(resultSet.getString(5)); // status
                    row.add(resultSet.getTimestamp(6)); // timestamp

                    if(!newOrder) {
                        order.add(new ArrayList<>(row));
                    } else {
                        data.add(new ArrayList<>(order));

                        order = new ArrayList<>();
                        order.add(new ArrayList<>(row));
                    }

                    if(index == rowCount) {
                        data.add(new ArrayList<>(order));
                    }
                }
                resultSet.close();
                statement.close();
                conn.close();

                return data;
            }

        } catch (SQLException e) {
            System.out.println("SQLException HAPPENED");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("MENU ADDING EXCEPTION");
            e.printStackTrace();
        }

        return data;
    }
}
