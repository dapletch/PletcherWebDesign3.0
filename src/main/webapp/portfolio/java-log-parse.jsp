<%--
  Created by IntelliJ IDEA.
  User: Seth
  Date: 2/7/2017
  Time: 5:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Java Log Parsing Analytics Application</h1>
<img src="../images/log_parse_2.PNG" width="555px" height="310px" align="right" class="log_parse"
     style="padding-left: 15px; padding-bottom: 2px;"/>
<p>Part of being a successful web developer is understanding web analytics. I was assigned this assignment, as a tool to
    better understanding HashMaps and ArrayLists. The original requirements for this assignment are listed as
    follows:</p>
<ul>
    <li>Create a method to search the ArrayList for the first instance of an IP Address.</li>
    <li>A method to return the last instance of an IP address.</li>
    <li>Create a HashMap of IP Addresses and a count, for how many times they are accessed the site.</li>
    <li>Create a HashMap of URL's and how often each one is visited.</li>
</ul>
<p>I had never had to parse a log file before and after performing some more analysis of the log file I realized that
    this would be no easy task. I was accustomed to parsing files with only one or two more delimiters at a time, but
    never had to deal with such complex files such as log files. It was with this knowledge that I looked over the
    internet to be able to find an example of how such a task would be done, when I came across the following website:
    <a href="http://cferricks.blogspot.com/2009/09/java-parsing-apache-log-file.html">http://cferricks.blogspot.com/2009/09/java-parsing-apache-log-file.html</a>.
    After further analysis of the example that I found I was able to determine the way that the log file was parsed was
    through the use of regular expressions. </p>
<p>Regular expressions are particularly useful because when you look at it almost everything that you see on a web page
    or in code can be translated, as a character value. It is through the use of regular expressions that we can
    interpret a sequence of characters, which allows the user to be able to read the data in a clear, concise,
    manner.</p>
<p>Once the regular expression was defined I was able to use the Pattern and Matcher classes in Java to be able read and
    parse the Apache Log file. After some trouble shooting and further configuration I was able to then use a series of
    JDBC connections in conjunction with WAMP to submit the parsed data to a MySQL database, so they data could be
    further analyzed. The tool that I created is a tool that I still use to this day.</p>
</body>
</html>
