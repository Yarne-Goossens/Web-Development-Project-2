package domain;

import static org.junit.Assert.*;

import classes.domain.model.Boek;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.List;

public class Testen {

    private static final String url = "http://localhost:8080/Web2_war_exploded/Controller?command=";
    private WebDriver driver;

    @Before
    public void setUp() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
    }


    //Valide html
    @Test
    public void test_Home_IsValidHtml() {
        driver.get("https://html5.validator.nu/");
        WebElement invulveld = driver.findElement(By.id("doc"));

        invulveld.sendKeys("http://cyclone3.uclllabs.be:8081/Yarne_Goossens_war/Controller?command=default");

        WebElement button = driver.findElement(By.id("submit"));
        button.click();

        WebElement pass = driver.findElement(By.cssSelector("#results .success"));
        assertEquals("The document is valid HTML5 + ARIA + SVG 1.1 + MathML 2.0 (subject to the utter previewness of this service).", pass.getText());
    }

    @Test
    public void test_Zoek_IsValidHtml() {
        driver.get("https://html5.validator.nu/");
        WebElement invulveld = driver.findElement(By.id("doc"));

        invulveld.sendKeys("http://cyclone3.uclllabs.be:8081/Yarne_Goossens_war/Controller?command=zoekform");

        WebElement button = driver.findElement(By.id("submit"));
        button.click();

        WebElement pass = driver.findElement(By.cssSelector("#results .success"));
        assertEquals("The document is valid HTML5 + ARIA + SVG 1.1 + MathML 2.0 (subject to the utter previewness of this service).", pass.getText());
    }

    @Test
    public void test_Voeg_Toe_IsValidHtml() {
        driver.get("https://html5.validator.nu/");
        WebElement invulveld = driver.findElement(By.id("doc"));

        invulveld.sendKeys("http://cyclone3.uclllabs.be:8081/Yarne_Goossens_war/Controller?command=voegtoe");

        WebElement button = driver.findElement(By.id("submit"));
        button.click();

        WebElement pass = driver.findElement(By.cssSelector("#results .success"));
        assertEquals("The document is valid HTML5 + ARIA + SVG 1.1 + MathML 2.0 (subject to the utter previewness of this service).", pass.getText());
    }

    @Test
    public void test_Overzicht_IsValidHtml() {
        driver.get("https://html5.validator.nu/");
        WebElement invulveld = driver.findElement(By.id("doc"));

        invulveld.sendKeys("http://cyclone3.uclllabs.be:8081/Yarne_Goossens_war/Controller?command=overzicht");

        WebElement button = driver.findElement(By.id("submit"));
        button.click();

        WebElement pass = driver.findElement(By.cssSelector("#results .success"));
        assertEquals("The document is valid HTML5 + ARIA + SVG 1.1 + MathML 2.0 (subject to the utter previewness of this service).", pass.getText());
    }

    @Test
    public void test_Logboek_IsValidHtml() {
        driver.get("https://html5.validator.nu/");
        WebElement invulveld = driver.findElement(By.id("doc"));

        invulveld.sendKeys("http://cyclone3.uclllabs.be:8081/Yarne_Goossens_war/Controller?command=logboek");

        WebElement button = driver.findElement(By.id("submit"));
        button.click();

        WebElement pass = driver.findElement(By.cssSelector("#results .success"));
        assertEquals("The document is valid HTML5 + ARIA + SVG 1.1 + MathML 2.0 (subject to the utter previewness of this service).", pass.getText());
    }



    //Nav test
    @Test
    public void test_Paginaflow_Navigatie_() {
        driver.get(url + "default");

        driver.findElement(By.id("zoek")).click();
        assertEquals(url + "zoekForm", driver.getCurrentUrl());

        driver.findElement(By.id("home")).click();
        assertEquals(url + "default", driver.getCurrentUrl());

        driver.findElement(By.id("voegtoe")).click();
        assertEquals(url + "voegtoe", driver.getCurrentUrl());

        driver.findElement(By.id("homelogo")).click();
        assertEquals(url + "default", driver.getCurrentUrl());

        driver.findElement(By.id("overzicht")).click();
        assertEquals(url + "overzicht", driver.getCurrentUrl());

        driver.findElement(By.id("logboek")).click();
        assertEquals(url + "logboek", driver.getCurrentUrl());
    }


    //Zoekform testen
    @Test
    public void test_Zoekopdracht_Maze_Runner_Geeft_Resultaat() {
        driver.get(url + "zoekForm");
        zoekForm("the maze runner");
        String resultaat = driver.findElement(By.tagName("p")).getText();
        assertEquals("We vonden dit boek voor the maze runner:", resultaat);
    }

    @Test
    public void test_Zoekopdracht_boek_Geeft_Geen_Resultaat() {
        driver.get(url + "zoekForm");
        zoekForm("boek");
        String resultaat = driver.findElement(By.tagName("p")).getText();
        assertEquals("We konden het boek genaamd boek helaas niet vinden.", resultaat);
    }

    @Test
    public void test_Zoekopdracht_Harry_Geeft_Geen_Resultaat() {
        driver.get(url + "zoekForm");
        zoekForm("Harry");
        String resultaat = driver.findElement(By.tagName("p")).getText();
        assertEquals("We konden het boek genaamd Harry helaas niet vinden.", resultaat);
    }

    @Test
    public void test_Zoekopdracht_Empty_Geeft_Error() {
        driver.get(url + "zoekForm");
        zoekForm("");
        String resultaat = driver.findElement(By.id("error")).getText();
        assertEquals("Het veld mag niet leeg zijn", resultaat);
    }

    private void zoekForm(String zoekOpdracht) {
        driver.findElement(By.name("boek")).clear();
        driver.findElement(By.name("boek")).sendKeys(zoekOpdracht);
        driver.findElement(By.id("verstuur")).click();
    }


    //Voeg Toe
    @Test
    public void test_Voegtoe_Dummyboek_Succes() {
        driver.get(url + "voegtoe");
        voegtoeForm("dummyboek", "schrijver", "5", "avontuur", "1234567890123", "15");
        List<WebElement> tds = driver.findElements(By.cssSelector("td"));
        assertTrue(paginaBevatTdMetText(tds, "dummyboek"));
    }

    @Test
    public void test_Voegtoe_Dummy_Succes() {
        driver.get(url + "voegtoe");
        voegtoeForm("dummy", "auteur", "7", "fantasie", "3210987654321", "18");
        List<WebElement> tds = driver.findElements(By.cssSelector("td"));
        assertTrue(paginaBevatTdMetText(tds, "dummy"));
    }

    @Test
    public void test_Voegtoe_Maze_Runner_Geeft_Error_Al_Toegevoegd() {
        driver.get(url + "voegtoe");
        voegtoeForm("the maze runner", "schrijver", "5", "avontuur", "1234567890123", "15");
        String resultaat = driver.findElement(By.id("error")).getText();
        assertEquals("Je mag een boek maar één keer toevoegen", resultaat);
    }

    @Test
    public void test_Voegtoe_Empty_Titel_Geeft_Error() {
        driver.get(url + "voegtoe");
        voegtoeForm("", "auteur", "5", "avontuur", "1234567890123", "15");
        String resultaat = driver.findElement(By.id("error")).getText();
        assertEquals("Vul een correcte titel in.", resultaat);
    }

    @Test
    public void test_Voegtoe_Empty_Auteur_Geeft_Error() {
        driver.get(url + "voegtoe");
        voegtoeForm("boek", "", "5", "avontuur", "1234567890123", "15");
        String resultaat = driver.findElement(By.id("error")).getText();
        assertEquals("Vul een correcte naam in.", resultaat);
    }

    @Test
    public void test_Voegtoe_Empty_Aantal_Geeft_Error() {
        driver.get(url + "voegtoe");
        voegtoeForm("boek", "auteur", "", "avontuur", "1234567890123", "15");
        String resultaat = driver.findElement(By.id("error")).getText();
        assertEquals("Vul een nummer in voor aantal.", resultaat);
    }

    @Test
    public void test_Voegtoe_Empty_Genre_Geeft_Error() {
        driver.get(url + "voegtoe");
        voegtoeForm("boek", "auteur", "5", "", "1234567890123", "15");
        String resultaat = driver.findElement(By.id("error")).getText();
        assertEquals("Vul een correct genre in.", resultaat);
    }

    @Test
    public void test_Voegtoe_Empty_Isbn_Geeft_Error() {
        driver.get(url + "voegtoe");
        voegtoeForm("boek", "auteur", "5", "avontuur", "", "15");
        String resultaat = driver.findElement(By.id("error")).getText();
        assertEquals("Vul een correct ISBN-nummer in. (Een isbn nummer moet 13 cijfers lang zijn.)", resultaat);
    }

    @Test
    public void test_Voegtoe_Empty_Leeftijd_Geeft_Error() {
        driver.get(url + "voegtoe");
        voegtoeForm("boek", "auteur", "5", "avontuur", "1234567890123", "");
        String resultaat = driver.findElement(By.id("error")).getText();
        assertEquals("Vul een nummer in voor leeftijd.", resultaat);
    }

    private void voegtoeForm(String titel, String auteur, String aantal, String genre, String isbn, String leeftijd) {
        driver.findElement(By.id("titel")).clear();
        driver.findElement(By.id("titel")).sendKeys(titel);
        driver.findElement(By.id("auteur")).clear();
        driver.findElement(By.id("auteur")).sendKeys(auteur);
        driver.findElement(By.id("aantal")).clear();
        driver.findElement(By.id("aantal")).sendKeys(aantal);
        driver.findElement(By.id("genre")).clear();
        driver.findElement(By.id("genre")).sendKeys(genre);
        driver.findElement(By.id("isbn")).clear();
        driver.findElement(By.id("isbn")).sendKeys(isbn);
        driver.findElement(By.id("leeftijd")).clear();
        driver.findElement(By.id("leeftijd")).sendKeys(leeftijd);
        driver.findElement(By.id("verstuur")).click();
    }

    private boolean paginaBevatTdMetText(List<WebElement> tds, String tekst) {
        for (WebElement td : tds) {
            if (td.getText().equals(tekst)) {
                return true;
            }
        }
        return false;
    }


    //Wijzigen
    @Test
    public void test_Wijzig_Lord_of_the_Rings_Naar_The_Maze_Runner_Geeft_Error() {
        driver.get(url + "overzicht");
        driver.findElement(By.id("wijzig0")).click();
        wijzigForm("the maze runner", "", "", "", "", "");
        String resultaat = driver.findElement(By.id("error")).getText();
        assertEquals("Je mag een boek maar één keer toevoegen",resultaat);
    }

    @Test
    public void test_Wijzig_Lord_of_the_Rings_Naar_Gewijzigd_Boek_Succes() {
        driver.get(url + "overzicht");
        driver.findElement(By.id("wijzig0")).click();
        wijzigForm("Gewijzigd boek", "Testschrijver", "1", "Test", "1234567890123", "0");
        List<WebElement> tds = driver.findElements(By.cssSelector("td"));
        assertTrue(paginaBevatTdMetText(tds, "Gewijzigd boek"));
    }

    private void wijzigForm(String titel, String auteur, String aantal, String genre, String isbn, String leeftijd) {
        if (!titel.isEmpty()) {
            driver.findElement(By.id("titel")).clear();
            driver.findElement(By.id("titel")).sendKeys(titel);
        }

        if (!auteur.isEmpty()) {
            driver.findElement(By.id("auteur")).clear();
            driver.findElement(By.id("auteur")).sendKeys(auteur);
        }

        if (!aantal.isEmpty()) {
            driver.findElement(By.id("aantal")).clear();
            driver.findElement(By.id("aantal")).sendKeys(aantal);
        }

        if (!genre.isEmpty()) {
            driver.findElement(By.id("genre")).clear();
            driver.findElement(By.id("genre")).sendKeys(genre);
        }

        if (!isbn.isEmpty()) {
            driver.findElement(By.id("isbn")).clear();
            driver.findElement(By.id("isbn")).sendKeys(isbn);
        }

        if (!leeftijd.isEmpty()) {
            driver.findElement(By.id("leeftijd")).clear();
            driver.findElement(By.id("leeftijd")).sendKeys(leeftijd);
        }

        driver.findElement(By.id("verstuur")).click();
    }

    //Verwijderen
    @Test
    public void test_Verwijder_Maze_Runner_Cancel() {
        driver.get(url + "overzicht");
        driver.findElement(By.id("delete1")).click();
        driver.findElement(By.linkText("Cancel")).click();
        List<WebElement> tds = driver.findElements(By.cssSelector("td"));
        assertTrue(paginaBevatTdMetText(tds, "The Maze Runner"));
    }

    @Test
    public void test_Verwijder_Harry_Potter_Succes() {
        driver.get(url + "overzicht");
        driver.findElement(By.id("delete2")).click();
        driver.findElement(By.linkText("Verwijder het boek.")).click();
        List<WebElement> tds = driver.findElements(By.cssSelector("td"));
        assertFalse(paginaBevatTdMetText(tds, "Harry Potter And The Philosopher's Stone"));
    }


    @After
    public void clean() {
        driver.quit();
    }
}