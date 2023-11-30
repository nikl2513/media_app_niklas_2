Scenario: View details of a selected movie
Given the user is logged into the streaming platform
When the user navigates to the Homepage
And selects a movie from the available options
And accesses the details of the selected movie
Then the MediaPage for the movie should load
And the image and the title of the movie should be displayed
And the release year of the movie should be visible
