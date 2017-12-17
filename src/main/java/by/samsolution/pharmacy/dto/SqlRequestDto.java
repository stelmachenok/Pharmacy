package by.samsolution.pharmacy.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


public class SqlRequestDto {
    private String key;
    private String value;

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    @XmlElement
    public void setKey(String key) {
        this.key = key;
    }

    @XmlElement
    public void setValue(String value) {
        this.value = value;
    }
}
