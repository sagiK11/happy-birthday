package sagikor.android.happybirthday.model;


public class Baby {
    private String name;
    private String date;
    private String imagePath;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return date;
    }

    public void setBirthday(String date) {
        this.date = date;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void resetName(){
        this.name = "";
    }

    @Override
    public String toString() {
        return "\nname: " + this.getName()
                + "\nbirthday: " + this.getBirthday() + "\n";
    }
}
