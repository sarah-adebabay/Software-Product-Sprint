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

/**
 * Adds a random greeting to the page.
 */
function addRandomGreeting() {
  const greetings = 
    ['I enjoy traveling!', 'I enjoy learning about skincare and multimedia communication.',
     'My favorite musical artist is Rihanna.', 'I love boba so much, but I decided to give it up boba this year!'];

  // Pick a random greeting.
  const greeting = greetings[Math.floor(Math.random() * greetings.length)];

  // Add it to the page.
  const greetingContainer = document.getElementById('greeting-container');
  greetingContainer.innerText = greeting;
}

/**
 * Adds my name to the page.
 */
function getName() {
    console.log('Getting Name');
    // The fetch() function returns a Promise because the request is asynchronous.
    const responsePromise = fetch('/data');
    // When the request is complete, pass the response into handleResponse().
    responsePromise.then(handleNameResponse);
}   

function handleNameResponse(response) {
    console.log('Handling the response.');

    // response.text() returns a Promise, because the response is a stream of
    // content and not a simple variable.
    const textPromise = response.text();

    // When the response is converted to text, pass the result into the
    // addQuoteToDom() function.
    textPromise.then(addQuoteToDom);
}

/** Adds a random quote to the DOM. */
function addQuoteToDom(quote) {
  console.log('Adding Sarah to page:');

  const nameContainer = document.getElementById('name-container');
  
  nameContainer.innerText = fetch('/my-data-url')  // sends a request to /my-data-url
    .then(response => response.json()) // parses the response as JSON
    .then((myObject) => { // now we can reference the fields in myObject!
    console.log(myObject.x);
    console.log(myObject.y);
    console.log(myObject.z);
    });;
}

/**
 * Gets the comments to display to the page.
 */
function getComments() {
    console.log('Getting Comments');
    // The fetch() function returns a Promise because the request is asynchronous.
    const responsePromise = fetch('/data');
    // When the request is complete, pass the response into handleResponse().
    responsePromise.then(handleResponse);
}   

function handleResponse(response) {
    console.log('Handling the response.');

    // response.text() returns a Promise, because the response is a stream of
    // content and not a simple variable.
    const textPromise = response.text();

    // When the response is converted to text, pass the result into the
    // addQuoteToDom() function.
    textPromise.then(addCommentToDom);
}

/** Adds a random quote to the DOM. */
function addCommentToDom(comment) {
  console.log('Adding comments to page:');
  const nameContainer = document.getElementById('comments-container');
  nameContainer.innerText = comment;
}




