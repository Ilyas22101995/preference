import java.util.Random;


// класс - игровая карта
public class PlayingCard {
    private String name;
    private String maste;
    private String age;
    private int price;
    private boolean flag;

    public PlayingCard(String name, String maste, String age, int price, boolean flag){
        this.name = name;
        this.maste = maste;
        this.age = age;
        this.price = price;
        this.flag = flag;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getMaste() {
        return maste;
    }
    public void setMaste(String maste) {
        this.maste = maste;
    }
    public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
    }
    public boolean isFlag() {
        return flag;
    }
    public void setFlag(boolean flag) {
        this.flag = flag;
    }
    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }

    //worms - черви
    //peaks - пики
    //bubbles - бубби
    //bartisms - крести
    //jack - валет
    //ace - туз


    @Override
    public String toString() {
        return "PlayingCard{" +
                "name='" + name + '\'' +
                ", maste='" + maste + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
