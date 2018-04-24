# Exercise - Technical Comparison of an SQL and Graph Database¶

#### 1: Setup an SQL and a Neo4j database respectively.

- I have setup the database both in SQL using SQL workbench and Neo4j. 

#### 2: Import the data from the social network (endorsement graph https://github.com/datsoftlyngby/soft2018spring-databases-teaching-material/raw/master/data/archive_graph.tar.gz) into a Neo4j database and into an SQL database respectively. 

- Unfortuantly after spending many hours trying to get get large CSV file to work within Neo4j i decided to contiune the assignment using the small version of the files. Therefore I have choosen to use the two smaller CSV files in both SQL and Neo4j. I did this because otherwise I would have been stuck with the assignment. 

#### 3: Construct queries in SQL and in Cypher, which find

##### - all persons that a person endorses, i.e., endorsements of depth one.

SQL - SELECT name, node_id FROM nodes WHERE node_id IN "
                + "(SELECT endorses FROM edges WHERE id IN "
                + "(SELECT node_id FROM nodes WHERE name = 'Odessa Satmary'))

Cypher - MATCH (:Person {name: '${name}'})-[:ENDORSES]->(other) RETURN distinct other

##### - all persons that are endorsed by endorsed persons of a person, i.e., endorsements of depth two.

SQL - SELECT name, node_id FROM nodes WHERE node_id IN \n"
                + "(SELECT endorses FROM edges WHERE id IN\n"
                + "(SELECT endorses FROM edges WHERE id IN \n"
                + "(SELECT node_id FROM nodes WHERE name = 'Odessa Satmary')))

Cypher - MATCH (:Person {name: '${name}' })-[:ENDORSES]->()-[:ENDORSES]->(other_other) RETURN distinct other_other

##### - endorsements of depth three.

SQL - SELECT name, node_id FROM nodes WHERE node_id IN \n"
                + "(SELECT endorses FROM edges WHERE id IN\n"
                + "(SELECT endorses FROM edges WHERE id IN \n"
                + "(SELECT endorses FROM edges WHERE id IN \n"
                + "(SELECT node_id FROM nodes WHERE name = 'Odessa Satmary'))))

Cypher - MATCH (:Person {name: '${name}' })-[:ENDORSES]->()-[:ENDORSES]->()-[:ENDORSES]->(other) RETURN distinct other

##### - endorsements of depth four.

SQL - SELECT name, node_id FROM nodes WHERE node_id IN \n"
                + "(SELECT endorses FROM edges WHERE id IN\n"
                + "(SELECT endorses FROM edges WHERE id IN \n"
                + "(SELECT endorses FROM edges WHERE id IN \n"
                + "(SELECT endorses FROM edges WHERE id IN \n"
                + "(SELECT node_id FROM nodes WHERE name = 'Odessa Satmary')))))

Cypher - MATCH (:Person {name: '${name}' })-[:ENDORSES]->()-[:ENDORSES]->()-[:ENDORSES]->()-[:ENDORSES]->(other) RETURN distinct other

##### - endorsements of depth five.

SQL - SELECT name, node_id FROM nodes WHERE node_id IN \n"
                + "(SELECT endorses FROM edges WHERE id IN\n"
                + "(SELECT endorses FROM edges WHERE id IN \n"
                + "(SELECT endorses FROM edges WHERE id IN \n"
                + "(SELECT endorses FROM edges WHERE id IN \n"
                + "(SELECT endorses FROM edges WHERE id IN \n"
                + "(SELECT node_id FROM nodes WHERE name = 'Odessa Satmary'))))))

Cypher - MATCH (:Person {name: '${name}' })-[:ENDORSES]->()-[:ENDORSES]->()-[:ENDORSES]->()-[:ENDORSES]->()-[:ENDORSES]->(other) RETURN distinct other

#### 4: Write a program in a programming language of your choice, such as Java, C#, etc., where the program executes the above queries for twenty random nodes against the two respective databases. That is, you run each query on the same twenty random nodes.

- I have choosen to write my program in Java. See program within github.  

#### 5: Extend your program, so that it measures the average and median execution times of each query. That is, you run a benchmark for the two databases.

- See program within github. 

#### 6: You collect your measurement results and present them with an evaluation of your experiment in a Markdown file in a repository on Github. That is, you hand in this assignment via Github.

Because of using the smaller CSV files i have used the "Table Data Import Wizard" within MySQL Workbench to import the CSV files. This theoretically would have been possible if I were to use the larger files but it would most likely take up to 6-12 hours. If I were to use the larger file I would have used the SQL-query decribed on the hand-in paper. 

I have imported the CSV files to Neo4j using the "LOAD CSV" command within Neo4J. 

My Java project is created within NetBeans. It is a maven project so it can be run on any system. 

#### Execution time

##### --- SQL ---
- Endorses 1 - 27645887
- Endorses 2 - 6798992
- Endorses 3 - 13807487
- Endorses 4 - 54740417
- Endorses 5 - 317840007

--- NEO ---
- Endorses 1 - 53151844
- Endorses 2 - 26416422
- Endorses 3 - 28618175
- Endorses 4 - 25014596
- Endorses 5 - 22651784

#### Conclusion

The above execution time does not tell the whole story. SQL is great when the database is smaller and not very complex, but when analysing big amounts of data SQL is not the way to go. My results vary a lot, this is because I have been using as explained above the smaller datasets. If I would have been using the bigger datasets Neo4j would have been a lot faster than SQL because it better at working with nodes and it is a much better tool for analytics. I hope that me attemting to solve the assignment using the smaller datasets proves that I have tried. 
