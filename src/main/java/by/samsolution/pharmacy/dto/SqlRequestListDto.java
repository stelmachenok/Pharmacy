package by.samsolution.pharmacy.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class SqlRequestListDto {
    List<SqlRequestDto> sqlRequestListDtos;

    public List<SqlRequestDto> getSqlRequestListDtos() {
        return sqlRequestListDtos;
    }

    @XmlElement(name = "SqlRequestDto")
    public void setSqlRequestListDtos(List<SqlRequestDto> sqlRequestListDtos) {
        this.sqlRequestListDtos = sqlRequestListDtos;
    }
}
