package hotel;

import org.junit.jupiter.api.Test;

import edu.mu.hotel.AccessCodeGenerator;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

/**
 * JUnit test class for AccessCodeGenerator.
 */
public class AccessCodeGeneratorTest {

    /**
     * Test case to verify the generated access code length is correct.
     */
    @Test
    void testGeneratedCodeLength() {
        
        String code = AccessCodeGenerator.generateCode();
        assertEquals(AccessCodeGenerator.CODE_LENGTH, code.length(), "Generated code length should match CODE_LENGTH");
    }

    /**
     * Test case to verify the uniqueness of generated access codes.
     */
    @Test
    void testUniqueGeneratedCodes() {
        Set<String> generatedCodes = new HashSet<>();
        int numberOfCodesToGenerate = 10000;

        for (int i = 0; i < numberOfCodesToGenerate; i++) {
            String code = AccessCodeGenerator.generateCode();
            generatedCodes.add(code);
        }

        //test to make sure codes are unique
        assertEquals(numberOfCodesToGenerate, generatedCodes.size(), "All generated codes should be unique");
    }
}

