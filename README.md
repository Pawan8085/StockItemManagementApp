## Dmart App
The Dmart App is an e-commerce platform that allows users to buy products, manage categories, and track sales in various Dmart locations.


### Main Admin Endpoints :
#### Required Authority: MAIN_ADMIN
```
http://localhost:8080/dmart/mainAdmin/highest_user_city (GET):  Retrieves the city with the highest number of users.
```
```
http://localhost:8080/dmart/mainAdmin/highest_user_state (GET):  Retrieves the state with the highest number of users.
```
```
http://localhost:8080/dmart/mainAdmin/dmart/dmartId (GET):  Fetch DmartLocation object
```
```
http://localhost:8080/dmart/mainAdmin/totalRevenue (GET):  Retrieves the total revenue across all Dmart locations.
```
```
http://localhost:8080/dmart/mainAdmin/allDmartLocation (GET):  Retrieves all Dmart locations.
```


### Dmart Admin Endpoints :
#### Required Authority: DMART_ADMIN
```
http://localhost:8080/dmart/dmartAdmin/createDmart (POST): Creates a new Dmart location.
```
```
http://localhost:8080/dmart/dmartAdmin/addCategory (POST): Adds a new category to Dmart.
```
```
http://localhost:8080/dmart/dmartAdmin/addStockItem/categoryId (POST): Adds a new stock item to a specific category.
```
```
http://localhost:8080/dmart/dmartAdmin/updatedItemQuantity/itemId/quantity (PUT): Updates the quantity of a stock item.
```
```
http://localhost:8080/dmart/dmartAdmin/deleteItem/itemId (DELETE): Deletes a stock item.
```
```
http://localhost:8080/dmart/dmartAdmin/stockItemMovement (GET): Retrieves the movement history of stock items.
```
```
http://localhost:8080/dmart/dmartAdmin/getMyMart (GET): Retrieves the Dmart location for the logged-in Dmart admin.
```


### User Endpoints :
#### Required Authority: USER
```
http://localhost:8080/dmart/user/buy/itemId/quantity (POST):  Allows users to purchase items.
```
```
http://localhost:8080/dmart/user/categories (GET):  Retrieves all available categories based on user city
```
```
http://localhost:8080/dmart/user/items (GET):  Retrieves all items available for purchase based on user city
```


### Registration Endpoints :
```
http://localhost:8080/dmart/user/register (POST):  Allows user registration.
```
```
http://localhost:8080/dmart/dmartAdmin/register (POST):  Allows Dmart admin registration.
```
```
http://localhost:8080/dmart/mainAdmin/register (POST):  Allows main admin registration.
```
