package challenge.cart;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;


public class CartSteps {

    public SeleniumHelper website;


    @Given("^Two products added to the shopping cart\\.$")
    public void two_products_added_to_the_shopping_cart() throws Throwable {
        website = new SeleniumHelper();
        website.addBooks();
    }

    @When("^User goes to bag an adds one more instance of a particular product\\.$")
    public void user_goes_to_bag_an_adds_one_more_instance_of_a_particular_product() {

        website.goToShoppingCart();
        Assert.assertTrue(website.checkShoppingCartSum());
        website.addExtraBook();
    }

    @Then("^the total sum is calculated correctly\\.$")
    public void the_total_sum_is_calculated_correctly() {

        website.goToShoppingCart();
        Assert.assertTrue(website.checkShoppingCartSum());

    }


}
