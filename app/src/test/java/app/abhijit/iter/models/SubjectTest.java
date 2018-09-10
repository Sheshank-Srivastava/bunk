/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 Abhijit Parida <abhijitparida.me@gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package app.abhijit.iter.models;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SubjectTest {

    @Test
    public void getName_SimpleValue_ReturnsCorrectly() {
        Subject subject = new Subject("name", "code", 0, 1, 2, 3, 4);
        assertEquals("name", subject.getName());
    }

    @Test
    public void getCode_SimpleValue_ReturnsCorrectly() {
        Subject subject = new Subject("name", "code", 0, 1, 2, 3, 4);
        assertEquals("code", subject.getCode());
    }

    @Test
    public void getLastUpdated_SimpleValue_ReturnsCorrectly() {
        Subject subject = new Subject("name", "code", 0, 1, 2, 3, 4);
        assertEquals(0, subject.getLastUpdated());
    }

    @Test
    public void getPresent_SimpleValue_ReturnsCorrectly() {
        Subject subject = new Subject("name", "code", 0, 1, 2, 3, 4);
        assertEquals(4, subject.getPresent());
    }

    @Test
    public void getAbsent_SimpleValue_ReturnsCorrectly() {
        Subject subject = new Subject("name", "code", 0, 1, 2, 3, 4);
        assertEquals(2, subject.getAbsent());
    }

    @Test
    public void getLabPresent_SimpleValue_ReturnsCorrectly() {
        Subject subject = new Subject("name", "code", 0, 1, 2, 3, 4);
        assertEquals(1, subject.getLabPresent());
    }

    @Test
    public void getLabTotal_SimpleValue_ReturnsCorrectly() {
        Subject subject = new Subject("name", "code", 0, 1, 2, 3, 4);
        assertEquals(2, subject.getLabTotal());
    }

    @Test
    public void getTheoryPresent_SimpleValue_ReturnsCorrectly() {
        Subject subject = new Subject("name", "code", 0, 1, 2, 3, 4);
        assertEquals(3, subject.getTheoryPresent());
    }

    @Test
    public void getTheoryTotal_SimpleValue_ReturnsCorrectly() {
        Subject subject = new Subject("name", "code", 0, 1, 2, 3, 4);
        assertEquals(4, subject.getTheoryTotal());
    }

    @Test
    public void getAttendance_SimpleValue_ReturnsCorrectly() {
        Subject subject = new Subject("name", "code", 0, 0, 0, 18, 22);
        assertEquals(81.81, subject.getAttendance(), 0.1);
    }

    @Test
    public void getAttendance_ZeroValues_HandlesGracefullyWithoutThrowingExceptions() {
        Subject subject = new Subject("name", "code", 0, 0, 0, 0, 0);
        assertEquals(0.0, subject.getAttendance(), 0.1);
    }

    @Test
    public void getBunkStats_NumberOfTotalClassesIsSmall_DoesNotContainRepeatedStats() {
        Subject subject = new Subject("name", "code", 0, 0, 0, 9, 9);

        String bunkStats = "";
        bunkStats += "Bunk 3 classes for 75% attendance\n";
        bunkStats += "Bunk 2 classes for 80% attendance\n";
        bunkStats += "Bunk 1 class for 85% attendance";

        assertEquals(bunkStats, subject.getBunkStats(75, true));
    }

    @Test
    public void getBunkStats_NumberOfTotalClassesIsLarge_DoesNotContainRepeatedStats() {
        Subject subject = new Subject("name", "code", 0, 0, 0, 17, 20);

        String bunkStats = "";
        bunkStats += "Bunk 2 more classes for 75% attendance\n";
        bunkStats += "Bunk 1 more class for 80% attendance\n";
        bunkStats += "Need 10 more classes for 90% attendance";

        assertEquals(bunkStats, subject.getBunkStats(75, true));
    }

    @Test
    public void getBunkStats_ZeroTotalClasses_GeneratesEmptyStats() {
        Subject subject = new Subject("name", "code", 0, 0, 0, 0, 0);

        assertEquals("", subject.getBunkStats(75, true));
    }

    @Test
    public void getBunkStats_LessThanMinimumAttendance_GeneratesDontBunkWarning() {
        Subject subject = new Subject("name", "code", 0, 0, 0, 1, 10);

        String bunkStats = "";
        bunkStats += "DO NOT BUNK ANY MORE CLASSES\n";
        bunkStats += "Need 12 more classes for 60% attendance\n";
        bunkStats += "Need 15 more classes for 65% attendance\n";
        bunkStats += "Need 20 more classes for 70% attendance\n";
        bunkStats += "Need 26 more classes for 75% attendance\n";
        bunkStats += "Need 35 more classes for 80% attendance";

        assertEquals(bunkStats, subject.getBunkStats(60, true));
    }

    @Test
    public void getBunkStats_ZeroAttendance_GeneratesDontBunkWarning() {
        Subject subject = new Subject("name", "code", 0, 0, 0, 0, 35);

        String bunkStats = "";
        bunkStats += "DO NOT BUNK ANY MORE CLASSES";

        assertEquals(bunkStats, subject.getBunkStats(75, true));
    }

    @Test
    public void getBunkStats_ExtendedStatsFalse_GeneratesMaxTwoLines() {
        Subject subject = new Subject("name", "code", 0, 0, 0, 17, 20);

        String bunkStats = "";
        bunkStats += "Bunk 1 more class for 80% attendance\n";
        bunkStats += "Need 10 more classes for 90% attendance";

        assertEquals(bunkStats, subject.getBunkStats(75, false));
    }
}
