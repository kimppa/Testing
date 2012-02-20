Narrative:
In order to buy products from shopping cart
As a customer
I want to add products to a shopping cart and modify it's contents


Scenario: Add one product to the cart

Given products exist:
| id | name      | unit price |
| 1  | product 1 | 10.00      |
And I have an empty cart
When I add 1 pc of product 1 to the cart
Then the cart contains rows:
| product id | quantity |
| 1          | 1        |

Scenario: Add multiple products to the cart

Given products exist:
| id | name      | unit price |
| 1  | product 1 | 10.00      |
| 2  | product 2 | 20.00      |
And I have an empty cart
When I add 1 pc of product 1 to the cart
And I add 2 pc of product 2 to the cart
Then the cart contains rows:
| product id | quantity |
| 1          | 1        |
| 2          | 2        |


Scenario: Add 5 pc of a product and remove it from a cart

Given products exist:
| id | name      | unit price |
| 1  | product 1 | 10.00      |
And I have an empty cart
When I add 5 pc of product 1 to the cart
And I remove product 1 from the cart
Then the cart is empty


Scenario: Add a product twice to the cart

Given products exist:
| id | name      | unit price |
| 1  | product 1 | 10.00      |
And I have an empty cart
When I add 2 pc of product 1 to the cart
When I add 2 pc of product 1 to the cart
Then the cart contains rows:
| product id | quantity |
| 1          | 4        |




Scenario: Shipping cost calculation

Given products exist:
| id | name      | unit price |
| 1  | product 1 | 10.00      |
And I have an empty cart
When I add <quantity> pc of product <product id> to the cart
Then shipping cost is <shipping cost>

Examples:
| product id | quantity | shipping cost | description            |
| 1          | 10       | 20            | total price < 200      |
| 1          | 20       | 0             | total price =>200      |



Scenario: Discount calculation

Given products exist:
| id | name      | unit price |
| 1  | product 1 | 10.00      |
| 2  | product 2 | 50.00      |
| 3  | product 3 | 100.00     |
And I have an empty cart
When I add <quantity> pc of product <product id> to the cart
Then the cart contains row for product <product id> with quantity of <quantity> and discounted amount <discounted amount>


Examples:
| product id | quantity | discounted amount | discount | description            |
| 1          | 10       | 100.00            | -        | unit price < 50        |
| 2          | 5        | 237.5000          | 5%       | 5 pc, unit price 50    |
| 2          | 10       | 475.0000          | 5%       | unit price < 100       |
| 3          | 4        | 400.00            | -        | quantity < 5           |
| 3          | 9        | 855.0000          | 5%       | quantity < 10          |
| 3          | 10       | 900.0000          | 10 %     | 10 pc, unit price 100  |