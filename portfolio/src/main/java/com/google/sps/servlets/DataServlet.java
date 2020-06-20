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

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.gson.Gson;
import com.google.sps.data.Comment;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Servlet that returns some example content. TODO: modify this file to handle comments data */
@WebServlet("/data")
public class DataServlet extends HttpServlet {

private List<String> comments;

  @Override
  public void init() {
    comments = new ArrayList<>();
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    Query query = new Query("Comment").addSort("timestamp", SortDirection.DESCENDING);
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();    
    PreparedQuery results = datastore.prepare(query);
    List<Comment> commentsList = new ArrayList<>();
    for (Entity entity : results.asIterable()) {
        long id = entity.getKey().getId();
        String comment = (String) entity.getProperty("comment");
        long timestamp = (long) entity.getProperty("timestamp");

        Comment com = new Comment(id, comment, timestamp);
        commentsList.add(com);
    }

    Gson gson = new Gson();
    response.setContentType("application/json;");
    response.getWriter().println(gson.toJson(commentsList));
    
    // String commentJson = convertToJsonUsingGson(comments);
    // response.setContentType("text/html;");
    // response.getWriter().println(commentJson);
  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    String commentLeft = getCommentLeft(request);
    //Warn about invalid comment
    if (commentLeft == null) {
        response.sendRedirect("/index.html");
        // response.setContentType("text/html");
        // response.getWriter().println("Please enter a valid comment");
    } else {
        comments.add(commentLeft);
        long timestamp = System.currentTimeMillis();

        Entity taskEntity = new Entity("Comment");
        taskEntity.setProperty("comment", commentLeft);
        taskEntity.setProperty("timestamp", timestamp);

        datastore.put(taskEntity);

        response.sendRedirect("/index.html");
  }}

  /** Returns the choice entered by the player, or -1 if the choice was invalid. */
  private String getCommentLeft(HttpServletRequest request) {
    // Get the input from the form.
    String commentLeftString = request.getParameter("comment-left");
    
    // Check for a valid input.
    if (commentLeftString.equals("")) {
        return null;
    } else{
       return commentLeftString; 
    }
  }

  /**
   * Converts a ServerStats instance into a JSON string using manual String concatentation.
   */
  private String convertToJson(List<String> arr) {
    String json = "{";
    json += "\"comments\": ";
    for (int i=0; i < arr.size(); i++) {
        json += arr.get(i);
        if (i < arr.size()- 1) {
            json += ", ";
        }
    }
    json += "}";
    System.out.println(json);
    return json;
  }

  /**
   * Converts a ServerStats instance into a JSON string using the Gson library. Note: We first added
   * the Gson library dependency to pom.xml.
   */
  private String convertToJsonUsingGson(List<String> comments) {
    Gson gson = new Gson();
    String json = gson.toJson(comments);
    return json;
  } 
  }

  
