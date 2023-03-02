# Social_Network

Good afternoon, everyone, and thank you for joining me today for the presentation of my social network personal project. As a software developer, I have always been fascinated by the power of social media to connect people from all around the world. In developing this project, I had the opportunity to learn and apply my knowledge of software architecture and object-oriented programming to create a platform that is not only visually appealing but also efficient and scalable. My aim was to create a social network that is built on the solid foundation of sound software design principles, promoting a clean codebase and high maintainability. I'm excited to showcase the results of my efforts today, and share with you the features, the architecture, and the future roadmap of my project. So, without further ado, let's dive into the world of Socialio.<br/>
<br/>
The application opens with the login window.<br/>
![plot](./screenshots/login_page.png)<br/>
<br/>
Each new person can register in a very simple way.<br/>
![plot](./screenshots/register_page.png)<br/>
If a field is filled in incorrectly, the user receives a notification, and the account is not created.<br/>
![plot](./screenshots/fields_incorrectly.png)<br/>
Every password (both from the login and from the register) is encrypted with AES-CBC, with a key of 256.<br/>
<br/>
After login, the feed page is opened.<br/>
![plot](./screenshots/feed_page.png)<br/>
<br/>
In the search page, the logged user can search for other users using their username or their names.<br/>
![plot](./screenshots/search_page.png)<br/>
He can see the profile of every found user. <br/>
Profile with a default picture:<br/>
![plot](./screenshots/giani_profile.png)<br/>
Personalized profile:<br/>
![plot](./screenshots/diana_profile.png)<br/>
The logged user can send friend requests/cancel friend requests/accept friend requests/decline friend requests both from search page and from the profile of the other user.<br/>
<br/>
The logged user can see his notifications.<br/>
User with 0 notifications:<br/>
![plot](./screenshots/0notifications.png)<br/>
User with one friend request:<br/>
![plot](./screenshots/1friend_request.png)<br/>
User with two friend requests:<br/>
![plot](./screenshots/2friend_requests.png)<br/>
Friend requests and notifications update in real time using observer pattern.<br/>
<br/>
The messages button will open the list with all conversations:<br/>
![plot](./screenshots/conversations.png)<br/>
<br/>
The logged user can search a friend, a person with a pending friend request or  a person with whom he has spoken in the past and send a message.<br/>
![plot](./screenshots/search_user_send_message.png)<br/>

When the logged user press a conversation:<br/>
![plot](./screenshots/conversation0.png)<br/>
The logged user can send a message. If the message is sent, the logged user will see o:<br/>
![plot](./screenshots/conversation1.png)<br/>
If the message is received, the logged user will see oo:<br/>
![plot](./screenshots/conversation2.png)<br/>
If the message is seen, the logged user will see:<br/>
![plot](./screenshots/conversation3.png)<br/>
The state of every message will be updated in real time.<br/>
<br/>
The logged users will know when they have an unseen message by the red message button:<br/>
![plot](./screenshots/conversations4.png)<br/>
![plot](./screenshots/conversations5.png)<br/>
<br/>
<br/>
Every user can see their own profile:<br/>
![plot](./screenshots/own_profile0.png)<br/>
Every user can edit their own profile:<br/>
![plot](./screenshots/own_profile1.png)<br/>

The application is not vulnerable to sql injection and is very easy to scale
