package ZIMG.services;

import ZIMG.exceptions.CommentConstrainsException;
import ZIMG.exceptions.TagConstrainsException;
import ZIMG.models.Tag;
import javassist.NotFoundException;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
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
public class CommentServiceTest {



    final Logger logger = Logger.getLogger(CommentServiceTest.class);

    @Autowired
    private CommentService commentService;
    /**
     * Tests save this should fail.
     */
    @Test(expected = NotFoundException.class)
    public void saveWrongId() throws NotFoundException,CommentConstrainsException{
        commentService.save("asdasd", "999999999");

    }

    /**
     * Tests save this should fail.
     */
    @Test(expected = CommentConstrainsException.class)
    public void saveToShortComment() throws NotFoundException,CommentConstrainsException{
        commentService.save("", "1");

    }

}