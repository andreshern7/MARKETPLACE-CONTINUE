package andres.learning.marketplace.user.model;

public class ResponseClient {

    private int id;
    private String name;
    private String lastName;
    private String address;
    private String email;
    private String username;
    private String password;

    public ResponseClient() {

    }

    /*public ResponseUser(Client client) {
        this.id = user.getId();
        this.name = user.getName();
        this.lastName = user.getLastName();
        this.userName = user.getUserName();
        this.password = user.getPassword();
        this.country = user.getCountry();
        this.technology = user.getTechnology();
    }*/

    public ResponseClient(Client client) {
        this.name = client.getName();
        this.lastName = client.getLastName();
        this.address = client.getAddress();
        this.email = client.getEmail();
        this.username = client.getUsername();

    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "ResponseUser{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                '}';
    }

}

