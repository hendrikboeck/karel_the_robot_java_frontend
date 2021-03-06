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

package com.github.hendrikboeck.karel;

public class Pipe {

  public static String HOST = "127.0.0.1";
  public static int PORT = 14480;

  private static Pipe instance;

  private int id;
  private TCPClient client;

  private Pipe() {
    id = 0;
    client = new TCPClient();
    client.connect(HOST, PORT);
  }

  public static Pipe getInstance() {
    if (instance == null) instance = new Pipe();
    return instance;
  }

  public void send(String data) {
    client.send(data);
  }

  public String recv() {
    return client.recv();
  }

  public void close() {
    client.close();
  }

  public int getCID() {
    return id++;
  }

  

}
