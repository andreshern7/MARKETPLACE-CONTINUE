package andres.learning.marketplace.user.model;

public class User {
    private int id;
    private String name;
    private String lastName;
    private String address;
    private String email;
    private String username;
    private String password;

    public User() {

    }

    /**
     * This constructor is used when the app will do a:
     *      *Read
     *      *Update
     *      *Delete
     * Because to this operations is necessarily have the id of the User
     */

    public User(int id, String name, String lastName, String address, String email, String userName, String password) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.username = userName;
        this.password = password;
    }


    /**
     * This constructor is used when the app will do a:
     *      *Create
     * Because to this operations is not necessarily have the id of the User
     */

    public User(String name, String lastName, String address, String email, String userName, String password) {
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.username = userName;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String completeDataUser() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String dataUser() {
        return "User{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                '}';
    }


}
