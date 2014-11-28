#insert into user_profile (email, username, phonenumber)
#value ("bob@mail.toronto", "bob", "");

#insert into user_pw (username, password, email)
#value ("bob", "password", "bob@mail.toronto");	

#UPDATE user_profile
#SET phonenumber = "1-(222)-333-4444"
#WHERE username = "bob";

#select * from user_profile;

#select * from user_pw;

#user_pwselect username, email from user_pw
#where password = "password"

/*select username from user_pw
where username = "jonny"
and password = "password"*/

#INSERT INTO topic (subject, creator, body, time)
#VALUE ("Tickets to nowhere", "jonny", "$5.00", curtime())

#select * from topic
#order by timeuser_profile

/*SELECT * FROM topic
WHERE subject LIKE "%102%"
ORDER BY time DESC*/

#SELECT * from user_profile
#SELECT * from topicsubscription
#SELECT * from subscription

/*INSERT INTO subscription (iduser, idtopic)
VALUE (16, 19)*/

/*
SELECT topic.id AS id, 
	   topic.title AS title, 
	   topic.author AS author, 
	   topic.content AS content, 
	   topic.timestamp AS timestamp
FROM topic, subscription, user_profile
WHERE idsubscription = 2
AND subscription.iduser = user_profile.iduser
AND topic.id = subscription.idtopic*/

/*DELETE FROM subscription
WHERE iduser = 16
AND idtopic = 19*/

/*DELETE FROM user_sub
WHERE cur_user = 16
AND sub_to = 1*/

/*INSERT INTO user_sub (cur_user, sub_to)
SELECT 16 as cur_user, iduser as sub_to
FROM user_profile
WHERE username = "anton2"*/

/*DELETE FROM user_sub
WHERE (cur_user, sub_to) in (
SELECT 16 as cur_user, iduser as sub_to
FROM user_profile
WHERE username = "anton2"
)
AND cur_user = 16*/


/*SELECT topic.id AS id,
		topic.title AS title,
		topic.author AS author,
		topic.content AS content,
		topic.timestamp AS timestamp
FROM topic, user_sub, user_profile
WHERE user_sub.cur_user = 16
AND user_sub.sub_to = user_profile.iduser
AND topic.author = user_profile.username
ORDER BY timestamp DESC*/

/*SELECT count(*) FROM user_sub, user_profile
WHERE user_sub.cur_user = 16
AND user_profile.username = "jonny"
AND user_sub.sub_to = user_profile.iduser*/
SELECT phonenumber
FROM user_profile 
WHERE iduser
IN
(SELECT cur_user
FROM user_sub, user_profile
WHERE sub_to = user_profile.iduser
AND user_profile.username = "jonny")
