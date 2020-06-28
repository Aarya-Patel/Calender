# Calender

This was part of my CSC207 - Software Design course project that my team and I had built. It heavily focusses on the use of software design patterns and implementation. We built a calender app with a GUI interface that allows you to create events, alerts, memos, and tags to allow for searching, notifying and various other features. 

## Important note
* This program requires mysql database for storage.
* All input data format is in dd/MM/yyyy HH:mm:ss, example: 23/07/2022 13:34:45 means 
July 23rd 2022, at 13 hours 34 minutes 45 second
* Event start time cannot be after end time
* To prevent data loss when exiting program, please use provided logout option

## Configuration
* You must have MySql server live and have created a database.
* Then change the connection() method information to your personal info and run the code

## Design patterns used:

1: Facade design pattern
* Classes involved: ViewFactory, CalenderManagement, ViewManagement
* Description: In our program design, the ViewFactory class is responsible for providing all the functionality in the program, which includes two main types of functionality: management (e.g. create, edit, remove Event/Memo/Tag/Alert Objects) and viewing functionality (e.g. display Events/Memos/Tags according to different criteria). With this program design, the ViewFactory class is responsible for all two types of functions which would violate the Single Responsibility Principle and has low cohesion. To improve the situation, we added two extra classes, CalenderManagement and ViewManagement, and have them responsible for the two types of functions. In particular, CalenderManagement is responsible for the management functions while ViewManagement is responsible for the viewing functions. By applying this design pattern, we can separate the implementation of the two types of functions which can decreases coupling between them. Also, by encapsulating each type of function in a single class, we can achieve a higher cohesion.


2: Observer design pattern
* Classes involved: Alert and CalenderManagement, Event and ViewManagement
* Description: According to the data structure we used to store the entities (e.g. Event, Memo, Alert), whenever an Alert or Event object fires, we need to remove or edit the object from the calendar. In other words, whenever the fireAlert() method in the Alert class and the fireEvent() method in the Event class runs, we need to run the removeAlert() method in the CalenderManagement class and removeEvent() method in the ViewManagement class. These consistencies between the methods allow the application of the observer design pattern. In particular, since the removeAlert() method depends on the fireAlert() method, we made the Alert class extends the Observable class and have the CalenderManagement class implements the Observer interface and be the observer of each alert object, for the same reason, we made the Event class Observable and ViewManagement class be the observer of each event object. With this design pattern, we can separate the two methods in different classes and achieve a lower coupling.



3: Factory design pattern
* Classes involved: ViewFactory and all GUI classes in the gui folder
* Description: In our program design, we implemented the GUI by creating a new GUI object of the selected functionality every time a function is selected which displays the function in a graphic interface. Since the ViewFactory class is responsible for all the functions in the program while each function is related to a specific GUI class object, this relationship between the ViewFactory class and all the many GUI classes allows the application of the Factory design pattern. In particular, we had the ViewFactory class to handle all the GUI objects creation which can help obscure the creation process of the GUI objects 



4: Builder design pattern
* Classes involved: ViewFactoryBuilder, ViewFactory, CalenderManagement, ViewManagement
* Description: As mentioned above, in our program design, we implemented a facade design pattern between the ViewFactory, CalenderManagement and ViewManagement class. While the facade design pattern can provide high cohesion low coupling design, this pattern makes the creation of ViewFactory object very complicated and requires multiple steps. This creation of a complex structure provided an opportunity to apply the builder design pattern. In particular, we created a new ViewFactoryBuilder class to handle the creation of a ViewFactory object. With this adjustment, we can obscure the complex creation and internal structure of the ViewFactory object.


## Contributors
* Ansh Chokshi
* Ashu Banjara
* Camus Lam
* Mandi Qu
