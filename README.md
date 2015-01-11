To Run:
=======

Requires Java1.7

Go to the project directory and run
`ant`

`./codingchallenge` or `codingchallenge.bat`

You can also include parameters on those scripts

or just run the Main class in eclipse.

There are a few test files that can be used in the solution directory. 
Obviously the one in the junit directory is explicitly used by the junits.

Design:
=======

The solution to this problem is fairly simple, I extracted a few of the major functions of the project into seperate classes:

Digit Translator Service - This is the only part of the system that knows anything about digits, and could easily be extended
to solve other similar problems, for instance if you included spaces and asterisks.

Dictionary - The dictionary side of the system could really be used with any other kind of dictionary. The Dialling Dictionary
really just adds to the basic concept of a dictionary, also providing the function to read digits into words.

Digit to Word processor - This is really the smarts behind the solution, and recursively loops through sections of the word until
it finds all solutions. This could also be solved with a stack, but the recursive loop is a lot easier to read and maintain.

Main - handles all the user interactions this really has only output tasks and calls other classes to get it's job done.

File Import Utils - Handles all parts of file interactions, including clensing of the data.
