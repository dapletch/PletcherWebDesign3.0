package com.pletcherwebdesign.test;

import com.pletcherwebdesign.email.beans.MessageBody;
import com.pletcherwebdesign.email.dao.EmailFormDao;
import com.pletcherwebdesign.jdbcproperties.JdbcConfiguration;
import com.pletcherwebdesign.utils.PletcherWebDesignUtils;
import org.apache.struts2.StrutsTestCase;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DataAccessException;

/**
 * Created by Seth on 2/5/2017.
 */
public class PletcherWebDesignTestCases extends StrutsTestCase {

    private MessageBody messageBody = new MessageBody();

    @Test
    public void testEmailValidation() throws Exception {
        String email1 = "seth.pletcher@gmail.com";
        String email2 = "Megan.Barr@state.vt.us";

        System.out.println("The result of test 1 is: " + PletcherWebDesignUtils.isEmailAddressValid(email1));
        System.out.println("The result of test 2 is: " + PletcherWebDesignUtils.isEmailAddressValid(email2));
    }

    @Test
    public void testEmailForm() throws Exception {

        String formToBeSearched = "infosession";
        ApplicationContext context = new AnnotationConfigApplicationContext(JdbcConfiguration.class);
        EmailFormDao emailFormDao = context.getBean(EmailFormDao.class);
        try {
            messageBody = emailFormDao.getHtmlFormInfoForEmail(formToBeSearched);

            System.out.println("The message body object: " + messageBody.toString());
        } catch (DataAccessException e) {
            System.out.println(e);
        }
    }
}