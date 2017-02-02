import com.dvlsoft.LotteryProcessor;
import com.dvlsoft.Person;
import org.junit.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class LotteryTest {
    private static LotteryProcessor lotteryProcessor;
    private static String winCombination = "456000123";
    /*
        Alison,Alice,Austria,1234567890
        Bert,Bertram,Belgium,0987654321
        Alison,Alice,Austria,9988776655
        Carlson,Cynthia,China,1111999911
        Carlson,Cynthia,Chile,1122334455
        Daneson,Dan Dilbert,Denmark,4680468023
        Dilbert Daneson,Dan,Denmark,0000000000
        Eggbert,Eric,Estonia,9988778899
    */

    @Before
    public void setUp() {
        lotteryProcessor = new LotteryProcessor(winCombination);
    }

    @After
    public void tearDown() {
        lotteryProcessor = null;
    }

    @Test
    public void testGetCreditsForAliceWithOneTicket() {
        List<String> ticketsNumbers = new ArrayList<>(Arrays.asList("1234567890"));
        Person alice = new Person("Alice", "Alison", "Austria", ticketsNumbers);
        List<Person> persons = new ArrayList<>();
        persons.add(alice);
        lotteryProcessor.calculateCredits(persons);
        assertEquals(4, persons.get(0).getCredits());
    }

    @Test
    public void testGetCreditsForAliceWithTwoTickets() {
        List<String> ticketsNumbers = new ArrayList<>(Arrays.asList("1234567890", "9988776650"));
        Person alice = new Person("Alice", "Alison", "Austria", ticketsNumbers);
        List<Person> persons = new ArrayList<>();
        persons.add(alice);
        lotteryProcessor.calculateCredits(persons);
        assertEquals(6, persons.get(0).getCredits());
    }

    @Test
    public void testGetCreditsForEric() {
        List<String> ticketsNumbers = new ArrayList<>(Arrays.asList("9988778899"));
        Person eric = new Person("Eric", "Eggbert", "Estonia", ticketsNumbers);
        List<Person> persons = new ArrayList<>();
        persons.add(eric);
        lotteryProcessor.calculateCredits(persons);
        assertEquals(0, persons.get(0).getCredits());
    }

    @Test
    public void testGetCreditsForCynthia() {
        List<String> list = new ArrayList<>(Arrays.asList("1111999911"));
        Person cynthia = new Person("Carlson", "Cynthia", "China", list);
        List<Person> persons = new ArrayList<>();
        persons.add(cynthia);
        lotteryProcessor.calculateCredits(persons);
        assertEquals(1, persons.get(0).getCredits());
    }

    @Test
    public void testGetCreditsForDanDilbert() {
        List<String> list = new ArrayList<>(Arrays.asList("4680468023"));
        Person danDilbert = new Person("Daneson", "Dan Dilbert", "Denmark", list);
        List<Person> persons = new ArrayList<>();
        persons.add(danDilbert);
        lotteryProcessor.calculateCredits(persons);
        assertEquals(6, persons.get(0).getCredits());
    }

    @Test
    public void testGetCreditsForDan() {
        List<String> list = new ArrayList<>(Arrays.asList("0000000000"));
        Person dan = new Person("Dilbert Daneson", "Dan", "Denmark", list);
        List<Person> persons = new ArrayList<>();
        persons.add(dan);
        lotteryProcessor.calculateCredits(persons);
        assertEquals(3, persons.get(0).getCredits());
    }

}