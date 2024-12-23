import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class ClientService implements CRUDService<Client> {
    private Connection connection;

    // Constructor for initializing database connection
    public ClientService(Connection connection) {
        this.connection = connection;
    }

    @Override
public void create(Client client) {
    String sql = "INSERT INTO clients (name, contact, email, event_location, status, event_time, cost) VALUES (?, ?, ?, ?, ?, ?, ?)";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setString(1, client.getName());
        statement.setString(2, client.getContact());
        statement.setString(3, client.getEmail());
        statement.setString(4, client.getEventLocation());
        statement.setString(5, client.getStatus());

        // Periksa jika eventTime adalah null sebelum mengaksesnya
        if (client.getEventTime() != null) {
            statement.setDate(6, new java.sql.Date(client.getEventTime().getTime()));
        } else {
            // Jika eventTime null, set null ke parameter SQL
            statement.setNull(6, Types.DATE);
        }

        statement.setDouble(7, client.getCost());
        statement.executeUpdate();
        System.out.println("Client created successfully.");
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    @Override
    public List<Client> read() {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM clients";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Client client = new Client(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("contact"),
                        resultSet.getString("email"),
                        resultSet.getString("event_location"),
                        resultSet.getString("status"),
                        resultSet.getDate("event_time"),
                        resultSet.getDouble("cost")
                );
                clients.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }

    @Override
    public void update(int id, Client client) {
        String sql = "UPDATE clients SET name = ?, contact = ?, email = ?, event_location = ?, status = ?, event_time = ?, cost = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, client.getName());
            statement.setString(2, client.getContact());
            statement.setString(3, client.getEmail());
            statement.setString(4, client.getEventLocation());
            statement.setString(5, client.getStatus());
            statement.setDate(6, new java.sql.Date(client.getEventTime().getTime()));
            statement.setDouble(7, client.getCost());
            statement.setInt(8, id);
            statement.executeUpdate();
            System.out.println("Client updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM clients WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("Client deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}