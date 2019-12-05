# spring-blog


[![versionJava](https://img.shields.io/badge/jdk-11-brightgreen.svg?logo=java)](https://openjdk.java.net)
[![versionSpring](https://img.shields.io/badge/Spring-2.2.1-brightgreen)](https://spring.io/)
[![mavenVersion](https://img.shields.io/badge/Maven-3.6.1-brightgreen)](https://maven.apache.org/)
[![versionThymeleaf](https://img.shields.io/badge/thymeleaf-3.011-brightgreen)](https://www.thymeleaf.org/)
[![versionSecurity](https://img.shields.io/badge/security-5.16-brightgreen)](https://spring.io/projects/spring-security/)
[![versionJUnit](https://img.shields.io/badge/jUnit-5.3.1-brightgreen)](https://junit.org/junit5//)
[![versionLombok](https://img.shields.io/badge/lombok-1.18.8-brightgreen)](https://junit.org/junit5//)
[![versionMockito](https://img.shields.io/badge/mockito-2.23-brightgreen)](https://site.mockito.org/)


### About Application
##
I have always wanted to create blog application, so here comes my spring blog app. In my project I decided to use thymeleaf instead of creating REST application
and connect it with Angular because I found it more consisable but as new ideas come to my head and more lines of code have been added, this approach has some weakenss.
First of all, it would be more clean design to make it full stack application but on the other hand, such a solution takes much more time to develop and there is more places for mistakes.
On the other hand, it is much more easier to deliver only spring-boot app and also some experience in thymeleaf is nice to have in my opinion.
##

### App Features

Application covers core features of blog:
* User registration
* Reset password feature
* Forgotten password feature
* User login/log out
* User profile 
* User avatars (every user has his own profile picture, which can be changed)
* Admin has ability to create new post, add image to it, title and post content
* Every post has comments section, where only logged users can add new comments
* Each post has his own comment size feature, post creation date
* Admin has ability to delete any comment which is realeted to single post
##
### UserRegistration