# Trade IT

Trade-It is a web-based application designed to facilitate bartering between users. This README provides detailed information about the various API endpoints available in the backend.

## Backend API Documentation

### Authentication
  #### Registration
- **Endpoint:** /api/auth/register
- **Method:** POST
  
**Headers:** JSON with user's "name", "surname", "username", "email", "role" set to default USER, and "password" fields

**Response:**  JSON with "access_token" and "expires_in" fields


  #### Log in
- **Endpoint:** /api/auth/login
- **Method:** POST
  
**Headers:** JSON with "username" , "iat" and "exp"

**Response:**  SON with "access_token" and "expires_in" fields

### Products
  #### Products Listing
- **Endpoint:** /api/products
- **Method:** GET
  
**Parameters:** "categories", "condition", "seller", "name", and "search"
  
**Response:** JSON array of product objects

  #### Add Product
- **Endpoint:** /api/products
Method: POST
- **Headers:** Authorization with login token
  
**Payload:** JSON with "seller_id", "name", "category_id", "description", "details", "condition", and "targetProducts" fields

**Response:** JSON with "product_id" and "message" fields

  #### Single Product by ID
- **Endpoint:** /api/products/{product_id}
- **Method:** GET
  
**Response:** JSON object of the requested product

### Categories
  #### Categories Listing
- **Endpoint:** /api/categories
- **Method:** GET
- **Response:** JSON array of category objects

### Cities
  #### Cities Listing
- **Endpoint:** /api/cities
- **Method:** GET
  
**Response:** JSON array of city objects

### Barter Proposal
  #### Initiate Barter Proposal
- **Endpoint:** /api/barters
- **Method:** POST
  
**Headers:** Authorization with login token

**Payload:** JSON with "offered_product_id", "desired_product_id", and "message" fields

**Response:** JSON with "proposal_id" and "message" fields

### Profile/User
  #### User Info
- **Endpoint:** /api/users/{user_id}
- **Method:** GET
  
**Response:** JSON object of the user's information

### Image Upload
- **Endpoint:** /api/image
- **Method:** POST
  
Use form-data to submit the image and product_id.


## Tools and Technologies

The backend is implemented in Java, utilizing the Spring Boot 3 framework, Spring Security for authentication, and JSON Web Tokens for session management. 
Data is persisted in a MSSQL database, with Hibernate 6 handling ORM tasks. 
Image files are managed via the high-performance object storage service, MinIO, while session tokens are stored in Redis.
