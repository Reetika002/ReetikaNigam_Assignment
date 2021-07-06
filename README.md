# ReetikaNigam_Assignment_JP_Morgan

This is the BDD Framework using Cucumber, TestNG, Java and selenium, maven.
You can directly run "run.bat" file. It will execute the test case using maven and generate the HTML 
report under /generated/reports folder. I have used extent report for reporting purposes.

**To verify the logo, I have used SIKULI Tool (Jars) and Path is src/main/resources/siluli_images/logo.png**

**JDK : 11**

**Feature File :**
Path : D:\ReetikaNigam_Assignment\src\test\resources\feature

**TestCase:**
Open Google, search for “J. P. Morgan”, click the first result returned by Google, verify that the J.P. Morgan logo is shown.

**StepDefinition:**
Path : src/test/java/stepdefinitions/

**Logging :**
Using Log4J 

**Reporting**
Using extent reports
report under /generated/reports folder

**Config File**
Path : src/main/resources/configs/

BasePage Class

src/test/java/com.jpmorgan/BasePage.java





