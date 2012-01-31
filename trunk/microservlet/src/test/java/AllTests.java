import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.sokolov.microservlet.MicroServletTest;
import com.sokolov.microservlet.RequestFormImplTest;
import com.sokolov.microservlet.RequestFormUtilTest;

/**
 * Main test class AllTests.
 * @author Helio Frota
 *
 */
@RunWith(value = Suite.class)
@SuiteClasses(value = {
    RequestFormUtilTest.class,
    RequestFormImplTest.class,
    MicroServletTest.class
})
public class AllTests {

}
