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
package br.com.fidias.chance4j;

import br.com.fidias.chance4j.person.Cpf;
import br.com.fidias.chance4j.person.CpfOptions;
import br.com.fidias.chance4j.text.TextOptions;
import br.com.fidias.chance4j.text.Character;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.math3.random.MersenneTwister;
import org.apache.commons.math3.random.RandomDataGenerator;

/**
 *
 * @author Átila Camurça camurca.home@gmail.com
 */
public class Chance {

    public final static int MIN_SENTENCES_FOR_PARAGRAPH = 3;
    public final static int MAX_SENTENCES_FOR_PARAGRAPH = 7;
    public final static int MIN_WORDS_FOR_SENTENCE = 12;
    public final static int MAX_WORDS_FOR_SENTENCE = 18;
    public final static int MIN_SYLLABLES_FOR_WORD = 1;
    public final static int MAX_SYLLABLES_FOR_WORD = 3;
    public final static int MIN_CHARS_FOR_STRING = 5;
    public final static int MAX_CHARS_FOR_STRING = 20;
    public final static int MIN_CHAR_FOR_SYLLABLE = 2;
    public final static int MAX_CHAR_FOR_SYLLABLE = 3;

    private final RandomDataGenerator random;

    /**
     * Creates an instance of RandomDataGenerator with the Mersenne Twister
     * generator created by Apache Commons Math library.
     *
     * The Mersenne Twister is a pseudorandom number generator (PRNG). It is by
     * far the most widely used general-purpose PRNG.[1] Its name derives from
     * the fact that its period length is chosen to be a Mersenne prime.
     */
    public Chance() {
        this.random = new RandomDataGenerator(new MersenneTwister());
    }

    /**
     * Return a random integer.
     *
     * @param min Minimum value to choose from
     * @param max Maximum value to choose from
     * @return A single random integer number
     * @throws ChanceException Min cannot be greater than Max.
     */
    public int integer(int min, int max) throws ChanceException {
        if (min > max) {
            throw new ChanceException("Min cannot be greater than Max.");
        }
        return random.nextInt(min, max);
    }

    /**
     * Return a random integer.
     *
     * @return A single random integer number
     * @throws ChanceException
     */
    public int integer() throws ChanceException {
        return integer(Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public int natural() {
        int result = 0;
        try {
            result = natural(0, Integer.MAX_VALUE);
        } catch (ChanceException e) {
            // it's never throw
        }
        return result;
    }

    public int natural(int max) throws ChanceException {
        if (max <= 0) {
            throw new ChanceException("Max must be greater than zero.");
        }
        return natural(0, max);
    }

    /**
     * Return a random natural. NOTE the max and min are INCLUDED in the range.
     *
     * @param min Minimum value to choose from
     * @param max Maximum value to choose from
     * @return A single random integer number
     * @throws ChanceException min cannot be greater than max. Min cannot be
     * less than zero. Max must be greater than zero.
     */
    public int natural(int min, int max) throws ChanceException {
        if (min < 0) {
            throw new ChanceException("Min cannot be less than zero.");
        }
        if (max <= 0) {
            throw new ChanceException("Max must be greater than zero.");
        }
        if (max < min) {
            throw new ChanceException("Max must be greater than min.");
        }
        return integer(min, max);
    }

    /**
     * Return a random bool, either true or false.
     *
     * @param likelihood Alter the likelihood of receiving a true or false value
     * back.
     * @return Either true or false
     * @throws ChanceException if the likelihood is out of bounds
     */
    public boolean bool(double likelihood) throws ChanceException {
        if (likelihood < 0 || likelihood > 100) {
            throw new ChanceException("Likelihood accepts values from 0 to 100.");
        }
        return random.nextUniform(0, 100) < likelihood;
    }

    /**
     * Return a random bool, either true or false.
     *
     * @return Either true or false
     */
    public boolean bool() {
        boolean result = false;
        try {
            result = bool(50);
        } catch (ChanceException e) {
            // it's never throw
        }
        return result;
    }

    private float floating(Float min, Float max, int fixed) {
        throw new UnsupportedOperationException();
    }

    /**
     * Return a random floating point number.
     *
     * @param min Minimum value to choose from
     * @param max Maximum value to choose from
     * @param fixed Specify a fixed precision
     * @return A single floating point number
     * @throws ChanceException Min cannot be greater than Max.
     */
    public float floating(int min, int max, int fixed) throws ChanceException {
        int num;
        int localFixed = (int) Math.pow(10, fixed);
        int localMax;
        if (max <= -1) {
            localMax = (int) (Integer.MAX_VALUE / localFixed);
        } else {
            localMax = max;
        }
        int localMin;
        if (min <= -1) {
            localMin = -localMax;
        } else {
            localMin = min;
        }
        num = integer(localMin * localFixed, localMax * localFixed);
        float f = num * 1.0f / localFixed * 1.0f;
        BigDecimal result = new BigDecimal(f).setScale(fixed, RoundingMode.HALF_UP);
        return result.floatValue();
    }

    /**
     * Return a random floating point number.
     *
     * @return A single floating point number
     * @throws ChanceException
     */
    public float floating() throws ChanceException {
        return floating(-1, -1, 4);
    }

    /**
     * Return a random floating point number.
     *
     * @param fixed Specify a fixed precision
     * @return A single floating point number
     * @throws ChanceException
     */
    public float floating(int fixed) throws ChanceException {
        return floating(-1, -1, fixed);
    }

    /**
     * Return an array of random character.
     *
     * @param options Can specify a character pool, only alpha, only symbols,
     * and casing (lower or upper)
     * @param length size of the array
     * @return
     * @throws ChanceException
     */
    private char[] characters(TextOptions options, int length) throws ChanceException {
        Character character = new Character(options);
        final String text = character.getTextPool();
        String result = "";
        for (int i = 0; i < length; i++) {
            int natural = natural(text.length() - 1);
            result += text.charAt(natural);
        }
        return result.toCharArray();
    }

    /**
     * Return a random character.
     *
     * @param options Can specify a character pool, only alpha, only symbols,
     * and casing (lower or upper)
     * @return a single random character
     * @throws ChanceException
     */
    public char character(TextOptions options) throws ChanceException {
        char[] characters = characters(options, 1);
        return characters[0];
    }

    /**
     * Return a random string.
     *
     * @param options Can specify a character pool, only alpha, only symbols,
     * and casing (lower or upper)
     * @param length Specify a length
     * @return A string of especified length
     * @throws ChanceException
     */
    public String string(TextOptions options, int length) throws ChanceException {
        if (length <= 0) {
            throw new ChanceException("Length cannot be less or equal than zero.");
        }
        char[] characters = characters(options, length);
        return String.valueOf(characters);
    }

    /**
     * Return a random string.
     *
     * @param options Can specify a character pool, only alpha, only symbols,
     * and casing (lower or upper)
     * @return A string of random length
     * @throws ChanceException
     */
    public String string(TextOptions options) throws ChanceException {
        int length = natural(5, 20);
        char[] characters = characters(options, length);
        return String.valueOf(characters);
    }

    /**
     * Return a semi-speakable syllable, 2 or 3 letters.
     *
     * @return
     */
    public String syllable() {
        String result = "";
        TextOptions options = new TextOptions();
        options.setCasing(TextOptions.Casing.lower);
        options.setPoolType(TextOptions.PoolType.custom);
        try {
            int length = natural(MIN_CHAR_FOR_SYLLABLE, MAX_CHAR_FOR_SYLLABLE);
            char chr = 0;
            String pool;
            for (int i = 0; i < length; i++) {
                if (i == 0) {
                    pool = Character.CONSONANTS_AND_VOWELS;
                } else if (Character.CONSONANTS_GROUP.indexOf(chr) == -1) {
                    pool = Character.CONSONANTS_GROUP;
                } else {
                    pool = Character.VOWELS;
                }
                options.setPool(pool);
                chr = character(options);
                result += chr;
            }
        } catch (ChanceException e) {
            // it's never throw
        }
        return result;
    }

    /**
     * Return a semi-pronounceable random (nonsense) array of words.
     *
     * @param length
     * @return
     */
    private String[] words(int length) {
        String[] words = new String[length];
        for (int i = 0; i < length; i++) {
            words[i] = word();
        }
        return words;
    }

    /**
     * Return a semi-pronounceable random (nonsense) word.
     *
     * @param numSyllables
     * @param capitalize
     * @return
     */
    public String word(int numSyllables, boolean capitalize) {
        String result = "";
        for (int i = 0; i < numSyllables; i++) {
            result += syllable();
        }
        return (capitalize ? StringUtils.capitalize(result) : result);
    }

    /**
     * Return a semi-pronounceable random (nonsense) word.
     *
     * @param numSyllables
     * @return
     */
    public String word(int numSyllables) {
        return word(numSyllables, false);
    }

    /**
     * Return a semi-pronounceable random (nonsense) word.
     *
     * @return
     */
    public String word() {
        String result = "";
        try {
            int natural = natural(MIN_SYLLABLES_FOR_WORD, MAX_SYLLABLES_FOR_WORD);
            result = word(natural);
        } catch (Exception e) {
            // it's never throw
        }
        return result;
    }

    /**
     * Return a random array of sentences populated by semi-pronounceable random
     * (nonsense) words.
     *
     * @param length
     * @return
     * @throws ChanceException
     */
    private String[] sentences(int length) throws ChanceException {
        String[] sentences = new String[length];
        for (int i = 0; i < length; i++) {
            sentences[i] = sentence();
        }
        return sentences;
    }

    /**
     * Return a random sentence populated by semi-pronounceable random
     * (nonsense) words.
     *
     * @param numWords
     * @return
     * @throws ChanceException
     */
    public String sentence(int numWords) throws ChanceException {
        String[] words = words(numWords);
        String join = StringUtils.join(words, " ");
        String result = StringUtils.capitalize(join);
        TextOptions options = new TextOptions();
        options.setPoolType(TextOptions.PoolType.custom);
        options.setPool(Character.PUNCTUATION);
        return result + character(options);
    }

    /**
     * Return a random sentence populated by semi-pronounceable random
     * (nonsense) words.
     *
     * @return
     * @throws ChanceException
     */
    public String sentence() throws ChanceException {
        int numWords = natural(MIN_WORDS_FOR_SENTENCE, MAX_WORDS_FOR_SENTENCE);
        return sentence(numWords);
    }

    /**
     * Return a random paragraph generated from sentences populated by
     * semi-pronounceable random (nonsense) words.
     *
     * @param numSentences
     * @return
     * @throws ChanceException
     */
    public String paragraph(int numSentences) throws ChanceException {
        String[] sentences = sentences(numSentences);
        return StringUtils.join(sentences, " ");
    }

    /**
     * Return a random paragraph generated from sentences populated by
     * semi-pronounceable random (nonsense) words.
     *
     * @return
     * @throws ChanceException
     */
    public String paragraph() throws ChanceException {
        int numSentences = natural(MIN_SENTENCES_FOR_PARAGRAPH, MAX_SENTENCES_FOR_PARAGRAPH);
        return paragraph(numSentences);
    }

    /**
     * Return a random valid Brazilian CPF.
     *
     * @return A random CPF
     * @throws ChanceException
     */
    public long cpf() throws ChanceException {
        int[] firstNineDigits = new int[9];
        int length = firstNineDigits.length;
        for (int i = 0; i < length; i++) {
            firstNineDigits[i] = natural(9);
        }

        int d1 = Cpf.calculateVerifyingDigitOne(firstNineDigits);
        int d2 = Cpf.calculateVerifyingDigitTwo(firstNineDigits, d1);

        StringBuilder numberRepresentation = new StringBuilder();
        for (int i = 0; i < length; i++) {
            numberRepresentation.append(firstNineDigits[i]);

        }
        numberRepresentation.append(d1);
        numberRepresentation.append(d2);
        return Long.parseLong(numberRepresentation.toString());
    }

    /**
     * Return a random valid Brazilian CPF, either unmasked (00000000000) or
     * masked (000.000.000-00).
     *
     * @param options
     * @return A random valid CPF
     * @throws ChanceException
     */
    public String cpfAsText(CpfOptions options) throws ChanceException {
        long cpf = cpf();
        String unmasked = new DecimalFormat("00000000000").format(cpf);
        switch (options) {
            case masked:
                return Cpf.format(unmasked);
            case unmasked:
                return unmasked;
            default:
                throw new AssertionError();
        }
    }

    /**
     * Return a random valid masked Brazilian CPF.
     *
     * @return
     * @throws ChanceException
     */
    public String cpfAsText() throws ChanceException {
        return cpfAsText(CpfOptions.masked);
    }
}
