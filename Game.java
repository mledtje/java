//Eigentlich unnötig weil alles in einem File steht
package bacs;
//Ne Menge Imports
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;


//Die eigentliche Klasse die hier alleine stehen sollte lul ._.
public class Game {
    //Fenster, Panels, Buttons, TextAreas, Fonts und Datentypen deklarieren
    JFrame window, guide;
    Container con, guidecon;
    //playerPanel soll später Leben und Items anzeigen
    JPanel titleNamePanel, startPanel, endPanel, menuPanel, mainTextPanel, choicePanel,playerPanel,
            continuePanel, loadPanel, newGamePanel, controlsPanel, guidePanel, guideTextPanel, pauseMenuPanel,
            pausePanel, resumePanel, savePanel, menuButtonPanel ;
    JLabel titleNameLabel;
    Font titleFont = new Font("Times New Roman", Font.PLAIN, 50);
    Font normalFont = new Font("Times New Roman", Font.PLAIN, 20);
    JButton startButton, endButton, continueButton, loadButton, newGameButton, controlsButton,
            guideButton, choice1, choice2, choice3, choice4, pauseButton, resumeButton, saveButton, menuButton;
    JTextArea mainTextArea;
    JTextArea guideTextArea;
    String position;
    Boolean foundGameSave;

    //Klassen hier initialisieren weil wir zu blöd sind das in separaten Klassen zu machen
    TitleScreenHandler tsHandler = new TitleScreenHandler();
    MenuScreenHandler msHandler = new MenuScreenHandler();
    ChoiceHandler choiceHandler = new ChoiceHandler();
    ContinueHandler contHandler = new ContinueHandler();
    //Noch nicht implementiert deswegen auch Button ausgegraut
    LoadHandler loadHandler = new LoadHandler();
    NewGameHandler ngHandler = new NewGameHandler();
    //Noch nicht implementiert deswegen auch Button ausgegraut
    ControlsHandler ctrlsHandler = new ControlsHandler();
    GuideHandler guideHandler = new GuideHandler();
    EndHandler endHandler = new EndHandler();
    PauseScreenHandler pauseHandler = new PauseScreenHandler();
    ResumeHandler resumeHandler = new ResumeHandler();
    SaveHandler saveHandler = new SaveHandler();

    //Main Funktion, many things happening here..
    public static void main(String[] args){
        new Game();
    }
    public void readSaveFile() throws FileNotFoundException { //Lade-/Leseroutine
        //vorerst fixer Pfad statt temporär, da keine .exe
        File file = new File("C:\\Users\\mledtje\\Desktop\\save.txt");
        Scanner scan = new Scanner(file);
        String fileContent = "";
        while(scan.hasNextLine()){ // Später für den Button "Laden" benutzen und umbauen
            fileContent = fileContent.concat(scan.nextLine());
        }
        setPosition(fileContent);
        System.out.println(position);
        //auf DB erweitern: try connect, wenn nicht lokal laden
    }
    public void saveSaveFile() throws IOException { //Speicherroutine
        //vorerst fixer Pfad statt temporär, da keine .exe
        FileWriter writer = new FileWriter("C:\\Users\\mledtje\\Desktop\\save.txt");
        writer.write(position);
        writer.close();
        //auf DB erweitern: try connect, wenn nicht lokal speichern
    }
    public Game(){
        window = new JFrame(); //Hauptfenster
        window.setSize(1024, 768);//Pixel, beste Auflösung natürlich
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Standardbehaviour einstellen
        window.getContentPane().setBackground(Color.BLACK); //Hier vielleicht lieber ein Bild.. später
        window.setLayout(null);
        con = window.getContentPane(); //container

        titleNamePanel = new JPanel(); //Unser Spieltitel für Hauptmenü und Startbildschirm
        titleNamePanel.setBounds(187,100,650,150);
        titleNamePanel.setBackground(Color.BLACK);
        titleNameLabel = new JLabel("Becoming a computer scientist");
        titleNameLabel.setForeground(Color.WHITE);
        titleNameLabel.setFont(titleFont);

        startPanel = new JPanel();
        startPanel.setBounds(460,300,100,100);
        startPanel.setBackground(Color.BLACK);


        startButton = new JButton("Start");
        startButton.setBackground(Color.BLACK);
        startButton.setForeground(Color.WHITE);
        startButton.setFont(normalFont);
        startButton.addActionListener(tsHandler);//actionlistener
        startButton.setFocusPainted(false);

        titleNamePanel.add(titleNameLabel);
        startPanel.add(startButton);

        con.add(titleNamePanel);
        con.add(startPanel);
        window.setVisible(true);

    }

    public String getPosition() { //unnötig
        return position;
    }
    public void setPosition(String position) { //um den Spielstand für spätere Abfrage zuzuweisen (Aus Datei/DB)
        this.position = position;
    }


    public void createMenuScreen() {
        startPanel.setVisible(false); //Startbutton ausblenden

        menuPanel = new JPanel(); //Hier werden alle Buttons/Panels eingefügt
        menuPanel.setBounds(380,250,300,250);
        menuPanel.setBackground(Color.BLACK);
        menuPanel.setLayout(new GridLayout(6,1));


        continuePanel = new JPanel();
        continuePanel.setBackground(Color.BLACK);

        continueButton = new JButton("Fortsetzen");
        continueButton.setBackground(Color.BLACK);
        if(foundGameSave==true){ //check nach vorhandenem Speicherstand
            continueButton.setForeground(Color.WHITE);
            continueButton.addActionListener(contHandler);
        }
        continueButton.setFont(normalFont);
        continueButton.setFocusPainted(false);

        loadPanel = new JPanel();
        loadPanel.setBackground(Color.BLACK);

        loadButton = new JButton("Laden");
        loadButton.setBackground(Color.BLACK);
        //wenn implementiert
        //if possible
        //loadButton.setForeground(Color.WHITE);
        loadButton.setFont(normalFont);
        //loadButton.addActionListener(tsHandler);//actionlistener
        loadButton.setFocusPainted(false);

        newGamePanel = new JPanel();
        newGamePanel.setBackground(Color.BLACK);

        newGameButton = new JButton("Neues Spiel");
        newGameButton.setBackground(Color.BLACK);
        newGameButton.setForeground(Color.WHITE);
        newGameButton.setFont(normalFont);
        newGameButton.addActionListener(ngHandler);//actionlistener
        newGameButton.setFocusPainted(false);

        controlsPanel = new JPanel();
        controlsPanel.setBackground(Color.BLACK);

        controlsButton = new JButton("Steuerung");
        controlsButton.setBackground(Color.BLACK);
        //wenn implementiert
        //controlsButton.setForeground(Color.WHITE);
        controlsButton.setFont(normalFont);
        //controlsButton.addActionListener(ctrlsHandler);//actionlistener
        controlsButton.setFocusPainted(false);

        guidePanel = new JPanel();
        guidePanel.setBackground(Color.BLACK);

        guideButton = new JButton("Anleitung");
        guideButton.setBackground(Color.BLACK);
        guideButton.setForeground(Color.WHITE);
        guideButton.setFont(normalFont);
        guideButton.addActionListener(guideHandler);//actionlistener
        guideButton.setFocusPainted(false);

        endPanel = new JPanel();
        endPanel.setBackground(Color.BLACK);

        endButton = new JButton("Spiel Verlassen");
        endButton.setBackground(Color.BLACK);
        endButton.setForeground(Color.WHITE);
        endButton.setFont(normalFont);
        endButton.addActionListener(endHandler);//actionlistener
        endButton.setFocusPainted(false);

        continuePanel.add(continueButton);
        loadPanel.add(loadButton);
        newGamePanel.add(newGameButton);
        controlsPanel.add(controlsButton);
        guidePanel.add(guideButton);
        endPanel.add(endButton);

        menuPanel.add(continuePanel);
        menuPanel.add(loadPanel);
        menuPanel.add(newGamePanel);
        menuPanel.add(controlsPanel);
        menuPanel.add(guidePanel);
        menuPanel.add(endPanel);


        con.add(menuPanel);
    }
    //Spielerklärung erstellen
    public void showGuide(){
        //
        guide = new JFrame();
        guide.setSize(500, 500);//Pixel
        guide.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        guide.getContentPane().setBackground(Color.BLACK);
        guide.setLayout(null);
        guidecon = guide.getContentPane(); //container

        guideTextPanel = new JPanel();
        guideTextPanel.setBounds(100, 100, 400, 250);
        guideTextPanel.setBackground(Color.black);

        guideTextArea = new JTextArea("Wie man spielt:\n" +
                "Wähle eine Dialogoption und versuche es\n" +
                "bis zur Abschlussprüfung zu schaffen.");
        guideTextArea.setBounds(100, 100, 400, 250);
        guideTextArea.setBackground(Color.BLACK);
        guideTextArea.setForeground(Color.WHITE);
        guideTextArea.setFont(normalFont);
        guideTextArea.setLineWrap(true);
        guideTextArea.setWrapStyleWord(true);
        guideTextArea.setEditable(false);
        guideTextPanel.add(guideTextArea);

        guidecon.add(guideTextPanel);
        guide.setVisible(true);
    }
    //Verbindung mit MDB herstellen
    public void connect(){}
    //Spielbildschirm erstellen
    public void createGameScreen(){
        continuePanel.setVisible(false);
        loadPanel.setVisible(false);
        newGamePanel.setVisible(false);
        controlsPanel.setVisible(false);
        guidePanel.setVisible(false);
        endPanel.setVisible(false);
        menuPanel.setVisible(false);
        //Tagesscreen implementieren
        //titleNameLabel.setText("Tag 1");
        //wait implementieren
        titleNamePanel.setVisible(false);


        mainTextPanel = new JPanel();
        mainTextPanel.setBounds(100,100,600,300);
        mainTextPanel.setBackground(Color.BLACK);
        mainTextArea = new JTextArea("Platzhalter Text");
        mainTextArea.setBounds(100, 100, 600, 300);
        mainTextArea.setBackground(Color.black);
        mainTextArea.setForeground(Color.white);
        mainTextArea.setFont(normalFont);
        mainTextArea.setLineWrap(true);
        mainTextArea.setWrapStyleWord(true);
        mainTextArea.setEditable(false);
        mainTextPanel.add(mainTextArea);
        con.add(mainTextPanel);

        pausePanel = new JPanel();
        pausePanel.setBounds(800,50,100,100);
        pausePanel.setBackground(Color.BLACK);

        pauseButton = new JButton("Pause");
        pauseButton.setBackground(Color.BLACK);
        pauseButton.setForeground(Color.WHITE);
        pauseButton.setFont(normalFont);
        pauseButton.addActionListener(pauseHandler);
        pauseButton.setFocusPainted(false);
        pausePanel.add(pauseButton);
        con.add(pausePanel);


        choicePanel = new JPanel();
        choicePanel.setBounds(100,400,800,200);
        choicePanel.setBackground(Color.BLACK);
        choicePanel.setLayout(new GridLayout(2,2));
        con.add(choicePanel);
        choice1 = new JButton("Choice 1");
        choice1.setBackground(Color.BLACK);
        choice1.setForeground(Color.WHITE);
        choice1.setFont(normalFont);
        choice1.setFocusPainted(false);
        choice1.addActionListener(choiceHandler);
        choice1.setActionCommand("c1");
        choicePanel.add(choice1);
        choice2 = new JButton("Choice 2");
        choice2.setBackground(Color.BLACK);
        choice2.setForeground(Color.WHITE);
        choice2.setFont(normalFont);
        choice2.setFocusPainted(false);
        choice2.addActionListener(choiceHandler);
        choice2.setActionCommand("c2");
        choicePanel.add(choice2);
        choice3 = new JButton("Choice 3");
        choice3.setBackground(Color.BLACK);
        choice3.setForeground(Color.WHITE);
        choice3.setFont(normalFont);
        choice3.setFocusPainted(false);
        choice3.addActionListener(choiceHandler);
        choice3.setActionCommand("c3");
        choicePanel.add(choice3);
        choice4 = new JButton("Choice 4");
        choice4.setBackground(Color.BLACK);
        choice4.setForeground(Color.WHITE);
        choice4.setFont(normalFont);
        choice4.setFocusPainted(false);
        choice4.addActionListener(choiceHandler);
        choice4.setActionCommand("c4");
        choicePanel.add(choice4);


    }
    //public void startGame(){}
    //Pausenmenü erstellen
    public void pauseMenu(){
        pausePanel.setVisible(false);
        choicePanel.setVisible(false);
        mainTextPanel.setVisible(false);
        pauseMenuPanel = new JPanel();
        pauseMenuPanel.setBounds(380,250,200,250);
        pauseMenuPanel.setBackground(Color.BLACK);
        pauseMenuPanel.setLayout(new GridLayout(4,1));

        resumePanel = new JPanel();
        resumePanel.setBackground(Color.BLACK);
        resumeButton = new JButton("Fortsetzen");
        resumeButton.setBackground(Color.BLACK);
        resumeButton.setForeground(Color.WHITE);
        resumeButton.setFont(normalFont);
        resumeButton.setFocusPainted(false);
        resumeButton.addActionListener(resumeHandler);

        resumePanel.add(resumeButton);

        savePanel = new JPanel();
        savePanel.setBackground(Color.BLACK);
        saveButton = new JButton("Speichern");
        saveButton.setBackground(Color.BLACK);
        saveButton.setForeground(Color.WHITE);
        saveButton.setFont(normalFont);
        saveButton.setFocusPainted(false);
        saveButton.addActionListener(saveHandler);
        savePanel.add(saveButton);

        menuButtonPanel = new JPanel();
        menuButtonPanel.setBackground(Color.BLACK);
        menuButton = new JButton("Menü");
        menuButton.setBackground(Color.BLACK);
        menuButton.setForeground(Color.WHITE);
        menuButton.setFont(normalFont);
        menuButton.setFocusPainted(false);
        menuButton.addActionListener(msHandler);
        menuButtonPanel.add(menuButton);

        pauseMenuPanel.add(resumePanel);
        pauseMenuPanel.add(savePanel);
        pauseMenuPanel.add(menuButtonPanel);
        pauseMenuPanel.add(endPanel);
        endPanel.setVisible(true);
        con.add(pauseMenuPanel);


    }
    /*
    * Hier werden/wurden die Action Listener/ Handler implementiert
    * */

    public class MenuScreenHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            resumeButton.setVisible(false);
            saveButton.setVisible(false);
            resumePanel.setVisible(false);
            savePanel.setVisible(false);
            endPanel.setVisible(false);
            pauseMenuPanel.setVisible(false);
            pausePanel.setVisible(false);
            choicePanel.setVisible(false);
            mainTextPanel.setVisible(false);
            titleNamePanel.setVisible(true);
            try {
                readSaveFile();
                if(!position.equals("")){
                    foundGameSave = true;
                }
                else foundGameSave = false;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            createMenuScreen();
        }
    }
    public class TitleScreenHandler implements ActionListener {
        public void actionPerformed(ActionEvent event){
            try {
                readSaveFile();
                if(!position.equals("")){
                    foundGameSave = true;
                }
                else foundGameSave = false;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            createMenuScreen();

        }

    }
    public class PauseScreenHandler implements ActionListener {
        public void actionPerformed(ActionEvent event){
            pauseMenu();

        }

    }
    public class ContinueHandler implements ActionListener { //AKTUALISIEREN NICHT VERGESSEN

        public void actionPerformed(ActionEvent event) {
            createGameScreen();
            if(position.equals("tag1")){
                tag1();
            }
            else if(position.equals("gameOver1")){
                gameOver1();
            }
            else if(position.equals("kaffee1")){
                kaffee1();
            }
            else if(position.equals("schnell1")){
                schnell1();
            }
            else if(position.equals("vorne1")){
                vorne1();
            }
            else if(position.equals("hinten1")){
                hinten1();
            }
            else if(position.equals("kaffee2")){
                kaffee2();
            }
            else if(position.equals("mitte1")){
                mitte1();
            }
            else if(position.equals("neunA")){
                neunA();
            }
            else if(position.equals("falsch1")){
                falsch1();
            }
            else if(position.equals("computer1")){
                computer1();
            }
            else if(position.equals("laptop1")){
                laptop1();
            }
            else tag1();

        }
    }
    public class LoadHandler implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            //tbd
        }
    }
    public class NewGameHandler implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            createGameScreen();
            tag1();
        }
    }
    public class ControlsHandler implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            //tbd
        }
    }
    public class GuideHandler implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            showGuide();
        }
    }
    public class EndHandler implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            System.exit(0);
        }
    }

    public class SaveHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            //getPosition();
            System.out.println(position);
            try {
                saveSaveFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("...\n" +
                    "Spiel gespeichert...");
            resumeButton.setVisible(false);
            saveButton.setVisible(false);
            resumePanel.setVisible(false);
            savePanel.setVisible(false);
            endPanel.setVisible(false);
            pauseMenuPanel.setVisible(false);
            choicePanel.setVisible(true);
            mainTextPanel.setVisible(true);
            pausePanel.setVisible(true);
        }
    }
    public class ResumeHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            resumeButton.setVisible(false);
            saveButton.setVisible(false);
            resumePanel.setVisible(false);
            savePanel.setVisible(false);
            endPanel.setVisible(false);
            pauseMenuPanel.setVisible(false);
            choicePanel.setVisible(true);
            mainTextPanel.setVisible(true);
            pausePanel.setVisible(true);
        }
    }

    /*
    * Hier wird die Story implementiert
    *
    * */

    //Kopiervorlage um neue Dialogoptionen einzufügen
    public void platzhalter(){
        position = "playtzhalter"; //umbenennen nicht vergessen
        mainTextArea.setText("");
        choice1.setText("");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    public void tag1(){

        position = "tag1";
        mainTextArea.setText("*Du machst deinen Wecker aus*\n" +
                "Alexa: Hey you, you're finally awake...\n" +
                "Du hast einen Termin um 09:00 Uhr: \"Berufsschule\".\n" +
                "Dir verbleiben 1 Stunde und 3 Minuten bis zu deinem Termin.");
        choice1.setText("Bis um 08:00 weiterschlafen");
        choice2.setText("Kaffee trinken");
        choice3.setText("Anziehen und auf den weg machen");
        choice4.setText("Reddit durchstöbern");
    }
    public void gameOver1(){
        position = "gameOver1";
        mainTextArea.setText("OH NEIN!!! Es ist schon 13:37 Uhr!\n" +
                "Sieht aus als hättest du deinen ersten Tag verpasst :(\n" +
                "Beschämt setzt du dich an deinen PC und startest WoW..");
        choicePanel.setVisible(false);
    }
    public void kaffee1(){
        position = "kaffee1";
        mainTextArea.setText("Nachdem du deinen Kaffee getrunken hast ruft " +
                "die Toilette.\nNachdem du dich erleichtert und für den ersten " +
                "Schultag vorbereitet hast, begiebst du dich mit der S3 nach Wilhelmsburg.\n" +
                "Du kommst pünktlich an der Schule an und siehst deinen Arbeitskollegen " +
                "schon vor der Mensa stehen. Ihr betretet gemeinsam die Mensa und " +
                "müsst euch einen Platz aussuchen...");
        choice1.setText("Ihr setzt euch in die erste Reihe");
        choice2.setText("Ihr setzt euch nach hinten");
        choice3.setText("Ihr holt euch einen Kaffee");
        choice4.setText("Ihr setzt euch in die Mitte");
    }
    public void schnell1(){
        position = "schnell1";
        mainTextArea.setText("Nachdem du dich angezogen hast, " +
                "begiebst du dich mit der S3 nach Wilhelmsburg.\n" +
                "Du kommst überpünktlich an der Schule an und siehst deinen Arbeitskollegen" +
                "schon vor der Mensa stehen. Ihr betretet gemeinsam die fast leere Mensa und " +
                "müsst euch einen Platz aussuchen...");
        choice1.setText("Ihr setzt euch in die erste Reihe");
        choice2.setText("Ihr setzt euch nach hinten");
        choice3.setText("Ihr holt euch einen Kaffee");
        choice4.setText("Ihr setzt euch in die Mitte");
    }

    public void vorne1(){
        position = "vorne1";
        mainTextArea.setText("Gespannt folgt ihr der Präsentation...\n" +
                "Ihr wurdet der IT9a zugeteilt und sollt euch nun in einer Ecke sammeln...");
        choice1.setText("Klasse Folgen");
        choice2.setText("Kaffee holen");
        choice3.setText("Rauchen gehen");
        choice4.setText("Aufgeben und nachhause gehen");
    }
    public void hinten1(){
        position = "hinten1";
        mainTextArea.setText("Ihr setzt euch in die vorletzte Reihe und die Präsentation " +
                "beginnt. Da ihr so weit hinten sitzt könnt ihr kein Wort verstehen. " +
                "Als die Präsentation vorüber ist sammeln sich in allen Ecken die unterschiedlichen " +
                "Klassen. Ihr wisst nicht in welcher Klasse ihr seid, welcher schließt ihr euch an?");
        choice1.setText("9n");
        choice2.setText("9a");
        choice3.setText("9c");
        choice4.setText("9b");
    }
    public void kaffee2(){
        position = "kaffee2";
        mainTextArea.setText("Oh nein, du musst dringend auf Klo. " +
                "Nachdem du 15 Minuten nach einer Toilette gesucht hast, " +
                "kommst du in die völlig überfüllte Mensa zurück und musst dich " +
                "hinten in die letzte Reihe stellen..." +
                "Da ihr so weit hinten steht könnt ihr kein Wort verstehen. \" +\n" +
                "Als die Präsentation vorüber ist sammeln sich in allen Ecken die unterschiedlichen\n" +
                "Klassen. Ihr wisst nicht in welcher Klasse ihr seid, welcher schließt ihr euch an?");
        choice1.setText("9n");
        choice2.setText("9a");
        choice3.setText("9c");
        choice4.setText("9b");
    }
    public void mitte1(){
        position = "mitte1";
        mainTextArea.setText("Ihr setzt euch in die Mitte der Mensa. " +
                "nach der Präsentation folgt ihr eurer Klasse in den Klassenraum. " +
                "In der Klasse angekommen setzt du dich an einen freien Platz und " +
                "legst deine Sachen ab. Deine neue Klassenlehrerin ist nicht da, stattdessen " +
                "stellt sich Nino als euer WuG Lehrer vor. Nino erklärt allen wie ihr euren " +
                        "Account einrichtet, dafür sollt ihr den Computer anschalten");
        choice1.setText("Schalte den Computer an");
        choice2.setText("Du schaltest deinen Laptop an");
        choice3.setText("Vor dir ist kein Computer");
        choice4.setText("Was soll ich machen?");
    }
    public void neunA(){
        position = "neunA";
        mainTextArea.setText("In der Klasse angekommen setzt du dich an einen freien Platz und " +
                "legst deine Sachen ab. Deine neue Klassenlehrerin ist nicht da, stattdessen " +
                "stellt sich Nino als euer WuG Lehrer vor. Nino erklärt allen wie ihr euren " +
                "Account einrichtet, dafür sollt ihr den Computer anschalten");
        choice1.setText("Schalte den Computer an");
        choice2.setText("Du schaltest deinen Laptop an");
        choice3.setText("Vor dir ist kein Computer");
        choice4.setText("Was soll ich machen?");
    }
    public void falsch1(){
        position = "falsch1";
        mainTextArea.setText("Oh nein, du bist in der falschen Klasse gelandet!\n" +
                "Als die Anwesenheitsliste durchgegangen wird steht dein Name " +
                "nicht mit auf der Liste. Du machst dich auf die Suche nach den anderen Klassen " +
                "und fragst ob du in diese gehörst. In der IT9a wirst du fündig. " +
                "In der Klasse angekommen setzt du dich an einen freien Platz und " +
                "legst deine Sachen ab. Dein WuG Lehrer Nino stellt sich vor und " +
                "erklärt allen wie ihr euren " +
                "Account einrichtet, dafür sollt ihr den Computer anschalten");
        choice1.setText("Schalte den Computer an");
        choice2.setText("Du schaltest deinen Laptop an");
        choice3.setText("Vor dir ist kein Computer");
        choice4.setText("Was soll ich machen?");
    }
    public void computer1() {
        position = "computer1";
        mainTextArea.setText("Nachdem du 10 Minuten gebraucht hast " +
                "um die alte Hachel zum laufen zu bekommen, sagst du Bescheid, " +
                "dass es weitergehen kann. Nino erklärt allen was \"Moodle\" ist " +
                "und wie ihr auch dort euren Account einrichtet." +
                "\n\n\n\n ÜBERRASCHUNG!!! Dein Passwort ist falsch! ");
        choice1.setText("Eine Mail an den Support senden");
        choice2.setText("Erst Nino, dann Stefan fragen");
        choice3.setText("Passwort zurücksetzen");
        choice4.setText("Das selbe Passwort nochmal!");
    }
    public void laptop1(){
        position = "laptop1";
        mainTextArea.setText("Du hast kein LAN-Kabel dabei gehabt und kommst " +
                "nicht in die Domain, deshalb kannst du dich natürlich auch nicht anmelden.\n" +
                "Nachdem du 5 Kabel von der Fensterbank durchprobiert hast, " +
                "findest du endlich eines, welches funktioniert. Nino erklärt nun allen, " +
                "was \"Moodle\" ist und wie ihr euch dort anmeldet..." +
                "\n\n\n\n ÜBERRASCHUNG!!! Dein Passwort ist falsch! ");
        choice1.setText("Eine Mail an den Support senden");
        choice2.setText("Erst Nino, dann Stefan fragen");
        choice3.setText("Passwort zurücksetzen");
        choice4.setText("Das selbe Passwort nochmal!");
    }


    public class ChoiceHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            //Antwortmöglichkeiten implementieren
            String playersChoice = event.getActionCommand();
            switch(position){
            case "tag1":
                switch(playersChoice){
                    case "c1":
                    case "c4":
                        gameOver1();break;
                    case "c2": kaffee1(); break;
                    case "c3": schnell1(); break;
                } break;
                case "kaffee1":
                case "schnell1":
                    switch(playersChoice){
                        case "c1": vorne1();break;
                        case "c2": hinten1(); break;
                        case "c3": kaffee2(); break;
                        case "c4": mitte1(); break;

                    }break;
                case "hinten1":
                    switch (playersChoice){
                        case "c1":
                        case "c3":
                        case "c4":
                            falsch1();break;
                        case "c2": neunA();break;
                    }
                case "kaffee2":
                    switch (playersChoice){
                        case "c1":
                        case "c3":
                        case "c4":
                            falsch1();break;
                        case "c2": neunA();break;
                    }
                case "falsch1":
                case "neunA":
                    switch (playersChoice){
                        case "c1":computer1();break;
                        case "c2":laptop1();break;
                    }
            }
        }
    }








}
