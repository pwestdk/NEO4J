
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;

public class Quries {

    public static void main(String[] args) throws Exception {

        // SQL endorses 1
        long SQL_endorses1_startTime = System.nanoTime();
        for (int i = 1; i <= 20; i++) {
            SQLendorses1();
        }
        long SQL_endorses1_endTime = System.nanoTime();
        long SQL_endorses1_duration = ((SQL_endorses1_endTime - SQL_endorses1_startTime) / 20);

        // SQL endorses 2
        long SQL_endorses2_startTime = System.nanoTime();
        for (int i = 1; i <= 20; i++) {
            SQLendorses2();
        }
        long SQL_endorses2_endTime = System.nanoTime();
        long SQL_endorses2_duration = ((SQL_endorses2_endTime - SQL_endorses2_startTime) / 20);

        // SQL endorses 3
        long SQL_endorses3_startTime = System.nanoTime();
        for (int i = 1; i <= 20; i++) {
            SQLendorses3();
        }
        long SQL_endorses3_endTime = System.nanoTime();
        long SQL_endorses3_duration = ((SQL_endorses3_endTime - SQL_endorses3_startTime) / 20);

        // SQL endorses 4
        long SQL_endorses4_startTime = System.nanoTime();
        for (int i = 1; i <= 20; i++) {
            SQLendorses4();
        }
        long SQL_endorses4_endTime = System.nanoTime();
        long SQL_endorses4_duration = ((SQL_endorses4_endTime - SQL_endorses4_startTime) / 20);

        // SQL endorses 5
        long SQL_endorses5_startTime = System.nanoTime();
        for (int i = 1; i <= 20; i++) {
            SQLendorses5();
        }
        long SQL_endorses5_endTime = System.nanoTime();
        long SQL_endorses5_duration = ((SQL_endorses5_endTime - SQL_endorses5_startTime) / 20);

        // NEO endorses 1
        long NEO_endorses1_startTime = System.nanoTime();
        for (int i = 1; i <= 20; i++) {
            NEOendorses("MATCH (:Person {name: '${name}'})-[:ENDORSES]->(other) RETURN distinct other");
        }
        long NEO_endorses1_endTime = System.nanoTime();
        long NEO_endorses1_duration = ((NEO_endorses1_endTime - NEO_endorses1_startTime) / 20);
        
        // NEO endorses 2
        long NEO_endorses2_startTime = System.nanoTime();
        for (int i = 1; i <= 20; i++) {
            NEOendorses("MATCH (:Person {name: '${name}' })-[:ENDORSES]->()-[:ENDORSES]->(other_other) RETURN distinct other_other");
        }
        long NEO_endorses2_endTime = System.nanoTime();
        long NEO_endorses2_duration = ((NEO_endorses2_endTime - NEO_endorses2_startTime) / 20);
        
        // NEO endorses 3
        long NEO_endorses3_startTime = System.nanoTime();
        for (int i = 1; i <= 20; i++) {
            NEOendorses("MATCH (:Person {name: '${name}' })-[:ENDORSES]->()-[:ENDORSES]->()-[:ENDORSES]->(other) RETURN distinct other");
        }
        long NEO_endorses3_endTime = System.nanoTime();
        long NEO_endorses3_duration = ((NEO_endorses3_endTime - NEO_endorses3_startTime) / 20);
        
        // NEO endorses 4
        long NEO_endorses4_startTime = System.nanoTime();
        for (int i = 1; i <= 20; i++) {
            NEOendorses("MATCH (:Person {name: '${name}' })-[:ENDORSES]->()-[:ENDORSES]->()-[:ENDORSES]->()-[:ENDORSES]->(other) RETURN distinct other");
        }
        long NEO_endorses4_endTime = System.nanoTime();
        long NEO_endorses4_duration = ((NEO_endorses4_endTime - NEO_endorses4_startTime) / 20);
        
        // NEO endorses 5
        long NEO_endorses5_startTime = System.nanoTime();
        for (int i = 1; i <= 20; i++) {
            NEOendorses("MATCH (:Person {name: '${name}' })-[:ENDORSES]->()-[:ENDORSES]->()-[:ENDORSES]->()-[:ENDORSES]->()-[:ENDORSES]->(other) RETURN distinct other");
        }
        long NEO_endorses5_endTime = System.nanoTime();
        long NEO_endorses5_duration = ((NEO_endorses5_endTime - NEO_endorses5_startTime) / 20);
        
        System.out.println("--- SQL ---");
        System.out.println("Endorses 1 - " + SQL_endorses1_duration);
        System.out.println("Endorses 2 - " + SQL_endorses2_duration);
        System.out.println("Endorses 3 - " + SQL_endorses3_duration);
        System.out.println("Endorses 4 - " + SQL_endorses4_duration);
        System.out.println("Endorses 5 - " + SQL_endorses5_duration);
        
        System.out.println();
        
        System.out.println("--- NEO ---");
        System.out.println("Endorses 1 - " + NEO_endorses1_duration);
        System.out.println("Endorses 2 - " + NEO_endorses2_duration);
        System.out.println("Endorses 3 - " + NEO_endorses3_duration);
        System.out.println("Endorses 4 - " + NEO_endorses4_duration);
        System.out.println("Endorses 5 - " + NEO_endorses5_duration);

    }

    public static void SQLendorses1() throws Exception {
        // Establishing connection to DB
        Connection connection = new DBConnector().getConnection();
        Statement stmt = connection.createStatement();

        // Query
        String query = "SELECT name, node_id FROM nodes WHERE node_id IN "
                + "(SELECT endorses FROM edges WHERE id IN "
                + "(SELECT node_id FROM nodes WHERE name = 'Odessa Satmary'))";
        ResultSet res = stmt.executeQuery(query);
    }

    public static void SQLendorses2() throws Exception {
        // Establishing connection to DB
        Connection connection = new DBConnector().getConnection();
        Statement stmt = connection.createStatement();

        // Query
        String query = "SELECT name, node_id FROM nodes WHERE node_id IN \n"
                + "(SELECT endorses FROM edges WHERE id IN\n"
                + "(SELECT endorses FROM edges WHERE id IN \n"
                + "(SELECT node_id FROM nodes WHERE name = 'Odessa Satmary'))); ";
        ResultSet res = stmt.executeQuery(query);
    }

    public static void SQLendorses3() throws Exception {
        // Establishing connection to DB
        Connection connection = new DBConnector().getConnection();
        Statement stmt = connection.createStatement();

        // Query
        String query = "SELECT name, node_id FROM nodes WHERE node_id IN \n"
                + "(SELECT endorses FROM edges WHERE id IN\n"
                + "(SELECT endorses FROM edges WHERE id IN \n"
                + "(SELECT endorses FROM edges WHERE id IN \n"
                + "(SELECT node_id FROM nodes WHERE name = 'Odessa Satmary'))));";
        ResultSet res = stmt.executeQuery(query);
    }

    public static void SQLendorses4() throws Exception {
        // Establishing connection to DB
        Connection connection = new DBConnector().getConnection();
        Statement stmt = connection.createStatement();

        // Query
        String query = "SELECT name, node_id FROM nodes WHERE node_id IN \n"
                + "(SELECT endorses FROM edges WHERE id IN\n"
                + "(SELECT endorses FROM edges WHERE id IN \n"
                + "(SELECT endorses FROM edges WHERE id IN \n"
                + "(SELECT endorses FROM edges WHERE id IN \n"
                + "(SELECT node_id FROM nodes WHERE name = 'Odessa Satmary')))));";
        ResultSet res = stmt.executeQuery(query);
    }

    public static void SQLendorses5() throws Exception {
        // Establishing connection to DB
        Connection connection = new DBConnector().getConnection();
        Statement stmt = connection.createStatement();

        // Query
        String query = "SELECT name, node_id FROM nodes WHERE node_id IN \n"
                + "(SELECT endorses FROM edges WHERE id IN\n"
                + "(SELECT endorses FROM edges WHERE id IN \n"
                + "(SELECT endorses FROM edges WHERE id IN \n"
                + "(SELECT endorses FROM edges WHERE id IN \n"
                + "(SELECT endorses FROM edges WHERE id IN \n"
                + "(SELECT node_id FROM nodes WHERE name = 'Odessa Satmary')))))); ";
        ResultSet res = stmt.executeQuery(query);
    }

    public static void NEOendorses(String query) {
        Driver driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "class"));
        Session session = driver.session();
        StatementResult result = session.run(query);
        session.close();
        driver.close();
    }
}
