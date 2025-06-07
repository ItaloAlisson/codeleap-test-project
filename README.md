# CodeLeap - Test Project

This is an API for the CodeLeap test, developed with Java 17 and Spring Boot. The API manages posts in a fictional social network, including creation, reading, updating, and deletion of posts, with integration to a PostgreSQL database.

## Technologies Used

- **Java 17**
- **Spring Boot 3.5.0**
- **Maven**
- **Lombok** for code simplification
- **SpringBoot DevTools** for faster development
- **Spring Data JPA** for database integration
- **PostgreSQL** as the relational database
- **Hibernate Validator** for data validation
- **MapStruct** for object mapping
- **JUnit 5** for unit testing
- **Mockito** for mock creation in tests

## Endpoints

The API provides the following endpoints for interacting with posts:

- `POST /careers` - Creates a new post
- `GET /careers` - Returns all posts
- `PATCH /careers/{id}` - Updates the contents of a post
- `DELETE /careers/{id}` - Deletes a post

### Usage Examples

#### Create Post
```bash
POST /careers
Content-Type: application/json
{
  "userName": "UserName test",
  "title": "Title test",
  "content": "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc efficitur pulvinar magna, eget ultrices elit
   ultricies vitae. Phasellus consectetur neque sem, nec elementum eros"
}
```

#### Get Posts
```bash
GET /careers
```

#### Update Post Content
```bash
PATCH /careers/423
Content-Type: application/json
{
  "title": "Title test",
  "content": "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc efficitur pulvinar magna, eget ultrices elit
   ultricies vitae. Phasellus consectetur neque sem, nec elementum eros"
}
```

#### Delete Post
```bash
DELETE /careers/423
```