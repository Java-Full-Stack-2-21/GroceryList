package repositories;

import models.GroceryItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GroceryItemDAOImpl implements GroceryItemDAO{

    String url = "jdbc:postgresql://[CONNECTION_URL]/[DATABASE_NAME]";
    String username = "postgres";
    String password = "";

    @Override
    public List<GroceryItem> getAllItemsGivenListId(Integer listId) {
        List<GroceryItem> items = new ArrayList<>();

        try{
            Connection conn = DriverManager.getConnection(this.url, this.username, this.password);
            String sql = "SELECT * FROM items WHERE list_id_fk = ? ORDER BY item_id;";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, listId);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                items.add(new GroceryItem(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getBoolean(4),
                        rs.getInt(5)
                        ));
            }


        }catch (SQLException sqle){
            sqle.printStackTrace();
        }

        return items;
    }

    @Override
    public void markItemInCart(Integer itemId) {
        try{
            Connection conn = DriverManager.getConnection(this.url, this.username, this.password);
            String sql = "UPDATE items SET item_in_cart = TRUE WHERE item_id = ?;";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,itemId);

            ps.executeUpdate();

        }catch (SQLException sqle){
            sqle.printStackTrace();
        }
    }

    @Override
    public void createItemForListWithQuantity(GroceryItem item) {
        try{
            Connection conn = DriverManager.getConnection(this.url, this.username, this.password);
            String sql = "INSERT INTO items (item_name, item_quantity, list_id_fk) VALUES (?, ?, ?);";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, item.getName());
            ps.setInt(2, item.getQuantity());
            ps.setInt(3, item.getListId());


            ps.executeUpdate();

        }catch (SQLException sqle){
            sqle.printStackTrace();
        }
    }

    @Override
    public void createItemForListWithoutQuantity(GroceryItem item) {
        try{
            Connection conn = DriverManager.getConnection(this.url, this.username, this.password);
            String sql = "INSERT INTO items (item_name, list_id_fk) VALUES (?, ?);";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, item.getName());
            ps.setInt(2, item.getListId());

            ps.executeUpdate();

        }catch (SQLException sqle){
            sqle.printStackTrace();
        }
    }

    @Override
    public void deleteItemFromList(Integer itemId) {
        try{
            Connection conn = DriverManager.getConnection(this.url, this.username, this.password);
            String sql = "DELETE FROM items WHERE item_id = ?;";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, itemId);

            ps.executeUpdate();

        }catch (SQLException sqle){
            sqle.printStackTrace();
        }
    }
}
