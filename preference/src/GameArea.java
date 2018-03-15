import java.io.IOException;
import java.util.*;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.stream.Collectors;

// класс - игра
public class GameArea {

    private List<PlayingCard> cards;
    private List<Player> players;
    private Map<Integer,Object> history = new HashMap<>();
    private Map<Player,PlayingCard> table;
    private static Logger log = Logger.getLogger(GameArea.class.getName());

    // класс - игра
    public GameArea (){
        try {
            LogManager.getLogManager().readConfiguration(
                    GameArea.class.getClassLoader().getResourceAsStream("logging.properties"));
        } catch (IOException e) {
            System.err.println("Could not setup logger configuration: " + e.toString());
        }
        initialize();
    }


    public void initialize(){
        log.info("START GAME\n");
        table = new HashMap<>();
        cardCreator();
        playersCreator();
        razdacha();
    }

    // создание объектов карт и занесение их в колоду
    public void cardCreator(){
        PlayingCard sevenWorms = new PlayingCard("sevenWorms", "worms", "7", 0, true);
        PlayingCard sevenPeaks = new PlayingCard("sevenPeaks", "peaks", "7", 0, true);
        PlayingCard sevenBubbles = new PlayingCard("sevenBubbles", "bubbles", "7", 0, true);
        PlayingCard sevenBartisms = new PlayingCard("sevenBartisms", "bartisms", "7", 0, true);
        PlayingCard eightWorms = new PlayingCard("eightWorms", "worms", "8", 1, true);
        PlayingCard eightPeaks = new PlayingCard("eightPeaks", "peaks", "8", 1, true);
        PlayingCard eightBubbles = new PlayingCard("eightBubbles", "bubbles", "8", 1, true);
        PlayingCard eightBartisms = new PlayingCard("eightBartisms", "bartisms", "8", 1, true);
        PlayingCard nineWorms = new PlayingCard("nineWorms", "worms", "9", 2, true);
        PlayingCard ninePeaks = new PlayingCard("ninePeaks", "peaks", "9", 2, true);
        PlayingCard nineBubbles = new PlayingCard("nineBubbles", "bubbles", "9", 2, true);
        PlayingCard nineBartisms = new PlayingCard("nineBartisms", "bartisms", "9", 2, true);
        PlayingCard tenWorms = new PlayingCard("tenWorms", "worms", "10", 3, true);
        PlayingCard tenPeaks = new PlayingCard("tenPeaks", "peaks", "10", 3, true);
        PlayingCard tenBubbles = new PlayingCard("tenBubbles", "bubbles", "10", 3, true);
        PlayingCard tenBartisms = new PlayingCard("tenBartisms", "bartisms", "10", 3, true);
        PlayingCard jackWorms = new PlayingCard("jackWorms", "worms", "jack", 4, true);
        PlayingCard jackPeaks = new PlayingCard("jackPeaks", "peaks", "jack", 4, true);
        PlayingCard jackBubbles = new PlayingCard("jackBubbles", "bubbles", "jack", 4, true);
        PlayingCard jackBartisms = new PlayingCard("jackBartisms", "bartisms", "jack", 4, true);
        PlayingCard ladyWorms = new PlayingCard("ladyWorms", "worms", "lady", 5, true);
        PlayingCard ladyPeaks = new PlayingCard("ladyPeaks", "peaks", "lady", 5, true);
        PlayingCard ladyBubbles = new PlayingCard("ladyBubbles", "bubbles", "lady", 5, true);
        PlayingCard ladyBartisms = new PlayingCard("ladyBartisms", "bartisms", "lady", 5, true);
        PlayingCard kingWorms = new PlayingCard("kingWorms", "worms", "king", 6, true);
        PlayingCard kingPeaks = new PlayingCard("kingPeaks", "peaks", "king", 6, true);
        PlayingCard kingBubbles = new PlayingCard("kingBubbles", "bubbles", "king", 6, true);
        PlayingCard kingBartisms = new PlayingCard("kingBartisms", "bartisms", "king", 6, true);
        PlayingCard aceWorms = new PlayingCard("aceWorms", "worms", "ace", 7, true);
        PlayingCard acePeaks = new PlayingCard("acePeaks", "peaks", "ace", 7, true);
        PlayingCard aceBubbles = new PlayingCard("aceBubbles", "bubbles", "ace", 7, true);
        PlayingCard aceBartisms = new PlayingCard("aceBartisms", "bartisms", "ace", 7, true);
        cards  = new ArrayList<PlayingCard>(
                Arrays.asList(
                        sevenWorms, sevenPeaks, sevenBubbles, sevenBartisms, eightWorms, eightPeaks, eightBubbles, eightBartisms,
                        nineWorms, ninePeaks, nineBubbles, nineBartisms, tenWorms, tenPeaks, tenBubbles, tenBartisms, jackWorms, jackPeaks, jackBubbles,
                        jackBartisms, ladyWorms, ladyPeaks, ladyBubbles, ladyBartisms, kingWorms, kingPeaks, kingBubbles, kingBartisms, aceWorms,
                        acePeaks, aceBubbles, aceBartisms
                )
        );
    }
    // cоздание объектов игроков и занесение их в игральный стол
    public void playersCreator() {
        Player player1 = new Player("Alex", 10, 10);
        Player player2 = new Player("Niko", 10, 10);
        Player player3 = new Player("Liza", 10, 10);
        players = new ArrayList<Player>(Arrays.asList(player1, player2, player3));
    }
    // метод раздачи карт
    public void razdacha(){
        int range = 30;
        int index = 0;
        log.info("\nStart distribution cards for players");
        for (int i = 0; i<30;i++){
            index = (int) (Math.random() * range--);
            PlayingCard tmp = cards.get(index);
            if (players.get(0).getCards().size()<10){
                players.get(0).addedCard(tmp);
                log.info("\nAdded card: "+tmp.getName()+" for player: "+players.get(0).getName());
            } else
            if (players.get(1).getCards().size()<10) {
                players.get(1).addedCard(tmp);
                log.info("\nAdded card: "+tmp.getName()+" for player: "+players.get(1).getName());
            }
            else
            if (players.get(2).getCards().size()<10) {
                players.get(2).addedCard(tmp);
                log.info("\nAdded card: "+tmp.getName()+" for player: "+players.get(2).getName());
            }
            cards.remove(index);
        }
        log.info("End distribution cards for players\n");
    }
    // метод хода первого игрока
    public void oneGamerToOneGame(){
        int range = players.get(0).getCards().size();
        int index = 0;

        index = (int) (Math.random() * range);
        PlayingCard tmp = players.get(0).getCards().get(index);

        table.put(players.get(0),tmp);
        players.get(0).getCards().remove(tmp);
        log.info("\n"+players.get(0).getName()+" put on the table "+tmp.getName());
    }
    // метод хода второго игрока
    public void twoGamerToOneGame(){
        PlayingCard firstCard = table.get(players.get(0));
        Player twoPlayer = players.get(1);
        List<PlayingCard> masteCard = twoPlayer.getCards().stream().filter(o -> o.getMaste().equalsIgnoreCase(firstCard.getMaste())).collect(Collectors.toList());
        if (!masteCard.isEmpty()) {
            masteCard.stream()
                    .filter(x -> x.getPrice() > firstCard.getPrice())
                    .findFirst()
                    .ifPresent(x -> table.put(twoPlayer, x));
        }
        if (!table.containsKey(twoPlayer)){
            int range = twoPlayer.getCards().size();
            int index = (int) (Math.random() * range);
            table.put(
                    twoPlayer,
                    twoPlayer.getCards().get(index)
            );
        }
        twoPlayer.getCards().remove(table.get(twoPlayer));
        log.info("\n"+twoPlayer.getName()+" put on the table "+table.get(twoPlayer).getName());
        masteCard.clear();
    }
    // метод хода третьего игрока
    public void threeGamerToOneGame() {
        PlayingCard firstCard = table.get(players.get(0));
        Player threePlayer = players.get(2);
        List<PlayingCard> masteCard = threePlayer.getCards().stream().filter(o -> o.getMaste().equalsIgnoreCase(firstCard.getMaste())).collect(Collectors.toList());
        PlayingCard cardThreeGamer = null;
        if (!masteCard.isEmpty()) {
            masteCard.stream()
                    .filter(x -> x.getPrice() > firstCard.getPrice())
                    .findFirst()
                    .ifPresent(x -> table.put(threePlayer, x));
        }
        if (!table.containsKey(threePlayer)){
            int range = threePlayer.getCards().size();
            int index = (int) (Math.random() * range);
            table.put(
                    threePlayer,
                    threePlayer.getCards().get(index)
            );
        }
        log.info("\n"+threePlayer.getName()+" put on the table "+table.get(threePlayer).getName());
        threePlayer.getCards().remove(table.get(threePlayer));
        masteCard.clear();
    }
    // метод выявления победителя
    public void win(){
        int counter = 0;
        PlayingCard first = table.get(players.get(0));
        PlayingCard two = table.get(players.get(1));
        PlayingCard three = table.get(players.get(2));
        PlayingCard winCard = null;

        if (first.getMaste().equals(two.getMaste()) && first.getMaste().equals(three.getMaste())) {
            if (first.getPrice() < two.getPrice() && first.getPrice() < three.getPrice())
                if (two.getPrice() < three.getPrice())
                    winCard = three;
                else
                    winCard = two;
            else if (first.getPrice() > two.getPrice() && first.getPrice() > three.getPrice())
                winCard = first;
            else if (first.getPrice() < two.getPrice() && first.getPrice() > three.getPrice())
                winCard = two;
            else
                winCard = three;
        } else if (first.getMaste().equals(two.getMaste()) && !first.getMaste().equals(three.getMaste())){
            if (first.getPrice()>two.getPrice())
                winCard = first;
            else
                winCard = two;
        } else if (first.getMaste().equals(three.getMaste()) && !first.getMaste().equals(two.getMaste())){
            if (first.getPrice()>three.getPrice())
                winCard = first;
            else
                winCard = three;
        }
        else if (!first.getMaste().equals(three.getMaste()) && !first.getMaste().equals(two.getMaste()))
            winCard = first;

        PlayingCard finalWinCard = winCard;
        table.forEach((player, card)->
                {
                    if (card.equals(finalWinCard)) {
                        player.plusPoints();
                    }
                    else
                        player.minusPoints();
                }
        );
        log.info("\nWinner card: "+winCard.getName());
        String logMsg = "";
        for (Player cur: players)
            logMsg += "\nPlayer: "+cur.getName()+" points: "+cur.getCountPoints()+" count cards: "+cur.getCards().size();
        log.info(logMsg);
        history.put(counter,table);
        table.clear();
        counter++;
    }
    // метод одного розыгрыша
    public void hod(){
        oneGamerToOneGame();
        twoGamerToOneGame();
        threeGamerToOneGame();
        win();
    }
    // метод всей игры
    public void game(){
        for (int i = 0;i<10;i++){
            hod();
            log.info("END "+i+" stake\n");
        }
    }

    public List<PlayingCard> getCards() {
        return cards;
    }
    public List<Player> getPlayers() {
        return players;
    }

    public static void main(String[] args) {
        GameArea a = new GameArea();
        a.game();
        log.info("\nEND GAME");
    }
}
