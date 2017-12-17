package by.samsolution.pharmacy.util;
import by.samsolution.pharmacy.dto.SqlRequestDto;
import by.samsolution.pharmacy.dto.SqlRequestListDto;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.InputStream;

public class SqlQueryReader {

    public static String read(String key){

        InputStream stream = SqlQueryReader.class.getResourceAsStream("/bigSqlQueries.xml");
        SqlRequestListDto request;

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(SqlRequestDto.class, SqlRequestListDto.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            request = (SqlRequestListDto) jaxbUnmarshaller.unmarshal(stream);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return request.getSqlRequestListDtos().stream().filter((r)-> r.getKey().equals(key)).findAny().get().getValue();
    }
}
