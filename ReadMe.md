## To Run this project on your machine:
- change the database configurations in application.properties folder
- you can also use h2 in memory database instead of postgres

## Features :
 - add/update/delete for product
 - add / update / delete for categories
 - registration and login where each user has a role
 - we have to defined roles USER and ADMIN
 - authentication using jwt bearer token

## APIs
>test the following APIs on postman :
 - localhost:8080/api/login : login a user ->type:post
 - localhost:8080/api/login :  register a user -> type:post
 - localhost:8080/api/v1/products/create : create a product type:post
 - localhost:8080/api/v1/products/update : updates a product type->put
 - localhost:8080/api/v1/products/delete : deletes a product type->delete
 - localhost:8080/api/v1/products/id : get all products with category (id) type->get
 - localhost:8080/api/v1/categories/create : create a category type:post
 - localhost:8080/api/v1/categories/update : updates a category type->put
 - localhost:8080/api/v1/categories/delete : deletes a category type->delete
