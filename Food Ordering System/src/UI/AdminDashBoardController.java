package UI;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.jar.JarFile;

public class AdminDashBoardController {
    private final String url = "jdbc:mysql://localhost:3310/foodorderingsystem";
    public final String[] colsName = {"ID", "Food Name", "Price", "Image URL"};
    private final String user = "root";
    private final String password = "";
    private final String tableName = "menu";
    private Connection conn;
    private PreparedStatement preparedStatement;
    private Statement statement;
    private ResultSet resultSet;
    public static void main(String[] args) {
        ADBUI adbui = new ADBUI();
        adbui.setVisible(true);
    }

    public static void start() {
        ADBUI adbui = new ADBUI();
        adbui.setVisible(true);
    }

    public static void setVisibility(JFrame frame) {
        frame.setVisible(true);
    }

    public Object[] addMenu(String foodName, double price, String imageUrl) {
        try {
           conn = DriverManager.getConnection(url, user, password);
            if(conn != null) {
              String sql = "INSERT  INTO " + tableName + " (foodName, price, imageUrl) VALUES(?, ?, ? )";
              preparedStatement = conn.prepareStatement(sql);
              preparedStatement.setString(1, foodName);
                preparedStatement.setDouble(2, price);
                preparedStatement.setString(3, imageUrl);

                int rowAffected = preparedStatement.executeUpdate();
                System.out.println("ROW AFFECTED: " + rowAffected);
            }

            preparedStatement.close();

            return getRow();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            System.out.println("MENU SINGLE ADDING EXCEPTION");
            e.printStackTrace();
        }
    return null;
    }

    public Object[] getRow() {
        try {
            conn = DriverManager.getConnection(url, user, password);
            if(conn != null) {
                // Query the database for the last row of the table
                String sql = "SELECT * FROM " + tableName + " ORDER BY id DESC LIMIT 1";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                System.out.println("RessultSet: "+ rs );
                Object[] row = new Object[5];
                if (rs.next()) {
                    row[0] = rs.getInt(1);
                    row[1] = rs.getString(2);
                    row[2] = rs.getDouble(3);
                    row[3] = rs.getString(4);
                }
                rs.close();
                stmt.close();
                conn.close();

                return row;
            }
        }catch (SQLException e) {
            System.out.println("SQLException IN GET ROW ");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("EXCEPTION IN GET ROW");
            e.printStackTrace();
        }

        return null;
    }

    public Object[][] getTableData() {
        Object [][] empty = new Object[0][0];
        ArrayList<ArrayList<Object>> data = new ArrayList<ArrayList<Object>>();
        try {
            conn = DriverManager.getConnection(url, user, password);
            if(conn != null) {
                String sql = "SELECT * FROM " + tableName;
                statement = conn.createStatement();
                resultSet = statement.executeQuery(sql);
                int columnCount = 5;

                while(resultSet.next()) {
                    ArrayList<Object> row = new ArrayList<>();
                    row.add(resultSet.getInt(1));
                    row.add(resultSet.getString(2));
                    row.add(resultSet.getDouble(3));
                    row.add(resultSet.getString(4));
                    data.add(row);
                }

                if(data.size() == 0) return empty;

                Object datas[][] = new Object[data.size()][data.get(0).size()];
                for(int i = 0; i < data.size(); i++) {
                    for(int j = 0; j < data.get(0).size(); j++) {
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

    private JLabel getLabel(String path) {
        Image image = new ImageIcon(path).getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH);
        JLabel lable = new JLabel(new ImageIcon(image));

        return lable;
    }

    public void createImageWindow(String path, JFrame frame) {
        JDialog dialog = new JDialog(frame, "Image", true);
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel label = getLabel(path);
        panel.add(label, BorderLayout.CENTER);
        panel.setPreferredSize(new Dimension(300, 200));
        dialog.add(panel);

        dialog.setResizable(false);
        dialog.setLocation(175, 268);
        dialog.pack();
        dialog.setVisible(true);
    }
    public void update(int id, String foodName, double price, String imageUrl) {
        try {
                conn = DriverManager.getConnection(url, user, password);
                String sql = "UPDATE " + tableName + " SET foodName=?, price=?, imageUrl=? WHERE id=?";
                if(conn != null) {
                    preparedStatement = conn.prepareStatement(sql);
                    preparedStatement.setString(1, foodName);
                    preparedStatement.setDouble(2, price);
                    preparedStatement.setString(3, imageUrl);
                    preparedStatement.setInt(4, id);

                    int rowAffected = preparedStatement.executeUpdate();
                    System.out.println("ROW UPDATED IN FOOD MENU AND " + rowAffected + " ROWS AFFECTED");
            }

            preparedStatement.close();
            conn.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            System.out.println("MENU SINGLE ADDING EXCEPTION");
            e.printStackTrace();
        }

    }

    public void delete(int id) {
        try {
            conn = DriverManager.getConnection(url, user, password);
            String sql = "DELETE FROM " + tableName + " WHERE id=?";
            if(conn != null) {
                preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setInt(1, id);

                int rowAffected = preparedStatement.executeUpdate();
                System.out.println("ROW DELETED IN FOOD MENU AND " + rowAffected + " ROWS AFFECTED");
            }

            preparedStatement.close();
            conn.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            System.out.println("MENU SINGLE ADDING EXCEPTION");
            e.printStackTrace();
        }

    }

    public void clear() {
        try {
            conn = DriverManager.getConnection(url, user, password);
            String sql = "DELETE FROM " + tableName;
            if(conn != null) {
                preparedStatement = conn.prepareStatement(sql);
                int rowAffected = preparedStatement.executeUpdate();
                System.out.println("ROW DELETED IN FOOD MENU AND " + rowAffected + " ROWS AFFECTED");
            }

            preparedStatement.close();
            conn.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            System.out.println("MENU SINGLE ADDING EXCEPTION");
            e.printStackTrace();
        }

    }
}
