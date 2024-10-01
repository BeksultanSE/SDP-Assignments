1. CinemaConfig Class  
Design Pattern: Singleton  
The Singleton pattern ensures that a class has only one instance and provides a global point of access to it. In the CinemaConfig class, the static getInstance() method controls access to the single instance, making it ideal for managing global configurations like cinema name and number of screens.  
3. Movie Creation Classes  
Design Pattern: Factory Method  
The Factory Method pattern defines an interface for creating an object but allows subclasses to alter the type of objects that will be created. The MovieFactory class and its subclasses (RegularMovieFactory and IMAXMovieFactory) demonstrate this pattern by providing a method to create movie instances of different types without exposing the instantiation logic to the client.
4. MovieSchedule Class  
Design Pattern: Prototype  
The Prototype pattern allows for the creation of new objects by copying an existing object. The MovieSchedule interface includes a clone() method, enabling the StandardSchedule class to create copies of itself, which is useful for scheduling multiple instances of similar movies.  
5. TicketBooking Class  
Design Pattern: Builder  
The Builder pattern separates the construction of a complex object from its representation. The TicketBookingBuilder allows for a flexible way to create a TicketBooking instance by chaining method calls, making it easier to set various attributes without needing a complex constructor.  
6. UIFactory Class  
Design Pattern: Abstract Factory  
The Abstract Factory pattern provides an interface for creating families of related or dependent objects without specifying their concrete classes. The UIFactory interface and its implementations (DarkThemeFactory and LightThemeFactory) create related UI components (buttons and text fields) that share a common theme, allowing for easy theme switching and extension.  
