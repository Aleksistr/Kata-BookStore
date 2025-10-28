# Book Store Online - BACK

This is the application is the back end section from the Book Store Online application.

## Executing program

### How to run the program via Intellij:

* Import to maven program on Intellij
* Run (to load and install dependencies)
````
    mvn clean install
 ````
* Then execute the maven goal


### Test the application
* Install to testing tool [Bruno](https://www.usebruno.com/)
* In the folder ``BookStoreOnlineBrunoCollection`` I've haded a Bruno collection.
* Import this connection on the bruno application

#### Running endpoints
* Two endpoints don't need any security, there are describe in the RegistrationController folder
* The other endpoints need a Bearer token.
* To get this token run the login command and inject it in the request header **without the term "Bearer"**, Bruno will add it automatically

