/*
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
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
