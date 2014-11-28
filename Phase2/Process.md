* Summary of our planning and review meetings.
  Note: Most of these meetings were also group coding sessions where we worked collaboratively to solve problems and
  work on code.
  ** Sprint 1
    Orgainized what team member would do which feature within the web application.
    This sprint was reserved for setting up the web application and the database that would be used for our project.
    (MySql and Spring)
  ** Sprint 2
    Began working collectively on our parts of the phase, making sure what we accomplished would not affect others.
    We took this meeting session to present what work we had did to other team members and make sure everything
    connected properly.
  ** Sprint 3
    By this meeting we had finished a lot of the code base of the project and used this meeting to find, fix, or
    present bugs/issues that we had found within our code. What we found interesting that most of the code completed
    was 80% with 10% bugs/issues that need to be solved.
  ** Sprint 4
    Here we discussed what was left to do. We found that there was little to do in terms of MVP now and so we decided
    on where the direction of our code should go (we were mainly finialzing what we had and discussing about the name
    of our product). It was a split vote between changing the name and keeping it.

* For our "daily" scrum meetings, we used Skype chat. As Skype is an ongoing tool for us to communicate with every
  group member, the dates we used to meet up are everyday of the phase (October 10 - October 31). Here is a summary
  of what we discussed in our Skype chats.
  ** We usually asked questions regarding the direction of the project code (ie. can someone explain what this code
     does, should I go ahead and do this now, is this complete yet, etc)
  ** We also notified others of the current status of our part of the project (ie. I finished this guys so everyone
     should pull).
  ** We also troubleshoot other people's problems (ie. I keep getting this error, anyone got an idea?)

* The tools we used heavily on the GitHub issue management system was the pull/merge request and the commit viewer.
  It was here where we reviewed and commented on other team member's code. The combination of these two services also
  helped us look for bugs (such as code that was not working nicely with other pieces) and help determine a way to
  solve them.
  
* We dropped what was one of our key tables within our database (user_pw table, which most of our functions used such
  as login, signup). We decided to remove this table because it was a bad design implementation; we had multiple tables
  having the same information or that were not being used properly. In order to make sure these tables were being useful
  to the project, we changed how our code accesses the database and the tables within.
