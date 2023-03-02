# Social_Network

Good afternoon, everyone, and thank you for joining me today for the presentation of my social network personal project. As a software developer, I have always been fascinated by the power of social media to connect people from all around the world. In developing this project, I had the opportunity to learn and apply my knowledge of software architecture and object-oriented programming to create a platform that is not only visually appealing but also efficient and scalable. My aim was to create a social network that is built on the solid foundation of sound software design principles, promoting a clean codebase and high maintainability. I'm excited to showcase the results of my efforts today, and share with you the features, the architecture, and the future roadmap of my project. So, without further ado, let's dive into the world of Socialio.

The application opens with the login window.
![plot](./screenshots/login_page.png)

Each new person can register in a very simple way.
![plot](./screenshots/register_page.png)
If a field is filled in incorrectly, the user receives a notification, and the account is not created.
![plot](./screenshots/fields_incorrectly.png)
Every password (both from the login and from the register) is encrypted with AES-CBC, with a key of 256.

After login, the feed page is opened.
![plot](./screenshots/feed_page.png)

In the search page, the logged user can search for other users using their username or their names.
![plot](./screenshots/search_page.png)
He can see the profile of every found user. 
Profile with a default picture:
![plot](./screenshots/giani_profile.png)
Personalized profile:
![plot](./screenshots/diana_profile.png)
The logged user can send friend requests/cancel friend requests/accept friend requests/decline friend requests both from search page and from the profile of the other user.

The logged user can see his notifications.
User with 0 notifications:
![plot](./screenshots/0notifications.png)
User with one friend request:
![plot](./screenshots/1friend_request.png)
User with two friend requests:
![plot](./screenshots/2friend_requests.png)
Friend requests and notifications update in real time using observer pattern.

The messages button will open the list with all conversations:
![plot](./screenshots/conversations.png)

The logged user can search a friend, a person with a pending friend request or  a person with whom he has spoken in the past and send a message.
![plot](./screenshots/search_user_send_message.png)

When the logged user press a conversation:
![plot](./screenshots/conversation0.png)
The logged user can send a message. If the message is sent, the logged user will see o:
![plot](./screenshots/conversation1.png)
If the message is received, the logged user will see oo:
![plot](./screenshots/conversation2.png)
If the message is seen, the logged user will see:
![plot](./screenshots/conversation3.png)

The logged users will know when they have an unseen message by the red message button:
![plot](./screenshots/conversations4.png)
![plot](./screenshots/conversations5.png)


Every user can see their own profile:
![plot](./screenshots/own_profile0.png)
Every user can edit their own profile:
![plot](./screenshots/own_profile1.png)
