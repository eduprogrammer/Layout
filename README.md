Library written in Java that build a full User Interface (GUI),
based on the JavaFX core. This package contains two interfaces 
and one class for the job.

Dependency: This library requires the Common.jar file.
Get it from the Common repository:

https://gitgub.com/eduprogrammer/Common

1) Skelet Class:

* This class contains several methods for building full user interfaces,
* used for manipulating large GUI programs.
* It is built upon the JavaFX library
* and makes its usage easier and more efficient.
* This class only can be instantiated with the static method getInstance().
* Also, the class Skelet has a static class called Builder,
* which may be initialized with a default constructor for create UI objects

2) FillHandler Interface

* This interface acts as a callback
* for filling the box layout
* from the Skelet class.

3) Fired interface

* This interface contains several methods for working
* with the events fired from the objects returned from
* Skelet class.

