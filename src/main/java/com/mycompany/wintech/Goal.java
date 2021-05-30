package com.mycompany.wintech;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.io.Serializable;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
/*
 * The MIT License
 *
 * Copyright 2021 Thomas.
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

/**
 *
 * @author Thomas
 */
public class Goal implements Serializable {
  private final int minutes;
  private final String name;
  private boolean finished;
  private String completed;

    /**
     *
     * @param i
     * @param m
     * @param n
     * @param o
     */
    public Goal(int m, String n, String o){
    minutes = m;
    name = n;
    completed = o;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    LocalDateTime now = LocalDateTime.now();
    finished = completed == null ? dtf.format(now) == null : completed.equals(dtf.format(now));
  }
  public int getMinutes(){
    return minutes;
  }
  public String getName(){
    return name;
  }
  public boolean getFinished(){
    return finished;
  }
  public String getCompleted(){
    return completed;
  }

  @Override
  public String toString() {
      return minutes + "" + name + completed;
  }
}
