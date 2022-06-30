# ingsw2022-AM30
Progetto di Ingegneria del Software AA 2021/2022. Partecipanti: Anzillotti Giuseppe, Asciutti Daniele, Borgonovo Samuele

- ###   10721209    Giuseppe Anzillotti <br> ([@GiuseppeAnzillotti](https://github.com/GiuseppeAnzillotti)) <br>giuseppe.anzillotti@mail.polimi.it
- ###   10678078    Daniele Asciutti    <br>([@DanieleAsciutti](https://github.com/DanieleAsciutti)) <br>daniele.asciutti@mail.polimi.it
- ###   10669269    Samuele Borgonovo <br> ([@SamueleBorgonovo](https://github.com/SamueleBorgonovo)) <br>samuele.borgonovo@mail.polimi.it


| Functionality                 |                       State                        |
|:------------------------------|:--------------------------------------------------:|
| Basic rules                   | 🟢 |
| Complete rules                | 🟢 |
| Socket                        | 🟢 |
| GUI                           | 🟢 |
| CLI                           | 🟢 |
| Multiple games                | 🟢 |
| Persistence                   | 🔴 |
| Character Cards               | 🟢 |
| 4 Players Game                | 🔴 |
| Disconnections<br/>Resilience | 🟢 |

# Eriantys

-------
## Setup

-----------

- In the [Deliveries](Deliveries) folder there is one jar file to start both server and client.
- The Application can be run with the following command:
    ```shell
    > java -jar Eriantys.jar
    ```
- The Application contains a guided configuration to setup a server or to start a client
- - When starting the server it gives LAN ip. If computers are connected via Wi-Fi, it's necessary to search for the ip in computer's settings

  ### CLI
- To use the client with CLI the computer needs an UTF-8 shell

  ###GUI
- To use the client with GUI the computer needs monitor resolution of 1000 x 700 px
  (We recommend a full HD screen with an OS zoom of 125%)

## Test Coverage

The total coverage of model is 87% (823/994).
In the following table there is the coverage of model's packages

| Package |    Coverage    |
|:--------|:--------------:|
| board   | 193/194 (99%)  |
| effects | 156/156 (100%) |
| game    | 365/485 (75%)  |
| player  | 109/109 (100%) |

Model contains all the logic of our project, so the controller has not been tested


## Tools

---------------
* [Astah UML](https://astah.net/) - Initial UML Diagram
* [Maven](https://maven.apache.org/) - Dependency Management
* [IntelliJ](https://www.jetbrains.com/idea/) - IDE
* [JavaFX](https://openjfx.io) - Graphical Framework

## License

----------

This project is developed in collaboration with [Politecnico di Milano](https://www.polimi.it) and [Cranio Creations](http://www.craniocreations.it).
 