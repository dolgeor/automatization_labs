package ex_3;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CarSerializationDeserializationTest {
    private final String CAR_XML = "src/main/resources/car.xml";
    private final String CAR_JSON = "src/main/resources/car.json";
    private final List<Driver> drivers =
            Arrays.asList(
                    new Driver("Andrey", 40L, 13L),
                    new Driver("Mike", 21L, 3L)
            );
    private final Car car = new Car("wolksvagen golf", Type.SEDAN, 2013L, 200_000L, drivers);

    @Test
    public void test1_whenJavaSerializedToXmlFile_thenCorrect() throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.writeValue(new File(CAR_XML), car);
        File file = new File(CAR_XML);
        assertNotNull(file);
    }

    @Test
    public void test2_whenJavaSerializedToJSONFile_thenCorrect() throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(CAR_JSON),car);
        File file = new File(CAR_JSON);
        assertNotNull(file);
    }

    @Test
    public void test3_whenJavaGotFromXmlFile_thenCorrect() throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        StringJoiner stringJoiner = new StringJoiner("");
        Files
                .readAllLines(Paths.get(CAR_XML))
                .stream()
                .forEach(s -> stringJoiner.add(s));
        String xml = stringJoiner.toString();
        Car value = xmlMapper.readValue(xml, Car.class);
        assertEquals(car, value);
    }

    @Test
    public void test4_whenJavaGotFromJSONFile_thenCorrect() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Car value = mapper.readValue(new File(CAR_JSON), Car.class);
        assertEquals(car, value);
    }
}
