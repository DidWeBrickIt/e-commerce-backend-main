The e-commerce back end is designed to support the primary functions of an e-commerce webiste. The backend supports authorization functionality through JSON Web Tokens that allows users to login and logout. New users can register a new account and begin shopping. Users are also able to modify their profile information including thier name, address, and billing information. Users can view products in order to make purchases.

(Additional info can be added for user stories if stretch goals are completed)

The e-commerce backend is a Spring based Java web server that uses Maven as a build automation tool. The web serve is connected to a ProstgeSQL batabase through a jdbc connection that is procteded inside an environment variable. The primary user stories are implemented by routes provided by Spring mapping annotation in the controllers. All routes are tested using Spring Boot Start Test dependency. Exceptions are handled by classes that extend RuntimeException and those exceptions are thrown when called by the service layer. 

The repository has been set up with Github actions to automatically test pushes to branches as well as tested merged pull requests in order to achieve continuous integration. After each successful merge to the master branch, a new image is created and uplaoded to dockerhub where the container app on Azure refreshes to maintain the latest image. This process allows the backend to achieve continuous deployment.
