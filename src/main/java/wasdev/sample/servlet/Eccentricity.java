package wasdev.sample.servlet;
import java.util.EnumSet;
import de.lmu.ifi.dbs.jfeaturelib.features.AbstractFeatureDescriptor;
import ij.process.ImageProcessor;

public class Eccentricity extends AbstractFeatureDescriptor {

    private double eccentricity;

    public double getEccentricity() {
        return eccentricity;
    }

    @Override
    public void run(ImageProcessor ip) {
        startProgress();

        BoundingBox bb = new BoundingBox();
        bb.run(ip);
        double[] boundingBox = bb.getBoundingBox();
        eccentricity = boundingBox[2] / boundingBox[3];

        addData(new double[]{eccentricity});

        endProgress();
    }

    @Override
    public EnumSet<Supports> supports() {
        return new BoundingBox().supports();
    }

    @Override
    public String getDescription() {
        return "Eccentricity of the shape";
    }
}