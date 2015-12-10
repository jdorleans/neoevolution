package org.neoevolution.sample.soundfilter.core;

import org.neoevolution.core.operator.evaluation.TrainingEvaluation;
import org.neoevolution.mvc.dataset.SampleData;
import org.neoevolution.sample.soundfilter.util.WavFile;
import org.neoevolution.sample.soundfilter.util.WavFileException;

import java.io.File;
import java.io.IOException;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
public class SFEvaluation extends TrainingEvaluation {

    public static final String INPUT_FILE = "sound/mix-cello-piano.wav";
    public static final String OUTPUT_FILE = "sound/piano.wav";
    public static final int TIME_FRAME = 16;

    public SFEvaluation() {
        super();
        initSamples();
    }

    private void initSamples()
    {
        try {
            String inPath = getClass().getClassLoader().getResource(INPUT_FILE).getPath();
            String outPath = getClass().getClassLoader().getResource(OUTPUT_FILE).getPath();
            WavFile inFile = WavFile.openWavFile(new File(inPath));
            WavFile outFile = WavFile.openWavFile(new File(outPath));

            int timeFrame = TIME_FRAME;
            int inFrames, outFrames;

            do {
                double[] inBuffer = new double[timeFrame];
                double[] outBuffer = new double[timeFrame];
                inFrames = inFile.readFrames(inBuffer, timeFrame);
                outFrames = outFile.readFrames(outBuffer, timeFrame);

                if (inFrames > 0 && outFrames > 0)
                {
                    SampleData sample = new SampleData(timeFrame, timeFrame);

                    for (int i = 0; i < timeFrame; i++) {
                        sample.addInput(inBuffer[i]);
                        sample.addOutput(outBuffer[i]);
                    }
                    data.add(sample);
                }
            }
            while (inFrames == timeFrame && outFrames == timeFrame);

            inFile.close();
            outFile.close();
        }
        catch (WavFileException | IOException e) {
            e.printStackTrace();
        }
    }

}
