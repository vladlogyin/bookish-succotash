# Movie Web App

## Table of Contents
* [Project Description](#project-description)
* [Technology](#technology)
* [Project Management](#project-management)
* [Project Setup & Use](#project-setup--use)
* [Design](#design)
* [Testing](#testing)
* [Contributors](#contributors)

## Project Description
A web application based on the [Mflix](https://www.mongodb.com/docs/atlas/sample-data/sample-mflix/) MongoDB database which allows users to browse movies, theatres and comments. 

## Technology
- __Spring Boot__ [2.7.2](https://spring.io/projects/spring-boot)
- __Thymeleaf__ [3.0.4](https://www.thymeleaf.org/)
- __MongoDB__ [3.4.2](https://www.mongodb.com/)
- __JUnit Jupiter__ [5.8.2](https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-api/5.8.2)

## Project Management
We used the following tools to manage our project:
- __GitHub__ for version control.
- __Microsoft Teams Tasks__ for task management.
- __Discord__ for communication and meetings.
- __Code With Me (IntelliJ plugin)__ for collaborative work.

## Project Setup & Use

1. Clone the repository by following this [link](https://github.com/vladlogyin/bookish-succotash), click the __Code__ button and copy the __URL__.

![image](/images/CloneRepo.JPG)

2. Open IntelliJ IDEA and navigate to __File__ > __New__ > __Project from Version Control__.

![image](/images/IntelliJNewProject.JPG)

3. Paste the URL and choose where you want the project to be saved.

![image](/images/PasteURL.JPG)

4. Once the project is open make sure that all the dependencies have been downloaded, if unsure click the __Maven__ tab on the right hand bar and click __reload__.

![image](/images/MavenDependencies.JPG)

5. Navigate to SpartaFinalProjectApplication and __Run__.

![image](/images/FinalProjectApp.JPG)


## Design

### Mflix Database
The Mflix database can be viewed in more detail [here](https://www.mongodb.com/docs/atlas/sample-data/sample-mflix/). We modified the existing database by adding a Schedule table so that theatres can be scheduled.

We implemented CRUD operations for the following tables:
- Comments
- Movies
- Theatres
- Users
- Schedule

### Roles and Access Levels
We have 2 defined roles which are assigned by logging in:
- Admin
  - Can access all areas of the application
- User
  - Can access movies and theatres

### Login Details
- __Admin__
  - Username: Admin1
  - Password: 1234
- __User__
  - Username: Kira
  - Password: kira
  - Username: Jeff
  - Password: jeff

### User Interface (TODO)

## Testing

### Frontend (TODO)

### Backend

The following is the structure that we used to test our backend functionality (API), this example is demonstrating the testing for Comments.

![image](/images/ObjectSetup.JPG)

We begin by setting up the objects that we need for the tests. Comment has the attribute movie so we first create an instance of a Movie, next we create an instance of a Comment.

![image](/images/Create.JPG)

To test the create operation we first store the number of comments in the database using repo.count(), we then perform post operation through the URL "/comments/add" and pass it the Comment object that we created earlier. We expect that we receive a 2__ successful response message and that repo.count() now returns a value that is 1 greater than the previous count.

![image](/images/Request.JPG)

To test the request operation we perform get operation through the URL "/comments/id/{id}" where we give the test Comment object's id as the path variable. We expect that we receive a 2__ successful response and that we receive a json representing our Comment object.

![image](/images/Update.JPG)

To test the update operation we first change one of the attributes of our Comment object, in this case we change the email, we then perform put operation through the URL "/comments/update" and pass it the Comment object with a newly updated email. We expect that we receive a 2__ successful response message indicating that the Comment was successfully updated.

![image](/images/Delete.JPG)

To test the delete operation we perform delete operation through the URL "/movcomments/delete/{id}" where we give the test Comment object's id as the path variable. We expect that we receive a 2__ successful response and that the Comment no longer exists in the database.

## Contributors
- [Jeffrey Champion](https://m.media-amazon.com/images/M/MV5BOTNmZGU0MDYtNDE4Zi00NTE4LTg0ZGEtYzBjOWNjOTkwMzdlXkEyXkFqcGdeQXVyMTg3Nzg5ODY@._V1_.jpg)
- [Kira Coke](https://variety.com/wp-content/uploads/2021/07/Rick-Astley-Never-Gonna-Give-You-Up.png?w=681&h=383&crop=1)
- [Michael Matson](https://static.wikia.nocookie.net/agk/images/1/18/21499dba0eec71730fdaa0534a82e163.jpg/revision/latest?cb=20210511185219)
- [Vlad Logyin](https://note-store.com/upload/resize_cache/iblock/e43/325_380_2/Rick-Astley.jpg)
- [David Carew](https://americansongwriter.com/wp-content/uploads/2022/03/RickAstley.jpeg?fit=2000%2C800)
- [Jamie Scofield](https://static.ffx.io/images/$zoom_0.3543%2C$multiply_0.7554%2C$ratio_1.776846%2C$width_1059%2C$x_3%2C$y_27/t_crop_custom/q_86%2Cf_auto/f4c4e404c4edcc1c2b60450649a12ee841f1592a)
- [Billie Parsons](https://helios-i.mashable.com/imagery/articles/076ZWWQaiV20K9dE4YCKiNE/hero-image.fill.size_1200x1200.v1611610122.png)
- [Omar Tehami](https://www.pedestrian.tv/wp-content/uploads/2021/07/30/RICKastley.jpg?quality=80&resize=1280,720)