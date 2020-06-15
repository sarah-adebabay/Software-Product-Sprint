// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.sps.servlets;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/** Servlet that returns some example content. TODO: modify this file to handle comments data */
@WebServlet("/data")
public class DataServlet extends HttpServlet {

private List<String> comments;

  @Override
  public void init() {
    comments = new ArrayList<>();
    comments.add("First");
    comments.add("Second");
    comments.add("Third");
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String commentJson = convertToJson(comments);

    response.setContentType("text/html;");
    response.getWriter().println(commentJson);
  }

  /**
   * Converts a ServerStats instance into a JSON string using manual String concatentation.
   */
  private String convertToJson(List<String> arr) {
    String json = "{";
    json += "\"comments\": ";
    json += arr.get(0);
    json += ", ";
    json += arr.get(1);
    json += ", ";
    json += arr.get(1);
    json += "}";
    System.out.println(json);
    return json;
  } 
  }

  
