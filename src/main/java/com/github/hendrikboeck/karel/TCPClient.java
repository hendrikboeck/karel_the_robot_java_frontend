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

import java.io.*;
import java.net.Socket;

public class TCPClient {

  private static final int MAX_PACKET_SIZE = 65535;
  private Socket sock;

  public TCPClient() {
    sock = null;
  }

  public void connect(String ip, int port) {
    try {
      sock = new Socket(ip, port);
    } catch (IOException e) {
      System.err.println("ERROR: could not create socket: " + e.toString());
      System.exit(1);
    }
  }

  public void send(String data) {
    try {
      var writer = new PrintWriter(new OutputStreamWriter(sock.getOutputStream()));
      writer.print(data);
      writer.flush();
    } catch (IOException e) {
      System.err.println("ERROR: could not send to server: " + e.toString());
      System.exit(1);
    }
  }

  public String recv() {
    try {
      var reader = new BufferedReader(new InputStreamReader(sock.getInputStream()));
      var buff = new char[MAX_PACKET_SIZE];
      var numRecvBytes = reader.read(buff, 0, MAX_PACKET_SIZE);
      return new String(buff, 0, numRecvBytes);
    } catch (IOException e) {
      System.err.println("ERROR: could not recv from server: " + e.toString());
      System.exit(1);
    }
    return null;
  }

  public void close() {
    try {
      sock.close();
    } catch (IOException e) {
      System.err.println("ERROR: could not close socket: " + e.toString());
    }
  }

}
