# How to run tests
- from command line:
    - run this command from root of this project `./gradlew test`
- open `src/test/java/testRunners/OpenLibraryTest.java` and press run button
- use gradle build lifecycle `Tasks -> verification -> test`

# Run tests on Windows/Linux/Mac
Default settings of chromedriver for this project is Mac. Go to `src/main/java/com/fourfinance/utils/PageObject.java` when using Linux or Windows
and change commenting on lines 15 to 17 according to your environment.
Versions of chromedriver are for chrome `version 113`.
If you happen to have problems running tests, check your version of chrome and download new chromedriver according to your chrome browser version
on `https://chromedriver.chromium.org/downloads`