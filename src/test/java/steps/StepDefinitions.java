package steps;

import com.fourfinance.pages.BookPage;
import com.fourfinance.pages.MainPage;
import com.fourfinance.pages.ResultsPage;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;
import utils.Context;
import utils.OpenLibraryApi;

public class StepDefinitions {
    private final MainPage mainPage;
    private final ResultsPage resultsPage;
    private final BookPage bookPage;
    private final OpenLibraryApi openLibraryApi;
    private final Context context;

    public StepDefinitions() {
        this.mainPage = new MainPage();
        this.resultsPage = new ResultsPage();
        this.bookPage = new BookPage();
        this.openLibraryApi = new OpenLibraryApi();
        this.context = new Context();
    }

    @Given("User goes to the OpenLibrary page")
    public void openOLPage() {
        mainPage.openAndVerifyOALPage();
    }

    @And("User sets website in \"([^\"]*)\"$")
    public void selectLanguage(String language) {
        mainPage.selectAndVerifyLanguage(language);
    }

    @When("User searches using Title option for \"([^\"]*)\"$")
    public void searchForBook(String book) {
        context.setBookTitle(book);
        mainPage.searchForBookByTitle(book);
    }

    @And("User chooses book published in \"([^\"]*)\"$")
    public void chooseBookByYear(String year) {
        resultsPage.selectBookByYear(year, context.getBookTitle());
        bookPage.checkWorkTitle(context.getBookTitle());
        var authorName = bookPage.getAuthorName();
        var isbn = bookPage.getIsbn();
        context.setPageAuthor(authorName);
        context.setIsbn(isbn);
    }

    @And("Get author from API")
    public void getAuthor() {
        var author = openLibraryApi.getAuthor(context.getIsbn());
        context.setApiAuthor(author);
    }

    @Then("Author from API matches author on book page")
    public void verifyAuthor() {
        Assertions.assertThat(context.getPageAuthor()).isEqualTo(context.getApiAuthor());
    }

    @After()
    public void closeBrowser() {
        mainPage.quit();
    }
}
