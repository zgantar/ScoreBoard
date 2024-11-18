This is a project for a job application at Sportradar.

The way the code is written is to simplify the use of this library so all the interaction, 
all parameters, are strings. In this way there is no need to know the classes used for 
retaining data and one can simply interact with it in more human friendly way. In this way
I've created two ways of updating score of a match in progress:
  - using stack as a stack and "manually" traversing and reordering it, since I 
wanted to show that with such a simple sorting requirements, "manual" work is quicker but harder to maintain.
  - using streams functionality of Java to show the much more maintenance friendly code, but 
it comes with a performance hit.

After I was done with the coding. I reviewed the requirements of the task and found out that
I implemented the said two methods in the wrong way. That is why I added the third way of
updating the score for a match with ints for home and away score.

All the required functionality is covered with tests.

Project consists of four packages:
- POJO 
  - All the entity classes used in this project are in this package.
- UseCases
  - All the business logic of the project is in this package to separate it from the POJO classes.
- Tests
  - Junit tests are stored in this package
- Exceptions
  - Two custom Exceptions to handle improper String inputs for name and score.