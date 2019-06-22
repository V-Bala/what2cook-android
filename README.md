# What2Cook - Recipes for you based on ingredients you have (Android)

## Overview
What2Cook is a mobile application which suggests interesting recipe ideas based 
on a list of products specified by the user. Once a user has specified a set of 
ingredients, he/she will then be shown recipe suggestions with links the complete 
cooking instructions.

## Requirements
Minimum API level required to run the app - minSdkVersion 15

## Build and Deploy (Command Line)
All build tasks can be executed via the Gradle wrapper.
```
// View all runnable tasks
gradlew tasks

// Start the emulator
// Navigate to android_sdk/tools/
emulator -avd avd_name

// Install the app 
gradlew installDebug
```
