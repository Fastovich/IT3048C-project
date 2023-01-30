# PlaceFinder

## Introduction
Are you bored? Do you need to find something to do? PlaceFinder can help you:
- Find local parks and activities
- Coordinate the weather with your desired park day
- See how active the parks are.

## Storyboard
[PlaceFinder Storyboard](https://docs.google.com/presentation/d/1OG7eTNcOelRfatnLq7LP2MbUTvGfcoe_3ltykH4EOug/edit?usp=sharing)

## Functional Requirements

1. Search for Local and National Parks by Location
 - As a user, I want to be able to find parks near me by searching for local and national parks by location.
   - Example: Given that I live in Cincinnati, I can enter "Cincinnati, OH" as the location and the app should show me a list of parks within a 20-mile radius of the selected location.

2. Filter Park Search Results by Activity Type
 - As a user, I want to be able to find parks that offer the activities I am interested in by filtering park search results by activity type.
   - Example: When I'm interested in hiking, I can select "Hiking" as the activity type and then the app should show me a list of parks near my location that offer hiking trails.

3. View Details About a Specific Park
 - As a user, I want to be able to view detailed information about a specific park so I can plan my visit and decide which activities to do.
   - Example: Given that I'm interested in visiting Central Park in New York, I can select Central Park from the search results and the app should show me detailed information about the park, including its address, hours of operation, and a list of available activities.

4. Save Favorite Park
 - As a user, I want to be able to save my favorite parks for easy access later.
   - Example: Given that I've found a park I'm interested in visiting, I can save it as a favorite and the park will be added to my list of saved parks, which I can access at any time. If I try to add the same park again, the app should notify me that the park is already in my favorite list.

## Class Diagram
[Class Diagram](https://lucid.app/publicSegments/view/ab656d41-f893-45fa-bf4a-8bdbec57272c/image.png)

[Link to make changes if needed](https://lucid.app/lucidchart/f361d73f-a5f5-4b91-981d-c3c7cf38cac8/edit?viewport_loc=21%2C154%2C1648%2C1123%2CHWEp-vi-RSFO&invitationId=inv_3de24ab5-d2e8-40c5-8422-6fbbf5111702)

## Class Diagram Description 
- `ParkSearchRequest`: a data class that represents the user's search criteria, like location and activity.
- `ParkSearchResult`: represents the search results, including a list of parks that meet the user's search criteria.
- `Park`: represents a single park and its properties, such as name, location, and activities offered.
- `ParkDetails`: represents detailed information about a specific park, including its address, hours of operation, and list of available activities.
- `SearchParkActivity`: An activity class that handles the user interface for searching for parks, including displaying the search form and presenting the search results.
- `ParkListFragment`: A fragment class that displays a list of parks, including their names and locations.
- `ParkDetailsFragment`: A fragment class that displays detailed information about a specific park, including its address, hours of operation, and list of available activities.
- `ParkSearchViewModel`: A view model class that provides the data and logic for the SearchParkActivity and ParkListFragment.
- `ParkRepository`: A repository class that handles the data storage and retrieval for the parks, including storing the search results and park details.

## Product Backlog
[Scrum Board](https://github.com/Fastovich/PlaceFinder/projects?query=is%3Aopen)

## Project Board
[Project Board](https://github.com/users/Fastovich/projects/2/views/1)

## Scrum Roles
Scrum roles and the individuals who will fill those roles will be determined and communicated later.

## Standups
Weekly standups will be held at 1:30pm on Sundays over teams, with reminders and meeting schedules created to avoid missing the meeting
