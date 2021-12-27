import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Person{

    private final String name;
    private final String surname;
    private int vkId;
    private Gender gender;
    private String city;
    private String birthdate;
    private String photo;

    public Person(String name, String surname) {
        this.name = name;
        this.surname = surname;
        this.city = "None";
        this.birthdate = null;
        this.photo = "None";
        this.vkId = -1;
        gender = Gender.None;
    }
    
    enum Gender {
    Male,
    Female,
    None };

    @Override
    public String toString() {
        return vkId + ": Имя - " + name + ", Фамилия - " + surname + ", Дата рождения - " + birthdate + ", Город - " +city + ", Photo - "+ photo;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public String getCity() {
        return city;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public int getVkId() {
        return vkId;
    }

    public void setVkId(int vkId) {
        this.vkId = vkId;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setBirthdate(String birthdate) {
        var validator = new SimpleDateFormat("MM.dd.yyyy");
        try {
            var date = validator.parse(birthdate);
            validator.applyPattern("yyyy-MM-dd");
            this.birthdate = validator.format(date);
        } catch (ParseException e) {
            this.birthdate = null;
        }
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
