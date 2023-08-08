package utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import io.cucumber.plugin.event.PickleStepTestStep;

public class Filters {
	@SuppressWarnings("unused")
	private static PickleStepTestStep currentStep;
    @SuppressWarnings("unused")
	private int counter = 0;
    public void removeDirectoryEvidence() {
        File directory = new File(System.getProperty("user.dir") + "/src/test/resources/reports");
        try {
            FileUtils.deleteDirectory(directory);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
