package seedu.address.commons.util;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


public class MailUtil {

    public static final String MAIL_TO = "mailto:";

    /**
     * Launches the system default email application.
     * @param emailList The list of receiver's email addresses.
     * @throws IOException if there are desktop related errors.
     * @throws URISyntaxException if the syntax of the uri is incorrect.
     */
    public static void launchMail(String... emailList) throws IOException, URISyntaxException {
        if (isDesktopCompatible()) {
            Desktop desktop;
            desktop = Desktop.getDesktop();
            String uri = preprocessEmailAddresses(emailList);
            URI mailto = new URI(uri);

            desktop.mail(mailto);
        } else {
            throw new IOException();
        }
    }

    /**
     * Checks whether the URI is in the correct format.
     * @param uri The URI to be checked.
     * @return The boolean value associated with the validity of the URI.
     */
    public static boolean isValidURI(String uri) {
        return uri.startsWith(MAIL_TO);
    }

    /**
     * Processes an array of email strings to the appropriate URI.
     * @param emailList The array of email strings.
     * @return The required URI string.
     */
    public static String preprocessEmailAddresses(String[] emailList) {
        StringBuilderUtil stringBuilderUtil = StringBuilderUtil.getInstance();
        stringBuilderUtil.append(MAIL_TO);

        for (int i = 0; i < emailList.length; ++i) {
            stringBuilderUtil.append(emailList[i]);

            if (i + 1 < emailList.length) {
                stringBuilderUtil.append(",");
            }
        }
        return stringBuilderUtil.getFormattedOutput();
    }

    /**
     * Checks whether the desktop used is supported for the mail functionality.
     * @return The boolean value associated with whether the desktop is compatible.
     */
    public static boolean isDesktopCompatible() {
        return Desktop.isDesktopSupported()
                && (Desktop.getDesktop()).isSupported(Desktop.Action.MAIL);
    }
}
