/******************************************************************************/
/* karel_the_robot_java_frontend                                              */
/* Copyright (C) 2021  Hendrik Boeck <hendrikboeck.dev@protonmail.com>        */
/*                                                                            */
/* This program is free software: you can redistribute it and/or modify       */
/* it under the terms of the GNU General Public License as published by       */
/* the Free Software Foundation, either version 3 of the License, or          */
/* (at your option) any later version.                                        */
/*                                                                            */
/* This program is distributed in the hope that it will be useful,            */
/* but WITHOUT ANY WARRANTY; without even the implied warranty of             */
/* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the              */
/* GNU General Public License for more details.                               */
/*                                                                            */
/* You should have received a copy of the GNU General Public License          */
/* along with this program.  If not, see <http://www.gnu.org/licenses/>.      */
/******************************************************************************/

package com.github.hendrikboeck.karel.examples;

import com.github.hendrikboeck.karel.Karel;

public class TestRoom extends Karel {

  public TestRoom() {
    loadWorld("TestRoom");

    move();
    turnLeft();

    while (frontIsClear()) move();

    while (frontIsBlocked()) {
      turnLeft();
      move();
      turnRight();
    }

    var counter = 0;
    while (frontIsClear()) {
      while (beeperPresent()) {
        pickBeeper();
        counter++;
      }
      move();
    }

    while(counter > 0) {
      putBeeper();
      counter--;
    }
  }

  public void turnRight() {
    turnLeft();
    turnLeft();
    turnLeft();
  }

}