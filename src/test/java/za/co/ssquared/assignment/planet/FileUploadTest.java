package za.co.ssquared.assignment.planet;

import org.apache.commons.io.IOUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Ignore
public class FileUploadTest {

    @Test
    public void givenFileName_whenUsingIOUtils_thenFileData() throws IOException {
        String expectedData = "PlanetNode|PlanetName\n" + "A|Earth\n" + "B|Moon\n" + "C|Jupiter\n" + "D|Venus\n" + "E|Mars\n" + "F|Saturn\n" + "G|Uranus\n" + "H|Pluto\n" + "I|Neptune\n" + "J|Mercury\n" + "K|Alpha Centauri\n" + "L|Luhman 16\n" + "M|Epsilon Eridani\n" + "N|Groombridge 34\n" + "O|Epsilon Indi\n" + "P|Tau Ceti\n" + "Q|Kapteyn's star\n" + "R|Gliese 687\n" + "S|Gliese 674\n" + "T|Gliese 876#\n" + "U|Gliese 832\n" + "V|Fomalhaut\n" + "W|Virginis\n" + "X|HD 102365\n" + "Y|Gliese 176\n" + "Z|Gliese 436\n" + "A'|Pollux\n" + "B'|Gliese 86\n" + "C'|HIP 57050\n" + "D'|Piscium\n" + "E'|GJ 1214\n" + "F'|Upsilon Andromedae\n" + "G'|Gamma Cephei\n" + "H'|HD 176051\n" + "I'|Gliese 317\n" + "J'|HD 38858\n" + "K'|Ursae Majoris";

        FileInputStream fis = new FileInputStream ( "src/main/resources/data/planets.txt" );
        String data = IOUtils.toString ( fis,"UTF-8" );

        assertEquals ( expectedData,data.trim () );
    }
}
