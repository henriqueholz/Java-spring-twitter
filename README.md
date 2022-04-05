Henrique Holz de Castro
# posterr
Strider backend technical assessment test using JAVA Spring Boot.

## Installation
- Unzip the zip file
- Open the project using an IDE of your choice
- Set Up a PostgreSQL Database
- Copy your database name, username, and password and paste them on application.properties file.
- Run the application
- Requests can be made using API client for REST(Insomnia or Postman)  or using swagger at http://localhost:8080/swagger-ui.html

## APIs
GET -> http://localhost:8080/api/follow/{user_id}
- User profile page
- Logged user follows the user

GET -> http://localhost:8080/api/unfollow/{user_id}
- User profile page
- Logged user unfollows the user

GET -> http://localhost:8080/api/followcheck/{user_id}
- User profile page
- Shows whether logged user follow the user or not

POST -> http://localhost:8080/api/create
- User profile page
- Payload { "post_message": "alala" }
- Logged user creating a post

GET -> http://localhost:8080/api/repost/{post_id}
- User profile page
- Logged user resposts a post

POST -> http://localhost:8080/api/quote/{post_id}
- User profile page
- Payload { "post_message": "elele" }
- Logged user quoting an existent post

GET -> http://localhost:8080/api/feed/{number_of_posts}
- Homepage
- Get all posts

GET -> http://localhost:8080/api/userfeed/{number_of_posts}
- Homepage, User profile page
- Get the specific number of posts, quotes, and reposts from the logged user and followed users

GET -> http://localhost:8080/api/user/{user_id}
- User profile page
- Get information of the user

GET -> http://localhost:8080/api/search/{word}
- Extra feature: search
- Get posts and quotes if a given word

## Planning
Questions:
- Does a user have access to other users’ “Posts and Replies” page?
- When a user types “@“ what happens?

Assumptions:
- Assuming that a user has access to other users’ “Posts and Replies” page.
- Assuming that a list of followed users is shown when a user types @

Database change:
- A new field should be added to the “Post” model, called Post_Mentioned_UserId, this field has a relationship of one to many between “Post” and “User” model, meaning a post can have many mentioned users.

API changes:
- Whenever a CreatePost(/api/create) or a Quote(/api/quote/{post_id} is requested, the users mentioned should be included on the request payload and added in the “Post_mentioned_userid” field.

New requests:
- Get ->  /api/replies/{user_id} - This request will get all original posts of the user and posts that he has replied to. This info will be shown on the “Posts and Replies page.
- Get -> /api/followinguser - This request will get all users that the logged user follows to be used as a suggestion when a user types “@“

Front-End changes:
- A new page has to be created: “Posts and replies”.
- This page needs to send a request to /api/replies/{user_id}, the user_id is the id of the user’s posts and replies that will be shown on the page".
- The page then shows all posts and replies.
- When a user types “@“ it should send a request to “/api/followinguser” to obtain all users he is following to be suggested to the end-user.

## Critique

Improvements:
- Write a 100% test coverage.
- Consider creating new tables to make the project easier to understand: “Comment”, “Quote”, and “Repost”.
- Review the APIs and Queries to make them more performative.
- Document swagger better.
- Write a specific API response for each request

Scaling:
- Initially, space is not a problem since the posts have a limited size.
- We have a limited number of daily posts, so we are having more people reading than tweeting. So we should care much more about the availability and scale of the application for a heavy read.
- Speed is important but in this case, reliability is even more important. We should consider eventual consistency for this kind of system. It’s ok if a user sees the tweet of his follower a bit delayed.
- We should consider scaling horizontally using microservices and Redis as a database(much faster because data is stored in memory).
- In this situation, the data for timeline queries will be saved in Redis(caching layer).
- Through Load Balancer posts will flow into back-end servers.
- Fanout Approach: Whenever a post is made by a user, the server node will inject this tweet into the in-memory timelines of his followers.