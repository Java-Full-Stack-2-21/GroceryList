
import controllers.GroceryItemController;
import controllers.GroceryListController;
import controllers.UserController;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

import static io.javalin.apibuilder.ApiBuilder.*;


public class MainDriver {
    public static void main(String[] args) {
        //Landing.view();
        Javalin app = Javalin.create(config -> {
            config.addStaticFiles("/", Location.CLASSPATH);
        }).start(9001);

        UserController userController = new UserController();
        GroceryListController groceryListController = new GroceryListController();
        GroceryItemController groceryItemController = new GroceryItemController();

        /// :: method reference symbol
        //user endpoints
//        app.post("/user", userController::createUser);
//        app.post("/login", userController::login);
//
//        //list endpoints
//        app.get("/list", groceryListController::displayAllListForUser);
//        app.post("/list", groceryListController::createList);
//        app.delete("/list/{listId}", groceryListController::deleteList);
//
//        //item endpoints
//        app.get("/item", groceryItemController::getAllItemsGivenListId);
//        app.patch("/item/{itemId}", groceryItemController::markItemInCart);
//        app.delete("/item/{itemId}", groceryItemController::deleteItem);
//        app.post("/item", groceryItemController::createItem);


        app.routes(() -> {
            path("list", () -> {
                get(groceryListController::displayAllListForUser);
                post(groceryListController::createList);
                path("{listId}", () -> {
                    delete(groceryListController::deleteList);
                });
            });

            path("item", () -> {
                get(groceryItemController::getAllItemsGivenListId);
                post(groceryItemController::createItem);
                path("{itemId}", () -> {
                    patch(groceryItemController::markItemInCart);
                    delete(groceryItemController::deleteItem);
                });
            });

            post("/user", userController::createUser);
            post("/login", userController::login);
        });


    }
}
