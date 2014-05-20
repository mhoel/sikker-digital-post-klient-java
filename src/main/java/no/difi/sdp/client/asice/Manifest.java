package no.difi.sdp.client.asice;

public class Manifest implements AsicEAttachable {

    private final byte[] xmlBytes;

    public Manifest(byte[] xmlBytes) {
        this.xmlBytes = xmlBytes;
    }

    @Override
    public String getFileName() {
        return "Manifest.xml";
    }

    @Override
    public byte[] getBytes() {
        return xmlBytes;
    }

    @Override
    public String getMimeType() {
        return "application/xml";
    }
}
