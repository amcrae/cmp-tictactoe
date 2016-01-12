#Cmp-TicTacToe
*Comparing several popular Java web application frameworks by building the same multiplayer Tic-Tac-Toe game in all of them.*

##Goals and Scope

The Tic-Tac-Toe game was chosen because the correct end result is simply defined and easily understood, which should maximize the time spent learning and applying the web frameworks and very little time on the application domain. Adding in-game chat and chat rooms increases the complexity in a way that is unnecessary for the basic game, but creates the need for functionality that a more realistic business application would have, such as:
 * permitting different actions for different classes of users on the same URL. (eg Room administrators versus normal players).
 * presenting different outputs for different classes of users on the same URL.
 * creating multiple pages that link together and must be navigated in a fixed sequence. (eg: Lobby->Room->Game->Rules->Game.)
 * repeating the same information on multiple pages, preferably without duplicating presentation code (eg a reusable component).

Some functions which most business applications would need, but which are unnecessary or specifically excluded from this project, are: data storage and Object/Relational mapping, application-to-application interfaces, JEE server integration, federated identity, LDAP/OAuth/SAML authentication. The focus is on the presentation tier. An object configuration and DI framework like Spring Beans may be used for the game core if time permits. 

The game will need to implement game rules on the server to avoid trusting results sent in by clients. The project emphasis is on the web GUI implementation complexity. To gauge the time and artifact complexity more easily it is preferable to reuse the same code for game mechanics with each framework. That's why the scope of this project will be initially limited to server-side frameworks.

In the future an AJAX API could be created around the code, allowing browser frameworks (AngularJS, JQueryUI, etc) for client side languages (Javascript, ES6, TypeScript, etc) and single-page-applications to be compared against the same API server.

In the distant future it will be possible to re-implement the core game in another server-side language, such as Scala, Python, or C#3/Mono, then investigate web frameworks for those other languages. However due to author bias and the dominance of Java in enterprise environments the initial comparison is based on the TicTacToe_Java project.

##Value Proposition
The two main valuable outcomes of the project should be:  
 * greater expertise in the specific frameworks studied, which can translate to more job and consulting opportunities in those frameworks,
 * better general understanding of the opposing design forces in GUI applications, which should allow lower risk design decisions on future projects as well as a better ability to evaluate other new web UI frameworks while the programming world continues to churn out more of them.

##Phase 1 - Core Application Domain Library
Phase 1 is specifying and building the core logic of Tic-Tac-Toe, such as game board and player state representation, gameplay logic, and some extra social features. In phase 1 there is no GUI and the software can be driven through test harnesses only. (Maybe there will be a command line UI if time permits.)

To make the test authentic it will be necessary to implement a subset of the features and release them as a tagged version "R1.0", then implement the rest of the features and possibly change the arity (multiplicity) of some object relationships, then release this final library as a later version "R2.0". Similarly each web framework implementation will have a "R1.0" and "R2.0" version. This allows the realistic process of adding features to an existing GUI to be trialled in all the frameworks. The authenticity of the final comparison is likely to rely heavily on how severe the changes are between "R1.0" and "R2.0", as the more upheaval that can be efficiently implemented the better the framework will be over the lifecycle of a product. 

It is also possible that good design will make more difference than the technology choice when coping with requirement volatility. This prompts the question of whether the R1.0 core design should be deliberately designed badly in order to show web technology differences more clearly. On the other hand, a dramatic redesign is occasionally contemplated and rarely done early in the life of a product, because there is rarely a desire by customers or management to spend money on improving the internal design purity of a product which appears to work acceptably on the outside. Probably the best answer here, remembering the purpose of the comparison project, is yes the design should be bad because any technology that makes a rework less costly to do is more likely to result in design improvements actually being funded.

One realistic source of requirement volatility is feature creep, so making the initial design simplistic can be done by simply limiting the number of features supported. In this project the R1.0 functionality will be a single web page which allows two people at the same client browser to enter their player names and play the game. The R2.0 functionality will include all the other social features that were anticipated. This upgrade will require the player name entry form to move from the Game page to the Lobby page, but the player name must be displayed on the Game page in the same manner as R1.0.


##Phase 2 - Technology Analysis Plan and Metrics
Phase 2 will be defining the scoring criteria that will be used on all the frameworks, so that the activities that need to be recorded/rated during development are known in advance. This is done after the core game logic has been completed so that the types of GUI construction tasks that will be needed are better understood. 

##Phase 3 - Web Framework Implementations 
Phase 3 will be iterating through different web application frameworks and implementing a web UI on top of the basic game logic according to the release R1.0 requirements, then going back through all the frameworks and adding the R2.0 functionality, noting the required effort and other metrics along the way.

The web application frameworks which will be tested initially are:

| Framework Name | Version | Main Pattern | DHTML? | AJAX? | Rationale for inclusion | Examples |  
| ------------- |------- | ------------ | ------ | ----- | ----------------- | ----------------- |  
| Apache Wicket | 7.0 | Pull MVC | Y | Y | Made by some of the people who built Tapestry. | http://builtwithwicket.tumblr.com |  
| JSF 			| 2.2 | Pull MVC | Y | Y | The official Sun/Oracle endorsed option.| ? |  
| Spring MVC  | 4.2.2 | Push MVC | ? | ? | Push instead of Pull and has Spring support. | ? |

##Phase 4 - Framework Comparison
Phase 4 will compare the frameworks both subjectively and objectively. Rather than pick a "winner" the goal is to describe the type of project for which each framework would be the best option. The analysis of project types and the choice of project characteristic parameters is therefore determined by the need to differentiate between one implementation technology option versus another, rather than an independent set of project attributes that covers the entire breadth/depth of global software project diversity. 
It is also possible that the only significant difference between one framework and another is in a aspect that was not foreseen at the outset and so some modification of comparison criteria may be needed. This could certainly happen as more web frameworks are discovered and implemented in the project.
