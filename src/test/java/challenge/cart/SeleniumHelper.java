package challenge.cart;

import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

/**
 * Created by murreli on 1/2/17.
*/

public class SeleniumHelper {


    private WebDriver driver;
    private String baseUrl;

    String book1Xpath;
    String book2Xpath;

    ShoppingCartHelper shoppingCart;

    public float lastBookAddedPrice;
    public float bookPriceAmount;
    public float shoppingCartTotalAmount;


    public SeleniumHelper() {
        book1Xpath = "//*[@id=\"tile-container\"]/ul/li[1]/div/a[1]/img";
        book2Xpath = "//*[@id=\"tile-container\"]/ul/li[2]/div/a[1]/img";
        baseUrl = "https://www.walmart.com/browse/books/top-200-books/3920_1057224";
        //REPLACE CHROME DRIVER PATH
        System.setProperty("webdriver.chrome.driver", "/Users/murreli/Downloads/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        shoppingCart = new ShoppingCartHelper();
    }

    @Test
    public void addBooks() throws Exception {

        driver.get(baseUrl);
        addBookToShoppingCart(book1Xpath);
        addBookToShoppingCart(book2Xpath);
    }

    public void goToShoppingCart() {
        driver.navigate().to("https://www.walmart.com/cart");
    }

    public void getShoppingCartTotalAmount() {
        //get total amount from shoppingCart
        //shoppingCartTotalAmount = Float.parseFloat(driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div[1]/div/div/div[1]/div[2]/div/div[1]/div/div[4]/span[2]/span/span[3]")).getText());
        //Float decimals = Float.parseFloat(driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div[1]/div/div/div[1]/div[2]/div/div[1]/div/div[4]/span[2]/span/span[5]")).getText());
        //shoppingCartTotalAmount = shoppingCartTotalAmount + decimals/100;
        //discard shipping and taxes
    }

    private void addBookToShoppingCart(String bookXpath) {
        driver.findElement(By.xpath(bookXpath)).click();
        sumBookPrice();
        driver.findElement(By.className("prod-ProductCTAAddToCart")).click();
        driver.findElement(By.className("Cart-POS-continueShopping")).click();
        driver.navigate().to(baseUrl);
    }

    private void sumBookPrice() {
        WebElement element = driver.findElement(By.className("Price-characteristic"));
        lastBookAddedPrice = Float.parseFloat(element.getAttribute("content"));
        bookPriceAmount = bookPriceAmount + lastBookAddedPrice;

    }


    public void addExtraBook() {

        driver.findElement(By.cssSelector("div.chooser-option-current")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div[1]/div/div/div[1]/div[1]/div/div[3]/div[1]/div/div/div/div[1]/div/div/div[3]/div/div/div[1]/div/div/span/div/div/div[2]")).click();
        bookPriceAmount += lastBookAddedPrice;
        driver.navigate().to(baseUrl);
    }




    public String XpathExtractor (String element) {

        String xpath = "-> xpath: ";

        return element.substring((element.indexOf(xpath)) + xpath.length(), element.lastIndexOf(xpath)-1 );


    }

    public Boolean checkShoppingCartSum() {

        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);

        String totalAmount = String.valueOf(df.format(bookPriceAmount));
        if (driver.getPageSource().contains(totalAmount)) {
            return true;
        }
        else {
            System.out.println(totalAmount);
            return false;
        }

    }

}

