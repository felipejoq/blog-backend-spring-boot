# Blog Api backend with Spring Boot
This is an Application can I will learn about Spring Boot and Java.
In this repository I will make a RESTful API building a typical website schema like a blog, and so I'm going learn, clarify and obtains more knowing about this usefully framework that we called: **Spring Boot**

## Create a controller
Controller is a component into Spring Boot it has a responsibility of serve resources from server, db or another service.

### - Step I
I create a RestController with annotations for implements HTTP Verbs: Get, Post, Put and Delete.

- @RestController: for indicated to Spring Boot container that this class is a component type Controller and ResponseBody.
- @RequestMapping: Define a base URL for service, I defined how "students", so all url declare in this class serve under to this URL base (/students**).
- @GetMapping: annotation for function manage method http "GET" on this resource.
- @PostMapping: annotation for function manage method http "POST" on this resource.
- @PutMapping: annotation for function manage method http "PUT" on this resource.
- @DeleteMapping: annotation for function manage method http "DELETE" on this resource.

Well, this is all in this opportunity and excuse me you for my english, I'm practicing this too on this case.
