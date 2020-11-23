# CSIT Final Project

## Project Requirements

- [ ] Use of at least three Abstract Data Types
- [x] Use of inheritance and polymorphism
- [ ] Use of recursion.
- [x] Connection and use of a simple database
- [ ] At least one sorting algorithm
- [ ] At least one search algorithm
- [x] Use of a graphical user interface

## Tasks

- [ ] Add logon dialog.
- [ ] Add dialog for opening new rental.
    * Enter customer.
    * Enter vehicle.
    * Enter estimated mileage.
    * Calculates estimated price.
- [ ] Add dialog for closing a rental.
    * Enter mileage.
    * Calculates total price.
- [ ] Add dialog for entering new customers.
- [ ] Add dialog for entering new employees.
- [ ] Restrict user access.
    * Associates can add rentals and add vehicles.
    * Executives can add new employees.
- [ ] Complete menu bar (File, Help, etc.).

## Ideas

For the **three Abstract Data Types** requirement:
1. Create a generic **List** class and use in place of *java.util.List*.
    * add()
    * implements Iterable<>
2. Create a generic read-only **Query** class that wraps the custom List class.
    * filter() -> returns new Query object with filtered results
    * implements Iterable<>
3. ?

For the **recursion** requirement:
- If the custom List class uses Nodes, the length function can be recursive.

For the **sorting and search algorithms** requirement:
- Use sort and search algorithms to process database records and generate
simple text file reports from them.
