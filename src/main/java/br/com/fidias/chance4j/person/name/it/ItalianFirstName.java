/**
 * Chance4j is a minimalist generator of random strings, numbers, etc. to
 * help reduce some monotony particularly while writing automated tests or
 * anywhere else you need anything random.
 * Based on the <http://chancejs.com> by Victor Quinn and contributors
 *
 * Copyright (C) 2016 Átila Camurça <camurca.home@gmail.com>
 * Fidias Free Source Team <fidiascom@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package br.com.fidias.chance4j.person.name.it;

import br.com.fidias.chance4j.person.Gender;
import java.util.EnumMap;
import java.util.Map;

/**
 *
 * @author atila
 */
public class ItalianFirstName {
    
    private final static Map<Gender, String[]> NAMES = new EnumMap(Gender.class);
    
    public static String[] getNames(Gender gender) {
        if (NAMES.isEmpty()) {
            NAMES.put(Gender.Female, FEMALE_NAMES);
            NAMES.put(Gender.Male, MALE_NAMES);
        }
        return NAMES.get(gender);
    }
    
    private final static String[] FEMALE_NAMES = new String[] {
        "Ada",
        "Adriana",
        "Alessandra",
        "Alessia",
        "Alice",
        "Angela",
        "Anna",
        "Annalisa",
        "Anna Maria",
        "Annita",
        "Annunziata",
        "Antonella",
        "Arianna",
        "Asia",
        "Assunta",
        "Aurora",
        "Barbara",
        "Beatrice",
        "Benedetta",
        "Bianca",
        "Bruna",
        "Camilla",
        "Carla",
        "Carlotta",
        "Carmela",
        "Carolina",
        "Caterina",
        "Catia",
        "Cecilia",
        "Chiara",
        "Cinzia",
        "Clara",
        "Claudia",
        "Costanza",
        "Cristina",
        "Daniela",
        "Debora",
        "Diletta",
        "Dina",
        "Donatella",
        "Elena",
        "Eleonora",
        "Elisa",
        "Elisabetta",
        "Emanuela",
        "Emma",
        "Eva",
        "Federica",
        "Fernanda",
        "Fiorella",
        "Fiorenza",
        "Flora",
        "Franca",
        "Francesca",
        "Gabriella",
        "Gaia",
        "Gemma",
        "Giada",
        "Gianna",
        "Gina",
        "Ginevra",
        "Giorgia",
        "Giovanna",
        "Giulia",
        "Giuliana",
        "Giuseppa",
        "Giuseppina",
        "Grazia",
        "Graziella",
        "Greta",
        "Ida",
        "Ilaria",
        "Ines",
        "Iolanda",
        "Irene",
        "Irma",
        "Isabella",
        "Jessica",
        "Laura",
        "Leda",
        "Letizia",
        "Licia",
        "Lidia",
        "Liliana",
        "Lina",
        "Linda",
        "Lisa",
        "Livia",
        "Loretta",
        "Luana",
        "Lucia",
        "Luciana",
        "Lucrezia",
        "Luisa",
        "Manuela",
        "Mara",
        "Marcella",
        "Margherita",
        "Maria",
        "Maria Cristina",
        "Maria Grazia",
        "Maria Luisa",
        "Maria Pia",
        "Maria Teresa",
        "Marina",
        "Marisa",
        "Marta",
        "Martina",
        "Marzia",
        "Matilde",
        "Melissa",
        "Michela",
        "Milena",
        "Mirella",
        "Monica",
        "Natalina",
        "Nella",
        "Nicoletta",
        "Noemi",
        "Olga",
        "Paola",
        "Patrizia",
        "Piera",
        "Pierina",
        "Raffaella",
        "Rebecca",
        "Renata",
        "Rina",
        "Rita",
        "Roberta",
        "Rosa",
        "Rosanna",
        "Rossana",
        "Rossella",
        "Sabrina",
        "Sandra",
        "Sara",
        "Serena",
        "Silvana",
        "Silvia",
        "Simona",
        "Simonetta",
        "Sofia",
        "Sonia",
        "Stefania",
        "Susanna",
        "Teresa",
        "Tina",
        "Tiziana",
        "Tosca",
        "Valentina",
        "Valeria",
        "Vanda",
        "Vanessa",
        "Vanna",
        "Vera",
        "Veronica",
        "Vilma",
        "Viola",
        "Virginia",
        "Vittoria",
    };
    
    private final static String[] MALE_NAMES = new String[] {
        "Adolfo",
        "Alberto",
        "Aldo",
        "Alessandro",
        "Alessio",
        "Alfredo",
        "Alvaro",
        "Andrea",
        "Angelo",
        "Angiolo",
        "Antonino",
        "Antonio",
        "Attilio",
        "Benito",
        "Bernardo",
        "Bruno",
        "Carlo",
        "Cesare",
        "Christian",
        "Claudio",
        "Corrado",
        "Cosimo",
        "Cristian",
        "Cristiano",
        "Daniele",
        "Dario",
        "David",
        "Davide",
        "Diego",
        "Dino",
        "Domenico",
        "Duccio",
        "Edoardo",
        "Elia",
        "Elio",
        "Emanuele",
        "Emiliano",
        "Emilio",
        "Enrico",
        "Enzo",
        "Ettore",
        "Fabio",
        "Fabrizio",
        "Federico",
        "Ferdinando",
        "Fernando",
        "Filippo",
        "Francesco",
        "Franco",
        "Gabriele",
        "Giacomo",
        "Giampaolo",
        "Giampiero",
        "Giancarlo",
        "Gianfranco",
        "Gianluca",
        "Gianmarco",
        "Gianni",
        "Gino",
        "Giorgio",
        "Giovanni",
        "Giuliano",
        "Giulio",
        "Giuseppe",
        "Graziano",
        "Gregorio",
        "Guido",
        "Iacopo",
        "Jacopo",
        "Lapo",
        "Leonardo",
        "Lorenzo",
        "Luca",
        "Luciano",
        "Luigi",
        "Manuel",
        "Marcello",
        "Marco",
        "Marino",
        "Mario",
        "Massimiliano",
        "Massimo",
        "Matteo",
        "Mattia",
        "Maurizio",
        "Mauro",
        "Michele",
        "Mirko",
        "Mohamed",
        "Nello",
        "Neri",
        "Niccolò",
        "Nicola",
        "Osvaldo",
        "Otello",
        "Paolo",
        "Pier Luigi",
        "Piero",
        "Pietro",
        "Raffaele",
        "Remo",
        "Renato",
        "Renzo",
        "Riccardo",
        "Roberto",
        "Rolando",
        "Romano",
        "Salvatore",
        "Samuele",
        "Sandro",
        "Sergio",
        "Silvano",
        "Simone",
        "Stefano",
        "Thomas",
        "Tommaso",
        "Ubaldo",
        "Ugo",
        "Umberto",
        "Valerio",
        "Valter",
        "Vasco",
        "Vincenzo",
        "Vittorio",
    };
}