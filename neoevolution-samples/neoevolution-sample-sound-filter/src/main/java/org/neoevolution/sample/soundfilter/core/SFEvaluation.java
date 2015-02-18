package org.neoevolution.sample.soundfilter.core;

import org.neoevolution.core.operator.evaluation.TrainingEvaluation;
import org.neoevolution.mvc.dataset.SampleData;
import org.neoevolution.sample.soundfilter.util.WavFile;
import org.neoevolution.sample.soundfilter.util.WavFileException;
import org.neoevolution.util.Randomizer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
public class SFEvaluation extends TrainingEvaluation {

    public static final String INPUT_FILE = "sound/mix-cello-piano.wav";
    public static final String OUTPUT_FILE = "sound/piano.wav";
    public static final int TIME_FRAME = 21;
    public static final int FRAME_SECOND = 44100;

    public SFEvaluation() {
        super();
        initSamples();
    }

    private void initSamples()
    {
        List<Double> inputs = new ArrayList<>();
        List<Double> outputs = new ArrayList<>();

        try {
            String inPath = getClass().getClassLoader().getResource(INPUT_FILE).getPath();
            String outPath = getClass().getClassLoader().getResource(OUTPUT_FILE).getPath();
            WavFile inFile = WavFile.openWavFile(new File(inPath));
            WavFile outFile = WavFile.openWavFile(new File(outPath));

            int timeFrame = TIME_FRAME;
            int inFrames, outFrames;
            double[] inBuffer = new double[timeFrame];
            double[] outBuffer = new double[timeFrame];

            do {
                inFrames = inFile.readFrames(inBuffer, timeFrame);
                outFrames = outFile.readFrames(outBuffer, timeFrame);

                if (inFrames % timeFrame == 0 && outFrames % timeFrame == 0) {
                    for (int i = 0; i < inFrames && i < outFrames; i++) {
                        inputs.add(inBuffer[i]);
                        outputs.add(outBuffer[i]);
                    }
                }
            }
            while (inFrames % timeFrame == 0 && outFrames % timeFrame == 0);

            inFile.close();
            outFile.close();
            initData(inputs, outputs);
        }
        catch (WavFileException | IOException e) {
            e.printStackTrace();
        }
    }

    private void initData(List<Double> inputs, List<Double> outputs)
    {
        int size = inputs.size();
        int frames = calculateFrames(size);
        int inputRange = size - TIME_FRAME;
        List<Integer> indexes = new ArrayList<>(size);

        for (int j = 0; j < frames; j++)
        {
            int start = Randomizer.randomInt(0, inputRange);

            while (indexes.contains(start)) {
                start = Randomizer.randomInt(0, inputRange);
            }
            SampleData sample = new SampleData(TIME_FRAME, TIME_FRAME);

            for (int i = start; i < start + TIME_FRAME; i++) {
                indexes.add(i);
                sample.addInput(inputs.get(i));
                sample.addOutput(outputs.get(i));
            }
            data.add(sample);
        }
    }

    private int calculateFrames(int size)
    {
        int s = size;

        if (size >= FRAME_SECOND) {
            s = FRAME_SECOND;
        }
        return s / TIME_FRAME;
    }

}
