## Basic Model ##

 * We have our basic object at the root of our application:
 	* Topic     - contains author, time created, content, getter and setter
 	* Reply     - same as topic, with extra field to_topic.
 	* User      - basic info, getter and setter
 
 ----

## Back End Database ##
 ![](https://github.com/csc301-fall2014/Proj-UTM-Team2-repo/blob/master/ggMsgER.png)

 * We used a relational database schema for our database, and the it is using MySQL query language to communicate.
 * Looking at the Entity Relational Diagram (ER Diagram) above it clearly shows that we used tables to store relative information for each Object we are using in the webapp, and we use queries to join the tables together and manipulate the output to get what is desired.
 * Some of the major component in the database and how they interact:
 	* User Profile - each row contains the password, email, phone number for a user. Originally there exist a separate table for username and password, we later decided to merge the two because it will create more work querying.
 	* Topic - each row is a single topic opening, it will contain relative information. This table do not have replies to this specific topic.
 	* Reply - each row is a reply, and it is linked to the topics by having a field called topic_id that indicate which topic this reply belongs to.
 	* Subscription and user_sub - subscription contains the topics a user is subbed to, while user_sub contains the the list of subscribed users. Because we offer the ability for user to subscribe to a single topic of follow another user and get notified, we need two separate tables. When it is necessary to join the two table together and produce a list of all topics subscribed to, it is done using query on the database end.
 * After each query, the data we received is mapped onto our basic model class using an automapper, and then we pass around the object without having to requery.
 
 ----

## Data Access Object (DAO) / Controller

 * Between the front end and back end we have Data Access Objects.
 	* We use DAOs for one specific purpose, to execute queries.
 	* Each controller controls one page on the front end, and each controller can create instances of DAO and use it to fetch / update data.
 * DAOs are first drafted as interfaces, we used these interface to outline what queries / actions we want this specific DAO to perform, and what data type it will need and what it will return. Later on we implemented the DAO by completing the interface. Each DAO can have more than one query, for example, topicDAO will have addTopic, removeTopic, closeTopic, fetchTopic, etc.
 * Controller's purpose is to control the front end display page, based on different button press execute action accordingly. With this in mind, controller are created based on task performed and what page it is on:
 	* ForgotPassword
 	* HomeController
 	* LoginController
 	* LogoutController
 	* NotificationController
 	* SMSBuilder
 	* NewTopicController
 	* NewUserController
 	* ProfilerController
 	* ViewTopicsController
 * A basic example of creating a new topic would be:
 	* User click new topic -> NewTopicController -> Create topicDAO -> call topicDAO.createTopic() -> data add to database

----

## Front End jsp

 * Front end we have HTML web pages in the form of jsp since we are using the Spring Framework.
 * We have a separate module for CSS, we used bootstrap for some of our CSSing.
 * The home index page is called home.jsp and that's where the webapp will start, and it is hooked up to HomeController
 * Each page will load / unload content based on what the Controller's feedback will be, the front end pages have no knowledge of DAOs.
 * From the log in point we save the current session of the user into the user model class, thus we always have the necessary information to the controller needs.
 * Controller and the webpage exchange information using request, if a controller need the user id, when a method is created it must first request it from the page.
