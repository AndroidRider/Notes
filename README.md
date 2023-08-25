# Notes App
The Notes App is a simple application that allows users to create, view, update, and delete notes. 
Users can organize their thoughts, ideas, and tasks using this app. 
The app follows the MVVM (Model-View-ViewModel) architectural pattern and utilizes the Android Navigation Component for smooth navigation between different screens.

# Screenshot
<p>
  <img src="https://user-images.githubusercontent.com/140700822/263239140-ad23f34b-e37b-4332-8452-116ff18fc82c.png" alt="feed example" width = "200" >
  &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
  <img src="https://user-images.githubusercontent.com/140700822/263239194-f0338e7a-b27a-486a-be67-352f8ff0c291.png" alt="feed example" width = "200" >
  &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
  <img src="https://user-images.githubusercontent.com/140700822/263239220-ceacabc0-6cb9-4e73-a3b6-e1c7f8e1f946.png" alt="feed example" width = "200" >
  &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
  <img src="https://user-images.githubusercontent.com/140700822/263239251-90a4acf0-fa89-4b2a-921a-ebc2a2652ef6.png" alt="feed example" width = "200" >
  &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
  <img src="https://user-images.githubusercontent.com/140700822/263239268-a9bc75bd-47ea-4915-adec-26ca58bf8776.png" alt="feed example" width = "200" >
  &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
  <img src="https://user-images.githubusercontent.com/140700822/263239330-670d4dfc-9355-4add-b24b-64ca07d48be6.png" alt="feed example" width = "200" >
  &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
  <img src="https://user-images.githubusercontent.com/140700822/263239395-07cc39fa-ea91-4c32-9f56-cdc18fd100bd.png" alt="feed example" width = "200" >
  &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
  <img src="https://user-images.githubusercontent.com/140700822/263239426-5b94939e-9f36-4dde-8c33-091132b8747e.png" alt="feed example" width = "200" >
  &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
  <img src="https://user-images.githubusercontent.com/140700822/263239473-53b92a43-399d-45aa-99ec-d98f3ffc199e.png" alt="feed example" width = "200" >
</p>

# Features:
<p>1. <b>Create Note:</b> Users can create a new note by providing a title and content. After entering the details, they can save the note.</p>
<p>2. <b>View Notes:</b> The app displays a list of all saved notes on the main screen. Users can tap on a note to view its details.</p>
<p>3. <b>Update Note:</b> Users can edit the title and content of an existing note. After making changes, they can save the updated note.</p>
<p>4. <b>Delete Note:</b> Users can delete a note from the list. A confirmation dialog will appear before the note is permanently deleted.</p>

# MVVM Architecture:
MVVM is an architectural pattern that separates an application into three main components: Model, View, and ViewModel.


<p>1. <b>Model:</b></p>

* Represents the data and business logic of the application.
* In this app, the Note class represents a single note with properties like id, title, and content.

<p>2. <b>View:</b></p>

* Displays the UI to the user and captures user input.
* In this app, activities and fragments represent the views.
* The main screen displays the list of notes, and a detail screen displays the details of a selected note.

<p>3. <b>ViewModel:</b></p>

* Acts as an intermediary between the Model and the View.
* Holds and manages UI-related data and states.
* Handles user interactions and triggers corresponding actions in the Model.
* In this app, the NoteViewModel class exposes methods for CRUD operations on notes.



# Navigation Component:
The Navigation Component simplifies the navigation flow between different screens in an Android app. It uses a graph-based navigation model.

<p>1. <b>Navigation Graph:</b></p>

* Create a navigation graph that defines the flow between the main screen and the detail/edit screen.
* Add actions to navigate between the screens.

<p>2. <b>Main Screen:</b></p>

* Display the list of notes using a RecyclerView.
* Handle item clicks to navigate to the detail screen.

<p>3. <b>Detail/Edit Screen:</b></p>

* Display the details of the selected note.
* Allow editing of the note's title and content.
* Handle save and delete actions.


