import java.util.ArrayList;
import java.util.List;


// класс - игрок
public class Player {
    private String name;
    private int countCard;
    private int countPoints;
    private List<PlayingCard> cards;

    public Player(String name, int countCard, int countPoints){
        cards = new ArrayList<PlayingCard>();
        this.name = name;
        this.countCard = countCard;
        this.countPoints = countPoints;
    }

    public void addedCard(PlayingCard card){
        cards.add(card);
    }

    public String getName() {
        return name;
    }

    public void plusPoints(){++countPoints;}
    public void minusPoints(){--countPoints;}

    public void setName(String name) {
        this.name = name;
    }
    public int getCountCard() {
        return countCard;
    }
    public void setCountCard(int countCard) {
        this.countCard = countCard;
    }
    public int getCountPoints() {
        return countPoints;
    }
    public void setCountPoints(int countPoints) {
        this.countPoints = countPoints;
    }
    public List<PlayingCard> getCards() {
        return cards;
    }
    public void setCards(List cards) {
        this.cards = cards;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", countCard=" + countCard +
                ", countPoints=" + countPoints +
                ", cards=" + cards +
                '}';
    }
}

