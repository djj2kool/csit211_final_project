# CSIT Final Project

## Project Requirements

- [ ] Use of at least three Abstract Data Types
    1. [x] ListADT interface
    2. [x] Collection interface (java.util.Collection)
    3. [ ] ?
- [x] Use of inheritance and polymorphism
- [x] Use of recursion.
    * LinkedList.size() uses recusion.
    * Used to display number of records fetched in GUI.
- [x] Connection and use of a simple database
- [ ] At least one sorting algorithm
- [ ] At least one search algorithm
- [x] Use of a graphical user interface

## Tasks

- [ ] Add logon dialog.
- [x] Add dialog for opening new rental.
    * Enter customer.
    * Enter vehicle.
    * Enter estimated mileage.
    * Calculates estimated price.
- [ ] Add dialog for closing a rental.
    * Enter mileage.
    * Calculates total price.
- [x] Add dialog for entering new customers.
- [x] Add dialog for entering new employees.
- [x] Add dialog for entering new vehicles.
- [ ] Validate new objects before adding records to database.
- [ ] Restrict user access.
    * Associates can add rentals and add vehicles.
    * Executives can add new employees.
- [x] Add status bar to bottom of GUI
    * Show record count (uses Query.size() which satisfies recursion requirement)
- [ ] Complete menu bar (File, Help, etc.).

## Ideas

For the **three Abstract Data Types** requirement:
1. ListADT interface -> LinkedList class
2. Collection interface -> Query class
3. ?

For the **sorting and search algorithms** requirement:
- Use sort and search algorithms to process database records and generate
simple text file reports from them.
