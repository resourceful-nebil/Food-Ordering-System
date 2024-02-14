package UI;

import java.sql.*;
import java.util.ArrayList;

public class PasswordChangeController {
    private final String url = "jdbc:mysql://localhost:3310/auth";
    private final String user = "root";
    private final String password = "";
    private final String tableName = "admin";
    private Connection conn;
    private PreparedStatement preparedStatement;
    private Statement statement;
    private ResultSet resultSet;

    public static void main(String[] args) {
        ChangePassword changePassword = new ChangePassword();
        changePassword.setVisible(true);
    }

    public static void start() {
        ChangePassword changePassword = new ChangePassword();
        changePassword.setVisible(true);
    }

    public Object[]getAuth() {
        Object [] empty = new Object[0];
        ArrayList<Object> data = new ArrayList<>();
        try {
            conn = DriverManager.getConnection(url, user, password);
            if(conn != null) {
                String sql = "SELECT * FROM " + tableName;
                statement = conn.createStatement();
                resultSet = statement.executeQuery(sql);

                while(resultSet.next()) {
                    data.add(resultSet.getInt(1));
                    data.add(resultSet.getString(2));
                    data.add(resultSet.getString(3));
                }

                if(data.size() == 0) return empty;
                Object[] info = new Object[data.size()];
                int i = 0;
                for (Object p: data
                ) {
                    info[i++] = p;
                }


                resultSet.close();
                statement.close();
                conn.close();

                return info;
            }

        } catch (SQLException e) {
            System.out.println("SQLException HAPPENED");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Auth Getting EXCEPTION");
            e.printStackTrace();
        }

        return empty;
    }

    public void updateAuth(int id, String name, String pass) {
                ArrayList<Object> data = new ArrayList<>();
        try {
            conn = DriverManager.getConnection(url, user, password);
            if(conn != null) {
                String sql = "UPDATE " + tableName + " SET userName=?, password=? WHERE id=?";
                preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, pass);
                preparedStatement.setInt(3, id);

                int rowAffected = preparedStatement.executeUpdate();
                System.out.println("Row affected: " + rowAffected);

                preparedStatement.close();
                conn.close();
            }

        } catch (SQLException e) {
            System.out.println("SQLException HAPPENED");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Auth updating EXCEPTION");
            e.printStackTrace();
        }
    }
}
