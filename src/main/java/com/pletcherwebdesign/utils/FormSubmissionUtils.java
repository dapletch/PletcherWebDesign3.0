package com.pletcherwebdesign.utils;

import com.pletcherwebdesign.beans.Ticket;
import com.pletcherwebdesign.beans.interfaces.FormSubmission;
import com.pletcherwebdesign.dao.TicketDao;
import com.pletcherwebdesign.email.beans.MessageBody;
import com.pletcherwebdesign.email.dao.EmailFormDao;
import com.pletcherwebdesign.email.sendemail.EmailConfig;
import com.pletcherwebdesign.email.sendemail.SendEmail;
import com.pletcherwebdesign.jdbcproperties.JdbcConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.util.List;

/**
 * Created by Seth on 2/17/2017.
 */
public class FormSubmissionUtils implements FormSubmission {

    public static final String url = "https://www.google.com/recaptcha/api/siteverify";
    private static final String secret = "6LeYChYUAAAAAGfsrTacgenTUpF0K-jJ42O2Om6V";
    private String captchaResponse = null;
    private Logger logger = LoggerFactory.getLogger(FormSubmissionUtils.class);


    public void sendNotificationEmail(String formSubmission, String emailMessage) {
        ApplicationContext context = new AnnotationConfigApplicationContext(EmailConfig.class);
        SendEmail sendEmail = context.getBean(SendEmail.class);
        sendEmail.sendEmailNoAttachment(getMessageBodyForm(formSubmission, emailMessage));
    }

    public MessageBody getMessageBodyForm(String formSubmission, String emailMessage) {
        ApplicationContext context = new AnnotationConfigApplicationContext(JdbcConfiguration.class);
        EmailFormDao emailFormDao = context.getBean(EmailFormDao.class);
        MessageBody messageBody = emailFormDao.getHtmlFormInfoForEmail(formSubmission);
        return PletcherWebDesignUtils.setMessageBodyNoAttachment(messageBody, emailMessage);
    }

    public String getTicketsForAdmin(String adminUsername) {
        ApplicationContext context = new AnnotationConfigApplicationContext(JdbcConfiguration.class);
        TicketDao ticketDao = context.getBean(TicketDao.class);
        List<Ticket> adminTicketList = ticketDao.selectTicketsForAdmin();
        return TicketUtils.getTicketsForAdmin(adminTicketList, adminUsername);
    }

    public String getTicketListForClient(String clientUsername) {
        ApplicationContext context = new AnnotationConfigApplicationContext(JdbcConfiguration.class);
        TicketDao ticketDao = context.getBean(TicketDao.class);
        List<Ticket> clientTicketList = ticketDao.selectTicketsForClient(clientUsername);
        return TicketUtils.getTicketsClientToSee(clientTicketList);
    }

    public Boolean isCaptchaValid(HttpServletRequest request) {
        captchaResponse = request.getParameter("g-recaptcha-response");
        if (captchaResponse == null || captchaResponse.equals("")) {
            return false;
        }
        try {
            URL obj = new URL(url);
            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

            // add reuqest header
            con.setRequestMethod("POST");
            con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

            String postParams = "secret=" + secret + "&response=" + captchaResponse;

            // Send post request
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(postParams);
            wr.flush();
            wr.close();

            Integer responseCode = con.getResponseCode();
            logger.info("\nSending 'POST' request to URL : " + url);
            logger.info("Post parameters : " + postParams);
            logger.info("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            logger.info(response.toString());

            //parse JSON response and return 'success' value
            JsonReader jsonReader = Json.createReader(new StringReader(response.toString()));
            JsonObject jsonObject = jsonReader.readObject();
            jsonReader.close();

            return jsonObject.getBoolean("success");
        } catch (Exception e) {
            logger.error("There was an issue validating the captcha: " + e);
        }
        return false;
    }
}
