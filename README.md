# Budget App API
This is the set of APIs to interact with the different portions of the Budget App.

## Technology Used
**Swagger** is used to generate and document the RESTful APIs used. The code generation definitions and config are located in the **generator** folder.
To generate the code, simply run the `gen.sh` script.

Information is stored in the in-memory database of **H2**

Database schema management and initial data loading is done using **Liquibase**. This is located in the _resources/db_ section.

Whole application is running using **Spring Boot**

## Brief explanation of App
This app is a combination of a normal budget app, mixed with EveryDollar format and then using David Ramsey's envelope system

### Income
Income are positive contributions to the dollar amount for the month

<ins>Fields:</ins>
- **ID**: Unique id of the table
- **Name**: The name of the income. i.e. Paycheck
- **Planned Amount**: The amount planned for the income
- **Received Amount**: The amount actually received

### Group
A group is a collection of items that will reduce the monthly amount by a certain amount. Example: Phone bill, Netflix, etc.

<ins>Fields:</ins>
- **ID**: Unique id of the table
- **Name**: Name of the group. i.e. Housing
- **Type ID**: The foreign key for the Group Type
- **Color**: The color of the heading of group

### Group Item
The item that goes into a group. This will be the line item of a Group.

<ins>Fields:</ins>
- **ID**: Unique id of the table
- **Name**: The name of the type. i.e. Standard, Debt, Savings, Envelope
- **Amount**: The amount for the item. This will be overridden if a Bill is connected.
- **Pay Type ID**: The pay period type. i.e. Weekly, Monthly
- **Group ID**: The foreign key to the group the item belongs to
- **Bill ID**: The foreign key to the bill associated to the item. This overrides the amount field.

### Pay Period Type
This defines when the group item happens in a pay cycle. Will the item occur every week? Once a month?

<ins>Fields:</ins>
- **ID**: Unique id of the table
- **Type**: What is the pay period type? (enum)

### Bill
A bill is a defined bill item. This will incorporate the due date and amount into group items

<ins>Fields</ins>
- **ID**: Unique id of the table
- **Name**: The name of the bill
- **Description**: Describe the bill
- **Due Date**: The date the bill is due
- **Amount**: How much is the bill?

### Envelope
Define an envelope using the group items

<ins>Fields:</ins>
- **ID**: Unique id of the table
- **Name**: The name of the envelope
- **Amount**: The amount to go into the envelope
- **Pay Type ID**: The pay period type. i.e. Weekly or Monthly