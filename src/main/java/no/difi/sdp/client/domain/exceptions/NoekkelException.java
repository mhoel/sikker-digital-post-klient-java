package no.difi.sdp.client.domain.exceptions;

public class NoekkelException extends KonfigurasjonException {
    public NoekkelException(String message, Exception e) {
        super(message, e);
    }

    public NoekkelException(String s) {
        super(s);
    }
}
