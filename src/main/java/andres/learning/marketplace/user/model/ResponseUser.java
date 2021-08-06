package andres.learning.marketplace.user.model;

public class ResponseUser {

    private int id;
    private String name;
    private String lastName;
    private String address;
    private String email;
    private String username;
    private String password;

    public ResponseUser() {

    }

    /*public ResponseUser(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.lastName = user.getLastName();
        this.userName = user.getUserName();
        this.password = user.getPassword();
        this.country = user.getCountry();
        this.technology = user.getTechnology();
    }*/

    public ResponseUser(User user) {
        this.name = user.getName();
        this.lastName = user.getLastName();
        this.address = user.getAddress();
        this.email = user.getEmail();
        this.username = user.getUsername();

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

