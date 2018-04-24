# Exercise - Technical Comparison of an SQL and Graph Database¶

#### 1: Setup an SQL and a Neo4j database respectively.

- I have setup the database both in SQL using SQL workbench and Neo4j. 

#### 2: Import the data from the social network (endorsement graph https://github.com/datsoftlyngby/soft2018spring-databases-teaching-material/raw/master/data/archive_graph.tar.gz) into a Neo4j database and into an SQL database respectively. 

- Unfortuantly after spending many hours trying to get get large CSV file to work within Neo4j i decided to contiune the assignment using the small version of the files. Therefore I have choosen to use the two smaller CSV files in both SQL and Neo4j. I did this because otherwise I would have been stuck with the assignment. 

#### 3: Construct queries in SQL and in Cypher, which find

- all persons that a person endorses, i.e., endorsements of depth one.

SQL: 

SELECT name, node_id FROM nodes WHERE node_id IN "
                + "(SELECT endorses FROM edges WHERE id IN "
                + "(SELECT node_id FROM nodes WHERE name = 'Odessa Satmary'))

Cypher: 

MATCH (:Person {name: '${name}'})-[:ENDORSES]->(other) RETURN distinct other

- all persons that are endorsed by endorsed persons of a person, i.e., endorsements of depth two.

SQL:

SELECT name, node_id FROM nodes WHERE node_id IN \n"
                + "(SELECT endorses FROM edges WHERE id IN\n"
                + "(SELECT endorses FROM edges WHERE id IN \n"
                + "(SELECT node_id FROM nodes WHERE name = 'Odessa Satmary')))

Cypher: 

MATCH (:Person {name: '${name}' })-[:ENDORSES]->()-[:ENDORSES]->(other_other) RETURN distinct other_other

- endorsements of depth three.

SQL:

SELECT name, node_id FROM nodes WHERE node_id IN \n"
                + "(SELECT endorses FROM edges WHERE id IN\n"
                + "(SELECT endorses FROM edges WHERE id IN \n"
                + "(SELECT endorses FROM edges WHERE id IN \n"
                + "(SELECT node_id FROM nodes WHERE name = 'Odessa Satmary'))))

Cypher: 

MATCH (:Person {name: '${name}' })-[:ENDORSES]->()-[:ENDORSES]->()-[:ENDORSES]->(other) RETURN distinct other

- endorsements of depth four.

SQL:

SELECT name, node_id FROM nodes WHERE node_id IN \n"
                + "(SELECT endorses FROM edges WHERE id IN\n"
                + "(SELECT endorses FROM edges WHERE id IN \n"
                + "(SELECT endorses FROM edges WHERE id IN \n"
                + "(SELECT endorses FROM edges WHERE id IN \n"
                + "(SELECT node_id FROM nodes WHERE name = 'Odessa Satmary')))))

Cypher: 

MATCH (:Person {name: '${name}' })-[:ENDORSES]->()-[:ENDORSES]->()-[:ENDORSES]->()-[:ENDORSES]->(other) RETURN distinct other

- endorsements of depth five.

SQL:

SELECT name, node_id FROM nodes WHERE node_id IN \n"
                + "(SELECT endorses FROM edges WHERE id IN\n"
                + "(SELECT endorses FROM edges WHERE id IN \n"
                + "(SELECT endorses FROM edges WHERE id IN \n"
                + "(SELECT endorses FROM edges WHERE id IN \n"
                + "(SELECT endorses FROM edges WHERE id IN \n"
                + "(SELECT node_id FROM nodes WHERE name = 'Odessa Satmary'))))))

Cypher: 

MATCH (:Person {name: '${name}' })-[:ENDORSES]->()-[:ENDORSES]->()-[:ENDORSES]->()-[:ENDORSES]->()-[:ENDORSES]->(other) RETURN distinct other

#### 4: Write a program in a programming language of your choice, such as Java, C#, etc., where the program executes the above queries for twenty random nodes against the two respective databases. That is, you run each query on the same twenty random nodes.

- I have choosen to write my program in Java. 

#### 5: Extend your program, so that it measures the average and median execution times of each query. That is, you run a benchmark for the two databases.
You collect your measurement results and present them with an evaluation of your experiment in a Markdown file in a repository on Github. That is, you hand in this assignment via Github.
Describe the setup of your experiment. That is, what does someone has to do/install/setup to reproduce your experiment?
Present the execution time of each query each of the 20 random nodes/persons per database.
Present the average and the median runtime of each of the queries per database.
Give an explanation of the differences in your time measurements.
Conclude which database is better suited for this kind of queries and explain why.
Push your solution, source, code, and presentation of the results to a Github repository and hand-in a link to that repository on peergrade.
The hand-in time is latest 24. April 2018 at 24o'clock .
