import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class User {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private String name;
    @DatabaseField
    private String surname;
    @DatabaseField
    private String mail;
    @DatabaseField
    private String phone;
    @DatabaseField
    private String pasword;
    @DatabaseField
    private Role role;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPasword() {
        return pasword;
    }

    public void setPasword(String pasword) {
        this.pasword = pasword;
    }

    User (){}

    User (String name, String surname, String mail, String phone){
        this.name=name;
        this.surname=surname;
        this.mail=mail;
        this.phone=phone;
    }

    User (String name, String surname, String mail, String phone,String password){
        this.name=name;
        this.surname=surname;
        this.mail=mail;
        this.phone=phone;
        this.pasword=password;
    }

    User (String name, String surname, String mail, String phone,String password,Role role){
        this.name=name;
        this.surname=surname;
        this.mail=mail;
        this.phone=phone;
        this.pasword=password;
        this.role=role;
    }

    User (User user){
        this.name=user.name;
        this.surname=user.surname;
        this.phone=user.phone;
        this.mail=user.mail;
        this.pasword=user.pasword;
    }

    public void EqualTo(User user){
        this.name=user.name;
        this.surname=user.surname;
        this.phone=user.phone;
        this.mail=user.mail;
        this.pasword=user.pasword;
    }

    @Override
    public String toString() {
        return "User TOSTRING{" + '\n' +
                "name:" + name + '\n' +
                "surname:" + surname + '\n' +
                "mail:" + mail + '\n' +
                "phone:" + phone + '\n' +
                '}';
    }
}
