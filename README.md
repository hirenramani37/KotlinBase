## Introduction

Kotlin Base Project used as a base project in order to maintain your
project structure in Kotlin.

# Important Points that needs to be taken care while creating projects

1. Maintain this project structure compulsory
2. Simply copy the "com" package and gradle dependencies in your
   project
3. Put all your recycler adapters into single package named adapters.
   (Already created package with name "adapters")
4. Change the name of package "your_app_directory_name" with your app
   name.
5. Room Database Integration is optional. (You can remove, if it isn't
   necessary in your project)
6. Make sure that you create request model in "request" package and
   response model outside the "request"
7. Change your app base url in app level "build.gradle" file.
