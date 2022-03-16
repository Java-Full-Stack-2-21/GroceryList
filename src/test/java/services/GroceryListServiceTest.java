package services;

import models.GroceryList;
import models.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import repositories.GroceryListDAO;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GroceryListServiceTest {

    GroceryListService groceryListService;

    GroceryListDAO groceryListDAO = Mockito.mock(GroceryListDAO.class);

    public GroceryListServiceTest(){
        groceryListService = new GroceryListService(groceryListDAO);
    }

    @Test
    void getAllGivenUserId() {
        //arrange
        Integer userId = 2;
        List<GroceryList> expectedOutput = new ArrayList<>();
        expectedOutput.add(new GroceryList(1, "Kroger", userId));
        expectedOutput.add(new GroceryList(2, "Albertsons", userId));
        Mockito.when(groceryListDAO.getAllListsGivenUserId(userId)).thenReturn(expectedOutput);

        //act
        List<GroceryList> actualOutput = groceryListService.getAllGivenUserId(userId);


        //assert
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void getOne() {
        //arrange
        Integer listId = 2;
        GroceryList expectedOutput = new GroceryList(listId, "Kroger", 1);
        Mockito.when(groceryListDAO.getOneList(listId)).thenReturn(expectedOutput);

        //act
        GroceryList actualOutput = groceryListService.getOne(listId);

        //assert
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void createList() {
        //arrange
        GroceryList listToPass = new GroceryList(2, "Kroger", 1);

        //act
        groceryListService.createList(listToPass);

        //assert
        Mockito.verify(groceryListDAO, Mockito.times(1)).createList(listToPass);

    }

    @Test
    void deleteList() {
        //arrange
        Integer listId = 2;

        //act
        groceryListService.deleteList(listId);

        //assert
        Mockito.verify(groceryListDAO).deleteList(listId);
    }

    @Test
    void listIsOurs() {
        //arrange
        User user = new User(1, "user123", "pass123", "firstname", "lastname", null);
        GroceryList groceryList = new GroceryList(2, "Kroger",1);
        Mockito.when(groceryListDAO.getOneList(groceryList.getId())).thenReturn(groceryList);

        //act
        Boolean actualResult = groceryListService.isListOurs(user, groceryList.getId());

        //assert
        assertTrue(actualResult);

    }

    @Test
    void listIsNotOurs(){
        //arrange
        User user = new User(1, "user123", "pass123", "firstname", "lastname", null);
        GroceryList groceryList = new GroceryList(2, "Kroger",2);
        Mockito.when(groceryListDAO.getOneList(groceryList.getId())).thenReturn(groceryList);

        //act
        Boolean actualResult = groceryListService.isListOurs(user, groceryList.getId());

        //assert
        assertFalse(actualResult);
    }
}