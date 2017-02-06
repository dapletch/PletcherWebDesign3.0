package com.pletcherwebdesign.test;

import com.pletcherwebdesign.utils.PletcherWebDesignUtils;
import org.apache.struts2.StrutsTestCase;
import org.junit.Test;

/**
 * Created by Seth on 2/5/2017.
 */
public class PletcherWebDesignTestCases extends StrutsTestCase {

    @Test
    public void testEmailValidation() throws Exception {
        String email1 = "seth.pletcher@gmail.com";
        String email2 = "Megan.Barr@state.vt.us";

        System.out.println("The result of test 1 is: " + PletcherWebDesignUtils.isEmailAddressValid(email1));
        System.out.println("The result of test 2 is: " + PletcherWebDesignUtils.isEmailAddressValid(email2));
    }
}