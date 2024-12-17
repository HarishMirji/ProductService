# Product Service API

## Overview

This Product Service API provides endpoints to manage product entities. It supports creating, updating, deleting, retrieving, and bulk operations on products. The service integrates with third-party services to manage products by category, fetch product information, and handle paginated data.

## Features

- **Product Management**: Create, update, and delete products.
- **Category-Based Product Retrieval**: Fetch products by category.
- **Bulk Product Creation**: Support bulk product creation.
- **Pagination**: Retrieve products with pagination support.

## Endpoints

### Create a Product
`POST /products/create`
- Request body: Product details
- Response: Newly created product

### Update a Product
`PUT /products/update/{id}`
- Path variable: `id` (Product ID)
- Request body: Product details to update
- Response: Updated product

### Delete a Product
`DELETE /products/delete/{id}`
- Path variable: `id` (Product ID)
- Response: Deleted product

### Get All Products
`GET /products/get`
- Response: List of all products

### Get Product by ID
`GET /products/get/{id}`
- Path variable: `id` (Product ID)
- Response: Product details

### Get Products by Category
`GET /products/category/{category}`
- Path variable: `category` (Category name)
- Response: List of products in the specified category

### Bulk Create Products
`POST /products/createbulk`
- Request body: List of products to be created
- Response: Success message

### Get Paginated Products
`GET /products`
- Query parameters:
  - `pageSize` (default: 20)
  - `pageNum` (default: 0)
- Response: Paginated list of products

## Dependencies

- Spring Boot
- Spring Data JPA
- MySql Database (or any other database you configure)

## Exception Handling

- `ProductNotFoundException`: Thrown when a product is not found.
- `CategoryNotFoundException`: Thrown when a category is not found.

## Running the Application

1. Clone the repository.
2. Run the application with `mvn spring-boot:run`.
3. Access the API at `http://localhost:8080/products`.

## Contributing

Feel free to open issues and submit pull requests.
