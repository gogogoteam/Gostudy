# GoStudy

## Table of Contents
1. [Overview](#Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)
2. [Schema](#Schema)

## Overview
### Description
All you have to do is to enter the subjects and credits you have for the semester, and the app helps you to find the perfect study plan for you!

### App Evaluation
[Evaluation of your app across the following attributes]
- **Category:** productivity
- **Mobile:** students could share their study plans with each others and upload their plans on google calendar
- **Story:** generates a study plan and helps the student to find the best study style
- **Market:** any student or education organization
- **Habit:** the student should start setting up their plan at the start of the semester and continue to adjust it
- **Scope:** an app that helps users to plan out their personal study plan by entering the courses. Can continue with other sharing-plans features.

## Product Spec

### 1. User Stories (Required and Optional)

**Required Must-have Stories**
- [X] has a parse data base created
- [ ] allows users to create their study plan
- [ ] students can upload their plan to google calendar

**Optional Nice-to-have Stories**

- [ ] the app creates multiple plans for the user to choose from
- [ ] a platform to share people's study plan

### 2. Screen Archetypes

* signup screen
    * users can sign up for an account
* login screen
   * users can login and save their study plans on different devices
* home screen 
   * for the users to list out all the plans he or she created so far
* creation screen
    * the user enters the courses and credits he or she has for this semester
    * enters the preference of their style(study early, during nights, duration of the study sections...)
* edit screen
    * the user can edit a plan that he or she has already created
    * modifies the plan to suit the user
* view screen
    * users can view their plans separately
* share platform
    * users upload their plans and suggestions 
* personal information
    * for users to sign out
    

### 3. Navigation

**Tab Navigation** (Tab to Screen)

* [home screen]
* [share platform]
* [personal information]
* [add plan]

**Flow Navigation** (Screen to Screen)

* login screen
   * home screen
   * signup screen
* home screen
   * creation screen
   * view screen
   * share platform
   * personal information
* view screen
    * edit screen

## Wireframes
[Add picture of your hand sketched wireframes in this section]
<img src="https://github.com/gogogoteam/Gostudy/blob/main/wireframe%202.jpg" width=600>

### [BONUS] Digital Wireframes & Mockups

### [BONUS] Interactive Prototype

## Schema 
[This section will be completed in Unit 9]
### Models
#### user
| Property  | Type | Description | 
| ------------- | ------------- | ------------- | 
| objectId  | String  | unique id for the user post (default field)  |
| userId  | String | content cell  |
| username  | String | username for users to be identified  |
| password | String | user's password to sign into his or her account |
#### plans
| Property  | Type | Description | 
| ------------- | ------------- | ------------- |
| objectId| String | unique id for the plan (default field)  |
| planName | String | unique name for a plan
| user | Pointer => user.objectId | user who created the plan |
#### course
| Property  | Type | Description | 
| ------------- | ------------- | ------------- |
| objectId| String | unique id for the course (default field)  |
| courseName | String | a name to identify the course
| credits | Int | the amount of credits for each course |
| studySection | Int | how long the user wants to study for this course |
|plan | pointer => plans.objectId | the plan of this course |

### Networking
- Home Screen
   - (Read/GET) Query all plans created by the user
- View Plan Screen
   - (Read/GET) Query all plan that is selected
- Edit Plan Screen
   - (Read/GET) Query all plan that is selected
   - (Update/PUT) update the plan selected
   - (Delete) delete the course of section selected
- Personal Information Screen
   - (Read/GET) get the userId for user
   - (Read/GET) get the username for user
- Create Plan Screen
   - (Create/POST) create a new plan
