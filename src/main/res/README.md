# BoozeHound
Official app repository information and documentation


To Begin Work
---------
When in Android Studio, click on `File -> New -> Import Project`.
This should find the correct files and then open the project as a whole. If you have problems with importing, submit an [issue](https://github.com/BoozeHoundApp/BoozeHound/issues).

Documentation
----------
*Circle Continuous Integration*
To add continuous integration to the project, we are using a service called "Circle CI" from circleci.com. This will run tests against all pull requests and new commits to ensure that the app is bug-free even after new commits and code changes. Before the testing can begin, we added a file called `circle.yml` to our GitHub repository. Much of the necessary code for the continuous integration within the `circle.yml` file is located on Circle CI's Android documentation page here: https://circleci.com/docs/android/. Once that file is added, the service will continually check all new changes for quality assurance purposes.
