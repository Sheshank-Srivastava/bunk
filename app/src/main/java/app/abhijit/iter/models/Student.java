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

import android.support.annotation.NonNull;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

/**
 * This class represents a student.
 */
public class Student {

    private final String username;
    private final String password;
    private final String name;
    private final TreeMap<String, Subject> subjects;

    public Student(@NonNull String username, @NonNull String password, @NonNull String name,
                   @NonNull Subject subjects[]) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.subjects = new TreeMap<>();
        for (Subject subject : subjects) {
            this.subjects.put(subject.getCode(), subject);
        }
    }

    @NonNull
    public String getUsername() {
        return this.username;
    }

    @NonNull
    public String getPassword() {
        return this.password;
    }

    @NonNull
    public String getName() {
        return this.name;
    }

    @NonNull
    public Map<String, Subject> getSubjects() {
        return Collections.unmodifiableSortedMap(this.subjects);
    }
}
