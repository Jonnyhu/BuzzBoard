# Code Reviews

The table below indicates which team member reviewed which other team member's code.

| Reviewer | Coder |
| -------- | ----- |
| vf0 |  Jonnyhu |
| Jonnyhu |  vf0 |
| csc301ab |  AlexandreStrain|
| AlexandreStrain |  csc301ab / bondaran |


-----

## Reviewer : Jonnyhu

 * I found a bug (_https://github.com/csc301-fall2014/Proj-UTM-Team2-repo/issues/11_), and managed to fix it (_1258015da57936b57257e050416ef1809f89e6d0_).
 
 * I suggested an improvement to the design of _topic.jsp, topic.class_ (for more details, _https://github.com/csc301-fall2014/Proj-UTM-Team2-repo/issues/16_), and fixed it on this commit (_bcce45267532277ae1564f14f932dab0fae5d703_). 
 
 * I thought that the implementation of _component Y_ was very elegant - 
The use of _Spring's build in Session Scope_ (_https://github.com/csc301-fall2014/Proj-UTM-Team2-repo/blob/master/src/main/webapp/WEB-INF/views/topics/topic.jsp_) made it really easy to add _Topic and reply_ to the rest of the system.

You can see all of my comments by searching for issues with the label _Jonnyhu_ (_link to search results_).


-----

## Reviewer : GitHub username: csc301ab / bondaran (same person)

* I found a bug (_https://github.com/csc301-fall2014/Proj-UTM-Team2-repo/issues/12_) and successfully solved it with the team member responsible for the section of code. Also made a fix for (_https://github.com/csc301-fall2014/Proj-UTM-Team2-repo/issues/13_)

* I suggested to improve the user page by adding tag in yellow saying "Invalid Phone Number" whenever the user's phone number was incorrect (_https://github.com/csc301-fall2014/Proj-UTM-Team2-repo/issues/14_). Also, (_https://github.com/csc301-fall2014/Proj-UTM-Team2-repo/issues/15_).

* I thought that the implementation of model was very elegant - the use of UserProfile model (_https://github.com/csc301-fall2014/Proj-UTM-Team2-repo/blob/master/src/main/java/com/ggmsg/mvc/models/UserProfile.java_)  used annotations to check if variables are not nulls

You can see all of my comments by searching for issues with the label _csc301ab_ (_https://github.com/csc301-fall2014/Proj-UTM-Team2-repo/issues/2#comments_).

-----

## Reviewer : GitHub vf0

Reviewer : vf0

- Jonny did a good job creating the database and the tables for our application. However, his table schema was questionable. The password tables were separate from the user profiles tables. There was no reason to split those tables, especially in this agile process.

- Jonny did a good job with the model classes. However we found several bugs in our spring.io row mapper because the table columns did not match our code.  (pull request review https://github.com/csc301-fall2014/Proj-UTM-Team2-repo/pull/3 )

- Jonny wrote most DAOs for the project and they were excellent. (i.e https://github.com/csc301-fall2014/Proj-UTM-Team2-repo/commit/339e9ba30e0b68e701fc6f4969ff4cc98e758172) I had to review some of the queries mostly to test them out and apply small fixes.

- Jonny included several important forms in the application, such as subscribing to users and topics (i.e https://github.com/csc301-fall2014/Proj-UTM-Team2-repo/commit/58f957c72c5454fc97ac43d9576a04ce464810cb ) 
- Jonny made important fixes to the login and signup. However he also committed merge conflicts into the source (i.e https://github.com/csc301-fall2014/Proj-UTM-Team2-repo/commit/948fdf834cc4c768c4e0a1f9438a88be3522301b#diff-88976911336cb2b6f19e6a6b673963d3R172). 

- Jonny wrote a lot of important parts for the subscription feature (i.e https://github.com/csc301-fall2014/Proj-UTM-Team2-repo/commit/58f957c72c5454fc97ac43d9576a04ce464810cb). This was really good code. 

- Jonny also made sure all our code worked on AWS and deployed correctly. He made sure that the application was compatible with Tomcat and Glassfish servers. 


-----

## Reviewer : GitHub AlexandreStrain

- Anton wrote all of the SMS notificiations for our project, which cohesively worked with the "forgot password" feature, profile as well as subscribers.
  * https://github.com/csc301-fall2014/Proj-UTM-Team2-repo/commit/b05ecef815845e46f42f288b8491c0c7c8adcb29

- Anton did a good job with the sign in page, as well as the creation of topics and replies.
  * https://github.com/csc301-fall2014/Proj-UTM-Team2-repo/commit/d35350d054d430e527610341ded28623b7e7b55e
  * https://github.com/csc301-fall2014/Proj-UTM-Team2-repo/commit/d2fcc0139351afc28c72966d6e7ef414f893fe3e
  * https://github.com/csc301-fall2014/Proj-UTM-Team2-repo/commit/7f8e803e0d9ab53bf151ce335e19aea1df639a0f

- Both Anton and I worked on the profile and discussed outside of gitHub issues ways to improve. We both concluded to phone verification to the profile page, which he coded. The code is a great addition.
  * https://github.com/csc301-fall2014/Proj-UTM-Team2-repo/commit/af823fa58fb701e09f3a248bf0d28ba0673b1448

- Anton added a Twillo builder to the code, which furthered the cohesiveness of the code within the system.
  * https://github.com/csc301-fall2014/Proj-UTM-Team2-repo/commit/befdaed68201ccb1b5342daea85421cebd6d9144

- Anton made an interface to better handle the Twillo requests, code is simple and straightforward to understand and easy implements itself to the rest of the system. 
  * https://github.com/csc301-fall2014/Proj-UTM-Team2-repo/commit/90193dfdb72065cc94b3786d416d5fd59d79eef8

- Both Anton and I found a bug which occurred when a user change their phone number due to the new phone number not being verified. 
  * https://github.com/csc301-fall2014/Proj-UTM-Team2-repo/commit/d2509cf3e2f6354eaaaec1cd67c9d2b061e5263d

-----

## Reviewer : GitHub username 5

-----

