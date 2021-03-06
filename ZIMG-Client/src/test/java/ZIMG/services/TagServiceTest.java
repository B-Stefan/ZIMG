package ZIMG.services;

import ZIMG.exceptions.TagConstrainsException;
import ZIMG.models.Tag;
import javassist.NotFoundException;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithSecurityContextTestExecutionListener;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.ServletTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "file:src/main/webapp/WEB-INF/zimg-servlet.xml"
})
@WebAppConfiguration
@TestExecutionListeners(listeners={ServletTestExecutionListener.class,
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        WithSecurityContextTestExecutionListener.class})
@WithMockUser
@Rollback
public class TagServiceTest {



    final Logger logger = Logger.getLogger(TagServiceTest.class);

    @Autowired
    private TagService tagService;
    /**
     * Tests save this should fail.
     */
    @Test(expected = TagConstrainsException.class)
    public void save() throws TagConstrainsException, NotFoundException{
        tagService.save("","asdasd");

    }

    /**
     * Method to add 1000 tags for testing
     */
    public void add1000Tags(){

        for(int i = 0; i != 1000; i++){

            final Tag t = new Tag();
            t.setTag("tag-" + i);
            this.tagService.create(t);
        }
    }

}