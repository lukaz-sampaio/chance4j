/**
 * Chance4j is a minimalist generator of random strings, numbers, etc. to
 * help reduce some monotony particularly while writing automated tests or
 * anywhere else you need anything random.
 * Based on the <http://chancejs.com> by Victor Quinn and contributors
 *
 * Copyright (C) 2016 Átila Camurça <camurca.home@gmail.com>
 * Fidias Free and Open Source Team <fidiascom@gmail.com>
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
package br.com.fidias.chance4j.person;

import br.com.fidias.chance4j.AbstractChanceTesting;
import br.com.fidias.chance4j.ChanceException;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

/**
 * Implementation based on <https://github.com/danielfariati/cpf-cnpj-validator>
 *
 * @author atila
 */
public class CpfTest extends AbstractChanceTesting {

    private String cpf;

    @Test
    public void randomValidCpf() {
        for (int i = 0; i < 1000; i++) {
            cpf = chance.cpfAsText(CpfOptions.unmasked);
            assertTrue("random valid cpf", isValid(cpf));
        }
    }

    @Test
    public void randomValidMaskedCpf() {
        for (int i = 0; i < 1000; i++) {
            cpf = chance.cpfAsText(CpfOptions.masked);
            assertTrue("random valid cpf", isMasked(cpf));
        }
    }
    
    @Test(expected = ChanceException.class)
    public void wrongNumberOfValuesOnDigitOne() throws ChanceException {
        int[] values = new int[10];
        Cpf.calculateVerifyingDigitOne(values);
    }
    
    @Test(expected = ChanceException.class)
    public void wrongNumberOfValuesOnDigitTwo() throws ChanceException {
        int[] values = new int[10];
        Cpf.calculateVerifyingDigitTwo(values, 0);
    }

    private static boolean isValid(String cpf) {
        int i;
        int j;
        int digit;
        int coeficient;
        int sum;
        int[] foundDv = {0, 0};

        int dv1 = Integer.parseInt(String.valueOf(cpf.charAt(cpf.length() - 2)));
        int dv2 = Integer.parseInt(String.valueOf(cpf.charAt(cpf.length() - 1)));

        for (j = 0; j < 2; j++) {
            sum = 0;
            coeficient = 2;

            for (i = cpf.length() - 3 + j; i >= 0; i--) {
                digit = Integer.parseInt(String.valueOf(cpf.charAt(i)));
                sum += digit * coeficient;
                coeficient++;
            }

            foundDv[j] = 11 - sum % 11;

            if (foundDv[j] >= 10) {
                foundDv[j] = 0;
            }
        }

        return dv1 == foundDv[0] && dv2 == foundDv[1];
    }

    private boolean isMasked(String cpf) {
        return cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}");
    }
}
