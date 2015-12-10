package org.neoevolution.sample.soundfilter.mvc.service;

import org.neoevolution.core.activation.GenotypeActivation;
import org.neoevolution.mvc.model.Genotype;
import org.neoevolution.mvc.service.GenotypeService;
import org.neoevolution.sample.soundfilter.util.WavFile;
import org.neoevolution.sample.soundfilter.util.WavFileException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
@Service
public class SFValidationService {

    public static final String INPUT_FILE1 = "sound/mix-cello-piano";
    public static final String INPUT_FILE2 = "sound/mix-female-piano";
    public static final int TIME_FRAME = 16;
    public static int count = 0;

    @Autowired
    private GenotypeService service;

    @Autowired
    private GenotypeActivation activation;


    public void validate(Long id) {
        Genotype genotype = service.find(id);
        read(genotype, INPUT_FILE1);
        read(genotype, INPUT_FILE2);
    }

    public void validate(Genotype genotype) {
        count++;
        read(genotype, INPUT_FILE1);
        read(genotype, INPUT_FILE2);
    }


    private void read(Genotype genotype, String inputName)
    {
        List<Double> outputs = new ArrayList<>();

        try {
            String inPath = getClass().getClassLoader().getResource(inputName +".wav").getPath();
            WavFile inFile = WavFile.openWavFile(new File(inPath));

            int inFrames;
            int timeFrame = TIME_FRAME;

            do {
                double[] inBuffer = new double[timeFrame];
                inFrames = inFile.readFrames(inBuffer, timeFrame);

                if (inFrames > 0)
                {
                    List<Double> inputs = new ArrayList<>(timeFrame);

                    for (int i = 0; i < timeFrame; i++) {
                        inputs.add(inBuffer[i]);
                    }
                    outputs.addAll(activation.activate(genotype, inputs));
                }
            }
            while (inFrames == timeFrame);

            inFile.close();
        }
        catch (WavFileException | IOException e) {
            e.printStackTrace();
        }


        if (!outputs.isEmpty()) {
            write(genotype.toString(), outputs, inputName);
        }
    }


    private void write(String code, List<Double> outputs, String outputName)
    {
        try {
            String suffix = "-out-" + count + "-" + code + ".wav";
            String outPath = getClass().getClassLoader().getResource("").getPath() + outputName + suffix;

            int sampleRate = 16000;		// Samples per second
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
