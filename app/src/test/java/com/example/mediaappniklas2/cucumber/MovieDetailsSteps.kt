package com.example.mediaappniklas2.cucumber
import com.example.mediaappniklas2.presentation.Login.Credentials
import com.example.mediaappniklas2.presentation.Login.LoginViewModel
import io.cucumber.java.en.*

class MovieDetailsSteps {
        private val loginViewModel = LoginViewModel()




        @Given("the user is logged into the streaming platform")
        fun givenUserIsLoggedIntoStreamingPlatform() {
            // Implement logic for user login

                val credentials = Credentials(login = "admin", pwd = "password")
                loginViewModel.performLogin(credentials)
                assert(loginViewModel.loginState.value.success)


        }

        @When("the user navigates to the Homepage")
        fun whenUserNavigatesToHomepage() {
            // Implement logic for navigating to the homepage
        }

        @And("selects a movie from the available options")
        fun andSelectsMovie() {
            // Implement logic for selecting a movie
        }

        @And("accesses the details of the selected movie")
        fun andAccessesMovieDetails() {
            // Implement logic for accessing movie details
        }

        @Then("the MediaPage for the movie should load")
        fun thenMediaPageShouldLoad() {
            // Implement logic for checking if the MediaPage has loaded
        }

        @And("the image and the title of the movie should be displayed")
        fun andImageAndTitleShouldBeDisplayed() {
            // Implement logic for checking if image and title are displayed
        }

        @And("the release year of the movie should be visible")
        fun andReleaseYearShouldBeVisible() {
            // Implement logic for checking if the release year is visible
        }
    }

