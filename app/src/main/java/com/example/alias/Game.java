package com.example.alias;

import android.media.MediaPlayer;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Arrays;

public class Game {
    /// CONTAINER VARIABLES ------------------------------------------------------------------------
    private boolean is_english;
    private int difficulty; //1- junior; 2- medium; 3-godlike
    private int num_of_teams;
    private int current_round_num;
    private int time_of_one_round;
    private int max_points_to_win_game;
    private boolean is_skip_penalty;
    private boolean is_sound;
    private boolean is_music;
    private boolean game_created_for_the_first_time;
    private int id_currently_playing_team;
    private ArrayList<Team> all_teams;
    private ArrayList<Team> all_teams_current_round;
    private MediaPlayer music_effect;
    private Boolean visited_fragment_6=false;
    private ArrayList<String> game_words; // copy of words, so that already seen could be removed

    /// CONSTRUCTORS -------------------------------------------------------------------------------
    Game() {
        this(0);
    }

    Game(int numTeams){
        this.setTeams(numTeams);
        this.num_of_teams = numTeams;
        this.difficulty = 1;
        this.is_english = false;
        this.current_round_num = 1;
        this.time_of_one_round = 10;
        this.max_points_to_win_game = 10;
        this.is_skip_penalty = false;
        this.id_currently_playing_team = 0;
        this.is_sound = false;
        this.is_music = false;
        this.game_created_for_the_first_time = true;
    }

    Game(int numTeams, boolean isEnglish, int level, int timeOfOneRound,
         int maxPointsToWinGame, boolean isSkipPenalty, boolean isSound,
         boolean isMusic) {
        this(numTeams);

        this.is_english = isEnglish;
        this.difficulty = level;
        this.time_of_one_round = timeOfOneRound;
        this.max_points_to_win_game = maxPointsToWinGame;
        this.is_skip_penalty = isSkipPenalty;
        this.is_sound = isSound;
        this.is_music = isMusic;
    }

    /// SET methods --------------------------------------------------------------------------------
    public void setTeams(int numOfTeams) {
        this.num_of_teams = numOfTeams;

        all_teams = new ArrayList<Team>();
        int lastTeamId=0;
        for(int i = 0; i < this.num_of_teams; i++)
        {
            all_teams.add(new Team(lastTeamId));
            lastTeamId++;
        }
    }

    public void setMusic(MediaPlayer music_effect) { this.music_effect = music_effect; }
    public void setNumOfTeams(int num) { this.num_of_teams = num ;}
    public void setDifficulty(int level) { this.difficulty = level; }
    public void setLanguage(boolean isEnglish) { this.is_english = isEnglish; }
    public void setIsSound(boolean isSound) { this.is_sound = isSound; }
    public void setIsMusic(boolean isMusic) { this.is_music = isMusic; }
    public void setTimeOfOneRound (int timeOfOneRound) { this.time_of_one_round = timeOfOneRound; }
    public void setMaxPointsToWinGame(int maxPointsToWinGame) { this.max_points_to_win_game =
            maxPointsToWinGame; }
    public void setIsSkipPenalty(boolean isSkipPenalty) { this.is_skip_penalty = isSkipPenalty; }
    public void FRAGMENT6_WAS_VISITED(boolean VISIT) { this.visited_fragment_6 = VISIT; }
    public void setGameCreatedForTheFirstTime(boolean gameCreatedForTheFirstTime) {
        this.game_created_for_the_first_time = gameCreatedForTheFirstTime;
    }
    public void setGameWords(String[] gameWords) {
        this.game_words = new ArrayList<String>(Arrays.asList(gameWords)); }

    /// GET methods --------------------------------------------------------------------------------
    public Team getTeam(int id) { return this.all_teams.get(id); }
    /***
     * Be veeeeeeery careful with usage of this method
     * @return
     */
    public ArrayList<Team> getAll_teams() { return this.all_teams; }
    //public ArrayList<Team> getAll_teams_current_round() { return this.all_teams_current_round; }
    public int getNumOfTeams() { return this.num_of_teams; }
    public int getCurrentRoundNum() { return this.current_round_num; }
    public boolean getIsEnglish() { return this.is_english; }
    public boolean Visit_of_fragment_6() { return this.visited_fragment_6; }
    public int getIdCurrentlyPlayingTeam() { return this.id_currently_playing_team; }
    public Team getCurrentlyPlayingTeam() { return this.all_teams.get(this.id_currently_playing_team); }
    public boolean getIsSound() { return this.is_sound; }
    public boolean getIsMusic() { return this.is_music; }
    public MediaPlayer getMusic() { return this.music_effect; }
    /**
     * Difficulty is described accordingly: 1-junior; 2-middle; 3-senior
     * @return int: level of difficulty (1,2 or 3)
     */
    public int getDifficulty() { return this.difficulty; }
    public int getTimeOfOneRound() { return this.time_of_one_round; }
    public int getMaxPointsToWinGame() { return this.max_points_to_win_game; }
    public boolean getIsSkipPenalty() { return this.is_skip_penalty; }
    public boolean getGameCreatedForTheFirstTime() { return this.game_created_for_the_first_time; }
    public String getGameWord(int index) { return this.game_words.get(index); }
    public int getGameWordsCount() { return this.game_words.size(); }

    /// ADDITIONAL methods -------------------------------------------------------------------------
    public void removeGameWord(int index) { this.game_words.remove(index); }
    public void updateCurrentRoundNum()
    {
        this.current_round_num++;

        for (int i = 0; i < this.num_of_teams; i++)
            all_teams.get(i).newRoundIsStarted();

        if (this.current_round_num % this.num_of_teams == 0)
            this.id_currently_playing_team = 0;
        else
            this.id_currently_playing_team++;
    }

    /***
     * Verifies that at least one team have reached required amount of points
     * to win
     * @return boolean: at least one team have reached required amount of points
     */
    public boolean oneTeamWon() {
        for (int i = 0; i < this.num_of_teams; i++)
        {
            if(all_teams.get(i).getTotalPoints() >= this.max_points_to_win_game)
                return true;
        }
        return false;
    }

    /**
     * Verifies that every team had a chance to play in the current round
     * @return boolean: every team had a chance to play in the current round
     */
    public boolean everyTeamPlayedInThisRound() {
        if (this.current_round_num % this.num_of_teams == 0)
            return true;
        return false;
    }

    public Team getWinnerTeam(){
        int max = all_teams.get(0).getTotalPoints();
        int maxId = all_teams.get(0).getId();

        if (oneTeamWon()){

            for(int i = 1; i < this.num_of_teams; i++){
                if (this.all_teams.get(i).getTotalPoints() > max)
                {
                    max = this.all_teams.get(i).getTotalPoints();
                    maxId = this.all_teams.get(i).getId();
                }

            }
        }
        return this.all_teams.get(maxId);
    }

    public void sortTeamsByPoints(){

        for(int i = 0; i < this.num_of_teams - 1; i++){

            for (int j = 0; j < this.num_of_teams - i - 1; j++){

                if(this.all_teams.get(j).getTotalPoints() < this.all_teams.get(j+1).getTotalPoints()){
                    Team temp = this.all_teams.get(j);
                    this.all_teams.set(j, this.all_teams.get(j+1));
                    this.all_teams.set(j+1, temp);
                }
            }

        }
    }




    /// WORDS' LISTS -------------------------------------------------------------------------------
    public String[] getWordsJunior_lt() { return this.words_junior_lt; }
    public String[] getWordsJunior_en() { return this.words_junior_en; }
    public String[] getWordsMedium_lt() { return this.words_medium_lt; }
    public String[] getWordsMedium_en() { return this.words_medium_en; }
    public String[] getWordsSenior_lt() { return this.words_senior_lt; }
    public String[] getWordsSenior_en() { return this.words_senior_en; }
    private String[] words_junior_lt = {"Kauk??",        "Citrina",      "U??tvanka",     "Medus",
                                "Kiaul??",       "Ra??ysena",     "Kaklas",       "Arbatinukas",
                                "Arb??zas",      "??ingsnis",     "Instrukcija",  "M??daus m??nuo",
                                "Klas??",        "In??inierius",  "Sodas",        "Dangtelis",
                                "Atspindys",    "Renginys",     "Pilis",        "Prietaisas",
                                "Platukas",     "??okoladas",    "Kv??pavimas",   "Tinklinis",
                                "Galaktika",    "Svog??nas",     "Kaulas",       "U??sakymas",
                                "Kritika",      "Pab??gti",      "S??ra??as",      "Variklis",
                                "Lie??uvis",     "V????lys",       "Aukl??",        "Triu??is",
                                "Drakonas",     "Kalnas",       "Valgiara??tis", "D??????",
                                "K??d??",         "Au??ra",        "Pir??tin??",     "Pilnametis",
                                "Ateitis",      "Gerb??jas",     "Pel??da",       "Grybas",
                                "Stresas",      "Klubas",       "U??kandis",     "Pagalv??",
                                "Eil??",         "Planas",       "Cirkas",       "Kodas",
                                "Linija",       "Svetain??",     "Piet??s",       "Gyvat??",

                                "Meil??",         "Plaukai",      "Saldainis",     "Tu??inukas",
                                "Robotas",        "Ma??ina",       "Tiltas",       "Kukur??zas",
                                "Stalas",         "Futbolas",      "Garbana",      "Kalnai",
                                "Snaig??",         "Vy??nia",      "Zigzagas",       "V??liava",
                                "Kum??tin?? pir??tin??",  "Morka",      "??viesa",       "Skruzdel??s",
                                "??andas",           "Atletas",    "Filmas",       "Kar??tis",
                                "Padav??ja",         "D??d??",      "Brokolis",    "Pel??",
                                "Kavin??",        "Kramtyti",       "Druska",       "Skrudinta duona",
                                "Akiniai",           "Knyga",       "Sraig??",       "Dubuo",
                                "Supyn??s",            "Varl??",      "Gyvas",        "Monstras",
                                "Aitvaras",             "Sapnas",     "Vandenynas",        "Kal??jimas",
                                "Kablys",              "Laikrodis",    "Apskritimas",       "J??r?? v????lys",
                                "Avis",            "??uvis",     "Krabas",         "Kaminas",
                                "Sraigtasparnis",       "M??nulis",     "Kvadratas",       "Muzika",
                                "??vaig??d??",             "Kiaul??",      "Ausis",          "Debesis",

                                "??uo", "Lydeka", "Ver??elis", "Vover??",
                                "Tinginys", "Pap??ga", "Raganosis", "Boru????l??",
                                "Vilkas", "Kupranugaris", "Ponis", "Strutis",
                                "Lama", "Med??za", "Gepardas", "Briedis",
                                "Elnias", "Pauk??tis", "Kurmis", "Keng??ra",
                                "Svirplys", "Pelikanas", "??iogas", "Ruonis",
                                "Vik??ras", "??uv??dra", "Baltasis lokys", "Zebras",
                                "Omaras", "Jautis", "Kat??", "??iurk??nas",
                                "??e??kas", "Bit??", "Aligatorius", "Chameleonas",
                                "Voras", "Li??tas", "Asilas", "Krokodilas",
                                "??ik??nosparnis", "????sis", "Nuotrauka", "Spintel??",
                                "Vonia", "Klim??lis", "Dangtis", "Butelis",
                                "Durys", "Namas", "Audinys", "Popierius",
                                "Gara??as", "L??k??t??", "Dar??ov??s", "K??dikis",
                                "Virtuv??", "??ol??", "Mygtukas", "Mama",
                                "T??tis", "Stiklas", "Fortepijonas", "Gitara",
                                "B??gnai", "Augalas", "Kempin??l??", "Langas",
                                "Naminis gyv??nas", "Lova", "Kompiuteris", "Sk??tis",

                                "Skaidr??","Medis","Kablys","Vi??ta","Kaminas",
                                "Vy??nia","Sniego ??mogus","Kal??jimas","Lietus","Hipopotamas",
                                "Kompiuteris","Pel??da","J??ra","??ol??","Banginis",
                                "Apskritimas","Skruzdel??","Ply??ys","Valtis","Kuprin??",
                                "Mus??","Rankin??","Kreidel??","Pica","Sausainis",
                                "Kalnas","Juros v????lys","Stiklainis","Keksiukas","Nagas",
                                "Balionas","Puodelis","Blokai","Lapas","Varl??",
                                "Vandenynas","V????lys","Kubas","Sapnas","Tualetas",
                                "Dinozauras","Ledai","??aka","Pledas","??ukos",
                                "Plunksna","Motoroleris","Snaig??","Zigzagas","Sala",

                                "Bu??inys", "??altis", "Pusry??iai", "Nie??ulys", "Apkabinimas",
                                "Kel??nas", "??nab??desys", "??i??ronai", "Diskoteka", "Pertrauka",
                                "Skai??iavimas", "Skonis", "Li??desys", "Irklas", "Beldimas",
                                "Berniukas", "Siurprizas", "Rie??as", "Plojimas", "??uolis",
                                "Pa??i????a", "L??pa", "Plaukimas", "??iovulys", "Akis",
                                "P??da", "Vakarien??", "Trauka", "Eil??", "??iedas",
                                "Pabudimas", "St??mimas", "Liftas", "Malda", "Triu??is", "Krep??inis"
    };

    private String[] words_junior_en = {"Mask",         "Lemon",        "Embankment",   "Honey",
                                "Cow",          "Handwriting",  "Neck",         "Teapot",
                                "Watermelon",   "Footstep",     "Instruction",  "Honeymoon",
                                "Classroom",    "Engineer",     "Garden",       "Lid",
                                "Reflection",   "Event",        "Bone",         "Device",
                                "Hammer",       "Chocolate",    "List",         "Volleyball",
                                "Galaxy",       "Onion",        "Babysitter",   "Order",
                                "Judge",        "Escape",       "Menu",         "Engine",
                                "Tongue",       "Turtle",       "Glove",        "Rabbit",
                                "Dragon",       "Mountain",     "Owl",          "Box",
                                "Chair",        "Dawn",         "Snack",        "Adult",
                                "Future",       "Fan",          "Circus",       "Mushroom",
                                "Stress",       "Club",         "Lunch",        "Pillow",
                                "Queue",        "Plan",         "Castle",       "Code",
                                 "Line",         "Web",         "Breathing",    "Snake",

                               "Love",         "Hair",         "Candy",        "Pen",
                                "Robot",        "Car",          "Bridge",       "Corn",
                                "Desk",         "Football",      "Curl",         "Mountains",
                               "Snowflake",     "Cherry",      "Zigzag",       "Flag",
                               "Mitten",         "Carrot",      "Light",       "Ants",
                               "Cheek",           "Athlete",    "Movie",       "Hot",
                                "Waitress",         "Uncle",      "Broccoli",    "Mouse",
                                "Cafeteria",        "Chew",       "Salt",       "Toast",
                                 "Glasses",           "Book",       "Snail",       "Bowl",
                                "Swing",            "Frog",      "Alive",        "Monster",
                                 "Kite",             "Dream",     "Ocean",        "Jail",
                                 "Hook",              "Clock",    "Circle",       "Sea turtle",
                                  "Sheep",            "Fish",     "Crab",         "Chimney",
                                  "Helicopter",       "Moon",     "Square",       "Music",
                                  "Star",             "Pig",      "Ear",          "Cloud",

                                "Dog","Pike","Calf","Squirrel",
                                "Sloth","Parrot","Rhino","Ladybug",
                                "Wolf","Camel","Pony","Ostrich",
                                "Llama","Jellyfish","Cheetah","Moose",
                                "Reindeer","Bird","Mole","Kangaroo",
                                "Cricket","Pelican","Grasshopper","Seal",
                                "Caterpillar","Seagull","Polar bear","Zebra",
                                "Lobster","Ox","Cat","Hamster",
                                "Skunk","Bee","Alligator","Chameleon",
                                "Spider","Lion","Donkey","Crocodile",
                                "Bat","Goose","Photograph","Cabinet",
                                "Bath","Mat","Lid","Bottle",
                                "Door","Home","Tissue","Paper",
                                "Garage","Plate","Vegetable","Baby",
                                "Kitchen","Grass","Button","Mom",
                                "Dad","Glass","Piano","Guitar",
                                "Drums","Plant","Sponge","Window",
                                "Pet","Bed","Computer","Umbrella",

                                "Slide","Tree","Hook","Chicken","Chimney",
                                "Cherry","Snowman","Jail","Rain","Hippo",
                                "Computer","Owl","Sea","Grass","Whale",
                                "Circle","Ant","Crack","Boat","Backpack",
                                "Fly","Purse","Crayon","Pizza","Cookie",
                                "Mountain","Sea turtle","Jar","Cupcake","Nail",
                                "Baloon","Cup","Blocks","Leaf","Frog",
                                "Ocean","Turtle","Cube","Dream","Bathroom",
                                "Dinosaur","Popsicle","Branch","Blanket","Comb",
                                "Feather","Motorcycle","Snowflake","Zigzag","Island",

                                "Kiss", "Cold", "Breakfast", "Itch", "Hug",
                                "Knee", "Whisper", "Binoculars", "Disco", "Break",
                                "Count", "Taste", "Sad", "Paddle", "Knock",
                                "Boy", "Surprise", "Wrist", "Clap", "Hop",
                                "Skate", "Lip", "Swim", "Yawn", "Eye",
                                "Feet", "Dinner", "Pull", "Row", "Ring",
                                "Awake", "Push", "Lift", "Beg", "Prayer",
                                "Rabbit", "Basketball"

    };



    private String[] words_medium_lt = {"Liokajus",         "??turmanas",        "I??davyst??",
                                "Na??ta",            "Migracija",        "Kalakutas",
                                "Pakartojimas",     "Guma",             "Mantija",
                                "??iu??inys",         "Sutrikimas",       "Santykis",
                                "Dokumencija",      "Mineralas",        "??lanka",
                                "Korespondentas",   "Tardymas",         "Nekantrumas",
                                "Katedra",          "Pratarm??",         "Prevencija",
                                "Budrumas",         "Gerov??",           "Intelektas",
                                "R??kas",            "V??jas",            "M??lyn??",
                                "Gidas",            "??vyturys",         "Pasi??lymas",
                                "Fleita",           "Alergija",         "Riba",
                                "Teisingumas",      "S??saga",           "Kalorija",
                                "Nuostaba",         "??protis",          "??impanz??",
                                "Klest??jimas",      "Lankomumas",       "Sud??tingumas",
                                "Diagrama",         "Nelaim??",          "Vaiduoklis",
                                "Angelas",          "Atsargin?? kopija", "Gr????tas",

                                "Naujagimis",          "Kamera",           "Ra??alas",
                                "Rit??",             "Kino teatras",    "Apskritas langas",
                                "Ledinukas",         "Smegenys",            "Juosta",
                                "Skrandis",          "Banano ??iev??",     "Pluk??",
                                "Maik??",          "Hot-dogas",          "Randas",
                                "Durys",             "Rytai",             "Menininkas",
                                "??adintuvas",      "Guminis kamuoliukas",          "Organas",
                                "Savivartis ",       "Tarakonas",        "Tualetinis popierius",
                                "Ryklys",            "Skerdikas",          "Varpas",
                                "Avilys",          "Mokytoja",          "??kininkas",
                                "Vir??elis",            "Buo??galvis",          "Kep??jas",
                                "Suknel??",            "Mokyklinis autobusas",       "??ukos",
                                "Letena",             "U??uolaidos",         "Nyk??tys",
                                "Pagalv??s u??valkalas",       "Eskalatorius",        "U??daryta",
                                "Lietus",             "Colis",             "Tramplynas",
                                "Sidabro dirbiniai",       "Ratas",              "Dur?? rankena",

                                "Avarija", "Prad??ti", "Kreiv??", "Miestas",
                                "Tvenkinys", "Kop????ios", "Su??iai", "Nuot??kis",
                                "Mik??iojimas", "Stadionas", "Naujienlai??kis", "Cukrus",
                                "Valanda", "Limitas", "??aisl?? parduotuv??", "Prailgintojas",
                                "Kostiumas", "Gamykla", "Lygis", "Skalbimo ma??ina",
                                "Gydymas", "Puslapis", "Vidurnaktis", "Teta",
                                "Kaubojus", "??ildytuvas", "Leid??jas", "??aknis",
                                "Mitas", "Legenda", "??alis", "Alus",
                                "U??trauktukas", "Chronometras", "??entas", "Dukra",
                                "Uo??v??", "Uo??vis", "S??n??nas", "Dukter????ia",
                                "Gerv??", "Istorija", "Pakrant??", "Pirmininkas",
                                "Prisijungti", "Komanda", "Partneris", "Ugniagesys",

                                "Gele??inkelis","Grilius","??aknis","Matyti","Padanga",
                                "Avis","Kauk??","Supyn??s","Tinklas","Pi??ama",
                                "Pavasaris","Varlyt??","Oro uostas","iPad","Puma",
                                "Vainikas","J??r?? arkliukas","Dar??ov??","Spyna","Lempos jungtukas",
                                "Bok??tas","Kriketas","Kojin??","Vamzdis","Kep??jas",
                                "Bur??","Gryni pinigai","Va??kas","Kaset??","Tarakonas",
                                "Vaflis","B??sena","Vert??jas","Princas","??ymeklis",
                                "Rie??utas","Diagrama","Ketvertukas","Platuma","Virtuv??",
                                "Snieglen??i?? sportas","??vilpukas","Kastuvas","Salieras","Sandalas",
                                "Gele??is","Slidin??ti","Bi??i?? avilys","Greitkelis","??eiti",

    };

    private String[] words_medium_en = {"Butler",           "Navigator",        "Betrayal",
                                "Burden",           "Migration",        "Turkey",
                                "Repetition",       "Rubber",           "Mantle",
                                "Mattress",         "Disorder",       "Relation",
                                "Documentary",      "Mineral",          "Gulf",
                                "Correspondent",    "Interrogation",    "Impatience",
                                "Cathedral",        "Preface",          "Prevention",
                                "Vigilance",        "Well",             "Intelligence",
                                "Mist",             "Breeze",           "Blueberry",
                                "Guide",            "Lighthouse",       "Proposition",
                                "Flute",            "Allergy",          "Boundary",
                                "Justice",          "Cufflink",         "Calorie",
                                "Amazement",        "Habit",            "Chimpanzee",
                                "Prosperity",       "Attendance",       "Complexity",
                                "Chart",            "Disaster",         "Ghost",
                                "Angel",            "Backup",           "Drill",

                              "Newborn",          "Camera",           "Ink",
                              "Coil",             "Movie theater",    "Porthole",
                              "Popsicle",         "Brain",            "Tape",
                               "Stomach",          "Banana split",     "Anemone",
                               "T-shirt",          "Hot dog",          "Scar",
                               "Door",             "East",             "Artist",
                               "Alarm clock",      "Gumball",          "Organ",
                              "Dump truck",       "Cockroach",        "Toilet paper",
                               "Shark",            "Butcher",          "Bell",
                              "Beehive",          "Teacher",          "Farmer",
                             "Cover",            "Tadpole",          "Baker",
                              "Dress",            "School bus",       "Hairbrush",
                              "Claw",             "Curtains",         "Thumb",
                              "Pillowcase",       "Escalator",        "Closed",
                              "Rain",             "Inch",             "Trampoline",
                              "Silverware",       "Lap",              "Doorknob",

                            "Shipwreck","Start","Curve","City",
                            "Pond","Ladder","Sushi","Leak",
                            "Stutter","Stadium","Newsletter","Sugar",
                            "Hour","Limit","Toy store","Extension cord",
                            "Suit","Factory","Level","Washing machine",
                            "Cure","Page","Midnight","Aunt",
                            "Cowboy","Heater","Publisher","Blueprint",
                            "Myth","Legend","Country","Tin",
                            "Zipper","Stopwatch","Son-in-law","Daughter-in-law",
                            "Brother-in-law","Sister-in-law","Father-in-law","Mother-in-law",
                            "Crane","Story","Coastline","Chairman",
                            "Log-in","Team","Partner","Firefighter",

                            "Railroad","Grill","Root","See","Tire",
                            "Sheep","Mask","Swing","Net","Pajamas",
                            "Spring","Bowtie","Airport","iPad","Cougar",
                            "Wreath","Seahorse","Vegetable","Lock","Light switch",
                            "Tower","Cricket","Stocking","Pipe","Baker",
                            "Sail","Cash","Wax","Tape","Cockroach",
                            "Waffle","State","Dump truck","Prince","Marker",
                            "Nut","Chart","Quadruplets","Latitude","Kitchen",
                            "Snowboarding","Whistle","Shovel","Celery","Sandal",
                            "Iron","Ski","Beehive","Highway","Enter",
    };

    private String[] words_senior_lt = {"Vikrumas",          "Saugotojas",      "Filharmonija",
                                "Atrologija",        "Valda",           "Terariumas",
                                "Gyn??jas",           "Antena",          "Pramoga",
                                "Poliglotas",        "Vaisingumas",     "Samurajus",
                                "Finansai",          "Elektronas",      "Jakas",
                                "Respublika",        "Kar??navimas",     "Klubas",
                                "??urmulys",          "??vertinimas",     "Vanagas",
                                "Gausa",             "Vartininkas",     "Grizlis",
                                "Neigimas",          "Kvota",           "Klarnetas",
                                "Gin??as",            "Tvarumas",        "Sagtis",
                                "Ekvilibristas",     "Gyvybingumas",    "Puma",
                                "Lokatorius",        "S??junga",         "Er??ilas",
                                "Pri??i??r??tojas",     "Frakas",          "Ekscelencija",

                                "Nardymas nuo uol??",      "Kr??miniai dantys",           "Kasykla",
                                "Grobis",              "Vestuvinis tortas",     "Pabaiga",
                                "Med??iaga",            "D??iazas",             "??em??s dreb??jimas",
                                "Savininkas",          "Maisto parduotuv??",    "Svetain??",
                                "Optometristas",       "Vi??tid??",      "Keltuvas",
                                "D??iungl??s",            "Filosofas",             "Kolon??l??s",
                                "Kapitonas",           "??stri??ain??",          "Kvidi??as",
                                "Teisingas",           "Maistas i??sive??imui",      "Mokamas kelias",
                                "Bobsl??jus",           "Trynys",                "Geras pirkinys",
                                "Kalvis",        "Jausmas",             "Kartografija",
                                "I??silavinimas",          "Atstovas",      "Sieksnis",
                                "S??vartynas",            "Vertimas",        "Ironija",
                                "Laiko zona",            "Nepa????stamas",          "Pelk??s",

                                "Linksmas", "Gylis", "Protestantas", "??lam??tas",
                                "Stal??ius", "Siesta", "Mirtingas", "Nemirtingas",
                                "Fragmentas", "Kilometras", "M??sl??", "Susikerta",
                                "Pavadinimas", "Eket??", "Gimnazistas", "I??sklaidyti",
                                "Du??o", "Chaosas", "Harmonija", "??statymas",
                                "Budrus", "Veteranas", "Parei??kimas", "Psichologas",
                                "Pasitik??ti", "Atradimas", "Pasidid??iavimas", "Dispe??eris",
                                "Brunet??", "Turtas", "Indeksas", "Infekcija",
                                "Sauja", "Skrydis", "Keliamieji metai", "Prie??as",
                                "Sugriauti", "Ironija", "Pok??tas", "Bausm??",
                                "Gyventojas", "Teismas", "Nuomon??", "Tvarkara??tis",
                                "Proto bok??tas", "Su??av??ti", "K??r??jas", "Reakcija",

                                "Griaustinis","Meras","A??arotas","Tureklai","Orbita",
                                "Mikrobas","Taika","Dailid??","Ikrai","I??nykti",
                                "Automobili?? prekyba","Klij?? pistoletas","Snausti","Krovinys","Konkurencija",
                                "Vald??ia","Ping pong'as","Ma??menin?? prekyba","Visa??dis","Vandens ciklas",
                                "Kostiumas","Kra??tovaizdis","Katalogas","Tarnas","Pristatymas",
                                "Koala","Trauma","Kelion??","Hipsteris","La??elin??",
                                "Gaminti","Lenta","Rasa","Baga??as","??iuk??l??s",
                                "Va??iuojamoji dalis","Gamykla","Nardymas","I??rasti","??onkaulis",
                                "Apsiaustas","Barbershop'as","Stipri li??tis","Apskleidimas","Kaiminyst??",
                                "Nutek??jimas","Tankumas","Pir??t?? galiukai","Tr??kumas","Aplodismentai",
                                "Mimika","Vilkikas","Varn?? lizdas","Animacinis filmas","Leid??jas",


    };

    private String[] words_senior_en = {"Dexterity",        "Protector",        "Philharmonic",
                                            "Astrology",        "Possession",       "Terrarium",
                                            "Defender",         "Antenna",          "Pastime",
                                            "Polyglot",         "Fruitfulness",     "Samurai",
                                            "Finance",          "Electron",         "Yak",
                                            "Republic",         "Coronation",       "Hip",
                                            "Commotion",        "Appreciation",     "Hawk",
                                            "Abundance",        "Gatekeeper",       "Grizzly",
                                            "Denial",           "Quota",            "Clarinet",
                                            "Altercation",      "Sustainability",   "Clasp",
                                            "Equilibrist",      "Vitality",         "Cougar",
                                            "Locator",          "Conjunction",      "Stallion",
                                            "Caretaker",        "Tailcoat",         "Excellency",

                                           "Cliff diving",      "Molar teeth",           "Mine",
                                           "Prey",              "Wedding cake",     "End zone",
                                           "Fabric",            "Jazz",             "Earthquake",
                                          "Landlord",          "Grocery store",    "Living room",
                                         "Optometrist",       "Chicken coop",      "Ski lift",
                                          "Jungle",            "Philosopher",             "Speakers",
                                          "Captain",           "Diagonal",          "Quidditch",
                                          "Correct",           "Drive-through",      "Toll road",
                                           "Bobsled",           "Yolk",                "Bargain",
                                          "Blacksmith",        "Feeling",             "Cartography",
                                          "Education",          "Representative",      "Fathom",
                                          "Landfill",            "Translation",        "Irony",
                                          "Time zone",            "Stranger",          "Wetlands",

                                        "Fun","Depth","Protestant","Junk",
                                        "Drawer","Siesta","Mortal","Immortal",
                                        "Fragment","Acre","Riddle","Intersect",
                                        "Title","Ice fishing","Gymnast","Scatter",
                                        "Shatter","Chaos","Harmony","Law",
                                        "Vigilante","Veteran","aftermath","Psychologist",
                                        "Confident","Discovery","Pride","Dispatcher",
                                        "Brunette","Wealth","Index","Infection",
                                        "Handful","Flight","Leap year","Enemy",
                                        "Destruction","Ironic","Joke","Punishment",
                                        "Population","Court","Opinion","Schedule",
                                        "Brainstorm","Admire","Creator","Reaction",

                                        "Thunder","Mayor","Tearful","Banister","Orbit",
                                        "Germ","Peace","Carpenter","Caviar","Granpa",
                                        "Car dealership","Glue gun","Nap","Cargo","Competition",
                                        "Government","Ping pong","Retail","Omnivore","Water cycle",
                                        "Suit","Landscape","Catalog","Servant","Deliver",
                                        "Koala","Injury","Trip","Hipster","Drip",
                                        "Produce","Plank","Dew","Baggage","Junk",
                                        "Driveway","Factory","Scuba diving","Invent","Rib",
                                        "Cape","Barbershop","Downpour","Reveal","Neighborhood",
                                        "Leak","Density","Tiptoe","Drawback","Applause",
                                        "Mime","Tow truck","Crow's nest","Cartoon","Publisher",
    };


    //---------------Team Names---------------------

    public String[] getAdjectives_en()  { return this.adjectives_en; }
    public String[] getNouns_en()       { return this.nouns_en; }
    public String[] getAdjectives_lt()  { return this.adjectives_lt; }
    public String[] getNouns_lt()       { return this.nouns_lt; }
    private String[] adjectives_en ={"Mad",      "Beautiful",     "Crazy",       "Lazy",       "Reckless",
                                     "Fast",     "Dead",          "Happy",       "Slow",       "Big",
                                     "Small",    "Dumb",          "Smelly",      "Lithuanian", "Scary",
                                     "Boring",   "Smart",         "Heavy",       "Chubby",     "Skinny",
                                     "Lame",     "Upset",         "Green",       "Bored",      "Red",
                                     "Yellow",   "Blue",          "Drunk",       "Pink",       "Tall",
                                     "Short",    "Imaginary",     "Real",        "Fluffy",     "Wicked",
                                     "Dirty",    "Clean",         "Edgy",        "Teenage",    "Bad",
                                     "Lonely"};

    private String[] nouns_en =     {"Dogs",         "Cats",      "Horses",      "Cows",       "Mice",
                                     "Pigs",         "Fishes",    "Bears",       "Pitbulls",   "Labradors",
                                     "Chickens",      "Trees",     "Goats",       "Cars",       "Flowers",
                                     "Trains",       "Planes",    "Candies",     "Boxes",      "Spoons",
                                     "Bananas",      "Tomatoes",  "Oranges",     "Shoes",      "Forks",
                                     "Hairs",        "Houses",    "Pebbles",     "Terriers",   "Pizzas",
                                     "Salmons",      "Eggs",      "Bushes",      "Guns",       "Skunks",
                                     "Toes",         "Fingers",   "Noses",       "Friends",    "Enemies",
                                     "Students"};

    private String[] adjectives_lt= {"Pikti",       "Gra????s",     "Pami????",       "Ting??s",    "Greiti",
                                     "Mir??",        "Laimingi",   "L??ti",         "Dideli",    "Ma??i",
                                     "Kvaili",      "Dvok??s",     "Lietuvi??ki",   "Bais??s",    "Nuobod??s",
                                     "Nusimin??",    "Protingi",   "Sunk??s",       "??ali",      "Raudoni",
                                     "Geltoni",     "M??lyni",     "Girti",        "Ro??iniai",  "Auk??ti",
                                     "??emi",        "Tikri",      "Putl??s",       "Nesveiki",  "Purvini",
                                     "??var??s",      "Mandr??s",    "Jauni",        "Blogi",     "Du",
                                     "Vieni??i"};

    private String[] nouns_lt=      {"??unys",       "Katinai",    "Arkliai",      "Jau??iai",   "??e??kai",
                                     "??iurk??nai",   "??ernai",     "E??eriai",      "Med??iai",   "Grizliai",
                                     "Labradorai",  "Gaid??iai",   "Vi????iukai",    "O??iai",     "??auk??tai",
                                     "Bananai",     "Pomidorai",  "Motociklai",   "Apelsinai", "Batai",
                                     "Cepelinai",   "Kugeliai",   "Plaukai",      "Namai",     "Akmenys",
                                     "Kiau??iai",    "Kr??mai",     "??autuvai",     "Pir??tai",   "Draugai",
                                     "Prie??ai",     "Studentai"};


    /// TEAM NAME ARRAYLISTS -----------------------------------------------------------------------

    ArrayList<String> adjectives_enn = new ArrayList<String>();
    ArrayList<String> adjectives_ltt = new ArrayList<String>();

    ArrayList<String> nouns_enn = new ArrayList<String>();
    ArrayList<String> nouns_ltt = new ArrayList<String>();


}
