package repositories;

import models.GroceryList;
import services.GroceryListService;
import util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GroceryListDAOImpl implements GroceryListDAO{

    @Override
    public List<GroceryList> getAllListsGivenUserId(Integer userId) {
        List<GroceryList> lists = new ArrayList<>();

        try(Connection conn = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM lists WHERE user_id_fk = ? ORDER BY list_id;";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,userId);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                lists.add(new GroceryList(rs.getInt(1), rs.getString(2), rs.getInt(3)));
            }

        }catch (SQLException sqle){
            sqle.printStackTrace();
        }

        return lists;
    }

    @Override
    public GroceryList getOneList(Integer listId) {
        GroceryList groceryList = null;

        try(Connection conn = ConnectionUtil.getConnection()){
            String sql = "SELECT * FROM lists WHERE list_id = ?;";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, listId);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                groceryList = new GroceryList(rs.getInt(1), rs.getString(2), rs.getInt(3));
            }


        }catch (SQLException sqle){
            sqle.printStackTrace();
        }

        return groceryList;
    }


    @Override
    public void createList(GroceryList groceryList) {
        try(Connection conn = ConnectionUtil.getConnection()){
            String sql = "INSERT INTO lists (list_name, user_id_fk) VALUES (?, ?);";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, groceryList.getName());
            ps.setInt(2,groceryList.getUserId());

            ps.executeUpdate();

        }catch (SQLException sqle){
            sqle.printStackTrace();
        }
    }

    @Override
    public void deleteList(Integer listId) {
        try(Connection conn = ConnectionUtil.getConnection()){
            String sql = "DELETE FROM lists WHERE list_id = ?;";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, listId);

            ps.executeUpdate();

        }catch (SQLException sqle){
            sqle.printStackTrace();
        }
    }
}
