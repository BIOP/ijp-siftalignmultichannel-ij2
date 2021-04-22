import ch.epfl.biop.register.SIFT_Align_MultiChannel;
import ij.IJ;
import ij.ImagePlus;
import net.imagej.ImageJ;

public class RegistrationDemo {

    public static void main(String... args) {
        // set the plugins.dir property to make the plugin appear in the Plugins menu
        Class<?> clazz = SIFT_Align_MultiChannel.class;
        String url = clazz.getResource("/" + clazz.getName().replace('.', '/') + ".class").toString();
        String pluginsDir = url.substring("file:".length(), url.length() - clazz.getName().length() - ".class".length());
        System.setProperty("plugins.dir", pluginsDir);

        // create the ImageJ application context with all available services
        final ImageJ ij = new ImageJ();
        ij.ui().showUI();

        ImagePlus imp = IJ.openImage("src/test/resources/TestRegMultiChannel.tif");
        imp.show();
        IJ.run(imp, "Linear Stack Alignment with SIFT MultiChannel",
                "registration_channel=1 "+
                "initial_gaussian_blur=1.60 "+
                "steps_per_scale_octave=3 "+
                "minimum_image_size=64 maximum_image_size=1024 "+
                "feature_descriptor_size=4 "+
                "feature_descriptor_orientation_bins=8 "+
                "closest/next_closest_ratio=0.92 "+
                "maximal_alignment_error=25 "+
                "inlier_ratio=0.05 "+
                "expected_transformation=Affine interpolate");
    }

}