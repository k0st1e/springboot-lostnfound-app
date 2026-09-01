# Spring Lost n' Found Items Application 

<img src="https://github.com/k0st1e/springboot-lostnfound-app/blob/main/showcase.png" width="800">

## Tools used to develop the application

* Spring MVC with Thymeleaf, Session-based Authentication.
* [Aurora DX](https://docs.getaurora.dev/dx/aurora-dx-intro): Amazing developer workflow. Rock-stable and feature packed immutable Linux distro.
* [MySQL](https://hub.docker.com/_/mysql), [phpmyadmin](https://hub.docker.com/_/phpmyadmin) and [lazydocker](https://github.com/jesseduffield/lazydocker) - Docker related.
* [JPA Buddy](https://jpa-buddy.com/): Awesome plug-in. Used it to create entities and carry out database migrations with flyway.
  
## How to run it?

* Start a local MySQL instance with your tool of choice.
* Import the database schema from `lostfoundapp.sql`.
* Set the database credentials in the application properties file.
* Run the application and visit `localhost:8080`.
* Register a new user and start using the application.

## Features Showcase

**Adding items**
<br><br>
<img src="https://github.com/k0st1e/springboot-lostnfound-app/blob/main/screens/submitting-items.png" width="650">

**Item details**
<br><br>
<img src="https://github.com/k0st1e/springboot-lostnfound-app/blob/main/screens/item-details-for-each-one.png" width="650">

**Reactive photo columns and navigation bar with media queries**
<br><br>
<img src="https://github.com/k0st1e/springboot-lostnfound-app/blob/main/screens/media-queries.png" width="650">

**Message board below item details**
<br><br>
<img src="https://github.com/k0st1e/springboot-lostnfound-app/blob/main/screens/message-board.png" width="650">

**Simple item statistics using JavaScript**
<br><br>
<img src="https://github.com/k0st1e/springboot-lostnfound-app/blob/main/screens/simple-stats.png" width="650">

**Alerts flow**
<br><br>
<img src="https://github.com/k0st1e/springboot-lostnfound-app/blob/main/screens/alerts-flow-example.png" width="650">

**Nav-bar alert shows up regardless of where the user is in the application**
<br><br>
<img src="https://github.com/k0st1e/springboot-lostnfound-app/blob/main/screens/alerts-show-up-everywhere.png" width="650">
