This is a project for a job application at Sportradar.

The way the code is written is to simplify the use of this library so all the interaction, 
all parameters are strings. In this way there is no need to know the classes used for 
retaining data and one can simply interact with it in more human friendly way.

All the required functionality is done and tested. I've created two methods for updating 
score, one using streams functionality and one updating the list by moving through the 
stack "manually" since I wanted to show that with such a simple sorting requirements, 
"manual" functionality is quicker but the streams functionality is much more code 
maintenance friendly.

Project consists of three packages:
- POJO 
  - All the classes used in this project are in this package.
- UseCases
  - All the business logic of the project is in this package to separate it from the POJO classes.
- Tests
  - Junit tests are stored in this package
- Exceptions
  - Two custom Exceptions to handle improper input