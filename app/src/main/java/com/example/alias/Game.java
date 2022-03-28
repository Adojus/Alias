package com.example.alias;

import java.util.ArrayList;

public class Game {
    public static boolean is_english=true;
    public static int difficulty=1;//1- junior; 2- medium; 3-godlike
    int num_of_teams = Team_Count_3.num_of_teams;
    public static String[] words_junior_lt = {"Kaukė",        "Citrina",      "Užtvanka",     "Medus",
                                "Kiaulė",       "Rašysena",     "Kaklas",       "Arbatinukas",
                                "Arbūzas",      "Žingsnis",     "Instrukcija",  "Mėdaus mėnuo",
                                "Klasė",        "Inžinierius",  "Sodas",        "Dangtelis",
                                "Atspindys",    "Renginys",     "Pilis",        "Prietaisas",
                                "Platukas",     "Šokoladas",    "Kvėpavimas",   "Tinklinis",
                                "Galaktika",    "Svogūnas",     "Kaulas",       "Užsakymas",
                                "Kritika",      "Pabėgti",      "Sąrašas",      "Variklis",
                                "Liežuvis",     "Vėžlys",       "Auklė",        "Triušis",
                                "Drakonas",     "Kalnas",       "Valgiaraštis", "Dėžė",
                                "Kėdė",         "Aušra",        "Pirštinė",     "Pilnametis",
                                "Ateitis",      "Gerbėjas",     "Pelėda",       "Grybas",
                                "Stresas",      "Klubas",       "Užkandis",     "Pagalvė",
                                "Eilė",         "Planas",       "Cirkas",       "Kodas",
                                "Linija",       "Svetainė",     "Pietūs",       "Gyvatė"};

    public static String[] words_junior_en = {"Mask",         "Lemon",        "Embankment",   "Honey",
                                "Cow",          "Handwriting",  "Neck",         "Teapot",
                                "Watermelon",   "Footstep",     "Instruction",  "Honeymoon",
                                "Classroom",    "Engineer",     "Garden",       "Lid",
                                "Reflection",   "Event",        "Bone",         "Device",
                                "Hammer",       "Chocolate",    "List",         "Voleyball",
                                "Galaxy",       "Onion",        "Babysitter",   "Order",
                                "Judge",        "Escape",       "Menu",         "Engine",
                                "Tangue",       "Turtle",       "Glove",        "Rabbit",
                                "Dragon",       "Mountain",     "Owl",          "Box",
                                "Chair",        "Dawn",         "Snack",        "Adult",
                                "Future",       "Fan",          "Circus",       "Mushroom",
                                "Stress",       "Club",         "Lunch",        "Pillow",
                                "Queue",        "Plan",         "Castle",       "Code",
                                 "Line",         "Web",         "Breathing",    "Snake"};

    public static String[] words_medium_lt = {"Liokajus",         "Šturmanas",        "Išdavystė",
                                "Našta",            "Migracija",        "Kalakutas",
                                "Pakartojimas",     "Guma",             "Mantija",
                                "Čiužinys",         "Sutrikimas",       "Santykis",
                                "Dokumencija",      "Mineralas",        "Įlanka",
                                "Korespondentas",   "Tardymas",         "Nekantrumas",
                                "Katedra",          "Pratarmė",         "Prevencija",
                                "Budrumas",         "Gerovė",           "Intelektas",
                                "Rūkas",            "Vėjas",            "Mėlynė",
                                "Gidas",            "Švyturys",         "Pasiūlymas",
                                "Fleita",           "Alergija",         "Riba",
                                "Teisingumas",      "Sąsaga",           "Kalorija",
                                "Nuostaba",         "Įprotis",          "Šimpanzė",
                                "Klestėjimas",      "Lankomumas",       "Sudėtingumas",
                                "Diagrama",         "Nelaimė",          "Vaiduoklis",
                                "Angelas",          "Atsarginė kopija", "Grąžtas"};

    public static String[] words_medium_en = {"Butler",           "Navigator",        "Betrayal",
                                "Burden",           "Migration",        "Turkey",
                                "Repetition",       "Rubber",           "Mantle",
                                "Mattress",         "Sutrikimas",       "Relation",
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
                                "Angel",            "Backup",           "Drill"};

    public static String[] words_senior_lt = {"Vikrumas",          "Saugotojas",      "Filharmonija",
                                "Atrologija",        "Valda",           "Terariumas",
                                "Gynėjas",           "Antena",          "Pramoga",
                                "Poliglotas",        "Vaisingumas",     "Samurajus",
                                "Finansai",          "Elektronas",      "Jakas",
                                "Respublika",        "Karūnavimas",     "Klubas",
                                "Šurmulys",          "Įvertinimas",     "Vanagas",
                                "Gausa",             "Vartininkas",     "Grizlis",
                                "Neigimas",          "Kvota",           "Klarnetas",
                                "Ginčas",            "Tvarumas",        "Sagtis",
                                "Ekvilibristas",     "Gyvybingumas",    "Puma",
                                "Lokatorius",        "Sąjunga",         "Eržilas",
                                "Prižiūrėtojas",     "Frakas",          "Ekscelencija"};

    public static String[] words_senior_en = {"Dexterity",        "Protector",        "Philharmonic",
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
                                "Caretaker",        "Tailcoat",         "Excellency"};


}
