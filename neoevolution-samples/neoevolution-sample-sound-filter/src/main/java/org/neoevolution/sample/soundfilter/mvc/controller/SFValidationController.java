package org.neoevolution.sample.soundfilter.mvc.controller;

import org.neoevolution.core.activation.GenotypeActivation;
import org.neoevolution.mvc.model.Genotype;
import org.neoevolution.mvc.service.GenotypeService;
import org.neoevolution.sample.soundfilter.util.WavFile;
import org.neoevolution.sample.soundfilter.util.WavFileException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
@RestController
@RequestMapping("/validation/{id}")
public class SFValidationController {

    public static final String INPUT_FILE1 = "sound/mix-cello-piano";
    public static final String INPUT_FILE2 = "sound/mix-female-piano";
    public static final int TIME_FRAME = 21;

    @Autowired
    private GenotypeService service;

    @Autowired
    private GenotypeActivation activation;


    @RequestMapping(value = "/validate")
    public void validate(@PathVariable Long id) {
        read(id, INPUT_FILE1);
        read(id, INPUT_FILE2);
    }

    private void read(Long id, String inputName)
    {
        Genotype genotype = service.find(id);
        List<Double> outputs = new ArrayList<>();

        try {
            String inPath = getClass().getClassLoader().getResource(inputName +".wav").getPath();
            WavFile inFile = WavFile.openWavFile(new File(inPath));

            int inFrames;
            int timeFrame = TIME_FRAME;
            double[] inBuffer = new double[timeFrame];

            do {
                inFrames = inFile.readFrames(inBuffer, timeFrame);
                List<Double> inputs = new ArrayList<>(timeFrame);

                for (int i = 0; i < inFrames; i++) {
                    inputs.add(inBuffer[i]);
                }

                while (inputs.size() < timeFrame) {
                    inputs.add(0d);
                }
                outputs.addAll(activation.activate(genotype, inputs));
            }
            while (inFrames != 0);

            inFile.close();
        }
        catch (WavFileException | IOException e) {
            e.printStackTrace();
        }


        if (!outputs.isEmpty()) {
            write(outputs, inputName);
        }
    }


    private void write(List<Double> outputs, String outputName)
    {
        try {
            String outPath = getClass().getClassLoader().getResource("").getPath() + outputName +"-out.wav";

            int sampleRate = 44100;		// Samples per second
            int size = outputs.size();
            double[] buffer = new double[size];
            WavFile wavFile = WavFile.newWavFile(new File(outPath), 1, size, 16, sampleRate);

            for (int i = 0; i < size; i++) {
                buffer[i] = outputs.get(i);
            }
            wavFile.writeFrames(buffer, size);
            wavFile.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
